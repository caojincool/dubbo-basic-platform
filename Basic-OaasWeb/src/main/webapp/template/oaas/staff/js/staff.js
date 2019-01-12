//////////////////////////////////////////
//知识体系
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.staff = function() {

	var orgTree = null;
	var staffGrid = null;
	var staffFrom = null;// 表单
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
			staffFrom =  new ruizhi.FormExt("oaas-staffInfo-form1");
			orgTree = new ruizhi.ZTree("org-orgTree-staff",{
				url : WEB_ROOT+"/oaas/func/org/qryOrgTree.do",
				autoParam:["id=parentOrgId"],
				otherParam :{
					userId : _SESSION.userId,
					orgType : orgType,
				}
			});
			
			staffGrid = new ruizhi.DataGrid("oaas-staff-grid1");
		},
		
		loadData : function(param){//加载数据
			var url = WEB_ROOT + '/oaas/func/staff/pagin.do';
			var paramObj = {
				orgId : param.orgId
			}
			staffGrid.loadData(url, paramObj);
		},
		/*部门点击事件*/
		treeClick : function(treeNode) {
			ruizhi.oaas.staff.loadData(treeNode);
		},
		
		/*添加员工*/
		create:function(){
			//重置表单
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/staff/staffDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.staff.createComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		createComeBack : function(param){
			var params = {};
			params.params = ruizhi.ToJson(param);
			var url = WEB_ROOT + "/oaas/func/staff/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.staff.loadData(param);
			});
		},
		
		
		modify:function(){
			var selItems =  staffGrid.getCheckedItems();
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/staff/staffDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.staff.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			if(!ruizhi.IsNull(selItems)){
				if(selItems.length==1){
					paramObj.staff = selItems[0];
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
			var url = WEB_ROOT + "/oaas/func/staff/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.staff.loadData(param);
			});
		},
		
		//删除节点
		del:function (){
			var selItemIds =  staffGrid.getCheckedIds();
			var selItem = staffGrid.getSelectedItem();
			if(ruizhi.IsNull(selItemIds)){
				ruizhi.Alert('请选择员工');
				return;
			}
			
			
			ruizhi.Confirm("是否删除选择的员工？",function(result){
				if(result || 'true' == result) {
					var params = {};
					params.staffIds = selItemIds.join(",");
					var url = WEB_ROOT+"/oaas/func/staff/del.do";
					ruizhi.InvokeMethodAsyn(url,params,function(){
						
						ruizhi.oaas.staff.loadData(selItem);
					});
				} else {
					
				}
			})
			
		},
		itemClick:function(){
			var selItem = staffGrid.getSelectedItem();
			
			var paramObj = {
				staffId : selItem.staffId	
			}
			
			ruizhi.oaas.staffJob.loadData(paramObj);
			ruizhi.oaas.userStaff.loadData(paramObj);
		},
		qry :function(){
			var url = WEB_ROOT + '/oaas/func/staff/pagin.do';
			var paramObj = staffFrom.formToObject();
			var treeNode =  orgTree.getSelectedNodes()[0];
			//查询下属部门时
			if(paramObj.qrySubOrg){
				if(ruizhi.IsNull(treeNode)){
					ruizhi.Alert("请选择部门");
				}else{
					paramObj.orgId =treeNode.id;
				}
			}else{
				if(ruizhi.IsNull(paramObj.orgId)){
					paramObj.orgId=_SESSION.orgId;
				}
				if(orgType =='CURORG'){
					paramObj.orgType = orgType;
				}
			}
			staffGrid.loadData(url, paramObj);
		},
		staffTrans : function(cellvalue, options, rowObject){
			if(rowObject.sex == 1){
				return "男";
			}else if(rowObject.sex == 0){
				return "女";
			}
		},
		resetForm : function(){
			
			staffFrom.reset();
			
		}
		

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.staff.init();
});

// ////////////////////////////////////////
// function定义
