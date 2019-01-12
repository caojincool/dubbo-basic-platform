//////////////////////////////////////////
//Excel导出模板详情页面
ruizhi.Package("ruizhi.system");

ruizhi.system.excelExportTmpDetail = function() {
	
	var form1 = null;
	var _this = this;
	var URL =  WEB_ROOT + '/system/funcPage/excelExportTmp/excelExportTmpDetail.do';//当前窗口的URL
	
	return{
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));
			
			var excelExportTmp = paramObj.excelExportTmp;
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(excelExportTmp));
			
			form1 = new ruizhi.FormExt("system-excelExportTmpDetail-form1");
			
			if(excelExportTmp != null && excelExportTmp != 'undefined' && excelExportTmp.templateCode != null && excelExportTmp.templateCode != ''){
				var templateCodeObject = form1.getObject("templateCode");
				templateCodeObject.attr("readonly", "readonly");
			}
			form1.objectToForm(excelExportTmp);
		},	
		
		//提交到上一页面的回调方法
		doSubmit : function() {
			if(!form1.validate()){
				return;
			}

			var valueObj = form1.formToObject();// 整个表单的值
			//不能为空，不然后台转为对象的时候类型不匹配
			if(valueObj.fileInfoId == ''){
				valueObj.fileInfoId = null;
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
	ruizhi.system.excelExportTmpDetail.init()
});