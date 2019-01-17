//人员单选窗口
ruizhi.Package("ruizhi.oaas");
ruizhi.oaas.selPrivateDataInst = function(){
	
	var URL =  WEB_ROOT + '/oaas/funcPage/privateDataInst/selPrivateDataInst.do';//当前窗口的URL
	
	var grid1;
	var form1;
	
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);
			var submitParam = null;
			
			if(!ruizhi.IsNull(paramObj)){
				submitParam = {
				}
			}
//			form1 = new ruizhi.FormExt("oaas-selPrivateDataInst-form1");
			
			grid1 = new ruizhi.DataGrid("oaas-selPrivateDataInst-grid1",{
				postData : submitParam,
				url : WEB_ROOT + '/oaas/func/privateDataInst/pagin.do'
			});
			
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#oaas-selPrivateDataInst-title").html(paramObj.title);
			}
			
		},
		
		
		qry : function(){//查询加载数据
			var url = WEB_ROOT + '/oaas/func/user/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
			
		},
		
		//i职位双击确定事件
		dataDbClick : function(rowId) {
			//alert(rowId);
			this.doSubmit();
		},

		doSubmit : function() {

			var selItem = grid1.getSelectedItem();//所选行
			if(selItem!=null){
				ruizhi.SubmitModalWin(URL,selItem);//通过公用方法调用父页面的回调方法
			}else{
				ruizhi.Alert('请选择');
			}
			
			
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		
	}
	
}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.selPrivateDataInst.init();
});