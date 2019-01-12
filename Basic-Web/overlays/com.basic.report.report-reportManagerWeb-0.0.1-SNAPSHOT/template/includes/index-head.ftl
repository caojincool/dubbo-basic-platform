	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>${systemName!"RuiZhi"}</title>

	<!-- Global stylesheets -->
	<link rel="stylesheet" type="text/css" href="${staticRoot}/limitless/assets/css/fonts.googleapis.com.css" >
	<link rel="stylesheet" type="text/css" href="${staticRoot}/limitless/assets/css/icons/icomoon/styles.css">
	<link rel="stylesheet" type="text/css" href="${staticRoot}/limitless/assets/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${staticRoot}/limitless/assets/css/core.css">
	<link rel="stylesheet" type="text/css" href="${staticRoot}/limitless/assets/css/components.css">
	<link rel="stylesheet" type="text/css" href="${staticRoot}/limitless/assets/css/colors.css">
	<!--jqGrid-->
	<link rel="stylesheet" type="text/css" href="${staticRoot}/jqGrid/css/jquery-ui.css">
	
	<link rel="stylesheet" type="text/css" href="${staticRoot}/limitless/assets/css/countDiv.css">
	
	<link rel="stylesheet" type="text/css" href="${staticRoot}/jqGrid/css/ui.jqgrid.css">
	<!--
	<link rel="stylesheet" type="text/css" href="${staticRoot}/jqGrid/css/ui.jqgrid-bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${staticRoot}/jqGrid/css/ui.jqgrid-bootstrap-ui.css">
	-->
	<!-- 附件上传下载 -->
	<link rel="stylesheet" type="text/css" href="${staticRoot}/limitless/assets/js/plugins/uploaders/css/fileinput.min.css">
	
	<!-- /global stylesheets -->
	
    <link rel="stylesheet" type="text/css" href="${staticRoot}/limitless/assets/css/navigation.css">
	<!-- Core JS files  -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/core/libraries/jquery.min.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/core/libraries/bootstrap.min.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/loaders/blockui.min.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/loaders/pace.min.js"></script>
	<!-- /core JS files -->

	<!-- Theme JS files -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/visualization/d3/d3.min.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/visualization/d3/d3_tooltip.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/forms/styling/switchery.min.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/forms/styling/uniform.min.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/forms/selects/bootstrap_multiselect.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/ui/moment/moment.min.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/ui/headroom/headroom.min.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/ui/headroom/headroom_jquery.min.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/ui/nicescroll.min.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/core/app.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/pages/layout_fixed_custom.js"></script>
	
	<!--jqGrid-->
	<script type="text/javascript" src="${staticRoot}/jqGrid/src/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="${staticRoot}/jqGrid/js/i18n/grid.locale-zh_CN.js"></script>
	
	<!-- 下拉框插件 -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/forms/selects/select2.min.js"></script>
	<!-- 开关选择器 -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/forms/styling/switch.min.js"></script>	
	<!-- 弹窗提示 -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/notifications/bootbox.min.js"></script>
	<!-- 时间选择 -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/datepicker/js/bootstrap-datetimepicker.js"></script>	
		
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/core/libraries/jquery_ui/core.min.js"></script>
	<!-- 表单验证 -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/forms/validation/validate.min.js"></script>
	<!---->

	<!-- 附件上传下载 -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/uploaders/js/fileinput.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/uploaders/js/fileinput_locale_zh.js"></script>
	
	<!-- 省市区三级级联选择地址 -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/address/region_select.js"></script>
	
	<!--
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/pages/uploader_bootstrap.js"></script>
	-->
	
	<!-- 在线视频播放 -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/flowplayer/flowplayer-3.2.11.min.js" ></script>
	<!--  -->
	
	<!-- 打印 -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/jqprint/jquery.jqprint-0.3.js" ></script>
	<!--  -->
	
	<!-- /theme JS files -->
	<script>
		var WEB_ROOT = '${webRoot!''}';
		
		
		var _SESSION = ${mySessionUser!''};
		
		/*_SESSION.hasPriv = function(privCode){
			if(_SESSION.privates!=null && _SESSION.privates.indexOf(privCode)>-1){
				return true;
			}else{
				return false;
			}
		};*/
		
		
	</script>
	
	<script type="text/javascript" src="${webRoot}/template/public/jqueryExt.js"></script>
	<script type="text/javascript" src="${webRoot}/template/public/helper.js"></script>
	<script type="text/javascript" src="${webRoot}/template/public/formExt.js"></script>
	<script type="text/javascript" src="${webRoot}/template/public/dataGrid.js"></script>
	<script type="text/javascript" src="${webRoot}/template/public/baiduMap.js"></script>
	<script type="text/javascript" src="${webRoot}/template/public/tab.js"></script>
	<script type="text/javascript" src="${webRoot}/template/public/zTree.js"></script>
	
	<script src="http://api.map.baidu.com/api?v=2.0&ak=mF7uC71GhzPINhGTD7kw9yYw" type="text/javascript"></script>
	
		<!-- ztree树 -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/ztree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/ztree/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/ztree/jquery.ztree.exhide.js"></script>
	
		<!-- ztree树 -->
	<link rel="stylesheet" type="text/css" href="${staticRoot}/limitless/assets/css/zTreeStyle.css">
	
	<!-- 本页面js -->
	<script type="text/javascript" src="${webRoot}/template/includes/js/index-head.js"></script>
	<script type="text/javascript" src="${webRoot}/template/public/topSearch.js"></script>
	<link rel="stylesheet" type="text/css" href="${staticRoot}/limitless/assets/css/companys.css">
	<script type="text/javascript" src="${webRoot}/template/includes/js/myWorkBench.js"></script>
	
	
	<link rel="stylesheet" type="text/css" href="${webRoot}/template/util/bootstrap-select-master/dist/css/bootstrap-select.min.css" >
	<script type="text/javascript" src="${webRoot}/template/util/bootstrap-select-master/dist/js/bootstrap-select.js"></script>
	<script type="text/javascript" src="${webRoot}/template/util/utils.js"></script>
	<link rel="stylesheet" type="text/css" href="${webRoot}/template/util/bootstrap3-editable-1.5.1/bootstrap3-editable/css/bootstrap-editable.css" />
	<script type="text/javascript" src="${webRoot}/template/util/bootstrap3-editable-1.5.1/bootstrap3-editable/js/bootstrap-editable.js" />
	<script type="text/javascript" src="${webRoot}/template/util/js/changeIcon.js"></script>
	<link rel="stylesheet" type="text/css" href="${webRoot}/template/util/css/basic-search.css" >

	<link rel="stylesheet" href="${webRoot}/static/limitless/assets/css/zsBasicCss/style.css">

	<!-- 分页插件 -->
	<script type="text/javascript" src="${staticRoot}/limitless/assets/js/plugins/pagination/bs_pagination.min.js"></script>