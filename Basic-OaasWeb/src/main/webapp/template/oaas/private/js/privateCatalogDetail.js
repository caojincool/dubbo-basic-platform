//知识体系详情页面
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.privateCatalogDetail = function() {
	
	//私有成员
	var form1;
	var _this = this;
	var URL =  WEB_ROOT + '/oaas/funcPage/privateCatalog/catalogDetail.do';//当前窗口的URL
	//私有方法
	var checkCode;
	return{
		/* 初始化 */
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			
			var privateCatalog = paramObj.privateCatalog;
			
			form1 = new ruizhi.FormExt("oaas-privateCatalog-form1");
			form1.objectToForm(privateCatalog);
		},	
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			var valueObj = form1.formToObject();// 整个表单的值
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			if(valueObj.catalogId == ''){
				valueObj.catalogId = null;
				valueObj.createUserId = _SESSION.userId;//创建人
			}else{
				valueObj.modifyUserId = _SESSION.userId;//修改人
			}
			if(valueObj.parentCatalogId == ''){
				valueObj.parentCatalogId = -1;
			}
			valueObj.state ='10A'
			var param = valueObj;
			//alert("整个提交数据:"+ruizhi.ToJson(param));
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			

		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		}
		
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.oaas.privateCatalogDetail.init()
});

// ////////////////////////////////////////
// function定义
