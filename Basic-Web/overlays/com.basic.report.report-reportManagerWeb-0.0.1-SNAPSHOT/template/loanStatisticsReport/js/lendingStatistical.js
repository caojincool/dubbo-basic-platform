//////////////////////////////////////////
ruizhi.Package("ruizhi.loanStatisticsReport");

ruizhi.loanStatisticsReport.lendingStatistical = function() {
	var fileName = "file:贷款发放统计报表.ureport.xml";
	var tableId = "lendingStatistical-table";
	var formId = "lendingStatistical-form";
	return {
		init : function(){
			//初始化表格
			ruizhi.reportManager.report.init(tableId,formId,fileName, null);
		},
		
	}
}();
//初始化
ruizhi.ExecWait(function() {
	ruizhi.loanStatisticsReport.lendingStatistical.init();
});
