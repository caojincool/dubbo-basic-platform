<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<form class="form-horizontal" id="cache-redisCache-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-2 control-label">key</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="key" name="key"/>
							</div>
						</div>
					</fieldset>
				</div>	
		
			</div>

<div class="row" style="color: red;">
请注意：</br>
1.redis服务器上的key组成：cacheCode&&key</br>
2.模糊查询请在前面或者后面加上*</br>
</div>

		</div>
	</div>
</form>	

					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.cache.redisCache.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.cache.redisCache.create()"><i class="icon-plus2 position-left"></i>新增</a></li>
								<li><a href="javascript:ruizhi.cache.redisCache.modify()"><i class="icon-bianji position-left"></i>编辑</a></li>
								<li><a href="javascript:ruizhi.cache.redisCache.del()"><i class="icon-x position-left"></i>作废</a></li>				
							</ul>
						</div>
					</div>

<table id="cache-redisCache-grid1" showCheck="false" height="300" idPropertyName="cacheCode+key"
 ><!-- rowNum="10" pagerid="cache-redisCache-page1" onItemDblClick="itemDblClick"-->
	<tr>
		<td display="true" displayName="cacheCode" width="" propertyName="cacheCode" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="key" width="" propertyName="key" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="value" width="" propertyName="value" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<!--<div id="cache-redisCache-page1"></div>-->

	
<#include "../../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/cache/redisCache/js/redisCache.js"></script>


</html>
