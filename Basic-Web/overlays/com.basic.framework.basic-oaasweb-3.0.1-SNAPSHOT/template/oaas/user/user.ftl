<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<!-- 查询form开始 -->
<form class="form-horizontal" id="oaas-user-form" action="#">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
					  <div class="col-md-2">
						<div class="form-group">
							<label class="col-md-3 control-label">部门</label>
							<div class="col-md-9">
								<div class="input-group">
									<input type="hidden"  name="orgId" >
									<input type="text" class="form-control" placeholder="选择部门" name="orgName"  readonly  >
									<span class="input-group-btn">
										<button id="selectBtn" class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.oaas.user.selOrg()">选择</button>
									</span>
								</div>
							</div>
						</div>
					  </div>
					  <div class="col-md-2">
						<div class="form-group">
							<label class="col-md-4 control-label">登陆账号</label>
							<div class="col-md-8">
								<input type="text" class="form-control" placeholder="单行文本" name="username"/>
							</div>
						</div>
					  </div>
					  <div class="col-md-2">
						<div class="form-group">
							<label class="col-md-4 control-label">账号名称</label>
							<div class="col-md-8">
								<input type="text" class="form-control" placeholder="单行文本" name="userText"/>
							</div>
						</div>
					  </div>
					  <div class="col-md-3">
						<div class="form-group">
							<label class="col-md-2 control-label">有效期</label>
							<div class="col-md-10 form-inline">
								<div class="input-group pull-left col-md-5">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="beginExpireTime" class="form-control datetimepicker" format="YYYY-MM-DD" value=""/>
								</div>
								<div class="col-md-1 pull-left text-center">
								 	<span style="line-height:36px;"> - </span>
								</div>
								<div class="input-group col-md-5">
									<span class="input-group-addon"><i class="icon-rili"></i></span>
									<input type="text" name="endExpireTime" class="form-control datetimepicker" format="YYYY-MM-DD" value=""/>
								</div>
							</div>
						</div>
					 </div>
					 
					<div class="col-md-3">
						<div class="pull-right">
								<button type="button" class="btn btn-info" onclick="ruizhi.oaas.user.loadData()"><i class="icon-search4 position-left"></i>查询</button>
								&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-success" onclick="ruizhi.oaas.user.resetForm()"><i class="icon-x position-left"></i>清除</button>
						</div>
					</div>
				</div>	
		
			</div>
		</div>
	</div>
</form>	
<!-- /查询form结束 --> 

<!-- 按钮开始 -->  
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.oaas.user.add()"><i class="icon-plus2 position-left"></i>新增账号</a></li>
								<li><a href="javascript:ruizhi.oaas.user.edit()"><i class="icon-pencil4 position-left"></i>修改账号</a></li>
								<li><a href="javascript:ruizhi.oaas.user.del()"><i class="icon-x position-left"></i>删除账号</a></li>
								<li><a href="javascript:ruizhi.oaas.user.resetPwd()"><i class="icon-rotate-cw3 position-left"></i>重置密码</a></li>
								<li><a href="javascript:ruizhi.oaas.user.selRole()"><i class="icon-gear position-left"></i>设置角色</a></li>
								<li><a href="javascript:ruizhi.oaas.user.selPrivate()"><i class="icon-gear position-left"></i>设置权限</a></li>
								<li><a href="javascript:ruizhi.oaas.user.selDataGroup()"><i class="icon-gear position-left"></i>设置数据权限分组</a></li>
								<li><a href="javascript:ruizhi.oaas.user.selDataInst()"><i class="icon-gear position-left"></i>设置数据权限</a></li>
							<!--<li><a href="javascript:ruizhi.oaas.user.resetForm()"><i class="icon-rotate-ccw3 position-left"></i>重置查询</a></li> -->
							</ul>
						</div>
					</div>
<!-- /按钮结束 --> 

