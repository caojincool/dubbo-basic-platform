//功能配置详情页面
ruizhi.Package("ruizhi.oaas");

ruizhi.oaas.userDetail = function() {
	
	//私有成员
	var form1;
	var _this = this;
	var URL = WEB_ROOT + '/oaas/funcPage/user/userDetail.do';//当前窗口的URL
	
	return{
		/* 初始化 */
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert(ruizhi.ToJson(paramObj));
			debugger;
			form1 = new ruizhi.FormExt("oaas-userDetail-form1");
			
			if(paramObj != null && paramObj != '' && paramObj != 'undefined'){
				if(paramObj.pageDateType == 'UPDATE'){
					$("#oaas-userDetail-userPasswordDiv").hide();
//					form1.getObject("userText").attr("disabled","disabled");
					form1.getObject("username").attr("disabled","disabled");
					$(".modal-title").html("修改账号");
				}
			}
			console.log(paramObj);
			form1.objectToForm(paramObj);
				
		},	
		
		password:function(){
			
			var userPassword = form1.getValue('userPassword');
			
			//校验密码是否符合长度6位及以上、英文数字组合、不区分大小写、不含特殊符号
			var patrn=/^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z\d]{6,}$/; 
			//alert();
			if (!patrn.exec(userPassword)){
				//$("#remarks").css("color","red").text("密码不符合长度6位及以上、英文数字组合、不区分大小写、不含特殊符号");
				alert("密码不符合长度6位及以上、英文数字组合、不区分大小写、不含特殊符号");
				//return false;
			}else{
				$("#remarks").css("color","red").text("");
			}
			//return false; 
			
		},
		
		/* 表单提交 */
		doSubmit : function() {
			debugger;
			if(!form1.validate()){
				return;
			}
			
			var userPassword = form1.getValue('userPassword');
			//校验密码是否符合长度6位及以上、英文数字组合、不区分大小写、不含特殊符号
//			var patrn=/^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z\d]{6,}$/; 
//			if (!patrn.exec(userPassword)){
//				return;
//			}
			
			var valueObj = form1.formToObject();// 整个表单的值
			
			if(valueObj.userId == ''){
				valueObj.userId = null;
				valueObj.createUserId = _SESSION.userId;//创建人
			}else{
				valueObj.modifyUserId = _SESSION.userId;//修改人
			}
			
			if(valueObj.createUserId == ''){
				valueObj.createUserId = null;
			}
			
			var params = valueObj;
			debugger;
			//alert("整个提交数据:"+ruizhi.ToJson(params));
			ruizhi.SubmitModalWin(URL,params);//通过公用方法调用父页面的回调方法
			
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},
		
	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.oaas.userDetail.init()
});

// ////////////////////////////////////////
// function定义
