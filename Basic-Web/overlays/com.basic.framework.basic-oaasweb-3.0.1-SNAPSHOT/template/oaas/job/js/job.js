//////////////////////////////////////////
//知识体系
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.job = function() {

	var orgTree = null;// 组织树
	var jobGrid = null;// 右边树
	var jobFrom = null;// 表单
	var tab = null;
	var orgType = null;
	return {
		init : function() {
			// TODO 权限功能完善需要回来改
//			if(ruizhi.IsSuperadminRole() || _SESSION.hasPriv("oaas.special.allAreaQry") ){
			if(ruizhi.IsSuperadminRole() ){
				orgType ='ALLORG';
			}else{
				orgType ='CURORG';
			}
			jobFrom =  new ruizhi.FormExt("oaas-jobInfo-form1");
			orgTree = new ruizhi.ZTree("org-orgTree-job",{
				url : WEB_ROOT+"/oaas/func/org/qryOrgTree.do",
				autoParam:["id=parentOrgId"],
				otherParam :{
					userId : _SESSION.userId,
					orgType : orgType,
				}
			});
			
			jobGrid = new ruizhi.DataGrid("oaas-job-grid1");
			
			tab = new ruizhi.Tab('oaas-job-tab');
		},
		
		loadData : function(param){//加载数据
			var url = WEB_ROOT + '/oaas/func/job/pagin.do';
			var paramObj = {
				orgId : param.orgId,
			}
			jobGrid.loadData(url, paramObj);
		},
		/*部门点击事件*/
		treeClick : function(treeNode) {
			ruizhi.oaas.job.loadData(treeNode);
		},
		
		/*添加职位*/
		create:function(){
			//重置表单
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/job/jobDetail.do';
			var width = null;
			var heigth = null;
			var privateCatalog = {};
			var submitFn = ruizhi.oaas.job.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		createComeBack : function(param){
			var params = {};
			params.params = ruizhi.ToJson(param);
			var url = WEB_ROOT + "/oaas/func/job/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				ruizhi.oaas.job.loadData(param);
			});
		},
		
		
		modify:function(){
			var selItems =  jobGrid.getCheckedItems();
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/job/jobDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.job.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			if(!ruizhi.IsNull(selItems)){
				if(selItems.length==1){
					paramObj.job = selItems[0];
					ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
				}else{
					ruizhi.Alert('请选择一条记录');
				}
			}else{
				ruizhi.Alert('请选择');
			}

		},
		modifyComeBack :function(param){
			var params = {};
			params.params = ruizhi.ToJson(param);
			var url = WEB_ROOT + "/oaas/func/job/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.job.loadData(param);
			});
			
		},
		
		//删除节点
		del:function (){
			var selItemIds =  jobGrid.getCheckedIds();
			var selItem = jobGrid.getSelectedItem();
			if(ruizhi.IsNull(selItemIds)){
				ruizhi.Alert('请选择职位');
				return;
			}
			
			
			ruizhi.Confirm("是否删除选择的职位？",function(result){
				if(result || 'true' == result) {
					var params = {};
					params.jobIds = selItemIds.join(",");
					var url = WEB_ROOT+"/oaas/func/job/del.do";
					ruizhi.InvokeMethodAsyn(url,params,function(){
						
						ruizhi.oaas.job.loadData(selItem);
					});
				} else {
					
				}
			})
			
		},
		itemClick:function(){
			jobFrom.reset();
			var selItem = jobGrid.getSelectedItem();
			jobFrom.objectToForm(selItem);
		}
		
		
		
		
		

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.job.init();
});

// ////////////////////////////////////////
// function定义