<!-- table开始 --> 	
<table id="oaas-user-grid" showCheck="true" height="300" onItemClick="ruizhi.oaas.user.itemClick" idPropertyName="userId"
 rowNum="10" pagerid="oaas-user-page"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="用户Id" width="100px" propertyName="userId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="登陆账号" width="100px" propertyName="username" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="名称" width="100px" propertyName="userText" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="登陆密码" width="100px" propertyName="userPassword" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="账号失效时间" width="150px" propertyName="expireTime" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="来源URL" width="200px" propertyName="planEndTime" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="创建人" width="100px" propertyName="createUserText" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="创建时间" width="150px" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="修改人" width="100px" propertyName="modifyUserText" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="修改时间" width="150px" propertyName="modifyTime" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="用户状态" width="100px" propertyName="state" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="修改用户Id" width="100px" propertyName="state" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="用户状态" width="100px" propertyName="state" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="oaas-user-page"></div>
<div class="row">
	<ul id="oaas-user-tab" class="nav nav-tabs">
	   <li>
	      <a href="#oaas-userRole-tab" data-toggle="tab" >
	        	角色
	      </a>
	   </li>
	   <li>
	      <a href="#oaas-userPrivate-tab" data-toggle="tab" >
	        	权限
	      </a>
	   </li>
	    <li>
	      <a href="#oaas-dataGroup-tab" data-toggle="tab" >
	        	数据权限分组
	      </a>
	   </li>
	    <li>
	      <a href="#oaas-userData-tab" data-toggle="tab" >
	        	数据权限
	      </a>
	   </li>
	</ul>
