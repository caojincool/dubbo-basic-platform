<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<ul id="order-orderMonitor-tab1" class="nav nav-tabs">
   <li>
      <a href="#order-orderMonitor-qry" data-toggle="tab">
         查询条件
      </a>
   </li>
</ul>
<div id="order-orderMonitor-tab1Content" class="tab-content">
   <div class="tab-pane fade" id="order-orderMonitor-qry">
   
<!-- 上面查询form表单 start -->
<form class="form-horizontal" id="order-orderMonitor-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-4">
						<div class="form-group">
							<label class="col-lg-3 control-label">区域：</label>
							<div class="col-lg-9">
								<div class="input-group">
									<input type="hidden" name="areaId" class="form-control">
									<input type="text" name="areaName"  class="form-control" placeholder="区域" readonly="readonly">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.orderMonitor.selArea()">选择</button>
										</span>
								</div>
							</div>
						</div>
				</div>	
			
				<div class="col-md-4">
						<div class="form-group">
							<label class="col-lg-3 control-label">组织：</label>
							<div class="col-lg-9">
								<div class="input-group">
									<input type="hidden" name="createOrgId" class="form-control">
									<input type="text" name="createOrgName"  class="form-control" placeholder="组织" readonly="readonly">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.orderMonitor.selOrg()">选择</button>
										</span>
								</div>
							</div>
						</div>
				</div>	
			
				<div class="col-md-4">
						<div class="form-group">
							<label class="col-lg-3 control-label">单据编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="单据编码" name="orderCode"/>
							</div>
						</div>
				</div>	
			
				<div class="col-md-4">
						<div class="form-group">
							<label class="col-lg-3 control-label">单据状态：</label>
							<div class="col-lg-9">
								<select name="orderState" class="select" >
									<option value="">请选择</option>
									<option value="10I">流程未启动</option>
									<option value="1I">启动处理中</option>
									<option value="10N">正常执行中</option>
									<option value="10F">竣工</option>
									<option value="10E">异常</option>
									<option value="10R">已回退</option>
								</select>
							</div>
						</div>
				</div>	
			
				<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-3 control-label">创建开始时间：</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="createTimeStart" class="form-control datetimepicker" format="YYYY-MM-DD HH:mm:ss" value="">
								</div>
							</div>
						</div>
				</div>	
			
				<div class="col-md-4">
						<div class="form-group">
							<label class="col-md-3 control-label">创建结束时间：</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="createTimeEnd" class="form-control datetimepicker" format="YYYY-MM-DD HH:mm:ss" value="">
								</div>
							</div>
						</div>
				</div>	
		
			</div>
		</div>
	</div>
</form>	
<!-- 上面查询form表单 end -->

   </div>
</div>


<!-- 中间按钮 start -->
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.order.orderMonitor.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
							</ul>
						</div>
					</div>
<!-- 中间按钮 end -->


<ul id="order-orderMonitor-tab2" class="nav nav-tabs">
   <li>
      <a href="#order-orderMonitor-orderList" data-toggle="tab">
        单据列表
      </a>
   </li>
</ul>
<div id="order-orderMonitor-tab2Content" class="tab-content">
   <div class="tab-pane fade" id="order-orderMonitor-orderList">
<!-- 按钮 start -->
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<!-- 页面固定按钮-->
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.order.orderMonitor.orderCreate()"><i class="icon-plus2 position-left"></i>创建单据</a></li>
								<li><a href="javascript:ruizhi.order.orderMonitor.orderDetail()"><i class="icon-bianji position-left"></i>单据详情</a></li>
								<li><a href="javascript:ruizhi.order.orderMonitor.processStart()"><i class="icon-bianji position-left"></i>启动流程</a></li>
								<li><a href="javascript:ruizhi.order.orderMonitor.processReset()"><i class="icon-bianji position-left"></i>流程重置</a></li>
								<li><a href="javascript:ruizhi.order.orderMonitor.processJump()"><i class="icon-bianji position-left"></i>流程跳转</a></li>
								<li><a href="javascript:ruizhi.order.orderMonitor.orderDel()"><i class="icon-x position-left"></i>单据作废</a></li>							
							</ul>
							<!-- 页面可配置按钮-->
							<ul class="nav nav-tabs nav-tabs-solid" id="order-orderMonitor-orderButton">
							</ul>
						</div>
					</div>
<!-- 按钮 end -->
   
<!-- 下面展示数据table start -->
<table id="order-orderMonitor-grid1" showCheck="true" height="200" idPropertyName="orderId"
 rowNum="10" pagerid="order-orderMonitor-page1"
 onItemClick="ruizhi.order.orderMonitor.orderItemClick"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="单据编码" width="" propertyName="orderCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="单据标题" width="" propertyName="orderTitle" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="单据类型" width="" propertyName="orderType" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="单据状态" width="" propertyName="orderState" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="单据状态" width="" propertyName="orderStateName" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="服务编码" width="" propertyName="serviceCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="服务名称" width="" propertyName="serviceName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="创建时间" width="" propertyName="state" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="创建人" width="" propertyName="state" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="单据id" width="" propertyName="orderId" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="order-orderMonitor-page1"></div>
<!-- 下面展示数据table end -->

   </div>
</div>


<ul id="order-orderMonitor-tab3" class="nav nav-tabs">
   <li>
      <a href="#order-orderMonitor-workOrderList" data-toggle="tab">
        任务列表
      </a>
   </li>
</ul>
<div id="order-orderMonitor-tab3Content" class="tab-content">
   <div class="tab-pane fade" id="order-orderMonitor-workOrderList">

<!-- 按钮 start -->
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.order.orderMonitor.workOrderFinish()"><i class="icon-bianji position-left"></i>强制回单</a></li>
								<li><a href="javascript:ruizhi.order.orderMonitor.workOrderDisp()"><i class="icon-bianji position-left"></i>强制转派</a></li>
							</ul>
						</div>
					</div>
<!-- 按钮 end -->

<!-- 下面展示数据table start -->
<table id="order-orderMonitor-grid2" showCheck="true" height="200" idPropertyName="workOrderId"
><!-- rowNum="10" pagerid="order-orderMonitor-page2" onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="任务编码" width="" propertyName="workOrderCode" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="任务状态" width="" propertyName="workOrderState" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="任务状态" width="" propertyName="workOrderStateName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="环节编码" width="" propertyName="tacheCode" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="接收人类型" width="" propertyName="partyType" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="接收人类型" width="" propertyName="partyTypeName" sortType="text" align="center" formatter="ruizhi.order.orderMonitor.partyTypeTrans">&nbsp;</td>
		<td display="false" displayName="接收人id" width="" propertyName="partyId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="接收人" width="" propertyName="partyName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="创建时间" width="" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="状态时间" width="" propertyName="stateTime" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="回单人id" width="" propertyName="finishUserId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="回单人" width="" propertyName="finishUserText" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="单据id" width="" propertyName="orderId" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="流程任务id" width="" propertyName="workOrderId" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<!--<div id="order-orderMonitor-page2"></div>-->
<!-- 下面展示数据table end -->

   </div>
</div>


<#include "../../includes/page-foot.ftl">
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/order/orderMonitor/js/orderMonitor.js"></script>
<!-- 引入该页面js end -->

</html>