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
	<h6 class="modal-title" id="oaas-selFuncCatalog-title" >单选菜单目录窗口</h6>
</div>

<div class="modal-body" id="modalBody">
  
   
    
	<div class="panel-body">
		
		<div class="row">

			<div class="col-md-12" id="oaas-private-col1">
				<div class="formBody">
				<div class="ui-jqgrid ui-widget ui-widget-content ui-corner-all" style="height: 390px;overflow-y: auto;">
				<table id = "oaas-funcCatalog-zTree1" class="ztree" check="false" >
				</table>				
				</div>		
	
			</div>		
			
		</div>
	</div>
		
	</div>
	
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selFuncCatalog.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.selFuncCatalog.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/func/js/selCatalog.js"></script>
</html>
