//目录单选窗口
ruizhi.Package("ruizhi.order");
ruizhi.order.selTacheCatalog = function(){
	
	var URL =  WEB_ROOT + '/order/funcPage/tacheCatalog/selTacheCatalog.do';//当前窗口的URL
	
	var zTree1;
	
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);

			var submitParam = null;//提交到后台的参数
			if(!ruizhi.IsNull(paramObj)){
				submitParam = paramObj.submitParam;
			}
			zTree1 = new ruizhi.ZTree("order-selTacheCatalog-zTree1", {
				otherParam : submitParam,
				url : WEB_ROOT + '/order/func/tacheCatalog/qryTreeStep.do'
			});
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#order-selTacheCatalog-title").html(paramObj.title);
			}
			
		},

		doSubmit : function() {

			var selItem = zTree1.getSelectedNodes();//所选行
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
	ruizhi.order.selTacheCatalog.init();
});