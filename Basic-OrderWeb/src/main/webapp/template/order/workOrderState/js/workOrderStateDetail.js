//////////////////////////////////////////
//单据类型详情页面
ruizhi.Package("ruizhi.order");

ruizhi.order.workOrderStateDetail = function() {
	
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/order/funcPage/workOrderState/workOrderStateDetail.do';//当前窗口的URL
	var pageDateType = null;//新增：CREATE，编辑：UPDATE
	var _UserDetail = ruizhi.GetUser();
	
	return{
		//初始化
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var workOrderState = paramObj.workOrderState;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(workOrderState));
			pageDateType = workOrderState.pageDateType;
			
			form1 = new ruizhi.FormExt("order-workOrderStateDetail-form1");
			
			//编码不允许修改
			if(!ruizhi.IsNull(workOrderState) && !ruizhi.IsNull(workOrderState.workOrderState)){
				var workOrderStateObject = form1.getObject("workOrderState");
				workOrderStateObject.attr("readonly", "readonly");
			}
			
			form1.objectToForm(workOrderState);
		},	
		
		//提交到上一页面的回调方法
		doSubmit : function() {
			//验证表单必填项
			if(!form1.validate()){
				return;
			}

			var valueObj = form1.formToObject();// 整个表单的值
			
			if(!ruizhi.IsNull(pageDateType) && pageDateType == 'CREATE'){//新增
//			if(ruizhi.IsNull(valueObj.workOrderState)){//新增
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
	ruizhi.order.workOrderStateDetail.init()
});