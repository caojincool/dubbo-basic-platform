<!DOCTYPE html>
<html lang="en">

<head>

<#include "../includes/page-head.ftl">	


</head>

<body>
<#include "../includes/page-title.ftl">
<!--不分页查询-->

<form class="form-horizontal" id="security-onlineUser-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">账号：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="账号" name="userAccount"/>
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
								<li><a href="javascript:ruizhi.security.onlineUser.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.security.onlineUser.logoutUser()"><i class="icon-x position-left"></i>删除</a></li>							
							</ul>
						</div>
					</div>

<table id="security-onlineUser-grid1" showCheck="false" height="50%" idPropertyName="sessionId"
 rowNum="10" pagerid="security-onlineUser-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="sessionId" width="" propertyName="sessionId" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="账号名称" width="" propertyName="userName" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="账号" width="" propertyName="userAccount" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="登录时间" width="" propertyName="creationTime" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="最后访问时间" width="" propertyName="lastAccessedTime" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="客户端主机ip" width="" propertyName="cIp" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="客户端主机名称" width="" propertyName="cHostName" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="客户端浏览器" width="" propertyName="cBrowserInfo" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="客户端MAC地址" width="" propertyName="cMacAddress" sortType="text" align="left">&nbsp;</td>
		<td display="false" displayName="超时时间(S)" width="" propertyName="maxInactiveInterval" sortType="text" align="left">&nbsp;</td>
		<td display="false" displayName="错误信息" width="" propertyName="springSecurityLastException" sortType="text" align="left">&nbsp;</td>
	</tr>
</table>
<div id="security-onlineUser-page1"></div>

	
<#include "../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/security/js/onlineUser.js"></script>


</html>
