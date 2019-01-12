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
	<h6 class="modal-title" id="oaas-selCompany-title" >单选账号窗口</h6>
</div>

<div class="modal-body" id="modalBody">
		<div class="row">
    		<div class="col-md-12" >
    			<form class="form-horizontal" id="selCompany-company-form1" action="#">
					<div class="panel panel-flat">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-4">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-4 control-label">公司名称</label>
											<div class="col-lg-8">
												<input type="text" class="form-control" placeholder="名称" name="fullName"/>
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
							<li><a href="javascript:ruizhi.oaas.selCompany.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
						</ul>
					</div>
				</div>
				<!-- 下面展示数据table start -->
				<table id="selCompany-company-grid1" showCheck="false" height="250" idPropertyName="companyId"
				 	rowNum="10" pagerid="selCompany-company-page1" onItemDblClick="ruizhi.oaas.selCompany.companyDbClick"><!--onItemDblClick="itemDblClick"-->
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
						<td display="false" displayName="创建人" width="" propertyName="createUserName" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="创建人标识" width="" propertyName="createUserId" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="创建时间" width="" propertyName="createTime" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="最后修改人" width="" propertyName="modifyUserName" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="最后修改人Id" width="" propertyName="modifyUserId" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="最后修改时间" width="" propertyName="modifyTime" sortType="text" align="center">&nbsp;</td>
						<td display="false" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
					</tr>
				</table>
				<div id="selCompany-company-page1"></div>
			</div>
		</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.oaas.selCompany.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.oaas.selCompany.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/oaas/company/js/selCompany.js"></script>
</html>
