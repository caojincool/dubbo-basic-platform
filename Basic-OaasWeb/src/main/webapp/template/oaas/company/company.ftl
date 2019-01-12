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
    		<div class="col-md-12" >
    			<form class="form-horizontal" id="oaas-company-form1" action="#">
					<div class="panel panel-flat">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-3">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-3 control-label">公司名称</label>
											<div class="col-lg-9">
												<input type="text" class="form-control" placeholder="名称" name="fullName"/>
											</div>
										</div>
									</fieldset>
								</div>	
								
								<div class="col-md-3">
									<div class="pull-right">
										<button type="button" class="btn btn-info" onclick="ruizhi.oaas.company.loadData()"><i class="icon-search4 position-left"></i>查询</button>
										&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn btn-success" onclick="ruizhi.oaas.company.resetForm()"><i class="icon-x position-left"></i>清除</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>	
				<div class="navbar navbar-default navbar-xs">
					<div class="navbar-collapse collapse" id="navbar-filter">
						<ul class="nav nav-tabs nav-tabs-solid">
						 <!--<li><a href="javascript:ruizhi.oaas.company.loadData()"><i class="icon-search4 position-left"></i>查询</a></li> -->
							<li><a href="javascript:ruizhi.oaas.company.create()"><i class="icon-plus2 position-left"></i>新增</a></li>
							<li><a href="javascript:ruizhi.oaas.company.modify()"><i class="icon-pencil4 position-left"></i>修改</a></li>
							<li><a href="javascript:ruizhi.oaas.company.del()"><i class="icon-x position-left"></i>删除</a></li>
							<li><a href="javascript:ruizhi.oaas.company.oaToErp()"><i class="icon-folder-plus2 position-left"></i>同步OA组织</a></li>
						<!--li><a href="javascript:ruizhi.oaas.company.resetForm()"><i class="icon-rotate-ccw3 position-left"></i>重置查询</a></li>  -->
						</ul>
					</div>
				</div>
				<!-- 下面展示数据table start -->
				<table id="oaas-company-grid1" showCheck="true" height="300" idPropertyName="companyId"
				 	rowNum="10" pagerid="oaas-company-page1" ><!--onItemDblClick="itemDblClick"-->
					<tr>
						<td display="false" displayName="公司标识" width="" propertyName="companyId" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="公司全称" width="" propertyName="fullName" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="公司中文名" width="" propertyName="chineseName" sortType="text" align="center" >&nbsp;</td>
						<td display="true" displayName="公司英文名" width="" propertyName="englishName" sortType="text" align="center" >&nbsp;</td>
						<td display="true" displayName="公司logo" width="" propertyName="logo" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="公司法人" width="" propertyName="legal" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="主营业务" width="" propertyName="business" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="公司地址" width="" propertyName="address" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="社会信用代码" width="" propertyName="socialCreditCode" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="公司邮箱" width="" propertyName="email" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="公司电话" width="" propertyName="telephone" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="状态" width="" propertyName="state" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="创建人" width="" propertyName="createUserName" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="创建人标识" width="" propertyName="createUserId" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="创建时间" width="" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="最后修改人" width="" propertyName="modifyUserName" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="最后修改人Id" width="" propertyName="modifyUserId" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="最后修改时间" width="" propertyName="modifyTime" sortType="text" align="center">&nbsp;</td>
						<td display="true" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
					</tr>
				</table>
				<div id="oaas-company-page1"></div>
			</div>
		</div>
	</div>
</div>
	
<#include "../../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/oaas/company/js/company.js"></script>

</html>
