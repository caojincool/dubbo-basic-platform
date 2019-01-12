<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<form class="form-horizontal" id="process-processMonitor-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">ip+端口：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="address" name="address"/>
							</div>
						</div>
					</fieldset>
				</div>	
		
			</div>

<div class="row" style="color: red;">
请注意：</br>
1.记得是ip+端口</br>
2.本地的线程地址是：127.0.0.1:9080</br>
</div>

		</div>
	</div>
</form>	

					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.process.processMonitor.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
							</ul>
						</div>
					</div>

<table id="process-processMonitor-grid1" showCheck="false" height="300" idPropertyName="key"
 ><!-- rowNum="10" pagerid="process-processMonitor-page1" onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="业务名称" width="" propertyName="key" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="线程状态" width="" propertyName="value" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<!--<div id="process-processMonitor-page1"></div>-->

	
<#include "../../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/process/processMonitor/js/processMonitor.js"></script>


</html>
