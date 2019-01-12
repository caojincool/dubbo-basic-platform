//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

//类定义
//////////////////////////////////////////
ruizhi.Package("ruizhi.order");

ruizhi.order.selOrderService = function() {
	
	var URL =  WEB_ROOT + '/order/funcPage/orderService/selOrderService.do';//当前窗口的URL
	// 私有成员定义;
	var grid1 = null;
	var form1;
	return {
		// 公有成员

		// 公有方法
		init : function() {
			//alert("kkk")
			var paramObj = ruizhi.GetModalWinParam(URL);
			form1 = new ruizhi.FormExt("order-selOrderService-form1");
			grid1 = new ruizhi.DataGrid("order-selOrderService-grid1",{
				postData : {
//					staffId : paramObj.staffId
				},
				url : WEB_ROOT + '/order/func/orderService/pagin.do'
			});
			
		},
		
		qry : function(){//查询加载数据
			var url = WEB_ROOT + '/order/func/orderService/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
			
		},
		
		//双击确定事件
		selProcessRuleClick : function(rowId) {
			//alert(rowId);
			this.doSubmit();
		},

		doSubmit : function() {

			var selItem = grid1.getSelectedItem();//所选行
			if(selItem!=null){
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

// 初始化
// ////////////////////////////////////////

ruizhi.ExecWait(function() {
	ruizhi.order.selOrderService.init();
});
