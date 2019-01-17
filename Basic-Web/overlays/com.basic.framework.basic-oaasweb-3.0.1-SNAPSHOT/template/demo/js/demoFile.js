//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

//类定义
//////////////////////////////////////////
ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoFile = function() {

	// 私有成员定义;
	var _this = this;
	var form1 = null;

	// 私有方法定义

	return {

		init : function() {
			form1 = new ruizhi.FormExt("demo-demoFile-form1");
			
			//alert("初始化参数");
			form1.setValue("fileId1","");
			form1.setValue("fileId2","72730,72731,72732");
			form1.setValue("fileId3","72730,72731,72732");
			form1.setValue("fileId4","116.425631,39.916771");
			form1.setAddress("fileId4","北京市120路");
			//form1.setValue("fileId4","116.460126,39.905093");
			//ruizhi.FileInput.udloadImage("fileId1");
			//ruizhi.FileInput.udloadFile("fileId2");

			//ruizhi.FileInput.downloadFile('downFilexx');
/*			$("#clickHiddenText").focus(function(){
				//$("#l-map").attr("style","display: block;")
				$("#clickHiddenDiv2").css("display","none");
				$("#clickHiddenDiv").css("display","block");
			});
			$("#clickHiddenText").blur(function(){
				//$("#l-map").attr("style","display: none;");
				$("#clickHiddenDiv2").css("display","block");
				$("#clickHiddenDiv").css("display","none");
			});*/
		    
		},
		
		getImageId1 : function(){
			var fileId1 = form1.getValue("fileId1");
			alert(fileId1);
		},
		
		getImageId2 : function(){
			var fileId2 = form1.getValue("fileId2");
			alert(fileId2);
		},
		
		getFileId : function(){
			var fileId3 = form1.getValue("fileId3");
			alert(fileId3);
		},
		
		getMapPoint : function(){
			var fileId4 = form1.getValue("fileId4");
			alert(fileId4);
			var address = form1.getAddress("fileId4");
			alert(address);
		},
		
		getfileInfoIds : function(){
			var fileId4 = form1.getValue("fileInfoIds");
			alert(fileId4);
		},
		
		
		setFormValue : function(){
			var obj = {
					fileId1 : '72730,72731',
					fileId2 : '',
					fileId3 : '72730',
					fileId4 : ''
				};
				form1.objectToForm(obj);
		},

	}

}();

// 初始化
// ////////////////////////////////////////

ruizhi.ExecWait(function() {
	ruizhi.demo.demoFile.init();
});