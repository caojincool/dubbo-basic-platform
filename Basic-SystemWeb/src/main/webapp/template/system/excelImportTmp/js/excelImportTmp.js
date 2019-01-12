//////////////////////////////////////////
//Excel上传模板
ruizhi.Package("ruizhi.system");

ruizhi.system.excelImportTmp = function() {

	var grid1;
	var form1;

	return {
		init : function() {

			grid1 = new ruizhi.DataGrid("system-excelImportTmp-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/system/func/excelImportTmp/pagin.do'
			});
			form1 = new ruizhi.FormExt("system-excelImportTmp-form1");
		},

		loadData : function() {// 加载数据
			var url = WEB_ROOT + '/system/func/excelImportTmp/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
		create : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/system/funcPage/excelImportTmp/excelImportTmpDetail.do';
			var width = null;
			var heigth = null;
			//paramObj.name=name;
			var excelImportTmp = {};
			excelImportTmp.pageDateType = 'CREATE';
			paramObj.excelImportTmp = excelImportTmp;
			var submitFn = ruizhi.system.excelImportTmp.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		createComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/system/func/excelImportTmp/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params, ruizhi.system.excelImportTmp.loadData);
			//ruizhi.system.excelImportTmp.loadData();
		},
		
		modify : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/system/funcPage/excelImportTmp/excelImportTmpDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.system.excelImportTmp.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";

			var selItem = grid1.getSelectedItem();//所选行
			if(selItem!=null){
				selItem.pageDateType = 'UPDATE';
				paramObj.excelImportTmp = selItem;
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
			var url = WEB_ROOT + "/system/func/excelImportTmp/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params, ruizhi.system.excelImportTmp.loadData);
			//ruizhi.system.excelImportTmp.loadData();
		},
		
		del : function(){
			var selItem = grid1.getSelectedItem();//所选行
			if(selItem!=null){
				if(selItem.templateCode != null && selItem.templateCode != '' && selItem.templateCode != 'undefined'){
					var msg = "确定要删除吗？";
					bootbox.confirm(msg, function(result) {
						if(result || 'true' == result) {
							var params = {};
							params.templateCode = selItem.templateCode;
							//params.modifyUserId = _SESSION.userId;//修改人
							//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
							var url = WEB_ROOT + "/system/func/excelImportTmp/del.do";
							ruizhi.InvokeMethodAsyn(url,params, ruizhi.system.excelImportTmp.loadData);
							//ruizhi.system.excelImportTmp.loadData();
						} else {
							
						}
					});
				}
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.system.excelImportTmp.init();
});