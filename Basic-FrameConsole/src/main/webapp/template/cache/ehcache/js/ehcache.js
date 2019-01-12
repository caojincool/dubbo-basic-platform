//////////////////////////////////////////
//ehcache
ruizhi.Package("ruizhi.cache");

ruizhi.cache.ehcache = function() {

	var grid1;
	var form1;

	return {
		//初始化
		init : function() {
			grid1 = new ruizhi.DataGrid("cache-ehcache-grid1"/*,{
				postData : {
					
				},
				url : WEB_ROOT + '/cache/func/ehcache/pagin.do'
			}*/);
			form1 = new ruizhi.FormExt("cache-ehcache-form1");
		},

		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/cache/func/ehcache/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
		//清除一个缓存
		clearOne : function(){
			var selItem = grid1.getSelectedItem();//所选行
			if(!ruizhi.IsNull(selItem)){
				var url = WEB_ROOT + '/cache/func/ehcache/clearCache.do';
				var paramObj = selItem;
				ruizhi.InvokeMethodAsyn(url,paramObj,ruizhi.cache.ehcache.loadData);
				//ruizhi.cache.ehcache.loadData();
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
		//清空缓存
		clearAll : function(){
			var params = {};
			var url = WEB_ROOT + "/cache/func/ehcache/clearAll.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.cache.ehcache.loadData);
			//ruizhi.cache.ehcache.loadData();
		},
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.cache.ehcache.init();
});