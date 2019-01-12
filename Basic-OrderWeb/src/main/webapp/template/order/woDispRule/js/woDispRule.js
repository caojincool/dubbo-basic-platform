//////////////////////////////////////////
//任务派发规则
ruizhi.Package("ruizhi.order");

ruizhi.order.woDispRule = function() {

	var grid1;
	var form1;
	var _UserDetail = ruizhi.GetUser();
	
	return {
		//初始化
		init : function() {

			grid1 = new ruizhi.DataGrid("order-woDispRule-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/order/func/woDispRule/pagin.do'
			});
			form1 = new ruizhi.FormExt("order-woDispRule-form1");
		},
		
		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/order/func/woDispRule/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
		//新增窗口
		create : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/woDispRule/woDispRuleDetail.do';
			var width = null;
			var heigth = null;
			var woDispRule = {};
			woDispRule.pageDateType = 'CREATE';
			paramObj.woDispRule = woDispRule;
			var submitFn = ruizhi.order.woDispRule.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		//新增窗口回调函数
		createComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/order/func/woDispRule/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params, ruizhi.order.woDispRule.loadData);
			//ruizhi.order.woDispRule.loadData();
		},
		
		//编辑窗口
		modify : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/woDispRule/woDispRuleDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.order.woDispRule.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";

			var ids = grid1.getCheckedIds();
			if(!ruizhi.IsNull(ids)){
				if(ids.length == 0){
					ruizhi.Alert('请选择');
					return false;
				}else if(ids.length > 1){
					ruizhi.Alert('不能选择多条数据');
					return false;
				}
			}
			var selItem = grid1.getSelectedItem();//所选行
			if(!ruizhi.IsNull(selItem)){
				selItem.pageDateType = 'UPDATE';
				paramObj.woDispRule = selItem;
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
			var url = WEB_ROOT + "/order/func/woDispRule/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.woDispRule.loadData);
			//ruizhi.order.woDispRule.loadData();
		},
		
		//删除，可批量删除
		del : function(){
			var ids = grid1.getCheckedIds();//所选行的id数组
			if(!ruizhi.IsNull(ids) && ids.length > 0){
				ids = ids.join(",");
				var msg = "确定要删除吗？";
				bootbox.confirm(msg, function(result) {
					if(result || 'true' == result) {
						var params = {};
						params.ruleIds = ids;
						params.modifyUserId = _UserDetail.userId;//修改人
						//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
						var url = WEB_ROOT + "/order/func/woDispRule/del.do";
						ruizhi.InvokeMethodAsyn(url,params, ruizhi.order.woDispRule.loadData);
						//ruizhi.order.woDispRule.loadData();
					} else {
						
					}
				});
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
		
		//单选环节
		selTache : function(){
			var url = WEB_ROOT + '/order/funcPage/tache/selTache.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.submitParam = null;//提交到后台的参数
			paramObj.title = "单选环节";
			var submitFn = ruizhi.order.woDispRule.selTacheComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selTacheComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param)){
				form1.setValue('tacheId', param.tacheId);
				form1.setValue('tacheName', param.tacheName);
			}
		},
		
		//单击，查询该规则下的规则实例
		woDispRuleItemClick : function(rowId) {
			var item = grid1.getItemByRowId(rowId);
		
			//加载规则实例
			if(!ruizhi.IsNull(item) && !ruizhi.IsNull(item.ruleId)){
				ruizhi.order.woDispRuleInst.loadData(item.ruleId);
			}
		},
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.order.woDispRule.init();
});