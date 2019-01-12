<!DOCTYPE html>
<html lang="en">

<head>

<#include "../includes/page-head.ftl">	


</head>

<body>
<#include "../includes/page-title.ftl">

<!--不分页查询-->
<table id="demo-demoGridPage-grid1" showCheck="true" height="200" idPropertyName="id" rowNum="10" pagerid="demo-demoGridPage-page1" 
	onItemDblClick="ruizhi.demo.demoGridPage.itemDblClick" ><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="姓名" width="300" propertyName="userName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="生日" width="400" propertyName="birthDay" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="备注" width="700" propertyName="comments" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="标识" width="700" propertyName="id" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="demo-demoGridPage-page1"></div>

<input type="button" onclick="ruizhi.demo.demoGridPage.loadData()" value="加载数据"/>	
<input type="button" onclick="ruizhi.demo.demoGridPage.getSelectedItem()" value="获取所选数据"/>
<input type="button" onclick="ruizhi.demo.demoGridPage.getCheckedItems()" value="获取勾选数据"/>
	
<#include "../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/demo/js/demoGridPage.js"></script>


</html>
