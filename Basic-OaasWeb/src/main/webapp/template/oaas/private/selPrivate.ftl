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
	<h6 class="modal-title" id="oaas-selPrivate-title" >选择权限窗口</h6>
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
									<div class="ui-jqgrid-sortable">权限目录</div>
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
										<td id="selPrivate-catalogTree-td" class="ztree" zTreeOnClick="ruizhi.oaas.selPrivate.catalogClick"  style="height:0px;width:100%;"></td>
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
			
		<table id="oaas-selPrivate-grid1" showCheck="true" height="320" idPropertyName="privateId"
			rowNum="10" pagerid="oaas-selPrivate-page1" onItemDblClick="ruizhi.oaas.selPrivate.privateDbClick"><!--onItemDblClick="itemDblClick"-->
				<tr>
					<td display="false" displayName="权限Id"  propertyName="privateId" sortType="text" align="center">&nbsp;</td>
					<td display="false" displayName="是否授权"  propertyName="empowerFlag" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="编码"  propertyName="privateCode" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="名称"  propertyName="privateName" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="类型"  propertyName="privateType" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="允许授权" width="100" propertyName="checkbox" sortType="text" align="center" 
					 formatter="ruizhi.oaas.selPrivate.checkBoxFormatter" >&nbsp;</td>
					<td display="true" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
				</tr>
		</table>
		<div id="oaas-selPrivate-page1"></div>
	</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selPrivate.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.selPrivate.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/private/js/selPrivate.js"></script>
</html>
