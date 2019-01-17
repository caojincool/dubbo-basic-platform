//////////////////////////////////////////
//数据权限数据
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.instData = function() {

	var instDataGrid = null;
	var dataInstId = null;
	return {
		init : function() {
			instDataGrid =  new ruizhi.DataGrid("oaas-instData-grid");
			
			tab1 = new ruizhi.Tab('oaas-instData-tab');
		},
		
		loadData : function(param){//加载数据
			var url = WEB_ROOT + '/oaas/func/PrivateDataInstData/pagin.do';
			if(ruizhi.IsNull(param)){
				return;
			}
			dataInstId = param.dataInstId;
			var paramObj = {
				dataInstId : param.dataInstId
			}
			instDataGrid.loadData(url, paramObj);
		},
		
		/*添加数据权限*/
		create:function(){
			
			if(ruizhi.IsNull(dataInstId)){
				ruizhi.Alert('请选择数据实例');
				return;
			}
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/oaas/funcPage/privateDataData/selPrivateDataData.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.oaas.instData.createComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
//			paramObj.dataInstId = dataInstId;
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		createComeBack : function(param){
			var params = {};
			
			var paramObj = {
					dataInstId : dataInstId,
					dataDataId : param.dataDataId,
					sourceId : param.sourceId,
					sourceName : param.dataDataName
			}
			params.params = ruizhi.ToJson(paramObj);
			
			var url = WEB_ROOT + "/oaas/func/PrivateDataInstData/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,function(){
				
				ruizhi.oaas.instData.loadData(param);
			});
		},
		
		//删除实例数据
		del:function (){
			var selItemIds =  instDataGrid.getCheckedIds();
			var selItem =  instDataGrid.getCheckedItems();
			if(ruizhi.IsNull(selItemIds)){
				ruizhi.Alert('请选择数据权限实例数据');
				return;
			}
			
			
			ruizhi.Confirm("是否删除选择的数据权限实例数据？",function(result){
				if(result || 'true' == result) {
					var params = {};
					params.dataInstDataIds = selItemIds.join(",");
					var url = WEB_ROOT+"/oaas/func/PrivateDataInstData/del.do";
					ruizhi.InvokeMethodAsyn(url,params,function(){
						
						ruizhi.oaas.instData.loadData({dataInstId :dataInstId});
					});
				} else {
					
				}
			})
			
		},
		itemClick:function(){
//			var selItem = privateDataInstGrid.getSelectedItem();
//			
//			var paramObj = {
//				privateDataInstId : selItem.privateDataInstId	
//			}
//			var url1 =WEB_ROOT + "/oaas/func/privateDataInstJob/pagin";
//			var url2 =WEB_ROOT + ""
//			instDataGrid.loadData(url1, paramObj)
//			userGrid.loadData(url2, paramObj)
		},
		

	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.instData.init();
});

// ////////////////////////////////////////
// function定义
