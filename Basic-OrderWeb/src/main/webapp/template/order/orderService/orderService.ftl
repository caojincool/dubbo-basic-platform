<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<!-- 查询form开始 -->
<form class="form-horizontal" id="order-orderService-form1" action="#">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-6">
					<fieldset>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">服务名称</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="服务名称" name="serviceName"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">服务编码</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="服务编码" name="serviceCode"/>
							</div>
						</div>
					</fieldset>
				</div>	
				
			</div>
		</div>
	</div>
</form>	
<!-- /查询form结束 --> 

<!-- 按钮开始 -->  
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.order.orderService.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.order.orderService.add()"><i class="icon-plus2 position-left"></i>新增</a></li>
								<li><a href="javascript:ruizhi.order.orderService.modify()"><i class="icon-pencil4 position-left"></i>编辑</a></li>
								<li><a href="javascript:ruizhi.order.orderService.del()"><i class="icon-x position-left"></i>删除</a></li>							
							</ul>
						</div>
					</div>
<!-- /按钮结束 --> 

<!-- table开始 --> 	
<table id="order-orderService-grid1" showCheck="true" height="300" idPropertyName="serviceId"
 rowNum="10" pagerid="order-orderService-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="服务ID" width="100px" propertyName="serviceId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="服务编码" width="150px" propertyName="serviceCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="服务名称" width="" propertyName="serviceName" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="创建人" width="120px" propertyName="createUserId" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="状态" width="50px" propertyName="state" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="创建时间" width="150px" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="修改时间" width="150px" propertyName="modifyTime" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="备注" width="150px" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="order-orderService-page1"></div>
<!-- /table结束 --> 	
	
<#include "../../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/order/orderService/js/orderService.js"></script>


</html>
