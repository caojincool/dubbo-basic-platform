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
	<h6 class="modal-title">创建单据</h6>
</div>
<!--页面title end-->

<div class="modal-body" id="">

<!-- 提交form表单 start -->
<form class="form-horizontal" id="order-createOrder-form1" action="#">
<!--<input type="hidden" name="dictId" class="form-control">-->
<input type="hidden" name="pageDateType" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">单据编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="单据编码" name="orderCode" required="required" message="required:请填写">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">单据标题：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="单据标题" name="orderTitle" required="required" message="required:请填写">
							</div>
						</div>
											
						<div class="form-group">
							<label class="col-lg-3 control-label">服务编码：</label>
							<div class="col-lg-9">
								<div class="input-group">
									<input type="hidden" name="serviceId" class="form-control">
									<input type="text" name="serviceCode"  class="form-control" placeholder="服务编码" readonly="readonly">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.createOrder.selOrderService()">选择</button>
										</span>
								</div>
							</div>
						</div>		
						
						<div class="form-group">
							<label class="col-lg-3 control-label">优先级：</label>
							<div class="col-lg-9">
								<div class="input-group">
									<input type="hidden" name="orderPriority" class="form-control">
									<input type="text" name="orderPriorityName"  class="form-control" placeholder="优先级" readonly="readonly">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.createOrder.selOrderPriority()">选择</button>
										</span>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">业务单据id：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="业务单据id" name="serviceOrderId" required="required" message="required:请填写">
							</div>
						</div>
											
						<div class="form-group">
							<label class="col-lg-3 control-label">单据类型：</label>
							<div class="col-lg-9">
								<div class="input-group">
									<input type="hidden" name="orderType" class="form-control">
									<input type="text" name="orderTypeName"  class="form-control" placeholder="单据类型" readonly="readonly">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.createOrder.selOrderType()">选择</button>
										</span>
								</div>
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">父单据：</label>
							<div class="col-lg-9">
								<div class="input-group">
									<input type="hidden" name="parentOrderId" class="form-control">
									<input type="text" name="parentOrderName"  class="form-control" placeholder="父单据" readonly="readonly">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.orderMonitor.selOrder()">选择</button>
										</span>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">区域：</label>
							<div class="col-lg-9">
								<div class="input-group">
									<input type="hidden" name="areaId" class="form-control">
									<input type="text" name="areaName"  class="form-control" placeholder="区域" readonly="readonly">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.createOrder.selArea()">选择</button>
										</span>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">范围id：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="范围id" name="scopeId">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">单据备注：</label>
							<div class="col-lg-9">
								<textarea rows="5" cols="5" class="form-control" placeholder="单据备注" name="orderComments"></textarea>
							</div>
						</div>

					</fieldset>
				</div>
			</div>

		</div>
	</div>
</form>
<!-- 提交form表单 end -->

<ul id="order-createOrder-tab1" class="nav nav-tabs">
   <li>
      <a href="#order-createOrder-orderFollowUser" data-toggle="tab">
       关注人
      </a>
   </li>
</ul>
<div id="order-createOrder-tab1Content" class="tab-content">
   <div class="tab-pane fade" id="order-createOrder-orderFollowUser">
<!-- 按钮 start -->
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.order.createOrder.orderFollowUserCreate()"><i class="icon-plus2 position-left"></i>新增</a></li>
								<li><a href="javascript:ruizhi.order.createOrder.orderFollowUserDel()"><i class="icon-x position-left"></i>删除</a></li>							
							</ul>
						</div>
					</div>
<!-- 按钮 end -->
   
<!-- 下面展示数据table start -->
<table id="order-createOrder-grid1" showCheck="true" height="300" idPropertyName="userId"
 rowNum="10" pagerid="order-createOrder-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="用户id" width="" propertyName="userId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="用户名称" width="" propertyName="userText" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="创建时间" width="" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="单据id" width="" propertyName="orderId" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="流程关注人id" width="" propertyName="followUserId" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="order-createOrder-page1"></div>
<!-- 下面展示数据table end -->

   </div>
</div>

</div>

<!-- 下面页面按钮 start -->
<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.order.createOrder.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.order.createOrder.doClose()">关闭</button>
</div>
<!-- 下面页面按钮 end -->

<#include "../../includes/modal-foot.ftl">
	
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/order/orderMonitor/js/createOrder.js"></script>
<!-- 引入该页面js end -->

</html>
