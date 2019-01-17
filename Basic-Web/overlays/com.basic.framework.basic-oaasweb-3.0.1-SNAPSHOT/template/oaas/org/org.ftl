<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">

<div class="panel panel-flat">
	<div class="panel-body">
		<div class="row">
			<div class="col-md-2" id="demo-demoPageLayout-col1">
				<div class=" ui-jqgrid ui-widget ui-widget-content ui-corner-all"> 
					<!-- 标题头 开始-->
					<div class="ui-jqgrid-hdiv ui-state-default ui-corner-top" style="width: 100%;">
						<div class="ui-jqgrid-hbox">
							<table class="ui-jqgrid-htable ui-common-table " style="width: 110%" >
								<thead>
									<tr class="ui-jqgrid-labels" >
										<th  class="ui-th-column ui-th-ltr ui-state-default" style="width: 20rem;">    
											<span class="ui-jqgrid-resize ui-jqgrid-resize-ltr" style="cursor: col-resize;">&nbsp;</span>
											<div class="ui-jqgrid-sortable">部门</div>
										</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					<!-- 标题 头结束 -->
					<div class="ui-jqgrid-bdiv" style="height: 480px; width: 100%;">
						<div>
							<div>
								<div>
									<table  style="width: 100%;">
										<tbody>
											<tr class="jqgfirstrow">
												<!-- ztree 容器开始 -->
												<td id="org-orgTree-td" class="ztree" zTreeOnClick="ruizhi.oaas.org.treeClick"  style="height:0px;width:100%;"></td>
												<!--ztree 容器结束 -->
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>	
				</div>
			</div>
    		<div class="col-md-10" id="demo-demoPageLayout-col2">
				<div class="navbar navbar-default navbar-xs">
					<div class="navbar-collapse collapse" id="navbar-filter">
						<ul class="nav nav-tabs nav-tabs-solid">
							<li><a href="javascript:ruizhi.oaas.org.createOrg()"><i class="icon-plus2 position-left"></i>新增部门</a></li>
							<li><a href="javascript:ruizhi.oaas.org.createSubOrg()"><i class="icon-plus2 position-left"></i>新增子部门</a></li>
							<li><a href="javascript:ruizhi.oaas.org.editOrg()"><i class="icon-pencil4 position-left"></i>修改部门</a></li>
							<li><a href="javascript:ruizhi.oaas.org.delOrg()"><i class="icon-x position-left"></i>删除部门</a></li>							
						</ul>
					</div>
				</div>
				<form class="form-horizontal" id="org-orgMana-from" action="#">
					<input id="parentOrgId" type="hidden"  name="parentOrgId">
					<div class="panel panel-flat">
						<!--标题 结束-->
						<div class="panel-body">
							<div class="row">
								<div class="col-md-8">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">部门编码<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input type="text" class="form-control" readonly name="orgCode">
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">部门名称<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input type="text" class="form-control" readonly name="orgName" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">显示顺序<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input type="text" class="form-control" readonly name="displayIndex" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">所属区域<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input type="text" class="form-control" readonly name="areaName" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">创建人<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input type="text" class="form-control"  readonly name="createUserName" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">创建时间<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input type="text" class="form-control"  readonly name="createTime">
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">最后修改人<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input type="text" class="form-control"  readonly name="modifyUserName">
												<input type="hidden" name="modifyUserId">
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">最后修改时间<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input type="text" class="form-control"  readonly name="modifyTime" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">ID路径<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input id="idPath" type="text" class="form-control"   readonly name="idPath" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">编码路径<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input id="codePath" type="text" class="form-control"  readonly name="codePath" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">名称路径<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input id="namePath" type="text" class="form-control"  readonly name="namePath" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">备注<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<textarea name = "remarks" class="form-control" rows="3" readonly></textarea>
											</div>
										</div>
									<!--<div class="form-group">
											<div class="col-lg-3 col-lg-offset-6">
												<button type="submit" class="btn btn-default">确定</button>
												<button type="button" class="btn btn-default">取消</button>
											</div>
										</div> -->
										
									</fieldset>
								</div>
							</div>
						</div>
					</div>
				</form>	
			</div>
		</div>
	</div>
</div

	
<#include "../../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/oaas/org/js/org.js"></script>


</html>
