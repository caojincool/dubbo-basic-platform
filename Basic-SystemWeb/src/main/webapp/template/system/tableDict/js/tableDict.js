//////////////////////////////////////////
//字典表
ruizhi.Package("ruizhi.system");

ruizhi.system.tableDict = function() {

	var grid1;
	var form1;

	return {
		//初始化
		init : function() {

			grid1 = new ruizhi.DataGrid("system-tableDict-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/system/func/tableDict/pagin.do'
			});
			form1 = new ruizhi.FormExt("system-tableDict-form1");
		},
		
		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/system/func/tableDict/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
		//新增窗口
		create : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/system/funcPage/tableDict/tableDictDetail.do';
			var width = null;
			var heigth = null;
			var tableDict = {};
//			tableDict.pageDateType = 'CREATE';
			paramObj.tableDict = tableDict;
			var submitFn = ruizhi.system.tableDict.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		//新增窗口回调函数
		createComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/system/func/tableDict/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params, ruizhi.system.tableDict.loadData);
			//ruizhi.system.tableDict.loadData();
		},
		
		//编辑窗口
		modify : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/system/funcPage/tableDict/tableDictDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.system.tableDict.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";

			var selItem = grid1.getSelectedItem();//所选行
			if(!ruizhi.IsNull(selItem)){
//				selItem.pageDateType = 'UPDATE';
				paramObj.tableDict = selItem;
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
			
		},
		
		//编辑窗口回调函数
		modifyComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/system/func/tableDict/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params, ruizhi.system.tableDict.loadData);
			//ruizhi.system.tableDict.loadData();
		},
		
		//删除，可批量删除
		del : function(){
			var dictIds = grid1.getCheckedIds();//所选行的id数组
			if(!ruizhi.IsNull(dictIds) && dictIds.length > 0){
				dictIds = dictIds.join(",");
				var msg = "确定要删除吗？";
				bootbox.confirm(msg, function(result) {
					if(result || 'true' == result) {
						var params = {};
						params.dictIds = dictIds;
						//params.modifyUserId = _SESSION.userId;//修改人
						//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
						var url = WEB_ROOT + "/system/func/tableDict/del.do";
						ruizhi.InvokeMethodAsyn(url,params, ruizhi.system.tableDict.loadData);
						//ruizhi.system.tableDict.loadData();
					} else {
						
					}
				});
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.system.tableDict.init();
});