<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<form class="form-horizontal" id="gid-gidServer-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-2 control-label">编码</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="编码" name="gidCode"/>
							</div>
						</div>
					</fieldset>
				</div>	
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-2 control-label">名称</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="名称" name="gidName"/>
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
								<li><a href="javascript:ruizhi.gid.gidServer.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.gid.gidServer.create()"><i class="icon-plus2 position-left"></i>新增</a></li>
								<li><a href="javascript:ruizhi.gid.gidServer.modify()"><i class="icon-bianji position-left"></i>编辑</a></li>
								<li><a href="javascript:ruizhi.gid.gidServer.getCurrValue()"><i class="icon-search4 position-left"></i>获取当前序列</a></li>
							</ul>
						</div>
					</div>

<table id="gid-gidServer-grid1" showCheck="false" height="300" idPropertyName="templateCode"
 rowNum="10" pagerid="gid-gidServer-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="编码" width="" propertyName="gidCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="名称" width="" propertyName="gidName" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="是否使用" width="" propertyName="isUse" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="当前值" width="" propertyName="currValue" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="缓存大小" width="" propertyName="cacheSize" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="客户端缓存" width="" propertyName="clientCacheSize" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="步长" width="" propertyName="increamentBy" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="备注" width="" propertyName="remark" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="gid-gidServer-page1"></div>

	
<#include "../../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/gid/gidServer/js/gidServer.js"></script>


</html>
