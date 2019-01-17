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
	<h6 class="modal-title">业务按钮</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="oaas-funcDetail-form1" action="#">
<input type="hidden" name="privateId" class="form-control">
<input type="hidden" name="funcId" class="form-control">

	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">按钮编码<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control" placeholder="必填且唯一"  name="funcCode" remote="common/verify/verifyUniqueCode.do?tableCode=OAAS_PRIV_FUNC&colCode=FUNC_CODE"  required="required" message="required:请填写,remote:该编码已重复" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">按钮名称<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control" placeholder="必填"  name="funcName"  required="required" message="请填写" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">URL<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control" placeholder="必填"  name="funcUrl"  required="required" message="请填写" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">图标<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control" placeholder="必填"  name="funcIcon"  required="required" message="请填写" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">显示顺序<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="number" class="form-control" placeholder="必填"  name="displayIndex"  required="required" message="请填写" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">按钮目录<span class="text-danger"></span></label>
							<div class="col-lg-6">
								<div class="input-group">
									<input type="hidden"  name="catalogId" >
									<input type="text" class="form-control" placeholder="选择目录" name="catalogName" readonly required="required" message="请选择目录" >
									<span class="input-group-btn">
										<button id="selectBtn" class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.oaas.funcDetail.selCatalog()">选择</button>
									</span>
								</div>
							</div>
						</div> 
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">备注<span class="text-danger"></span></label>
							<div class="col-lg-6">
								<textarea class="form-control" name="remarks"></textarea>
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
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.funcDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.funcDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/func/js/funcDetail.js"></script>
</html>
