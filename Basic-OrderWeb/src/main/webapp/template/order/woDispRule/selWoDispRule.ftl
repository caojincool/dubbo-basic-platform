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
	<h6 class="modal-title" id="order-selWoDispRule-title" >单选任务派发规则窗口</h6>
</div>

<div class="modal-body" id="modalBody">
  
    
	<div class="panel-body">
		
		<div class="row">
		
<!-- 上面查询form表单 start -->
<form class="form-horizontal" id="order-selWoDispRule-form1" action="#">
	<div class="panel panel-flat">
		<!--标题 结束-->
		<div class="panel-body">
			<div class="row">
			
				<div class="col-md-12">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">环节：</label>
							<div class="col-lg-9">
								<div class="input-group">
									<input type="hidden" name="tacheId" class="form-control">
									<input type="text" name="tacheName"  class="form-control" placeholder="环节" readonly="readonly">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.selWoDispRule.selTache()">选择</button>
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
								<li><a href="javascript:ruizhi.order.selWoDispRule.loadData()"><i class="icon-search4 position-left"></i>查询</a></li>
							</ul>
						</div>
					</div>
<!-- 中间按钮 end -->


<!-- 下面展示数据table start -->
<table id="order-selWoDispRule-grid1" showCheck="false" height="300" idPropertyName="ruleId"
 rowNum="10" pagerid="order-selWoDispRule-page1"><!--onItemDblClick="itemDblClick"-->
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
<div id="order-selWoDispRule-page1"></div>
<!-- 下面展示数据table end -->

		</div>
		
	</div>
	
	
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.order.selWoDispRule.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.order.selWoDispRule.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/order/woDispRule/js/selWoDispRule.js"></script>
</html>
