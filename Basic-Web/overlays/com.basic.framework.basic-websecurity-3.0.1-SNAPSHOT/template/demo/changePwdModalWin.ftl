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
	<h6 class="modal-title">修改密码</h6>
</div>

<div class="modal-body" id="modalBody">
	<form id="demo-changePwd-form1" class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-offset-1 col-lg-4 control-label">原密码<span class="text-danger"></span></label>
			<div class="col-lg-6">
				<input type="password" class="form-control" placeholder="必填"  name="userPassword"  required="required" message="请填写" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-offset-1 col-lg-4 control-label">新密码<span class="text-danger"></span></label>
			<div class="col-lg-6">
				<input type="password" class="form-control" placeholder="必填" id="newPassword" name="newPassword"  required="required" message="请填写" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-offset-1 col-lg-4 control-label">再次输入新密码<span class="text-danger"></span></label>
			<div class="col-lg-6">
				<input type="password" class="form-control" placeholder="必填"  name="confirmPassword" equalTo="#newPassword"  required="required" message="required:请填写,equalTo=两次输入的密码不一致" >
			</div>
		</div>
	</form>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-link" onclick="ruizhi.demo.changePwdModalWin.doClose()">关闭</button>
	<button type="button" class="btn btn-primary" onclick="ruizhi.demo.changePwdModalWin.doSubmit()">确定</button>
</div>
<!--页面foot-->
<#include "../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/demo/js/changePwdModalWin.js"></script>
</html>
