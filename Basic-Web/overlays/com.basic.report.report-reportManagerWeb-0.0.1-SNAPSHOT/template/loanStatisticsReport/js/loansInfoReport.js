//////////////////////////////////////////
ruizhi.Package("ruizhi.loanStatisticsReport");

ruizhi.loanStatisticsReport.loansInfoReport = function() {
	var fileName = "file:loanStatisticsReport.ureport.xml";
	var tableId = "loansInfoReport-table";
	var formId = "loansInfoReport-form";
	return {
		init : function(){
			//初始化表格
			ruizhi.reportManager.report.init(tableId,formId,fileName, null);
		},
		submit : function(){
			ruizhi.reportManager.report.submit(tableId,formId);
		},
		clearData : function(){
			ruizhi.reportManager.report.clearData(formId);
			ruizhi.loanStatisticsReport.loansInfoReport.submit();
		},
	}
}();
//初始化
ruizhi.ExecWait(function() {
	ruizhi.loanStatisticsReport.loansInfoReport.init();
});
