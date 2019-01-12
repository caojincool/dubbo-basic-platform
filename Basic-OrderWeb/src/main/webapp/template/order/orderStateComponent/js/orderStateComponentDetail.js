//////////////////////////////////////////
//单据类型详情页面
ruizhi.Package("ruizhi.order");

ruizhi.order.orderStateComponentDetail = function() {
	
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/order/funcPage/orderStateComponent/orderStateComponentDetail.do';//当前窗口的URL
	var pageDateType = null;//新增：CREATE，编辑：UPDATE
	var _UserDetail = ruizhi.GetUser();
	
	return{
		//初始化
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			var orderStateComponentDetail = paramObj.orderStateComponent;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(orderState));
			pageDateType = orderStateComponentDetail.pageDateType;
			
			form1 = new ruizhi.FormExt("order-orderStateComponentDetail-form1");
			
//			组织标识不允许修改
//			if(!ruizhi.IsNull(orderStateComponentDetail) && !ruizhi.IsNull(orderStateComponentDetail.componentId)){
//				var orderStateObject = form1.getObject("componentId");
//				orderStateObject.attr("readonly", "readonly");
//			}
//			ruizhi.ToJson(orderStateObject);
			form1.objectToForm(orderStateComponentDetail);
		},	
		//单选状态
		selorderState: function(){
			var url = WEB_ROOT + '/order/funcPage/orderState/orderStateDialog.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.submitParam = null;//提交到后台的参数
			paramObj.title = "单选状态";
			var submitFn = ruizhi.order.orderStateComponentDetail.selorderStateComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selorderStateComeBack: function(param){
			form1.setValue('orderState', param.orderState);
			form1.setValue('orderStateName', param.orderStateName);
//			if(!ruizhi.IsNull(param) && param.length >0){
//				alert(param[0].orderState);
//				form1.setValue('orderState', param[0].orderState);
//				form1.setValue('orderStateName', param[0].orderStateName);
//			}
		},
		//提交到上一页面的回调方法
		doSubmit : function() {
			//验证表单必填项
			if(!form1.validate()){
				return;
			}

			var valueObj = form1.formToObject();// 整个表单的值
			
			if(!ruizhi.IsNull(pageDateType) && pageDateType == 'CREATE'){//新增
//			if(ruizhi.IsNull(valueObj.orderState)){//新增
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
	ruizhi.order.orderStateComponentDetail.init()
});