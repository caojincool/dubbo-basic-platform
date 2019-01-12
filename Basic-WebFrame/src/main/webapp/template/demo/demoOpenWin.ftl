<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<!--页面head-->
<#include "../includes/page-head.ftl">
</head>

<body>
<!--页面title-->
<#include "../includes/page-title.ftl">
Hello World


<div class="form-group">
	<label class="col-lg-3 control-label">单行文本<span class="text-danger">*(最少长度2)</span></label>
	<div class="col-lg-9">
		<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
	</div>
</div>

<div class="form-group">
	<label class="control-label col-lg-3">选择弹窗：</label>
	<div class="col-lg-9">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="选择弹窗">
			<span class="input-group-btn">
				<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.demo.demoOpenWin.openModalWin()">选择</button>
			</span>
		</div>
	</div>
</div>

<#--
<div class="form-group">
	<label class="control-label col-lg-3">单选人员弹窗：</label>
	<div class="col-lg-9">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="选择弹窗">
			<span class="input-group-btn">
				<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.demo.demoOpenWin.openStaffSingleWin()">选择</button>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-lg-3">多选人员弹窗：</label>
	<div class="col-lg-9">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="选择弹窗">
			<span class="input-group-btn">
				<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.demo.demoOpenWin.openStaffMoreWin()">选择</button>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-lg-3">单选组织弹窗：</label>
	<div class="col-lg-9">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="选择弹窗">
			<span class="input-group-btn">
				<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.demo.demoOpenWin.openOrgSingleWin()">选择</button>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-lg-3">多选组织弹窗：</label>
	<div class="col-lg-9">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="选择弹窗">
			<span class="input-group-btn">
				<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.demo.demoOpenWin.openOrgMoreWin()">选择</button>
			</span>
		</div>
	</div>
</div>
 -->


<!--页面foot-->	
<#include "../includes/page-foot.ftl">
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/demo/js/demoOpenWin.js"></script>
</html>
