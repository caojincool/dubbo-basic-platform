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
	<h6 class="modal-title" id="oaas-selDataGroup-title" >选择数据权限分组窗口</h6>
</div>

<div class="modal-body" id="modalBody">
	  <div class="col-md-5">
	  	<div class="text-center">可分配数据权限分组</div>
		<table id="oaas-unselDataGroup-grid1" showCheck="true" height="50%"  idPropertyName="dataGroupId"
		  ><!--onItemDblClick="itemDblClick"-->
			<tr>
				<td display="false" displayName="数据分组实例Id" width="100px" propertyName="dataGroupId" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="数据分组名称" width="300" propertyName="dataGroupName" sortType="text" align="center">&nbsp;</td>
			</tr>
		</table>
	  </div>
	  <div class="col-md-2">
	  	<div class="text-center">
	  		<button onclick="ruizhi.oaas.selDataGroup.selectedBtn()">&gt;&gt;</button>
	  	</div>
	  	<div class="text-center">
	  		<button onclick="ruizhi.oaas.selDataGroup.unselectedBtn()">&lt;&lt;</button>
	  	</div>
	  </div>
	  <div class="col-md-5">
	  	<div class="text-center">已分配数据权限分组</div>
		<table id="oaas-selDataGroup-grid1" showCheck="true" height="50%"  idPropertyName="dataGroupId"
		  ><!--onItemDblClick="itemDblClick"-->
			<tr>
				<td display="false" displayName="数据分组实例Id" width="100px" propertyName="dataGroupId" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="数据分组名称" width="300" propertyName="dataGroupName" sortType="text" align="center">&nbsp;</td>
			</tr>
		</table>
	  </div>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selDataGroup.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.selDataGroup.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/user/js/selDataGroup.js"></script>
</html>
