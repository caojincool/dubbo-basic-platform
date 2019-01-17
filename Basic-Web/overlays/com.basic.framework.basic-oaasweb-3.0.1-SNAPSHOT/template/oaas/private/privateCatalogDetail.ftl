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
	<h6 class="modal-title">权限目录</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="oaas-privateCatalog-form1" action="#">
<input type="hidden" name="catalogId" class="form-control">
<input type="hidden" name="parentCatalogId" class="form-control">
<input type="hidden" name="idPath" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">目录名称：</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="目录名称" name="catalogName"  id="catalogName"  required="required" message="required:请填写"> 
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">创建人：</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="" name="createUserName" readonly>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">创建时间：</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="" name="createTime" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">最后修改人：</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="" name="modifyUserName" readonly>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">最后修改时间：</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="" name="modifyTime" readonly>
							</div>
						</div>
						<#--<div class="form-group">
							<label class="col-lg-3 control-label">上级节点：</label>
							<div class="col-lg-8">
								<div class="input-group">
									<input type="hidden" id="parentprivateCatalogId" name="parentprivateCatalogId" class="form-control">
									<input type="text" id="parentprivateCatalogName" name="parentprivateCatalogName"  class="form-control" placeholder="上级节点">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.oaas.privateCatalogDetail.selprivateCatalog()">选择</button>
										</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">备注：</label>
							<div class="col-lg-8">
								<textprivateCatalog rows="5" cols="5" class="form-control" placeholder="备注" name="remarks"></textprivateCatalog>
							</div>
						</div> -->
					</fieldset>
				</div>
			</div>

		</div>
	</div>
</form>
<!--  form -->
									
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.privateCatalogDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.privateCatalogDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/private/js/privateCatalogDetail.js"></script>
</html>
