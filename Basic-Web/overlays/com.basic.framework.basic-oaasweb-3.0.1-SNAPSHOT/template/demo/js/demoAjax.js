//类定义
ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoAjax = function(){
	
	var grid = null;
	
	return {
		init:function(){
			
		},
		
		loadDataFalse : function(){
			var params = {};
			var qryParam = {};
			qryParam.userName = "测试";
			params.params = ruizhi.ToJson(qryParam);
			var qryUrl = WEB_ROOT + '/demo/func/demoAjax/qry.do';
			var item = ruizhi.InvokeMethod(qryUrl, params);
			
			ruizhi.demo.demoAjax.func1();
			ruizhi.demo.demoAjax.func2();
		},
		
		loadDataTrue : function(){
			var params = {};
			var qryParam = {};
			qryParam.userName = "测试";
			params.params = ruizhi.ToJson(qryParam);
			var qryUrl = WEB_ROOT + '/demo/func/demoAjax/qry.do';
			var item = ruizhi.InvokeMethodAsyn(qryUrl, params);
			
			ruizhi.demo.demoAjax.func1();
			ruizhi.demo.demoAjax.func2();
		},
		
		loadDataTrue1 : function(){
			var params = {};
			var qryParam = {};
			qryParam.userName = "测试";
			params.params = ruizhi.ToJson(qryParam);
			var qryUrl = WEB_ROOT + '/demo/func/demoAjax/qry.do';
			var item = ruizhi.InvokeMethodAsyn(qryUrl, params, ruizhi.demo.demoAjax.func3);
			
			ruizhi.demo.demoAjax.func1();
			ruizhi.demo.demoAjax.func2();
		},
		
		func1 : function(){
			alert("我是第一个方法func1");
		},
		
		func2 : function(){
			alert("我是第二个方法func2");
		},
		
		func3 : function(item){
			alert("我是异步成功回调方法");
			alert(ruizhi.ToJson(item));
		},

	}
	
	
}();

//初始化
ruizhi.ExecWait(function(){
	ruizhi.demo.demoAjax.init();
});