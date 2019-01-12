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
	<h6 class="modal-title">业务权限</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="oaas-privateDetail-form1" action="#">
<input type="hidden" name="privateId" class="form-control">
<!--
<input type="hidden" name="parentOrgId" class="form-control">
-->
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">权限编码<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control" placeholder="必填且唯一"  name="privateCode" remote="common/verify/verifyUniqueCode.do?tableCode=OAAS_PRIVATE&colCode=PRIVATE_CODE"  required="required" message="required:请填写,remote:该编码已重复" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">权限名称<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control" placeholder="必填"  name="privateName"  required="required" message="请填写" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">权限类型<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<select class="select" name="privateType" disabled>
									<option value="MENU">菜单</option>
									<option value="FUNC">功能按钮权限</option>
									<option value="SPECIAL" selected>特殊权限</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">权限目录<span class="text-danger"></span></label>
							<div class="col-lg-6">
								<div class="input-group">
									<input type="hidden"  name="catalogId" >
									<input type="text" class="form-control" placeholder="选择目录" name="catalogName" readonly required="required" message="请选择目录" >
									<span class="input-group-btn">
										<button id="selectBtn" class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.oaas.privateDetail.selCatalog()">选择</button>
									</span>
								</div>
							</div>
						</div>
					</fieldset>
				</div>
			</div>

		</div>
	</div>
</form>
<!--  form -->
									
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.privateDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.privateDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/private/js/privateDetail.js"></script>
</html>
