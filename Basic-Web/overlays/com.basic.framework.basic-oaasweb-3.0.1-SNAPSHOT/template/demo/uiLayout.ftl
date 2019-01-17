<!DOCTYPE html>
<html lang="en">

<head>

<#include "../includes/page-head.ftl">	

</head>

<#include "../includes/page-title.ftl">
<body>
<!--不分页查询-->







<div class="easyui-layout-outdiv">

<div id="demo-uiLayout-layout" class="easyui-layout" data-options="fit:true" style="height:650px;">
    <div data-options="region:'north',title:'查询条件',split:true,border:false" style="height:153px;">
    </div>
    <div data-options="region:'south',title:'South Title',split:true,border:false" style="height:100px;">
    </div>
    <div data-options="region:'east',title:'East',split:true,border:false" style="width:100px;">
    </div>
    <div data-options="region:'west',title:'West',split:true,border:false" style="width:100px;">
    </div>
    <div data-options="region:'center',title:'查询结果'" style="padding:5px;">
    </div>
</div>



</div>



<#include "../includes/page-foot.ftl">
</body>
<!-- 引入该页面js start -->
<script type="text/javascript" src="${webRoot}/template/demo/js/uiLayout.js"></script>
<!-- 引入该页面js end -->

</html>