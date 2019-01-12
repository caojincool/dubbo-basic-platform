//////////////////////////////////////////
ruizhi.Package("ruizhi.loanStatisticsReport");

ruizhi.loanStatisticsReport.managementCredit = function() {
	var fileName = "file:资方授信余额.ureport.xml";
	var tableId = "managementCredit-table";
	var formId = "managementCredit-form";
	return {
		init : function(){
			//初始化表格
			ruizhi.reportManager.report.init(tableId,formId,fileName, null);
		},
		
	}
}();
//初始化
ruizhi.ExecWait(function() {
	ruizhi.loanStatisticsReport.managementCredit.init();
});
