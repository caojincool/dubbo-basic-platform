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
	<h6 class="modal-title">优先级</h6>
</div>
<!--页面title end-->

<div class="modal-body" id="">

<!-- 提交form表单 start -->
<form class="form-horizontal" id="order-workOrderStateDetail-form1" action="#">
<!--<input type="hidden" name="dictId" class="form-control">-->
<input type="hidden" name="pageDateType" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">任务单状态：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="任务单状态" name="workOrderState">
								<!-- remote="common/verify/verifyUniqueCode.do?tableCode=PF_WORK_ORDER_STATE&colCode=WORK_ORDER_STATE" required="required" message="required:请填写,remote:该任务单状态已重复" -->
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-lg-3 control-label">任务单状态名称：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="任务单状态名称" name="workOrderStateName">
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
	<button type="button" class="btn btn-primary" onclick="ruizhi.order.workOrderStateDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.order.workOrderStateDetail.doClose()">关闭</button>
</div>
<!-- 下面页面按钮 end -->

<#include "../../includes/modal-foot.ftl">
	
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/order/workOrderState/js/workOrderStateDetail.js"></script>
<!-- 引入该页面js end -->

</html>
