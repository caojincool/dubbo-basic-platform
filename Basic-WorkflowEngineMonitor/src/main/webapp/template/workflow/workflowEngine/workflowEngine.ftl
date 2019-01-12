<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<form class="form-horizontal" id="workflow-workflowEngine-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">流程模板编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="processDefineKey" name="processDefineKey"/>
							</div>
						</div>
					</fieldset>
				</div>	
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">流程实例id：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="processInstanceId" name="processInstanceId"/>
							</div>
						</div>
					</fieldset>
				</div>	
		
			</div>

<div class="row" style="color: red;">
请注意：</br>
1.两项都填，只查流程实例id</br>
</div>

		</div>
	</div>
</form>	

					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.workflow.workflowEngine.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
							</ul>	
						</div>
					</div>

<table id="workflow-workflowEngine-grid1" showCheck="false" height="300" idPropertyName="id"
 onItemClick="ruizhi.workflow.workflowEngine.onItemClick"><!-- rowNum="10" pagerid="workflow-workflowEngine-page1" onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="流程实例id" width="" propertyName="id" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="流程定义id" width="" propertyName="processDefinitionId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="流程模板编码" width="" propertyName="processDefinitionKey" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="流程名称" width="" propertyName="processDefinitionName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="开始时间" width="" propertyName="startTime" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="结束时间" width="" propertyName="endTime" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<!--<div id="workflow-workflowEngine-page1"></div>-->

<ul id="workflow-workflowEngine-tab1" class="nav nav-tabs">
   <li>
      <a href="#cliWorkflowCommand" data-toggle="tab">
                         客户端命令
      </a>
   </li>
   
   <li>
      <a href="#serWorkflowCommandExec" data-toggle="tab">
                         服务端命令执行
      </a>
   </li>
   
   <li>
      <a href="#serWorkflowNotice" data-toggle="tab">
                         服务端通知
      </a>
   </li>
   
   <li>
      <a href="#cliWorkflowNoticeExec" data-toggle="tab">
                         客户端通知执行
      </a>
   </li>

</ul>
<div id="workflow-workflowEngine-tabContent1" class="tab-content">

   <div class="tab-pane fade" id="cliWorkflowCommand">
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.workflow.workflowEngine.rerunCliWorkflowCommand()"><i class="icon-pencil4 position-left"></i>重新执行</a></li>
							</ul>	
						</div>
					</div>

<table id="workflow-workflowEngine-grid2" showCheck="false" height="300" idPropertyName="commandId">
	<tr>
		<td display="true" displayName="请求标识" width="" propertyName="commandId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="交互序列" width="" propertyName="serialNo" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="流程模板编码" width="" propertyName="processDefineKey" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="业务单标识" width="" propertyName="busiOrderId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="任务实例标识" width="" propertyName="taskId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="创建时间" width="" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="请求参数" width="" propertyName="param" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="异常编码" width="" propertyName="errorCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="发送结果标识" width="" propertyName="sendFlag" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="发送结果" width="" propertyName="sendResult" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="指令类型" width="" propertyName="commandType" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="流程实例标识" width="" propertyName="processInstanceId" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
   </div>
   
   <div class="tab-pane fade" id="serWorkflowCommandExec">
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<!--
								<li><a href="javascript:ruizhi.workflow.workflowEngine.rerunSerWorkflowCommandExec()"><i class="icon-pencil4 position-left"></i>重新执行</a></li>
								-->
							</ul>	
						</div>
					</div>

<table id="workflow-workflowEngine-grid3" showCheck="false" height="300" idPropertyName="execId">
	<tr>
		<td display="true" displayName="执行标识" width="" propertyName="execId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="请求标识" width="" propertyName="commandId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="交互序列" width="" propertyName="serialNo" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="流程模板编码" width="" propertyName="processDefineKey" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="业务单标识" width="" propertyName="busiOrderId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="任务实例标识" width="" propertyName="taskId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="创建时间" width="" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="请求参数" width="" propertyName="param" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="异常编码" width="" propertyName="errorCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="执行结果标识" width="" propertyName="execFlag" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="执行结果" width="" propertyName="execResult" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="指令类型" width="" propertyName="commandType" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="流程实例标识" width="" propertyName="processInstanceId" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
   </div>
   
   <div class="tab-pane fade" id="serWorkflowNotice">
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.workflow.workflowEngine.rerunSerWorkflowNotice()"><i class="icon-pencil4 position-left"></i>重新执行</a></li>
							</ul>	
						</div>
					</div>

<table id="workflow-workflowEngine-grid4" showCheck="false" height="300" idPropertyName="noticeId">
	<tr>
		<td display="true" displayName="通知标识" width="" propertyName="noticeId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="创建时间" width="" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="通知类型" width="" propertyName="noticeType" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="业务单标识" width="" propertyName="busiOrderId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="流程实例标识" width="" propertyName="processInstanceId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="任务标识" width="" propertyName="taskId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="环节编码" width="" propertyName="taskDefinitionKey" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="异常编码" width="" propertyName="errorCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="发送结果标识" width="" propertyName="sendFlag" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="发送结果" width="" propertyName="sendResult" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
   </div>
   
   <div class="tab-pane fade" id="cliWorkflowNoticeExec">
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<!--
								<li><a href="javascript:ruizhi.workflow.workflowEngine.rerunCliWorkflowNoticeExec()"><i class="icon-pencil4 position-left"></i>重新执行</a></li>
								-->
							</ul>	
						</div>
					</div>

<table id="workflow-workflowEngine-grid5" showCheck="false" height="300" idPropertyName="execId">
	<tr>
		<td display="true" displayName="执行标识" width="" propertyName="execId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="通知标识" width="" propertyName="noticeId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="创建时间" width="" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="通知类型" width="" propertyName="noticeType" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="业务单标识" width="" propertyName="busiOrderId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="流程实例标识" width="" propertyName="processInstanceId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="任务标识" width="" propertyName="taskId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="环节编码" width="" propertyName="taskDefinitionKey" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="异常编码" width="" propertyName="errorCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="执行结果标识" width="" propertyName="execFlag" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="执行结果" width="" propertyName="execResult" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
   </div>
</div>

	
<#include "../../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/workflow/workflowEngine/js/workflowEngine.js"></script>


</html>
