<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<!--页面head-->
<#include "../../includes/modal-head.ftl">
	
</head>

<body>
<!--页面title start-->
<div class="modal-header bg-primary">
	<!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
	<h6 class="modal-title">任务派发规则实例</h6>
</div>
<!--页面title end-->

<div class="modal-body" id="">

<!-- 提交form表单 start -->
<form class="form-horizontal" id="order-woDispRuleInstDetail-form1" action="#">
<input type="hidden" name="ruleInstId" class="form-control">
<input type="hidden" name="pageDateType" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>

						<div class="form-group">
							<label class="col-lg-3 control-label">任务派发规则：</label>
							<div class="col-lg-9">
								<div class="input-group">
									<input type="hidden" name="ruleId" class="form-control">
									<input type="text" name="ruleType"  class="form-control" placeholder="任务派发规则" readonly="readonly">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.woDispRuleInstDetail.selWoDispRule()">选择</button>
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
								<div class="input-group">
									<input type="hidden" name="partyId" class="form-control">
									<input type="text" name="partyName"  class="form-control" placeholder="接收人名称" readonly="readonly">
										<span class="input-group-btn">
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.woDispRuleInstDetail.selParty()">选择</button>
										</span>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">实例条件：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="实例条件" name="conditionId">
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 control-label">备注：</label>
							<div class="col-lg-9">
								<textarea rows="5" cols="5" class="form-control" placeholder="备注" name="remarks"></textarea>
							</div>
						</div>

					</fieldset>
				</div>
			</div>

		</div>
	</div>
</form>
<!-- 提交form表单 end -->

</div>

<!-- 下面页面按钮 start -->
<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.order.woDispRuleInstDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.order.woDispRuleInstDetail.doClose()">关闭</button>
</div>
<!-- 下面页面按钮 end -->

<#include "../../includes/modal-foot.ftl">
	
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/order/woDispRuleInst/js/woDispRuleInstDetail.js"></script>
<!-- 引入该页面js end -->

</html>
