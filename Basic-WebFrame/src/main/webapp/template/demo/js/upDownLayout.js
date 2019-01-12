//////////////////////////////////////////
//上下布局
ruizhi.Package("ruizhi.demo");

ruizhi.demo.upDownLayout = function() {

	var grid1;
	var form1;
	var _UserDetail = ruizhi.GetUser();
	
	return {
		//初始化
		init : function() {

//	        var height1 = $(window).height()-20;  
//	        alert(height1);
//	        $("#main_layout").attr("style","width:100%;height:"+height1+"px");  
	        $("#demo-upDownLayout-layout").layout();
//	        $("#main_layout").layout("resize",{  
//	            width:"100%",  
//	            height:height1-500+"px"  
//	        });  

	        
	        
			grid1 = new ruizhi.DataGrid("demo-upDownLayout-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/demo/func/demoGrid/qryAll.do'
			});
			form1 = new ruizhi.FormExt("demo-upDownLayout-form1");
			
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
	ruizhi.demo.upDownLayout.init();
});