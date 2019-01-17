//////////////////////////////////////////
//知识体系
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.privDataGroupInst = function() {

	var dataInstGrid = null;
	var tab = null;
	var dataGroupId = null;
	return {
		init : function() {
			tab = new ruizhi.Tab("oaas-dataInst-tab");
			dataInstGrid =  new ruizhi.DataGrid("oaas-dataInst-grid");
		},
		
		loadData : function(param){//加载数据
			var url = WEB_ROOT + '/oaas/func/privateData/pagin.do';
			if(ruizhi.IsNull(param)){
				return;
			}
			dataGroupId = param.dataGroupId;
			var paramObj = {
				dataGroupId : param.dataGroupId
			}
			dataInstGrid.loadData(url, paramObj);
		},
		
		/*添加员数据权限实例*/
		create:function(){
			
			if(ruizhi.IsNull(dataGroupId)){
				ruizhi.Alert('请选择数据权限');
				return;
			}
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/privateDataInst/selPrivateDataInst.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.privDataGroupInst.createComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			paramObj.dataGroupId = dataGroupId;//用户标识（All的状态可选）
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		createComeBack : function(param){
			var params = {};
			paramObj = {
					dataGroupId:dataGroupId,
					dataInstId:param.dataInstId
			}
			params.params = ruizhi.ToJson(paramObj);
			var url = WEB_ROOT + "/oaas/func/privDataGroupInst/create.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.privDataGroupInst.loadData({dataGroupId:dataGroupId});
			});
		},
		
		//删除数据实例
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
					params.dataGrpInstIds = selItemIds.join(",");
					var url = WEB_ROOT+"/oaas/func/privDataGroupInst/del.do";
					ruizhi.InvokeMethodAsyn(url,params,function(){
						
						ruizhi.oaas.privDataGroupInst.loadData({dataGroupId :dataGroupId});
					});
				} else {
					
				}
			})
			
		},
		itemClick:function(){
			var selItem = dataInstGrid.getSelectedItem();
			var param = selItem;
			ruizhi.oaas.privInstData.loadData(param);
		},
//		qry :function(){
//			var url = WEB_ROOT + '/oaas/func/privDataGroupInst/pagin.do';
//			var paramObj = privDataGroupInstFrom.formToObject();
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
//			privDataGroupInstGrid.loadData(url, paramObj);
//		},
		
		
		
		

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.privDataGroupInst.init();
});

// ////////////////////////////////////////
// function定义
