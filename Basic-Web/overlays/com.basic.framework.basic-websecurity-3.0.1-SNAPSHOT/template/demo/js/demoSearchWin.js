//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

//类定义
//////////////////////////////////////////
ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoSearchWin= function() {
	
	var URL = WEB_ROOT + '/demo/win/demoSearchWin.do';//当前窗口的URL
	

	return {
		// 公有成员

		// 公有方法
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			alert("模态窗口1内获取初始化参数："+ruizhi.ToJson(paramObj));

		},

		
		doSubmit : function() {
			var obj = {
					staffId:1,
					staffName:'world'
			}
			ruizhi.SubmitModalWin(URL,obj);//通过公用方法调用父页面的回调方法
			
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		}
	}

}();

// 初始化
// ////////////////////////////////////////

ruizhi.ExecWait(function() {
	ruizhi.demo.demoSearchWin.init();
});
