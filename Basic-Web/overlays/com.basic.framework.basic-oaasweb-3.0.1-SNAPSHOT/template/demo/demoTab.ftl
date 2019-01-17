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

<input type="button" onclick="ruizhi.demo.demoTab.getActiveTab()" value="获取当前标签页"/>	
<input type="button" onclick="ruizhi.demo.demoTab.getPreviousTab()" value="获取上一个标签页"/>
<input type="button" onclick="ruizhi.demo.demoTab.setTabDisplay()" value="设置显示的标签页"/>
<input type="button" onclick="ruizhi.demo.demoTab.setHide('ios1')" value="隐藏第二个tab的ios"/>
<input type="button" onclick="ruizhi.demo.demoTab.setShow('ios1')" value="显示弟二个tab的ios"/>


<ul id="myTab" class="nav nav-tabs">
   <li>
      <a href="#home" data-toggle="tab" onclick="ruizhi.demo.demoTab.home()">
         W3Cschool Home
      </a>
   </li>
   
   <li>
      <a href="#ios" data-toggle="tab">
      	 iOS
      </a>
   </li>
   
   <li class="dropdown">
      <a href="#" id="myTabDrop1" class="dropdown-toggle" 
         data-toggle="dropdown">Java 
         <b class="caret"></b>
      </a>
      <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
         <li><a href="#jmeter" tabindex="-1" data-toggle="tab">jmeter</a></li>
         <li><a href="#ejb" tabindex="-1" data-toggle="tab">ejb</a></li>
      </ul>
   </li>
</ul>
<div id="myTabContent" class="tab-content">
   <div class="tab-pane fade" id="home">
      <p>W3Cschoool菜鸟教程是一个提供最新的web技术站点，本站免费提供了建站相关的技术文档，帮助广大web技术爱好者快速入门并建立自己的网站。菜鸟先飞早入行——学的不仅是技术，更是梦想。</p>
   </div>
   <div class="tab-pane fade" id="ios">
      <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple 
      TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
   </div>
   <div class="tab-pane fade" id="jmeter">
      <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
   </div>
   <div class="tab-pane fade" id="ejb">
      <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
      </p>
   </div>
</div>

<ul id="myTab1" class="nav nav-tabs">
   <li>
      <a href="#home1" data-toggle="tab" onclick="ruizhi.demo.demoTab.home()">
         W3Cschool Home
      </a>
   </li>
   
   <li>
      <a href="#ios1" data-toggle="tab">
      	 iOS
      </a>
   </li>
   
   <li class="dropdown">
      <a href="#" id="myTabDrop1" class="dropdown-toggle" 
         data-toggle="dropdown">Java 
         <b class="caret"></b>
      </a>
      <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
         <li><a href="#jmeter1" tabindex="-1" data-toggle="tab">jmeter</a></li>
         <li><a href="#ejb1" tabindex="-1" data-toggle="tab">ejb</a></li>
      </ul>
   </li>
</ul>
<div id="myTabContent" class="tab-content">
   <div class="tab-pane fade" id="home1">
      <p>W3Cschoool菜鸟教程是一个提供最新的web技术站点，本站免费提供了建站相关的技术文档，帮助广大web技术爱好者快速入门并建立自己的网站。菜鸟先飞早入行——学的不仅是技术，更是梦想。</p>
   </div>
   <div class="tab-pane fade" id="ios1">
      <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple 
      TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
   </div>
   <div class="tab-pane fade" id="jmeter1">
      <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
   </div>
   <div class="tab-pane fade" id="ejb1">
      <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
      </p>
   </div>
</div>
<!--页面foot-->	
<#include "../includes/page-foot.ftl">
</body>

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/demo/js/demoTab.js"></script>
</html>
