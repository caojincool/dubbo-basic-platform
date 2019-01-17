//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

//类定义
//////////////////////////////////////////
ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoTab = function() {

	// 私有成员定义;
	var _this = this;
	var tab;
	// 私有方法定义

	return {

		init : function() {

			tab = new ruizhi.Tab('myTab');
			tab1 = new ruizhi.Tab('myTab1');
		},
		
		getActiveTab : function(){
			var activeTab = tab.getActiveTab();
			alert(activeTab.text());
			var activeTab1 = tab1.getActiveTab();
			alert(activeTab1.text());
		},
		
		getPreviousTab : function(){
			var previousTab = tab.getPreviousTab();
			alert(previousTab.text());
			var previousTab1 = tab1.getPreviousTab();
			alert(previousTab1.text());
		},
		
		//设置第几个标签页显示（从 0 开始索引）
		setTabDisplay : function(){
			tab.setTabDisplay("4");
			tab1.setTabDisplay("1");
		},
		
		home : function(){
			alert("你点击w3c之后执行的方法");
		},
		
		setHide : function(href){
			tab1.setHide(href);
		},
		
		setShow : function(href){
			tab1.setShow(href);
		},
	}

}();


//初始化
//////////////////////////////////////////

ruizhi.ExecWait(function() {
	ruizhi.demo.demoTab.init();
});