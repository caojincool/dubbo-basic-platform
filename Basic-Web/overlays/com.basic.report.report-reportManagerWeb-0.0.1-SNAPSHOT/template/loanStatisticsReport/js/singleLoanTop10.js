//////////////////////////////////////////
ruizhi.Package("ruizhi.loanStatisticsReport");

ruizhi.loanStatisticsReport.singleLoanTop10 = function() {
	var fileName = "file:单笔贷款十大客户.ureport.xml";
	var tableId = "singleLoanTop10-table";
	var formId = "singleLoanTop10-form";
	return {
		init : function(){
			//初始化表格
			ruizhi.reportManager.report.init(tableId,formId,fileName, null);
		},
		
	}
}();
//初始化
ruizhi.ExecWait(function() {
	ruizhi.loanStatisticsReport.singleLoanTop10.init();
});
