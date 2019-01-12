//目录单选窗口
ruizhi.Package("ruizhi.order");
ruizhi.order.selWoDispRule = function(){
	
	var URL =  WEB_ROOT + '/order/funcPage/woDispRule/selWoDispRule.do';//当前窗口的URL
	
	var grid1;
	var form1;
	
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);

			var submitParam = null;//提交到后台的参数
			if(!ruizhi.IsNull(paramObj)){
				submitParam = paramObj.submitParam;
			}
			
			grid1 = new ruizhi.DataGrid("order-selWoDispRule-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/order/func/woDispRule/pagin.do'
			});
			form1 = new ruizhi.FormExt("order-selWoDispRule-form1");
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#order-selWoDispRule-title").html(paramObj.title);
			}
			
		},

		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/order/func/woDispRule/pagin.do';
			var paramObj = form1.formToObject();
			grid1.loadData(url, paramObj);
		},
		
		//单选环节
		selTache : function(){
			var url = WEB_ROOT + '/order/funcPage/tache/selTache.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.submitParam = null;//提交到后台的参数
			paramObj.title = "单选环节";
			var submitFn = ruizhi.order.selWoDispRule.selTacheComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-lg';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selTacheComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param)){
				form1.setValue('tacheId', param.tacheId);
				form1.setValue('tacheName', param.tacheName);
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
	ruizhi.order.selWoDispRule.init();
});