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
	<h6 class="modal-title">任务详情</h6>
</div>
<!--页面title end-->

<div class="modal-body" id="">

<ul id="order-workOrderDetail-tab1" class="nav nav-tabs">
   <li>
      <a href="#order-workOrderDetail-orderInfo" data-toggle="tab">
        单据基本信息
      </a>
   </li>
   
   <li>
      <a href="#order-workOrderDetail-workOrderInfo" data-toggle="tab">
        任务基本信息
      </a>
   </li>
   
   <li>
      <a href="#order-workOrderDetail-workOrderOperList" data-toggle="tab">
      任务处理记录
      </a>
   </li>
   
</ul>
<div id="order-workOrderDetail-tab1Content" class="tab-content">
   <div class="tab-pane fade" id="order-workOrderDetail-orderInfo">

<!-- 提交form表单 start -->
<form class="form-horizontal" id="order-workOrderDetail-form1" action="#">
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
   
   <div class="tab-pane fade" id="order-workOrderDetail-workOrderInfo">

<!-- 提交form表单 start -->
<form class="form-horizontal" id="order-workOrderDetail-form2" action="#">
<input type="hidden" name="workOrderId" class="form-control">
<input type="hidden" name="orderId" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-6">
					<fieldset>

						<div class="form-group">
							<label class="col-lg-3 control-label">任务编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="任务编码" name="workOrderCode" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">任务状态：</label>
							<div class="col-lg-9">
								<input type="hidden" class="form-control" placeholder="任务状态" name="workOrderState" readonly="readonly">
								<input type="text" class="form-control" placeholder="任务状态" name="workOrderStateName" readonly="readonly">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">接收人类型：</label>
							<div class="col-lg-9">
								<input type="hidden" class="form-control" placeholder="接收人类型" name="partyType" readonly="readonly">
								<input type="text" class="form-control" placeholder="接收人类型" name="partyTypeName" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">回单人：</label>
							<div class="col-lg-9">
								<input type="hidden" class="form-control" placeholder="回单人" name="finishUserId" readonly="readonly">
								<input type="text" class="form-control" placeholder="回单人" name="finishUserText" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">超时时间：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="超时时间" name="overTime" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">创建时间：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="创建时间" name="createTime" readonly="readonly">
							</div>
						</div>
						
					</fieldset>
				</div>
				
				<div class="col-md-6">
					<fieldset>

						<div class="form-group">
							<label class="col-lg-3 control-label">环节编码：</label>
							<div class="col-lg-9">
								<input type="hidden" class="form-control" placeholder="环节id" name="tacheId" readonly="readonly">
								<input type="text" class="form-control" placeholder="环节编码" name="tacheCode" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">任务实例标识：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="任务实例标识" name="taskId" readonly="readonly">
							</div>
						</div>
												
						<div class="form-group">
							<label class="col-lg-3 control-label">接收人：</label>
							<div class="col-lg-9">
								<input type="hidden" class="form-control" placeholder="接收人" name="partyId" readonly="readonly">
								<input type="text" class="form-control" placeholder="接收人" name="partyName" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">回单时间：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="回单时间" name="finishTime" readonly="readonly">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">告警时间：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="告警时间" name="alterTime" readonly="readonly">
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
								<textarea rows="5" cols="5" class="form-control" placeholder="备注" name="workOrderComments" readonly="readonly"></textarea>
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
   
   <div class="tab-pane fade" id="order-workOrderDetail-workOrderOperList">

<!-- 下面展示数据table start -->
<table id="order-workOrderDetail-grid1" showCheck="true" height="300" idPropertyName="operId"
><!-- rowNum="10" pagerid="order-orderMonitor-page2" onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="操作类型" width="" propertyName="operType" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="操作类型" width="" propertyName="operTypeName" sortType="text" align="center" formatter="ruizhi.order.workOrderDetail.operTypeTrans">&nbsp;</td>
		<td display="false" displayName="操作人id" width="" propertyName="operUserId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="操作人" width="" propertyName="operUserText" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="操作时间" width="" propertyName="operTime" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="操作信息" width="" propertyName="operInfo" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="操作说明" width="" propertyName="operComments" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="任务id" width="" propertyName="workOrderId" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="任务操作记录id" width="" propertyName="operId" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<!--<div id="order-orderMonitor-page2"></div>-->
<!-- 下面展示数据table end -->

   </div>
   
</div>


</div>

<!-- 下面页面按钮 start -->
<div class="modal-footer">
	<!--<button type="button" class="btn btn-primary" onclick="ruizhi.order.workOrderDetail.doSubmit()">确定</button>-->
	<button type="button" class="btn btn-link" onclick="ruizhi.order.workOrderDetail.doClose()">关闭</button>
</div>
<!-- 下面页面按钮 end -->

<#include "../../includes/modal-foot.ftl">
	
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/order/orderMonitor/js/workOrderDetail.js"></script>
<!-- 引入该页面js end -->

</html>
