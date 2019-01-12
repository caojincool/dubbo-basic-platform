//人员单选窗口
ruizhi.Package("ruizhi.oaas");
ruizhi.oaas.selArea = function(){
	
	var URL =  WEB_ROOT + '/oaas/funcPage/org/selOrg.do';//当前窗口的URL
	
	var zTree1;
	var showCheck =false;
	
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);
			
			var url = WEB_ROOT + '/oaas/func/org/qryOrgTree.do';
			
			showCheck = paramObj.showCheck;
			ruizhi.GetObj("oaas-org-zTree1").attr("showCheck",paramObj.showCheck);
			
			//组织
			zTree1 = new ruizhi.ZTree("oaas-org-zTree1",{
				url : url,
				autoParam:["id=parentOrgId"],
				otherParam :{
					userId : _SESSION.userId,
					orgType : paramObj.orgType
				}
			});
			var qryParams = {};
//			var param = paramObj;
//			qryParams.params = param;
//			zTree1.loadData(url,qryParams);
			
			
			if(paramObj.title != null && paramObj.title != '' && paramObj.title != 'undefined'){
				$("#oaas-selOrg-title").html(paramObj.title);
			}
			
		},

		doSubmit : function() {

			var selItem = showCheck ? zTree1.getCheckedNodes(true) : zTree1.getSelectedNodes();//所选行
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
	ruizhi.oaas.selArea.init();
});