<!--<div class="showFirstnav-body">-->
	<div class="f-l navigation-first-link">
		<#--<div class="navigation-first-link-title" id="showNav"><p>相关流程</p></div>-->
		<ul class="navigation-first-link-ul" id="ulNav">
			<!-- Main -->

			<#-- 遍历menuTreeList构造左侧树菜单 -->
			<#if menuTreeList?? && menuTreeList?size gt 0>
			<#list menuTreeList as menu>

			<li>
				<img src="${menu.imgUrl !webRoot+'/template/img/FirstNav/massage.png'}" ><span men_co="${menu.catalogId}">${menu.catalogName}</span><img src="${webRoot}/template/img/arrow-right.png" alt="" class="arrow-r">
				<div style="display: none" class="firstNav">${menu.catalogUrl !''}</div>
<!-- 						<a href="#">
							${menu.catalogName}
						</a> -->
						<@subNav subObj = menu.childObj />
					</li>
					<#--<#list menu?keys as key>
					<#if menu["children"]?? && menu["children"]?size gt 0>
					<#list menu["children"] as children>
					<@subNav subs = menu.children/>
					</#list>
					</#if>
					</#list>-->

					</#list>
					</#if>

					<!-- /main -->


<!-- 
			<li><img src="${webRoot}/template/img/FirstNav/buy.png" alt="buy.png">我的工作台<img src="${webRoot}/template/img/arrow-right.png" alt="" style="display: block;"></li>
				<li><img src="${webRoot}/template/img/FirstNav/buy.png" alt="buy.png">采购管理流程<img src="${webRoot}/template/img/arrow-right.png" alt=""></li>
				<li><img src="${webRoot}/template/img/FirstNav/sale.png" alt="sale.png">销售管理流程<img src="${webRoot}/template/img/arrow-right.png" alt=""></li>
				<li><img src="${webRoot}/template/img/FirstNav/money.png" alt="money.png">资金管理流程<img src="${webRoot}/template/img/arrow-right.png" alt=""></li>
				<li><img src="${webRoot}/template/img/FirstNav/stock.png" alt="stock.png">库存管理流程<img src="${webRoot}/template/img/arrow-right.png" alt=""></li>
				<li><img src="${webRoot}/template/img/FirstNav/bang.png" alt="bang.png">磅差管理流程<img src="${webRoot}/template/img/arrow-right.png" alt=""></li>
				<li><img src="${webRoot}/template/img/FirstNav/massage.png" alt="massage.png">基础信息流程<img src="${webRoot}/template/img/arrow-right.png" alt=""></li> -->



			</ul>
		</div>
<!--	</div>-->
<#-- 自定义递归函数 -->
<#macro subNav subObj>
<#if subObj ?? && subObj?size gt 0 >
<ul style="display: none" class="search-my-power">
	<#list subObj as obj>

	<#if obj.catalogName ?? >
	<li>
		<a href="#">
			${obj.catalogName}
		</a>
		<#if obj.childObj ??>
		<@subNav subObj = obj.childObj />
	</#if>
	
	<#elseif obj.menuName ??> 
		<li >
			<div>${obj.menuUrl}</div>
			<div>${obj.menuName}</div>
			<div>${obj.menuCode}</div>
			</#if>
			
		</li>
	</#list>

		
	</ul>
	</#if>
	</#macro>