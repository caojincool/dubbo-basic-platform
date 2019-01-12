//////////////////////////////////////////
//待办任务
ruizhi.Package("ruizhi.order");

ruizhi.order.partyWorkOrder = function() {

	var grid1;
	var form1;
	var _UserDetail = ruizhi.GetUser();
	
	return {
		//初始化
		init : function() {

			var staffId = _UserDetail.staffId;
			var userId = _UserDetail.userId;
			var jobId = _UserDetail.jobId;
			var orgId = _UserDetail.orgId;
			grid1 = new ruizhi.DataGrid("order-partyWorkOrder-grid1",{
				postData : {
					staffId : staffId,
					userId : userId,
					jobId : jobId,
					orgId : orgId
				},
				url : WEB_ROOT + '/order/func/workOrder/qryPartyWorkOrder.do'
			});
			form1 = new ruizhi.FormExt("order-partyWorkOrder-form1");
		},
		
		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/order/func/workOrder/qryPartyWorkOrder.do';
			var paramObj = form1.formToObject();
			var staffId = _UserDetail.staffId;
			var userId = _UserDetail.userId;
			var jobId = _UserDetail.jobId;
			var orgId = _UserDetail.orgId;
			paramObj.staffId = staffId;
			paramObj.userId = userId;
			paramObj.jobId = jobId;
			paramObj.orgId = orgId;
			grid1.loadData(url, paramObj);
		},

		//字典表翻译字段值
		partyTypeTrans:function(cellvalue, options, rowObject){
			var talbeCode = "PF_WO_DISP_RULE_INST";
			var colCode = "PARTY_TYPE";
			var colValue = rowObject.partyType;
			var transName = ruizhi.TableDictTrans(talbeCode,colCode,colValue);
			return transName;
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
		
		//任务回单
		workOrderFinish : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/orderMonitor/workOrderFinish.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.order.partyWorkOrder.workOrderFinishComeBack;//模态窗口提交后回调方法
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
			ruizhi.InvokeMethodAsyn(url,params, ruizhi.order.partyWorkOrder.loadData);
			//ruizhi.order.partyWorkOrder.loadData();
		},
		
		//任务转派
		workOrderDisp : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/orderMonitor/workOrderDisp.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.order.partyWorkOrder.workOrderDispComeBack;//模态窗口提交后回调方法
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
			ruizhi.InvokeMethodAsyn(url,params, ruizhi.order.partyWorkOrder.loadData);
			//ruizhi.order.partyWorkOrder.loadData();
		},
		
		//任务提单
		workOrderGet : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/orderMonitor/workOrderGet.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.order.partyWorkOrder.workOrderGetComeBack;//模态窗口提交后回调方法
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
				paramObj.workOrderGet = selItem;
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
			
		},
		
		workOrderGetComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/order/func/orderMonitor/workOrderGet.do";
			ruizhi.InvokeMethodAsyn(url,params, ruizhi.order.partyWorkOrder.loadData);
			//ruizhi.order.partyWorkOrder.loadData();
		},
		
		//单击
		itemClick : function(rowId) {
			var item = grid1.getItemByRowId(rowId);

			//加载任务按钮
			if(!ruizhi.IsNull(item) && !ruizhi.IsNull(item.tacheId)){
				ruizhi.order.partyWorkOrder.loadWoButton(item.tacheId);
			}
			
		},
		
		loadWoButton :function(tacheId){
//			debugger
			if(!ruizhi.IsNull(tacheId)){
				var qryUrl = WEB_ROOT + '/order/func/orderMonitor/qryWoButtonList.do';
				var qryParam = {};
				qryParam.tacheId = tacheId;
				var buttons = ruizhi.InvokeMethod(qryUrl, qryParam);
				if(!ruizhi.IsNull(buttons) && buttons.length > 0){
					//清空按钮
					var buttonObj = ruizhi.GetObj("order-partyWorkOrder-woButton");
					buttonObj.empty();
//					$("#order-orderMonitor-orderButton").empty();
					for(var i in buttons){
						var item = buttons[i];
						if(ruizhi.IsNull(item.privateCode)){//权限编码为空代表可直接展示按钮
							//加载js文件
							//TODO 直接在页面加载js，不从数据库加载了
							if(!ruizhi.IsNull(item.jsFile)){
//								document.write("<script src='"+item.jsFile+"'><\/script>"); 
//								document.write('<script type="text/javascript" src="'+WEB_ROOT+item.jsFile+'"></script>');
//								$.getScript(item.jsFile,function(){  //加载test.js,成功后，并执行回调函数
//									  console.log("加载js文件");
//									});
//								$.getScript("template/order/orderMonitor/js/orderMonitor.js");  //加载js文件
							}
							//加载js方法
							var buttonHtml = '<li><a href="javascript:'+item.jsMethod+'"><i class="icon-plus2 position-left"></i>'+item.buttonName+'</a></li>'
//							$("#order-orderMonitor-orderButton").append(buttonHtml);
							buttonObj.append(buttonHtml);
						}else{//权限编码不为空代表要匹配该用户是否有这个权限
							if(ruizhi.HasPriv(item.privateCode)){//有该权限
								//加载js文件
								if(!ruizhi.IsNull(item.jsFile)){
									document.write("<script src='"+item.jsFile+"'><\/script>"); 
								}
								//加载js方法
								var buttonHtml = '<li><a href="javascript:'+item.jsMethod+'"><i class="icon-plus2 position-left"></i>'+item.buttonName+'</a></li>'
//								$("#order-orderMonitor-orderButton").append(buttonHtml);
								buttonObj.append(buttonHtml);
							}else{//没有该权限
								
							}
						}
					}
				}
			}
		},
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.order.partyWorkOrder.init();
});