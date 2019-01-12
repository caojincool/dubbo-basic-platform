//////////////////////////////////////////
//单据详情页面
ruizhi.Package("ruizhi.order");

ruizhi.order.orderDetail = function() {
	
	var tab1;
	var form1 = null;
	var grid1 = null;
	var grid2 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/order/funcPage/orderMonitor/orderDetail.do';//当前窗口的URL
	var _UserDetail = ruizhi.GetUser();
	
	return{
		//初始化
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var order = paramObj.order;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(orderType));
			
			tab1 = new ruizhi.Tab('order-orderDetail-tab1');
			
			form1 = new ruizhi.FormExt("order-orderDetail-form1");
			
			if(!ruizhi.IsNull(order) && !ruizhi.IsNull(order.orderId)){
				
				var orderUrl = WEB_ROOT + '/order/func/orderMonitor/qryOrderById.do';
				var orderParam = {};
				orderParam.orderId = order.orderId;
				ruizhi.InvokeMethodAsyn(orderUrl, orderParam, ruizhi.order.orderDetail.orderDetailComeBack);
				
				//任务
				grid1 = new ruizhi.DataGrid("order-orderDetail-grid1",{
					postData : {
						orderId : order.orderId
					},
					url : WEB_ROOT + '/order/func/orderMonitor/qryWorkOrdersByOrderId.do'
				});
				
				//操作记录
				grid2 = new ruizhi.DataGrid("order-orderDetail-grid2",{
					postData : {
						orderId : order.orderId
					},
					url : WEB_ROOT + '/order/func/orderMonitor/qryOrderOperByOrderId.do'
				});
				
			}
		},	
		
		orderDetailComeBack : function(orderDetail) {
			if(!ruizhi.IsNull(orderDetail)){
				form1.objectToForm(orderDetail);
			}
		},	
		
//		//提交到上一页面的回调方法
//		doSubmit : function() {
//			//验证表单必填项
//			if(!form1.validate()){
//				return;
//			}
//
//			var valueObj = form1.formToObject();// 整个表单的值
//			
//			if(ruizhi.IsNull(valueObj.orderId)){
//				ruizhi.Alert("单据id为空！");
//				return ;
//			}
//			if(ruizhi.IsNull(_UserDetail.userId) || ruizhi.IsNull(_UserDetail.userText)){
//				ruizhi.Alert("当前账号为空！");
//				return ;
//			}else{
//				valueObj.operUserId = _UserDetail.userId;//创建人
//				valueObj.operUserText = _UserDetail.userText;//创建人名称
//			}
//			
//			
//			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
//			
//			var param = valueObj;
//			//alert("整个提交数据:"+ruizhi.ToJson(param));
//			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
//			
//		},
		
		//关闭窗口
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},

		//字典表翻译字段值
		operTypeTrans:function(cellvalue, options, rowObject){
			var talbeCode = "OF_ORDER_OPER";
			var colCode = "OPER_TYPE";
			var colValue = rowObject.operType;
			var transName = ruizhi.TableDictTrans(talbeCode,colCode,colValue);
			return transName;
		},
		
		
		//单据任务详情
		workOrderDetail : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/orderMonitor/workOrderDetail.do';
			var width = null;
			var heigth = null;
//			var submitFn = ruizhi.order.orderMonitor.orderDetailComeBack;//模态窗口提交后回调方法
			var submitFn = null;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			var ids = grid1.getCheckedIds();
			if(!ruizhi.IsNull(ids)){
				if(ids.length == 0){
					ruizhi.Alert('请选择');
					return false;
				}else if(ids.length > 1){
					ruizhi.Alert('不能选择多条数据');
					return false;
				}
			}
			var selItem = grid1.getSelectedItem();//所选行
			if(!ruizhi.IsNull(selItem)){
				paramObj.workOrder = selItem;
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
			
		},
		
	}
}();

//初始化
ruizhi.ExecWait(function(){
	ruizhi.order.orderDetail.init()
});