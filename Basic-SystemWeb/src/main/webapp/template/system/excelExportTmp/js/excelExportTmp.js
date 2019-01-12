//////////////////////////////////////////
//Excel导出模板
ruizhi.Package("ruizhi.system");

ruizhi.system.excelExportTmp = function() {

	var grid1;
	var form1;

	return {
		init : function() {

			grid1 = new ruizhi.DataGrid("system-excelExportTmp-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/system/func/excelExportTmp/pagin.do'
			});
			form1 = new ruizhi.FormExt("system-excelExportTmp-form1");
		},

		loadData : function() {// 加载数据
			var url = WEB_ROOT + '/system/func/excelExportTmp/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
		create : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/system/funcPage/excelExportTmp/excelExportTmpDetail.do';
			var width = null;
			var heigth = null;
			//paramObj.name=name;
			var excelExportTmp = {};
			excelExportTmp.pageDateType = 'CREATE';
			paramObj.excelExportTmp = excelExportTmp;
			var submitFn = ruizhi.system.excelExportTmp.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		createComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/system/func/excelExportTmp/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params, ruizhi.system.excelExportTmp.loadData);
			//ruizhi.system.excelExportTmp.loadData();
		},
		
		modify : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/system/funcPage/excelExportTmp/excelExportTmpDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.system.excelExportTmp.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";

			var selItem = grid1.getSelectedItem();//所选行
			if(selItem!=null){
				selItem.pageDateType = 'UPDATE';
				paramObj.excelExportTmp = selItem;
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
			var url = WEB_ROOT + "/system/func/excelExportTmp/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params, ruizhi.system.excelExportTmp.loadData);
			//ruizhi.system.excelExportTmp.loadData();
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
							var url = WEB_ROOT + "/system/func/excelExportTmp/del.do";
							ruizhi.InvokeMethodAsyn(url,params, ruizhi.system.excelExportTmp.loadData);
							//ruizhi.system.excelExportTmp.loadData();
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
	ruizhi.system.excelExportTmp.init();
});