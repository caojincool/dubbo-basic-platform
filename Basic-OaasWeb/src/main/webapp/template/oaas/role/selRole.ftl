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
	<h6 class="modal-title" id="oaas-selRole-title" >选择角色窗口</h6>
</div>

<div class="modal-body" id="modalBody">

	<div class="col-md-3" id="demo-demoPageLayout-col1">
		<div class=" ui-jqgrid ui-widget ui-widget-content ui-corner-all"> 
			<!-- 标题头 开始-->
			<div class="ui-jqgrid-hdiv ui-state-default ui-corner-top" style="width: 100%;">
				<div class="ui-jqgrid-hbox">
					<table class="ui-jqgrid-htable ui-common-table " style="width: 110%" >
						<thead>
							<tr class="ui-jqgrid-labels" >
								<th  class="ui-th-column ui-th-ltr ui-state-default" style="width: 20rem;">    
									<span class="ui-jqgrid-resize ui-jqgrid-resize-ltr" style="cursor: col-resize;">&nbsp;</span>
									<div class="ui-jqgrid-sortable">角色目录</div>
								</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<!-- 标题 头结束 -->
			<div class="ui-jqgrid-bdiv" style="height: 350px; width: 100%;">
				<div>
					<div>
						<div>
							<table  style="width: 100%;">
								<tbody>
									<tr class="jqgfirstrow">
										<!-- ztree 容器开始 -->
										<td id="selRole-catalogTree-td" class="ztree" zTreeOnClick="ruizhi.oaas.selRole.catalogClick"  style="height:0px;width:100%;"></td>
										<!--ztree 容器结束 -->
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>	
		</div>
	</div>
	<div class="col-md-9">
			
		<table id="oaas-selRole-grid1" showCheck="true" height="320" idPropertyName="roleId"
			rowNum="10" pagerid="oaas-selRole-page1" onItemDblClick="ruizhi.oaas.selRole.roleDbClick"><!--onItemDblClick="itemDblClick"-->
				<tr>
					<td display="false" displayName="角色Id"  propertyName="roleId" sortType="text" align="center">&nbsp;</td>
					<td display="false" displayName="是否授权"  propertyName="empowerFlag" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="编码"  propertyName="roleCode" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="名称"  propertyName="roleName" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="允许授权" width="100" propertyName="checkbox" sortType="text" align="center" 
					 formatter="ruizhi.oaas.selRole.checkBoxFormatter" >&nbsp;</td>
					<td display="true" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
				</tr>
		</table>
		<div id="oaas-selRole-page1"></div>
	</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selRole.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.selRole.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/role/js/selRole.js"></script>
</html>
