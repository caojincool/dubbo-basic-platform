<!DOCTYPE html>
<html lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />

	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
		#l-map{height:300px;width:100%;}
		#r-result{width:100%;}
	</style>
	
<head>
<!--页面head-->

  <title>添加定位相关控件</title>
<#include "../includes/page-head.ftl">
</head>
<body>
<!--页面title-->
<#include "../includes/page-title.ftl">

	<div id="r-result">请输入:<input type="text" id="suggestId" size="20" value="百度" style="width:150px;" /></div>
	<div id="l-map"></div>

<button class='btn btn-primary' onclick="ruizhi.demo.demoMap.xx();">坐标和位置 <buttona>
<!--页面foot-->	
<#include "../includes/page-foot.ftl">
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/demo/js/demoMap.js"></script>
</html>