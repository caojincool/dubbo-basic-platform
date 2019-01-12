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
	<h6 class="modal-title" id="order-selOrderPriority-title" >单选优先级窗口</h6>
</div>

<div class="modal-body" id="modalBody">
  
    
	<div class="panel-body">
		
		<div class="row">
		
<!-- 上面查询form表单 start -->
<form class="form-horizontal" id="order-selOrderPriority-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">优先级：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="优先级" name="orderPriority"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">优先级名称：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="优先级名称" name="orderPriorityName"/>
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
								<li><a href="javascript:ruizhi.order.selOrderPriority.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
							</ul>
						</div>
					</div>
<!-- 中间按钮 end -->


<!-- 下面展示数据table start -->
<table id="order-selOrderPriority-grid1" showCheck="false" height="300" idPropertyName="orderPriority"
 rowNum="10" pagerid="order-selOrderPriority-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="优先级" width="" propertyName="orderPriority" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="优先级名称" width="" propertyName="orderPriorityName" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="状态" width="" propertyName="state" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="order-selOrderPriority-page1"></div>
<!-- 下面展示数据table end -->

		</div>
		
	</div>
	
	
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.order.selOrderPriority.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.order.selOrderPriority.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/order/orderPriority/js/selOrderPriority.js"></script>
</html>
