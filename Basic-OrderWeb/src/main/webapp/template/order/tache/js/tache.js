//////////////////////////////////////////
//环节
ruizhi.Package("ruizhi.order");

ruizhi.order.tache = function() {

	var zTree1;
	var grid1;
	var form1;
	var _UserDetail = ruizhi.GetUser();
	
	return {
		//初始化
		init : function() {

			zTree1 = new ruizhi.ZTree("order-tache-zTree1", {
				otherParam : null,
				url : WEB_ROOT + '/order/func/tacheCatalog/qryTreeStep.do'
			});
			
			grid1 = new ruizhi.DataGrid("order-tache-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/order/func/tache/pagin.do'
			});
			form1 = new ruizhi.FormExt("order-tache-form1");
		},
		
		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/order/func/tache/pagin.do';
			var paramObj = form1.formToObject();
			
			var selItem = zTree1.getSelectedNodes()[0];//所选行
			if(!ruizhi.IsNull(selItem) && !ruizhi.IsNull(selItem.catalogId)){
				paramObj.catalogId = selItem.catalogId
			}
			
			grid1.loadData(url, paramObj);
		},
		
		//新增窗口
		create : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/tache/tacheDetail.do';
			var width = null;
			var heigth = null;
			var tache = {};
			tache.pageDateType = 'CREATE';
			paramObj.tache = tache;
			var submitFn = ruizhi.order.tache.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		//新增窗口回调函数
		createComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/order/func/tache/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.tache.loadData);
			//ruizhi.order.tache.loadData();
		},
		
		//编辑窗口
		modify : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/tache/tacheDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.order.tache.modifyComeBack;//模态窗口提交后回调方法
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
				paramObj.tache = selItem;
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
			var url = WEB_ROOT + "/order/func/tache/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.tache.loadData);
			//ruizhi.order.tache.loadData();
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
						params.tacheIds = ids;
						params.modifyUserId = _UserDetail.userId;//修改人
						//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
						var url = WEB_ROOT + "/order/func/tache/del.do";
						ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.tache.loadData);
						//ruizhi.order.tache.loadData();
					} else {
						
					}
				});
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
		
		//--------------环节目录--------------
		//加载数据
		loadDataTacheCatalog : function() {
			var url = WEB_ROOT + '/order/func/tacheCatalog/qryTreeStep.do';
			var paramObj = null;
			zTree1.loadData(url,paramObj);
		},

		//树点击事件
		treeClick : function(treeNode){
			ruizhi.order.tache.loadData();
		},
		
		//新增窗口
		createTacheCatalog : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/tacheCatalog/tacheCatalogDetail.do';
			var width = null;
			var heigth = null;
			var tacheCatalog = {};
			tacheCatalog.pageDateType = 'CREATE';
			paramObj.tacheCatalog = tacheCatalog;
			var submitFn = ruizhi.order.tache.createTacheCatalogComeBack;//模态窗口提交后回调方法
			
			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		//新增窗口回调函数
		createTacheCatalogComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/order/func/tacheCatalog/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.tache.loadDataTacheCatalog);
			//ruizhi.order.tache.loadDataTacheCatalog();
//			zTree1.reAsyncChildNodes(parentNode, 'refresh', false);
		},
		
		//编辑窗口
		modifyTacheCatalog : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/tacheCatalog/tacheCatalogDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.order.tache.modifyTacheCatalogComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			var selItem = zTree1.getSelectedNodes()[0];//所选行
			if(!ruizhi.IsNull(selItem)){
				selItem.pageDateType = 'UPDATE';
				paramObj.tacheCatalog = selItem;
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择目录');
			}
			
		},
		
		//编辑窗口回调函数
		modifyTacheCatalogComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/order/func/tacheCatalog/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.tache.loadDataTacheCatalog);
			//ruizhi.order.tache.loadDataTacheCatalog();
		},
		
		//删除
		delTacheCatalog : function(){
			var selItem = zTree1.getSelectedNodes()[0];//所选行
			if(!ruizhi.IsNull(selItem) && !ruizhi.IsNull(selItem.catalogId)){
				var msg = "确定要删除吗？";
				bootbox.confirm(msg, function(result) {
					if(result || 'true' == result) {
						var params = {};
						params.catalogId = selItem.catalogId;
						params.modifyUserId = _UserDetail.userId;//修改人
						//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
						var url = WEB_ROOT + "/order/func/tacheCatalog/del.do";
						ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.tache.loadDataTacheCatalog);
						//ruizhi.order.tache.loadDataTacheCatalog();
					} else {
						
					}
				});
			}else{
				ruizhi.Alert('请选择目录');
			}
		},
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.order.tache.init();
});