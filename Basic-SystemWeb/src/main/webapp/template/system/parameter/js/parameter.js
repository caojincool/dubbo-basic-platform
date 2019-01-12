//////////////////////////////////////////
//字典表
ruizhi.Package("ruizhi.system");

ruizhi.system.parameter = function() {

	var grid1;
	var form1;

	return {
		//初始化
		init : function() {

			grid1 = new ruizhi.DataGrid("system-parameter-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/system/func/parameter/pagin.do'
			});
			form1 = new ruizhi.FormExt("system-parameter-form1");
		},
		
		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/system/func/parameter/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
		//新增窗口
		create : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/system/funcPage/parameter/parameterDetail.do';
			var width = null;
			var heigth = null;
			var parameter = {};
			parameter.pageDateType = 'CREATE';
			paramObj.parameter = parameter;
			var submitFn = ruizhi.system.parameter.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		//新增窗口回调函数
		createComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/system/func/parameter/create.do";
			ruizhi.InvokeMethodAsyn(url,params, ruizhi.system.parameter.loadData);
			//ruizhi.system.parameter.loadData();
		},
		
		//编辑窗口
		modify : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/system/funcPage/parameter/parameterDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.system.parameter.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";

			var selItem = grid1.getSelectedItem();//所选行
			if(!ruizhi.IsNull(selItem)){
				selItem.pageDateType = 'UPDATE';
				paramObj.parameter = selItem;
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
			var url = WEB_ROOT + "/system/func/parameter/modify.do";
			ruizhi.InvokeMethodAsyn(url,params, ruizhi.system.parameter.loadData);
			//ruizhi.system.parameter.loadData();
		},
		
		//删除，可批量删除
		del : function(){
			var paramCodes = grid1.getCheckedIds();//所选行的id数组
			if(!ruizhi.IsNull(paramCodes) && paramCodes.length > 0){
				paramCodes = paramCodes.join(",");
				var msg = "确定要删除吗？";
				bootbox.confirm(msg, function(result) {
					if(result || 'true' == result) {
						var params = {};
						params.paramCodes = paramCodes;
						//params.modifyUserId = _SESSION.userId;//修改人
						//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
						var url = WEB_ROOT + "/system/func/parameter/del.do";
						ruizhi.InvokeMethodAsyn(url,params, ruizhi.system.parameter.loadData);
						//ruizhi.system.parameter.loadData();
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
	ruizhi.system.parameter.init();
});