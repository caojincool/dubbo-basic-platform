<!DOCTYPE html>
<html lang="en">

<head>

<#include "../includes/page-head.ftl">	


</head>

<body>
<#include "../includes/page-title.ftl">
<!--不分页查询-->


<div>
   <table 
   id="treeDemo" 
   class="ztree"
   checked="true"
   
   ></table>
</div>


<input type="button" onclick="ruizhi.demo.demoZTree.add()" value="新增"/>	
<input type="button" onclick="ruizhi.demo.demoZTree.onCheck()" value="获取当前选择复选框节点"/>	
<input type="button" onclick="ruizhi.demo.demoZTree.onSelect()" value="获取当前选择节点"/>	
<input type="button" onclick="ruizhi.demo.demoZTree.remove()" value="删除"/>	
<input type="button" onclick="ruizhi.demo.demoZTree.loadData2()" value="加载数据(一次性加载)"/>	
<input type="button" onclick="ruizhi.demo.demoZTree.loaddate()" value="加载数据(一次性加载)"/>	

<#include "../includes/page-foot.ftl">
</body>
<script type="text/javascript" src="${webRoot}/template/demo/js/demoZTree.js"></script>


</html>
