<div class="navbar zsXq-bg navbar-fixed-top">
	<div class="navbar-header">
		<ul class="nav navbar-nav" style="float: left;">
			<li id="showFirstNav-btn"><a class=""><i class="icon-paragraph-justify3"></i></a></li>
		</ul>
		<a class="navbar-brand" onclick="ruizhi.OpenPage('${webRoot}/main.do')"><img src="${staticRoot}/limitless/assets/images/logo@2x.png" alt="" style="margin-left: 0;"></a>

		<ul class="nav navbar-nav visible-xs-block">
			<li><a data-toggle="collapse" data-target="#navbar-mobile"><i class="icon-tree5"></i></a></li>
			<li><a class="sidebar-mobile-main-toggle"><i class="icon-paragraph-justify3"></i></a></li>
		</ul>
	</div>

	<div class="navbar-collapse collapse" id="navbar-mobile">

		
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown dropdown-user">
				<a class="dropdown-toggle" data-toggle="dropdown">
					<span id="includes-menu_navbar-displayUserName"></span>
				</a>
			</li>
			<li class="dropdown dropdown-user">
				<a class="dropdown-toggle" data-toggle="dropdown">
					<img src="${staticRoot}/limitless/assets/images/demo/users/face11.jpg" alt="">
					<span id="menu_userName">个人设置</span>
					<i class="caret"></i>
				</a>

				<ul class="dropdown-menu dropdown-menu-right">
					<!-- 
						<li><a href="#"><i class="icon-user-plus"></i> My profile</a></li>
						<li><a href="#"><i class="icon-coins"></i> My balance</a></li>
						<li><a href="#"><span class="badge bg-teal-400 pull-right">58</span> <i class="icon-comment-discussion"></i> Messages</a></li>
						<li class="divider"></li>
						<li><a href="#"><i class="icon-cog5"></i> Account settings</a></li>
					-->
					<li><a href="${webRoot}/logout.do"><i class="icon-switch2"></i> Logout</a></li>
					<li id="companysTab"><a href="#"><i class="icon-user-plus"></i> 切换公司</a></li>
				</ul>
			</li>

		</ul>
	</div>
</div>
