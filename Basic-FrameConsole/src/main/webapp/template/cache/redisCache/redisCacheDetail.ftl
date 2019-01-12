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
	<h6 class="modal-title">redisCache</h6>
</div>

<div class="modal-body" id="">

<!-- form -->
<form class="form-horizontal" id="cache-redisCacheDetail-form1" action="#">
<input type="hidden" name="pageDateType" class="form-control">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>
					
						<!--<div class="form-group">
							<label class="col-lg-3 control-label">cacheCode</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="cacheCode" name="cacheCode">
							</div>
						</div>-->

						<div class="form-group">
							<label class="col-lg-3 control-label">cacheCode</label>
							<div class="col-lg-8">
								<select name="cacheCode" class="select" >
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">key</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="key" name="key" required="required" message="required:请填写">
							</div>
						</div>	
					
						<div class="form-group">
							<label class="col-lg-3 control-label">value</label>
							<div class="col-lg-8">
								<input type="text" class="form-control" placeholder="value" name="value" required="required" message="required:请填写">
							</div>
						</div>	

					</fieldset>
					
				</div>
			</div>

<div class="row" style="color: red;">
请注意格式问题：</br>
1.如果是字符串：请前后加上双引号，例如："这是一个字符串"</br>
2.如果是对象：请按照你在后台定义的对象的格式，例如：{"cacheCode":"123","key":"456","value":"789"}</br>
3.cacheCode和key里面不能含有 && 的符号</br>
4.cacheCode必须在后台有定义，一般指的是某个模块</br>
</div>
		</div>
	</div>
</form>
<!--  form -->

</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="ruizhi.cache.redisCacheDetail.doSubmit()">确定</button>
	<button type="button" class="btn btn-link" onclick="ruizhi.cache.redisCacheDetail.doClose()">关闭</button>
</div>
<!--页面foot-->
<#include "../../includes/modal-foot.ftl">
	
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/cache/redisCache/js/redisCacheDetail.js"></script>
</html>
