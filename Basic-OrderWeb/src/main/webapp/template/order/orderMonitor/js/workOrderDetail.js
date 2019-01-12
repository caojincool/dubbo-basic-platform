//////////////////////////////////////////
//单据详情页面
ruizhi.Package("ruizhi.order");

ruizhi.order.workOrderDetail = function() {
	
	var tab1;
	var form1 = null;
	var form2 = null;
	var grid1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/order/funcPage/orderMonitor/workOrderDetail.do';//当前窗口的URL
	var _UserDetail = ruizhi.GetUser();
	
	return{
		//初始化
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var workOrder = paramObj.workOrder;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(orderType));
			
			tab1 = new ruizhi.Tab('order-workOrderDetail-tab1');
			
			form1 = new ruizhi.FormExt("order-workOrderDetail-form1");
			form2 = new ruizhi.FormExt("order-workOrderDetail-form2");
			
			if(!ruizhi.IsNull(workOrder) && !ruizhi.IsNull(workOrder.orderId) && !ruizhi.IsNull(workOrder.workOrderId)){
				
				//单据详情
				var orderUrl = WEB_ROOT + '/order/func/orderMonitor/qryOrderById.do';
				var orderParam = {};
				orderParam.orderId = workOrder.orderId;
				var orderDetail = ruizhi.InvokeMethod(orderUrl, orderParam);
				form1.objectToForm(orderDetail);
				
				//任务详情
				var workOrderUrl = WEB_ROOT + '/order/func/orderMonitor/qryWorkOrderById.do';
				var workOrderParam = {};
				workOrderParam.workOrderId = workOrder.workOrderId;
				var workOrderDetail = ruizhi.InvokeMethod(workOrderUrl, workOrderParam);
				
				var rowObject = {};
				rowObject.partyType = workOrderDetail.partyType;
				var partyTypeName = ruizhi.order.workOrderDetail.partyTypeTrans(null, null, rowObject);
				workOrderDetail.partyTypeName = partyTypeName;
				
				form2.objectToForm(workOrderDetail);
				
				
				//任务操作记录
				grid1 = new ruizhi.DataGrid("order-workOrderDetail-grid1",{
					postData : {
						workOrderId : workOrder.workOrderId
					},
					url : WEB_ROOT + '/order/func/orderMonitor/qryWorkOrderOperByWorkOrderId.do'
				});
				
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
			var talbeCode = "OF_WORK_ORDER_OPER";
			var colCode = "OPER_TYPE";
			var colValue = rowObject.operType;
			var transName = ruizhi.TableDictTrans(talbeCode,colCode,colValue);
			return transName;
		},
		
		//字典表翻译字段值
		partyTypeTrans:function(cellvalue, options, rowObject){
			var talbeCode = "PF_WO_DISP_RULE_INST";
			var colCode = "PARTY_TYPE";
			var colValue = rowObject.partyType;
			var transName = ruizhi.TableDictTrans(talbeCode,colCode,colValue);
			return transName;
		},
		
	}
}();

//初始化
ruizhi.ExecWait(function(){
	ruizhi.order.workOrderDetail.init()
});