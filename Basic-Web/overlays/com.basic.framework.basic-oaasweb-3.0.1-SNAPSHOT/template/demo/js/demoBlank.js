//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

//类定义
//////////////////////////////////////////

ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoBlank = function() {

	// 私有成员定义;
	var myName = null;
	var _this = this;

	// 私有方法定义
	var sayHi = function() {
		// 引用公有成员
		_this.age = 20;
		alert(_this.age);

		alert(myName);
	}

	return {
		// 公有成员
		name : null,
		age : null,

		// 公有方法
		init : function() {
		}

	}

}();

// 初始化
// ////////////////////////////////////////

ruizhi.ExecWait(function() {
	ruizhi.demo.demoBlank.init();
});
