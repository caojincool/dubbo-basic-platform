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
	<h6 class="modal-title" id="oaas-selDataInst-title" >选择数据实例窗口</h6>
</div>

<div class="modal-body" id="modalBody">
	  <div class="col-md-5">
	  	<div class="text-center">可分配数据权限</div>
		<table id="oaas-unselDataInst-grid1" showCheck="true" height="50%"  idPropertyName="dataInstId"
		  ><!--onItemDblClick="itemDblClick"-->
			<tr>
				<td display="false" displayName="数据实例Id" width="100px" propertyName="dataInstId" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="实例名称" width="300" propertyName="dataInstName" sortType="text" align="center">&nbsp;</td>
			</tr>
		</table>
	  </div>
	  <div class="col-md-2">
	  	<div class="text-center">
	  		<button onclick="ruizhi.oaas.selDataInst.selectedBtn()">&gt;&gt;</button>
	  	</div>
	  	<div class="text-center">
	  		<button onclick="ruizhi.oaas.selDataInst.unselectedBtn()">&lt;&lt;</button>
	  	</div>
	  </div>
	  <div class="col-md-5">
	  	<div class="text-center">已分配数据权限</div>
		<table id="oaas-selDataInst-grid1" showCheck="true" height="50%"  idPropertyName="dataInstId"
		  ><!--onItemDblClick="itemDblClick"-->
			<tr>
				<td display="false" displayName="数据实例Id" width="100px" propertyName="dataInstId" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="实例名称" width="300" propertyName="dataInstName" sortType="text" align="center">&nbsp;</td>
			</tr>
		</table>
	  </div>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selDataInst.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.selDataInst.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/user/js/selDataInst.js"></script>
</html>
