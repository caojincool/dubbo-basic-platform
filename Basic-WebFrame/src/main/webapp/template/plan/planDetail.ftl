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
	<h6 class="modal-title">计划管理</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="plan-planDetail-form1" action="#">
<input type="hidden" name="planId" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">计划名称</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="计划名称" name="planName">
							</div>
						</div>

						<div class="form-group">
				        	<label class="col-lg-3 control-label">计划类型</label>
				        	<div class="col-lg-4">
				                <select name="planType" class="select" onchange="ruizhi.plan.planDetail.planTypeChange(this.name)">
				                	<option value="TERMINAL">终端</option>
				                    <option value="AREA">区域</option>
				                </select>
				            </div>
						</div>


						<div class="form-group">
							<label class="col-lg-3 control-label">计划开始时间</label>
							<div class="col-lg-4">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="planBeginTime" class="form-control datetimepicker" format="YYYY-MM-DD HH:mm:ss" value="">
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">计划完成时间</label>
							<div class="col-lg-4">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="planEndTime" class="form-control datetimepicker" format="YYYY-MM-DD HH:mm:ss" value="">
								</div>
							</div>
						</div>


						<div class="form-group">
							<label class="col-lg-3 control-label">执行人</label>
							<div class="col-lg-4">
								<div class="input-group">
									<input type="hidden" name="execStaff" class="form-control">
									<input type="text" name="execStaffName" readonly="readonly" class="form-control" placeholder="选择弹窗">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.plan.planDetail.openStaffSingleWin()">选择</button>
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

<!-- tab表格开始 -->
<ul id="plan-planDetail-tab1" class="nav nav-tabs">
   <li>
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
   <div class="tab-pane fade" id="taskTemplate">
   
	<div class="navbar navbar-default navbar-xs">
		<div class="navbar-collapse collapse" id="navbar-filter">
			<ul class="nav nav-tabs nav-tabs-solid">
				<li><a href="javascript:ruizhi.plan.planDetail.taskTemplateAdd()"><i class="icon-plus2 position-left"></i>新增</a></li>
				<li><a href="javascript:ruizhi.plan.planDetail.taskTemplateDel()"><i class="icon-x position-left"></i>删除</a></li>							
			</ul>
		</div>
	</div>

				<table id="plan-planDetail-grid1" showCheck="false" height="150" idPropertyName="taskTemplateId"
				rowNum="10" pagerid="plan-planDetail-page1"><!--onItemDblClick="itemDblClick"-->
					<tr>
						<td display="true" displayName="模板名称"  propertyName="taskTemplateName" sortType="text" align="left">&nbsp;</td>
						<td display="true" displayName="创建人"  propertyName="createStaffName" sortType="text" align="right">&nbsp;</td>
						<td display="true" displayName="创建时间"  propertyName="createTime" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="终端类型标识"  propertyName="terminalClassId" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="终端类型父标识"  propertyName="parentId" sortType="text" align="right">&nbsp;</td>
						<td display="false" displayName="标识"  propertyName="taskTemplateId" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="计划与任务模板关联标识"  propertyName="planTaskTemplateId" sortType="text" align="center">&nbsp;</td>
					</tr>
				</table>
				<div id="plan-planDetail-page1"></div>	
					
   </div>
   <div class="tab-pane fade" id="terminal">

	<div class="navbar navbar-default navbar-xs">
		<div class="navbar-collapse collapse" id="navbar-filter">
			<ul class="nav nav-tabs nav-tabs-solid">
				<li><a href="javascript:ruizhi.plan.planDetail.terminalObjectAdd()"><i class="icon-plus2 position-left"></i>新增</a></li>
				<li><a href="javascript:ruizhi.plan.planDetail.terminalObjectDel()"><i class="icon-x position-left"></i>删除</a></li>							
			</ul>
		</div>
	</div>
	
				<table id="plan-planDetail-grid2" showCheck="false" height="150" idPropertyName="objectId"
				rowNum="10" pagerid="plan-planDetail-page2"><!--onItemDblClick="itemDblClick"-->
					<tr>
						<td display="true" displayName="名称"  propertyName="objectName" sortType="text" align="left">&nbsp;</td>
						<td display="true" displayName="类型"  propertyName="terminalClassName" sortType="text" align="left">&nbsp;</td>
						<td display="true" displayName="地址"  propertyName="addrDetail" sortType="text" align="right">&nbsp;</td>
						<td display="true" displayName="模板"  propertyName="taskTemplateName" sortType="text" align="right">&nbsp;</td>
						<td display="false" displayName="终端类型标识"  propertyName="terminalClassId" sortType="text" align="right">&nbsp;</td>
						<td display="false" displayName="标识"  propertyName="objectId" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="模板标识"  propertyName="taskTemplateId" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="计划检查对象标识"  propertyName="planObjectId" sortType="text" align="center">&nbsp;</td>
					</tr>
				</table>
				<div id="plan-planDetail-page2"></div>
				
   </div>
   <div class="tab-pane fade" id="area">

	<div class="navbar navbar-default navbar-xs">
		<div class="navbar-collapse collapse" id="navbar-filter">
			<ul class="nav nav-tabs nav-tabs-solid">
				<li><a href="javascript:ruizhi.plan.planDetail.planAreaAdd()"><i class="icon-plus2 position-left"></i>新增</a></li>
				<li><a href="javascript:ruizhi.plan.planDetail.planAreaDel()"><i class="icon-x position-left"></i>删除</a></li>							
			</ul>
		</div>
	</div>
	
				<table id="plan-planDetail-grid3" showCheck="false" height="150" idPropertyName="areaId"
				rowNum="10" pagerid="plan-planDetail-page3"><!--onItemDblClick="itemDblClick"-->
					<tr>
						<td display="true" displayName="名称" propertyName="areaName" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="数量" propertyName="objectCount" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="编码" propertyName="areaCode" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="路径" propertyName="namePath" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="标识" propertyName="areaId" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="对象区域标识" propertyName="planAreaId" sortType="text" align="center">&nbsp;</td>
					</tr>
				</table>
				<div id="plan-planDetail-page3"></div>
				
   </div>
</div>
<!-- tab表格结束 -->

</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.plan.planDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.plan.planDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/plan/js/planDetail.js"></script>
</html>
