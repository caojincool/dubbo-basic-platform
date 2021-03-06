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
	<form class="form-horizontal" id="loansLimitReport-form" action="#">
		<div class="panel panel-flat">
			<!--标题 结束-->
			<div class="panel-body">
				<div class="row">	
					 
						<div class="col-md-2">
								<div class="form-group">
									<label class="col-lg-5 control-label text-right">项目数：</label>
									<div class="col-lg-7">
										 <input type="text" class="form-control" id="countProject"  name="countProject" />
										 <input type="hidden" id="fileName" name="fileName" class="form-control"/>
									</div>
								</div>
						</div>
						<div class="col-md-3">
								<div class="form-group">
									<label class="col-lg-5 control-label text-right">贷款金额起始区间(万元)：</label>
									<div class="col-lg-7">
										 <input type="text" class="form-control" id="startMoney" name="startMoney" />
									</div>
								</div>
						</div>
						<div class="col-md-3">
								<div class="form-group">
									<label class="col-lg-5 control-label text-right">贷款金额截止区间(万元)：</label>
									<div class="col-lg-7">
										 <input type="text" class="form-control" id="endMoney" name="endtMoney" />
									</div>
								</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<div class="col-lg-8">
								    <div style = "text-align:right;height:150%">
										<button type="button" class="btn btn-info" onclick="ruizhi.loanAfterStatisticalReport.loansLimitReport.submit()"><i class="icon-search4 position-left"></i>查询</button>
										<button type="button" class="btn btn-success" onclick="ruizhi.loanAfterStatisticalReport.loansLimitReport.clearData()"><i class="icon-x position-left"></i>清除</button>
									</div>
								</div>
						    </div>
						<div>
				
				</div>
			</div>
		</div>
	</form>	
	<!-- 按钮 -->
	<#include './report-button.ftl'/>
	<div>
		<table id="loansLimitReport-table"></table>
	</div>
<!-- 引入该页面js start -->
<#include './report-table.ftl'/>
<script type="text/javascript" src="${webRoot}/template/loanAfterStatisticalReport/js/loansLimitReport.js"></script>
<!-- 引入该页面js end -->

<#include "../includes/page-foot.ftl">
</body>


</html>