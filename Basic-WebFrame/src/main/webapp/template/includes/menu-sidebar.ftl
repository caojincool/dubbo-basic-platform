<div class="sidebar sidebar-main sidebar-fixed">
	<div class="sidebar-content">

		<!-- Main navigation -->
		<div class="sidebar-category sidebar-category-visible">
			<div class="category-content no-padding">
				<ul class="navigation navigation-main navigation-accordion">

					<!-- Main -->

								  <#-- 遍历menuTreeList构造左侧树菜单 -->
						          <#if menuTreeList?? && menuTreeList?size gt 0>
						          	<#list menuTreeList as menu>
						          	
				<li>
					<a href="#">
						${menu.catalogName}
					</a>
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

				</ul>
			</div>
		</div>
		<!-- /main navigation -->

	</div>
</div>

<#-- 自定义递归函数 -->
<#macro subNav subObj>
	<#if subObj ?? && subObj?size gt 0 >
		<ul>
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
				<li>
					<a onclick="ruizhi.OpenPage('${obj.menuUrl}')">
						${obj.menuName}

					</a>
			</#if>
			
		        </li>
        </#list>

		
        </ul>
    </#if>
</#macro>