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
	<h6 class="modal-title" id="oaas-selStaff-title" >单选账号窗口</h6>
</div>

<div class="modal-body" id="modalBody">
		<div class="row">
			<div class="col-md-3" >
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
					<div class="ui-jqgrid-bdiv" style="height: 400px; width: 100%;">
						<div>
							<div>
								<div>
									<table  style="width: 100%;">
										<tbody>
											<tr class="jqgfirstrow">
												<!-- ztree 容器开始 -->
												<td id="selStaff-orgTree-td" class="ztree" zTreeOnClick="ruizhi.oaas.selStaff.treeClick"  style="height:0px;width:100%;"></td>
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
    		<div class="col-md-9" >
    			<form class="form-horizontal" id="selStaff-staffInfo-form1" action="#">
					<div class="panel panel-flat">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-4">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-3 control-label">姓名</label>
											<div class="col-lg-9">
												<input type="text" class="form-control" placeholder="姓名" name="staffName"/>
											</div>
										</div>
									</fieldset>
								</div>	
								
								<div class="col-md-4">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-3 control-label">工号</label>
											<div class="col-lg-9">
												<input type="text" class="form-control" placeholder="工号" name="staffNumber"/>
											</div>
										</div>
									</fieldset>
								</div>	
								<div class="col-md-4">
									<fieldset>
										<div class="form-group">
											<label class="checkbox-inline">
								  				<input type="checkbox" name="qrySubOrg" value="true">查询下属部门
											</label>
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
							<li><a href="javascript:ruizhi.oaas.selStaff.qry()"><i class="icon-search4 position-left"></i>查询</a></li>
						</ul>
					</div>
				</div>
				<!-- 下面展示数据table start -->
				<table id="selStaff-staff-grid1" showCheck="true" height="250" idPropertyName="staffId"
				 	rowNum="10" pagerid="selStaff-staff-page1" onItemDblClick="ruizhi.oaas.staffDbClick.itemClick"><!--onItemDblClick="itemDblClick"-->
					<tr>
						<td display="true" displayName="姓名" width="" propertyName="staffName" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="工号" width="" propertyName="staffNumber" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="性别" width="" propertyName="sex" sortType="text" align="center" formatter="ruizhi.oaas.selStaff.staffTrans">&nbsp;</td>
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
				<div id="selStaff-staff-page1"></div>
			</div>
		</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selStaff.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.selStaff.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/staff/js/selStaff.js"></script>
</html>
