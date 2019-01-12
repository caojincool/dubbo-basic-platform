//////////////////////////////////////////
//字典表
ruizhi.Package("ruizhi.log");

ruizhi.log.systemLog = function() {

	var grid1;
	var form1;

	return {
		//初始化
		init : function() {

			grid1 = new ruizhi.DataGrid("log-systemLog-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/log/func/systemLog/pagin.do'
			});
			form1 = new ruizhi.FormExt("log-systemLog-form1");
		},
		
		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/log/func/systemLog/pagin.do';
			var paramObj = form1.formToObject();
			if(!ruizhi.IsNull(paramObj.beginTime)){
				paramObj.beginTime  = paramObj.beginTime +" 00:00:00";
			}
			if(!ruizhi.IsNull(paramObj.endTime)){
				paramObj.endTime  = paramObj.endTime+" 00:00:00";
			}
			grid1.loadData(url, paramObj);
		},
		logTypeTrans : function(cellvalue, options, rowObject){
			if(cellvalue == "LOGIN"){
				return "登录";
			}else if (cellvalue == "MENU"){
				
				return "菜单";
			}else if (cellvalue == "FUNC"){
				
				return "功能"
			}else{
				return "";
			}
		},
		resetForm : function (){
			form1.reset();
			form1.setValue("logType","");
		}
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.log.systemLog.init();
});