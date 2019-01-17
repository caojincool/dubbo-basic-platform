<!DOCTYPE html>
<html lang="en">

<head>

<#include "../includes/page-head.ftl">	


</head>

<body>
<#include "../includes/page-title.ftl">
<!--不分页查询-->
<table 
	id = "demo-demoGridTree-grid1"  
	showCheck = "false" 
	height = "150" 
	idPropertyName = "orgId" 
	treeFlag = "true"
	treeCode = "orgCode"
	idPropertyName="orgId"
	><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="编码" width="300" propertyName="orgCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="名称" width="400" propertyName="orgName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="路径" width="700" propertyName="namePath" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="标识" width="700" propertyName="orgId" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>

<input type="button" onclick="ruizhi.demo.demoGridTree.loadData()" value="加载数据(分步加载)"/>	
<input type="button" onclick="ruizhi.demo.demoGridTree.getSelectedItem()" value="获取所选数据"/>
<input type="button" onclick="ruizhi.demo.demoGridTree.addChild()" value="增加子节点"/>
<input type="button" onclick="ruizhi.demo.demoGridTree.removeChild()" value="移除节点与子节点"/>
<input type="button" onclick="ruizhi.demo.demoGridTree.getChildItemsByRowId()" value="获取子节点数据"/>
<input type="button" onclick="ruizhi.demo.demoGridTree.getAllItmes()" value="获取所有节点"/>

<input type="button" onclick="ruizhi.demo.demoGridTree.loadData2()" value="加载数据(一次性加载)"/>	
<input type="button" onclick="ruizhi.demo.demoGridTree.loadData3()" value="加载数据(一次性加载)"/>	




<table 
	id = "demo-demoGridTree-grid2"  
	showCheck = "false" 
	height = "150" 
	idPropertyName = "orgId" 
	treeFlag = "true"
	treeCode = "orgCode"
	idPropertyName="orgId"
	><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="编码" width="300" propertyName="orgCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="名称" width="400" propertyName="orgName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="路径" width="700" propertyName="namePath" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="标识" width="700" propertyName="orgId" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
	
	
<table 
	id = "demo-demoGridTree-grid3"  
	showCheck = "false" 
	height = "150" 
	idPropertyName = "orgId" 
	treeFlag = "true"
	treeCode = "orgCode"
	idPropertyName="orgId"
	><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="编码" width="300" propertyName="orgCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="名称" width="400" propertyName="orgName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="路径" width="700" propertyName="namePath" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="标识" width="700" propertyName="orgId" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
	
<#include "../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/demo/js/demoGridTree.js"></script>


</html>
