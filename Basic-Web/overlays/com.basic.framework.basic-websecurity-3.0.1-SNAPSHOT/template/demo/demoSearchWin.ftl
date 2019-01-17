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
	<h6 class="modal-title">我是高级搜索复杂的表单</h6>
</div>

<div class="modal-body" id="modalBody">
	<form class="form-horizontal" id="demo-demoPageLayout-form1" action="#">
		<div class="panel panel-flat">
			<!--标题 结束-->
			<div class="panel-body">
				<div class="col-md-4">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-4 control-label">单行文本<span class="text-danger">*</span></label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-4 control-label">单行文本<span class="text-danger">*</span></label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-4">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-4 control-label">单行文本<span class="text-danger">*</span></label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-4 control-label">单行文本<span class="text-danger">*</span></label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-4">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-4 control-label">单行文本<span class="text-danger">*</span></label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-4 control-label">单行文本<span class="text-danger">*</span></label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-4">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-4 control-label">单选按钮<span class="text-danger">*</span></label>
							<div class="col-lg-8">
								<label class="radio-inline">
								  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> 1
								</label>
								<label class="radio-inline">
								  <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> 2
								</label>
								<label class="radio-inline">
								  <input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3"> 3
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-4 control-label">多选框<span class="text-danger">*</span></label>
							<div class="col-lg-8">
								<label class="checkbox-inline">
								  <input type="checkbox" id="inlineCheckbox1" value="option1"> 1
								</label>
								<label class="checkbox-inline">
								  <input type="checkbox" id="inlineCheckbox2" value="option2"> 2
								</label>
								<label class="checkbox-inline">
								  <input type="checkbox" id="inlineCheckbox3" value="option3"> 3
								</label>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="col-md-4">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-4 control-label">下拉框<span class="text-danger">*</span></label>
							<div class="col-lg-8">
								<select class="form-control">
								  <option>1</option>
								  <option>2</option>
								  <option>3</option>
								  <option>4</option>
								  <option>5</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-4 control-label">下拉框<span class="text-danger">*</span></label>
							<div class="col-lg-8">
								<select  class="form-control">
								  <option>1</option>
								  <option>2</option>
								  <option>3</option>
								  <option>4</option>
								  <option>5</option>
								</select>
							</div>
						</div>
					</fieldset>
				</div>
			 </div>	
		</div>
	</form>		
<div class="modal-footer">
	<button type="button" class="btn btn-link" onclick="ruizhi.demo.demoSearchWin.doClose()">关闭</button>
	<button type="button" class="btn btn-primary" onclick="ruizhi.demo.demoSearchWin.doSubmit()">确定</button>
</div>
<!--页面foot-->
<#include "../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/demo/js/demoSearchWin.js"></script>
</html>
