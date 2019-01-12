<!DOCTYPE html>
<html lang="en">

<head>

<#include "../includes/page-head.ftl">	


</head>

<body>
<#include "../includes/page-title.ftl">
<!--不分页查询-->
注意：此页面excel导入

<input type="button" onclick="ruizhi.demo.demoExcel.downloadTemplate()" value="下载导入模板"/>	
<input type="button" onclick="ruizhi.demo.demoExcel.importExcel()" value="导入excel"/>

<input type="button" onclick="ruizhi.demo.demoExcel.exportExcel()" value="导出excel"/>
<input type="button" onclick="ruizhi.demo.demoExcel.exportExcelForBigDate()" value="导出excel百万数据量（慎重！）"/>

<table id="demo-demoExcel-grid1" showCheck="false" height="200" idPropertyName="randomId"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="顺序" width="" propertyName="index" sortType="text" align="center" editable="true" >&nbsp;</td>
		<td display="true" displayName="车架码" width="" propertyName="productInstCode" sortType="text" align="center" >&nbsp;</td>
		<td display="true" displayName="时间" width="" propertyName="createTime" sortType="text" align="center" >&nbsp;</td>
		<td display="true" displayName="标识" width="" propertyName="randomId" sortType="text" align="center" >&nbsp;</td>
	</tr>
</table>


<#include "../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/demo/js/demoExcel.js"></script>


</html>
