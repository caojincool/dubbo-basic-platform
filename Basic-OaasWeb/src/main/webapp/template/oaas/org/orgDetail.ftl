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
	<h6 class="modal-title">部门</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="oaas-orgDetail-form1" action="#">
<input type="hidden" name="orgId" class="form-control">
<input type="hidden" name="parentOrgId" class="form-control">

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
							<label class="col-lg-offset-2 col-lg-2 control-label">部门编码<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control" placeholder="必填且唯一"  name="orgCode" remote="common/verify/verifyUniqueCode.do?tableCode=OAAS_ORG&colCode=ORG_CODE"  required="required" message="required:请填写,remote:该编码已重复" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">部门名称<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control" placeholder="必填"  name="orgName"  required="required" message="请填写" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">显示顺序<span class="text-danger"></span></label>
							<div class="col-lg-6">
								<input type="number" class="form-control" placeholder="选填数字" name="displayIndex" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">所属公司<span class="text-danger"></span></label>
							<div class="col-lg-6">
								<div class="input-group">
									<input type="hidden"  name="companyId" >
									<input type="text" class="form-control" placeholder="选择公司" name="companyName" readonly required="required" message="请选择公司" >
									<span class="input-group-btn">
										<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.oaas.orgDetail.selCompany()">选择</button>
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">所属区域<span class="text-danger"></span></label>
							<div class="col-lg-6">
								<div class="input-group">
									<input type="hidden"  name="areaId" >
									<input type="text" class="form-control" placeholder="选择区域" name="areaName" readonly required="required" message="请选择区域" >
									<span class="input-group-btn">
										<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.oaas.orgDetail.selArea()">选择</button>
									</span>
								</div>
							</div>
						</div>
					<!-- <div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">级别<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control"  name="orgName" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">创建人<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control"  readonly name="createUserName" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">创建时间<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control"  readonly name="createTime">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">最后修改人<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control"  readonly name="modifyUserName">
								<input type="hidden" name="modifyUserId">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">最后修改时间<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input type="text" class="form-control"  readonly name="modifyTime" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">ID路径<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input id="idPath" type="text" class="form-control"   readonly name="idPath" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">编码路径<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input id="codePath" type="text" class="form-control"  readonly name="codePath" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-offset-2 col-lg-2 control-label">名称路径<span class="text-danger">*</span></label>
							<div class="col-lg-6">
								<input id="namePath" type="text" class="form-control"  readonly name="namePath" >
							</div>
						</div> -->
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
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.orgDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.orgDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/org/js/orgDetail.js"></script>
</html>
