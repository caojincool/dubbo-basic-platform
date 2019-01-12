//////////////////////////////////////////
//单据类型
ruizhi.Package("ruizhi.loanAfterStatisticalReport");

ruizhi.loanAfterStatisticalReport.collectionLogReport = function() {
	var fileName = "file:reportOfCollection.ureport.xml";
	var tableId = "collectionLogReport-table";
	var formId = "collectionLogReport-form";
	var user = ruizhi.GetUser();
	return {
		init : function(){
			$("#staffId").val(user.staffId);
			//初始化表格
			debugger;
			ruizhi.reportManager.report.init(tableId,formId,fileName,null);
			var c = 2;
		},
		submit : function(){
			ruizhi.reportManager.report.submit(tableId,formId);
		},
		clearData : function(){
			ruizhi.reportManager.report.clearData(formId);
			ruizhi.loanAfterStatisticalReport.collectionLogReport.submit();
		},
	}
}();
//初始化
ruizhi.ExecWait(function() {
	ruizhi.loanAfterStatisticalReport.collectionLogReport.init();
});
