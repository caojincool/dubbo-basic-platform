<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<!--页面head-->
<#include "../../includes/modal-head.ftl">
	
</head>

<body>
<!--页面title start-->
<div class="modal-header bg-primary">
	<!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
	<h6 class="modal-title">单据详情</h6>
</div>
<!--页面title end-->

<div class="modal-body" id="">

<ul id="order-orderDetail-tab1" class="nav nav-tabs">
   <li>
      <a href="#order-orderDetail-orderInfo" data-toggle="tab">
        基本信息
      </a>
   </li>
   
   <li>
      <a href="#order-orderDetail-workOrderList" data-toggle="tab">
        任务列表
      </a>
   </li>
   
   <li>
      <a href="#order-orderDetail-orderOperList" data-toggle="tab">
      单据处理记录
      </a>
   </li>
   
</ul>
<div id="order-orderDetail-tab1Content" class="tab-content">
   <div class="tab-pane fade" id="order-orderDetail-orderInfo">

<!-- 提交form表单 start -->
<form class="form-horizontal" id="order-orderDetail-form1" action="#">
<input type="hidden" name="orderId" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-6">
					<fieldset>

						<div class="form-group">
							<label class="col-lg-3 control-label">单据编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="单据编码" name="orderCode" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">流程实例：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="流程实例标识" name="processInstanceId" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">服务编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="服务编码" name="serviceCode" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">单据状态：</label>
							<div class="col-lg-9">
								<input type="hidden" class="form-control" placeholder="单据状态" name="orderState" readonly="readonly">
								<input type="text" class="form-control" placeholder="单据状态" name="orderStateName" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">单据类型：</label>
							<div class="col-lg-9">
								<input type="hidden" class="form-control" placeholder="单据类型" name="orderType" readonly="readonly">
								<input type="text" class="form-control" placeholder="单据类型" name="orderTypeName" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">区域：</label>
							<div class="col-lg-9">
								<input type="hidden" class="form-control" placeholder="区域" name="areaId" readonly="readonly">
								<input type="text" class="form-control" placeholder="区域" name="areaName" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">创建人：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="创建人" name="createUserText" readonly="readonly">
							</div>
						</div>

					</fieldset>
				</div>
				
				<div class="col-md-6">
					<fieldset>

						<div class="form-group">
							<label class="col-lg-3 control-label">单据标题：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="单据标题" name="orderTitle" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">范围标识：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="范围标识" name="scopeId" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">服务名称：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="服务名称" name="serviceName" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">优先级：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="优先级" name="orderPriorityName" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">单据历时：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="单据历时" name="costTime" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">创建时间：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="创建时间" name="createTime" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">修改时间：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="修改时间" name="stateTime" readonly="readonly">
							</div>
						</div>

					</fieldset>
				</div>
			</div>
			<div class="row">
				
				<div class="col-md-12">
					<fieldset>

						<div class="form-group">
							<label class="col-lg-3 control-label">备注：</label>
							<div class="col-lg-9">
								<textarea rows="5" cols="5" class="form-control" placeholder="备注" name="orderComments" readonly="readonly"></textarea>
							</div>
						</div>

					</fieldset>
				</div>
			</div>
			
			<div class="row">
				
				<div class="col-md-12">
					<fieldset>

						<div class="form-group">
							<label class="col-lg-3 control-label">异常编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="异常编码" name="errorCode" readonly="readonly">
							</div>
						</div>

					</fieldset>
				</div>
			</div>
			
			<div class="row">
				
				<div class="col-md-12">
					<fieldset>

						<div class="form-group">
							<label class="col-lg-3 control-label">异常信息：</label>
							<div class="col-lg-9">
								<textarea rows="5" cols="5" class="form-control" placeholder="异常信息" name="errInfo" readonly="readonly"></textarea>
							</div>
						</div>

					</fieldset>
				</div>
			</div>

		</div>
	</div>
</form>
<!-- 提交form表单 end -->

   </div>
   
   <div class="tab-pane fade" id="order-orderDetail-workOrderList">

<!-- 按钮 start -->
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.order.orderDetail.workOrderDetail()"><i class="icon-bianji position-left"></i>任务详情</a></li>
							</ul>
						</div>
					</div>
<!-- 按钮 end -->

<!-- 下面展示数据table start -->
<table id="order-orderDetail-grid1" showCheck="true" height="300" idPropertyName="workOrderId"
><!-- rowNum="10" pagerid="order-orderMonitor-page2" onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="任务编码" width="" propertyName="workOrderCode" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="任务状态" width="" propertyName="workOrderState" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="任务状态" width="" propertyName="workOrderStateName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="环节编码" width="" propertyName="tacheCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="接收人类型" width="" propertyName="partyType" sortType="text" align="center">&nbsp;</td>
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
   
   <div class="tab-pane fade" id="order-orderDetail-orderOperList">

<!-- 下面展示数据table start -->
<table id="order-orderDetail-grid2" showCheck="true" height="300" idPropertyName="operId"
><!-- rowNum="10" pagerid="order-orderMonitor-page2" onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="操作类型" width="" propertyName="operType" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="操作类型" width="" propertyName="operTypeName" sortType="text" align="center" formatter="ruizhi.order.orderDetail.operTypeTrans">&nbsp;</td>
		<td display="false" displayName="操作人id" width="" propertyName="operUserId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="操作人" width="" propertyName="operUserText" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="操作时间" width="" propertyName="operTime" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="操作信息" width="" propertyName="operInfo" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="操作说明" width="" propertyName="operComments" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="单据id" width="" propertyName="orderId" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="流程单据操作记录id" width="" propertyName="operId" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<!--<div id="order-orderMonitor-page2"></div>-->
<!-- 下面展示数据table end -->

   </div>
   
</div>


</div>

<!-- 下面页面按钮 start -->
<div class="modal-footer">
	<!--<button type="button" class="btn btn-primary" onclick="ruizhi.order.orderDetail.doSubmit()">确定</button>-->
	<button type="button" class="btn btn-link" onclick="ruizhi.order.orderDetail.doClose()">关闭</button>
</div>
<!-- 下面页面按钮 end -->

<#include "../../includes/modal-foot.ftl">
	
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/order/orderMonitor/js/orderDetail.js"></script>
<!-- 引入该页面js end -->

</html>
