ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.userPrivate = function() {

	var userPrivateGrid;
	return {
		init : function() {
			var gridParam ={};
			userPrivateGrid = new ruizhi.DataGrid("oaas-userPrivate-grid");
		},
		

		loadData : function(param) {// 加载数据
			var url = WEB_ROOT + '/oaas/func/private/userPirvatePagin.do';
			var paramObj = {
					userId : param.userId
			}
			userPrivateGrid.loadData(url, paramObj);
		},
		
	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.userPrivate.init();
});
