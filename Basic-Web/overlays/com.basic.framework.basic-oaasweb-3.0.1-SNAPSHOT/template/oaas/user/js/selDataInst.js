//人员单选窗口
ruizhi.Package("ruizhi.oaas");
ruizhi.oaas.selDataInst = function(){
	
	var URL =  WEB_ROOT + '/oaas/funcPage/user/selDataInst.do';//当前窗口的URL
	
	var unSelectedGrid = null;
	var selectedGrid = null;
	var userDataInst = null;
	var userId = null;
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);
			var submitParam = null;
			
			if(!ruizhi.IsNull(paramObj)){
				submitParam = {
				}
				userId = paramObj.userId;
			}
			
			unSelectedGrid = new ruizhi.DataGrid("oaas-unselDataInst-grid1",{
				url : WEB_ROOT + '/oaas/func/privateDataInst/pagin.do',
				
			});
			selectedGrid = new ruizhi.DataGrid("oaas-selDataInst-grid1",{
				url : WEB_ROOT + '/oaas/func/privateDataInst/pagin.do',
				postData : {
					userId : userId,
					
				},
				loadComplete : function(data){
					ruizhi.oaas.selDataInst.loadComplete(data);
				}
			});
			
			
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#oaas-selDataInst-title").html(paramObj.title);
			}
			
		},
		

		doSubmit : function() {

			var selItem = selectedGrid.getAllIds();//所选行
			var userDataInsts = [];
			
			for(var i in selItem){
				userDataInsts.push({
					userId : userId,
					dataInstId : selItem[i]
				})
			}
			var param = {
				userId : userId,
				userDataInsts :userDataInsts
			}
			
			if(selItem!=null){
				ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			}else{
				ruizhi.Alert('请选择');
			}
			
			
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		
		selectedBtn :function(){
			var checkeItems = unSelectedGrid.getCheckedItems();
			
			for(var i in checkeItems){
				selectedGrid.addItem(checkeItems[i].dataInstId,checkeItems[i]);
				unSelectedGrid.removeItem(checkeItems[i].dataInstId);
			}
		},
		unselectedBtn :function(){
			var checkeItems = selectedGrid.getCheckedItems();
			
			for(var i in checkeItems){
				unSelectedGrid.addItem(checkeItems[i].dataInstId,checkeItems[i]);
				selectedGrid.removeItem(checkeItems[i].dataInstId);
			}
		},
		loadComplete : function(data){
			console.log(data);
			if(!ruizhi.IsNull(data)){
				
				var list = data.root;
				for(var i in list){
					
					unSelectedGrid.removeItem(list[i].dataInstId);
				}
			}
		},
		
		
	}
	
}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.selDataInst.init();
});