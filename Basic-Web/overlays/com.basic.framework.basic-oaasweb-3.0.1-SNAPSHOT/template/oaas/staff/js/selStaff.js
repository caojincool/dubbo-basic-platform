//人员单选窗口
ruizhi.Package("ruizhi.oaas");
ruizhi.oaas.selStaff = function(){
	
	var URL =  WEB_ROOT + '/oaas/funcPage/staff/selStaff.do';//当前窗口的URL
	
	var orgTree = null;
	var staffGrid = null;
	var staffFrom = null;// 表单
	var orgType = null;
	
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);
			
			// TODO 权限功能完善需要回来改
//			if(ruizhi.IsSuperadminRole() || _SESSION.hasPriv("oaas.special.allAreaQry") ){
			if(ruizhi.IsSuperadminRole() ){
				orgType ='ALLORG';
			}else{
				orgType ='CURORG';
			}
			staffFrom =  new ruizhi.FormExt("selStaff-staffInfo-form1");
			orgTree = new ruizhi.ZTree("selStaff-orgTree-td",{
				url : WEB_ROOT+"/oaas/func/org/qryOrgTree.do",
				autoParam:["id=parentOrgId"],
				otherParam :{
					userId : _SESSION.userId,
					orgType : orgType,
				}
			});
			
			staffGrid = new ruizhi.DataGrid("selStaff-staff-grid1");
			
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#oaas-selStaff-title").html(paramObj.title);
			}
			
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
			ruizhi.oaas.selStaff.loadData(treeNode);
		},
		
		qry :function(){
			var url = WEB_ROOT + '/oaas/func/staff/pagin.do';
			var paramObj = staffFrom.formToObject();
			var treeNode =  orgTree.getSelectedNodes()[0];
			//查询下属部门时
			if(paramObj.qrySubOrg){
				if(ruizhi.IsNull(treeNode)){
					ruizhi.Alert("请选择部门");
					return;
				}else{
					paramObj.orgId =treeNode.id;
				}
			}else{
				/*if(ruizhi.IsNull(paramObj.orgId)){
					paramObj.orgId=ruizhi.GetUser().orgId;
				}*/
				if(orgType =='CURORG'){
					paramObj.userId =_SESSION.userId;
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
		//i职位双击确定事件
		staffDbClick : function(rowId) {
			//alert(rowId);
			this.doSubmit();
		},

		doSubmit : function() {

			var selItem = staffGrid.getSelectedItem();//所选行
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
	ruizhi.oaas.selStaff.init();
});