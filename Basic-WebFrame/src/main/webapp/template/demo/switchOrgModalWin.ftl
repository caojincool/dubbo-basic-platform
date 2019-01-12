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
	<h6 class="modal-title">切换组织</h6>
</div>

<div class="modal-body" id="modalBody">
	<form id="demo-switchOrg-form1" class="form-horizontal">
		<div class="radio">
		  <label>
		    <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
		   	中国 --中科--总经理
		  </label>
		</div>
		<div class="radio">
		  <label>
		    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
		    中国 --中科--总经理2
		  </label>
		</div>
	</form>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-link" onclick="ruizhi.demo.switchOrgModalWin.doClose()">关闭</button>
	<button type="button" class="btn btn-primary" onclick="ruizhi.demo.switchOrgModalWin.doSubmit()">确定</button>
</div>
<!--页面foot-->
<#include "../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/demo/js/switchOrgModalWin.js"></script>
</html>
