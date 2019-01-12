<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<!-- 上面查询form表单 start -->
<form class="form-horizontal" id="order-partyWorkOrder-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">单据编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="单据编码" name="orderCode"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">任务编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="任务编码" name="workOrderCode"/>
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
							<!-- 页面固定按钮-->
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.order.partyWorkOrder.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.order.partyWorkOrder.orderDetail()"><i class="icon-bianji position-left"></i>单据详情</a></li>
								<li><a href="javascript:ruizhi.order.partyWorkOrder.workOrderDetail()"><i class="icon-bianji position-left"></i>任务详情</a></li>
								<li><a href="javascript:ruizhi.order.partyWorkOrder.workOrderFinish()"><i class="icon-bianji position-left"></i>回单</a></li>
								<li><a href="javascript:ruizhi.order.partyWorkOrder.workOrderDisp()"><i class="icon-bianji position-left"></i>转派</a></li>
								<li><a href="javascript:ruizhi.order.partyWorkOrder.workOrderGet()"><i class="icon-bianji position-left"></i>提单</a></li>
							</ul>
							<!-- 页面可配置按钮-->
							<ul class="nav nav-tabs nav-tabs-solid" id="order-partyWorkOrder-woButton">
							</ul>
						</div>
					</div>
<!-- 中间按钮 end -->


<!-- 下面展示数据table start -->
<table id="order-partyWorkOrder-grid1" showCheck="false" height="300" idPropertyName="workOrderId"
 rowNum="10" pagerid="order-partyWorkOrder-page1"
 onItemClick="ruizhi.order.partyWorkOrder.itemClick"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="单据编码" width="" propertyName="orderCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="单据标题" width="" propertyName="orderTitle" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="服务" width="" propertyName="serviceId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="服务" width="" propertyName="serviceCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="服务" width="" propertyName="serviceName" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="单据类型" width="" propertyName="orderType" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="单据类型" width="" propertyName="orderTypeName" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="单据状态" width="" propertyName="orderState" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="单据状态" width="" propertyName="orderStateName" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="单据创建人" width="" propertyName="createUserId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="单据创建人" width="" propertyName="createUserText" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="任务编码" width="" propertyName="workOrderCode" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="环节id" width="" propertyName="tacheId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="环节编码" width="" propertyName="tacheCode" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="任务状态" width="" propertyName="workOrderState" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="任务状态" width="" propertyName="workOrderStateName" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="接收人类型" width="" propertyName="partyType" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="接收人类型" width="" propertyName="partyTypeName" sortType="text" align="center" formatter="ruizhi.order.partyWorkOrder.partyTypeTrans">&nbsp;</td>
		<td display="false" displayName="接收人" width="" propertyName="partyId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="接收人" width="" propertyName="partyName" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="单据id" width="" propertyName="orderId" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="流程任务id" width="" propertyName="workOrderId" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="order-partyWorkOrder-page1"></div>
<!-- 下面展示数据table end -->


<#include "../../includes/page-foot.ftl">
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/order/workOrder/js/partyWorkOrder.js"></script>
<!-- 引入该页面js end -->

</html>