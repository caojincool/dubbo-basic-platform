//////////////////////////////////////////
//单据作废页面
ruizhi.Package("ruizhi.order");

ruizhi.order.removeOrder = function() {
	
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/order/funcPage/orderMonitor/removeOrder.do';//当前窗口的URL
	var _UserDetail = ruizhi.GetUser();
	
	return{
		//初始化
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var removeOrderIbean = paramObj.removeOrderIbean;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(orderType));
			
			form1 = new ruizhi.FormExt("order-removeOrder-form1");
			
			form1.objectToForm(removeOrderIbean);
		},	
		
		//提交到上一页面的回调方法
		doSubmit : function() {
			//验证表单必填项
			if(!form1.validate()){
				return;
			}

			var valueObj = form1.formToObject();// 整个表单的值
			
			if(ruizhi.IsNull(valueObj.orderId)){
				ruizhi.Alert("单据id为空！");
				return ;
			}
			if(ruizhi.IsNull(_UserDetail.userId) || ruizhi.IsNull(_UserDetail.userText)){
				ruizhi.Alert("当前账号为空！");
				return ;
			}else{
				valueObj.operUserId = _UserDetail.userId;//创建人
				valueObj.operUserText = _UserDetail.userText;//创建人名称
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
	ruizhi.order.removeOrder.init()
});