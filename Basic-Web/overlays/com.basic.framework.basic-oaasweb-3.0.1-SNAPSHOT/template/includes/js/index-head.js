//////////////////////////////////////////
//RuizhiSoft corp. 2017-6-28 17:59:25
//Author :zheng.zhijie
//commits:主页
//////////////////////////////////////////

//类定义
ruizhi.Package("ruizhi.includes");

ruizhi.includes.index_head = function() {
	
	//私有成员
	var _this = this;
	var session = null;
	
	
	
	return{
		/* 初始化 */
		init : function() {
			session = ruizhi.GetSession();
			
			
			/**
			 * 获取用户详细信息
			 */
			if(!ruizhi.IsNull(session)){
				var userId = session.userId;
				ruizhi.GetObj("includes-menu_navbar-displayUserName").html("您好，"+session.userName);
				var url = WEB_ROOT + "/common/user/qryUserDetail.do";
				var paramObj = {};
				paramObj.userId = userId;
				_UserDetail = null;
				var msg = ruizhi.InvokeMethod(url,paramObj);
				ruizhi.includes.index_head.userDetailComeBack(msg);
			}
			
			//初始化显示我的工作台
			var firstNavName = "我的工作台";
			var firstMenu_co = 2923985;
			var firstNavHtml = "./template/includes/navShow/myWorkBench/myWorkBench.html";
		if(firstNavHtml == undefined){
			tips('没找到');
		}else{
			try {
				if($('[id='+firstMenu_co+']').length > 0){
					// $('.newContent').remove();
					$('#'+firstMenu_co).show().siblings('.second-show').hide();
					$('#second-nav-ul li[title='+firstNavName+']').addClass('actived').siblings().removeClass('actived')
				} else {
					if ($('#second-nav-ul li span.created').length > 0) {
						var navLi = '<li class="actived " title="'+firstNavName+'"><span title="'+firstNavName+'" class="created daoHang" men_co='+firstMenu_co+'>'+firstNavName+'</span><i class="icon-cross3"></i></li>';
						$("#second-nav-ul li.actived").removeClass('actived');
						$("#second-nav-ul").append(navLi);
					} else {
						$('#second-nav-ul li').addClass('actived')
						.attr('title', firstNavName)
						.find('span').attr('men_co', firstMenu_co).attr('title', firstNavName)
						.text(firstNavName).addClass('created daoHang')
					}
					// fixTabSize();
					var $clone = $('#mainContent').clone().attr('id', 'mainContent1');
					$clone.insertAfter('#mainContent').hide();
					$("#mainContent").load(firstNavHtml, function(){
						$('.second-show').hide();
						checkBtnUse();
						$('#mainContent').attr('id', firstMenu_co).show().addClass(firstMenu_co);
						$('#mainContent1').attr('id', 'mainContent').hide();
						var script = document.createElement('script');
						script.src = './template/public/newNav/navigation.js';
						document.body.appendChild(script);
					})
					
				}
				
				// $this.find('img:last').show();
				// $this.siblings().find('img:last').hide();
				
				var secondnavName;
				if(firstNavName.indexOf('流程')>0){
					secondnavName = firstNavName+'导航';
				}else{
					secondnavName = firstNavName;
				}
				firstN = secondnavName;
				$("#navName").text(secondnavName);
				$('.navigation-first-link').removeClass('show-fist-link').parent(".showFirstnav-body").hide();
				$('#showFirstNav-btn').show();
				//getHtml(firstNavHtml);

			} catch (e) {
				console.error(e)
			}
		}	
		},
		
		userDetailComeBack : function(obj) {
			debugger;
			if(!ruizhi.IsNull(obj)){
				_UserDetail = obj;
			   $("#menu_userName").text(_UserDetail.staffName);
				
			　　   var companys = _UserDetail.companys;
			    var company = _UserDetail.company;
			    $("#companysTab ul").remove();
			    
			    $("#companysTab a").html("<i class='icon-user-plus'></i>"+company.chineseName);
			    if(ruizhi.IsNull(companys)){
			    	return;
			    }
			    
			 	$("#companysTab").append("<ul id='companyTab'></ul>");
			 	for(var i=0;i<companys.length;i++){
			 		if(companys[i]!= null){
			 			$("#companyTab").append("<li><a href='javascript:void(0)' onclick='ruizhi.includes.index_head.switchCompany("+companys[i].companyId+")'>"+companys[i].chineseName+"</a></li>");
			 		}
			 		
			 	}
			 	$("#companysTab").hover(function(){
			 		$("#companyTab").show();
			 	},function(){
			 		$("#companyTab").hide();
			 	});
			 	
			}else{
				ruizhi.Alert("获取用户详细信息失败！");
			}
		},
		switchOrg :function (){
			var paramObj = {};//传给模态窗口的参数
			var url = WEB_ROOT + '/common/funcPage/user/switchOrg.do';
			var width = null;
			var heigth = null;
			var submitFn = ruizhi.includes.index_head.switchOrgComeBack;//模态窗口提交后回调方法

			var modalClass = "modal-sm";
			ruizhi.ShowModalWin(url,width,heigth,paramObj,submitFn,modalClass);
		},
		switchOrgComeBack : function(param){
			
			if(!ruizhi.IsNull(param.orgIndex)){
				
				var orgObj = _UserDetail.jobOrgAreas[param.orgIndex];
				
				_UserDetail.areaCode = orgObj.areaCode;
				_UserDetail.areaCodePath = orgObj.areaCodePath
				_UserDetail.areaId = orgObj.areaId
				_UserDetail.areaName = orgObj.areaName
				_UserDetail.areaNamePath = orgObj.areaNamePath
				_UserDetail.orgCode = orgObj.orgCode
				_UserDetail.orgCodePath = orgObj.orgCodePath
				_UserDetail.orgId = orgObj.orgId
				_UserDetail.orgName = orgObj.orgName
				_UserDetail.orgNamePath = orgObj.orgNamePath
				_UserDetail.jobCode = orgObj.jobCode
				_UserDetail.jobId = orgObj.jobId
				_UserDetail.jobName = orgObj.jobName
				
				ruizhi.Alert("操作成功!")
			}
			//ruizhi.InvokeMethodAsyn(url,params,ruizhi.oaas.user.loadData);
		},
		switchCompany:function(companyId){
			var msg = ruizhi.InvokeMethod(WEB_ROOT+"/common/switchCompany.do",{
				"companyId":companyId,
				"userId":_UserDetail.userId
			});
			 ruizhi.includes.index_head.userDetailComeBack(msg);
		},

	}
}();

