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
	<h6 class="modal-title">公司信息</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="oaas-companyDetail-form1" action="#">
<input type="hidden" name="companyId" class="form-control">
<input type="hidden" name="state" class="form-control">

<input type="hidden" name="createUserId" class="form-control">
<input type="hidden" name="modifyUserId" class="form-control">


	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row"> 
				<div class="col-md-12">
					<div class="col-md-6">
						<fieldset>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">公司全名<span class="text-danger">*</span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control" placeholder="必填"  name="fullName"  required="required" message="required:请填写" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">公司中文名<span class="text-danger">*</span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control" placeholder="必填"  name="chineseName"  required="required" message="required:请填写" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">公司英文名<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control"  name="englishName" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">公司logo<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control"  name="logo" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">公司法人<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control" name="legal"  >
								</div>
							</div>
							
						</fieldset>
					</div>
					<div class="col-md-6">
						<fieldset>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">主营业务<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control"   name="business"    >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">公司邮箱<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="email" class="form-control" name="email" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">公司电话<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="number" class="form-control"  name="telephone"  >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">公司地址<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control"  name="address"   >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">社会信用代码<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control" name="socialCreditCode" >
								</div>
							</div>
						</fieldset>
					</div>
				</div>
			</div>

		</div>
	</div>
</form>
<!--  form -->
									
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.companyDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.companyDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/company/js/companyDetail.js"></script>
</html>
