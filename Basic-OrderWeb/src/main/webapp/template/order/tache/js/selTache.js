//目录单选窗口
ruizhi.Package("ruizhi.order");
ruizhi.order.selTache = function(){
	
	var URL =  WEB_ROOT + '/order/funcPage/tache/selTache.do';//当前窗口的URL
	
	var zTree1;
	var grid1;
	var form1;
	
	return {
		init:function(){
			var paramObj = ruizhi.GetModalWinParam(URL);

			var submitParam = null;//提交到后台的参数
			if(!ruizhi.IsNull(paramObj)){
				submitParam = paramObj.submitParam;
			}
			
			zTree1 = new ruizhi.ZTree("order-selTache-zTree1", {
				otherParam : submitParam,
				url : WEB_ROOT + '/order/func/tacheCatalog/qryTreeStep.do'
			});
			grid1 = new ruizhi.DataGrid("order-selTache-grid1",{
				postData : {
					
				},
				url : WEB_ROOT + '/order/func/tache/pagin.do'
			});
			form1 = new ruizhi.FormExt("order-selTache-form1");
			
			if(!ruizhi.IsNull(paramObj.title)){
				$("#order-selTache-title").html(paramObj.title);
			}
			
		},

		//加载数据
		loadData : function() {
			var url = WEB_ROOT + '/order/func/tache/pagin.do';
			var paramObj = form1.formToObject();
			
			var selItem = zTree1.getSelectedNodes()[0];//所选行
			if(!ruizhi.IsNull(selItem) && !ruizhi.IsNull(selItem.catalogId)){
				paramObj.catalogId = selItem.catalogId
			}
			
			grid1.loadData(url, paramObj);
		},
		
		//--------------环节目录--------------
		//加载数据
		loadDataTacheCatalog : function() {
			var url = WEB_ROOT + '/order/func/tacheCatalog/qryTreeStep.do';
			var paramObj = null;
			zTree1.loadData(url,paramObj);
		},

		//树点击事件
		treeClick : function(treeNode){
			ruizhi.order.selTache.loadData();
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
	ruizhi.order.selTache.init();
});