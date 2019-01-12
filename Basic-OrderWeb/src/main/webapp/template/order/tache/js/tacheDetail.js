//////////////////////////////////////////
//环节详情页面
ruizhi.Package("ruizhi.order");

ruizhi.order.tacheDetail = function() {
	
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/order/funcPage/tache/tacheDetail.do';//当前窗口的URL
	var pageDateType = null;//新增：CREATE，编辑：UPDATE
	var _UserDetail = ruizhi.GetUser();
	
	return{
		//初始化
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var tache = paramObj.tache;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(tache));
			pageDateType = tache.pageDateType;
			
			form1 = new ruizhi.FormExt("order-tacheDetail-form1");
			
			//编码不允许修改
			if(!ruizhi.IsNull(tache) && !ruizhi.IsNull(tache.tache)){
				var tacheObject = form1.getObject("tache");
				tacheObject.attr("readonly", "readonly");
			}

			//环节目录
			if(!ruizhi.IsNull(tache.catalogId)){
				var parentUrl = WEB_ROOT + '/order/func/tacheCatalog/qryTacheCatalog.do';
				var parentParam = {'catalogId': tache.catalogId};
				var parent = ruizhi.InvokeMethod(parentUrl,parentParam);
				if(!ruizhi.IsNull(parent)){
					tache.catalogName = parent.catalogName;
				}
			}
			
			form1.objectToForm(tache);
		},	

		//单选目录
		selTacheCatalog : function(){
			var url = WEB_ROOT + '/order/funcPage/tacheCatalog/selTacheCatalog.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.submitParam = null;//提交到后台的参数
			paramObj.title = "单选目录";
			var submitFn = ruizhi.order.tacheDetail.selTacheCatalogComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-sm';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selTacheCatalogComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param) && param.length >0){
				form1.setValue('catalogId', param[0].catalogId);
				form1.setValue('catalogName', param[0].catalogName);
			}
		},
		
		//提交到上一页面的回调方法
		doSubmit : function() {
			//验证表单必填项
			if(!form1.validate()){
				return;
			}

			var valueObj = form1.formToObject();// 整个表单的值
			
			if(!ruizhi.IsNull(pageDateType) && pageDateType == 'CREATE'){//新增
//			if(ruizhi.IsNull(valueObj.tache)){//新增
				valueObj.createUserId = _UserDetail.userId;//创建人
			}else{//编辑
				valueObj.modifyUserId = _UserDetail.userId;//修改人
			}
			
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			
			var param = valueObj;
			//alert("整个提交数据:"+ruizhi.ToJson(param));
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			
		},
		
		//关闭窗口
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},

	}
}();

//初始化
ruizhi.ExecWait(function(){
	ruizhi.order.tacheDetail.init()
});