//////////////////////////////////////////
//任务组件
ruizhi.Package("ruizhi.order");

ruizhi.order.woComponent = function() {

	var grid1;
	var form1;
	var _UserDetail = ruizhi.GetUser();
	
	return {
		//初始化
		init : function() {

			grid1 = new ruizhi.DataGrid("order-woComponet-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/order/func/woComponet/pagin.do'
			});
			form1 = new ruizhi.FormExt("order-woComponet-form1");
		},
		
		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/order/func/woComponet/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
		//新增窗口
		create : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/woComponet/woComponetDetail.do';
			var width = null;
			var heigth = null;
			var  woComponet= {};
			woComponet.pageDateType = 'CREATE';
			paramObj.woComponet = woComponet;
			var submitFn = ruizhi.order.woComponent.createComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-lg";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		//新增窗口回调函数
		createComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			
			var url = WEB_ROOT + "/order/func/woComponet/createOrModify.do";
//			alert(params);
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.woComponent.loadData);
			//ruizhi.order.woComponent.loadData();
		},
		
		//编辑窗口
		modify : function() {
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/order/funcPage/woComponet/woComponetDetail.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.order.woComponent.modifyComeBack;//模态窗口提交后回调方法
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
				selItem.pageDateType = 'UPDATE';
				paramObj.woComponet = selItem;
				ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
			}else{
				ruizhi.Alert('请选择');
			}
			
		},
		
		//编辑窗口回调函数
		modifyComeBack : function(param){
			var params = {};
			param = ruizhi.ToJson(param);
			params.params = param;
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
			var url = WEB_ROOT + "/order/func/woComponet/createOrModify.do";
			ruizhi.InvokeMethodAsyn(url,params,ruizhi.order.woComponent.loadData);
			//ruizhi.order.woComponent.loadData();
		},
		
		//删除，可批量删除
		del : function(){
			var ids = grid1.getCheckedIds();//所选行的id数组
			if(!ruizhi.IsNull(ids) && ids.length > 0){
//				alert(ids);
				ids = ids.join(",");
				var msg = "确定要删除吗？";
				bootbox.confirm(msg, function(result) {
					if(result || 'true' == result) {
						var params = {};
						params.componentIds = ids;
//						alert("ids:"+ids);
//						params.modifyUserId = _UserDetail.userId;//修改人
						//alert("从模态窗口返回的参数："+ruizhi.ToJson(params));
						var url = WEB_ROOT + "/order/func/woComponent/del.do";
						ruizhi.InvokeMethodAsyn(url,params, ruizhi.order.woComponent.loadData);
						//ruizhi.order.woComponent.loadData();
					} else {
						
					}
				});
			}else{
				ruizhi.Alert('请选择');
			}
		},
		
	}

}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.order.woComponent.init();
});