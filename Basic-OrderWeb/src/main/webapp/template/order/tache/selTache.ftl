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
	<h6 class="modal-title" id="order-selTache-title" >单选环节窗口</h6>
</div>

<div class="modal-body" id="modalBody">
  
    
	<div class="panel-body">
		
		<div class="row">
			<!-- 左边ztree开始 -->
			<div class="col-md-2" id="order-selTache-col1">
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
												<td id="order-selTache-zTree1" class="ztree" style="height:0px;width:100%;" zTreeOnClick="ruizhi.order.selTache.treeClick"></td>
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
			<div class="col-md-10" id="order-selTache-col2">
				<!-- from表单开始 -->
				<form class="form-horizontal" id="order-selTache-form1" action="#">
					<div class="panel panel-flat">
						<div class="panel-body">
							<div class="row">
							
								<div class="col-md-12">
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
							<li><a href="javascript:ruizhi.order.selTache.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
						</ul>
					</div>
				</div>
				<!-- 按钮结束 -->
				<!-- table列表开始 -->
				<div>
					<table id="order-selTache-grid1" showCheck="false" height="300" idPropertyName="tacheId"
					 rowNum="10" pagerid="order-selTache-page1"><!--onItemDblClick="itemDblClick"-->
						<tr>
							<td display="false" displayName="环节id" width="" propertyName="tacheId" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="目录id" width="" propertyName="catalogId" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="环节编码" width="" propertyName="tacheCode" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="环节名称" width="" propertyName="tacheName" sortType="text" align="center">&nbsp;</td>
							<td display="false" displayName="状态" width="" propertyName="state" sortType="text" align="center">&nbsp;</td>
							<td display="true" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
						</tr>
					</table>
					<div id="order-selTache-page1"></div>
				</div>
				<!-- table列表结束 -->
			</div>
			<!-- 右边表单 结束 -->
		</div>
		
	</div>
	
	
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.order.selTache.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.order.selTache.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/order/tache/js/selTache.js"></script>
</html>
