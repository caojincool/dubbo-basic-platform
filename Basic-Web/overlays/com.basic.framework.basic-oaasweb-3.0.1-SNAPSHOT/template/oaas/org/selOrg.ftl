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
	<h6 class="modal-title" id="oaas-selOrg-title" >单部门域窗口</h6>
</div>

<div class="modal-body" id="modalBody">
  
   
    
	<div class="panel-body">
		
		<div class="row">

			<div class="col-md-12" id="oaas-area-col1">
                 
				<div class="formBody">
				<div class="ui-jqgrid ui-widget ui-widget-content ui-corner-all" style="height: 390px;overflow-y: auto;">
				<table id = "oaas-org-zTree1" class="ztree" showCheck="false" >
				</table>				
				</div>		
	
			</div>		
			
		</div>
	</div>
		
	</div>
	
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selArea.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.selArea.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/org/js/selOrg.js"></script>
</html>
