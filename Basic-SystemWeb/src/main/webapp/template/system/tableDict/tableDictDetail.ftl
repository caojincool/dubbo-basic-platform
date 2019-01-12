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
	<h6 class="modal-title">字典表</h6>
</div>
<!--页面title end-->

<div class="modal-body" id="">

<!-- 提交form表单 start -->
<form class="form-horizontal" id="system-tableDictDetail-form1" action="#">
<input type="hidden" name="dictId" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">表编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="表编码" name="tableCode">
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">表名称：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="表名称" name="tableName">
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">字段编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="字段编码" name="colCode">
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">字段名称：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="字段名称" name="colName">
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">字段值：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="字段值" name="colValue">
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">字段值中文：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="字段值中文" name="colText">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">备注：</label>
							<div class="col-lg-9">
								<textarea rows="5" cols="5" class="form-control" placeholder="备注" name="remarks"></textarea>
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
	<button type="button" class="btn btn-primary" onclick="ruizhi.system.tableDictDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.system.tableDictDetail.doClose()">关闭</button>
</div>
<!-- 下面页面按钮 end -->

<#include "../../includes/modal-foot.ftl">
	
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/system/tableDict/js/tableDictDetail.js"></script>
<!-- 引入该页面js end -->

</html>
