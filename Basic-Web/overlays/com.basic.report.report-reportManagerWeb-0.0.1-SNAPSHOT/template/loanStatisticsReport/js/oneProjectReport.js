//////////////////////////////////////////
ruizhi.Package("ruizhi.loanStatisticsReport");

ruizhi.loanStatisticsReport.oneProjectReport = function() {
	var fileName = "file:一抵项目统计.ureport.xml";
	var tableId = "oneProjectReport-table";
	var formId = "oneProjectReport-form";
	return {
		init : function(){
			//初始化表格
			ruizhi.reportManager.report.init(tableId,formId,fileName, null);
		},
		
	}
}();
//初始化
ruizhi.ExecWait(function() {
	ruizhi.loanStatisticsReport.oneProjectReport.init();
});
