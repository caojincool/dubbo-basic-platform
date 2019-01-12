
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.staffDetail = function() {
	
	//私有成员
	var form1;
	var URL =  WEB_ROOT + '/oaas/funcPage/staff/staffDetail.do';//当前窗口的URL
	var paramObj;
	//私有方法
	var checkCode;
	return{
		/* 初始化 */
		init : function() {
			paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var staff = paramObj.staff;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(staff));
			
			form1 = new ruizhi.FormExt("oaas-staffDetail-form1");
			form1.objectToForm(staff);
			
			if(form1.getValue("staffId")){
				form1.getObject("staffNumber").prop("disabled",true);
				form1.getObject("displayIndex").prop("disabled",true);
				form1.getObject("remarks").prop("disabled",true);
//				$("#selectBtn").prop("disabled",true);
			};
			
		},	
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			var jobName = form1.getObject('jobName')
			if(!jobName.val()){
				ruizhi.GetObj('jobName-error').remove();
				var str ='<label id="jobName-error" class="validation-error-label" for="jobName">请选择职位</label>'
				jobName.parent().after(str);
				return;
			};
			var valueObj = form1.formToObject();// 整个表单的值
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			if(valueObj.staffId == ''){
				valueObj.createUserId = _SESSION.userId;//创建人
				
			}else{
				valueObj.modifyUserId = _SESSION.userId;//修改人
			}
			if(valueObj.parentJobId == ''){
				valueObj.parentJobId = -1;
			}
			if(ruizhi.IsNull(valueObj.state)){
				valueObj.state = '10A'
			}
			
			var param = valueObj;
			//alert("整个提交数据:"+ruizhi.ToJson(param));
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			

		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		//单选职位
		selJob : function(){
			var url = WEB_ROOT + '/oaas/funcPage/job/selJob.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			// TODO 权限功能完善需要回来改
//			if(ruizhi.IsSuperadminRole() || _SESSION.hasPriv("oaas.special.allJobQry") ){
			if(ruizhi.IsSuperadminRole() ){
				orgType ='ALLORG';
			}else{
				orgType ='CURORG';
			}
			paramObj.userId = _SESSION.userId;//用户标识（All的状态可选）
			paramObj.orgType = orgType;//查询范围
			paramObj.title = "单选职位";
			var submitFn = ruizhi.oaas.staffDetail.selJobComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-md';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selJobComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			var params = {};
			if(!ruizhi.IsNull(param)){
				ruizhi.GetObj('jobName-error').remove();
				form1.setValue('jobName',param.jobName);
				form1.setValue('jobId',param.jobId);
				
			}
		},
		staffCode : function(){
			if(checkCode == 'true'){
				var url = WEB_ROOT + '/oaas/func/staffCode.do';;
				var staffCode = form1.getValue('staffCode');
				var params = {};
				params.staffCode = staffCode;
				var valueObj = ruizhi.InvokeMethodAsyn(url,params,function(){
					
					$("#staffInfo").css("color","red").text(valueObj.info);
				});
			}
		}
		
		
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.oaas.staffDetail.init()
});

// ////////////////////////////////////////
// function定义
