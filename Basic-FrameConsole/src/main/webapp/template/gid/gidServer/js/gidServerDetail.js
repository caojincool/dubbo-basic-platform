//////////////////////////////////////////
//gid服务详情页面
ruizhi.Package("ruizhi.gid");

ruizhi.gid.gidServerDetail = function() {
	
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/gid/funcPage/gidServer/gidServerDetail.do';//当前窗口的URL
	
	return{
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var gidServer = paramObj.gidServer;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(gidServer));
			
			form1 = new ruizhi.FormExt("gid-gidServerDetail-form1");
			
			if(!ruizhi.IsNull(gidServer) && !ruizhi.IsNull(gidServer.gidCode)){
				var codeObject = form1.getObject("gidCode");
				codeObject.attr("readonly", "readonly");
			}
			form1.objectToForm(gidServer);
		},	
		
		//提交到上一页面的回调方法
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}

			var valueObj = form1.formToObject();// 整个表单的值
			//alert("整个表单数据:"+ruizhi.ToJson(valueObj));

			//valueObj.createUserId = _SESSION.userId;//创建人
			
			var param = valueObj;
			//alert("整个提交数据:"+ruizhi.ToJson(param));
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},

	}
}();

//初始化
ruizhi.ExecWait(function(){
	ruizhi.gid.gidServerDetail.init()
});