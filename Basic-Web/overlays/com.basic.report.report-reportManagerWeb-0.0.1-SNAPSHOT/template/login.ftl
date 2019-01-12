<!DOCTYPE html>
<html lang="en">

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>${systemName!"RuiZhi"}</title>

	<!-- Global stylesheets -->
	<!--<link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">-->
	<link rel="stylesheet" type="text/css" href="${staticRoot}/limitless/assets/css/fonts.googleapis.com.css" >
	<link href="${staticRoot}/limitless/assets/css/icons/icomoon/styles.css" rel="stylesheet" type="text/css">
	<link href="${staticRoot}/limitless/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="${staticRoot}/limitless/assets/css/core.css" rel="stylesheet" type="text/css">
	<link href="${staticRoot}/limitless/assets/css/components.css" rel="stylesheet" type="text/css">
	<link href="${staticRoot}/limitless/assets/css/colors.css" rel="stylesheet" type="text/css">
	<!-- /global stylesheets -->

	<!-- Core JS files -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/loaders/pace.min.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/core/libraries/jquery.min.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/core/libraries/bootstrap.min.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/loaders/blockui.min.js"></script>
	<!-- /core JS files -->

	<!-- Theme JS files -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/forms/styling/uniform.min.js"></script>

	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/core/app.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/pages/login.js"></script>
	<!-- /theme JS files -->

	<!-- base64 -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/base64/jquery.base64.js"></script>
	<script type="text/javascript" src="${webRoot}/template/public/helper.js"></script>

	<style>
		#login {
			width: 890px;
			background: #fff;
			border-radius: 12px;
			margin: 0 auto;
			display: flex;
		}
		#login-left {
			width: 420px;
			background: blue;
			border-top-left-radius: 12px;
			border-bottom-left-radius: 12px;
			background: url(${webRoot}/template/img/login/zsbg1.png) center no-repeat;
			background-size: cover;
			position: relative;
		}
		#login-right {
			padding:70px;
			border-top-right-radius: 12px;
			border-bottom-right-radius: 12px;
		}
		.btn.btn-lg {
			background: #0078FF;
			border-color: #0078FF;
			color: #fff;
			height: 40px;
		}
		.login-options {
			margin-bottom: 36px;
			margin-top: 23px;
		}
		#logo {
			/*height: 38px;*/
			display:block;
			margin: 0 auto;
		}
		#title {
			display: inline-block;
			vertical-align: middle;
			font-weight: bold;
			font-size: 18px;
		}
		#copyRight {
			position: absolute;
			text-align: center;
			bottom: 10px;
			width: 100%;
			color: #fff;
		}
		#copyRight p {
			margin: 0;
		}
		.login-container {
			background: url(${webRoot}/template/img/login/zsbg2.png) center no-repeat;
		}
		.login-container .page-content {
			background: rgba(0,0,0, 0.5);
		}
		.span-input {
			color: #c7c7c7;
		}
		.has-feedback .form-control.input-lg {
			border:none;
			padding-left: 0;
			border-bottom: 1px solid #ccc;
			outline: none;
			border-radius: 0;
			transition: all ease .3s;
			height: 40px;
		}
		.has-feedback .form-control.input-lg:focus {
			border-color: #0078FF;
			color: #000;
			border-width: 2px;
		}
		.pos-r {
			position: relative;
		}
		#forget {
			position: absolute;
			right: 0;
			top: 32px;
			color: #666666;
		}
		.checker span {
			color: #0078FF;
			border: 1px solid #ccc;
			display: inline-block;
			text-align: center;
			position: relative;
			border-radius: 4px;
		}
		.checker span:after {
			top: 0;
			left: 0;
		}
	</style>

<body>

<!-- Main navbar -->
<!-- 	<div class="navbar navbar-inverse">
		<div class="navbar-header">
			<a class="navbar-brand" href="${webRoot}/index">${systemName!"PUX-console"}</a>
		</div>

	</div> -->
<!-- /main navbar -->


<!-- Page container -->
<div class="page-container login-container">

	<!-- Page content -->
	<div class="page-content">

		<!-- Main content -->
		<div class="content-wrapper">

			<!-- Content area -->
			<div class="content">

				<!-- Advanced login -->
				<form action="${webRoot}/login/login" method="POST" id="login">
					<div id="login-left">
						<div id="copyRight">
							<!--<p>Power by Creatson-科瑞森</p>-->
							<!--<p>Copyright &copy;2017 Richangsheng Finacial Services</p>-->
						</div>
					</div>
					<div id="login-right">

						<input type="hidden" name="encryptType" id="encryptType">
						<div class="login-form">
							<div class="text-center" style="margin-bottom: 40px;">
								<img id="logo" src="${webRoot}/static/limitless/assets/images/logo.png">
								<h4 id="title">日昌盛房贷管理系统</h4>
							</div>
							<#if Session.SPRING_SECURITY_LAST_EXCEPTION??>
							<div class="alert bg-danger">
								<button type="button" class="close" data-dismiss="alert"><span>&times;</span><span class="sr-only">Close</span></button>
								<span class="text-semibold">${Session.SPRING_SECURITY_LAST_EXCEPTION.message}</span>
							</div>
						</#if>

						<div class="form-group has-feedback has-feedback-left" style="margin-bottom: 27px;">
							<span class="span-input">Username</span>
							<input autocomplete="off" type="text" class="form-control input-lg" placeholder="请输入用户名" name="userAccount" id="username">
						</div>

						<div class="form-group has-feedback has-feedback-left pos-r">
							<span class="span-input">Password</span>
							<input autocomplete="off" type="password" class="form-control input-lg" placeholder="请输入密码" name="userPassword" id="password" autocomplete="off" >
							<a id="forget" href="#" onClick="alert('请联系超级管理员!')">忘记密码？</a>
						</div>

						<div class="form-group login-options" style="margin-top: 0;">
							<div class="row">
								<div class="col-sm-6">
									<label class="checkbox-inline" style="color:#666666">
										<input type="checkbox" class="styled" name="remember-me" id="remember">
										记住密码
									</label>
								</div>
							</div>
						</div>

						<div class="form-group">
							<button type="submit" onclick="submitForm();" class="btn btn-block btn-lg">登录 </button>
						</div>

					</div>
			</div>
			</form>
			<!-- /advanced login -->


			<!-- Footer -->
			<!-- 					<div class="footer text-muted">
                                    &copy; 2015. <a href="#">外勤管理系统</a> by <a href="#" target="_blank">睿治科技</a>
                                </div> -->
			<!-- /footer -->

		</div>
		<!-- /content area -->

	</div>
	<!-- /main content -->

</div>
<!-- /page content -->

</div>
<!-- /page container -->

<!--页面脚本-->
<script type="text/javascript" src="${webRoot}/template/login.js"></script>
<script>
    var _scale = window.innerWidth / 1366;
    $('#login').css('transform', 'scale('+_scale+')');
    if (_scale > 1) $('input').css('font-size', '12px')
</script>
</body>

</html>
