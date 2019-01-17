//////////////////////////////////////////
//左上下布局
ruizhi.Package("ruizhi.demo");

ruizhi.demo.leftUpDownLayout = function() {

	var grid1;
	var form1;
	var zTree1;
	var _UserDetail = ruizhi.GetUser();
	
	return {
		//初始化
		init : function() {

	        $("#demo-leftUpDownLayout-layout").layout();

	        
			grid1 = new ruizhi.DataGrid("demo-leftUpDownLayout-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/demo/func/demoGrid/qryAll.do'
			});
			form1 = new ruizhi.FormExt("demo-leftUpDownLayout-form1");
			zTree1 = new ruizhi.ZTree("demo-leftUpDownLayout-zTree1", {
				otherParam : null,
				url : WEB_ROOT + '/demo/func/demoZTree/qryTreeAll2.do'
			});
		},
		
		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/demo/func/demoGrid/qryAll.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.demo.leftUpDownLayout.init();
});