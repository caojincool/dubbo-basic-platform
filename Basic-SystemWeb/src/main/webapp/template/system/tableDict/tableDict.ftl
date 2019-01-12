<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<!-- 上面查询form表单 start -->
<form class="form-horizontal" id="system-tableDict-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">表编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="表编码" name="tableCode"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">表名称：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="表名称" name="tableName"/>
							</div>
						</div>
					</fieldset>
				</div>	
				
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">字段编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="字段编码" name="colCode"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">字段名称：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="字段名称" name="colName"/>
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
								<li><a href="javascript:ruizhi.system.tableDict.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.system.tableDict.create()"><i class="icon-plus2 position-left"></i>新增</a></li>
								<li><a href="javascript:ruizhi.system.tableDict.modify()"><i class="icon-bianji position-left"></i>编辑</a></li>
								<li><a href="javascript:ruizhi.system.tableDict.del()"><i class="icon-x position-left"></i>作废</a></li>							
							</ul>
						</div>
					</div>
<!-- 中间按钮 end -->


<!-- 下面展示数据table start -->
<table id="system-tableDict-grid1" showCheck="true" height="300" idPropertyName="dictId"
 rowNum="10" pagerid="system-tableDict-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="字典表标识" width="" propertyName="dictId" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="表编码" width="" propertyName="tableCode" sortType="text" align="left">&nbsp;</td>
		<td display="true" displayName="表名称" width="" propertyName="tableName" sortType="text" align="right">&nbsp;</td>
		<td display="true" displayName="字段编码" width="" propertyName="colCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="字段名称" width="" propertyName="colName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="字段值" width="" propertyName="colValue" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="字段值中文" width="" propertyName="colText" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="system-tableDict-page1"></div>
<!-- 下面展示数据table end -->


<#include "../../includes/page-foot.ftl">
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/system/tableDict/js/tableDict.js"></script>
<!-- 引入该页面js end -->

</html>