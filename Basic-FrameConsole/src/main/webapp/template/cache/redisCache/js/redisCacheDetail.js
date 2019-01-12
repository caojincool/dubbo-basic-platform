//////////////////////////////////////////
//redisCache详情页面
ruizhi.Package("ruizhi.cache");

ruizhi.cache.redisCacheDetail = function() {
	
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/cache/funcPage/redisCache/redisCacheDetail.do';//当前窗口的URL
	
	return{
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var redisCache = paramObj.redisCache;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(redisCache));
			
			form1 = new ruizhi.FormExt("cache-redisCacheDetail-form1");
			
			if(!ruizhi.IsNull(redisCache) && !ruizhi.IsNull(redisCache.key)){
				var cacheCodeObject = form1.getObject("cacheCode");
				cacheCodeObject.attr("readonly", "readonly");
				var keyObject = form1.getObject("key");
				keyObject.attr("readonly", "readonly");
			}
			
			ruizhi.cache.redisCacheDetail.setCacheCodes();
			
			form1.objectToForm(redisCache);
		},	
		
		//提交到上一页面的回调方法
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}

			var valueObj = form1.formToObject();// 整个表单的值
			//不能为空，不然后台转为对象的时候类型不匹配
//			if(valueObj.fileInfoId == ''){
//				valueObj.fileInfoId = null;
//			}
			
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));

			//valueObj.createUserId = _SESSION.userId;//创建人
			
			var param = valueObj;
			//alert("整个提交数据:"+ruizhi.ToJson(param));
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		
		setCacheCodes:function(){
			
			var qryUrl = WEB_ROOT + '/cache/func/redisCache/qryCacheCodes.do';
			var qryParam = {};
			ruizhi.InvokeMethodAsyn(qryUrl, qryParam, ruizhi.cache.redisCacheDetail.cacheCodeMapComeBack);
		},
		
		cacheCodeMapComeBack:function(cacheCodeMap){
			var cacheCodes = [];
			if(!ruizhi.IsNull(cacheCodeMap)){
				for(var key in cacheCodeMap){
					var cacheCode = {};
					cacheCode.key = key;
					cacheCode.value = cacheCodeMap[key];
					cacheCodes.push(cacheCode);
//					console.info(key);
//					console.info(cacheCodeMap[key]);
				}
			}
			form1.selOptionAddAll('cacheCode', cacheCodes, 'value', 'key',true);
			
		},

	}
}();

//初始化
ruizhi.ExecWait(function(){
	ruizhi.cache.redisCacheDetail.init();
});