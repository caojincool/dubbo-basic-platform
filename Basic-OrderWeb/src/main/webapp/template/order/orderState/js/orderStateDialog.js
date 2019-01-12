//人员单选窗口
ruizhi.Package("ruizhi.order");
ruizhi.order.orderStateDialog = function(){
	
	var URL =  WEB_ROOT + '/order/funcPage/orderState/orderStateDialog.do';//当前窗口的URL
	
	var grid1;
	var form1;
	
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);
			
			form1 = new ruizhi.FormExt("order-orderStateDialog-form1");
			
			grid1 = new ruizhi.DataGrid("order-orderStateDialog-grid1",{
				postData : {
					staffId : paramObj.staffId
				},
				url : WEB_ROOT + '/order/func/orderState/pagin.do'
			});
			
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#order-orderStateDialog-title").html(paramObj.title);
			}
			
		},
		
		
		qry : function(){//查询加载数据
			var url = WEB_ROOT + '/order/func/orderState/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
			
		},
		
		//i职位双击确定事件
		orderDbClick : function(rowId) {
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

ruizhi.ExecWait(function() {
	ruizhi.order.orderStateDialog.init();
});