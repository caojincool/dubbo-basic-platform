//////////////////////////////////////////
ruizhi.Package("ruizhi.loanAfterStatisticalReport");

ruizhi.loanAfterStatisticalReport.personalBusiness = function() {
	var fileName = "file:个人业务统计表.ureport.xml";
	var tableId = "personalBusiness-table";
	var formId = "personalBusiness-form";
	var form;
	var user = ruizhi.GetUser();
	return {
		init : function(){
			$("#staffId").val(user.staffId);
			from = new ruizhi.FormExt(formId);
			ruizhi.loanAfterStatisticalReport.personalBusiness.loadSelect();
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
			ruizhi.loanAfterStatisticalReport.personalBusiness.submit();
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
	ruizhi.loanAfterStatisticalReport.personalBusiness.init();
});
