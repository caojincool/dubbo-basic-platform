<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<style type="text/css">
</style>
<head>
<#include "../includes/page-head.ftl">
</head>

<body>
<#include "../includes/page-title.ftl">
	<form class="form-horizontal" id="lendingStatistical-form" action="#">
		<div class="panel panel-flat">
			<!--标题 结束-->
			<div class="panel-body">
				<div class="row">	
				 
					<div class="col-md-2">
							<div class="form-group">
								<label class="col-lg-5 control-label text-right">项目编码：</label>
								<div class="col-lg-7">
									 <input type="text" class="form-control" id="projectOrderCode"  name="projectOrderCode" />
									 <input type="hidden" id="fileName" name="fileName" class="form-control"/>
								</div>
							</div>
					</div>
					<div class="col-md-2">
							<div class="form-group">
								<label class="col-lg-5 control-label text-right">借款人：</label>
								<div class="col-lg-7">
									 <input type="text" class="form-control" id="borName" name="borName" />
								</div>
							</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<div class="col-lg-8">
							    <div style = "text-align:right;height:150%">
									<button type="button" class="btn btn-info" onclick="ruizhi.loanAfterStatisticalReport.collectionLogReport.submit()"><i class="icon-search4 position-left"></i>查询</button>
									<button type="button" class="btn btn-success" onclick="ruizhi.loanAfterStatisticalReport.collectionLogReport.clearData()"><i class="icon-x position-left"></i>清除</button>
								</div>
							</div>
					    </div>
					<div>
				
				</div>
			</div>
		</div>
	</form>
	<#include './report-button.ftl'/>
	<div>
		<table id="lendingStatistical-table"></table>
	</div>
<!-- 引入该页面js start -->
<#include './report-table.ftl'/>
<script type="text/javascript" src="${webRoot}/template/loanStatisticsReport/js/lendingStatistical.js"></script>
<!-- 引入该页面js end -->

<#include "../includes/page-foot.ftl">
</body>


</html>