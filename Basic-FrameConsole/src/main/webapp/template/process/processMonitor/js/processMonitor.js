//////////////////////////////////////////
//processMonitor
ruizhi.Package("ruizhi.process");

ruizhi.process.processMonitor = function() {

	var grid1;
	var form1;

	return {
		init : function() {

			grid1 = new ruizhi.DataGrid("process-processMonitor-grid1"/*,{
				postData : {
					
				},
				url : WEB_ROOT + '/process/func/processMonitor/pagin.do'
			}*/);
			form1 = new ruizhi.FormExt("process-processMonitor-form1");
		},

		loadData : function() {// 加载数据
			var url = WEB_ROOT + '/process/func/processMonitor/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.process.processMonitor.init();
});