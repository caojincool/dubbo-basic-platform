//////////////////////////////////////////
//知识体系
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.staffJob = function() {

	var jobGrid = null;
	var staffId = null;
	return {
		init : function() {
			jobGrid =  new ruizhi.DataGrid("oaas-jobTab-grid");
//			userGrid = new ruizhi.DataGrid("oaas-userTab-grid");
			
			tab1 = new ruizhi.Tab('oaas-job-tab');
//			tab2 = new ruizhi.Tab('oaas-user-tab');
		},
		
		loadData : function(param){//加载数据
			var url = WEB_ROOT + '/oaas/func/staffJob/pagin.do';
			staffId = param.staffId;
			var paramObj = {
				staffId : param.staffId
			}
			jobGrid.loadData(url, paramObj);
		},
		
		/*添加员工职位*/
		create:function(){
			
			if(ruizhi.IsNull(staffId)){
				ruizhi.Alert('请选择员工');
				return;
			}
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/job/selJob.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.staffJob.createComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			if(ruizhi.IsSuperadminRole() ){
				orgType ='ALLORG';
			}else{
				orgType ='CURORG';
			}
			paramObj.userId = _SESSION.userId;//用户标识（All的状态可选）
			paramObj.orgType = orgType;//查询范围
			paramObj.title = "单选职位";
			
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		createComeBack : function(param){
			var params = {};
			param.staffId = staffId;
			param.defaultJob = 0;
			var rul1 = WEB_ROOT + "/oaas/func/staffJob/verifyJob.do";
			var flag =  ruizhi.InvokeMethod(rul1,param);
			
			if(!flag.flag){
				ruizhi.Alert('该职位已存在！');
			}else{
				params.params = ruizhi.ToJson(param);
				var url = WEB_ROOT + "/oaas/func/staffJob/createOrModify.do";
				ruizhi.InvokeMethod(url,params);
			}
			ruizhi.oaas.staffJob.loadData(param);
		},
		
		//删除员工职位
		del:function (){
			var selItemIds =  jobGrid.getCheckedIds();
			debugger;
			var selItem =  jobGrid.getCheckedItems();
			for(var key in selItem ){
				if(selItem[key].defaultJob == 1){
					ruizhi.Alert('不能删除默认职位');
					return;
				}
			}
			if(ruizhi.IsNull(selItemIds)){
				ruizhi.Alert('请选择职位');
				return;
			}
			
			
			ruizhi.Confirm("是否删除选择的职位？",function(result){
				if(result || 'true' == result) {
					var params = {};
					params.staffJobIds = selItemIds.join(",");
					var url = WEB_ROOT+"/oaas/func/staffJob/del.do";
					ruizhi.InvokeMethodAsyn(url,params,function(){
						
						ruizhi.oaas.staffJob.loadData({staffId :staffId});
					});
				} else {
					
				}
			})
			
		},
		itemClick:function(){
//			var selItem = staffJobGrid.getSelectedItem();
//			
//			var paramObj = {
//				staffJobId : selItem.staffJobId	
//			}
//			var url1 =WEB_ROOT + "/oaas/func/staffJobJob/pagin";
//			var url2 =WEB_ROOT + ""
//			jobGrid.loadData(url1, paramObj)
//			userGrid.loadData(url2, paramObj)
		},
//		qry :function(){
//			var url = WEB_ROOT + '/oaas/func/staffJob/pagin.do';
//			var paramObj = staffJobFrom.formToObject();
//			var treeNode =  orgTree.getSelectedNodes()[0];
//			//查询下属部门时
//			if(paramObj.qrySubOrg){
//				if(ruizhi.IsNull(treeNode)){
//					ruizhi.Alert("请选择部门");
//				}else{
//					paramObj.orgId =treeNode.id;
//				}
//			}else{
//				debugger;
//				if(ruizhi.IsNull(paramObj.orgId)){
//					paramObj.orgId=_SESSION.orgId;
//				}
//				if(orgType =='CURORG'){
//					paramObj.orgType = orgType;
//				}
//			}
//			staffJobGrid.loadData(url, paramObj);
//		},
		staffJobTrans : function(cellvalue, options, rowObject){
			if(rowObject.defaultJob == 1){
				return "是";
			}else if(rowObject.defaultJob == 0){
				return "否";
			}
		},
		
		
		
		

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.staffJob.init();
});

// ////////////////////////////////////////
// function定义
