
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.companyDetail = function() {
	
	//私有成员
	var form1;
	var URL =  WEB_ROOT + '/oaas/funcPage/company/companyDetail.do';//当前窗口的URL
	var paramObj;
	//私有方法
	var checkCode;
	return{
		/* 初始化 */
		init : function() {
			paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var company = paramObj.company;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(company));
			
			form1 = new ruizhi.FormExt("oaas-companyDetail-form1");
			form1.objectToForm(company);
			
			
		},	
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			var valueObj = form1.formToObject();// 整个表单的值
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
			if(valueObj.companyId == ''){
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
	ruizhi.oaas.companyDetail.init()
});

// ////////////////////////////////////////
// function定义
