<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->


<div class="panel panel-flat">
	<div class="panel-body">
		<div class="row">
			<!-- 左边ztree开始 -->
			<div class="col-md-2" id="order-tache-col1">
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
					<!-- zTree div 开始-->
					<div class="ui-jqgrid-bdiv" style="height: 480px; width: 100%;">
						<div>
							<div>
								<div>
									<table  style="width: 100%;">
										<tbody>
											<tr class="jqgfirstrow">
												<!-- ztree 容器开始 -->
												<td id="order-tache-zTree1" class="ztree" style="height:0px;width:100%;" zTreeOnClick="ruizhi.order.tache.treeClick"></td>
												<!-- ztree 容器结束 -->
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>	
					<!-- zTree div 结束-->
				</div>
			</div>
			<!-- 左边Ztree 结束 -->
			<!-- 右边表单 开始 -->
			<div class="col-md-10" id="order-tache-col2">
				<!-- from表单开始 -->
				<form class="form-horizontal" id="order-tache-form1" action="#">
					<div class="panel panel-flat">
						<div class="panel-body">
							<div class="row">
							
								<div class="col-md-6">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-3 control-label">环节编码：</label>
											<div class="col-lg-9">
												<input type="text" class="form-control" placeholder="环节编码" name="tacheCode"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-3 control-label">环节名称：</label>
											<div class="col-lg-9">
												<input type="text" class="form-control" placeholder="环节名称" name="tacheName"/>
											</div>
										</div>
									</fieldset>
								</div>		
										
							</div>
						</div>
					</div>
				</form>	
				<!-- from表单结束 -->	
				<!-- 按钮开始 -->		
				<div class="navbar navbar-default navbar-xs">
					<div class="navbar-collapse collapse" id="navbar-filter">
						<ul class="nav nav-tabs nav-tabs-solid">
							<li><a href="javascript:ruizhi.order.tache.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
							<li><a href="javascript:ruizhi.order.tache.createTacheCatalog()"><i class="icon-plus2 position-left"></i>目录新增</a></li>
							<li><a href="javascript:ruizhi.order.tache.modifyTacheCatalog()"><i class="icon-bianji position-left"></i>目录编辑</a></li>
							<li><a href="javascript:ruizhi.order.tache.delTacheCatalog()"><i class="icon-x position-left"></i>目录作废</a></li>							
							<li><a href="javascript:ruizhi.order.tache.create()"><i class="icon-plus2 position-left"></i>新增</a></li>
							<li><a href="javascript:ruizhi.order.tache.modify()"><i class="icon-bianji position-left"></i>编辑</a></li>
							<li><a href="javascript:ruizhi.order.tache.del()"><i class="icon-x position-left"></i>作废</a></li>							
						</ul>
					</div>
				</div>
				<!-- 按钮结束 -->
				<!-- table列表开始 -->
				<div>
					<table id="order-tache-grid1" showCheck="true" height="300" idPropertyName="tacheId"
					 rowNum="10" pagerid="order-tache-page1"><!--onItemDblClick="itemDblClick"-->
						<tr>
							<td display="false" displayName="环节id" width="" propertyName="tacheId" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="目录id" width="" propertyName="catalogId" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="环节编码" width="" propertyName="tacheCode" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="环节名称" width="" propertyName="tacheName" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="状态" width="" propertyName="state" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
						</tr>
					</table>
					<div id="order-tache-page1"></div>
				</div>
				<!-- table列表结束 -->
			</div>
			<!-- 右边表单 结束 -->
		</div>
	</div>
</div>


<#include "../../includes/page-foot.ftl">
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/order/tache/js/tache.js"></script>
<!-- 引入该页面js end -->

</html>