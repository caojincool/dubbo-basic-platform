<!DOCTYPE html>
<html lang="en">

<head>

<#include "../includes/page-head.ftl">	


</head>

<body>
<#include "../includes/page-title.ftl">
<!--不分页查询-->
注意：此页面包含列字段值利用字典表转换，包含行编辑，包含表格里面显示图片
<table id="demo-demoGrid-grid1" showCheck="false" height="200" savesuccessfunc="ruizhi.demo.demoGrid.savesuccessfunc" onItemClick="ruizhi.demo.demoGrid.itemClick" idPropertyName="id"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="姓名" width="100" propertyName="userName" sortType="text" align="left" editable="true" >&nbsp;</td>
		<td display="true" displayName="年龄" width="100" propertyName="age" sortType="text" align="left" >&nbsp;</td>
		<td display="true" displayName="是否成年" width="100" propertyName="ageName" sortType="text" align="left" formatter="ruizhi.demo.demoGrid.ageTrans">&nbsp;</td>
		<td display="true" displayName="状态" width="100" propertyName="state" sortType="text" align="left" >&nbsp;</td>
		<td display="true" displayName="状态名称" width="200" propertyName="stateName" sortType="text" align="left" formatter="ruizhi.demo.demoGrid.stateTrans">&nbsp;</td>
		<td display="true" displayName="生日" width="200" propertyName="birthDay" sortType="text" align="right">&nbsp;</td>
		<td display="true" displayName="图片id" width="" propertyName="imageId" sortType="text" align="center" >&nbsp;</td>
		<td display="true" displayName="图片" width="" propertyName="image" sortType="text" align="center" formatter="ruizhi.demo.demoGrid.imageIdTrans">&nbsp;</td>
		<td display="true" displayName="备注" width="300" propertyName="comments" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="标识" width="200" propertyName="id" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="页面数据新增修改类型" width="200" propertyName="pageDateType" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="下拉框" width="" propertyName="select" sortType="text" align="center" editable="true" edittype="select" editoptions='{"value":"FE:FedEx;IN:InTime;TN:TNT"}'>&nbsp;</td>
		<td display="true" displayName="checkbox" width="" propertyName="checkbox" sortType="text" align="center" editable="true" edittype="checkbox" editoptions='{"value":"Yes:No"}'>&nbsp;</td>
	</tr>
</table>

<input type="button" onclick="ruizhi.demo.demoGrid.loadData()" value="加载数据"/>	

<input type="button" onclick="ruizhi.demo.demoGrid.getAllItems()" value="获取所有数据"/>

<input type="button" onclick="ruizhi.demo.demoGrid.delSelectedItem()" value="删除所选数据"/>

<input type="button" onclick="ruizhi.demo.demoGrid.addEditItem()" value="新增编辑行"/>

	
<#include "../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/demo/js/demoGrid.js"></script>


</html>
