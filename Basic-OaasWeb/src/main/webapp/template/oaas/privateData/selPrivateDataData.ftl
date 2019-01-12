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
	<h6 class="modal-title" id="oaas-selPrivateDataData-title" >单选数据窗口</h6>
</div>

<div class="modal-body" id="modalBody">
		<form class="form-horizontal" id="oaas-selPrivateDataData-form1" action="#">
			
		</form>						
	<#--<div class="navbar navbar-default navbar-xs">
			<div class="navbar-collapse collapse" id="navbar-filter">
				<ul class="nav nav-tabs nav-tabs-solid">
					<li><a href="javascript:ruizhi.oaas.selPrivateDataData.qry()"><i class="icon-search4 position-left"></i>查询</a></li>
				</ul>
			</div>
		</div> -->
			
		<table id="oaas-selPrivateDataData-grid1" showCheck="false" height="50%"  idPropertyName="dataDataId"
		 rowNum="10" pagerid="oaas-selPrivateDataData-page" onItemDblClick="ruizhi.oaas.selPrivateDataData.dataDbClick"><!--onItemDblClick="itemDblClick"-->
			<tr>
				<td display="false" displayName="数据Id" width="" propertyName="dataDataId" sortType="text" align="left">&nbsp;</td>
				<td display="false" displayName="父Id" width="" propertyName="parentId" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="数据编码" width="" propertyName="dataDataCode" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="数据名称" width="" propertyName="dataDataName" sortType="text" align="center">&nbsp;</td>
				<td display="false" displayName="数据范围类型" width="" propertyName="scopeType" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="数据范围类型" width="" propertyName="scopeTypeName" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="来源类型" width="" propertyName="sourceType" sortType="text" align="center">&nbsp;</td>
				<td display="false" displayName="来源id" width="" propertyName="sourceId" sortType="text" align="center">&nbsp;</td>
			</tr>
		</table>
<div id="oaas-selPrivateDataData-page"></div>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selPrivateDataData.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.selPrivateDataData.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/privateData/js/selPrivateDataData.js"></script>
</html>
