ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.userData = function() {

	var userDataInst;
	var userDataInstData;
	return {
		init : function() {
			var gridParam ={};
			userDataInst = new ruizhi.DataGrid("oaas-userDataInst-grid");
			userDataInstData = new ruizhi.DataGrid("oaas-userDataInstData-grid");
		},
		

		loadData : function(param) {// 加载数据
			var url = WEB_ROOT + '/oaas//func/privateData/userDataPagin.do';
			var paramObj = {
					userId : param.userId
			}
			userDataInst.loadData(url, paramObj);
		},
		itemClick : function(instId){
			if(!ruizhi.IsNull(instId)){
				var param ={
						dataInstId :instId
				}
				var url = WEB_ROOT + '/oaas/func/PrivateDataInstData/pagin.do';
				userDataInstData.loadData(url,param);
			}
		}
	}

}();

ruizhi.ExecWait(function() {
	ruizhi.oaas.userData.init();
});
