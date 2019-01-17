//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

//类定义
//////////////////////////////////////////
ruizhi.Package("ruizhi.file");
ruizhi.file.fileModelWin = function() {
	
	var URL =  WEB_ROOT + '/file/fileModelWin.do';//当前窗口的URL
	var hiddenObject;
	var resultList;//导入excel的数据
	var fileInfos;//导入的文件详细信息
	
	return {
		// 公有成员
		// 公有方法
		init : function() {
			var paramObj = ruizhi.GetModalWinParam(URL);
			//alert("模态窗口内获取初始化参数："+ruizhi.ToJson(paramObj));

			var name = paramObj.name;//name
			var value = paramObj.value;//value
			var maxFileCount = paramObj.maxFileCount;//上传文件个数
			var inputType = paramObj.inputType;//图片:image、文件:file、地图:map、导入excel：importExcel
			var moduleCode = paramObj.moduleCode;//上传文件的时候的模块编码
			var address = paramObj.address;//地址
			
			var templateCode = paramObj.templateCode;//模板编码
			var busiBeanName = paramObj.busiBeanName;//服务名称
			var otherParams = paramObj.otherParams;//额外参数
			
			//alert(inputType);
			//alert(name);
			//alert(value);
			if(inputType == "image" || inputType == "file" || inputType == "importExcel"){
				var html = '<input name="modelWin_'+name+'" value="'+value+'" type="hidden">';
				$("#fileModelWin-body").append(html);
				hiddenObject = $('input[name="modelWin_'+name+'"]');
				//alert(hiddenObject.val());
				//var val = $('input[name="modelWin_'+name+'"]').val();
				//alert(val);
				var fileCmpId = "fileinputImage_$_"+Math.random();
				var fileCmp = $('<input id="'+fileCmpId+'" name="'+fileCmpId+'" type="file" multiple  class="file-loading">');    
				fileCmp.insertAfter(hiddenObject);
				
				/*var uploadExtraDatas = {
						"moduleCode" : moduleCode,
						"templateCode" : templateCode,
						"busiBeanName" : busiBeanName
				};*/
				//alert(ruizhi.ToJson(uploadExtraDatas));
				var uploadUrl = null;
				if(inputType == "image" || inputType == "file"){
					/*var otherParamss = [];
					for(var i=0;i<100;i++){
						var otherParams = {};
						otherParams.id = i;
						otherParams.name = "额外参数"+i;
						otherParamss.push(otherParams);
					}
					var otherParamsStr = ruizhi.ToJson(otherParamss);
					alert(otherParamsStr);*/	//数据长也可以传进去
					
					uploadUrl = WEB_ROOT +"/file/uploadFile.do?moduleCode="+moduleCode+"&templateCode="+templateCode+"&busiBeanName="+busiBeanName+"&otherParams="+otherParams;
				}else{
					uploadUrl = WEB_ROOT +"/excel/importExcel.do?moduleCode="+moduleCode+"&templateCode="+templateCode+"&busiBeanName="+busiBeanName+"&otherParams="+otherParams;
				}
					
				var imgFooter =  '<div class="file-thumbnail-footer">\n' +
				'    <div class="file-footer-caption" title="{caption}">{caption}</div>\n' +
				'</div>';
				
				//默认配置
				var defaultOpts = {
						language: 'zh', //设置语言
						uploadUrl: uploadUrl, //上传的地址
						//allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
						layoutTemplates:{footer:imgFooter},//把单独图片下面的上传按钮和删除按钮去掉
						uploadAsync:true,//是否异步提交
						//showPreview:false,//是否显示缩略图
						showRemove:true,//是否显示删除按钮
						//showUpload: true, //是否显示上传按钮
						//showCaption: false,//是否显示标题
						//browseClass: "btn btn-primary", //按钮样式	 
						//dropZoneEnabled: false,//是否显示拖拽区域
						browseOnZoneClick: true,//是否点击拖拽区域弹出选择文件窗口
						//minImageWidth: 50, //图片的最小宽度
						//minImageHeight: 50,//图片的最小高度
						//maxImageWidth: 1000,//图片的最大宽度
						//maxImageHeight: 1000,//图片的最大高度
						//maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
						//minFileCount: 0,
						//maxFileCount: 10, //表示允许同时上传的最大文件个数
						//enctype: 'multipart/form-data',
						//validateInitialCount:true,
						//previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
						//msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
						//uploadExtraData:{"moduleCode":moduleCode},// 向服务器提交额外的参数
						//uploadExtraData: uploadExtraDatas,// 向服务器提交额外的参数
						/*uploadExtraData: function(previewId, index) {   //额外参数的关键点
		                    var obj = {};
		                    obj.fodder = "1234";
		                    console.log(obj);
		                    return obj;
		                }*/
				};
				//alert(JSON.stringify(defaultOpts));
				if(maxFileCount != null && maxFileCount != '' && maxFileCount != undefined && maxFileCount != 'undefined'){
					defaultOpts.maxFileCount = maxFileCount;
				}
				if(inputType == "image"){
					defaultOpts.allowedFileExtensions = ['jpg', 'gif', 'png', 'tif', 'tiff'];
				}
				//初始化上传控件的样式
				//fileCmp.fileinput(defaultOpts);
				/*fileCmp.fileinput({
    			
	    	  });*/
				//是否加载图片
				var fileId = hiddenObject.val();
				if(fileId != null && fileId != '' && fileId != undefined && fileId != 'undefined'){
					var fileTockenIds = fileId.split(",");
					//alert(fileTockenIds+"加载图片Id");
					var operTypeOts = {
							//overwriteInitial: false,//编辑初始化时候，已有图片，再次选择图片的时候不会被覆盖
					};
					if(inputType == "image"){
						var initialPreview = new Array();
						$.each(fileTockenIds,function(i,val){
							//alert(i+"--"+val);
							var downloadUrl = WEB_ROOT+"/file/getFile.do?fileInfoId="+val;
							var imgUrl = "<img style='height:160px' src='"+downloadUrl+"'>";
							initialPreview.push(imgUrl);//初始化图片
						})
						//alert(initialPreview);
						operTypeOts.initialPreview = initialPreview;
						//alert(JSON.stringify(operTypeOts));
						$.extend(defaultOpts,operTypeOts);
						//alert(JSON.stringify(defaultOpts));
					}else if(inputType == "file"){
						var initialPreview = new Array();
						var initialPreviewConfig = new Array();
						$.each(fileTockenIds,function(i,val){
							$.ajax({
								url: WEB_ROOT+"/file/getFileInfo.do",
								async: false, 
								type: 'POST', 
								dataType: 'JSON', 
								data: {"fileInfoId" : val},
								success: function(response){
									if(response != null && response != '' && response != undefined){
										var downloadUrl = WEB_ROOT+"/file/downloadFile.do?fileInfoId="+val;
										var imgUrl = "<a class='btn btn-primary' href='"+downloadUrl+"'>下载 </a>";
										initialPreview.push(imgUrl);//初始化图片
										
										var delUrl = "";
										var initialPreviewConfigObj = {};
										initialPreviewConfigObj.caption = response.srcFileName;
										initialPreviewConfigObj.width = "120px";
										initialPreviewConfigObj.url = delUrl;
										initialPreviewConfigObj.key = val;
										initialPreviewConfig.push(initialPreviewConfigObj);
										//alert(JSON.stringify(initialPreviewConfig));
									} else {
										ruizhi.Alert("加载失败！！！");
									}
								}
							});
							//alert(i+"--"+val);
							
						})
						//alert(initialPreview);
						operTypeOts.initialPreview = initialPreview;
						operTypeOts.initialPreviewConfig = initialPreviewConfig;
						//alert(JSON.stringify(operTypeOts));
						$.extend(defaultOpts,operTypeOts);
						//alert(JSON.stringify(defaultOpts));
					}
					//初始化上传控件的样式
					fileCmp.fileinput(defaultOpts);
					bindEvent();
				}else{
					//初始化上传控件的样式
					fileCmp.fileinput(defaultOpts);
					bindEvent();
				}
				
				function bindEvent(){
					//导入文件上传完成之后的事件
					fileCmp.on("fileuploaded", function (event, data, previewId, index) {
						//alert("回调函数");
						if(null != data.response){
							var rtData = data.response;
							if(rtData != null && rtData != 'undefined'){
								//上传文件的标识
								if(rtData.fileIds != null && rtData.fileIds != 'undefined'){
									//后台拿回来的文件Id
									var fileId = rtData.fileIds;
									var fileIds = new Array();
									var oldId = hiddenObject.val();
									if(oldId != null && oldId != '' && oldId != undefined && oldId != 'undefined'){
										fileIds = oldId.split(",");
									}else{
										
									}
									//alert(fileIds);
									fileIds.push(fileId);
									//alert(fileIds);
									hiddenObject.val(fileIds);
								}
								//导入的文件详细信息
								if(rtData.fileInfos != null && rtData.fileInfos != 'undefined'){
									//后台拿回来的文件信息
									var fileInfo = rtData.fileInfos;
									//alert(JSON.stringify(fileInfo));
									if(fileInfos == null || fileInfos.length == 0){
										fileInfos = [];
									}
									//把拿回来的文件信息存起来
									if(fileInfo != null && fileInfo != '' && fileInfo != undefined && fileInfo != 'undefined' && fileInfo.length > 0){
										for(var i in fileInfo){
											var item = fileInfo[i];
											fileInfos.push(item);
											//alert(JSON.stringify(item));
										}
									}
									//alert(JSON.stringify(fileInfos));
								}
								//导入excel的数据
								if(rtData.resultList != null && rtData.resultList != 'undefined'){
									resultList = rtData.resultList;
								}
							}
						}else{
							//alert("上传失败！！！");
							ruizhi.Alert("上传失败！！！");
						}
					});
					//删除之后调用的函数
					fileCmp.on('filecleared', function(event) {
						//alert("删除函数");
						hiddenObject.val("");
						//alert(hiddenObject.val());
					});
					
					fileCmp.on('filedeleted', function(event, key) {
						//alert("filecleared");
						console.log('Key = ' + key);
					});
				}
			}else if(inputType == "map"){
				//console.info(map);
				var mapHtml = '<input name="modelWin_'+name+'" value="'+value+'" type="hidden" address="'+address+'">';
				//mapHtml += '<input name="modelWin_'+name+'_TEXT" value="" type="hidden">';
				mapHtml += '<div id="r-result" style="width:100%;">请输入:<input type="text" id="baiduMap-suggestId" size="20" value="百度" style="width:150px;" /></div>';
				mapHtml += '<div id="baiduMap" style="height:300px;width:100%;"></div>';
				$("#fileModelWin-body").append(mapHtml);
				hiddenObject = $('input[name="modelWin_'+name+'"]');
				//$("#baiduMap-displayDiv").css("display","block");
				setTimeout(function(){
					ruizhiMap = new ruizhi.BaiduMap('modelWin_'+name+'','baiduMap','baiduMap-suggestId');
					//ruizhiMap.webGeolocation();
					ruizhiMap.clickEventListener();
					ruizhiMap.geolocationControl();
					ruizhiMap.autoSearch();
					/*				if(value != null && value != '' && value != undefined && value != 'undefined'){
					var p = value.split(",");
					var point = new BMap.Point(p[0],p[1]);
					ruizhiMap.setMarker(point);
				}*/
					//var newPoint = ruizhiMap.returnPoint();
					//hiddenObject.val(newPoint);
					//var newAddress = ruizhiMap.returnAddress();
					//$('input[name="modelWin_'+name+'"]').val(newAddress);
					//hiddenObject.attr("address", newAddress);
					},400);//延迟加载才能让地图显示完全
			}
		},
		

		doSubmit : function() {
			var param = {};
			param.name = hiddenObject.prop("name").replace("modelWin_","");
			param.value = hiddenObject.val();
			param.address = hiddenObject.attr("address");
			
			param.resultList = resultList;//导入excel的数据
			param.fileInfos = fileInfos;//导入的文件详细信息
			
			//alert(hiddenObject.prop("name"));
			//alert(hiddenObject.val());
			ruizhi.SubmitModalWin(URL,param);//通过公用方法调用父页面的回调方法
			
			
		},
		
		doClose:function(){
			ruizhi.CloseModalWin(URL);
		},

	}

}();

// 初始化
// ////////////////////////////////////////

ruizhi.ExecWait(function() {
	ruizhi.file.fileModelWin.init();
});
