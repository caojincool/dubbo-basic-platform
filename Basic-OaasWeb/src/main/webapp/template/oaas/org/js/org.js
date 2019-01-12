//////////////////////////////////////////
//知识体系
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.org = function() {

	var orgTree = null;// 组织树
	var gridList = null;// 右边树
	var orgFrom = null;// 表单

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
			orgFrom =  new ruizhi.FormExt("org-orgMana-from");
			orgTree = new ruizhi.ZTree("org-orgTree-td",{
				url : WEB_ROOT+"/oaas/func/org/qryOrgTree.do",
				autoParam:["id=parentOrgId"],
				otherParam :{
					userId : _SESSION.userId,
					orgType : orgType,
				}
			});
		},
		
		loadData : function(){//加载数据
			var url = WEB_ROOT+"/oaas/func/org/qryOrgTree.do";
			var paramObj = {
				userId : _SESSION.userId,
				orgType : orgType,
			}
			orgTree.loadData(url,paramObj);
		},
		/*部门点击事件*/
		treeClick : function(treeNode) {
			orgFrom.reset();
			orgFrom.objectToForm(treeNode);
		},
		
		/*添加部门*/
		createOrg:function(){
			var treeNode =  orgTree.getSelectedNodes()[0];
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/org/orgDetail.do';
			var width = null;
			var heigth = null;
			var privateCatalog = {};
			if(!ruizhi.IsNull(treeNode)){
				paramObj.Org={
					parentOrgId :treeNode.parentOrgId,
				}
			}
			var submitFn = ruizhi.oaas.org.createOrgComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		createOrgComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			var url = WEB_ROOT + "/oaas/func/org/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.oaas.org.loadData);
		},
		
		/*添加子部门*/
		createSubOrg:function(form){
			var treeNode =  orgTree.getSelectedNodes()[0];
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/org/orgDetail.do';
			var width = null;
			var heigth = null;
			var privateCatalog = {};
			if(ruizhi.IsNull(treeNode)){
				ruizhi.Alert('请选择部门');
				return;
			}
			paramObj.Org={
					parentOrgId :treeNode.orgId,
			}
			var submitFn = ruizhi.oaas.org.createOrgComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			
		},
		
		editOrg:function(){
			var treeNode =  orgTree.getSelectedNodes()[0];
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/org/orgDetail.do';
			var width = null;
			var heigth = null;
			var privateCatalog = {};
			if(ruizhi.IsNull(treeNode)){
				ruizhi.Alert('请选择部门');
				return;
			}
			paramObj.Org=treeNode;
			var submitFn = ruizhi.oaas.org.createOrgComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		//删除节点
		delOrg:function (){
			var treeNode = orgTree.getSelectedNodes()[0];
			if(ruizhi.IsNull(treeNode)){
				ruizhi.Alert('请选择部门');
				return;
			}
			
			var msg ="";
			if(treeNode.isParent){
				msg ="是否删除该部门，以及该部门下的所有部门?";
			}else{
				msg ="是否删除该部门？";
			}
			
			ruizhi.Confirm(msg,function(result){
				if(result || 'true' == result) {
					var params = {};
					params.orgIds = treeNode.orgId;
//					debugger;
					var url = WEB_ROOT+"/oaas/func/org/orgDel.do";
					ruizhi.InvokeMethodAsyn(url,params,ruizhi.oaas.org.loadData);
				} else {
					
				}
			})
			
//			var url = WEB_ROOT+"/oaas/func/orgDel.do";
//			ruizhi.InvokeMethod(url,{orgIds:selectNode.id});
		}
		
		
		
		

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.org.init();
});

// ////////////////////////////////////////
// function定义
