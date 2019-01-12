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
	<h6 class="modal-title">Excel导出模板</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="system-excelExportTmpDetail-form1" action="#">
<input type="hidden" name="pageDateType" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">模板编码</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="模板编码" name="templateCode" remote="common/verify/verifyUniqueCode.do?tableCode=PUB_EXCEL_EXPORT_TMP&colCode=TEMPLATE_CODE" required="required" message="required:请填写,remote:该名称已重复">
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">模板名称</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="模板名称" name="templateName">
							</div>
						</div>	

						<div class="form-group">
							<label class="col-lg-3 control-label">导出模板名称</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="上传模板名称" name="writeFileInfoName">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">导出模板文件</label>
							<div class="col-lg-4">
								<div class="input-group">
									<input type="hidden" class="form-control" name="fileInfoId" inputType="file" maxFileCount="1" placeholder="选择弹窗" moduleCode="excel">
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">备注</label>
							<div class="col-lg-8">
								<textarea rows="5" cols="5" class="form-control" placeholder="备注" name="comments"></textarea>
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
	<button type="button" class="btn btn-primary" onclick="ruizhi.system.excelExportTmpDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.system.excelExportTmpDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/system/excelExportTmp/js/excelExportTmpDetail.js"></script>
</html>
