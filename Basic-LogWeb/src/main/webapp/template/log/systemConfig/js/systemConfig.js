//////////////////////////////////////////
//字典表
ruizhi.Package("ruizhi.log");

ruizhi.log.systemConfig = function() {

	var grid1;
	var form1;

	return {
		//初始化
		init : function() {

			grid1 = new ruizhi.DataGrid("log-systemConfig-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/log/func/systemConfig/pagin.do'
			});
			form1 = new ruizhi.FormExt("log-systemConfig-form1");
		},
		
		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/log/func/systemConfig/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
		//新增窗口
		create : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/log/funcPage/systemConfig/systemConfigDetail.do';
			var width = null;
			var heigth = null;
			var systemConfig = {};
			paramObj.systemConfig = systemConfig;
			var submitFn = ruizhi.log.systemConfig.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		//新增窗口回调函数
		createComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			var url = WEB_ROOT + "/log/func/systemConfig/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params, ruizhi.log.systemConfig.loadData);
			//ruizhi.log.systemConfig.loadData();
		},
		
		//编辑窗口
		modify : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/log/funcPage/systemConfig/systemConfigDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.log.systemConfig.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";

			var selItem = grid1.getSelectedItem();//所选行
			if(!ruizhi.IsNull(selItem)){
//				selItem.pageDateType = 'UPDATE';
				paramObj.systemConfig = selItem;
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
			var url = WEB_ROOT + "/log/func/systemConfig/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params, ruizhi.log.systemConfig.loadData);
			//ruizhi.log.systemConfig.loadData();
		},
		
		//删除，可批量删除
		del : function(){
			var configIds = grid1.getCheckedIds();//所选行的id数组
			if(!ruizhi.IsNull(configIds) && configIds.length > 0){
				configIds = configIds.join(",");
				var msg = "确定要删除吗？";
				bootbox.confirm(msg, function(result) {
					if(result || 'true' == result) {
						var params = {};
						params.configIds = configIds;
						//params.modifyUserId = _SESSION.userId;//修改人
						//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
						var url = WEB_ROOT + "/log/func/systemConfig/del.do";
						ruizhi.InvokeMethodAsyn(url,params, ruizhi.log.systemConfig.loadData);
						//ruizhi.log.systemConfig.loadData();
					} else {
						
					}
				});
			}else{
				ruizhi.Alert('请选择');
			}
		},
		flagTrans : function(cellvalue, options, rowObject){
			if(cellvalue == 1){
				return "是";
			}else if(cellvalue == 0){
				return "否";
			}
		}
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.log.systemConfig.init();
});