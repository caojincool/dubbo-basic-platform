//知识体系详情页面
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.jobDetail = function() {
	
	//私有成员
	var form1;
	var URL =  WEB_ROOT + '/oaas/funcPage/job/jobDetail.do';//当前窗口的URL
	var paramObj;
	//私有方法
	var checkCode;
	return{
		/* 初始化 */
		init : function() {
			paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var job = paramObj.job;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(job));
			
			form1 = new ruizhi.FormExt("oaas-jobDetail-form1");
			
			form1.objectToForm(job);
			
			if(form1.getValue("jobId")){
				form1.getObject("jobCode").prop("disabled",true);
				form1.getObject("displayIndex").prop("disabled",true);
				form1.getObject("remarks").prop("disabled",true);
				$("#selectBtn").prop("disabled",true);
			};
			
		},	
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			var valueObj = form1.formToObject();// 整个表单的值
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			if(valueObj.jobId == ''){
				valueObj.createUserId = _SESSION.userId;//创建人
				
			}else{
				valueObj.modifyUserId = _SESSION.userId;//修改人
			}
			if(valueObj.parentOrgId == ''){
				valueObj.parentOrgId = -1;
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
		//单选部门
		selOrg : function(){
			var url = WEB_ROOT + '/oaas/funcPage/org/selOrg.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			// TODO 权限功能完善需要回来改
//			if(ruizhi.IsSuperadminRole() || _SESSION.hasPriv("oaas.special.allOrgQry") ){
			if(ruizhi.IsSuperadminRole() ){
				orgType ='ALLORG';
			}else{
				orgType ='CURORG';
			}
			paramObj.userId = _SESSION.userId;//用户标识（All的状态可选）
			paramObj.orgType = orgType;//查询范围
			paramObj.title = "单选部门";
			var submitFn = ruizhi.oaas.jobDetail.selOrgComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-sm';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selOrgComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			var params = {};
			if(param != null && param.length >0){
				for(var i = 0 ; i < param.length;i++){
					params.orgName = param[0].orgName;
					params.orgId = param[0].orgId;
					form1.setValue('orgName',params.orgName);
					form1.setValue('orgId',params.orgId);
				}
				
			}
		},
		jobCode : function(){
			if(checkCode == 'true'){
				var url = WEB_ROOT + '/oaas/func/jobCode.do';;
				var jobCode = form1.getValue('jobCode');
				var params = {};
				params.jobCode = jobCode;
				var valueObj = ruizhi.InvokeMethodAsyn(url,params,function(){
					
					$("#jobInfo").css("color","red").text(valueObj.info);
				});
			}
		}
		
		
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.oaas.jobDetail.init()
});

// ////////////////////////////////////////
// function定义
