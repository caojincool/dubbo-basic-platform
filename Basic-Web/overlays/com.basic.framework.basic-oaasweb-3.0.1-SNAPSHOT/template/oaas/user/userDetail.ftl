<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<!--页面head-->
<#include "../../includes/modal-head.ftl">
	
</head>

<body>
<!--页面title-->
<div class="modal-header bg-primary">
	<!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
	<h6 class="modal-title">新增账号</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="oaas-userDetail-form1" action="#">
<input type="hidden" name="userId" class="form-control">
<input type="hidden" name="modifyUserId" class="form-control">
<input type="hidden" name="createUserId" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-2 control-label">账号名称：</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="账号名称" name="userText" remote="oaas/func/verifyCode.do?tableName=OAAS_USER&attributeName=USER_NAME" required="required" message="required:请填写,remote:该名称已重复">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label">登陆账号：</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="登陆账号" name="username" remote="oaas/func/verifyCode.do?tableName=OAAS_USER&attributeName=USER_user" required="required" message="required:请填写,remote:该账号已重复">
							</div>
						</div>
						
						<div class="form-group" id="oaas-userDetail-userPasswordDiv">
							<label class="col-lg-2 control-label">登陆密码：</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="登陆密码" name="userPassword" message="required:请填写,onblur:原密码不正确" onblur="ruizhi.oaas.userDetail.password()">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label">账号失效时间：</label>
							<div class="col-lg-8">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="expireTime" class="form-control datetimepicker" format="YYYY-MM-DD" value="">
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">备注：</label>
							<div class="col-lg-8">
								<textarea rows="5" cols="5" class="form-control" placeholder="备注" name="remarks"></textarea>
							</div>
						</div>

					</fieldset>
				</div>
			</div>
		</div>
	</div>
</form>

</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.userDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.userDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/user/js/userDetail.js"></script>
</html>
