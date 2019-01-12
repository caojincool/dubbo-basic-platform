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
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h6 class="modal-title">我是模态窗口</h6>
</div>

<div class="modal-body" id="modalBody">
	<div class="form-group">
		<label class="col-lg-3 control-label">单行文本<span class="text-danger">*(最少长度2)</span></label>
		<div class="col-lg-9">
			<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
		</div>
	</div>

</div>

<div class="modal-footer">
	<button type="button" class="btn btn-link" data-dismiss="modal">关闭</button>
	<button type="button" class="btn btn-primary" onclick="ruizhi.demo.demoModalWin.doSubmit()">确定</button>
</div>
<!--页面foot-->
<#include "../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/demo/js/demoFormModalWin.js"></script>
</html>
