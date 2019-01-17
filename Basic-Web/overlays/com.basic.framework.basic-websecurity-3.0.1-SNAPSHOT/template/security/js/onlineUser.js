//////////////////////////////////////////
//类定义
ruizhi.Package("ruizhi.security");

ruizhi.security.onlineUser = function() {

	var grid1;
	var form;

	return {
		init : function() {
//			var gridParam ={};
//			gridParam.url = WEB_ROOT + '/security/func/qryOnlineUser.do';
//			gridParam.postData = {
//					id : 1,
//					userName : "world"
//			};
//			grid1 = new ruizhi.DataGrid("security-onlineUser-grid1",gridParam);
			var url = WEB_ROOT + '/security/func/qryOnlineUser.do';
			grid1 = new ruizhi.DataGrid("security-onlineUser-grid1",{
				postData : {
//					basicDataId : basicDataId
				},
				url : url
			});
			
			form = new ruizhi.FormExt("security-onlineUser-form1");
		},

		loadData : function() {// 加载数据
			var url = WEB_ROOT + '/security/func/qryOnlineUser.do';
			var paramObj = {
			};
			grid1.loadData(url, paramObj);
		},
		
		//踢出用户
		logoutUser : function(){
			var selItem = grid1.getSelectedItem();//所选行
			if(selItem != null){
				if(selItem.userAccount != null && selItem.userAccount != '' && selItem.userAccount != 'undefined'){
//					var params = {};
//					params.userAccount = selItem.userAccount;
					//params.modifyUserId = _SESSION.userId;//修改人
					//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
					var url = WEB_ROOT + "/security/func/logoutUser.do";
					ruizhi.InvokeMethodAsyn(url,selItem, ruizhi.security.onlineUser.loadData);
					//ruizhi.security.onlineUser.loadData();
				}
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
	}

}();

ruizhi.ExecWait(function() {
	ruizhi.security.onlineUser.init();
});

// ////////////////////////////////////////
// function定义
