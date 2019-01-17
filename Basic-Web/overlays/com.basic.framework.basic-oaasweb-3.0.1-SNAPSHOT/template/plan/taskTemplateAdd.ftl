<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<!--页面head-->
<#include "../includes/modal-head.ftl">
	
</head>

<body>
<!--页面title-->
<div class="modal-header bg-primary">
	<!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
	<h6 class="modal-title">任务模板添加窗口</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="demo-taskTemplateAdd-form1" action="#">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>

						<div class="form-group">
				        	<label class="col-lg-2 control-label">任务模板</label>
				        	<div class="col-lg-10" style="">
				                <select name="selectNormal" class="select">
				                	<option value="1">任务模板1</option>
				                    <option value="2">任务模板2</option>
				                    <option value="3">任务模板3</option>
				                    <option value="4">任务模板4</option>
				                </select>
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
	<button type="button" class="btn btn-link" onclick="ruizhi.demo.taskTemplateAdd.doClose()">关闭</button>
	<button type="button" class="btn btn-primary" onclick="ruizhi.demo.taskTemplateAdd.doSubmit()">确定</button>
</div>
<!--页面foot-->
<#include "../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/plan/js/taskTemplateAdd.js"></script>
</html>
