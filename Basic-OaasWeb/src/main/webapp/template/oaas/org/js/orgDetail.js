//知识体系详情页面
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.orgDetail = function() {
	
	//私有成员
	var form1;
	var URL =  WEB_ROOT + '/oaas/funcPage/org/orgDetail.do';//当前窗口的URL
	var paramObj;
	//私有方法
	var checkCode;
	return{
		/* 初始化 */
		init : function() {
			paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var org = paramObj.Org;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(org));
			
			form1 = new ruizhi.FormExt("oaas-orgDetail-form1");
			
			form1.objectToForm(org);
			
			if(form1.getValue("orgId")){
				form1.getObject("orgCode").prop("disabled",true);
			};
			
		},	
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			var valueObj = form1.formToObject();// 整个表单的值
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			if(valueObj.orgId == ''){
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
		selArea : function(){
			var url = WEB_ROOT + '/oaas/funcPage/area/selArea.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			// TODO 权限功能完善需要回来改
//			if(ruizhi.IsSuperadminRole() || _SESSION.hasPriv("oaas.special.allAreaQry") ){
			if(ruizhi.IsSuperadminRole() ){
				areaType ='ALLAREA';
			}else{
				areaType ='CURAREA';
			}
			paramObj.userId = _SESSION.userId;//用户标识（All的状态可选）
			paramObj.areaType = areaType;//查询范围
			paramObj.title = "单选区域";
			var submitFn = ruizhi.oaas.orgDetail.selAreaComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-sm';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selAreaComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			var params = {};
			if(param != null && param.length >0){
				for(var i = 0 ; i < param.length;i++){
					params.areaName = param[0].areaName;
					params.areaId = param[0].areaId;
					form1.setValue('areaName',params.areaName);
					form1.setValue('areaId',params.areaId);
//					$("#areaName").val(params.areaName);//赋值
//					$("#areaId").val(params.areaId);
				}
				
			}
		},
		orgCode : function(){
			if(checkCode == 'true'){
				var url = WEB_ROOT + '/oaas/func/orgCode.do';;
				var orgCode = form1.getValue('orgCode');
				var params = {};
				params.orgCode = orgCode;
				var valueObj = ruizhi.InvokeMethodAsyn(url,params,function(){
					
					$("#orgInfo").css("color","red").text(valueObj.info);
				});
			}
		},
		selCompany :function(){
			var url = WEB_ROOT + '/oaas/funcPage/company/selCompany.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.title = "单选公司";
			var submitFn = ruizhi.oaas.orgDetail.selCompanyComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		selCompanyComeBack :function(param){
			
			if(!ruizhi.IsNull(param)){
					form1.setValue('companyName',param.fullName);
					form1.setValue('companyId',param.companyId);
				
			}
		}
		
		
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.oaas.orgDetail.init()
});

// ////////////////////////////////////////
// function定义
