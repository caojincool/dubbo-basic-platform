/**
 * 引用JS和CSS头文件
 */
var rootPath = getRootPath(); //项目路径

/**
 * 动态加载CSS和JS文件
 */
var dynamicLoading = {
    meta : function(){
        document.write('<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">');
    },
    css: function(path){
        if(!path || path.length === 0){
            throw new Error('argument "path" is required!');
        }
        document.write('<link rel="stylesheet" type="text/css" href="' + path + '">');
    },
    js: function(path, charset){
        if(!path || path.length === 0){
            throw new Error('argument "path" is required!');
        }
        document.write('<script charset="' + (charset ? charset : "utf-8") + '" src="' + path + '"></script>');
    }
};

/**
 * 取得项目路径
 * @author wul
 */
function getRootPath() {
    //取得当前URL
    var path = window.document.location.href;
    //取得主机地址后的目录
    var pathName = window.document.location.pathname;
    var post = path.indexOf(pathName);
    //取得主机地址
    var hostPath = path.substring(0, post);
    //取得项目名
    var name = pathName.substring(0, pathName.substr(1).indexOf("/") + 1);
    return hostPath + name + "/";
}

//动态生成meta
dynamicLoading.meta();

//动态加载项目 JS文件
//dynamicLoading.js(rootPath + "/js/common/jquery-1.9.1.min.js", "utf-8");
//dynamicLoading.js(rootPath + "/js/common/bootstrap.min.js", "utf-8");
//dynamicLoading.js(rootPath + "/js/process/center/common/baseApp.js", "utf-8");
//dynamicLoading.js(rootPath + "/js/process/center/common/bootstrap-datetimepicker.min.js", "utf-8");
//dynamicLoading.js(rootPath + "/js/process/center/common/bootstrap-datetimepicker.zh-CN.js", "utf-8");
//dynamicLoading.js(rootPath + "/js/process/center/common/jquery.dataTables.js", "utf-8");
//dynamicLoading.js(rootPath + "/js/platform/system/loadHeader.js", "utf-8");

//动态加载项目 CSS文件
//dynamicLoading.css(rootPath + "/css/common/bootstrap-3.3.5/css/bootstrap.min.css");
//dynamicLoading.css(rootPath + "/css/common/bootstrap-3.3.5/css/bootstrap-responsive.css");
//dynamicLoading.css(rootPath + "/css/workflow/css/jquery.dataTables.css");
//dynamicLoading.css(rootPath + "/css/workflow/css/newWorkflow.css");

