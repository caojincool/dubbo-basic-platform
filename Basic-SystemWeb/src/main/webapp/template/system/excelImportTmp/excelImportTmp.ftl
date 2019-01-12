<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<form class="form-horizontal" id="system-excelImportTmp-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-2 control-label">模板编码</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="模板编码" name="templateCode"/>
							</div>
						</div>
					</fieldset>
				</div>	
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-2 control-label">模板名称</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="模板名称" name="templateName"/>
							</div>
						</div>
					</fieldset>
				</div>	
		
			</div>
		</div>
	</div>
</form>	

					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.system.excelImportTmp.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.system.excelImportTmp.create()"><i class="icon-plus2 position-left"></i>新增</a></li>
								<li><a href="javascript:ruizhi.system.excelImportTmp.modify()"><i class="icon-bianji position-left"></i>编辑</a></li>
								<li><a href="javascript:ruizhi.system.excelImportTmp.del()"><i class="icon-x position-left"></i>作废</a></li>							
							</ul>
						</div>
					</div>

<table id="system-excelImportTmp-grid1" showCheck="true" height="300" idPropertyName="templateCode"
 rowNum="10" pagerid="system-excelImportTmp-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="模板编码" width="" propertyName="templateCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="模板名称" width="" propertyName="templateName" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="解析模板标识" width="" propertyName="readFileInfoId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="解析模板名称" width="" propertyName="readFileInfoName" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="上传模板标识" width="" propertyName="importFileInfoId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="上传模板名称" width="" propertyName="importFileInfoName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="备注" width="" propertyName="comments" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="system-excelImportTmp-page1"></div>

	
<#include "../../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/system/excelImportTmp/js/excelImportTmp.js"></script>


</html>
