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
	<h6 class="modal-title" id="order-selOrderType-title" >单选单据类型窗口</h6>
</div>

<div class="modal-body" id="modalBody">
  
    
	<div class="panel-body">
		
		<div class="row">
		
<!-- 上面查询form表单 start -->
<form class="form-horizontal" id="order-selOrderType-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">类型编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="类型编码" name="orderType"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">类型名称：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="类型名称" name="orderTypeName"/>
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
								<li><a href="javascript:ruizhi.order.selOrderType.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
							</ul>
						</div>
					</div>
<!-- 中间按钮 end -->


<!-- 下面展示数据table start -->
<table id="order-selOrderType-grid1" showCheck="false" height="300" idPropertyName="orderType"
 rowNum="10" pagerid="order-selOrderType-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="类型编码" width="" propertyName="orderType" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="类型名称" width="" propertyName="orderTypeName" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="状态" width="" propertyName="state" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="order-selOrderType-page1"></div>
<!-- 下面展示数据table end -->

		</div>
		
	</div>
	
	
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.order.selOrderType.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.order.selOrderType.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/order/orderType/js/selOrderType.js"></script>
</html>