// 初始化
ruizhi.ExecWait(function(){
	ruizhi.includes.index_head.init();
	$.support.cors = true;
});
//获取FirstNav路径
function getBashUrl(menucode){
    var baseWay;
    $.each(BASE_PATH,function(index,obj){
        if(obj.menucode == menucode){
            baseWay = obj.url;
        }
    });
    return baseWay;
}
//获取secondNav路径
function getHtmlUrl(menucode){
    var url;
    $.each(HtmlName,function(index,obj){
        if(obj.menucode == menucode){
            url = obj.url
        }
    });
    return url;
}
//判断按钮，修改颜色
function checkBtnUse(){
    var btnArray = new Array();
    $.each(HtmlName,function(index,obj){
        btnArray.push(obj.menucode);
    });
    $("#mainContent").find(".clickthis").each(function(){
        var thisText = $(this).find('p').attr('men_co');
        if(btnArray.indexOf(thisText)>=0){
            $(this).find(".nav-ms-img").css('background','#0078FF');
            $(this).find(".nav-ms-p").css('color','black');
        }else{
            $(this).find(".nav-ms-img").css('background','#7e7e7e');
            $(this).find(".nav-ms-p").css('color','#7e7e7e');
            // 前一个td
            if(!$(this).parents('td').prev('td').find('div').hasClass('clickthis')){
                $(this).parents('td').prev('td').find('img').css('background','#7e7e7e');
            }
            // 后一个td
            if(!$(this).parents('td').next('td').find('div').hasClass('clickthis')){
                $(this).parents('td').next('td').find('img').css('background','#7e7e7e');
            }
            // 上一个td
            if(!$(this).parents('tr').prev('tr').find('td:eq('+$(this).parents('td').index()+')').find('div').hasClass('clickthis')){
                $(this).parents('tr').prev('tr').find('td:eq('+$(this).parents('td').index()+')').find('img').css('background','#7e7e7e');
            }
            //下一个td
            if(!$(this).parents('tr').next('tr').find('td:eq('+$(this).parents('td').index()+')').find('div').hasClass('clickthis')){
                $(this).parents('tr').next('tr').find('td:eq('+$(this).parents('td').index()+')').find('img').css('background','#7e7e7e');
            }
        }
    });
}
// menuCode
//获取页面渲染
function getHtml(url){
    if(url == ""){
        return;
    }else{
        ajaxHtml.get(url).then(function(res){
            $("#mainContent").html(res);
            checkBtnUse();
        });
    }

}

//第一次进入
function firstShow(firstNavName) {
    $("#second-nav-ul").find("li:first").find('span').attr('title',firstNavName);
    var firstNavHtml = getBashUrl(firstNavName);
    // ruizhi.OpenPage(firstNavHtml);
    getHtml(firstNavHtml);
}
//firstShow("我的工作台");
//提示框
function tips(ms){
    $("#tips").text(ms);
    $(".tips-body").fadeIn(100);
}

//修改tab大小
function fixTabSize () {
    var $tabs = $('#second-nav-ul li'),
        num = $tabs.length,
        maxW = $('#second-nav-ul').width()
    w = 0,
        $tabs.each(function () {
            w += $(this).width()+50
        })
    w = w - 25
    if (w >= maxW) {
        $('#second-nav-ul li').width($('#second-nav-ul').width()/num-30)
    } else {
        $('#second-nav-ul li').width(130)
    }
}