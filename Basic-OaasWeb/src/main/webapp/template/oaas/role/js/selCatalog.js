//人员单选窗口
ruizhi.Package("ruizhi.oaas");
ruizhi.oaas.selRoleCatalog = function(){
	
	var URL =  WEB_ROOT + '/oaas/funcPage/roleCatalog/selCatalog.do';//当前窗口的URL
	
	var zTree1;
	
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);
			
			//组织
			zTree1 = new ruizhi.ZTree("oaas-roleCatalog-zTree1",{
				autoParam:["id=parentCatalogId"],
			});
			var url = WEB_ROOT + '/oaas/func/roleCatalog/qryTree.do';
			var qryParams = {};
			var param = ruizhi.ToJson(paramObj);
			qryParams.params = param;
			zTree1.loadData(url,qryParams);
			
/*			grid_org1 = new ruizhi.DataGrid("oaas-org-grid1", {
				postData : {
					orgId : _SESSION.orgId,
					orgFlag : orgFlag
				},
				url : WEB_ROOT+"/oaas/func/org/qryTree.do"
			});*/
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#oaas-selRoleCatalog-title").html(paramObj.title);
			}
			
		},

		doSubmit : function() {

			var selItem = zTree1.getSelectedNodes();//所选行
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

//初始化
ruizhi.ExecWait(function() {
	ruizhi.oaas.selRoleCatalog.init();
});