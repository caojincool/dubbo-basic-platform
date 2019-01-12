<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<!-- 上面查询form表单 start -->
<form class="form-horizontal" id="order-woDispRule-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">环节：</label>
							<div class="col-lg-9">
								<div class="input-group">
									<input type="hidden" name="tacheId" class="form-control">
									<input type="text" name="tacheName"  class="form-control" placeholder="环节" readonly="readonly">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.woDispRule.selTache()">选择</button>
										</span>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">自定义规则：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="自定义规则" name="dispCode"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">规则类型：</label>
							<div class="col-lg-9">
								<select name="ruleType" class="select" >
									<option value="">请选择</option>
									<option value="AREA">按区域派单</option>
									<option value="ORG">按组织派单</option>
									<option value="USER">按用户派单</option>
									<option value="STA">按员工派单</option>
									<option value="SELF">自定义</option>
								</select>
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
								<li><a href="javascript:ruizhi.order.woDispRule.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
								<li><a href="javascript:ruizhi.order.woDispRule.create()"><i class="icon-plus2 position-left"></i>新增</a></li>
								<li><a href="javascript:ruizhi.order.woDispRule.modify()"><i class="icon-bianji position-left"></i>编辑</a></li>
								<li><a href="javascript:ruizhi.order.woDispRule.del()"><i class="icon-x position-left"></i>作废</a></li>							
							</ul>
						</div>
					</div>
<!-- 中间按钮 end -->


<!-- 下面展示数据table start -->
<table id="order-woDispRule-grid1" showCheck="true" height="300" idPropertyName="ruleId"
 rowNum="10" pagerid="order-woDispRule-page1"
 onItemClick="ruizhi.order.woDispRule.woDispRuleItemClick"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="任务派发规则id" width="" propertyName="ruleId" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="环节id" width="" propertyName="tacheId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="环节名称" width="" propertyName="tacheName" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="规则类型" width="" propertyName="ruleType" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="自定义规则" width="" propertyName="dispCode" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="状态" width="" propertyName="state" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="order-woDispRule-page1"></div>
<!-- 下面展示数据table end -->






<!-- 规则实例 -->
<!-- 中间按钮 start -->
					<div class="navbar navbar-default navbar-xs">
						<div class="navbar-collapse collapse" id="navbar-filter">
							<ul class="nav nav-tabs nav-tabs-solid">
								<li><a href="javascript:ruizhi.order.woDispRuleInst.create()"><i class="icon-plus2 position-left"></i>新增</a></li>
								<li><a href="javascript:ruizhi.order.woDispRuleInst.modify()"><i class="icon-bianji position-left"></i>编辑</a></li>
								<li><a href="javascript:ruizhi.order.woDispRuleInst.del()"><i class="icon-x position-left"></i>作废</a></li>							
							</ul>
						</div>
					</div>
<!-- 中间按钮 end -->


<!-- 下面展示数据table start -->
<table id="order-woDispRuleInst-grid1" showCheck="true" height="300" idPropertyName="ruleInstId"
 rowNum="10" pagerid="order-woDispRuleInst-page1"><!--onItemDblClick="itemDblClick"-->
	<tr>
		<td display="false" displayName="任务派发规则实例id" width="" propertyName="ruleInstId" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="任务派发规则id" width="" propertyName="ruleId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="规则类型" width="" propertyName="ruleType" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="实例条件" width="" propertyName="conditionId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="环节名称" width="" propertyName="tacheName" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="接收人类型" width="" propertyName="partyType" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="接收人类型" width="" propertyName="partyTypeName" sortType="text" align="center" formatter="ruizhi.order.woDispRuleInst.partyTypeTrans">&nbsp;</td>
		<td display="false" displayName="接收人id" width="" propertyName="partyId" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="接收人名称" width="" propertyName="partyName" sortType="text" align="center">&nbsp;</td>
		<td display="false" displayName="状态" width="" propertyName="state" sortType="text" align="center">&nbsp;</td>
		<td display="true" displayName="备注" width="" propertyName="remarks" sortType="text" align="center">&nbsp;</td>
	</tr>
</table>
<div id="order-woDispRuleInst-page1"></div>
<!-- 下面展示数据table end -->






<#include "../../includes/page-foot.ftl">
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/order/woDispRule/js/woDispRule.js"></script>

<script type="text/javascript" src="${webRoot}/template/order/woDispRuleInst/js/woDispRuleInst.js"></script>
<!-- 引入该页面js end -->

</html>