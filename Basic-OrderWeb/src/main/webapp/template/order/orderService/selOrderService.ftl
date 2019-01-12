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
	<h6 class="modal-title">我是模态窗口</h6>
</div>

<div class="modal-body" id="modalBody">

	<form class="form-horizontal" id="order-selOrderService-form1" action="#">
		 <div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-4">
					<fieldset>
					
						<div class="form-group">
							<label class="col-lg-4 control-label">服务编码</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="单行文本" name="serviceCode"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-4 control-label">服务名称</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="单行文本" name="serviceName"/>
							</div>
						</div>
					</fieldset>
				</div>	
				
			</div>
		</div>
	</div>
			</div>
		</form>						
	<div class="navbar navbar-default navbar-xs">
		<div class="navbar-collapse collapse" id="navbar-filter">
			<ul class="nav nav-tabs nav-tabs-solid">
				<li><a href="javascript:ruizhi.order.selOrderService.qry()"><i class="icon-search4 position-left"></i>查询</a></li>
			</ul>
		</div>
	</div>
	
	<table id="order-selOrderService-grid1" showCheck="true" height="150" idPropertyName="serviceId" 
	 rowNum="10" pagerid="order-selOrderService-page"><!--onItemDblClick="itemDblClick"-->
		<tr>
			<td display="false" displayName="服务Id" width="100" propertyName="serviceId" sortType="text" align="center">&nbsp;</td>
			<td display="true" displayName="服务名称" width="100" propertyName="serviceName" sortType="text" align="center">&nbsp;</td>
			<td display="true" displayName="服务编码" width="100" propertyName="serviceCode" sortType="text" align="center">&nbsp;</td>
			<td display="true" displayName="备注" width="100" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
		</tr>
	</table>
	<div id="order-selOrderService-page"></div>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.order.selOrderService.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.order.selOrderService.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/order/orderService/js/selOrderService.js"></script>
</html>
