//////////////////////////////////////////
//单据类型
ruizhi.Package("ruizhi.reportManager");

ruizhi.reportManager.report = function() {
	var reportForm;
	var tableId = null;
	var formId = null;
	var fileName;
	return {
		init : function(tableId_,formId_,fileName_,mergeCellsNames){
			debugger;
			if(ruizhi.IsNull(tableId_) ||ruizhi.IsNull(formId_) 
				||ruizhi.IsNull(fileName_)){
				ruizhi.Alert("必传参数为null");
				return;
			}
			tableId = tableId_;
			formId = formId_;
			fileName = fileName_;
			reportForm = new ruizhi.FormExt(formId);
			var params = {};
			params.fileName = fileName;
			params.formId = formId_;
			var url = WEB_ROOT + '/reportManager/getColumnAndProperty.do';
			ruizhi.InvokeMethodAsyn(url,params,function(msg){
				var state = msg.state;
				if(state == 'success'){
					ruizhi.reportManager.table.init(tableId,msg,params,mergeCellsNames)
				}else{
					ruizhi.Alert("加载数据失败!");
				}
			});
		},
		getFormValue : function(){
			var valueObj = reportForm.formToObject();
			var param = "";
			if(null != valueObj){
				for(var key in valueObj){
					var val = valueObj[key];
					if(!ruizhi.IsNull(val)){
						param += "&" + key +"=" + val;
					}
				}
			}
			return param;
		},
		submit : function(tableId_,formId_){
			reportForm = new ruizhi.FormExt(formId_);
			$("#" + tableId_).bootstrapTable('refresh');
		},
		clearData : function(formId_){
			var inputs = $("#" + formId_ +" input[type='text']");
			debugger;
			var selects = $("#" + formId_ +" select[class='form-control selectpicker']");
			if(selects.length > 0){
				$(selects).selectpicker('val',null);
			}
			$(inputs).val('');
		},
		exportONPDF : function(){
			var param = ruizhi.reportManager.report.getFormValue();
			window.open(WEB_ROOT +"/ureport/pdf/show?_u=" +fileName+ "&type=export" + param);
		},
		exportPDF : function(){
			var param = ruizhi.reportManager.report.getFormValue();
			window.location.href = WEB_ROOT +"/ureport/pdf?_u=" + fileName + "&type=export" + param;
		},
		exportWord : function(){
			var param = ruizhi.reportManager.report.getFormValue();
			window.location.href = WEB_ROOT +"/ureport/word?_u=" + fileName + "&type=export" + param;
		},
		exportExcel : function(){
			var param = ruizhi.reportManager.report.getFormValue();
			window.location.href = WEB_ROOT +"/ureport/excel?_u=" + fileName + "&type=export" + param;
		},
	}
}();
