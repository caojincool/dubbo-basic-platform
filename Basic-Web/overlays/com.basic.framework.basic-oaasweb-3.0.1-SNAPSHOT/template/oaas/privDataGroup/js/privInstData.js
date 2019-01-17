//////////////////////////////////////////
//数据权限数据
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.privInstData = function() {

	var privInstDataGrid = null;
	var dataInstId = null;
	var tab = null;
	return {
		init : function() {
			privInstDataGrid =  new ruizhi.DataGrid("oaas-privInstData-grid");
			
			tab = new ruizhi.Tab('oaas-privInstData-tab');
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
			privInstDataGrid.loadData(url, paramObj);
		},
		
	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.privInstData.init();
});

// ////////////////////////////////////////
// function定义
