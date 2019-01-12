<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<!--页面head-->
<#include "../includes/page-head.ftl">
</head>

<body>
<!--页面title-->
<#include "../includes/page-title.ftl">
<!-- form -->
<form class="form-horizontal" id="demo-demoFile-form1" action="#">
	<div class="panel panel-flat">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<fieldset>

<div class="form-group">
	<label class="control-label col-lg-3">图片上传：</label>
	<div class="col-lg-9">
		<div class="input-group">
			<input name="fileId1" type="hidden" class="form-control" value="" inputType="image" maxFileCount="2" moduleCode="terminal">
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-lg-3">图片上传：</label>
	<div class="col-lg-9">
		<div class="input-group">
			<input name="fileId2" type="hidden" class="form-control" value="" inputType="image" placeholder="选择弹窗">
		<!--	<span class="input-group-btn">
				<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.demo.demoFile.openModalWin()">选择</button>
			</span>-->
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-lg-3">附件上传：</label>
	<div class="col-lg-9">
		<div class="input-group">
			<input name="fileId3" type="hidden" class="form-control" value="" inputType="file" placeholder="选择弹窗" moduleCode="terminal">
		<!--	<span class="input-group-btn">
				<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.demo.demoFile.openModalWin()">选择</button>
			</span>-->
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-lg-3">地图</label>
	<div class="col-lg-9">
		<div class="input-group">
			<input name="fileId4" type="hidden" class="form-control" value="" inputType="map" placeholder="选择弹窗">
		<!--	<span class="input-group-btn">
				<button class="btn btn-primary" type="button" data-toggle="modal" onclick="ruizhi.demo.demoFile.openModalWin()">选择</button>
			</span>-->
		</div>
	</div>
</div>
	
	
	
                <div class="col-md-12">
                   <div class="form-group">
	                      <label class="control-label col-lg-3">附件上传举例：</label>
	                       <div class="col-lg-9">
		                    <div class="input-group">
			                 <input name="fileInfoIds" type="hidden" class="form-control" value="" inputType="file" placeholder="选择弹窗" moduleCode="terminal">
		                    </div>
	                    </div>
                     </div>
                 </div>
               
	
	
					</fieldset>
				</div>
			</div>

		</div>
	</div>
</form>
<!--  form -->

<input type="button" onclick="ruizhi.demo.demoFile.getImageId1()" value="第一项图片Id"/>	
<input type="button" onclick="ruizhi.demo.demoFile.getImageId2()" value="第二项图片Id"/>
<input type="button" onclick="ruizhi.demo.demoFile.getFileId()" value="第三项附件Id"/>
<input type="button" onclick="ruizhi.demo.demoFile.getMapPoint()" value="第四项地图经纬度"/>
<input type="button" onclick="ruizhi.demo.demoFile.setFormValue()" value="设置表单的值"/>
<input type="button" onclick="ruizhi.demo.demoFile.getfileInfoIds()" value="附件id"/>
<!--页面foot-->	
<#include "../includes/page-foot.ftl">
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/demo/js/demoFile.js"></script>
<script type="text/javascript">
</script>
</html>
