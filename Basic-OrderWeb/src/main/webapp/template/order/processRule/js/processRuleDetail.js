//////////////////////////////////////////
//Ruizhisoft corp. 2016-03-01
//Author :yu.xiao
//commits:表单
//////////////////////////////////////////

//////////////////////////////////////////
//类定义
ruizhi.Package("ruizhi.order");

ruizhi.order.processRuleDetail = function() {
	
	//私有成员
	var form1 = null;
	var _this = this;
	var URL  =  WEB_ROOT + '/order/funcPage/processRule/processRuleDetail.do';//当前窗口的URL
	var pageDateType = null;//新增：CREATE，编辑：UPDATE
	var _UserDetail = ruizhi.GetUser();
	
	return{
		//初始化
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			var processRule = paramObj.processRule;
			pageDateType = processRule.pageDateType;
			
			form1 = new ruizhi.FormExt("order-processRuleDetail-form1");
			
			//编码不允许修改
			if(!ruizhi.IsNull(processRule) && !ruizhi.IsNull(processRule.serviceCode)){
				var orderServiceObject = form1.getObject("serviceCode");
				orderServiceObject.attr("readonly", "readonly");
			}
			
			form1.objectToForm(processRule);
		},	
		openProcessRule : function() {
			
			var url = WEB_ROOT + '/order/funcPage/orderService/selOrderService.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			var submitFn = ruizhi.order.processRuleDetail.openProcessRuleComeBack;//模态窗口提交后回调方法

			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn);
			
		},
		
		openProcessRuleComeBack :function(param){
			form1.setValue('serviceId', param.serviceId);
			form1.setValue('serviceCode', param.serviceCode);
			//alert('abc');
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(param));
		},
		//提交到上一页面的回调方法
		doSubmit : function() {
			//验证表单必填项
			if(!form1.validate()){
				return;
			}

			var valueObj = form1.formToObject();// 整个表单的值
			
			if(!ruizhi.IsNull(pageDateType) && pageDateType == 'CREATE'){//新增
//			if(ruizhi.IsNull(valueObj.orderPriority)){//新增
				valueObj.createUserId = _UserDetail.userId;//创建人
			}else{//编辑
				valueObj.modifyUserId = _UserDetail.userId;//修改人
			}
			var param = valueObj;
			//alert("整个提交数据:"+ruizhi.ToJson(param));
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			
		},
		
		//关闭窗口
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},

	}
}();

//初始化
ruizhi.ExecWait(function(){
	ruizhi.order.processRuleDetail.init()
});