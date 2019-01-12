<!DOCTYPE html>
<html lang="en">

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />

<!-- 不缓存，测试阶段，后面上线可去掉 -->
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"> 
<META HTTP-EQUIV="Expires" CONTENT="0"> 
<!-- 不缓存，测试阶段，后面上线可去掉 -->

<head>
<#include "./includes/index-head.ftl">	
</head>

<body class="navbar-top">
    
	<!-- Main navbar -->

	<#include "./includes/menu-navbar.ftl">

	<!-- /main navbar -->

	<!-- Page container -->
	<div class="page-container">

		<!-- Page content -->
		<div class="page-content">
            
			<!-- Main sidebar -->
			<#include "./includes/index-newNav.ftl">
			<!-- /main sidebar -->
         

			<!-- Main content -->
			<div class="content-wrapper">

					<!-- Basic-->

						
						<#include "./includes/navShow/msqs-head.ftl">
										
					<!-- /basic  -->

					<!-- Footer -->
					<div class="footer text-muted">
					
					</div>
					<!-- /footer -->

			</div>
			<!-- /main content -->

		</div>
		<!-- /page content -->

	</div>
	<!-- /page container -->
	<#include "./includes/index-modal.ftl">
	<#include "./includes/modal-foot.ftl">
<link rel="stylesheet" type="text/css" href="${webRoot}/template/util/css/basic-search.css" >
<script type="text/javascript" src="${webRoot}/template/util/utils.js"></script>
<script type="text/javascript" src="${webRoot}/template/util/bootstrap-select-master/dist/js/bootstrap-select.js"></script>
<script type="text/javascript" src="${webRoot}/template/util/js/changeIcon.js"></script>
<script type="text/javascript" src="${webRoot}/template/public/formEditExt.js"></script>
<link rel="stylesheet" type="text/css" href="${webRoot}/template/util/bootstrap-select-master/dist/css/bootstrap-select.min.css">
</body>
<script>

</script>
</html>
