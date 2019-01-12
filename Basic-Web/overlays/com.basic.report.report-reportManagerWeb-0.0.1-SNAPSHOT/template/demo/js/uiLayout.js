//////////////////////////////////////////
//布局使用
ruizhi.Package("ruizhi.demo");

ruizhi.demo.uiLayout = function() {

	var grid1;
	var form1;
	var _UserDetail = ruizhi.GetUser();
	
	return {
		//初始化
		init : function() {

	        $("#demo-uiLayout-layout").layout();

		},
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.demo.uiLayout.init();
});