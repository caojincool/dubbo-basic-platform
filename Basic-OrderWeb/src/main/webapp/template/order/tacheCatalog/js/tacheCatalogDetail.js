//////////////////////////////////////////
//环节目录详情页面
ruizhi.Package("ruizhi.order");

ruizhi.order.tacheCatalogDetail = function() {
	
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/order/funcPage/tacheCatalog/tacheCatalogDetail.do';//当前窗口的URL
	var pageDateType = null;//新增：CREATE，编辑：UPDATE
	var _UserDetail = ruizhi.GetUser();
	
	return{
		//初始化
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var tacheCatalog = paramObj.tacheCatalog;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(tacheCatalog));
			pageDateType = tacheCatalog.pageDateType;
			
			form1 = new ruizhi.FormExt("order-tacheCatalogDetail-form1");
			
			//编码不允许修改
			if(!ruizhi.IsNull(tacheCatalog) && !ruizhi.IsNull(tacheCatalog.catalogCode)){
				var catalogCodeObject = form1.getObject("catalogCode");
				catalogCodeObject.attr("readonly", "readonly");
			}
			
			//上级目录
			if(!ruizhi.IsNull(tacheCatalog.parentCatalogId)){
				var parentUrl = WEB_ROOT + '/order/func/tacheCatalog/qryTacheCatalog.do';
				var parentParam = {'catalogId': tacheCatalog.parentCatalogId};
				var parent = ruizhi.InvokeMethod(parentUrl,parentParam);
				if(!ruizhi.IsNull(parent)){
					tacheCatalog.parentCatalogName = parent.catalogName;
				}
			}
			
			form1.objectToForm(tacheCatalog);
		},	


		//单选目录
		selTacheCatalog : function(){
			var url = WEB_ROOT + '/order/funcPage/tacheCatalog/selTacheCatalog.do';
			var width = null;
			var heigth = null;
			var paramObj = {};//传给模态窗口的参数
			paramObj.submitParam = null;//提交到后台的参数
			paramObj.title = "单选目录";
			var submitFn = ruizhi.order.tacheCatalogDetail.selTacheCatalogComeBack;//模态窗口提交后回调方法
			var modalClass = 'modal-sm';
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		selTacheCatalogComeBack :function(param){
			//alert(ruizhi.ToJson(param));
			if(!ruizhi.IsNull(param) && param.length >0){
				form1.setValue('parentCatalogId', param[0].catalogId);
				form1.setValue('parentCatalogName', param[0].catalogName);
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
//			if(ruizhi.IsNull(valueObj.tacheCatalog)){//新增
				valueObj.createUserId = _UserDetail.userId;//创建人
			}else{//编辑
				valueObj.modifyUserId = _UserDetail.userId;//修改人
			}
			
			if(valueObj.parentCatalogId == ''){
				valueObj.parentCatalogId = -1;
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
	ruizhi.order.tacheCatalogDetail.init()
});