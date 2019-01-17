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
	<h6 class="modal-title" id="oaas-selUser-title" >单选账号窗口</h6>
</div>

<div class="modal-body" id="modalBody">
		<form class="form-horizontal" id="oaas-selUser-form1" action="#">
			<input type="hidden" class="form-control" placeholder="单行文本" name="staffId"/>
			<!--标题 结束-->
		   <div class="panel panel-flat">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-md-offset-1 col-md-4 control-label">登陆账号</label>
							<div class="col-md-6">
								<input type="text" class="form-control" placeholder="单行文本" name="username"/>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-md-offset-1 col-md-4 control-label">账号名称</label>
							<div class="col-md-6">
								<input type="text" class="form-control" placeholder="单行文本" name="userText"/>
							</div>
						</div>
					</div>	
				</div>
			  </div>
			</div>
		</form>						
		<div class="navbar navbar-default navbar-xs">
			<div class="navbar-collapse collapse" id="navbar-filter">
				<ul class="nav nav-tabs nav-tabs-solid">
					<li><a href="javascript:ruizhi.oaas.selUser.qry()"><i class="icon-search4 position-left"></i>查询</a></li>
				</ul>
			</div>
		</div>
			
		<table id="oaas-selUser-grid1" showCheck="false" height="50%"  idPropertyName="userId"
		 rowNum="10" pagerid="oaas-selUser-page" onItemDblClick="ruizhi.oaas.selUser.userDbClick"><!--onItemDblClick="itemDblClick"-->
			<tr>
				<td display="false" displayName="用户Id" width="100px" propertyName="userId" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="登陆账号" width="100px" propertyName="username" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="名称" width="100px" propertyName="userText" sortType="text" align="center">&nbsp;</td>
		<#--	<td display="false" displayName="登陆密码" width="100px" propertyName="userPassword" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="账号失效时间" width="150px" propertyName="expireTime" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="来源URL" width="200px" propertyName="planEndTime" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="创建人" width="100px" propertyName="createUserText" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="创建时间" width="150px" propertyName="createTime" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="修改人" width="100px" propertyName="modifyUserText" sortType="text" align="left">&nbsp;</td>
				<td display="true" displayName="修改时间" width="150px" propertyName="modifyTime" sortType="text" align="left">&nbsp;</td>
				<td display="false" displayName="用户状态" width="100px" propertyName="state" sortType="text" align="left">&nbsp;</td>
				<td display="false" displayName="修改用户Id" width="100px" propertyName="state" sortType="text" align="left">&nbsp;</td>
				<td display="false" displayName="用户状态" width="100px" propertyName="state" sortType="text" align="left">&nbsp;</td> -->
			</tr>
		</table>
<div id="oaas-selUser-page"></div>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selUser.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.selUser.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/user/js/selUser.js"></script>
</html>
