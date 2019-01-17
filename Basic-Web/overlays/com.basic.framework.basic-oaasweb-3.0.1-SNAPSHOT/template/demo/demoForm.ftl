<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>

<#include "../includes/page-head.ftl">
</head>

<body>
<#include "../includes/page-title.ftl">

<!-- form -->
<form class="form-horizontal" id="demo-demoForm-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 开始 可以省略此部分-->
		<div class="panel-heading">
			<h5 class="panel-title">多列表单(标题可以省略)</h5>
			<div class="heading-elements">
				<ul class="icons-list">
            		<li><a data-action="collapse"></a></li>
            		<li><a data-action="reload"></a></li>
            		<li><a data-action="close"></a></li>
            	</ul>
        	</div>
		</div>
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>
						<legend class="text-semibold"><i class="icon-reading position-left"></i>分组1-基础组件(可以省略)</legend>

						<div class="form-group">
							<label class="col-lg-2 control-label">单行文本<span class="text-danger">*(最少长度2)</span></label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">单行文本<span class="text-danger">*(最大长度10)</span></label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="单行文本" name="textNorma2" required="required" maxlength="10" message="请填写">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">单行文本<span class="text-danger">*(长度5到10)</span></label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="单行文本" name="textNorma3" required="required" rangelength="[5,10]" message="required:请填写,rangelength:5到10位">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">只读文本</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="只读文本" readonly="readonly" value="不可编辑" name="textReadonly">
							</div>
						</div>


						<div class="form-group">
							<label class="col-lg-2 control-label">密码</label>
							<div class="col-lg-8">
								<input type="password" class="form-control" placeholder="密码" name="password">
							</div>
						</div>

						<div class="form-group">
				        	<label class="col-lg-2 control-label">单选<span class="text-danger">（改变有事件触发）</span></label>
				        	<div class="col-lg-8">
				                <select name="selectNormal" class="select" onchange="ruizhi.demo.demoForm.change1(this.name)">
				                	<option value="opt0">全部</option>
				                    <option value="opt1">选项 1</option>
				                    <option value="opt2">选项 2</option>
				                    <option value="opt3">选项 3</option>
				                    <option value="opt4">选项 4</option>
				                    <option value="opt5">选项 5</option>
				                    <option value="opt6">选项 6</option>
				                    <option value="opt7">选项 7</option>
				                    <option value="opt8">选项 8</option>
				                </select>
				            </div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">多选框<span class="text-danger">（改变有事件触发）</span></label>
							<div class="col-lg-8">
								<select name="selectMuti" multiple="multiple" data-placeholder="请选择..." class="select" onchange="ruizhi.demo.demoForm.change2(this.name)">
									<option value="0">选项 0</option>
									<option value="1">选项 1</option>
									<option value="2">选项 2</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">单选框2<span class="text-danger">(无待选项)</span></label>
							<div class="col-lg-8">
								<select name="selectNormal2" data-placeholder="请选择..." class="select" >
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">多选框2<span class="text-danger">(无待选项)</span></label>
							<div class="col-lg-8">
								<select name="selectMuti2" multiple="multiple" data-placeholder="请选择..." class="select" >
								</select>
							</div>
						</div>


						<div class="form-group">
							<label class="col-lg-2 control-label">多行文本</label>
							<div class="col-lg-8">
								<textarea rows="5" cols="5" class="form-control" placeholder="多行文本" name="testArea1"></textarea>
							</div>
						</div>


						<div class="form-group">
							<label class="col-lg-2 control-label">日期时间</label>
							<div class="col-lg-8">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="dateTime1" class="form-control datetimepicker" format="YYYY-MM-DD HH:mm:ss" value="">
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">日期</label>
							<div class="col-lg-8">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="date1" class="form-control datetimepicker" format="YYYY-MM-DD" value="">
								</div>

							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">时间</label>
							<div class="col-lg-8">
								<input class="form-control" type="time" name="time1">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label">弹窗选择</label>
							<div class="col-lg-8">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="选择弹窗">
									<span class="input-group-btn">
										<button class="btn btn-primary" type="button" onclick="ruizhi.demo.demoForm.openModalWin()">选择</button>
									</span>
								</div>
							</div>
						</div>

<!--
						<div class="form-group">
							<label class="col-lg-2 control-label">附件</label>
							<div class="col-lg-10"> 
								<input type="file" class="file-styled" name="file1">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label">弹窗</label>
							<div class="col-lg-10">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="选择弹窗">
									<span class="input-group-btn">
										<button class="btn btn-primary" type="button" onclick="selStaff()">选择</button>
										<button class="btn btn-primary" type="button" data-toggle="modal" data-target="#modal_theme_primary" href="selStaff.html">选择</button>
									</span>
								</div>
							</div>
						</div>
-->
					</fieldset>
				</div>
			</div>

			<div class="text-right">
				<button type="button" class="btn btn-primary" onclick="ruizhi.demo.demoForm.setInitValue()">设置初始值 <i class="position-right"></i></button>
				<button type="button" class="btn btn-primary" onclick="ruizhi.demo.demoForm.setSelectOption()">设置单选框待选项<i class="position-right"></i></button>
				<button type="button" class="btn btn-primary" onclick="ruizhi.demo.demoForm.setSelectOptionMuti()">设置多选框待选项<i class="position-right"></i></button>
				<button type="button" class="btn btn-primary" onclick="ruizhi.demo.demoForm.doSubmit()">提交 <i class="icon-arrow-right14 position-right"></i></button>
			</div>
		</div>
	</div>
</form>
<!--  form -->


	
<#include "../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/demo/js/demoForm.js"></script>


</html>
