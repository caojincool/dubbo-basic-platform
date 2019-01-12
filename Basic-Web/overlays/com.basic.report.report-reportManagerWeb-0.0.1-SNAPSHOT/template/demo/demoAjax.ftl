<!DOCTYPE html>
<html lang="en">

<head>

<#include "../includes/page-head.ftl">	


</head>

<body>
<#include "../includes/page-title.ftl">

<input type="button" onclick="ruizhi.demo.demoAjax.loadDataFalse()" value="同步请求后台"/>	

<input type="button" onclick="ruizhi.demo.demoAjax.loadDataTrue()" value="异步请求后台"/>	

<input type="button" onclick="ruizhi.demo.demoAjax.loadDataTrue1()" value="异步请求后台（有回调方法）"/>	
	
<#include "../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/demo/js/demoAjax.js"></script>

</html>
