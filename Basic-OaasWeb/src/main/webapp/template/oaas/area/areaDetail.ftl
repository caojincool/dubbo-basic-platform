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
	<h6 class="modal-title">区域</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="oaas-areaDetail-form1" action="#">
<input type="hidden" name="areaId" class="form-control">
<input type="hidden" name="parentAreaId" class="form-control">

<input type="hidden" name="idPath" class="form-control">
<input type="hidden" name="namePath" class="form-control">
<input type="hidden" name="codePath" class="form-control">
<input type="hidden" name="createUserId" >

	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row"> 
				<div class="col-md-12">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">区域编码<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control" placeholder="必填且唯一"  name="areaCode" remote="common/verify/verifyUniqueCode.do?tableCode=OAAS_AREA&colCode=AREA_CODE"  required="required" message="required:请填写,remote:该编码已重复" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">区域名称<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control" placeholder="必填"  name="areaName"  required="required" message="请填写" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">显示顺序<span class="text-danger"></span></label>
							<div class="col-lg-6">
								<input type="number" class="form-control" placeholder="选填数字" name="displayIndex" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">级别<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<select name="areaGrade" class="select" data-placeholder="请选择..." required="required" message="请选择">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">备注<span class="text-danger"></span></label>
							<div class="col-lg-6">
								<textarea name = "remarks" class="form-control" rows="3" ></textarea>
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
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.areaDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.areaDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/area/js/areaDetail.js"></script>
</html>
