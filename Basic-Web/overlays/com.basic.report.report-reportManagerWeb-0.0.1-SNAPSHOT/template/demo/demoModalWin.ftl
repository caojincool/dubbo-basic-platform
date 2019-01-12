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
	<h6 class="modal-title">我是模态窗口</h6>
</div>

<div class="modal-body" id="modalBody">

<!--
	<div class="form-group">
		<label class="col-lg-3 control-label">单行文本<span class="text-danger">*(最少长度2)</span></label>
		<div class="col-lg-9">
			<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
		</div>
	</div>
	<div class="form-group">
		<label class="col-lg-3 control-label">单行文本<span class="text-danger">*(最少长度2)</span></label>
		<div class="col-lg-9">
			<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
		</div>
	</div>
	<div class="form-group">
		<label class="col-lg-3 control-label">单行文本<span class="text-danger">*(最少长度2)</span></label>
		<div class="col-lg-9">
			<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
		</div>
	</div>
	<div class="form-group">
		<label class="col-lg-3 control-label">单行文本<span class="text-danger">*(最少长度2)</span></label>
		<div class="col-lg-9">
			<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
		</div>
	</div>
	<div class="form-group">
		<label class="col-lg-3 control-label">单行文本<span class="text-danger">*(最少长度2)</span></label>
		<div class="col-lg-9">
			<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
		</div>
	</div>
-->
	<div class="panel-body">
		<div class="row">
			<div class="col-md-12" id="demo-demoModalWin-col1">
				<table id="demo-demoModalWin-grid1" showCheck="false" height="150" idPropertyName="id"><!--onItemDblClick="itemDblClick"-->
					<tr>
						<td display="true" displayName="姓名"  propertyName="userName" sortType="text" align="left">&nbsp;</td>
						<td display="true" displayName="生日"  propertyName="birthDay" sortType="text" align="right">&nbsp;</td>
						<td display="true" displayName="备注"  propertyName="comments" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="标识"  propertyName="id" sortType="text" align="center">&nbsp;</td>
					</tr>
				</table>
			
			
			</div>
		</div>
	</div>

	<div class="text-center">
		<button type="button" class="btn btn-primary" onclick="ruizhi.demo.demoModalWin.add()">添加 <i class="position-right"></i></button>
		<button type="button" class="btn btn-primary" onclick="ruizhi.demo.demoModalWin.del()">删除<i class="position-right"></i></button>
	</div>
	
	<table id="demo-demoModalWin-grid2" showCheck="false" height="150" idPropertyName="id"><!--onItemDblClick="itemDblClick"-->
		<tr>
			<td display="true" displayName="姓名" width="300" propertyName="userName" sortType="text" align="left">&nbsp;</td>
			<td display="true" displayName="生日" width="400" propertyName="birthDay" sortType="text" align="right">&nbsp;</td>
			<td display="true" displayName="备注" width="700" propertyName="comments" sortType="text" align="center">&nbsp;</td>
			<td display="false" displayName="标识" propertyName="id" sortType="text" align="center">&nbsp;</td>
		</tr>
	</table>
			
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-link" onclick="ruizhi.demo.demoModalWin.openWin()">打开窗口</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.demo.demoModalWin.doClose()">关闭</button>
	<button type="button" class="btn btn-primary" onclick="ruizhi.demo.demoModalWin.doSubmit()">确定</button>
</div>
<!--页面foot-->
<#include "../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/demo/js/demoModalWin.js"></script>
</html>