//my css and js
//Global stylesheets
dynamicLoading.css("/msqs-web/static/limitless/assets/css/fonts.googleapis.com.css");
dynamicLoading.css("/msqs-web/static/limitless/assets/css/icons/icomoon/styles.css");
dynamicLoading.css("/msqs-web/static/limitless/assets/css/bootstrap.css");
dynamicLoading.css("/msqs-web/static/limitless/assets/css/core.css");
dynamicLoading.css("/msqs-web/static/limitless/assets/css/components.css");
dynamicLoading.css("/msqs-web/static/limitless/assets/css/colors.css");
//jqGrid
dynamicLoading.css("/msqs-web/static/jqGrid/css/jquery-ui.css");
dynamicLoading.css("/msqs-web/static/limitless/assets/css/countDiv.css");
dynamicLoading.css("/msqs-web/static/jqGrid/css/ui.jqgrid.css");
//附件上传下载
dynamicLoading.css("/msqs-web/static/limitless/assets/js/plugins/uploaders/css/fileinput.min.css");
//global stylesheets
dynamicLoading.css("/msqs-web/static/limitless/assets/css/navigation.css");
//Core JS files
dynamicLoading.js("/msqs-web/static/limitless/assets/js/core/libraries/jquery.min.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/core/libraries/bootstrap.min.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/loaders/blockui.min.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/loaders/pace.min.js", "utf-8");
//Theme JS files
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/visualization/d3/d3.min.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/visualization/d3/d3_tooltip.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/forms/styling/switchery.min.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/forms/styling/uniform.min.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/forms/selects/bootstrap_multiselect.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/ui/moment/moment.min.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/ui/headroom/headroom.min.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/ui/headroom/headroom_jquery.min.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/ui/nicescroll.min.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/pages/layout_fixed_custom.js", "utf-8");
//jqGrid
dynamicLoading.js("/msqs-web/static/jqGrid/src/jquery.jqGrid.js", "utf-8");
dynamicLoading.js("/msqs-web/static/jqGrid/js/i18n/grid.locale-zh_CN.js", "utf-8");
//下拉框插件
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/forms/selects/select2.min.js", "utf-8");
//开关选择器
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/forms/styling/switch.min.js", "utf-8");
//弹窗提示
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/notifications/bootbox.min.js", "utf-8");
//时间选择
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/datepicker/js/bootstrap-datetimepicker.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/core/libraries/jquery_ui/core.min.js", "utf-8");
//表单验证
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/forms/validation/validate.min.js", "utf-8");
//附件上传下载
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/uploaders/js/fileinput.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/uploaders/js/fileinput_locale_zh.js", "utf-8");
//省市区三级级联选择地址
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/address/region_select.js", "utf-8");
//在线视频播放
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/flowplayer/flowplayer-3.2.11.min.js", "utf-8");
//打印
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/jqprint/jquery.jqprint-0.3.js", "utf-8");
//theme JS files
//dynamicLoading.js("/msqs-web/template/public/indexSession.js", "utf-8");
//
dynamicLoading.js("/msqs-web/template/public/jqueryExt.js", "utf-8");
dynamicLoading.js("/msqs-web/template/public/helperHtml.js", "utf-8");
dynamicLoading.js("/msqs-web/template/public/formExt.js", "utf-8");
dynamicLoading.js("/msqs-web/template/public/dataGrid.js", "utf-8");
dynamicLoading.js("/msqs-web/template/public/baiduMap.js", "utf-8");
dynamicLoading.js("/msqs-web/template/public/tab.js", "utf-8");
dynamicLoading.js("/msqs-web/template/public/zTree.js", "utf-8");
dynamicLoading.js("http://api.map.baidu.com/api?v=2.0&ak=mF7uC71GhzPINhGTD7kw9yYw", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/ztree/jquery.ztree.core.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/ztree/jquery.ztree.excheck.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/ztree/jquery.ztree.exedit.js", "utf-8");
dynamicLoading.js("/msqs-web/static/limitless/assets/js/plugins/ztree/jquery.ztree.exhide.js", "utf-8");
dynamicLoading.css("/msqs-web/static/limitless/assets/css/zTreeStyle.css");

//本页面js
dynamicLoading.js("/msqs-web/template/includes/js/index-headHtml.js", "utf-8");
dynamicLoading.js("/msqs-web/template/util/utils.js", "utf-8");
dynamicLoading.js("/msqs-web/template/util/bootstrap3-editable-1.5.1/bootstrap3-editable/js/bootstrap-editable.js", "utf-8");
dynamicLoading.js("/msqs-web/template/util/js/changeIcon.js", "utf-8");
dynamicLoading.js("/msqs-web/template/public/topSearch.js", "utf-8");
dynamicLoading.css("/msqs-web/static/limitless/assets/css/companys.css");
//我的工作台
//dynamicLoading.js("/msqs-web/template/includes/js/myWorkBench.js", "utf-8");
dynamicLoading.js("/msqs-web/template/util/bootstrap-select-master/dist/js/bootstrap-select.js", "utf-8");
dynamicLoading.css("/msqs-web/template/util/bootstrap-select-master/dist/css/bootstrap-select.min.css");
dynamicLoading.css("/msqs-web/template/util/bootstrap3-editable-1.5.1/bootstrap3-editable/css/bootstrap-editable.css");
dynamicLoading.css("/msqs-web/template/util/bootstrap3-editable-1.5.1/bootstrap3-editable/js/bootstrap-editable.js");
dynamicLoading.css("/msqs-web/template/util/css/basic-search.css");



//3、loadHeader.js 
//$(function(){
//    $(".container").append('<div id="header"></div>');
//    $("#header").load(getRootPath() + "header.html");
//});