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
	<h6 class="modal-title">计划任务添加窗口</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="demo-demoPlanAdd-form1" action="#">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">计划名称<span class="text-danger">*(最少长度2)</span></label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="计划名称" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
							</div>
						</div>

						<div class="form-group">
				        	<label class="col-lg-3 control-label">计划类型</label>
				        	<div class="col-lg-4">
				                <select name="selectNormal" class="select">
				                	<option value="TERMINAL">终端</option>
				                    <option value="AREA">区域</option>
				                </select>
				            </div>
						</div>


						<div class="form-group">
							<label class="col-lg-3 control-label">开始时间</label>
							<div class="col-lg-4">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="dateTime1" class="form-control datetimepicker" format="YYYY-MM-DD HH:mm:ss" value="">
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">完成时间</label>
							<div class="col-lg-4">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="dateTime1" class="form-control datetimepicker" format="YYYY-MM-DD HH:mm:ss" value="">
								</div>
							</div>
						</div>


						<div class="form-group">
							<label class="col-lg-3 control-label">执行人</label>
							<div class="col-lg-4">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="选择弹窗">
									<span class="input-group-btn">
										<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.demo.demoPlanAdd.openModalWin()">选择</button>
									</span>
								</div>
							</div>
						</div>

					</fieldset>
				</div>
			</div>

		</div>
	</div>
</form>
<!--  form -->

<ul id="myTab" class="nav nav-tabs">
   <li class="active">
      <a href="#taskTemplate" data-toggle="tab">
                         任务模板
      </a>
   </li>
   
   <li>
      <a href="#terminal" data-toggle="tab">
      	 终端对象
      </a>
   </li>
   
   <li>
      <a href="#area" data-toggle="tab">
      	 区域
      </a>
   </li>

</ul>

<div id="myTabContent" class="tab-content">
   <div class="tab-pane fade in active" id="taskTemplate">
   
   							<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.demo.demoPlanAdd.taskTemplateAdd()"><i class="icon-plus2 position-left"></i>新增</a></li>
								<li><a href="javascript:"><i class="icon-pencil4 position-left"></i>编辑</a></li>
								<li><a href="javascript:"><i class="icon-x position-left"></i>删除</a></li>							
							</ul>
						</div>
					</div>
					
      <p>W3Cschoool菜鸟教程是一个提供最新的web技术站点，本站免费提供了建站相关的技术文档，帮助广大web技术爱好者快速入门并建立自己的网站。菜鸟先飞早入行——学的不仅是技术，更是梦想。</p>
   </div>
   <div class="tab-pane fade" id="terminal">
      <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple 
      TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
   </div>
   <div class="tab-pane fade" id="area">
      <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple 
      TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
   </div>
</div>

</div>

<div class="modal-footer">
	<button type="button" class="btn btn-link" onclick="ruizhi.demo.demoPlanAdd.doClose()">关闭</button>
	<button type="button" class="btn btn-primary" onclick="ruizhi.demo.demoPlanAdd.doSubmit()">确定</button>
</div>
<!--页面foot-->
<#include "../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/plan/js/demoPlanAdd.js"></script>
</html>
