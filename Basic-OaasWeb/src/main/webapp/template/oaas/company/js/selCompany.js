//人员单选窗口
ruizhi.Package("ruizhi.oaas");
ruizhi.oaas.selCompany = function(){
	
	var URL =  WEB_ROOT + '/oaas/funcPage/company/selCompany.do';//当前窗口的URL
	
	var orgTree = null;
	var companyGrid = null;
	var companyFrom = null;// 表单
	var orgType = null;
	
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);
			
			companyFrom =  new ruizhi.FormExt("selCompany-company-form1");
			
			companyGrid = new ruizhi.DataGrid("selCompany-company-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/oaas/func/company/pagin.do'
			});
			
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#oaas-selCompany-title").html(paramObj.title);
			}
			
		},
		loadData : function(){//加载数据
			var url = WEB_ROOT + '/oaas/func/company/pagin.do';
			var paramObj = companyFrom.formToObject();
			companyGrid.loadData(url, paramObj);
		},
		
		//双击确定事件
		companyDbClick : function(rowId) {
			//alert(rowId);
			this.doSubmit();
		},

		doSubmit : function() {

			var selItem = companyGrid.getSelectedItem();//所选行
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
	ruizhi.oaas.selCompany.init();
});