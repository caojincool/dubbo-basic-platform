//人员单选窗口
ruizhi.Package("ruizhi.oaas");
ruizhi.oaas.selJob = function(){
	
	var URL =  WEB_ROOT + '/oaas/funcPage/job/selJob.do';//当前窗口的URL
	
	var orgTree;
	var grid1;
	var tab1;
	var form1;
	
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);
			
			form1 = new ruizhi.FormExt("oaas-selJob-form1");
			
			grid1 = new ruizhi.DataGrid("oaas-selJob-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/oaas/func/job/pagin.do'
			});
			
			orgTree = new ruizhi.ZTree("selJob-orgTree-td",{
				url : WEB_ROOT+"/oaas/func/org/qryOrgTree.do",
				autoParam:["id=parentOrgId"],
				otherParam :{
					userId : _SESSION.userId,
					orgType : paramObj.orgType,
				}
			});
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#oaas-selJob-title").html(paramObj.title);
			}
			
		},
		
		orgClick : function(treeNode) {// 组织单击
			//alert(rowId);
			var url = WEB_ROOT + '/oaas/func/job/pagin.do';
			var paramObj = {
				orgId : treeNode.id,
				jobName : null,
				orgName : null
			}
			grid1.loadData(url, paramObj);
			
		},
		
		qry : function(){//查询加载数据
			var url = WEB_ROOT + '/oaas/func/job/pagin.do';
			var paramObj = form1.formToObject();
			paramObj.orgId ='';
			if(orgType =='CURORG'){
				paramObj.orgType = orgType;
				paramObj.orgId = _SESSION.orgId;
			}
			grid1.loadData(url, paramObj);
			
		},
		
		//i职位双击确定事件
		jobDbClick : function(rowId) {
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
	ruizhi.oaas.selJob.init();
});