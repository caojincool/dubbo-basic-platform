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
											<div class="ui-jqgrid-sortable">区域</div>
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
												<td id="area-areaTree-td" class="ztree" zTreeOnClick="ruizhi.oaas.area.treeClick"  style="height:0px;width:100%;"></td>
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
						<ul id="oaas-area-button1" class="nav nav-tabs nav-tabs-solid">
						<!--<li><a href="javascript:ruizhi.oaas.area.createArea()"><i class="icon-plus2 position-left"></i>新增区域</a></li>
							<li><a href="javascript:ruizhi.oaas.area.createSubArea()"><i class="icon-plus2 position-left"></i>新增子区域</a></li>
							<li><a href="javascript:ruizhi.oaas.area.editArea()"><i class="icon-pencil4 position-left"></i>修改区域</a></li>
							<li><a href="javascript:ruizhi.oaas.area.delArea()"><i class="icon-x position-left"></i>删除区域</a></li>		-->					
						</ul>
					</div>
				</div>
				<form class="form-horizontal" id="area-areaMana-from" action="#">
					<input id="parentAreaId" type="hidden"  name="parentAreaId">
					<div class="panel panel-flat">
						<!--标题 结束-->
						<div class="panel-body">
							<div class="row">
								<div class="col-md-8">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">区域编码<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input type="text" class="form-control" readonly name="areaCode">
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">区域名称<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input type="text" class="form-control" readonly name="areaName" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">显示顺序<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input type="text" class="form-control" readonly name="displayIndex" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-offset-2 col-lg-2 control-label">级别<span class="text-danger"></span></label>
											<div class="col-lg-8">
												<input type="text" class="form-control" readonly name="areaGrade" >
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
<script type="text/javascript" src="${webRoot}/template/oaas/area/js/area.js"></script>


</html>
