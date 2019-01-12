//人员单选窗口
ruizhi.Package("ruizhi.oaas");
ruizhi.oaas.selDataGroup = function(){
	
	var URL =  WEB_ROOT + '/oaas/funcPage/user/selDataGroup.do';//当前窗口的URL
	
	var unSelectedGrid = null;
	var selectedGrid = null;
	var userDataGroup = null;
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
			
			unSelectedGrid = new ruizhi.DataGrid("oaas-unselDataGroup-grid1",{
				url : WEB_ROOT + '/oaas/func/privDataGroup/pagin.do',
				
			});
			selectedGrid = new ruizhi.DataGrid("oaas-selDataGroup-grid1",{
				url : WEB_ROOT + '/oaas/func/privDataGroup/pagin.do',
				postData : {
					userId : userId,
					
				},
				loadComplete : function(data){
					ruizhi.oaas.selDataGroup.loadComplete(data);
				}
			});
			
			
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#oaas-selDataGroup-title").html(paramObj.title);
			}
			
		},
		

		doSubmit : function() {

			var selItem = selectedGrid.getAllIds();//所选行
			var userDataGroups = [];
			
			for(var i in selItem){
				userDataGroups.push({
					userId : userId,
					dataGroupId : selItem[i]
				})
			}
			var param = {
				userId : userId,
				userDataGroups :userDataGroups
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
				selectedGrid.addItem(checkeItems[i].dataGroupId,checkeItems[i]);
				unSelectedGrid.removeItem(checkeItems[i].dataGroupId);
			}
		},
		unselectedBtn :function(){
			var checkeItems = selectedGrid.getCheckedItems();
			
			for(var i in checkeItems){
				unSelectedGrid.addItem(checkeItems[i].dataGroupId,checkeItems[i]);
				selectedGrid.removeItem(checkeItems[i].dataGroupId);
			}
		},
		loadComplete : function(data){
			console.log(data);
			if(!ruizhi.IsNull(data)){
				
				var list = data.root;
				for(var i in list){
					
					unSelectedGrid.removeItem(list[i].dataGroupId);
				}
			}
		},
		
		
	}
	
}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.selDataGroup.init();
});