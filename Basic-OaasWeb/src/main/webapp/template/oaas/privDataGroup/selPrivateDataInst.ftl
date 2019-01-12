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
	<h6 class="modal-title" id="oaas-selPrivateDataInst-title" >单选数据权限实例</h6>
</div>

<div class="modal-body" id="modalBody">
		<form class="form-horizontal" id="oaas-selPrivateDataInst-form1" action="#">
			
		</form>						
	<#--<div class="navbar navbar-default navbar-xs">
			<div class="navbar-collapse collapse" id="navbar-filter">
				<ul class="nav nav-tabs nav-tabs-solid">
					<li><a href="javascript:ruizhi.oaas.selPrivateDataInst.qry()"><i class="icon-search4 position-left"></i>查询</a></li>
				</ul>
			</div>
		</div> -->
			
		<table id="oaas-selPrivateDataInst-grid1" showCheck="false" height="50%"  idPropertyName="dataInstId"
		 rowNum="10" pagerid="oaas-selPrivateDataInst-page" onItemDblClick="ruizhi.oaas.selPrivateDataInst.dataDbClick"><!--onItemDblClick="itemDblClick"-->
			<tr>
				<td display="false" displayName="实例ID" width="" propertyName="dataInstId" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="实例名称" width="" propertyName="dataInstName" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="数据权限编码" width="" propertyName="dataCode" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="数据权限名称" width="" propertyName="dataName" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="来源类型" width="" propertyName="scopeTypeName" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="来源URL" width="" propertyName="dataURL" sortType="text" align="center">&nbsp;</td>
				<td display="false" displayName="权限Id" width="" propertyName="dataId" sortType="text" align="center">&nbsp;</td>
			</tr>
		</table>
<div id="oaas-selPrivateDataInst-page"></div>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selPrivateDataInst.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.selPrivateDataInst.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/privDataGroup/js/selPrivateDataInst.js"></script>
</html>
