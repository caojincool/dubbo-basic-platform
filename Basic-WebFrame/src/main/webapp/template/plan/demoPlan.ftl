<!DOCTYPE html>
<html lang="en">

<head>

<#include "../includes/page-head.ftl">	


</head>

<body>
<#include "../includes/page-title.ftl">
<!--不分页查询-->

<!-- 查询form开始 -->
<form class="form-horizontal" id="demo-demoPlan-form1" action="#">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-4">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">计划名称</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="单行文本" name="planName"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">执行人</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="单行文本" name="执行人"/>
							</div>
						</div>
					</fieldset>
				</div>	
				
				<div class="col-md-4">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">计划名称</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="单行文本" name="planName"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">执行人</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="单行文本" name="执行人"/>
							</div>
						</div>
					</fieldset>
				</div>	
				
				<div class="col-md-4">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">时间范围</label>
							<div class="col-lg-9">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="dateTime1" class="form-control datetimepicker" format="YYYY-MM-DD HH:mm:ss" value=""/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">日期时间</label>
							<div class="col-lg-9">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="dateTime2" class="form-control datetimepicker" format="YYYY-MM-DD HH:mm:ss" value=""/>
								</div>
							</div>
						</div>
					</fieldset>
				</div>	
		
			</div>
		</div>
	</div>
</form>	
<!-- /查询form结束 --> 

<!-- 按钮开始 -->  
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.demo.demoPlan.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.demo.demoPlan.add()"><i class="icon-plus2 position-left"></i>新增</a></li>
								<li><a href="javascript:"><i class="icon-pencil4 position-left"></i>编辑</a></li>
								<li><a href="javascript:"><i class="icon-x position-left"></i>删除</a></li>							
							</ul>
						</div>
					</div>
<!-- /按钮结束 --> 

<!-- table开始 --> 	
<table id="demo-demoPlan-grid1" showCheck="true" height="50%" onItemClick="ruizhi.demo.demoPlan.itemClick" idPropertyName="planId"
 rowNum="10" pagerid="demo-demoPlan-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="计划标识" width="100px" propertyName="planId" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="计划名称" width="" propertyName="planName" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="计划开始时间" width="200px" propertyName="planBeginTime" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="计划完成时间" width="200px" propertyName="planEndTime" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="状态" width="100px" propertyName="state" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="创建人" width="150px" propertyName="createStaff" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="创建时间" width="200px" propertyName="createTime" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="任务标识" width="90px" propertyName="taskId" sortType="text" align="left">&nbsp;</td>
	</tr>
</table>
<div id="demo-demoPlan-page1"></div>
<!-- /table结束 --> 	
	
<#include "../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/plan/js/demoPlan.js"></script>


</html>
