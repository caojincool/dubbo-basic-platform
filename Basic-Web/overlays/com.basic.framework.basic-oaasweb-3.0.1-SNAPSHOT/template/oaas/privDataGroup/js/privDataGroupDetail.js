//知识体系详情页面
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.privDataGroupDetail = function() {
	
	//私有成员
	var form1;
	var URL =  WEB_ROOT + '/oaas/funcPage/privDataGroup/privDataGroupDetail.do';//当前窗口的URL
	var paramObj;
	
	var url1 = WEB_ROOT + '/oaas/func/dataScopeType/qryAll.do'
	var dataTypeList = ruizhi.InvokeMethod(url1,null);
	return{
		/* 初始化 */
		init : function() {
			paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var privDataGroup = paramObj.privDataGroup;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(privDataGroup));
			
			form1 = new ruizhi.FormExt("oaas-privDataGroupDetail-form1");
			
			form1.objectToForm(privDataGroup);
			
			//修改
			if(form1.getValue("dataGroupId")){
				
			};
			
		},	
		
		/* 表单提交 */
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}
			var valueObj = form1.formToObject();// 整个表单的值
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));
//			if(valueObj.dataGroupId == ''){
//				valueObj.createUserId = _SESSION.userId;//创建人
//				
//			}else{
//				valueObj.modifyUserId = _SESSION.userId;//修改人
//			}
//			if(ruizhi.IsNull(valueObj.state)){
//				valueObj.state = '10A'
//			}
			
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
	ruizhi.oaas.privDataGroupDetail.init()
});

// ////////////////////////////////////////
// function定义
