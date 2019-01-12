//////////////////////////////////////////
//任务派发规则实例详情页面
ruizhi.Package("ruizhi.order");

ruizhi.order.woDispRuleInstDetail = function() {
	
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/order/funcPage/woDispRuleInst/woDispRuleInstDetail.do';//当前窗口的URL
	var pageDateType = null;//新增：CREATE，编辑：UPDATE
	var _UserDetail = ruizhi.GetUser();
	
	return{
		//初始化
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var woDispRuleInst = paramObj.woDispRuleInst;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(woDispRuleInst));
			pageDateType = woDispRuleInst.pageDateType;
			
			form1 = new ruizhi.FormExt("order-woDispRuleInstDetail-form1");
			
			form1.objectToForm(woDispRuleInst);
		},	
		
		//提交到上一页面的回调方法
		doSubmit : function() {
			//验证表单必填项
			if(!form1.validate()){
				return;
			}

			var valueObj = form1.formToObject();// 整个表单的值
			
			if(ruizhi.IsNull(valueObj.ruleId)){
				ruizhi.Alert("请选择任务派发规则！");
				return ;
			}
			if(ruizhi.IsNull(valueObj.partyType)){
				ruizhi.Alert("请选择接收人类型！");
				return ;
			}
			if(ruizhi.IsNull(valueObj.partyId)){
				ruizhi.Alert("请选择接收人！");
				return ;
			}
			
			if(!ruizhi.IsNull(pageDateType) && pageDateType == 'CREATE'){//新增
//			if(ruizhi.IsNull(valueObj.woDispRuleInst)){//新增
				valueObj.createUserId = _UserDetail.userId;//创建人
			}else{//编辑
				valueObj.modifyUserId = _UserDetail.userId;//修改人
			}
			
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			
			var param = valueObj;
			//alert("整个提交数据:"+ruizhi.ToJson(param));
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			
		},
		
		//关闭窗口
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		
		//单选任务派发规则
		selWoDispRule : function(){
			var url = WEB_ROOT + '/order/funcPage/woDispRule/selWoDispRule.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.submitParam = null;//提交到后台的参数
			paramObj.title = "单选任务派发规则窗口";
			var submitFn = ruizhi.order.woDispRuleInstDetail.selWoDispRuleComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selWoDispRuleComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param)){
				form1.setValue('ruleId', param.ruleId);
				form1.setValue('ruleType', param.ruleType);
			}
		},

		
		//单选接收人
		selParty : function(){
			var valueObj = form1.formToObject();// 整个表单的值
			if(ruizhi.IsNull(valueObj.partyType)){
				ruizhi.Alert("请选择接收人类型！");
				return ;
			}
			var url = null;
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			if(valueObj.partyType == 'STA'){//员工
				url = WEB_ROOT + '/oaas/funcPage/staff/selStaff.do';
				paramObj.title = "单选员工窗口";
			}else if(valueObj.partyType == 'USER'){//用户
				url = WEB_ROOT + '/oaas/funcPage/user/selUser.do';
				paramObj.title = "单选用户窗口";
			}else if(valueObj.partyType == 'ORG'){//部门
				url = WEB_ROOT + '/oaas/funcPage/org/selOrg.do';
				paramObj.title = "单选部门窗口";
				paramObj.orgType = "ALLORG";//查询全部的部门
			}else if(valueObj.partyType == 'JOB'){//岗位
				url = WEB_ROOT + '/oaas/funcPage/job/selJob.do';
				paramObj.title = "单选岗位窗口";
				paramObj.orgType = "ALLORG";//查询全部的部门（查询岗位也要传）
			}else if(valueObj.partyType == 'SYS'){//系统
				ruizhi.Alert("外系统暂时不开放！");
				return ;
			}
//			paramObj.submitParam = null;//提交到后台的参数
			var submitFn = ruizhi.order.woDispRuleInstDetail.selPartyComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selPartyComeBack :function(param){
//			alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param)){
				var valueObj = form1.formToObject();// 整个表单的值
				if(ruizhi.IsNull(valueObj.partyType)){
					ruizhi.Alert("请选择接收人类型！");
					return ;
				}
				if(valueObj.partyType == 'STA'){//员工
					form1.setValue('partyId', param.staffId);
					form1.setValue('partyName', param.staffName);
				}else if(valueObj.partyType == 'USER'){//用户
					form1.setValue('partyId', param.userId);
					form1.setValue('partyName', param.userText);
				}else if(valueObj.partyType == 'ORG'){//部门
					form1.setValue('partyId', param[0].orgId);
					form1.setValue('partyName', param[0].orgName);
				}else if(valueObj.partyType == 'JOB'){//岗位
					form1.setValue('partyId', param.jobId);
					form1.setValue('partyName', param.jobName);
				}else if(valueObj.partyType == 'SYS'){//系统
//					form1.setValue('partyId', param.ruleId);
//					form1.setValue('partyName', param.ruleType);
				}
			}
		},
		
	}
}();

//初始化
ruizhi.ExecWait(function(){
	ruizhi.order.woDispRuleInstDetail.init()
});