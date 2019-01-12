//单据类型单选窗口
ruizhi.Package("ruizhi.order");
ruizhi.order.selOrderType = function(){
	
	var URL =  WEB_ROOT + '/order/funcPage/orderType/selOrderType.do';//当前窗口的URL
	
	var grid1;
	var form1;
	
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);

			var submitParam = null;//提交到后台的参数
			if(!ruizhi.IsNull(paramObj)){
				submitParam = paramObj.submitParam;
			}
			
			grid1 = new ruizhi.DataGrid("order-selOrderType-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/order/func/orderType/pagin.do'
			});
			form1 = new ruizhi.FormExt("order-selOrderType-form1");
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#order-selOrderType-title").html(paramObj.title);
			}
			
		},

		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/order/func/orderType/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
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
	ruizhi.order.selOrderType.init();
});