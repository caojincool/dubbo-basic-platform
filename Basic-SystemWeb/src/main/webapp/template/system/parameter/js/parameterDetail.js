//////////////////////////////////////////
//字典表详情页面
ruizhi.Package("ruizhi.system");

ruizhi.system.parameterDetail = function() {
	
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/system/funcPage/parameter/parameterDetail.do';//当前窗口的URL
	
	return{
		//初始化
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var parameter = paramObj.parameter;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(parameter));
			
			form1 = new ruizhi.FormExt("system-parameterDetail-form1");
			
			
			form1.objectToForm(parameter);
			
			if(!ruizhi.IsNull(parameter) && parameter.pageDateType == "UPDATE"){
				form1.getObject("paramCode").prop("readonly",true);
			}
		},	
		
		//提交到上一页面的回调方法
		doSubmit : function() {
			//验证表单必填项
			if(!form1.validate()){
				return;
			}

			var valueObj = form1.formToObject();// 整个表单的值
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
	ruizhi.system.parameterDetail.init()
});