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
	<h6 class="modal-title" id="order-selTacheCatalog-title" >单选目录窗口</h6>
</div>

<div class="modal-body" id="modalBody">
  
    
	<div class="panel-body">
		
		<div class="row">
			<!-- 左边ztree开始 -->
			<div class="col-md-12" id="order-selTacheCatalog-col1">
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
												<td id="order-selTacheCatalog-zTree1" class="ztree" style="height:0px;width:100%;"></td>
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
		</div>
		
	</div>
	
	
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.order.selTacheCatalog.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.order.selTacheCatalog.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/order/tacheCatalog/js/selTacheCatalog.js"></script>
</html>
