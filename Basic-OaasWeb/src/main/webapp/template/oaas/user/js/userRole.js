ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.userRole = function() {

	var userRoleGrid;
	var rolePrivateGrid;
	return {
		init : function() {
			var gridParam ={};
			userRoleGrid = new ruizhi.DataGrid("oaas-userRole-grid");
			rolePrivateGrid = new ruizhi.DataGrid("oaas-rolePrivate-grid");
		},
		

		loadData : function(param) {// 加载数据
			
			var url = WEB_ROOT + '/oaas/func/role/pagin.do';
			var paramObj = {
					userId : param.userId
			}
			userRoleGrid.loadData(url, paramObj);
		},
		itemClick :function(roleId){
			if(!ruizhi.IsNull(roleId)){
				var param ={
						roleId :roleId
				}
				var url = WEB_ROOT + '/oaas/func/private/pagin.do';
				rolePrivateGrid.loadData(url,param);
			}
		}
		
	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.userRole.init();
});
