//////////////////////////////////////////
//单据类型
ruizhi.Package("ruizhi.order");

ruizhi.order.orderMonitor = function() {

	var tab1;
	var tab2;
	var tab3;
	var grid1;
	var grid2;
	var form1;
	var _UserDetail = ruizhi.GetUser();
	
	return {
		//初始化
		init : function() {

			tab1 = new ruizhi.Tab('order-orderMonitor-tab1');
			tab2 = new ruizhi.Tab('order-orderMonitor-tab2');
			tab3 = new ruizhi.Tab('order-orderMonitor-tab3');
			
			grid1 = new ruizhi.DataGrid("order-orderMonitor-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/order/func/orderMonitor/pagin.do'
			});
			grid2 = new ruizhi.DataGrid("order-orderMonitor-grid2");
			form1 = new ruizhi.FormExt("order-orderMonitor-form1");
		},
		
		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/order/func/orderMonitor/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
		//任务列表加载数据
		workOrderLoadData : function(orderId) {
			if(!ruizhi.IsNull(orderId)){
				var url = WEB_ROOT + '/order/func/orderMonitor/qryWorkOrdersByOrderId.do';
				var paramObj = {};
				paramObj.orderId = orderId;
				grid2.loadData(url, paramObj);
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
			var submitFn = ruizhi.order.orderMonitor.selAreaComeBack;//模态窗口提交后回调方法
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
		
		//单选部门
		selOrg : function(){
			var url = WEB_ROOT + '/oaas/funcPage/org/selOrg.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
//			paramObj.submitParam = null;//提交到后台的参数
			paramObj.orgType = "ALLORG";//查询全部部门
			paramObj.title = "单选部门窗口";
			var submitFn = ruizhi.order.orderMonitor.selOrgComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selOrgComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param)){
				form1.setValue('createOrgId', param[0].orgId);
				form1.setValue('createOrgName', param[0].orgName);
			}
		},
		
		//创建单据
		orderCreate : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/orderMonitor/createOrder.do';
			var width = null;
			var heigth = null;
			var createOrderIbean = {};
			createOrderIbean.pageDateType = 'CREATE';
			paramObj.createOrderIbean = createOrderIbean;
			var submitFn = ruizhi.order.orderMonitor.orderCreateComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		//创建单据回调函数
		orderCreateComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/order/func/orderMonitor/createOrder.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.orderMonitor.loadData);
			//ruizhi.order.orderMonitor.loadData();
		},
		
		//启动流程
		processStart : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/orderMonitor/processStart.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.order.orderMonitor.processStartComeBack;//模态窗口提交后回调方法
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
				paramObj.processStartIbean = selItem;
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
			
		},
		
		processStartComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/order/func/orderMonitor/processStart.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.orderMonitor.loadData);
			//ruizhi.order.orderMonitor.loadData();
		},
		
		//流程重置
		processReset : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/orderMonitor/processReset.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.order.orderMonitor.processResetComeBack;//模态窗口提交后回调方法
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
				paramObj.processResetIbean = selItem;
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
			
		},
		
		processResetComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/order/func/orderMonitor/processReset.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.orderMonitor.loadData);
			//ruizhi.order.orderMonitor.loadData();
		},
		
		//流程跳转
		processJump : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/orderMonitor/processJump.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.order.orderMonitor.processJumpComeBack;//模态窗口提交后回调方法
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
				paramObj.processJumpIbean = selItem;
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
			
		},
		
		processJumpComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/order/func/orderMonitor/processJump.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.orderMonitor.loadData);
			//ruizhi.order.orderMonitor.loadData();
		},
		
		//单据作废
		orderDel : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/orderMonitor/removeOrder.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.order.orderMonitor.orderDelComeBack;//模态窗口提交后回调方法
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
				paramObj.removeOrderIbean = selItem;
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
			
		},
		
		orderDelComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/order/func/orderMonitor/removeOrder.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.orderMonitor.loadData);
			//ruizhi.order.orderMonitor.loadData();
		},
		
		//单据详情
		orderDetail : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/orderMonitor/orderDetail.do';
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
				paramObj.order = selItem;
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
			
		},
		
		//单击，查询该单据下的任务
		orderItemClick : function(rowId) {
			var item = grid1.getItemByRowId(rowId);

			//加载单据按钮
			if(!ruizhi.IsNull(item) && !ruizhi.IsNull(item.orderType)){
				ruizhi.order.orderMonitor.loadOrderButton(item.orderType);
			}
			
			//加载任务
			if(!ruizhi.IsNull(item) && !ruizhi.IsNull(item.orderId)){
//				var url = WEB_ROOT + '/order/func/orderMonitor/qryWorkOrdersByOrderId.do';
//				var paramObj = {};
//				paramObj.orderId = item.orderId;
//				grid1.loadData(url, paramObj);
				ruizhi.order.orderMonitor.workOrderLoadData(item.orderId);
			}
		},
		
		//字典表翻译字段值
		partyTypeTrans:function(cellvalue, options, rowObject){
			var talbeCode = "PF_WO_DISP_RULE_INST";
			var colCode = "PARTY_TYPE";
			var colValue = rowObject.partyType;
			var transName = ruizhi.TableDictTrans(talbeCode,colCode,colValue);
			return transName;
		},
		
		
		//任务回单
		workOrderFinish : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/orderMonitor/workOrderFinish.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.order.orderMonitor.workOrderFinishComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			var ids = grid2.getCheckedIds();
			if(!ruizhi.IsNull(ids)){
				if(ids.length == 0){
					ruizhi.Alert('请选择');
					return false;
				}else if(ids.length > 1){
					ruizhi.Alert('不能选择多条数据');
					return false;
				}
			}
			var selItem = grid2.getSelectedItem();//所选行
			if(!ruizhi.IsNull(selItem)){
				paramObj.workOrderFinish = selItem;
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
			
		},
		
		workOrderFinishComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/order/func/orderMonitor/workOrderFinish.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.orderMonitor.orderItemClick);
			//ruizhi.order.orderMonitor.orderItemClick();
		},
		
		//任务转派
		workOrderDisp : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/orderMonitor/workOrderDisp.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.order.orderMonitor.workOrderDispComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			var ids = grid2.getCheckedIds();
			if(!ruizhi.IsNull(ids)){
				if(ids.length == 0){
					ruizhi.Alert('请选择');
					return false;
				}else if(ids.length > 1){
					ruizhi.Alert('不能选择多条数据');
					return false;
				}
			}
			var selItem = grid2.getSelectedItem();//所选行
			if(!ruizhi.IsNull(selItem)){
				paramObj.workOrderDisp = selItem;
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
			
		},
		
		workOrderDispComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/order/func/orderMonitor/workOrderDisp.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.orderMonitor.orderItemClick);
			//ruizhi.order.orderMonitor.orderItemClick();
		},
		
		//单选父单据
		selOrder : function(){
			var url = WEB_ROOT + '/order/funcPage/orderMonitor/selOrder.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.submitParam = null;//提交到后台的参数
			paramObj.title = "单选单据";
			var submitFn = ruizhi.order.createOrder.selOrderComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selOrderComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param)){
				form1.setValue('parentOrderId', param.orderId);
				form1.setValue('parentOrderName', param.orderName);
			}
		},
		
		loadOrderButton :function(orderType){
//			debugger
			if(!ruizhi.IsNull(orderType)){
				var qryUrl = WEB_ROOT + '/order/func/orderMonitor/qryOrderButtonList.do';
				var qryParam = {};
				qryParam.orderType = orderType;
				ruizhi.InvokeMethodAsyn(qryUrl, qryParam, ruizhi.order.orderMonitor.buttonsComeBack);

			}
		},
		
		buttonsComeBack :function(buttons){
			if(!ruizhi.IsNull(buttons) && buttons.length > 0){
				//清空按钮
				var buttonObj = ruizhi.GetObj("order-orderMonitor-orderButton");
				buttonObj.empty();
//				$("#order-orderMonitor-orderButton").empty();
				for(var i in buttons){
					var item = buttons[i];
					if(ruizhi.IsNull(item.privateCode)){//权限编码为空代表可直接展示按钮
						//加载js文件
						//TODO 直接在页面加载js，不从数据库加载了
						if(!ruizhi.IsNull(item.jsFile)){
//							document.write("<script src='"+item.jsFile+"'><\/script>"); 
//							document.write('<script type="text/javascript" src="'+WEB_ROOT+item.jsFile+'"></script>');
//							$.getScript(item.jsFile,function(){  //加载test.js,成功后，并执行回调函数
//								  console.log("加载js文件");
//								});
//							$.getScript("template/order/orderMonitor/js/orderMonitor.js");  //加载js文件
						}
						//加载js方法
						var buttonHtml = '<li><a href="javascript:'+item.jsMethod+'"><i class="icon-plus2 position-left"></i>'+item.buttonName+'</a></li>'
//						$("#order-orderMonitor-orderButton").append(buttonHtml);
						buttonObj.append(buttonHtml);
					}else{//权限编码不为空代表要匹配该用户是否有这个权限
						if(ruizhi.HasPriv(item.privateCode)){//有该权限
							//加载js文件
							if(!ruizhi.IsNull(item.jsFile)){
								document.write("<script src='"+item.jsFile+"'><\/script>"); 
							}
							//加载js方法
							var buttonHtml = '<li><a href="javascript:'+item.jsMethod+'"><i class="icon-plus2 position-left"></i>'+item.buttonName+'</a></li>'
//							$("#order-orderMonitor-orderButton").append(buttonHtml);
							buttonObj.append(buttonHtml);
						}else{//没有该权限
							
						}
					}
				}
			}
		},
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.order.orderMonitor.init();
});