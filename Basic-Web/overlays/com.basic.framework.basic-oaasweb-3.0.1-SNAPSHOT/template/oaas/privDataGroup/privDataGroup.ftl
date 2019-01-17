<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">

<div class="panel panel-flat">
	<div class="panel-body">
		<div class="row">
    		<div class="col-md-3" >
    			<ul id="oaas-privDataGroup-tab" class="nav nav-tabs">
				   <li>
				      <a href="#oaas-privDataGroup-grid" data-toggle="tab" >
				        	数据权限分组
				      </a>
				   </li>
				</ul>
				<div class="navbar navbar-default navbar-xs">
					<div class="navbar-collapse collapse" id="navbar-filter">
						<ul class="nav nav-tabs nav-tabs-solid">
							<li><a href="javascript:ruizhi.oaas.privDataGroup.create()"><i class="icon-plus2 position-left"></i>新增分组</a></li>
							<li><a href="javascript:ruizhi.oaas.privDataGroup.modify()"><i class="icon-pencil4 position-left"></i>修改分组</a></li>
							<li><a href="javascript:ruizhi.oaas.privDataGroup.del()"><i class="icon-x position-left"></i>删除分组</a></li>
						</ul>
					</div>
				</div>
				<!-- 下面展示数据table start -->
				<table id="oaas-privDataGroup-grid1" showCheck="true" height="400" idPropertyName="dataGroupId"
				 	rowNum="10" pagerid="oaas-privDataGroup-page1" onItemClick="ruizhi.oaas.privDataGroup.itemClick"><!--onItemDblClick="itemDblClick"-->
					<tr>
						<td display="false" displayName="数据权限Id" width="" propertyName="dataGroupId" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="编码" width="" propertyName="dataGroupCode" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="名称" width="" propertyName="dataGroupName" sortType="text" align="center">&nbsp;</td>
					</tr>
				</table>
				<div id="oaas-privDataGroup-page1"></div>
				<!-- 下面展示数据table end -->
			</div>
			<div class="col-md-9">
				<div class="col-md-12">
					<ul id="oaas-dataInst-tab" class="nav nav-tabs">
					   <li>
					      <a href="#oaas-privDataGroupInstTab-grid" data-toggle="tab" >
					        	数据权限实例
					      </a>
					   </li>
					</ul>
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" >
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.oaas.privDataGroupInst.create()"><i class="icon-plus2 position-left"></i>添加实例</a></li>
								<li><a href="javascript:ruizhi.oaas.privDataGroupInst.del()"><i class="icon-x position-left"></i>删除实例</a></li>							
							</ul>
						</div>
					</div>
					<table id="oaas-dataInst-grid" showCheck="true" height="150" idPropertyName="dataGroupInstId" class="tab-content"
					 	rowNum="10" pagerid="oaas-dataInstTab-page1" onItemClick="ruizhi.oaas.privDataGroupInst.itemClick" >
						<tr>
							<td display="false" displayName="数据实例Id" width="" propertyName="dataInstId" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="数据权限Id" width="" propertyName="dataId" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="数据范围类型" width="" propertyName="scopeType" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="编码" width="" propertyName="dataCode" sortType="text" align="center" formatter="">&nbsp;</td>
							<td display="true" displayName="名称" width="" propertyName="dataName" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="类型" width="" propertyName="scopeType" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="类型" width="" propertyName="scopeTypeName" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="来源URL" width="" propertyName="dataURL" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="创建人" width="" propertyName="createUserName" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="创建时间" width="" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="修改人" width="" propertyName="modifyUserName" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="修改时间" width="" propertyName="modifyTime" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="状态" width="" propertyName="state" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
						</tr>
					</table>
					<div id="oaas-dataInstTab-page1"></div>
				</div>
				<div class="col-md-12">
					<ul id="oaas-privInstData-tab" class="nav nav-tabs">
					   <li>
					      <a href="#oaas-instData-grid" data-toggle="tab" >
					        	数据权限实例数据
					      </a>
					   </li>
					</ul>
					<table id="oaas-privInstData-grid" showCheck="false" height="150" idPropertyName="dataInstDataId" class="tab-content"
					 	rowNum="10" pagerid="oaas-instData-page1"  >
						<tr>
							<td display="true" displayName="数据标识" width="" propertyName="dataInstDataId" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="数据名称" width="" propertyName="sourceName" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="源数据标识" width="" propertyName="dataDataId" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="源数据名称" width="" propertyName="sourceName" sortType="text" align="center">&nbsp;</td>
						
						</tr>
					</table>
					<div id="oaas-instData-page1"></div>
				</div>
			<div>
		</div>
	</div>
</div

	
<#include "../../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/oaas/privDataGroup/js/privDataGroup.js"></script>
<script type="text/javascript" src="${webRoot}/template/oaas/privDataGroup/js/privDataGroupInst.js"></script>
<script type="text/javascript" src="${webRoot}/template/oaas/privDataGroup/js/privInstData.js"></script>

</html>
