//////////////////////////////////////////
//Ruisoft corp. 2016年9月6日08:54:28
//Author :zzj
//commits:演示页面
//////////////////////////////////////////

//类定义
ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoExcel = function(){
	
	var grid = null;
	var otherParams = null;
	
	return {
		init:function(){
			grid = new ruizhi.DataGrid("demo-demoExcel-grid1");
		},
		
		//下载导入模板
		downloadTemplate : function(){
			var url = WEB_ROOT + "/system/func/excelImportTmp/qryByTemplateCode.do";
			var paramObj = {};
			paramObj.templateCode = 'demo.test.excelReadTemplate';
			var excelImportTmp = ruizhi.InvokeMethod(url, paramObj);
			if(!ruizhi.IsNull(excelImportTmp)){
				var downloadUrl = WEB_ROOT + "/file/downloadFile.do?fileInfoId="+excelImportTmp.importFileInfoId;
				window.location.href = downloadUrl;
			}else{
				ruizhi.Alert('服务器不存在该模板！');
			}
		},
		
		//导入excel
		importExcel : function(){
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/file/fileModelWin.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.demo.demoExcel.importExcelWinComeBack;//模态窗口提交后回调方法
			var modalClass = "modal-lg";
			
			paramObj.inputType = "importExcel";//导入
			paramObj.moduleCode = "excel";//模块编码
			
			paramObj.templateCode = "demo.test.excelReadTemplate";//模板编码
			//paramObj.busiBeanName = "demoExcelImport";//服务名称
			paramObj.otherParams = "";//其他参数
			
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		
		//导入excel回调方法
		importExcelWinComeBack : function(param){
			//alert("从模态窗口返回的参数："+ruizhi.ToJson(param));
			alert("resultList："+ruizhi.ToJson(param.resultList));
			//alert("param.resultList[0]："+ruizhi.ToJson(param.resultList[0]));
			var lists = param.resultList[0];
			for(var i in lists){
				var item = lists[i];
				if(item != null){
					//alert("item："+ruizhi.ToJson(item));
					var randomId = "JQGRID_"+Math.random();
					item.randomId = randomId;
					grid.addItem(randomId,item);
				}
			}
		},
		
		//导出excel
		exportExcel : function(){
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/demo/func/demoExcel/exportExcel.do';
			paramObj.templateCode = 'demo.test.excelWriteTemplate';
			ruizhi.SubmitForm(paramObj, url);
		},
		
		//导出excel百万数据量（慎重！）
		exportExcelForBigDate : function(){
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/demo/func/demoExcel/exportExcelForBigDate.do';
			paramObj.templateCode = 'demo.test.excelWriteTemplate';
			ruizhi.SubmitForm(paramObj, url);
		},
		
	}
	
	
}();

//初始化
ruizhi.ExecWait(function(){
	ruizhi.demo.demoExcel.init();
});