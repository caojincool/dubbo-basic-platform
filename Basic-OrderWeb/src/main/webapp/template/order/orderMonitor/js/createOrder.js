//////////////////////////////////////////
//创建单据页面
ruizhi.Package("ruizhi.order");

ruizhi.order.createOrder = function() {
	
	var tab1 = null;
	var grid1 = null;
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/order/funcPage/orderMonitor/createOrder.do';//当前窗口的URL
	var pageDateType = null;//新增：CREATE，编辑：UPDATE
	var _UserDetail = ruizhi.GetUser();
	
	return{
		//初始化
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var createOrderIbean = paramObj.createOrderIbean;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(createOrderIbean));
			pageDateType = createOrderIbean.pageDateType;
			
			tab1 = new ruizhi.Tab('order-createOrder-tab1');
			form1 = new ruizhi.FormExt("order-createOrder-form1");
			grid1 = new ruizhi.DataGrid("order-createOrder-grid1");
			
			form1.objectToForm(createOrderIbean);
		},	
		
		//提交到上一页面的回调方法
		doSubmit : function() {
			//验证表单必填项
			if(!form1.validate()){
				return;
			}

			var valueObj = form1.formToObject();// 整个表单的值
			
			if(ruizhi.IsNull(valueObj.serviceCode)){
				ruizhi.Alert("请选择服务编码！");
				return ;
			}
			if(ruizhi.IsNull(valueObj.orderPriority)){
				ruizhi.Alert("请选择优先级！");
				return ;
			}
			if(ruizhi.IsNull(valueObj.orderType)){
				ruizhi.Alert("请选择单据类型！");
				return ;
			}
			if(ruizhi.IsNull(valueObj.areaId)){
				ruizhi.Alert("请选择区域！");
				return ;
			}
			if(ruizhi.IsNull(_UserDetail.userId) || ruizhi.IsNull(_UserDetail.userText)){
				ruizhi.Alert("当前账号为空！");
				return ;
			}
			if(ruizhi.IsNull(_UserDetail.orgId) || ruizhi.IsNull(_UserDetail.orgName)){
				ruizhi.Alert("当前账号没有部门，请先完善！");
				return ;
			}
			
			if(!ruizhi.IsNull(pageDateType) && pageDateType == 'CREATE'){//新增
//			if(ruizhi.IsNull(valueObj.orderType)){//新增
				valueObj.createUserId = _UserDetail.userId;//创建人
				valueObj.createUserText = _UserDetail.userText;//创建人名称
				valueObj.createOrgId = _UserDetail.orgId;//创建人默认组织
				valueObj.createOrgName = _UserDetail.orgName;//创建人默认组织名称
			}else{//编辑
				valueObj.modifyUserId = _UserDetail.userId;//修改人
			}
			
			//流程关注人
			var orderFollowUserList = grid1.getAllItems();
			valueObj.orderFollowUserList = orderFollowUserList;
			
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			
			var param = valueObj;
			//alert("整个提交数据:"+ruizhi.ToJson(param));
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			
		},
		
		//关闭窗口
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		
		//单选服务
		selOrderService : function(){
			var url = WEB_ROOT + '/order/funcPage/orderService/selOrderService.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.submitParam = null;//提交到后台的参数
			paramObj.title = "单选服务窗口";
			var submitFn = ruizhi.order.createOrder.selOrderServiceComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selOrderServiceComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param)){
				form1.setValue('serviceId', param.serviceId);
				form1.setValue('serviceCode', param.serviceCode);
			}
		},
		
		//单选优先级
		selOrderPriority : function(){
			var url = WEB_ROOT + '/order/funcPage/orderPriority/selOrderPriority.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.submitParam = null;//提交到后台的参数
			paramObj.title = "单选优先级窗口";
			var submitFn = ruizhi.order.createOrder.selOrderPriorityComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selOrderPriorityComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param)){
				form1.setValue('orderPriority', param.orderPriority);
				form1.setValue('orderPriorityName', param.orderPriorityName);
			}
		},
		
		//单选单据类型
		selOrderType : function(){
			var url = WEB_ROOT + '/order/funcPage/orderType/selOrderType.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.submitParam = null;//提交到后台的参数
			paramObj.title = "单选单据类型窗口";
			var submitFn = ruizhi.order.createOrder.selOrderTypeComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selOrderTypeComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param)){
				form1.setValue('orderType', param.orderType);
				form1.setValue('orderTypeName', param.orderTypeName);
			}
		},
		
		//单选单据
		selOrder : function(){
			var url = WEB_ROOT + '/order/funcPage/orderMonitor/selOrder.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.submitParam = null;//提交到后台的参数
			paramObj.title = "单选单据窗口";
			var submitFn = ruizhi.order.createOrder.selOrderComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selOrderComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param)){
				form1.setValue('parentOrderId', param.orderId);
				form1.setValue('parentOrderId', param.orderName);
			}
		},
		
		//单选区域
		selArea : function(){
			var url = WEB_ROOT + '/oaas/funcPage/area/selArea.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
//			paramObj.submitParam = null;//提交到后台的参数
			paramObj.areaType = "ALLAREA";//查询全部区域
			paramObj.title = "单选区域窗口";
			var submitFn = ruizhi.order.createOrder.selAreaComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selAreaComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param)){
				form1.setValue('areaId', param[0].areaId);
				form1.setValue('areaName', param[0].areaName);
			}
		},
		
		//流程关注人
		orderFollowUserCreate : function(){
			var url = WEB_ROOT + '/oaas/funcPage/user/selUser.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
//			paramObj.submitParam = null;//提交到后台的参数
			paramObj.title = "单选用户窗口";
			var submitFn = ruizhi.order.createOrder.orderFollowUserCreateComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		orderFollowUserCreateComeBack :function(param){
			var item = param;
			var flag = grid1.isExist(item.userId);
			if(!flag){
				grid1.addItem(item.userId,item);
			}else{
				ruizhi.Alert('数据已存在');
			}
//			var items = param;
//			alert(ruizhi.ToJson(items));
//			if(!ruizhi.IsNull(items) && items.length > 0){
//				for(var i in items){
//					var item = items[i];
////					item.userId = item.userId;
//					var flag = grid1.isExist(item.userId);
//					if(!flag){
//						grid1.addItem(item.userId,item);
//					}else{
//						ruizhi.Alert('数据已存在');
//					}
//				}
//			}
		},
		
		orderFollowUserDel : function(){
			var ids = grid1.getCheckedIds();
			if(!ruizhi.IsNull(ids) && ids.length > 0){
				for(var i in ids){
					var id = ids[i];
					grid1.removeItem(id);
				}
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
	}
}();

//初始化
ruizhi.ExecWait(function(){
	ruizhi.order.createOrder.init()
});