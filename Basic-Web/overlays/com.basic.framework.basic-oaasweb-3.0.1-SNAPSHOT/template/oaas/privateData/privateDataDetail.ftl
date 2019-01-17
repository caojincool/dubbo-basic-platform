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
	<h6 class="modal-title">数据权限</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="oaas-privateDataDetail-form1" action="#">
<input type="hidden" name="dataId" class="form-control">
<input type="hidden" name="state" class="form-control">

<input type="hidden" name="createUserId" class="form-control">
<input type="hidden" name="modifyUserId" class="form-control">


	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row"> 
				<div class="col-md-12">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">数据权限编码<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control" placeholder="必填且唯一"  name="dataCode" remote="common/verify/verifyUniqueCode.do?tableCode=OAAS_PRIV_DATA&colCode=DATA_CODE"  required="required" message="required:请填写,remote:该编码已重复" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">数据权限名称<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control" placeholder="必填"  name="dataName"  required="required" message="请填写" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">数据范围类型<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<select name="scopeType" class="select" data-placeholder="请选择..." required="required" message="请选择">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">备注<span class="text-danger"></span></label>
							<div class="col-lg-6">
								<textarea name = "remarks" class="form-control" rows="3" cols="10"></textarea>
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
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.privateDataDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.privateDataDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/privateData/js/privateDataDetail.js"></script>
</html>
