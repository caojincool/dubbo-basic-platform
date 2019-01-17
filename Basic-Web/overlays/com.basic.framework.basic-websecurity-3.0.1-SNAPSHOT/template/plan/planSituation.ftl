<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<!--页面head-->
<#include "../includes/modal-head.ftl">
	
</head>

<body>
<!--页面title-->
<div class="modal-header bg-primary">
	<!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
	<h6 class="modal-title">计划执行情况</h6>
</div>

<div class="modal-body" id="">
	
		<table id="plan-planSituation-grid1" showCheck="false" height="200" idPropertyName="planObjectId"
		rowNum="10" pagerid="plan-planSituation-page1"><!--onItemDblClick="itemDblClick"-->
			<tr>
				<td display="true" displayName="对象名称"  propertyName="objectName" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="状态"  propertyName="state" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="当前处理人"  propertyName="currDeallStaff" sortType="text" align="right">&nbsp;</td>
				<td display="true" displayName="执行人"  propertyName="execStaff" sortType="text" align="right">&nbsp;</td>
				<td display="true" displayName="确认人"  propertyName="confirmStaff" sortType="text" align="right">&nbsp;</td>
				<td display="true" displayName="执行时间"  propertyName="execTime" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="完成时间"  propertyName="finishTime" sortType="text" align="center">&nbsp;</td>
				<td display="false" displayName="标识"  propertyName="planObjectId" sortType="text" align="center">&nbsp;</td>
			</tr>
		</table>
		<div id="plan-planSituation-page1"></div>

</div>

<div class="modal-footer">
	<button type="button" class="btn btn-link" onclick="ruizhi.plan.planSituation.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/plan/js/planSituation.js"></script>
</html>
