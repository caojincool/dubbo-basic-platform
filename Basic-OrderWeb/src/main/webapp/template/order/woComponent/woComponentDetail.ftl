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
	<h6 class="modal-title">任务组件</h6>
</div>
<!--页面title end-->

<div class="modal-body" id="">

<!-- 提交form表单 start -->
<form class="form-horizontal" id="order-woComponentDetail-form1" action="#">
<input type="hidden" name="componentId" class="form-control">
<input type="hidden" name="pageDateType" class="form-control">
	<div class="panel panel-flat">
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
											<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.woComponentDetail.selTache()">选择</button>
										</span>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">组件编码：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="组件编码" name="componentCode" remote="common/verify/verifyUniqueCode.do?tableCode=PF_WO_COMPONENT&colCode=COMPONENT_CODE" required="required" message="required:请填写,remote:该编码已重复">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">组件名称：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="组件名称" name="componentName">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">执行顺序：</label>
							<div class="col-lg-9">
								<input type="text" class="form-control" placeholder="执行顺序" name="execIndex">
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
	<button type="button" class="btn btn-primary" onclick="ruizhi.order.woComponentDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.order.woComponentDetail.doClose()">关闭</button>
</div>
<!-- 下面页面按钮 end -->

<#include "../../includes/modal-foot.ftl">
	
</body>

<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/order/woComponent/js/woComponentDetail.js"></script>
<!-- 引入该页面js end -->

</html>
