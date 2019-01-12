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
	<h6 class="modal-title">数据权限分组</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="oaas-privDataGroupDetail-form1" action="#">
<input type="hidden" name="dataGroupId" class="form-control">



	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row"> 
				<div class="col-md-12">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">编码<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control" placeholder="必填且唯一"  name="dataGroupCode" remote="common/verify/verifyUniqueCode.do?tableCode=OAAS_PRIV_DATA_GROUP&colCode=DATA_GROUP_CODE"  required="required" message="required:请填写,remote:该编码已重复" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">分组名称<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control" placeholder="必填"  name="dataGroupName"  required="required" message="请填写" >
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
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.privDataGroupDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.privDataGroupDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/privDataGroup/js/privDataGroupDetail.js"></script>
</html>
