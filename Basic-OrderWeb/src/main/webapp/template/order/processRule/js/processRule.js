//////////////////////////////////////////
//服务

//类定义
ruizhi.Package("ruizhi.order");

ruizhi.order.processRule = function() {

	var grid;
	var form;

	return {
		init : function() {
			var gridParam ={};
			gridParam.url = WEB_ROOT + '/order/func/processRule/pagin.do';
			gridParam.postData = {
					
			};
			grid = new ruizhi.DataGrid("order-processRule-grid1",gridParam);
			form = new ruizhi.FormExt("order-processRule-form1");
		},
		loadData : function() {// 加载数据
			var url = WEB_ROOT + '/order/func/processRule/pagin.do';
			var paramObj = form.formToObject();
			grid.loadData(url, paramObj);
		},
		
		
		//添加窗口
		add : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/processRule/processRuleDetail.do';
			var width = null;
			var heigth = null;
			var processRule = {};
			processRule.pageDateType = 'CREATE';
			paramObj.processRule = processRule;
			var submitFn = ruizhi.order.processRule.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		//新增窗口回调函数
		createComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/order/func/processRule/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.processRule.loadData);
			//ruizhi.order.processRule.loadData();
		},
		//编辑窗口
		modify : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/processRule/processRuleDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.order.processRule.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";

			var ids = grid.getCheckedIds();
			if(!ruizhi.IsNull(ids)){
				if(ids.length == 0){
					ruizhi.Alert('请选择');
					return false;
				}else if(ids.length > 1){
					ruizhi.Alert('不能选择多条数据');
					return false;
				}
			}
			var selItem = grid.getSelectedItem();//所选行
			if(!ruizhi.IsNull(selItem)){
				selItem.pageDateType = 'UPDATE';
				paramObj.processRule = selItem;
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
			var url = WEB_ROOT + "/order/func/processRule/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.processRule.loadData);
			//ruizhi.order.processRule.loadData();
		},
		
		//删除，可批量删除
		del : function(){
			var ids = grid.getCheckedIds();//所选行的id数组
			if(!ruizhi.IsNull(ids) && ids.length > 0){
				ids = ids.join(",");
				var msg = "确定要删除吗？";
				bootbox.confirm(msg, function(result) {
					if(result || 'true' == result) {
						var params = {};
						params.RuleIds = ids;
						params.modifyUserId = _UserDetail.userId;//修改人
						var url = WEB_ROOT + "/order/func/processRule/del.do";
						ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.processRule.loadData);
						//ruizhi.order.processRule.loadData();
					} else {
						
					}
				});
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(THE_URL);
		},
		
		
	}

}();
//初始化
ruizhi.ExecWait(function() {
	ruizhi.order.processRule.init();
});

// ////////////////////////////////////////
// function定义