</div>
<div class="tab-content">
	<div class="row tab-pane fade" id="oaas-userRole-tab">
		<div class="col-md-3 ">
			<table id="oaas-userRole-grid"  height="200" onItemClick="ruizhi.oaas.userRole.itemClick" idPropertyName="roleId"
		 		rowNum="10" pagerid="oaas-userRole-page"><!--onItemDblClick="itemDblClick"-->
				<tr>
					<td display="true" displayName="角色编码" width="100px" propertyName="roleCode" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="角色名称" width="100px" propertyName="roleName" sortType="text" align="center">&nbsp;</td>
				</tr>
			</table>
			<div id="oaas-userRole-page"></div>
		</div>
		<div class="col-md-9">
			<table id="oaas-rolePrivate-grid"  height="200"  idPropertyName="privateId"
		 		rowNum="10" pagerid="oaas-rolePrivate-page"><!--onItemDblClick="itemDblClick"-->
				<tr>
					<td display="false" displayName="权限Id" width="" propertyName="privateId" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="权限编码" width="" propertyName="privateCode" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="权限名称" width="" propertyName="privateName" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="权限类型" width="" propertyName="privateType" sortType="text" align="center" formatter="ruizhi.oaas.user.typeTrans">&nbsp;</td>
				</tr>
			</table>
			<div id="oaas-rolePrivate-page"></div>
		</div>
	</div>
	<div class="row tab-pane fade" id="oaas-userPrivate-tab">
		<table id="oaas-userPrivate-grid"  height="200"  idPropertyName="privateId"
	 		rowNum="10" pagerid="oaas-userPrivate-page"><!--onItemDblClick="itemDblClick"-->
			<tr>
				<td display="false" displayName="权限Id" width="" propertyName="privateId" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="权限编码" width="" propertyName="privateCode" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="权限名称" width="" propertyName="privateName" sortType="text" align="center">&nbsp;</td>
				<td display="true" displayName="权限类型" width="" propertyName="privateType" sortType="text" align="center" formatter="ruizhi.oaas.user.typeTrans">&nbsp;</td>
				<td display="true" displayName="权限来源" width="" propertyName="privateSource" sortType="text" align="center">&nbsp;</td>
			</tr>
		</table>
		<div id="oaas-userPrivate-page"></div>
	</div>
	<div class="row tab-pane fade" id="oaas-dataGroup-tab">
		<div class="col-md-3 ">
			<table id="oaas-group-grid"  height="200" onItemClick="ruizhi.oaas.userDataGroup.itemClick" idPropertyName="dataGroupId"
		 		rowNum="10" pagerid="oaas-group-page"><!--onItemDblClick="itemDblClick"-->
				<tr>
					<td display="true" displayName="分组编码" width="100px" propertyName="dataGroupCode" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="分组名称" width="100px" propertyName="dataGroupName" sortType="text" align="center">&nbsp;</td>
				</tr>
			</table>
			<div id="oaas-group-page"></div>
		</div>
		<div class="col-md-4">
			<table id="oaas-groupPrivateInst-grid"  height="200"  idPropertyName="dataInstId"
		 		rowNum="10" pagerid="oaas-groupPrivateInst-page" onItemClick="ruizhi.oaas.userDataGroup.instItemClick"><!--onItemDblClick="itemDblClick"-->
				<tr>
					<td display="false" displayName="权限Id" width="" propertyName="privateId" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="权限编码" width="" propertyName="dataCode" sortType="text" align="center" formatter="">&nbsp;</td>
					<td display="true" displayName="权限名称" width="" propertyName="dataName" sortType="text" align="center">&nbsp;</td>
					<td display="false" displayName="类型" width="" propertyName="scopeType" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="权限类型" width="" propertyName="scopeTypeName" sortType="text" align="center">&nbsp;</td>
				</tr>
			</table>
			<div id="oaas-groupPrivateInst-page"></div>
		</div>
		<div class="col-md-5">
			<table id="oaas-groupInstData-grid"  height="200"  idPropertyName="privateId"
		 		rowNum="10" pagerid="oaas-groupInstData-page"><!--onItemDblClick="itemDblClick"-->
				<tr>
					<td display="true" displayName="数据标识" width="" propertyName="dataInstDataId" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="数据名称" width="" propertyName="sourceName" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="源数据标识" width="" propertyName="dataDataId" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="源数据名称" width="" propertyName="sourceName" sortType="text" align="center">&nbsp;</td>
				</tr>
			</table>
			<div id="oaas-groupInstData-page"></div>
		</div>
	</div>
	<div class="row tab-pane fade" id="oaas-userData-tab">
		<div class="col-md-6"> 
			<table id="oaas-userDataInst-grid"  height="200"  idPropertyName="dataInstId"
		 		rowNum="10" pagerid="oaas-userDataInst-page" onItemClick="ruizhi.oaas.userData.itemClick"><!--onItemDblClick="itemDblClick"-->
				<tr>
					<td display="false" displayName="权限Id" width="" propertyName="dataId" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="编码" width="" propertyName="dataCode" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="名称" width="" propertyName="dataName" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="类型" width="" propertyName="scopeTypeName" sortType="text" align="center" >&nbsp;</td>
					<td display="true" displayName="来源" width="" propertyName="dataSource" sortType="text" align="center" >&nbsp;</td>
				</tr>
			</table>
			<div id="oaas-groupPrivateInst-page"></div>
		</div>
		<div class="col-md-6">
			<table id="oaas-userDataInstData-grid"  height="200"  idPropertyName="privateId"
		 		rowNum="10" pagerid="oaas-userDataInstData-page"><!--onItemDblClick="itemDblClick"-->
				<tr>
					<td display="true" displayName="数据标识" width="" propertyName="dataInstDataId" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="数据名称" width="" propertyName="sourceName" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="源数据标识" width="" propertyName="dataDataId" sortType="text" align="center">&nbsp;</td>
					<td display="true" displayName="源数据名称" width="" propertyName="sourceName" sortType="text" align="center">&nbsp;</td>
				</tr>
			</table>
			<div id="oaas-userDataInstData-page"></div>
		</div>
	</div>
</div>
	
<#include "../../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/oaas/user/js/user.js"></script>
<script type="text/javascript" src="${webRoot}/template/oaas/user/js/userRole.js"></script>
<script type="text/javascript" src="${webRoot}/template/oaas/user/js/userPrivate.js"></script>
<script type="text/javascript" src="${webRoot}/template/oaas/user/js/userDataGroup.js"></script>
<script type="text/javascript" src="${webRoot}/template/oaas/user/js/userData.js"></script>
</html>
