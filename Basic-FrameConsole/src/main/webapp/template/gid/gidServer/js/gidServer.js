//////////////////////////////////////////
//gid服务
ruizhi.Package("ruizhi.gid");

ruizhi.gid.gidServer = function() {

	var grid1;
	var form1;

	return {
		init : function() {

			grid1 = new ruizhi.DataGrid("gid-gidServer-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/gid/func/gidServer/pagin.do'
			});
			form1 = new ruizhi.FormExt("gid-gidServer-form1");
		},

		loadData : function() {// 加载数据
			var url = WEB_ROOT + '/gid/func/gidServer/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
		create : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/gid/funcPage/gidServer/gidServerDetail.do';
			var width = null;
			var heigth = null;
			//paramObj.name=name;
			var gidServer = {};
			gidServer.pageDateType = 'CREATE';
			paramObj.gidServer = gidServer;
			var submitFn = ruizhi.gid.gidServer.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		createComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/gid/func/gidServer/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.gid.gidServer.loadData);
			//ruizhi.gid.gidServer.loadData();
		},
		
		modify : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/gid/funcPage/gidServer/gidServerDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.gid.gidServer.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";

			var selItem = grid1.getSelectedItem();//所选行
			if(selItem!=null){
				selItem.pageDateType = 'UPDATE';
				paramObj.gidServer = selItem;
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
			
		},
		
		modifyComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/gid/func/gidServer/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.gid.gidServer.loadData);
			//ruizhi.gid.gidServer.loadData();
		},
		
		getCurrValue : function() {
			var selItem = grid1.getSelectedItem();//所选行
			if(!ruizhi.IsNull(selItem)){
				var gidValue = ruizhi.GetGidValue(selItem.gidCode);
				ruizhi.Alert('当前序列为：' + gidValue);
			}else{
				ruizhi.Alert('请选择');
			}
			
		},
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.gid.gidServer.init();
});