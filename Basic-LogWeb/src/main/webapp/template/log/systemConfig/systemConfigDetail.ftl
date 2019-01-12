<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<!--页面head-->
<#include "../../includes/modal-head.ftl">
	
</head>

<body>
<!--页面title start-->
<div class="modal-header bg-primary">
	<!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
	<h6 class="modal-title">系统日志配置表</h6>
</div>
<!--页面title end-->

<div class="modal-body" id="">

<!-- 提交form表单 start -->
<form class="form-horizontal" id="log-systemConfigDetail-form1" action="#">
<input type="hidden" name="configId" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">请求URL：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="请求URL" name="requestUrl">
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">是否记录日志：</label>
							<div class="col-lg-9">
								<select class="select" name="requestFlag">
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">是否记录内容：</label>
							<div class="col-lg-9">
								<select class="select" name="requestContentFlag">
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
			</div>

		</div>
	</div>
</form>
<!-- 提交form表单 end -->

</div>

<!-- 下面页面按钮 start -->
<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.log.systemConfigDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.log.systemConfigDetail.doClose()">关闭</button>
</div>
<!-- 下面页面按钮 end -->

<#include "../../includes/modal-foot.ftl">
	
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/log/systemConfig/js/systemConfigDetail.js"></script>
<!-- 引入该页面js end -->

</html>
