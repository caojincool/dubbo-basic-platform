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

					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.security.onlineUser.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.security.onlineUser.logoutUser()"><i class="icon-x position-left"></i>删除</a></li>							
							</ul>
						</div>
					</div>

<table id="security-onlineUser-grid1" showCheck="true" height="50%" idPropertyName="sessionId"
 rowNum="10" pagerid="security-onlineUser-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="账号" width="" propertyName="userAccount" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="sessionId" width="" propertyName="sessionId" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="最后访问时间" width="" propertyName="lastAccessedTime" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="限制访问" width="" propertyName="isExpired" sortType="text" align="left">&nbsp;</td>
	</tr>
</table>
<div id="security-onlineUser-page1"></div>

	
<#include "../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/security/js/onlineUser.js"></script>


</html>
