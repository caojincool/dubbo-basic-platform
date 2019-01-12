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
	<h6 class="modal-title" id="order-orderStateDialog-title" >单据状态选择窗口</h6>
</div>

<div class="modal-body" id="modalBody">
		<form class="form-horizontal" id="order-orderStateDialog-form1" action="#">
			<input type="hidden" class="form-control" placeholder="单行文本" name="orderStates"/>
			<!--标题 结束-->
		   <div class="panel panel-flat">
			<div class="panel-body">
				<div class="row">	
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-md-offset-1 col-md-1 control-label">单据状态</label>
							<div class="col-md-3">
								<input type="text" class="form-control" placeholder="单行文本" name="orderState"/>
							</div>
							<label class="col-md-offset-1 col-md-2 control-label">单据状态名称</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="单行文本" name="userText"/>
						</div>
					</div>
				</div>
			  </div>
			</div>
		</form>						
		<div class="navbar navbar-default navbar-xs">
			<div class="navbar-collapse collapse" id="navbar-filter">
				<ul class="nav nav-tabs nav-tabs-solid">
					<li><a href="javascript:ruizhi.order.orderStateDialog.qry()"><i class="icon-search4 position-left"></i>查询</a></li>
				</ul>
			</div>
		</div>
			
		<table id="order-orderStateDialog-grid1" showCheck="false" height="50%"  idPropertyName="userId"
		 rowNum="10" pagerid="order-orderStateDialog-page" onItemDblClick="ruizhi.order.orderStateDialog.orderDbClick"><!--onItemDblClick="itemDblClick"-->
			<tr>
				<td display="false" displayName="单据状态" width="100px" propertyName="orderState" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="单据状态名称" width="100px" propertyName="orderStateName" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="备注" width="100px" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
		<#--	<td display="false" displayName="登陆密码" width="100px" propertyName="userPassword" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="账号失效时间" width="150px" propertyName="expireTime" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="来源URL" width="200px" propertyName="planEndTime" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="创建人" width="100px" propertyName="createUserText" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="创建时间" width="150px" propertyName="createTime" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="修改人" width="100px" propertyName="modifyUserText" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="修改时间" width="150px" propertyName="modifyTime" sortType="text" align="left">&nbsp;</td>
				<td display="false" displayName="用户状态" width="100px" propertyName="state" sortType="text" align="left">&nbsp;</td>
				<td display="false" displayName="修改用户Id" width="100px" propertyName="state" sortType="text" align="left">&nbsp;</td>
				<td display="false" displayName="用户状态" width="100px" propertyName="state" sortType="text" align="left">&nbsp;</td> -->
			</tr>
		</table>
<div id="order-orderStateDialog-page"></div>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.order.orderStateDialog.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.order.orderStateDialog.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/order/orderState/js/orderStateDialog.js"></script>
</html>
