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
	<h6 class="modal-title">选择职位</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="oaas-selectJob-form1" action="#">

	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-4">
						<div class="form-group">
							<label class="col-lg-4 control-label">职位名称:</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="职位名称" name="jobName">
							</div>
						</div>
				</div>
				
				<div class="<div class="text-right">
	               &nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selectJob.query()">查询</button>
	               &nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selectJob.clear()">清空</button>
				</div>
			</div>
			<div id="oaas-selectJob-page2"></div>
		</div>
	</div>
</form>
<!--  form -->
	
<table id="oaas-selectJob-grid1" showCheck="false" height="320" idPropertyName="jobId"
         rowNum="10" pagerid="oaas-selectJob-page1">
			<tr>
				<td display="false" displayName="职位id" width="" propertyName="jobId" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="职位名称" width="" propertyName="jobName" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="职位编码" width="" propertyName="jobCode" sortType="text" align="center">&nbsp;</td>
				<td display="false" displayName="部门id" width="" propertyName="orgId" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="所属部门" width="" propertyName="orgName" sortType="text" align="center">&nbsp;</td>
				
			</tr>
</table>
			
	<div id="oaas-selectJob-page1"></div>
	
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selectJob.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.selectJob.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/job/js/selectJob.js"></script>
</html>
