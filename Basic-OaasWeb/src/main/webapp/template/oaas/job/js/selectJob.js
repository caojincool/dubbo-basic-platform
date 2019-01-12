
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.selectJob = function() {
	
	//私有成员
	var form1;
	var _this = this;
	var orgId = null;
	var grid1;
	
	var URL =  WEB_ROOT + '/oaas/funcPage/job/selectJob.do';//当前窗口的URL
	//私有方法
	
	return{
		/* 初始化 */
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			orgId = paramObj.orgId;
			form1 = new ruizhi.FormExt("oaas-selectJob-form1");
			
			grid1 = new ruizhi.DataGrid("oaas-selectJob-grid1",{
				postData : {
					orgId : orgId
				},
				url : WEB_ROOT + '/oaas/func/job/pagin.do'
			});
			
		},	
		
		query:function(){
			var url = WEB_ROOT + '/oaas/func/job/pagin.do';
			var paramObj = form1.formToObject();
			if(paramObj.jobName == ''){
				paramObj.jobName = null;
			}
			paramObj.orgId = orgId;
			grid1.loadData(url, paramObj);
		},
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			var item = grid1.getSelectedItem();
			var param = {};
			param.item = item;
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		
		clear : function(){
			form1.setValue('jobName',null);
		},
		
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.oaas.selectJob.init()
});

// ////////////////////////////////////////
// function定义
