//////////////////////////////////////////
//Excel上传模板详情页面
ruizhi.Package("ruizhi.system");

ruizhi.system.excelImportTmpDetail = function() {
	
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/system/funcPage/excelImportTmp/excelImportTmpDetail.do';//当前窗口的URL
	
	return{
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var excelImportTmp = paramObj.excelImportTmp;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(excelImportTmp));
			
			form1 = new ruizhi.FormExt("system-excelImportTmpDetail-form1");
			
			if(excelImportTmp != null && excelImportTmp != 'undefined' && excelImportTmp.templateCode != null && excelImportTmp.templateCode != ''){
				var templateCodeObject = form1.getObject("templateCode");
				templateCodeObject.attr("readonly", "readonly");
			}
			form1.objectToForm(excelImportTmp);
		},	
		
		//提交到上一页面的回调方法
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}

			var valueObj = form1.formToObject();// 整个表单的值
			//不能为空，不然后台转为对象的时候类型不匹配
			if(valueObj.importFileInfoId == ''){
				valueObj.importFileInfoId = null;
			}
			if(valueObj.readFileInfoId == ''){
				valueObj.readFileInfoId = null;
			}
			
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
	ruizhi.system.excelImportTmpDetail.init()
});