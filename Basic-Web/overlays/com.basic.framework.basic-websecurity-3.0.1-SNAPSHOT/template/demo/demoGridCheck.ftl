<!DOCTYPE html>
<html lang="en">

<head>

<#include "../includes/page-head.ftl">	


</head>

<body>
<#include "../includes/page-title.ftl">
<!--不分页查询-->
<table id="demo-demoGridCheck-grid1" showCheck="true" height="200" onItemDblClick="ruizhi.demo.demoGridCheck.itemDblClick" idPropertyName="id"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="姓名" width="300" propertyName="userName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="生日" width="400" propertyName="birthDay" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="备注" width="700" propertyName="comments" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="标识" width="700" propertyName="id" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>

<input type="button" onclick="ruizhi.demo.demoGridCheck.loadData()" value="加载数据"/>	
<input type="button" onclick="ruizhi.demo.demoGridCheck.getSelectedItem()" value="获取所选数据"/>
<input type="button" onclick="ruizhi.demo.demoGridCheck.getCheckedItems()" value="获取勾选数据"/>
<input type="button" onclick="ruizhi.demo.demoGridCheck.setCheckedItems()" value="设置勾选数据"/>

	
<#include "../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/demo/js/demoGridCheck.js"></script>


</html>
