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
	<h6 class="modal-title">流程适配添加</h6>
</div>
<!--页面title end-->

<div class="modal-body" id="">

<!-- 提交form表单 start -->
<form class="form-horizontal" id="order-processRuleDetail-form1" action="#">
<input type="hidden" name="ruleId" class="form-control">
<input type="hidden" name="pageDateType" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">   
					<fieldset>
							<div class="form-group">
								<label class="col-lg-3 control-label">服务编码：</label>
									<div class="col-lg-9">
											<div class="input-group">
			                                     <input type="text" name="serviceCode" class="form-control" placeholder="服务编码" readonly="readonly">
												 <input type="hidden" name="serviceId"  class="form-control" placeholder="服务ID">
												<span class="input-group-btn">
													<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.order.processRuleDetail.openProcessRule()">选择</button>
												</span>
											</div>
									  </div>
							 </div>
							<div class="form-group">
								<label class="col-lg-3 control-label">流程模板编码：</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" placeholder="流程模板编码" name="processDefineKey" remote="common/verify/verifyUniqueCode.do?tableCode=PF_PROCESS_RULE&colCode=PROCESS_DEFINE_KEY" required="required" message="required:请填写,remote:该服务编码已重复">
								</div>
							</div>

							<div class="form-group">
								<label class="col-lg-3 control-label">扩展条件ID：</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" placeholder="扩展条件ID" name="extendRuleId">
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


<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.order.processRuleDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.order.processRuleDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/order/processRule/js/processRuleDetail.js"></script>
</html>
