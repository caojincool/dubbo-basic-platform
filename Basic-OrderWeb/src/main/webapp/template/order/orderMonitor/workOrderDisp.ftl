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
	<h6 class="modal-title">任务转派</h6>
</div>
<!--页面title end-->

<div class="modal-body" id="">

<!-- 提交form表单 start -->
<form class="form-horizontal" id="order-workOrderDisp-form1" action="#">
<input type="hidden" name="workOrderId" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>

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
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.workOrderDisp.selParty()">选择</button>
										</span>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">操作说明：</label>
							<div class="col-lg-9">
								<textarea rows="5" cols="5" class="form-control" placeholder="操作说明" name="operComments"></textarea>
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
	<button type="button" class="btn btn-primary" onclick="ruizhi.order.workOrderDisp.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.order.workOrderDisp.doClose()">关闭</button>
</div>
<!-- 下面页面按钮 end -->

<#include "../../includes/modal-foot.ftl">
	
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/order/orderMonitor/js/workOrderDisp.js"></script>
<!-- 引入该页面js end -->

</html>
