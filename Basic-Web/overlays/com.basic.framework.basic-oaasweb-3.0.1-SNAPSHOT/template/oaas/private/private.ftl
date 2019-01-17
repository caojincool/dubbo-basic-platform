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
			<div class="col-md-2" id="demo-demoPageLayout-col1">
				<div class=" ui-jqgrid ui-widget ui-widget-content ui-corner-all"> 
					<!-- 标题头 开始-->
					<div class="ui-jqgrid-hdiv ui-state-default ui-corner-top" style="width: 100%;">
						<div class="ui-jqgrid-hbox">
							<table class="ui-jqgrid-htable ui-common-table " style="width: 110%" >
								<thead>
									<tr class="ui-jqgrid-labels" >
										<th  class="ui-th-column ui-th-ltr ui-state-default" style="width: 20rem;">    
											<span class="ui-jqgrid-resize ui-jqgrid-resize-ltr" style="cursor: col-resize;">&nbsp;</span>
											<div class="ui-jqgrid-sortable">目录</div>
										</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					<!-- 标题 头结束 -->
					<div class="ui-jqgrid-bdiv" style="height: 480px; width: 100%;">
						<div>
							<div>
								<div>
									<table  style="width: 100%;">
										<tbody>
											<tr class="jqgfirstrow">
												<!-- ztree 容器开始 -->
												<td id="oaas-catalogTree-td" class="ztree" zTreeOnClick="ruizhi.oaas.private.treeClick"  style="height:0px;width:100%;"></td>
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
    		<div class="col-md-10" id="">
    			<form class="form-horizontal" id="oaas-private-form1" action="#">
					<div class="panel panel-flat">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-3">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-3 control-label">名称</label>
											<div class="col-lg-9">
												<input type="text" class="form-control" placeholder="名称" name="privateName"/>
											</div>
										</div>
									</fieldset>
								</div>	
								
								
								<div class="col-md-3">
									<div class="pull-right">
										<button type="button" class="btn btn-info" onclick="ruizhi.oaas.private.qry()"><i class="icon-search4 position-left"></i>查询</button>
										&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn btn-success" onclick="ruizhi.oaas.private.resetForm()"><i class="icon-x position-left"></i>清除</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
				<div class="navbar navbar-default navbar-xs">
					<div class="navbar-collapse collapse" id="navbar-filter">
						<ul class="nav nav-tabs nav-tabs-solid">
							<li><a href="javascript:ruizhi.oaas.private.createCatalog()"><i class="icon-plus2 position-left"></i>新增目录</a></li>
							<li><a href="javascript:ruizhi.oaas.private.createSubCatalog()"><i class="icon-plus2 position-left"></i>新增子目录</a></li>
							<li><a href="javascript:ruizhi.oaas.private.editCatalog()"><i class="icon-pencil4 position-left"></i>修改目录</a></li>
							<li><a href="javascript:ruizhi.oaas.private.delCatalog()"><i class="icon-x position-left"></i>删除目录</a></li>
							<li><a href="javascript:ruizhi.oaas.private.createPrivate()"><i class="icon-plus2 position-left"></i>新增权限</a></li>
							<li><a href="javascript:ruizhi.oaas.private.modifyPrivate()"><i class="icon-pencil4 position-left"></i>修改权限</a></li>	
							<li><a href="javascript:ruizhi.oaas.private.delPrivate()"><i class="icon-x position-left"></i>删除权限</a></li>						
						</ul>
					</div>
				</div>
				<div>
					<!-- 下面展示权限数据private start -->
					<table id="oaas-private-grid1" showCheck="true" height="300" idPropertyName="privateId"
					 rowNum="10" pagerid="oaas-private-page1"><!--onItemDblClick="itemDblClick"-->
						<tr>
							<td display="false" displayName="权限Id" width="" propertyName="privateId" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="权限目录Id" width="" propertyName="catalogId" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="目录名" width="" propertyName="catalogName" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="编码" width="" propertyName="privateCode" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="名称" width="" propertyName="privateName" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="类型" width="" propertyName="type" sortType="text" align="center" formatter="ruizhi.oaas.private.typeTrans" >&nbsp;</td>
							<td display="false" displayName="权限类型" width="" propertyName="privateType" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="状态" width="" propertyName="state" sortType="text" align="center" formatter="ruizhi.oaas.private.stateTrans">&nbsp;</td>
							<td display="false" displayName="创建人Id" width="" propertyName="createUserId" sortType="text" align="left">&nbsp;</td>
							<td display="true" displayName="创建人" width="" propertyName="createUserName" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="创建时间" width="" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="修改人Id" width="" propertyName="modifyUserId" sortType="text" align="left">&nbsp;</td>
							<td display="true" displayName="修改人" width="" propertyName="modifyUserName" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="修改时间" width="" propertyName="modifyTime" sortType="text" align="center">&nbsp;</td>
						</tr>
					</table>
					<div id="oaas-private-page1"></div>
					<!-- 下面展示权限数据private end -->
				</div>	
			</div>
		</div>
	</div>
</div

	
<#include "../../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/oaas/private/js/private.js"></script>


</html>
