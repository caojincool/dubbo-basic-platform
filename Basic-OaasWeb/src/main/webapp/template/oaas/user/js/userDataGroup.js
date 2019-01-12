ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.userDataGroup = function() {

	var userDataGroupGrid;
	var groupDataInstGrid;
	var groupInstDataGrid;
	return {
		init : function() {
			var gridParam ={};
			userDataGroupGrid = new ruizhi.DataGrid("oaas-group-grid");
			groupDataInstGrid = new ruizhi.DataGrid("oaas-groupPrivateInst-grid");
			groupInstDataGrid = new ruizhi.DataGrid("oaas-groupInstData-grid");
		},
		

		loadData : function(param) {// 加载数据
			var url = WEB_ROOT + '/oaas/func/privDataGroup/pagin.do';
			var paramObj = {
					userId : param.userId
			}
			userDataGroupGrid.loadData(url, paramObj);
		},
		itemClick :function(groupId){
			if(!ruizhi.IsNull(groupId)){
				var param ={
						dataGroupId :groupId
				}
				var url = WEB_ROOT + '/oaas/func/privateData/pagin.do';
				groupDataInstGrid.loadData(url,param);
			}
		},
		instItemClick :  function(instId){
			if(!ruizhi.IsNull(instId)){
				var param ={
						dataInstId :instId
				}
				var url = WEB_ROOT + '/oaas/func/PrivateDataInstData/pagin.do';
				groupInstDataGrid.loadData(url,param);
			}
		}
		
		
	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.userDataGroup.init();
});
