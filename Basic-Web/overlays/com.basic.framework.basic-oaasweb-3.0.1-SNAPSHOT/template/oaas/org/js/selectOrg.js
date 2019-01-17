//人员单选窗口
ruizhi.Package("ruizhi.oaas");
ruizhi.oaas.selectOrg = function(){
	
	var URL =  WEB_ROOT + '/oaas/funcPage/org/selectOrg.do';//当前窗口的URL
	
	var zTree1;
	
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);
			
			var url = WEB_ROOT + '/oaas/func/org/qryOrgTree.do';
			//组织
			zTree1 = new ruizhi.ZTree("oaas-selectOrg-zTree1",{
				url : url,
				autoParam:["id=parentOrgId"],
				otherParam :{
					userId : _SESSION.userId,
					orgType : paramObj.orgType
				}
			});
	
			if(paramObj.title != null && paramObj.title != '' && paramObj.title != 'undefined'){
				$("#oaas-selectOrg-title").html(paramObj.title);
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
	ruizhi.oaas.selectOrg.init();
});