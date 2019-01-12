//////////////////////////////////////////
ruizhi.Package("ruizhi.loanStatisticsReport");

ruizhi.loanStatisticsReport.customerBirthday = function() {
	var fileName = "file:生日客户报表.ureport.xml";
	var tableId = "customerBirthday-table";
	var formId = "customerBirthday-form";
	return {
		init : function(){
			//初始化表格
			ruizhi.reportManager.report.init(tableId,formId,fileName, null);
		},
		
	}
}();
//初始化
ruizhi.ExecWait(function() {
	ruizhi.loanStatisticsReport.customerBirthday.init();
});
