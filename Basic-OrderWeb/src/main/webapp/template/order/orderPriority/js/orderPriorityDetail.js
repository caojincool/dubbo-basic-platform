//////////////////////////////////////////
//单据类型详情页面
ruizhi.Package("ruizhi.order");

ruizhi.order.orderPriorityDetail = function() {
	
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/order/funcPage/orderPriority/orderPriorityDetail.do';//当前窗口的URL
	var pageDateType = null;//新增：CREATE，编辑：UPDATE
	var _UserDetail = ruizhi.GetUser();
	
	return{
		//初始化
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var orderPriority = paramObj.orderPriority;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(orderPriority));
			pageDateType = orderPriority.pageDateType;
			
			form1 = new ruizhi.FormExt("order-orderPriorityDetail-form1");
			
			//编码不允许修改
			if(!ruizhi.IsNull(orderPriority) && !ruizhi.IsNull(orderPriority.orderPriority)){
				var orderPriorityObject = form1.getObject("orderPriority");
				orderPriorityObject.attr("readonly", "readonly");
			}
			
			form1.objectToForm(orderPriority);
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
			
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			
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
	ruizhi.order.orderPriorityDetail.init()
});