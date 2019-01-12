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
	<h6 class="modal-title">gid服务</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="gid-gidServerDetail-form1" action="#">
<input type="hidden" name="pageDateType" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">编码</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="编码" name="gidCode" remote="common/verify/verifyUniqueCode.do?tableCode=PUB_GID_SERVER&colCode=GID_CODE" required="required" message="required:请填写,remote:该编码已重复">
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">名称</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="名称" name="gidName">
							</div>
						</div>	
					
						<div class="form-group">
							<label class="col-lg-3 control-label">是否使用</label>
							<div class="col-lg-8">
								<input type="number" class="form-control" placeholder="1" name="isUse" required="required" message="required:请填写">
							</div>
						</div>	
					
						<div class="form-group">
							<label class="col-lg-3 control-label">当前值</label>
							<div class="col-lg-8">
								<input type="number" class="form-control" placeholder="1" name="currValue" required="required" message="required:请填写">
							</div>
						</div>	
					
						<div class="form-group">
							<label class="col-lg-3 control-label">缓存大小</label>
							<div class="col-lg-8">
								<input type="number" class="form-control" placeholder="50" name="cacheSize" required="required" message="required:请填写">
							</div>
						</div>	
					
						<div class="form-group">
							<label class="col-lg-3 control-label">客户端缓存</label>
							<div class="col-lg-8">
								<input type="number" class="form-control" placeholder="100" name="clientCacheSize" required="required" message="required:请填写">
							</div>
						</div>	
					
						<div class="form-group">
							<label class="col-lg-3 control-label">步长</label>
							<div class="col-lg-8">
								<input type="number" class="form-control" placeholder="1" name="increamentBy" required="required" message="required:请填写">
							</div>
						</div>	

						
						<div class="form-group">
							<label class="col-lg-3 control-label">备注</label>
							<div class="col-lg-8">
								<textarea rows="5" cols="5" class="form-control" placeholder="备注" name="remark"></textarea>
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
	<button type="button" class="btn btn-primary" onclick="ruizhi.gid.gidServerDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.gid.gidServerDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/gid/gidServer/js/gidServerDetail.js"></script>
</html>
