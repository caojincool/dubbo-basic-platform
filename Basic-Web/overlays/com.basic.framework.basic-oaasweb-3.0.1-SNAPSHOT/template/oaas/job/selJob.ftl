<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<!--页面head-->
<#include "../../includes/modal-head.ftl">

</head>

<body>
<!--页面title-->
<div class="modal-header bg-primary">
	<!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
	<h6 class="modal-title" id="oaas-selJob-title" >单选人员窗口</h6>
</div>

<div class="modal-body" id="modalBody">

	<div class="col-md-3" id="demo-demoPageLayout-col1">
		<div class=" ui-jqgrid ui-widget ui-widget-content ui-corner-all"> 
			<!-- 标题头 开始-->
			<div class="ui-jqgrid-hdiv ui-state-default ui-corner-top" style="width: 100%;">
				<div class="ui-jqgrid-hbox">
					<table class="ui-jqgrid-htable ui-common-table " style="width: 110%" >
						<thead>
							<tr class="ui-jqgrid-labels" >
								<th  class="ui-th-column ui-th-ltr ui-state-default" style="width: 20rem;">    
									<span class="ui-jqgrid-resize ui-jqgrid-resize-ltr" style="cursor: col-resize;">&nbsp;</span>
									<div class="ui-jqgrid-sortable">部门</div>
								</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<!-- 标题 头结束 -->
			<div class="ui-jqgrid-bdiv" style="height: 480px; width: 100%;">
				<div>
					<div>
						<div>
							<table  style="width: 100%;">
								<tbody>
									<tr class="jqgfirstrow">
										<!-- ztree 容器开始 -->
										<td id="selJob-orgTree-td" class="ztree" zTreeOnClick="ruizhi.oaas.selJob.orgClick"  style="height:0px;width:100%;"></td>
										<!--ztree 容器结束 -->
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>	
		</div>
	</div>
	<div class="col-md-9">
		<form class="form-horizontal" id="oaas-selJob-form1" action="#">
			<!--标题 结束-->
		   <div class="panel panel-flat">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-5 control-label">职位名称：</label>
							<div class="col-md-6">
								<input type="text" class="form-control" placeholder="职位名称" name="jobName">
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="col-lg-4 control-label">部门：</label>
							<div class="col-md-6">
								<input type="text" class="form-control" placeholder="选择部门" name="orgName"  message="请选择部门" >
							</div>
						</div>
					</div>	
				</div>
			  </div>
			</div>
		</form>						
		<div class="navbar navbar-default navbar-xs">
			<div class="navbar-collapse collapse" id="navbar-filter">
				<ul class="nav nav-tabs nav-tabs-solid">
					<li><a href="javascript:ruizhi.oaas.selJob.qry()"><i class="icon-search4 position-left"></i>查询</a></li>
				</ul>
			</div>
		</div>
			
		<table id="oaas-selJob-grid1" showCheck="false" height="150" idPropertyName="jobId"
			rowNum="10" pagerid="oaas-selJob-page1" onItemDblClick="ruizhi.oaas.selJob.jobDbClick"><!--onItemDblClick="itemDblClick"-->
				<tr>
					<td display="true" displayName="职位"  propertyName="jobName" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="部门"  propertyName="orgName" sortType="text" align="center">&nbsp;</td>
					<td display="false" displayName="标识"  propertyName="jobId" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
				</tr>
		</table>
		<div id="oaas-selJob-page1"></div>
	</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selJob.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.selJob.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/job/js/selJob.js"></script>
</html>
