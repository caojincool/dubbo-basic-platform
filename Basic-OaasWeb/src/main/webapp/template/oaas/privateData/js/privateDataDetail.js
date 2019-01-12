//知识体系详情页面
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.privateDataDetail = function() {
	
	//私有成员
	var form1;
	var URL =  WEB_ROOT + '/oaas/funcPage/privateData/privateDataDetail.do';//当前窗口的URL
	var paramObj;
	//私有方法
	var checkCode;
	
	var url1 = WEB_ROOT + '/oaas/func/dataScopeType/qryAll.do'
	var dataTypeList = ruizhi.InvokeMethod(url1,null);
	return{
		/* 初始化 */
		init : function() {
			paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var privateData = paramObj.privateData;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(privateData));
			
			form1 = new ruizhi.FormExt("oaas-privateDataDetail-form1");
			
			form1.selOptionAddAll('scopeType',dataTypeList,'scopeTypeName','scopeType',true)
			
			form1.objectToForm(privateData);
			
			if(form1.getValue("dataId")){
				
			};
			
		},	
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			var valueObj = form1.formToObject();// 整个表单的值
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			if(valueObj.dataId == ''){
				valueObj.createUserId = _SESSION.userId;//创建人
				
			}else{
				valueObj.modifyUserId = _SESSION.userId;//修改人
			}
			if(ruizhi.IsNull(valueObj.state)){
				valueObj.state = '10A'
			}
			
			var param = valueObj;
			//alert("整个提交数据:"+ruizhi.ToJson(param));
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			

		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.oaas.privateDataDetail.init()
});

// ////////////////////////////////////////
// function定义
