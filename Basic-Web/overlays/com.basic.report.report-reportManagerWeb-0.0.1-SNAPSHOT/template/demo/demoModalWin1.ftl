<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<!--页面head-->
<#include "../includes/modal-head.ftl">

</head>

<body>
<!--页面title-->
<div class="modal-header bg-primary">
	<!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
	<h6 class="modal-title">我是模态窗口1</h6>
</div>

<div class="modal-body" id="modalBody">


			
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-link" onclick="ruizhi.demo.demoModalWin1.doClose()">关闭</button>
	<button type="button" class="btn btn-primary" onclick="ruizhi.demo.demoModalWin1.doSubmit()">确定</button>
</div>
<!--页面foot-->
<#include "../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/demo/js/demoModalWin1.js"></script>
</html>
