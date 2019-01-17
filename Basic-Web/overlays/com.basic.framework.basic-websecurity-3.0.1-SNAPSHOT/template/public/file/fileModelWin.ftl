<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<!--页面head-->
<#include "../../includes/modal-head.ftl">

	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
		.tangram-suggestion-main {
	    z-index: 1060;
		}
	</style>
	
</head>

<body>
<!--页面title-->
<div class="modal-header bg-primary">
	<!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
	<h6 class="modal-title"></h6>
</div>

<div class="modal-body" id="fileModelWin-body">
			<!--<div id="baiduMap-displayDiv" style="display:none;">
				<div id="r-result" style="width:100%;">请输入:<input type="text" id="baiduMap-suggestId" size="20" value="百度" style="width:150px;" /></div>
				<div id="baiduMap" style="height:300px;width:100%;"></div>
			</div>-->
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.file.fileModelWin.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.file.fileModelWin.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/public/file/js/fileModelWin.js"></script>
</html>
