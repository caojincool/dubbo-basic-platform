<!DOCTYPE html>
<html lang="en">

<head>

<#include "../includes/page-head.ftl">	

</head>

<#include "../includes/page-title.ftl">
<body>
<!--不分页查询-->







<div class="easyui-layout-outdiv">

<div id="demo-upDownLayout-layout" class="easyui-layout" data-options="fit:true" style="height:650px;">
    <div data-options="region:'north',title:'查询条件',split:true,border:false" style="height:183px;">
    
<!-- 上面查询form表单 start -->
<form class="form-horizontal" id="demo-upDownLayout-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="编码" name="name"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">名称：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="名称" name="type"/>
							</div>
						</div>
					</fieldset>
				</div>	
		
			</div>
		</div>
	</div>
</form>	
<!-- 上面查询form表单 end -->


<!-- 中间按钮 start -->
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.demo.upDownLayout.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.demo.upDownLayout.loadData()"><i class="icon-plus2 position-left"></i>新增</a></li>
								<li><a href="javascript:ruizhi.demo.upDownLayout.loadData()"><i class="icon-bianji position-left"></i>编辑</a></li>
								<li><a href="javascript:ruizhi.demo.upDownLayout.loadData()"><i class="icon-x position-left"></i>作废</a></li>							
							</ul>
						</div>
					</div>
<!-- 中间按钮 end -->

    </div>
    <div data-options="region:'center',title:'查询结果'" style="padding:5px">
<!-- 下面展示数据table start -->
<table id="demo-upDownLayout-grid1" showCheck="true" idPropertyName="upDownLayout"
 rowNum="10" pagerid="demo-upDownLayout-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="id" width="" propertyName="id" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="名称" width="" propertyName="userName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="生日" width="" propertyName="birthDay" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="备注" width="" propertyName="comments" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="年龄" width="" propertyName="age" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="状态" width="" propertyName="state" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="图片id" width="" propertyName="imageId" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="demo-upDownLayout-page1"></div>
<!-- 下面展示数据table end -->
    </div>
</div>



</div>



<#include "../includes/page-foot.ftl">
</body>
<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/demo/js/upDownLayout.js"></script>
<!-- 引入该页面js end -->

</html>