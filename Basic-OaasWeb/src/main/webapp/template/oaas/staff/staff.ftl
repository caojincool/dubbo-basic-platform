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
												<td id="org-orgTree-staff" class="ztree" zTreeOnClick="ruizhi.oaas.staff.treeClick"  style="height:0px;width:100%;"></td>
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
    			<form class="form-horizontal" id="oaas-staffInfo-form1" action="#">
					<div class="panel panel-flat">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-3">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-3 control-label">姓名</label>
											<div class="col-lg-9">
												<input type="text" class="form-control" placeholder="姓名" name="staffName"/>
											</div>
										</div>
									</fieldset>
								</div>	
								
								<div class="col-md-3">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-3 control-label">工号</label>
											<div class="col-lg-9">
												<input type="text" class="form-control" placeholder="工号" name="staffNumber"/>
											</div>
										</div>
									</fieldset>
								</div>	
								<div class="col-md-3">
									<fieldset>
										<div class="form-group">
											<label class="checkbox-inline">
								  				<input type="checkbox" name="qrySubOrg" value="true">查询下属部门
											</label>
										</div>
									</fieldset>
								</div>
								<div class="col-md-3">
									<div class="pull-right">
										<button type="button" class="btn btn-info" onclick="ruizhi.oaas.staff.qry()"><i class="icon-search4 position-left"></i>查询</button>
										&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn btn-success" onclick="ruizhi.oaas.staff.resetForm()"><i class="icon-x position-left"></i>清除</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>	
				<div class="navbar navbar-default navbar-xs">
					<div class="navbar-collapse collapse" id="navbar-filter">
						<ul class="nav nav-tabs nav-tabs-solid">
						 <!--<li><a href="javascript:ruizhi.oaas.staff.qry()"><i class="icon-search4 position-left"></i>查询</a></li> -->
							<li><a href="javascript:ruizhi.oaas.staff.create()"><i class="icon-plus2 position-left"></i>新增</a></li>
							<li><a href="javascript:ruizhi.oaas.staff.modify()"><i class="icon-pencil4 position-left"></i>修改</a></li>
							<li><a href="javascript:ruizhi.oaas.staff.del()"><i class="icon-x position-left"></i>删除</a></li>
						<!--li><a href="javascript:ruizhi.oaas.staff.resetForm()"><i class="icon-rotate-ccw3 position-left"></i>重置查询</a></li>  -->
						</ul>
					</div>
				</div>
				<!-- 下面展示数据table start -->
				<table id="oaas-staff-grid1" showCheck="true" height="300" idPropertyName="staffId"
				 	rowNum="10" pagerid="oaas-staff-page1" onItemClick="ruizhi.oaas.staff.itemClick"><!--onItemDblClick="itemDblClick"-->
					<tr>
						<td display="true" displayName="姓名" width="" propertyName="staffName" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="工号" width="" propertyName="staffNumber" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="性别标识" width="" propertyName="sex" sortType="text" align="center" >&nbsp;</td>
						<td display="true" displayName="性别" width="" propertyName="sexType" sortType="text" align="center" formatter="ruizhi.oaas.staff.staffTrans">&nbsp;</td>
						<td display="true" displayName="手机号码" width="" propertyName="telephone" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="邮箱" width="" propertyName="email" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="默认职位" width="" propertyName="jobName" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="默认职位Id" width="" propertyName="jobId" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="员工Id" width="" propertyName="staffId" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="头像" width="" propertyName="staffIcon" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="微信" width="" propertyName="wechat" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="QQ" width="" propertyName="qq" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="状态" width="" propertyName="state" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="创建用户Id" width="" propertyName="createUserId" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="创建用户名" width="" propertyName="createUserName" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="创建时间" width="" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="修改用户Id" width="" propertyName="modifyUserId" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="修改用户名" width="" propertyName="modifyUserName" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="修改时间" width="" propertyName="modifyTime" sortType="text" align="center">&nbsp;</td>
					</tr>
				</table>
				<div id="oaas-staff-page1"></div>
				<!-- 下面展示数据table end -->
				<div class="col-md-6">
					<ul id="oaas-job-tab" class="nav nav-tabs">
					   <li>
					      <a href="#oaas-staffTab-grid" data-toggle="tab" >
					        	职位列表
					      </a>
					   </li>
					</ul>
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" >
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.oaas.staffJob.create()"><i class="icon-plus2 position-left"></i>添加职位</a></li>
								<li><a href="javascript:ruizhi.oaas.staffJob.del()"><i class="icon-x position-left"></i>删除职位</a></li>							
							</ul>
						</div>
					</div>
					<table id="oaas-jobTab-grid" showCheck="true" height="150" idPropertyName="staffJobId" class="tab-content"
					 	rowNum="10" pagerid="oaas-jobTab-page1" onItemClick="ruizhi.oaas.staffJob.itemClick" >
						<tr>
							<td display="false" displayName="员工职位Id" width="" propertyName="staffJobId" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="职位Id" width="" propertyName="jobId" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="员工Id" width="" propertyName="staffId" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="职位" width="" propertyName="jobName" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="部门" width="" propertyName="orgName" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="默认标识" width="" propertyName="Yes_no" sortType="text" align="center" formatter="ruizhi.oaas.staffJob.staffJobTrans">&nbsp;</td>
							<td display="false" displayName="默认标识" width="" propertyName="defaultJob" sortType="text" align="center" >&nbsp;</td>
						</tr>
					</table>
					<div id="oaas-jobTab-page1"></div>
				</div>
				<div class="col-md-6">
					<ul id="oaas-user-tab" class="nav nav-tabs">
					   <li>
					      <a href="#oaas-userTab-grid" data-toggle="tab" >
					        	账号列表
					      </a>
					   </li>
					</ul>
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" >
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.oaas.userStaff.create()"><i class="icon-plus2 position-left"></i>添加账号</a></li>
								<li><a href="javascript:ruizhi.oaas.userStaff.del()"><i class="icon-x position-left"></i>删除账号</a></li>							
							</ul>
						</div>
					</div>
					<table id="oaas-userTab-grid" showCheck="true" height="150" idPropertyName="userId" class="tab-content"
					 	rowNum="10" pagerid="oaas-userTab-page1" onItemClick="ruizhi.oaas.userStaff.itemClick" >
						<tr>
							<td display="true" displayName="账号" width="" propertyName="username" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="账号名" width="" propertyName="userText" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="账号Id" width="" propertyName="userId" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="员工Id" width="" propertyName="staffId" sortType="text" align="center">&nbsp;</td>
						</tr>
					</table>
					<div id="oaas-userTab-page1"></div>
				</div>
			</div>
		</div>
	</div>
</div

	
<#include "../../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/oaas/staff/js/staff.js"></script>
<script type="text/javascript" src="${webRoot}/template/oaas/staff/js/staffJob.js"></script>
<script type="text/javascript" src="${webRoot}/template/oaas/staff/js/userStaff.js"></script>

</html>
