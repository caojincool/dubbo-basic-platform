<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<!-- 上面查询form表单 start -->
<form class="form-horizontal" id="log-systemLog-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-4">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">请求URL：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="请求URL" name="requestUrl"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">访问用户名称：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="访问用户名称" name="userText"/>
							</div>
						</div>
					</fieldset>
				</div>	
				<div class="col-md-4">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">日志类型：</label>
							<div class="col-lg-9">
								<select class="select" name ="logType">
									<option value="" selected>全部</option>
									<option value="LOGIN">登录</option>
									<option value="MENU">菜单</option>
									<option value="FUNC">操作</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">创建时间：</label>
							<div class="col-lg-9">
								<div class="input-group pull-left col-md-5">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="beginTime" class="form-control datetimepicker" format="YYYY-MM-DD" value=""/>
								</div>
								<div class="col-md-1 pull-left text-center">
								 	<span style="line-height:36px;"> - </span>
								</div>
								<div class="input-group col-md-5">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="endTime" class="form-control datetimepicker" format="YYYY-MM-DD" value=""/>
								</div>
							</div>
						</div>
					</fieldset>
				</div>
				
		
			</div>
		</div>
	</div>
</form>	
<!-- 上面查询form表单 end -->


<!-- 中间按钮 start -->
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.log.systemLog.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.log.systemLog.resetForm()"><i class="icon-x position-left"></i>清除</a></li>
							</ul>
						</div>
					</div>
<!-- 中间按钮 end -->


<!-- 下面展示数据table start -->
<table id="log-systemLog-grid1" showCheck="false" height="300" idPropertyName="logId"
 rowNum="10" pagerid="log-systemLog-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="系统日志表标识" width="" propertyName="logId" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="请求URL" width="" propertyName="requestUrl" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="请求内容" width="" propertyName="requestContent" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="日志类型" width="" propertyName="logType" sortType="text" align="center" formatter="ruizhi.log.systemLog.logTypeTrans">&nbsp;</td>
		<td display="true" displayName="访问用户名称" width="" propertyName="userText" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="创建时间" width="" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="访问用户ip地址" width="" propertyName="custId" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="log-systemLog-page1"></div>
<!-- 下面展示数据table end -->


<#include "../../includes/page-foot.ftl">
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/log/systemLog/js/systemLog.js"></script>
<!-- 引入该页面js end -->

</html>