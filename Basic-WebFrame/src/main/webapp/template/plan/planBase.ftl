<!DOCTYPE html>
<html lang="en">

<head>

<#include "../includes/page-head.ftl">	


</head>

<body>
<#include "../includes/page-title.ftl">
<!--不分页查询-->

<form class="form-horizontal" id="plan-planBase-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-2 control-label">计划名称</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="单行文本" name="planName"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label">执行人</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="单行文本" name="execStaffName"/>
							</div>
						</div>
					</fieldset>
				</div>	
				
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-2 control-label">开始时间</label>
							<div class="col-lg-8">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="planBeginTime" class="form-control datetimepicker" format="YYYY-MM-DD HH:mm:ss" value=""/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label">结束时间</label>
							<div class="col-lg-8">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="planEndTime" class="form-control datetimepicker" format="YYYY-MM-DD HH:mm:ss" value=""/>
								</div>
							</div>
						</div>
					</fieldset>
				</div>	
		
			</div>
		</div>
	</div>
</form>	

					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.plan.planBase.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.plan.planBase.create()"><i class="icon-plus2 position-left"></i>新增</a></li>
								<li><a href="javascript:ruizhi.plan.planBase.modify()"><i class="icon-bianji position-left"></i>编辑</a></li>
								<li><a href="javascript:ruizhi.plan.planBase.issued()"><i class="icon-download4 position-left"></i>下达</a></li>
								<li><a href="javascript:ruizhi.plan.planBase.del()"><i class="icon-x position-left"></i>作废</a></li>							
								<li><a href="javascript:ruizhi.plan.planBase.situation()"><i class="icon-versions position-left"></i>执行情况</a></li>							
							</ul>
						</div>
					</div>

<table id="plan-planBase-grid1" showCheck="true" height="300" idPropertyName="planId"
 rowNum="10" pagerid="plan-planBase-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="计划标识" width="" propertyName="planId" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="计划名称" width="" propertyName="planName" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="计划开始时间" width="" propertyName="planBeginTime" sortType="text" align="right">&nbsp;</td>
		<td display="true" displayName="计划完成时间" width="" propertyName="planEndTime" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="状态" width="" propertyName="state" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="状态" width="" propertyName="stateName" formatter="ruizhi.plan.planBase.stateTranslate" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="创建人" width="" propertyName="createStaffName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="创建时间" width="" propertyName="createTime" formatter="ruizhi.plan.planBase.dateTimeTranslate" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="任务标识" width="" propertyName="taskId" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="执行人" width="" propertyName="execStaff" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="执行人名字" width="" propertyName="execStaffName" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="plan-planBase-page1"></div>

	
<#include "../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/plan/js/planBase.js"></script>


</html>
