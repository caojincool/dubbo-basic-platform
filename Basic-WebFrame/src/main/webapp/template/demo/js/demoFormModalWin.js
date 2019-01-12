//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

//类定义
//////////////////////////////////////////
ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoModalWin = function() {

	// 私有成员定义;

	// 私有方法定义
	var sayHi = function() {
	}

	return {
		// 公有成员

		// 公有方法
		init : function() {
			var paramObj = ruizhi.GetModalWinParam();
			alert(ruizhi.ToJson(paramObj));
		},
		
		doSubmit:function(){
			var retObj = {
				age:10,
				birthday : new Date()
			}
			ruizhi.SubmitModalWin(retObj);
		}
	}

}();

// 初始化
// ////////////////////////////////////////

ruizhi.ExecWait(function() {
	ruizhi.demo.demoModalWin.init();
});
