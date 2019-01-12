//////////////////////////////////////////
ruizhi.Package("ruizhi.loanStatisticsReport");

ruizhi.loanStatisticsReport.twoProjectReport = function() {
	var fileName = "file:二抵项目统计.ureport.xml";
	var tableId = "twoProjectReport-table";
	var formId = "twoProjectReport-form";
	return {
		init : function(){
			//初始化表格
			ruizhi.reportManager.report.init(tableId,formId,fileName, null);
		},
		
	}
}();
//初始化
ruizhi.ExecWait(function() {
	ruizhi.loanStatisticsReport.twoProjectReport.init();
});
