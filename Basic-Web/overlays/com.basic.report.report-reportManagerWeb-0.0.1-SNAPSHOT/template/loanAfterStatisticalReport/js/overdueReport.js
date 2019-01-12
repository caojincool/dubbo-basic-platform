//////////////////////////////////////////
ruizhi.Package("ruizhi.loanAfterStatisticalReport");

ruizhi.loanAfterStatisticalReport.overdueReport = function() {
	var fileName = "file:reportOfOverdue.ureport.xml";
	var tableId = "overdueReport-table";
	var formId = "overdueReport-form";
	var user = ruizhi.GetUser();
	return {
		init : function(){
			$("#staffId").val(user.staffId);
			//初始化表格
			debugger;
			ruizhi.reportManager.report.init(tableId,formId,fileName);
			var c = 2;
		},
		submit : function(){
			ruizhi.reportManager.report.submit(tableId,formId);
		},
		clearData : function(){
			ruizhi.reportManager.report.clearData(formId);
			ruizhi.loanAfterStatisticalReport.overdueReport.submit();
		},
	}
}();
//初始化
ruizhi.ExecWait(function() {
	ruizhi.loanAfterStatisticalReport.overdueReport.init();
});
