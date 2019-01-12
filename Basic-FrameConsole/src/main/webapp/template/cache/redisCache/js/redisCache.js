//////////////////////////////////////////
//redisCache
ruizhi.Package("ruizhi.cache");

ruizhi.cache.redisCache = function() {

	var grid1;
	var form1;

	return {
		init : function() {

			grid1 = new ruizhi.DataGrid("cache-redisCache-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/cache/func/redisCache/pagin.do'
			});
			form1 = new ruizhi.FormExt("cache-redisCache-form1");
		},

		loadData : function() {// 加载数据
			var url = WEB_ROOT + '/cache/func/redisCache/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
		create : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/cache/funcPage/redisCache/redisCacheDetail.do';
			var width = null;
			var heigth = null;
			//paramObj.name=name;
			var redisCache = {};
			redisCache.pageDateType = 'CREATE';
			paramObj.redisCache = redisCache;
			var submitFn = ruizhi.cache.redisCache.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		createComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/cache/func/redisCache/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.cache.redisCache.loadData);
			//ruizhi.cache.redisCache.loadData();
		},
		
		modify : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/cache/funcPage/redisCache/redisCacheDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.cache.redisCache.modifyComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";

			var selItem = grid1.getSelectedItem();//所选行
			if(selItem!=null){
				selItem.pageDateType = 'UPDATE';
				paramObj.redisCache = selItem;
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
			var url = WEB_ROOT + "/cache/func/redisCache/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.cache.redisCache.loadData);
			//ruizhi.cache.redisCache.loadData();
		},
		
		del : function(){
			var selItem = grid1.getSelectedItem();//所选行
			if(selItem!=null){
				if(!ruizhi.IsNull(selItem.cacheCode) && !ruizhi.IsNull(selItem.key)){
					var msg = "确定要删除吗？";
					bootbox.confirm(msg, function(result) {
						if(result || 'true' == result) {
							var params = {};
							params.cacheCode = selItem.cacheCode;
							params.key = selItem.key;
							//params.modifyUserId = _SESSION.userId;//修改人
							//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
							var url = WEB_ROOT + "/cache/func/redisCache/del.do";
							ruizhi.InvokeMethodAsyn(url,params,ruizhi.cache.redisCache.loadData);
							//ruizhi.cache.redisCache.loadData();
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
	ruizhi.cache.redisCache.init();
});