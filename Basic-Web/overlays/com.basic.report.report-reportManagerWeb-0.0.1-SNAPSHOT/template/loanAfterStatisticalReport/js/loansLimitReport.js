//////////////////////////////////////////
ruizhi.Package("ruizhi.loanAfterStatisticalReport");

ruizhi.loanAfterStatisticalReport.loansLimitReport = function() {
	var fileName = "file:reportOfLoansLimit.ureport.xml";
	var tableId = "loansLimitReport-table";
	var formId = "loansLimitReport-form";
	var user = ruizhi.GetUser();
	return {
		init : function(){
			$("#staffId").val(user.staffId);
			//初始化表格
			debugger;
			ruizhi.reportManager.report.init(tableId,formId,fileName, null);
			var c = 2;
		},
		submit : function(){
			ruizhi.reportManager.report.submit(tableId,formId);
		},
		clearData : function(){
			ruizhi.reportManager.report.clearData(formId);
			ruizhi.loanAfterStatisticalReport.loansLimitReport.submit();
		},
	}
}();
//初始化
ruizhi.ExecWait(function() {
	ruizhi.loanAfterStatisticalReport.loansLimitReport.init();
});
