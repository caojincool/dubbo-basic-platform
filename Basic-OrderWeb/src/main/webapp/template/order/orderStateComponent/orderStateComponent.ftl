<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<!-- 上面查询form表单 start -->
<form class="form-horizontal" id="order-orderStateComponent-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
				<div class="col-md-6">
					<fieldset>
					<div class="form-group">
							<label class="col-lg-3 control-label">组件名称：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="组件名称" name="componentName"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">组件编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="组件编码" name="componentCode"/>
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
								<li><a href="javascript:ruizhi.order.orderStateComponent.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.order.orderStateComponent.create()"><i class="icon-plus2 position-left"></i>新增</a></li>
								<li><a href="javascript:ruizhi.order.orderStateComponent.modify()"><i class="icon-bianji position-left"></i>编辑</a></li>
								<li><a href="javascript:ruizhi.order.orderStateComponent.del()"><i class="icon-x position-left"></i>作废</a></li>							
							</ul>
						</div>
					</div>
<!-- 中间按钮 end -->


<!-- 下面展示数据table start -->
<table id="order-orderStateComponent-grid1" showCheck="true" height="300" idPropertyName="componentId"
 rowNum="10" pagerid="order-orderStateComponent-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="组织标识" width="" propertyName="componentId" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="单据状态" width="" propertyName="orderState" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="单据状态" width="" propertyName="orderStateName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="组件编码" width="" propertyName="componentCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="组件名称" width="" propertyName="componentName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="执行顺序" width="" propertyName="execIndex" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="状态" width="" propertyName="componentState" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="order-orderStateComponent-page1"></div>
<!-- 下面展示数据table end -->


<#include "../../includes/page-foot.ftl">
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/order/orderStateComponent/js/orderStateComponent.js"></script>
<!-- 引入该页面js end -->

</html>