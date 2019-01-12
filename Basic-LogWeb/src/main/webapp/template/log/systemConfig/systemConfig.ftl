<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<!-- 上面查询form表单 start -->
<form class="form-horizontal" id="log-systemConfig-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">拦截URL：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="拦截URL" name="requestUrl"/>
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
								<li><a href="javascript:ruizhi.log.systemConfig.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.log.systemConfig.create()"><i class="icon-plus2 position-left"></i>新增</a></li>
								<li><a href="javascript:ruizhi.log.systemConfig.modify()"><i class="icon-bianji position-left"></i>编辑</a></li>
								<li><a href="javascript:ruizhi.log.systemConfig.del()"><i class="icon-x position-left"></i>作废</a></li>							
							</ul>
						</div>
					</div>
<!-- 中间按钮 end -->


<!-- 下面展示数据table start -->
<table id="log-systemConfig-grid1" showCheck="true" height="300" idPropertyName="configId"
 rowNum="10" pagerid="log-systemConfig-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="系统日志配置表标识" width="" propertyName="configId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="拦截URL" width="" propertyName="requestUrl" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="是否记录日志" width="" propertyName="requestFlag" sortType="text" align="center" formatter="ruizhi.log.systemConfig.flagTrans">&nbsp;</td>
		<td display="true" displayName="是否记录内容" width="" propertyName="requestContentFlag" sortType="text" align="center" formatter="ruizhi.log.systemConfig.flagTrans">&nbsp;</td>
		<td display="false" displayName="是否记录日志" width="" propertyName="requestFlag" sortType="text" align="right">&nbsp;</td>
		<td display="false" displayName="是否记录内容" width="" propertyName="requestContentFlag" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="log-systemConfig-page1"></div>
<!-- 下面展示数据table end -->


<#include "../../includes/page-foot.ftl">
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/log/systemConfig/js/systemConfig.js"></script>
<!-- 引入该页面js end -->

</html>