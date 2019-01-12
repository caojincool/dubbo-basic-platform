<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<!--页面head-->
<#include "../../includes/modal-head.ftl">

</head>

<body>
<!--页面title-->
<div class="modal-header bg-primary">
	<!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
	<h6 class="modal-title" id="order-selOrder-title" >单选单据窗口</h6>
</div>

<div class="modal-body" id="modalBody">
  
    
	<div class="panel-body">
		
		<div class="row">
		
<!-- 上面查询form表单 start -->
<form class="form-horizontal" id="order-selOrder-form1" action="#">
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
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.selOrder.selArea()">选择</button>
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
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.selOrder.selOrg()">选择</button>
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


<!-- 中间按钮 start -->
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.order.selOrder.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
							</ul>
						</div>
					</div>
<!-- 中间按钮 end -->


<!-- 下面展示数据table start -->
<table id="order-selOrder-grid1" showCheck="false" height="200" idPropertyName="orderId"
 rowNum="10" pagerid="order-selOrder-page1"><!--onItemDblClick="itemDblClick"-->
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
<div id="order-selOrder-page1"></div>
<!-- 下面展示数据table end -->

		</div>
		
	</div>
	
	
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.order.selOrder.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.order.selOrder.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/order/orderMonitor/js/selOrder.js"></script>
</html>
