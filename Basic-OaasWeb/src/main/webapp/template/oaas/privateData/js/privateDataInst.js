//////////////////////////////////////////
//知识体系
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.privateDataInst = function() {

	var dataInstGrid = null;
	var dataId = null;
	return {
		init : function() {
			dataInstGrid =  new ruizhi.DataGrid("oaas-dataInst-grid");
			
			tab1 = new ruizhi.Tab('oaas-dataInst-tab');
		},
		
		loadData : function(param){//加载数据
			var url = WEB_ROOT + '/oaas/func/privateDataInst/pagin.do';
			if(ruizhi.IsNull(param)){
				return;
			}
			dataId = param.dataId;
			var paramObj = {
				dataId : param.dataId
			}
			dataInstGrid.loadData(url, paramObj);
		},
		
		/*添加员数据权限实例*/
		create:function(){
			
			if(ruizhi.IsNull(dataId)){
				ruizhi.Alert('请选择数据权限');
				return;
			}
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/privateDataInst/privateDataInstDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.privateDataInst.createComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			paramObj.dataId = dataId;//用户标识（All的状态可选）
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		createComeBack : function(param){
			var params = {};
			params.params = ruizhi.ToJson(param);
			var url = WEB_ROOT + "/oaas/func/privateDataInst/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.oaas.privateDataInst.loadData);
		},
		
		//删除员工职位
		del:function (){
			var selItemIds =  dataInstGrid.getCheckedIds();
			var selItem =  dataInstGrid.getCheckedItems();
			if(ruizhi.IsNull(selItemIds)){
				ruizhi.Alert('请选择数据权限实例');
				return;
			}
			
			
			ruizhi.Confirm("是否删除选择的数据权限实例？",function(result){
				if(result || 'true' == result) {
					var params = {};
					params.dataInstIds = selItemIds.join(",");
					var url = WEB_ROOT+"/oaas/func/privateDataInst/del.do";
					ruizhi.InvokeMethodAsyn(url,params,ruizhi.oaas.privateDataInst.loadData);
				} else {
					
				}
			})
			
		},
		itemClick:function(){
			var selItem = dataInstGrid.getSelectedItem();
			var param = selItem;
			ruizhi.oaas.instData.loadData(param);
		},
//		qry :function(){
//			var url = WEB_ROOT + '/oaas/func/privateDataInst/pagin.do';
//			var paramObj = privateDataInstFrom.formToObject();
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
//			privateDataInstGrid.loadData(url, paramObj);
//		},
		
		
		
		

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.privateDataInst.init();
});

// ////////////////////////////////////////
// function定义
