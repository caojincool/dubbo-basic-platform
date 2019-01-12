<!DOCTYPE html>
<html lang="en">

<head>

<#include "../../includes/page-head.ftl">	


</head>

<body>
<#include "../../includes/page-title.ftl">
<!--不分页查询-->

<!-- 上面查询form表单 start -->
<form class="form-horizontal" id="order-woDispRuleInst-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-6">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">任务派发规则：</label>
							<div class="col-lg-9">
								<div class="input-group">
									<input type="hidden" name="ruleId" class="form-control">
									<input type="text" name="ruleType"  class="form-control" placeholder="任务派发规则" readonly="readonly">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.woDispRuleInst.selWoDispRule()">选择</button>
										</span>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">接收人类型：</label>
							<div class="col-lg-9">
								<select name="partyType" class="select" >
									<option value="">请选择</option>
									<option value="STA">员工</option>
									<option value="USER">用户</option>
									<option value="ORG">部门</option>
									<option value="JOB">岗位</option>
									<option value="SYS">系统</option>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">接收人名称：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="接收人名称" name="partyName"/>
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
								<li><a href="javascript:ruizhi.order.woDispRuleInst.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
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
<script type="text/javascript" src="${webRoot}/template/order/woDispRuleInst/js/woDispRuleInst.js"></script>
<!-- 引入该页面js end -->

</html>