//单据单选窗口
ruizhi.Package("ruizhi.order");
ruizhi.order.selOrder = function(){
	
	var URL =  WEB_ROOT + '/order/funcPage/orderMonitor/selOrder.do';//当前窗口的URL
	
	var grid1;
	var form1;
	
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);

			var submitParam = null;//提交到后台的参数
			if(!ruizhi.IsNull(paramObj)){
				submitParam = paramObj.submitParam;
			}
			
			grid1 = new ruizhi.DataGrid("order-selOrder-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/order/func/orderMonitor/pagin.do'
			});
			form1 = new ruizhi.FormExt("order-selOrder-form1");
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#order-selOrder-title").html(paramObj.title);
			}
			
		},

		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/order/func/selOrder/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
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
			var submitFn = ruizhi.order.selOrder.selAreaComeBack;//模态窗口提交后回调方法
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
			var submitFn = ruizhi.order.selOrder.selOrgComeBack;//模态窗口提交后回调方法
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
		
		
		doSubmit : function() {

			var selItem = grid1.getSelectedItem();//所选行
			if(!ruizhi.IsNull(selItem)){
				ruizhi.SubmitModalWin(URL,selItem);//通过公用方法调用父页面的回调方法
			}else{
				ruizhi.Alert('请选择');
			}
			
			
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		
	}
	
}();

//初始化
ruizhi.ExecWait(function() {
	ruizhi.order.selOrder.init();
});