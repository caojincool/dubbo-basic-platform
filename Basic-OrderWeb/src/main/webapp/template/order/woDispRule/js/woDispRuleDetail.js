//////////////////////////////////////////
//任务派发规则详情页面
ruizhi.Package("ruizhi.order");

ruizhi.order.woDispRuleDetail = function() {
	
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/order/funcPage/woDispRule/woDispRuleDetail.do';//当前窗口的URL
	var pageDateType = null;//新增：CREATE，编辑：UPDATE
	var _UserDetail = ruizhi.GetUser();
	
	return{
		//初始化
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var woDispRule = paramObj.woDispRule;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(woDispRule));
			pageDateType = woDispRule.pageDateType;
			
			form1 = new ruizhi.FormExt("order-woDispRuleDetail-form1");
			
			form1.objectToForm(woDispRule);
		},	
		
		//提交到上一页面的回调方法
		doSubmit : function() {
			//验证表单必填项
			if(!form1.validate()){
				return;
			}

			var valueObj = form1.formToObject();// 整个表单的值
			
			if(ruizhi.IsNull(valueObj.tacheId)){
				ruizhi.Alert("请选择环节！");
				return ;
			}
			if(ruizhi.IsNull(valueObj.ruleType)){
				ruizhi.Alert("请选择规则类型！");
				return ;
			}else{
				if(valueObj.ruleType == 'SELF'){//自定义规则派单，必须填 自定义规则
					if(ruizhi.IsNull(valueObj.dispCode)){
						ruizhi.Alert("请填写自定义规则！");
						return ;
					}
				}
			}
			
			if(!ruizhi.IsNull(pageDateType) && pageDateType == 'CREATE'){//新增
//			if(ruizhi.IsNull(valueObj.woDispRule)){//新增
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
		
		//单选环节
		selTache : function(){
			var url = WEB_ROOT + '/order/funcPage/tache/selTache.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.submitParam = null;//提交到后台的参数
			paramObj.title = "单选环节";
			var submitFn = ruizhi.order.woDispRuleDetail.selTacheComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selTacheComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param)){
				form1.setValue('tacheId', param.tacheId);
				form1.setValue('tacheName', param.tacheName);
			}
		},

	}
}();

//初始化
ruizhi.ExecWait(function(){
	ruizhi.order.woDispRuleDetail.init()
});