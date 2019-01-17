<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>

<#include "../includes/page-head.ftl">
</head>

<body>
<#include "../includes/page-title.ftl">

<div class="panel panel-flat">
	<div class="panel-body">
		<div class="row">
			<!-- 左边ztree开始 -->
			<div class="col-md-2" id="demo-demoPageLayout-col1">
				<div class=" ui-jqgrid ui-widget ui-widget-content ui-corner-all"> 
					<!-- 标题头 开始-->
					<div class="ui-jqgrid-hdiv ui-state-default ui-corner-top" style="width: 100%;">
						<div class="ui-jqgrid-hbox">
							<table class="ui-jqgrid-htable ui-common-table " style="width: 110%" >
								<thead>
									<tr class="ui-jqgrid-labels" >
										<th  class="ui-th-column ui-th-ltr ui-state-default" style="width: 20rem;">    
											<span class="ui-jqgrid-resize ui-jqgrid-resize-ltr" style="cursor: col-resize;">&nbsp;</span>
											<div class="ui-jqgrid-sortable">区域</div>
										</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					<!-- 标题 头结束 -->
					<!-- zTree div 开始-->
					<div class="ui-jqgrid-bdiv" style="height: 480px; width: 100%;">
						<div>
							<div>
								<div>
									<table  style="width: 100%;">
										<tbody>
											<tr class="jqgfirstrow">
												<!-- zTree 容器开始 -->
												<td id="demo-area-tree"  style="height:0px;width:100%;"></td>
												<!-- zTree 容器结束 -->
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>	
					<!-- zTree div 结束-->
				</div>
			</div>
			<!-- 左边Ztree 结束 -->
			<!-- 右边表单 开始 -->
			<div class="col-md-10" id="demo-demoPageLayout-col2">
				<!-- from表单开始 -->
				<form class="form-horizontal" id="demo-demoPageLayout-form1" action="#">
					<div class="panel panel-flat">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-3">
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
								<div class="col-md-3">
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
								<div class="col-md-3">
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
								<div class="col-md-3">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-4 control-label">单行文本<span class="text-danger">*</span></label>
											<div class="col-lg-8">
												<input type="text" class="form-control" placeholder="单行文本" name="textNormal" required="required" minlength="2" message="required:请填写,minlength:不能少于2位">
											</div>
										</div>
										<div class="form-group">
											<div class="col-lg-12">
												<button type="button" class="btn btn-primary" onclick="ruizhi.demo.demoPageLayout.advancedSearch('demo-demoPageLayout-form1')">高级查询 <i class="position-right"></i></button>
											</div>
										</div>
									</fieldset>
								</div>				
							</div>
						</div>
					</div>
				</form>	
				<!-- from表单结束 -->	
				<!-- 按钮开始 -->		
				<div class="navbar navbar-default navbar-xs">
					<div class="navbar-collapse collapse" id="navbar-filter">
						<ul class="nav nav-tabs nav-tabs-solid">
							<li><a href="javascript:ruizhi.demo.demoPageLayout.orgClick()"><i class="icon-search4 position-left"></i>查询</a></li>
							<li><a href="javascript:ruizhi.demo.demoPageLayout.addStaff()"><i class="icon-plus2 position-left"></i>增加人员</a></li>
							<li><a href="javascript:createOrg('1')"><i class="icon-pencil4 position-left"></i>编辑</a></li>
							<li><a href="javascript:remove()"><i class="icon-x position-left"></i>删除</a></li>							
						</ul>
					</div>
				</div>
				<!-- 按钮结束 -->
				<!-- table列表开始 -->
				<div>
					<table id="demo-demoPageLayout-grid2" showCheck="true" height="310" idPropertyName="id" rowNum="20" pagerid="demo-demoPageLayout-page2" ><!--onItemDblClick="itemDblClick"-->
						<tr>
							<td display="true" displayName="姓名" width="300" propertyName="userName" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="生日" width="400" propertyName="birthDay" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="备注" width="500" propertyName="comments" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="标识" width="300" propertyName="id" sortType="text" align="center">&nbsp;</td>
						</tr>
					</table>			
					<div id="demo-demoPageLayout-page2"></div>
				</div>
				<!-- table列表结束 -->
			</div>
			<!-- 右边表单 结束 -->
		</div>
	</div>
</div>



	
<#include "../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/demo/js/demoPageLayout.js"></script>

</html>
