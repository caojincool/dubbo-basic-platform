//////////////////////////////////////////
ruizhi.Package("ruizhi.loanAfterStatisticalReport");

ruizhi.loanAfterStatisticalReport.loansRepaymentReport = function() {
	var fileName = "file:reportOfLoansRepayment.ureport.xml";
	var user = ruizhi.GetUser();
	var tableId = "loansRepaymentReport-table";
	var formId = "loansRepaymentReport-form";
	return {
		init : function(){
			$("#staffId").val(user.staffId);
			//初始化表格
			debugger;
//			ruizhi.reportManager.report.init(tableId,formId,fileName);
			ruizhi.loanAfterStatisticalReport.loansRepaymentReport.initTable();
			var c = 2;
		},
		submit : function(){
			ruizhi.reportManager.report.submit(tableId,formId);
		},
		clearData : function(formId){
			ruizhi.reportManager.report.clearData(formId);
			ruizhi.loanAfterStatisticalReport.loansRepaymentReport.submit();
		},
		initTable : function(){
			$("#" + tableId).bootstrapTable({
				url : 	WEB_ROOT + "/reportManager/getReportData.do",
	            method: 'post', 					//请求方式（*）  
	            contentType: "application/x-www-form-urlencoded",
//	            locale:'zh-CN',						//中文支持
	            striped: true,                      //是否显示行间隔色  
	            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）  
	            pagination: true,                   //是否显示分页（*）  
	            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）  
	            pageNumber:1,                       //初始化加载第一页，默认第一页  
	            pageSize: 50,                       //每页的记录行数（*）  
	            pageList: [50, 100, 150,"All"],     //可供选择的每页的行数（*）  
	            showColumns: true,                  //是否显示所有的列  
	            clickToSelect: true,                //是否启用点击选中行  
	            strictSearch: true,
	            queryParamsType : "",
	            columns: [
	                      	[
								{
							    field: 'name',
							    title: "借款人",
							    valign:"middle",
							    align:"center",
							    colspan: 1,
							    rowspan: 2
							 	},
							 	{
							 		field: 'borName',
							 		title: "借款人",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 1,
							 		rowspan: 2
							 	},
							 	{
							 		field: 'projectOrderCode',
							 		title: "项目编码",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 1,
							 		rowspan: 2
							 	},
							 	{
							 		field: 'staffName',
							 		title: "经办人",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 1,
							 		rowspan: 2
							 	},
							 	{
							 		field: 'orgName',
							 		title: "部门",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 1,
							 		rowspan: 2
							 	},
							 	{
							 		field: 'groupName',
							 		title: "组别",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 1,
							 		rowspan: 2
							 	},
							 	{
							 		field: 'manager',
							 		title: "主管",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 1,
							 		rowspan: 2
							 	},
							 	{
							 		field: 'loansCenterMainCode',
							 		title: "合同编号",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 1,
							 		rowspan: 2
							 	},
							 	{
							 		field: 'loansCenterMainCode',
							 		title: "借款金额",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 1,
							 		rowspan: 2
							 	},
							 	{
							 		field: 'loansCenterMainCode',
							 		title: "还款方式",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 1,
							 		rowspan: 2
							 	},
							 	{
							 		field: 'loansCenterMainCode',
							 		title: "放款日",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 1,
							 		rowspan: 2
							 	},
							 	{
							 		field: 'loansCenterMainCode',
							 		title: "到期日",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 1,
							 		rowspan: 2
							 	},
							 	{
							 		field: 'loansCenterMainCode',
							 		title: "借贷利率",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 1,
							 		rowspan: 2
							 	},
							 	{
							 		field: 'loansCenterMainCode',
							 		title: "咨询费率",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 1,
							 		rowspan: 2
							 	},
							 	{
							 		title: "上上月",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 7,
							 		rowspan: 1
							 	},
							 	{
							 		title: "上月",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 7,
							 		rowspan: 1
							 	},
							 	{
							 		title: "当前月",
							 		valign:"middle",
							 		align:"center",
							 		colspan: 7,
							 		rowspan: 1
							 	}
	                      	 ],
	                      	 [
	                     	  	{         
	                     	  		field: 'f_1', 
	                     	  		title: '收款日',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 'f_2', 
	                     	  		title: '本金',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 'f_3', 
	                     	  		title: '利息',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 'f_4', 
	                     	  		title: '咨询费',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 'f_5', 
	                     	  		title: '逾期天数',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 'f_6', 
	                     	  		title: '逾期费用',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 'f_7', 
	                     	  		title: '合计',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 's_1', 
	                     	  		title: '收款日',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 's_2', 
	                     	  		title: '本金',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 's_3', 
	                     	  		title: '利息',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 's_4', 
	                     	  		title: '咨询费',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 's_5', 
	                     	  		title: '逾期天数',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 's_6', 
	                     	  		title: '逾期费用',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 's_7', 
	                     	  		title: '合计',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 't_1', 
	                     	  		title: '收款日',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 't_2', 
	                     	  		title: '本金',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 't_3', 
	                     	  		title: '利息',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 't_4', 
	                     	  		title: '咨询费',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 't_5', 
	                     	  		title: '逾期天数',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 't_6', 
	                     	  		title: '逾期费用',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	},
	                     	  	{         
	                     	  		field: 't_7', 
	                     	  		title: '合计',       
	                     	  		valign:"middle",   
	                     	  		align:"center"
	                     	  	}
	                      	  ]
	                      ],  
	            queryParams: function queryParams(params) {  //传递参数（*），这里应该返回一个object，即形如{param1:val1,param2:val2}  
	            	debugger;
            		var reportForm = new ruizhi.FormExt(formId);
            		var searchParam = reportForm.formToObject();
            		searchParam.fileName = fileName;
            		searchParam.pageNumber = params.pageNumber;
            		searchParam.pageSize = params.pageSize;
                    return searchParam; 
                  },
	        });
		},
	}
}();
//初始化
ruizhi.ExecWait(function() {
	ruizhi.loanAfterStatisticalReport.loansRepaymentReport.init();
});
