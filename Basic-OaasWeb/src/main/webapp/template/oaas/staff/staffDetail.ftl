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
	<h6 class="modal-title">职位</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="oaas-staffDetail-form1" action="#">
<input type="hidden" name="staffId" class="form-control">
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
								<label class="col-lg-offset-1 col-lg-4 control-label">姓名<span class="text-danger">*</span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control" placeholder="必填"  name="staffName"  required="required" message="required:请填写" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">性别<span class="text-danger">*</span></label>
								<div class="col-lg-6">
									<select name="sex" class="select">
										<option value="0" selected >女</option>
										<option value="1">男</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">QQ<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="number" class="form-control" placeholder="选填数字" name="qq" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">邮箱<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="email" class="form-control" placeholder="选填" name="email" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">创建人<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control"  name="createUserName" disabled >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">最后修改人<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control" name="modifyUserName" disabled >
								</div>
							</div>
							
						</fieldset>
					</div>
					<div class="col-md-6">
						<fieldset>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">工号<span class="text-danger">*</span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control" placeholder="必填且唯一"  name="staffNumber" remote="common/verify/verifyUniqueCode.do?tableCode=OAAS_STAFF&colCode=STAFF_NUMBER"  required="required" message="required:请填写,remote:该编码已重复" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">微信<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control" placeholder="选填"  name="wechat"  message="请填写" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">手机<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="number" class="form-control" placeholder="选填数字" name="telephone" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">默认职位<span class="text-danger">*</span></label>
								<div class="col-lg-6">
									<div class="input-group">
										<input type="hidden"  name="jobId" >
										<input type="text" class="form-control" placeholder="选择职位" name="jobName"  readonly required="required" message="请选择职位" >
										<span class="input-group-btn">
											<button id="selectBtn" class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.oaas.staffDetail.selJob()">选择</button>
										</span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">创建时间<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control"  name="createTime" disabled >
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-offset-1 col-lg-4 control-label">修改时间<span class="text-danger"></span></label>
								<div class="col-lg-6">
									<input type="text" class="form-control"  name="modifyTime" disabled >
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
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.staffDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.staffDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/staff/js/staffDetail.js"></script>
</html>
