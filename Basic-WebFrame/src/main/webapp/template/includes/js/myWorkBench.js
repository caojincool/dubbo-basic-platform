//////////////////////////////////////////
//RuizhiSoft corp. 2017-6-28 17:59:25
//Author :zheng.zhijie
//commits:主页
//////////////////////////////////////////

//类定义
ruizhi.Package("ruizhi.includes");

ruizhi.includes.myWorkBench = function() {
	
	//私有成员
	var _this = this;
	var session = null;
	var myOrder;
	var myApprove;
	var groupClientTotal;
	var serviceCode;
	return{
		/* 初始化 */
		init : function() {
			// debugger;
			session = ruizhi.GetSession();
			
			
			/**
			 * 获取用户详细信息
			 */
			if(!ruizhi.IsNull(session)){
				var userId = session.userId;
			
				var paramObj = {};
				paramObj.userId = userId;
				var url = WEB_ROOT +"/sysIndex/getIndex.do"
				ruizhi.InvokeMethodAsyn(url,paramObj, function(msg){
					// debugger;
					if(msg!=null){
						var myOrderCount = msg.noFinish;
						var clientCount = msg.clientCount;
						var pending = msg.noDeal;
						$("#index-clientCount").text(clientCount);
						$("#index-myOrder").text(myOrderCount);
						$("#index-pending").text(pending);
					}
					// debugger;
					//我的申请Table
					myOrder = new ruizhi.DataGrid("index-myOrder-grid",{
						postData : {
							userId  : userId
						},
						url : WEB_ROOT + '/sysIndex/func/index/getMyOrder.do',shrinkToFit:false
					});
					//我的审批table
					myApprove = new ruizhi.DataGrid("index-myApprove-grid",{
						postData : {
							userId  : userId
						},
						url : WEB_ROOT + '/sysIndex/func/index/getMyTask.do',shrinkToFit:false
					});
					
					//客户table
					groupClientTotal = new ruizhi.DataGrid("index-groupClientTotal-grid",{
						postData : {
							page : 1,
							limit : 10
						},
						url : WEB_ROOT + '/sysIndex/func/index/pmClientInfoList.do',shrinkToFit:false
					});
				});
				
			}
			
			
		},
		// 加载数据
		loadData : function() {// 加载数据
			debugger;
			var paramObj = {};
			var userId = session.userId;
			paramObj.userId = userId;
			var url = WEB_ROOT + '/sysIndex/func/index/getMyTask.do';
			myApprove.loadData(url, paramObj);
		},
		//我的申请查看按钮
		myOrderStyle:function(cellValue,options,rowObject){
			return "<a href='javascript:void(0);' onclick='ruizhi.includes.myWorkBench.orderClick(\""+rowObject.orderId+"/"+rowObject.serviceCode+"\")' style='color:blue' >查看</a>";
		},
		//我的审批查看按钮
		myApproveStyle:function(cellValue,options,rowObject){
			
			return "<a href='javascript:void(0);' onclick='ruizhi.includes.myWorkBench.myTaskClick(\""+rowObject.orderId+"/"+rowObject.serviceCode+"/"+rowObject.tacheCode+"/"+rowObject.workOrderId+"\")' style='color:blue' >查看</a>";
		},
		//客户查看按钮
		groupClientTotalStyle:function(cellValue,options,rowObject){
			
			return "<a href='javascript:void(0);' onclick='ruizhi.includes.myWorkBench.pmClick("+rowObject.clientId+")' style='color:blue' >查看</a>";
		},
		//
		taskList : function(){
			debugger;
			var paramObj = {};//传给模态窗口的参数
			var url =  'orderManager/menuPage/taskList.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.includes.myWorkBench.taskListComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-sm";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		taskListComeBack : function(){
			
		},
		//我的申请查看事件
		myTaskClick : function(obj){
			debugger;
			var arr = obj.split("/"); 
			var orderId = arr[0];
			serviceCode = arr[1];
			var tacheCode = arr[2];
			var workOrderId = arr[3];
			ruizhi.includes.myWorkBench.approveTask(orderId,serviceCode,tacheCode,workOrderId);
		},
		//客户点击事件
		pmClick : function(obj){
			debugger;
			var url = WEB_ROOT + '/basicdata/func/groupClient/qryPage.do';
			var paramObj = {};
			paramObj.clientId = obj;
			ruizhi.InvokeMethodAsyn(url,paramObj, function(msg){
				debugger;
				if(msg!=null){
					var paramObj = {};//传给模态窗口的参数
					var url = WEB_ROOT + '/basicdata/funcPage/groupClientDoubleInfo.do';
					var width = null;
					var heigth = null;
					var groupClientInfo = {};
					groupClientInfo.pageDateType = 'doubleInfo';
					groupClientInfo= msg[0];
					paramObj.pmClientInfo = groupClientInfo;
					var submitFn = ruizhi.includes.myWorkBench.taskListComeBack;//模态窗口提交后回调方法
					var modalClass = "modal-lg";
					ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
				}
			});
		},
		//我的申请双击
		myOrderOnItemClick : function(){
			var order = myOrder.getSelectedItem();//所选行
			var orderId = order.orderId;
			var serviceCode = order.serviceCode;
			
			ruizhi.includes.myWorkBench.seeDetail(orderId,serviceCode);
			
		},
		//我的审批双击
		myApproveOnItemClick : function(){
			var approve = myApprove.getSelectedItem();//所选行
			var orderId = approve.orderId;
			serviceCode = approve.serviceCode;
			var tacheCode = approve.tacheCode;
			var workOrderId = approve.workOrderId;
			ruizhi.includes.myWorkBench.approveTask(orderId,serviceCode,tacheCode,workOrderId);
			
		},
		//客商双击
		groupClientTotalOnItemClick : function(){
			var pmClient = groupClientTotal.getSelectedItem();//所选行
			var clientId = pmClient.clientId;
			ruizhi.includes.myWorkBench.pmClick(clientId);
		},
		
		redistReport : function(){
			debugger;
			ruizhi.OpenPage("./template/includes/navShow/report-man/report-nav.html");
		},
		//申请查看
		orderClick : function(obj){
			debugger;
			var arr = obj.split("/"); 
			var orderId = arr[0];
			var serviceCode = arr[1];
			
			ruizhi.includes.myWorkBench.seeDetail(orderId,serviceCode);
		},
		seeDetailComeBack : function(){
			
		},
		//报表点击
		reportClick : function(navName,men_co,NavHtml){
		
			  var navLi = '<li class="hidden actived"><span class="created daoHang" title="'+navName+'" men_co='+men_co+'>'+navName+'</span><i class="icon-cross3"></i></li>';
			  $("#second-nav-ul").find('li').each(function(){
			    if($(this).text() == navName){
			      navLi = '';
			    }
			  });

            $("#second-nav-ul li.actived").removeClass('actived');
			  $("#second-nav-ul").append(navLi);
			  var allwidth = 0;
			  $("#second-nav-ul").find('li').each(function(){
			    allwidth += $(this).width()+70;
			  });

			  if(NavHtml == undefined){
			    $("#second-nav-ul").find('li:last').remove();
			    // tips("暂没开发完成！");
			  }else{
			    if(allwidth >=10000){
			      tips("请先关闭一些标签页!");
			      $("#second-nav-ul").find('li:last').remove();
			    }else{
			      $("#second-nav-ul").find('li:last').removeClass('hidden');
			      $("#nav-show-ms").empty();
			      console.log("清空");
			      $("#navName").text(navName);
			      // getHtml(NavHtml);
			      // ruizhi.OpenPage(NavHtml);
                    var $clone = $('#mainContent').clone().attr('id', 'mainContent1');
                    $clone.insertAfter('#mainContent').hide();
                    $("#mainContent").load(NavHtml, function(){
                        $('.second-show').hide();
                        checkBtnUse();
                        $('#mainContent').attr('id', men_co).show().addClass(men_co);
                        $('#mainContent1').attr('id', 'mainContent').hide();
                    })
			    }
			  }
		},
		//报表更多
		moreReport : function(){
			//跳转到报表页面
			var firstNavName = "统计分析";
			var firstMenu_co = -100;
			var firstNavHtml = "./template/includes/navShow/report-man/report-nav.html";
			if(firstNavHtml == undefined){
				tips('没找到');
			}else{
				try {
					if($('[id='+firstMenu_co+']').length > 0){
						// $('.newContent').remove();
						$('#'+firstMenu_co).show().siblings('.second-show').hide();
						$('#second-nav-ul li[title='+firstNavName+']').addClass('actived').siblings().removeClass('actived')
					} else {
						if ($('#second-nav-ul li span.created').length > 0) {
							var navLi = '<li class="actived " title="'+firstNavName+'"><span title="'+firstNavName+'" class="created daoHang" men_co='+firstMenu_co+'>'+firstNavName+'</span><i class="icon-cross3"></i></li>';
							$("#second-nav-ul li.actived").removeClass('actived');
							$("#second-nav-ul").append(navLi);
						} else {
							$('#second-nav-ul li').addClass('actived')
							.attr('title', firstNavName)
							.find('span').attr('men_co', firstMenu_co).attr('title', firstNavName)
							.text(firstNavName).addClass('created daoHang')
						}
						fixTabSize();
						var $clone = $('#mainContent').clone().attr('id', 'mainContent1');
						$clone.insertAfter('#mainContent').hide();
						$("#mainContent").load(firstNavHtml, function(){
							$('.second-show').hide();
							checkBtnUse();
							$('#mainContent').attr('id', firstMenu_co).show().addClass(firstMenu_co);
							$('#mainContent1').attr('id', 'mainContent').hide();			
						})
						
					}
					
					// $this.find('img:last').show();
					// $this.siblings().find('img:last').hide();
					
					var secondnavName;
					if(firstNavName.indexOf('流程')>0){
						secondnavName = firstNavName+'导航';
					}else{
						secondnavName = firstNavName;
					}
					firstN = secondnavName;
					$("#navName").text(secondnavName);
					$('.navigation-first-link').removeClass('show-fist-link').parent(".showFirstnav-body").hide();
					$('#showFirstNav-btn').show();
					//getHtml(firstNavHtml);
	
				} catch (e) {
					console.error(e)
				}
			}	
		},
		
		workOrderFinishComeBack : function(param) {
			debugger;
			if (param != null) {
				//出库提单业务判断
				var params = {};
				var tacheCode = param.tacheCode;
				var paramObj = param;
				param = ruizhi.ToJson(param);
				params.params = param;
				
				if (paramObj.doBack != "doBack") {
					var url = WEB_ROOT
							+ "/order/func/orderMonitor/workOrderFinish.do";
					ruizhi.InvokeMethod(url, params);
					
					var orderInfoUrl = WEB_ROOT
					+ '/buyManager/func/purContractInfo/getOrderInfo.do';
					params = {};
					params.params = paramObj.orderId;
					var orderInfo = {};
					orderInfo = ruizhi.InvokeMethod(orderInfoUrl, params);
				}
				// alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
				// debugger;
				// orderInfo = JSON.parse(orderInfo);

				// 更新客商状态
				if (serviceCode != null && serviceCode == 'tradeActive_3') {
					var tacheCode = paramObj.tacheCode;
					// debugger;
					if (tacheCode != null && tacheCode == 'clientBossApprove') {
						var clientInfo = {};
						clientInfo.pageDateType = "UPDATE";
						clientInfo.clientId = paramObj.clientId;
						clientInfo.state = "10T";
						clientInfo.stateTime = new Date()
								.format("yyyy-MM-dd HH:mm:ss");
						var params = {};
						clientInfo = ruizhi.ToJson(clientInfo);
						params.params = clientInfo;
						var url = WEB_ROOT
								+ "/basicdata/func/groupClient/createOrModify.do";
						// ruizhi.InvokeMethodAsyn(url, params, null);
					}
				}


			
				// 更新状态
				if (serviceCode != null && serviceCode == 'payment_activity') {
					// debugger;
					var tacheCode = paramObj.tacheCode;
					var purPayId = paramObj.purPayId;
					if (tacheCode != null && tacheCode == 'Cashier'
							&& paramObj.doBack != "doBack") {
						if(paramObj.flag){
							ruizhi.Alert("不能审批该记录");
							return;
						}
					}
				}

				ruizhi.includes.myWorkBench.loadData();
			}
			ruizhi.includes.myWorkBench.loadData();
		},
		purContractComeBack : function(param){
			debugger;
			var params = {};
			var method = param.method;
			param = ruizhi.ToJson(param);
			params.params = param;
			if(method == "submit"){
				var url = WEB_ROOT + "/buyManager/func/purContractInfo/commit.do";
			}else{
				var url = WEB_ROOT + "/buyManager/func/purContractInfo/createOrModify.do";
			}
		  ruizhi.InvokeMethodAsyn(url,params,function(msg){
            	if("success" == msg.status){
            		ruizhi.Alert("操作成功");
            		ruizhi.includes.myWorkBench.loadData();
            	}else{
    				ruizhi.Alert(msg.message);
    			}
            });
			
			
		},
		//销售合同驳回回调
		saleContractComeBack : function(param){
			var params = {};
			var method = param.method;
			param = ruizhi.ToJson(param);
			params.params = param;
			if(method == "submit"){
				var url = WEB_ROOT + "/salesManager/func/saleContractInfo/commit.do";
			}else{
				var url = WEB_ROOT + "/salesManager/func/saleContractInfo/createOrModify.do";
			}
		  ruizhi.InvokeMethodAsyn(url,params,function(msg){
            	if("success" == msg.status){
            		ruizhi.Alert("操作成功");
            		ruizhi.includes.myWorkBench.loadData();
            	}else{
    				ruizhi.Alert(msg.message);
    			}
            });
			
			
		},
		//我的审批单击
		approveTask : function(orderId,serviceCode,tacheCode,workOrderId){
			debugger;
			var paramObj = {};// 传给模态窗口的参数
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.includes.myWorkBench.workOrderFinishComeBack;// 模态窗口提交后回调方法
			var modalClass = "modal-lg";
			// debugger;
			if (!ruizhi.IsNull(orderId)) {
				var selItem = {};
				selItem.saleName = _UserDetail.userText;
				selItem.workOrderId = workOrderId;
				selItem.saleId = _UserDetail.userId;
				selItem.orderId = orderId
				selItem.tacheCode = tacheCode;
				selItem.operBusiTime = (new Date()).format("yyyy-MM-dd");
				var url = null;
				var stateCont;
				var tacheCode = tacheCode;
				// 根据流程单据Id查询业务
				paramObj.workOrderFinish = selItem;
				var params = {};
				paramObj.flag = 'finish';
				// 跳转到合同信息页面
				if (serviceCode != null && serviceCode == 'contract_service') {
					url = WEB_ROOT
							+ '/buyManager/funcPage/purContractInfo/approval.do';
					params.params = orderId;
					gidurl = WEB_ROOT
							+ '/buyManager/func/purContractInfo/getContractInfoByOrderId.do'
					var purContractInfo = ruizhi.InvokeMethod(gidurl, params);
					var state = purContractInfo.state;
					//驳回单据可做修改跳转
					if("10C" == state){
						submitFn = ruizhi.includes.myWorkBench.purContractComeBack;// 模态窗口提交后回调方法
						purContractInfo.pageDateType = 'UPDATE';
						url = WEB_ROOT + '/buyManager/funcPage/purContractInfo/purContractInfoDetail.do';
						//url = WEB_ROOT+ '/buyManager/funcPage/purContractInfo/approval.do';
					}
					paramObj.purContractInfo = purContractInfo;
					contId = purContractInfo.contId;
				}
				// 跳转到采购年度协议信息页面
				if (serviceCode != null && serviceCode == 'purYearPort1') {
					var purYearPortIbean = {};
					// debugger;
					purYearPortIbean.orderId = orderId;
					purYearPortIbean = ruizhi.ToJson(purYearPortIbean);
					params.params = purYearPortIbean;
					var gidurl = WEB_ROOT
							+ '/buyManager/func/qryPurYearPort.do';
					var purYearPortList = ruizhi.InvokeMethod(gidurl, params);
					var purYearPort = purYearPortList[0];
					var state = purYearPort.state;
					if( state != null && state == '10R'){
						purYearPort.pageDateType = 'UPDATE';
					}else{
						purYearPort.pageDateType = 'doubleInfo';
					}
					purYearPort.tacheCode = tacheCode;
					paramObj.purYearPort = purYearPort;
					url = WEB_ROOT
							+ '/buyManager/funcPage/personalTaskDetail.do';

				}
				
				// 跳转到采购年度协议展期信息页面
				if (serviceCode == 'PUR_YEAR_PORT_EXT') {
					var purYearPortIbean = {};
					 debugger;
					purYearPortIbean.orderId = orderId;
					purYearPortIbean = ruizhi.ToJson(purYearPortIbean);
					params.params = purYearPortIbean;
					var gidurl = WEB_ROOT
							+ '/buyManager/func/qryPurYearPort.do';
					var purYearPortList = ruizhi.InvokeMethod(gidurl, params);
					var purYearPort = purYearPortList[0];
					var state = purYearPort.state;
					if( state != null && state == '10R'){
						purYearPort.pageDateType = 'UPDATE';
					}else{
						purYearPort.pageDateType = 'doubleInfo';
					}
					purYearPort.tacheCode = tacheCode;
					paramObj.purYearPort = purYearPort;
					url = WEB_ROOT
							+ '/buyManager/funcPage/buyManager/extPersonalDetail.do';

				}

				// 跳转到运输公司信息页面
				if (serviceCode != null && serviceCode == 'transportCompany1') {
					// debugger;
					var ibean = {};
					ibean.orderId = orderId;
					var gidurl = WEB_ROOT
							+ '/basic/func/transportCompany/qryPage.do';
					var transportCompanyList = ruizhi.InvokeMethod(gidurl,
							ibean);
					var transportCompany = transportCompanyList.root[0];
					// transportCompany.tacheCode = tacheCode;
					paramObj.transportCompany = transportCompany;
					if(transportCompany.state == '10D'){
						paramObj.transportCompany.pageDateType = 'UPDATE';
						url = WEB_ROOT + '/basic/funcPage/transportCompany/transportCompanyDetail.do';
						submitFn = ruizhi.basic.transportCompany.modifyComeBack;//模态窗口提交后回调方法
					}else{
						url = WEB_ROOT + '/basic/transportCompany/funcPage/personalTaskDetail.do';
					}

				}

				//调整到采购保证金页面
				if(serviceCode != null && serviceCode == 'purDepositActivity'){
					debugger;
					var ibean = {};
					ibean.orderId = orderId;
//					var gidurl = WEB_ROOT + '/financeManager/func/purDepositManager/qryPurDeposit.do';
					var gidurl = WEB_ROOT + '/financeManager/func/purDepositManager/selectPurDepositByOrderId.do';
					var list = ruizhi.InvokeMethod(gidurl,ibean);
					var purDeposit = list[0];
					purDeposit.tacheCode = tacheCode;
					paramObj.purDeposit = purDeposit;
					if(purDeposit.state == '10D'){//驳回
						paramObj = purDeposit;
						paramObj.pageDateType = 'UPDATE';
						url = WEB_ROOT + '/financeManager/funcPage/purDepositManager/createPurDeposit.do';//当前窗口url
						submitFn = ruizhi.financeManager.purWaitDeposit.createPurDepositComeBack;//模态窗口提交后回调方法
					}else{
						url = WEB_ROOT + '/financeManager/funcPage/purDepositManager/purDepositTask.do';
					}
				}
				
				//调整到费用付款单
				if(serviceCode != null && serviceCode == 'feePay'){
					debugger;
					var ibean = {};
					ibean.orderId = orderId;
					var gidurl = WEB_ROOT + '/financeManager/func/feePay/selectFeePayOrderId.do';
					var list = ruizhi.InvokeMethod(gidurl,ibean);
					var feePay = list[0];
					feePay.tacheCode = tacheCode;
					paramObj.feePay = feePay;
					if(feePay.state == '10D'){//驳回
						paramObj = feePay;
						paramObj.pageDateType = 'UPDATE';
						url = WEB_ROOT + '/financeManager/funcPage/feePay/createFeePay.do';//当前窗口url
						submitFn = ruizhi.financeManager.feeWithholding.createFeePayComeback;//模态窗口提交后回调方法
					}else{
						url = WEB_ROOT + '/financeManager/funcPage/feePay/feePayTask.do';
					}
				}
				
				//跳到销售保定金退款
				if(serviceCode != null && serviceCode == 'saleDingJinTuiKuan'){
					debugger;
					var ibean = {};
					ibean.orderId = orderId;
					var gidurl = WEB_ROOT + '/financeManager/func/saleDingJinManager/selectSaleDingJinToGoodsFeeByOrderId.do';
					var list = ruizhi.InvokeMethod(gidurl,ibean);
					var depositToGoodsFee = list[0];
					depositToGoodsFee.tacheCode = tacheCode;
					paramObj.depositToGoodsFee = depositToGoodsFee;
					if(depositToGoodsFee.state == '10D'){//驳回
						paramObj = depositToGoodsFee;
						paramObj.pageDateType = 'UPDATE';
						url = WEB_ROOT + '/financeManager/funcPage/saleDingJinManager/dingjintransfer.do';
						submitFn = ruizhi.financeManager.saleDingJinManager.dingjintransferComeBack;//模态窗口提交后回调方法
					}else{
						url = WEB_ROOT + '/financeManager/funcPage/saleDingJinManager/dingjintransferTask.do';
					}
				}
				
				// 跳转到仓库公司信息页面
				if (serviceCode != null && serviceCode == 'warehouse1') {
					// debugger;
					var ibean = {};
					ibean.orderId = orderId;
					var gidurl = WEB_ROOT
							+ '/basic/func/warehouseCompany/qryPage.do';
					var warehouseCompanyList = ruizhi.InvokeMethod(gidurl,
							ibean);
					var warehouseCompany = warehouseCompanyList.root[0];
					// warehouseCompany.tacheCode = tacheCode;
					paramObj.warehouseCompany = warehouseCompany;
					if(warehouseCompany.state == '10D'){
						paramObj.warehouseCompany.pageDateType = 'UPDATE';
						url = WEB_ROOT + '/basic/funcPage/warehouseCompany/warehouseCompanyDetail.do';
						submitFn = ruizhi.basic.warehouseCompany.modifyComeBack;//模态窗口提交后回调方法
					}else{
						url = WEB_ROOT + '/basic/warehouseCompany/funcPage/personalTaskDetail.do';
					}

				}

				// 跳转到客商信息页面
				if (serviceCode != null && serviceCode == 'tradeActive_3') {
					url = WEB_ROOT
							+ '/basicdata/funcPage/groupClient/personalTaskDetail.do';
					params.params = orderId;
					var groupClient = {};
					groupClient.orderId = orderId;
					paramObj.groupClient = groupClient;
				}
				// 跳转到出库提单信息页面
				if (serviceCode != null && serviceCode == 'delivery_service') {
					url = WEB_ROOT
							+ '/salesManager/funcPage/deliveryWareList/personalTaskDetail.do';
					var bsLadingOutInfo = {};
					bsLadingOutInfo.orderId = orderId;
					var selLadingurl = WEB_ROOT + '/salesManager/func/bsSaleOutLading/qryLadingOutByOrderId.do';
					var bsLadingInfo = ruizhi.InvokeMethod(selLadingurl,bsLadingOutInfo);
					var state = bsLadingInfo.state;
					if(state == '10D'){
						bsLadingInfo.pageDateType = 'UPDATE';
						submitFn = ruizhi.salesManager.deliveryWareDetail.dobackComeBack;//模态窗口提交后回调方法
						url = WEB_ROOT + '/salesManager/funcPage/deliveryWareList/deliveryWareDetail.do';
					}
					paramObj.bsSaleWaitOut = bsLadingInfo;
				}
				// 跳转到销售退货信息页面
				if (serviceCode != null && serviceCode == 'bsSaleDoBackService') {
					url = WEB_ROOT
					+ '/salesManager/funcPage/bsSaleOutBill/personalTaskDetail.do';
					var bsOutDoBackInfo = {};
					bsOutDoBackInfo.orderId = orderId;
					var selBillurl = WEB_ROOT + '/salesManager/func/bsSaleOutBill/qryBillDoBackByOrderId.do';
					var bsBillInfo = ruizhi.InvokeMethod(selBillurl,bsOutDoBackInfo);
					var state = bsBillInfo.state;
					if(state == '10D'){
						bsBillInfo.pageDateType = 'UPDATE';
//						submitFn = ruizhi.salesManager.bsSaleOutBillMain.createSaleOutDoBackComeBack;//模态窗口提交后回调方法
//						url = WEB_ROOT + '/salesManager/funcPage/bsSaleOutBill/saleOutDoBack.do';
					}
					paramObj.bsSaleOut = bsBillInfo;
				}
				// 跳转到采购退货信息页面
				if (serviceCode != null && serviceCode == 'bsPurRetreatService') {
					url = WEB_ROOT
					+ '/buyManager/funcPage/bsPurStorBillDoBack/personalTaskDetail.do';
					var retreatInfo = {};
					retreatInfo.orderId = orderId;
					var selUrl = WEB_ROOT + '/buyManager/func/bsPurStorBillDoBack/qryBsRetreatBillByOrderId.do';
					retreatInfo = ruizhi.InvokeMethod(selUrl,retreatInfo);
					var state = retreatInfo.state;
					if(state == '10D'){
						retreatInfo.pageDateType = 'UPDATE';
						submitFn = ruizhi.buyManager.buyBsPurStorageDoBack.dobackCreateComeBack;//模态窗口提交后回调方法
						url = WEB_ROOT + '/buyManager/funcPage/buyBsPurStorage/buyBsPurStorageDoBack.do';
					}
					paramObj.buyBsPurStorage = retreatInfo;
				}
				// 跳转到采购付款信息页面
				if (serviceCode != null && serviceCode == 'payment_activity') {
					url = WEB_ROOT
							+ '/buyManager/purPayMent/personalTaskDetail.do';
				}
				
				// 跳转到其他付款信息页面
				if (serviceCode != null && serviceCode == 'other_pay_activity') {
					url = WEB_ROOT
							+ '/financeManager/saleOtherPayMent/personalTaskDetail.do';
				}
				
				//跳转调拨退单
				if (serviceCode != null && serviceCode == 'allotBackService') {
					url = WEB_ROOT
							+ '/salesManager/saleAllotRetreatBill/personalTaskDetail.do';
				}
				
				// 跳转到折让信息页面
				if (serviceCode != null && serviceCode == 'rebateManager') {
					url = WEB_ROOT
							+ '/financeManager/funcPage/rebateManagerAdd/personalTaskDetail.do';
					params.params = orderId;
					var rebateBills = {};
					rebateBills.orderId = orderId;
					paramObj.rebateBills = rebateBills;
				}
				//调转到采购合同异常界面
				if (serviceCode != null && serviceCode == 'contractErr_service') {
					url = WEB_ROOT
							+ '/buyManager/funcPage/purContractInfo/approvalErr.do';
					params.params = orderId;
					gidurl = WEB_ROOT
							+ '/buyManager/func/purContractErrInfo/getContractErrInfoByOrderId.do';
					var purContractErrInfo = ruizhi.InvokeMethod(gidurl, params);
					var state = purContractErrInfo.state;
					if( state != null && state == '10C'){
						purContractErrInfo.pageDateType = 'UPDATE';
					}else{
						purContractErrInfo.pageDateType = 'doubleInfo';
					}
					paramObj.purContractErrInfo = purContractErrInfo;
					contErrId = purContractErrInfo.contErrId;
				}
				
				//调转到销售年度协议界面
				if (serviceCode != null && serviceCode == 'SALE_YEAR_PORT') {
					var saleYearPortIbean = {};
					// debugger;
					saleYearPortIbean.orderId = orderId;
					saleYearPortIbean = ruizhi.ToJson(saleYearPortIbean);
					params.params = saleYearPortIbean;
					var gidurl = WEB_ROOT
							+ '/saleManager/func/qrySaleYearPort.do';
					var saleYearPortList = ruizhi.InvokeMethod(gidurl, params);
					var saleYearPort = saleYearPortList[0];
					var state = saleYearPort.state;
					if( state != null && state == '10R'){
						saleYearPort.pageDateType = 'UPDATE';
					}else{
						saleYearPort.pageDateType = 'doubleInfo';
					}
					saleYearPort.tacheCode = tacheCode;
					paramObj.saleYearPort = saleYearPort;
					url = WEB_ROOT
							+ '/saleManager/funcPage/personalTaskDetail.do';

				}
				
				//调转到销售年度协议展期界面
				if (serviceCode == 'SALE_YEAR_PORT_EXT') {
					var saleYearPortIbean = {};
					// debugger;
					saleYearPortIbean.orderId = orderId;
					saleYearPortIbean = ruizhi.ToJson(saleYearPortIbean);
					params.params = saleYearPortIbean;
					var gidurl = WEB_ROOT
							+ '/saleManager/func/qrySaleYearPort.do';
					var saleYearPortList = ruizhi.InvokeMethod(gidurl, params);
					var saleYearPort = saleYearPortList[0];
					var state = saleYearPort.state;
					if( state != null && state == '10R'){
						saleYearPort.pageDateType = 'UPDATE';
					}else{
						saleYearPort.pageDateType = 'doubleInfo';
					}
					saleYearPort.tacheCode = tacheCode;
					paramObj.saleYearPort = saleYearPort;
					url = WEB_ROOT
							+ '/saleManager/funcPage/saleManager/extPersonalDetail.do';

				}
				// 跳转到销售合同信息页面
				if (serviceCode != null && serviceCode == 'SALE_CONTRACT_INFO') {
					url = WEB_ROOT
							+ '/salesManager/salesManager/funcPage/saleContractInfo/approval.do';
					params.params = orderId;
					debugger;
					gidurl = WEB_ROOT
							+ '/salesManager/func/saleContractInfo/getContractInfoByOrderId.do'
					
					var saleContractInfo = ruizhi.InvokeMethod(gidurl, params);
					stateCont = saleContractInfo.state;
					saleContractInfo.pageDateType = 'UPDATEONE';
					
					if(saleContractInfo.state == "10C"){
						url = WEB_ROOT + '/salesManager/funcPage/saleContractInfo/saleContractInfoDetail.do';
						saleContractInfo.pageDateType = 'UPDATE';
						submitFn = ruizhi.includes.myWorkBench.saleContractComeBack;// 模态窗口提交后回调方法 
					}
					saleContractInfo.portIdDis = saleContractInfo.portCode;
					paramObj.saleContractInfo = saleContractInfo;
					contId = saleContractInfo.contId;
				}
				
				//调转到销售合同异常界面
				if (serviceCode != null && serviceCode == 'salecontractErrInfo') {
					url = WEB_ROOT
							+ '/salesManager/funcPage/saleContractErrInfo/approvalErr.do';
					params.params = orderId;
					gidurl = WEB_ROOT
							+ '/salesManager/func/saleContractErrInfo/getContractErrInfoByOrderId.do';
					var saleContractErrInfo = ruizhi.InvokeMethod(gidurl, params);
					debugger;
					var state = saleContractErrInfo.state;
					if( state != null && state == '10C'){
						saleContractErrInfo.pageDateType = 'UPDATE';
					}else{
						saleContractErrInfo.pageDateType = 'doubleInfo';
					}
					paramObj.saleContractErrInfo = saleContractErrInfo;
//					contErrId = purContractErrInfo.contErrId;
				}
				ruizhi.ShowModalWin(url, width, heigth, paramObj, submitFn,
						modalClass);
				//销售合同按钮浮动样式
				if (serviceCode != null && serviceCode == 'SALE_CONTRACT_INFO'&&stateCont =="10C") {
					$('.modal-content').css({
						'height': heigth,
						'overflow:': 'hidden',
						'overflow-y': 'scroll',
						'padding-bottom': '52px',
						'padding-top': '84px'
					})
				}
			} else {
				ruizhi.Alert('请选择');
			}
			
		},
		//个人申请查看
		seeDetail : function(orderId,serviceCode){
			// debugger;
			var paramObj = {};// 传给模态窗口的参数
			var url = WEB_ROOT + '/buyManager/funcPage/personalTaskDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.includes.index_head.seeDetailComeBack;// 模态窗口提交后回调方法
			var modalClass = "modal-lg";
			 debugger;
			if (!ruizhi.IsNull(orderId)) {
				// debugger;
				orderId = orderId;
				// orderId = ruizhi.ToJson(orderId);
				serviceCode = serviceCode;
				var url = null;
				var stateCont;
				var selItem= {};
				// 根据流程单据Id查询业务
				selItem.saleName = _UserDetail.userText;
				selItem.saleId = _UserDetail.userId;
				selItem.orderId = orderId;
				selItem.operBusiTime = (new Date()).format("yyyy-MM-dd");
				var url = null;
				var stateCont;
				// 根据流程单据Id查询业务
				paramObj.workOrderFinish = selItem;
				var params = {};
				paramObj.flag = 'detail';
				// 跳转到合同信息页面
				if (serviceCode != null && serviceCode == 'contract_service') {
					var params = {};
					url = WEB_ROOT+ '/buyManager/funcPage/purContractInfo/approval.do';
					params.params = orderId;
					gidurl = WEB_ROOT
							+ '/buyManager/func/purContractInfo/getContractInfoByOrderId.do'
					var purContractInfo = ruizhi.InvokeMethod(gidurl, params);
					var state = purContractInfo.state;
					purContractInfo.pageDateType = 'doubleInfo';
					paramObj.purContractInfo = purContractInfo;
					contId = purContractInfo.contId;
				}
				// 跳转到采购年度协议信息页面
				if (serviceCode != null && serviceCode == 'purYearPort1') {
					var purYearPortIbean = {};
					purYearPortIbean.orderId = orderId;
					purYearPortIbean = ruizhi.ToJson(purYearPortIbean);
					params.params = purYearPortIbean;
					var gidurl = WEB_ROOT
							+ '/buyManager/func/qryPurYearPort.do';
					var purYearPortList = ruizhi.InvokeMethod(gidurl, params);
					var purYearPort = purYearPortList[0];
					var state = purYearPort.state;
					//purYearPort.tacheCode = tacheCode;
					purYearPort.pageDateType = 'doubleInfo';
					paramObj.purYearPort = purYearPort;
					url = WEB_ROOT
							+ '/buyManager/funcPage/personalTaskDetail.do';
				}
				// 跳转到采购年度协议战旗信息页面
				if (serviceCode == 'PUR_YEAR_PORT_EXT') {
					var purYearPortIbean = {};
					purYearPortIbean.orderId = orderId;
					purYearPortIbean = ruizhi.ToJson(purYearPortIbean);
					params.params = purYearPortIbean;
					var gidurl = WEB_ROOT
							+ '/buyManager/func/qryPurYearPort.do';
					var purYearPortList = ruizhi.InvokeMethod(gidurl, params);
					var purYearPort = purYearPortList[0];
					var state = purYearPort.state;
					purYearPort.pageDateType = 'doubleInfo';
					//purYearPort.tacheCode = tacheCode;
					paramObj.purYearPort = purYearPort;
					url = WEB_ROOT
							+ '/buyManager/funcPage/buyManager/extPersonalDetail.do';
				}
				// 跳转到运输公司信息页面
				if (serviceCode != null && serviceCode == 'transportCompany1') {
					// debugger;
					var ibean = {};
					ibean.orderId = orderId;
					var gidurl = WEB_ROOT
							+ '/basic/func/transportCompany/qryPage.do';
					var transportCompanyList = ruizhi.InvokeMethod(gidurl,
							ibean);
					var transportCompany = transportCompanyList.root[0];
					// transportCompany.tacheCode = tacheCode;
					paramObj.transportCompany = transportCompany;
					url = WEB_ROOT
							+ '/basic/transportCompany/funcPage/personalTaskDetail.do';
				}
				// 跳转到仓库公司信息页面
				if (serviceCode != null && serviceCode == 'warehouse1') {
					var ibean = {};
					ibean.orderId = orderId;
					var gidurl = WEB_ROOT
							+ '/basic/func/warehouseCompany/qryPage.do';
					var warehouseCompanyList = ruizhi.InvokeMethod(gidurl,
							ibean);
					var warehouseCompany = warehouseCompanyList.root[0];
					paramObj.warehouseCompany = warehouseCompany;
					url = WEB_ROOT
							+ '/basic/warehouseCompany/funcPage/personalTaskDetail.do';

				}
				
				//跳转到采购保证金详情页面
				if (serviceCode != null && serviceCode == 'purDepositActivity') {
					var ibean = {};
					ibean.orderId = orderId;
					var gidurl = WEB_ROOT + '/financeManager/func/purDepositManager/selectPurDepositByOrderId.do';
					var list = ruizhi.InvokeMethod(gidurl,ibean);
					var purDeposit = list[0];
					paramObj.purDeposit = purDeposit;
					url = WEB_ROOT + '/financeManager/funcPage/purDepositManager/purDepositTask.do';
				}
				
				//跳转到费用付款单详情页面
				if (serviceCode != null && serviceCode == 'feePay') {
					var ibean = {};
					ibean.orderId = orderId;
					var gidurl = WEB_ROOT + '/financeManager/func/feePay/selectFeePayOrderId.do';
					var list = ruizhi.InvokeMethod(gidurl,ibean);
					var feePay = list[0];
					paramObj.feePay = feePay;
					url = WEB_ROOT + '/financeManager/funcPage/feePay/feePayTask.do';
				}

				// 跳转到客商信息页面
				if (serviceCode != null && serviceCode == 'tradeActive_3') {
					url = WEB_ROOT
							+ '/basicdata/funcPage/groupClient/personalTaskDetail.do';
					params.params = orderId;
					var groupClient = {};
					groupClient.orderId = orderId;
					paramObj.groupClient = groupClient;
				}
				// 跳转到出库提单信息页面
				if (serviceCode != null && serviceCode == 'delivery_service') {
					url = WEB_ROOT
							+ '/salesManager/funcPage/deliveryWareList/personalTaskDetail.do';
					var bsLadingOutInfo = {};
					bsLadingOutInfo.orderId = orderId;
					var selLadingurl = WEB_ROOT + '/salesManager/func/bsSaleOutLading/qryLadingOutByOrderId.do';
					var bsLadingInfo = ruizhi.InvokeMethod(selLadingurl,bsLadingOutInfo);
					if(null != bsLadingInfo){
						var state = bsLadingInfo.state;
						if(state == '10D'){
							bsLadingInfo.pageDateType = 'UPDATE';
							submitFn = ruizhi.salesManager.deliveryWareList.createDeliveryWareComeBack;//模态窗口提交后回调方法
							url = WEB_ROOT + '/salesManager/funcPage/deliveryWareList/deliveryWareDetail.do';
						}
					}
					paramObj.bsSaleWaitOut = bsLadingInfo;
				}
				// 跳转到销售退货信息页面
				if (serviceCode != null && serviceCode == 'bsSaleDoBackService') {
					url = WEB_ROOT
					+ '/salesManager/funcPage/bsSaleOutBill/personalTaskDetail.do';
					var bsOutDoBackInfo = {};
					bsOutDoBackInfo.orderId = orderId;
					var selBillurl = WEB_ROOT + '/salesManager/func/bsSaleOutBill/qryBillDoBackByOrderId.do';
					var bsBillInfo = ruizhi.InvokeMethod(selBillurl,bsOutDoBackInfo);
					var state = bsBillInfo.state;
					if(state == '10D'){
						bsBillInfo.pageDateType = 'UPDATE';
//						submitFn = ruizhi.salesManager.bsSaleOutBillMain.createSaleOutDoBackComeBack;//模态窗口提交后回调方法
//						url = WEB_ROOT + '/salesManager/funcPage/bsSaleOutBill/saleOutDoBack.do';
					}
					paramObj.bsSaleOut = bsBillInfo;
				}
				// 跳转到折让信息页面
				if (serviceCode != null && serviceCode == 'rebateManager') {
					url = WEB_ROOT
							+ '/financeManager/funcPage/rebateManagerAdd/personalTaskDetail.do';
					params.params = orderId;
					var rebateBills = {};
					rebateBills.orderId = orderId;
					paramObj.rebateBills = rebateBills;
				}
				// 跳转到采购付款信息页面
				if (serviceCode != null && serviceCode == 'payment_activity') {
					url = WEB_ROOT
							+ '/buyManager/purPayMent/personalTaskDetail.do';
				}
				// 跳转到其他付款信息页面
				if (serviceCode != null && serviceCode == 'other_pay_activity') {
					url = WEB_ROOT
							+ '/financeManager/saleOtherPayMent/personalTaskDetail.do';
				}
				
				//跳转调拨退单
				if (serviceCode != null && serviceCode == 'allotBackService') {
					url = WEB_ROOT
							+ '/salesManager/saleAllotRetreatBill/personalTaskDetail.do';
				}
				
				//调转到采购合同异常界面
				if (serviceCode != null && serviceCode == 'contractErr_service') {
					url = WEB_ROOT
							+ '/buyManager/funcPage/purContractInfo/approvalErr.do';
					params.params = orderId;
					gidurl = WEB_ROOT
							+ '/buyManager/func/purContractErrInfo/getContractErrInfoByOrderId.do';
					var purContractErrInfo = ruizhi.InvokeMethod(gidurl, params);
					purContractErrInfo.pageDateType = 'doubleInfo';
					paramObj.purContractErrInfo = purContractErrInfo;
					contErrId = purContractErrInfo.contErrId;
				}
				
				//调转到销售年度协议界面
				if (serviceCode != null && serviceCode == 'SALE_YEAR_PORT') {
					var saleYearPortIbean = {};
					// debugger;
					saleYearPortIbean.orderId = orderId;
					saleYearPortIbean = ruizhi.ToJson(saleYearPortIbean);
					params.params = saleYearPortIbean;
					var gidurl = WEB_ROOT
							+ '/saleManager/func/qrySaleYearPort.do';
					var saleYearPortList = ruizhi.InvokeMethod(gidurl, params);
					var saleYearPort = saleYearPortList[0];
					saleYearPort.pageDateType = 'doubleInfo';
					//saleYearPort.tacheCode = tacheCode;
					paramObj.saleYearPort = saleYearPort;
					url = WEB_ROOT
							+ '/saleManager/funcPage/personalTaskDetail.do';

				}
				
				//调转到销售年度协议展期界面
				if (serviceCode == 'SALE_YEAR_PORT_EXT') {
					var saleYearPortIbean = {};
					// debugger;
					saleYearPortIbean.orderId = orderId;
					saleYearPortIbean = ruizhi.ToJson(saleYearPortIbean);
					params.params = saleYearPortIbean;
					var gidurl = WEB_ROOT
							+ '/saleManager/func/qrySaleYearPort.do';
					var saleYearPortList = ruizhi.InvokeMethod(gidurl, params);
					var saleYearPort = saleYearPortList[0];
					saleYearPort.pageDateType = 'doubleInfo';
					//saleYearPort.tacheCode = tacheCode;
					paramObj.saleYearPort = saleYearPort;
					url = WEB_ROOT
							+ '/saleManager/funcPage/saleManager/extPersonalDetail.do';

				}
				
				debugger;
				// 跳转到销售合同信息页面
				if (serviceCode != null && serviceCode == 'SALE_CONTRACT_INFO') {
					url = WEB_ROOT
							+ '/salesManager/salesManager/funcPage/saleContractInfo/approval.do';
					params.params = orderId;
					debugger;
					gidurl = WEB_ROOT
							+ '/salesManager/func/saleContractInfo/getContractInfoByOrderId.do'
					
					var saleContractInfo = ruizhi.InvokeMethod(gidurl, params);
					stateCont = saleContractInfo.state;
					saleContractInfo.pageDateType = 'doubleInfo';
					saleContractInfo.portIdDis = saleContractInfo.portCode;
					paramObj.saleContractInfo = saleContractInfo;
					contId = saleContractInfo.contId;
				}
				// 跳转到出库提单信息页面
				if (serviceCode != null && serviceCode == 'delivery_service') {
					url = WEB_ROOT
							+ '/salesManager/funcPage/deliveryWareList/personalTaskDetail.do';
					var bsLadingOutInfo = {};
					bsLadingOutInfo.orderId = orderId;
					var selLadingurl = WEB_ROOT + '/salesManager/func/bsSaleOutLading/qryLadingOutByOrderId.do';
					var bsLadingInfo = ruizhi.InvokeMethod(selLadingurl,bsLadingOutInfo);
					var state = bsLadingInfo.state;
					if(state == '10D'){
						bsLadingInfo.pageDateType = 'UPDATE';
						submitFn = ruizhi.salesManager.deliveryWareList.createDeliveryWareComeBack;//模态窗口提交后回调方法
						url = WEB_ROOT + '/salesManager/funcPage/deliveryWareList/deliveryWareDetail.do';
					}
					paramObj.bsSaleWaitOut = bsLadingInfo;
				}
				// 跳转到销售退货信息页面
				if (serviceCode != null && serviceCode == 'bsSaleDoBackService') {
					url = WEB_ROOT
					+ '/salesManager/funcPage/bsSaleOutBill/personalTaskDetail.do';
					var bsOutDoBackInfo = {};
					bsOutDoBackInfo.orderId = orderId;
					var selBillurl = WEB_ROOT + '/salesManager/func/bsSaleOutBill/qryBillDoBackByOrderId.do';
					var bsBillInfo = ruizhi.InvokeMethod(selBillurl,bsOutDoBackInfo);
					var state = bsBillInfo.state;
					if(state == '10D'){
						bsBillInfo.pageDateType = 'UPDATE';
//						submitFn = ruizhi.salesManager.bsSaleOutBillMain.createSaleOutDoBackComeBack;//模态窗口提交后回调方法
//						url = WEB_ROOT + '/salesManager/funcPage/bsSaleOutBill/saleOutDoBack.do';
					}
					paramObj.bsSaleOut = bsBillInfo;
				}
				//调转到销售合同异常界面
				if (serviceCode != null && serviceCode == 'salecontractErrInfo') {
					url = WEB_ROOT
							+ '/salesManager/funcPage/saleContractErrInfo/approvalErr.do';
					params.params = orderId;
					gidurl = WEB_ROOT
							+ '/salesManager/func/saleContractErrInfo/getContractErrInfoByOrderId.do';
					var saleContractErrInfo = ruizhi.InvokeMethod(gidurl, params);
					paramObj.saleContractErrInfo = saleContractErrInfo;
//					contErrId = purContractErrInfo.contErrId;
				}
				ruizhi.ShowModalWin(url, width, heigth, paramObj, submitFn,
						modalClass);
				//销售合同按钮浮动样式
				if (serviceCode != null && serviceCode == 'SALE_CONTRACT_INFO') {
					$('.modal-content').css({
						'height': heigth,
						'overflow:': 'hidden',
						'overflow-y': 'scroll',
						'padding-bottom': '52px',
						'padding-top': '84px'
					})
				}
			} else {
				ruizhi.Alert('请选择');
			}
			
		},
		
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.includes.myWorkBench.init()
});