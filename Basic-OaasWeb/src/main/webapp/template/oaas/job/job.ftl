<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">

<div class="panel panel-flat">
	<div class="panel-body">
		<div class="row">
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
												<td id="org-orgTree-job" class="ztree" zTreeOnClick="ruizhi.oaas.job.treeClick"  style="height:0px;width:100%;"></td>
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
    		<div class="col-md-10" id="demo-demoPageLayout-col2">
				<div class="navbar navbar-default navbar-xs">
					<div class="navbar-collapse collapse" id="navbar-filter">
						<ul class="nav nav-tabs nav-tabs-solid">
							<li><a href="javascript:ruizhi.oaas.job.create()"><i class="icon-plus2 position-left"></i>新增</a></li>
							<li><a href="javascript:ruizhi.oaas.job.modify()"><i class="icon-plus2 position-left"></i>修改</a></li>
							<li><a href="javascript:ruizhi.oaas.job.del()"><i class="icon-x position-left"></i>删除</a></li>							
						</ul>
					</div>
				</div>
				<!-- 下面展示数据table start -->
				<table id="oaas-job-grid1" showCheck="true" height="300" idPropertyName="jobId"
				 	rowNum="10" pagerid="oaas-job-page1" onItemClick="ruizhi.oaas.job.itemClick"><!--onItemDblClick="itemDblClick"-->
					<tr>
						<td display="false" displayName="职位标识" width="" propertyName="jobId" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="名称" width="" propertyName="jobName" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="编码" width="" propertyName="jobCode" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="所属部门Id" width="" propertyName="orgId" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="所属部门" width="" propertyName="orgName" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="最后修改人" width="" propertyName="modifyUserName" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="最后修改时间" width="" propertyName="modifyTime" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="显示顺序" width="" propertyName="displayIndex" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="状态" width="" propertyName="state" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="创建用户的Id" width="" propertyName="createUserId" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="创建时间" width="" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="最后修改人Id" width="" propertyName="modifyUserId" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="职位类型" width="" propertyName="jobType" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="创建用户名" width="" propertyName="createUserName" sortType="text" align="center">&nbsp;</td>
					</tr>
				</table>
				<div id="oaas-job-page1"></div>
				<!-- 下面展示数据table end -->
				<ul id="oaas-job-tab" class="nav nav-tabs">
				   <li>
				      <a href="#info" data-toggle="tab" >
				        	职位信息
				      </a>
				   </li>
				</ul>
				<div id="oaas-jobTab-content" class="tab-content">
				   <div class="tab-pane fade" id="info">
				   		<form class="form-horizontal" id="oaas-jobInfo-form1" action="#">
						<!--<input type="hidden" name="jobId" class="form-control">
							<input type="hidden" name="state" class="form-control">
							<input type="hidden" name="createUserId" class="form-control">
							<input type="hidden" name="modifyUserId" class="form-control"> -->
							<div class="panel panel-flat">
								<div class="panel-body">
									<div class="row"> 
										<div class="col-md-6">
											<fieldset>
												<div class="form-group">
													<label class="col-lg-offset-2 col-lg-2 control-label">名称<span class="text-danger">*</span></label>
													<div class="col-lg-6">
														<input type="text" class="form-control"   name="jobName"  readonly >
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-offset-2 col-lg-2 control-label">创建人<span class="text-danger">*</span></label>
													<div class="col-lg-6">
														<input type="text" class="form-control"  name="createUserName" readonly >
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-offset-2 col-lg-2 control-label">最后修改人<span class="text-danger"></span></label>
													<div class="col-lg-6">
														<input type="text" class="form-control" name="modifyUserName" readonly>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-offset-2 col-lg-2 control-label">所属部门<span class="text-danger"></span></label>
													<div class="col-lg-6">
															<input type="text" class="form-control"  name="orgName" readonly  >
													</div>
												</div>
												
											</fieldset>
										</div>
										
										<div class="col-md-6">
											<fieldset>
												<div class="form-group">
													<label class="col-lg-offset-2 col-lg-2 control-label">编码<span class="text-danger">*</span></label>
													<div class="col-lg-6">
														<input type="text" class="form-control"   name="jobCode" readonly >
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-offset-2 col-lg-2 control-label">创建时间<span class="text-danger">*</span></label>
													<div class="col-lg-6">
														<input type="text" class="form-control"   name="createTime"  readonly >
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-offset-2 col-lg-2 control-label">最后修改时间<span class="text-danger"></span></label>
													<div class="col-lg-6">
														<input type="text" class="form-control"  name="modifyTime" readonly>
													</div>
												</div>
												<div class="form-group">
													<label class="col-lg-offset-2 col-lg-2 control-label">备注<span class="text-danger"></span></label>
													<div class="col-lg-6">
															<input type="text" class="form-control"name="remarks" readonly >
													</div>
												</div>
												
											</fieldset>
										</div>
									</div>
						
								</div>
							</div>
						</form>
				   </div>
				</div>
			</div>
		</div>
	</div>
</div

	
<#include "../../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/oaas/job/js/job.js"></script>


</html>
