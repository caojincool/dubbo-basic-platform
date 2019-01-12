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
	<form class="form-horizontal" id="loansInfoReport-form" action="#">
	</form>
	<#include './report-button.ftl'/>
	<div>
		<table id="loansInfoReport-table"></table>
	</div>
<!-- 引入该页面js start -->
<#include './report-table.ftl'/>
<script type="text/javascript" src="${webRoot}/template/loanStatisticsReport/js/loansInfoReport.js"></script>
<!-- 引入该页面js end -->

<#include "../includes/page-foot.ftl">
</body>


</html>