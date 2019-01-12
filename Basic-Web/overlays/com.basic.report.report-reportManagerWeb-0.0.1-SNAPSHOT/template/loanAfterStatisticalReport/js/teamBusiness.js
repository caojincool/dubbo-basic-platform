//////////////////////////////////////////
ruizhi.Package("ruizhi.loanAfterStatisticalReport");

ruizhi.loanAfterStatisticalReport.teamBusiness = function() {
	var fileName = "file:团队业务统计表.ureport.xml";
	var tableId = "teamBusiness-table";
	var formId = "teamBusiness-form";
	var form;
	var user = ruizhi.GetUser();
	return {
		init : function(){
			$("#staffId").val(user.staffId);
			from = new ruizhi.FormExt(formId);
			ruizhi.loanAfterStatisticalReport.teamBusiness.loadSelect();
			//初始化表格
			ruizhi.reportManager.report.init(tableId,formId,fileName,null);
			var c = 2;
		},
		submit : function(){
			ruizhi.reportManager.report.submit(tableId,formId);
		},
		clearData : function(){
			var inputs = $("#" + formId_ +" input[type='text']");
			$(inputs).val('');
			$("[name = projectGroup]").val("0");
			ruizhi.loanAfterStatisticalReport.teamBusiness.submit();
		},
		loadSelect: function(){
			var projectGroup = ruizhi.TableDictGetValueList("BS_MAIN_PROJECT_ORDER","PROJECT_GROUP");
			projectGroup.splice(0, 0, {colText : "请选择", colValue : "0"});
			var projectGroups = $("[name = projectGroup]");
			projectGroups.empty();
			for(var i=0;i<projectGroup.length;i++) {
			var option = $("<option>").text(projectGroup[i].colText).val(projectGroup[i].colValue)
			projectGroups.append(option);
			}
		}
	}
}();
//初始化
ruizhi.ExecWait(function() {
	ruizhi.loanAfterStatisticalReport.teamBusiness.init();
});
