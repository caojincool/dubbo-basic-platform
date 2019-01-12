var rclass = /[\t\r\n\f]/g;
$.fn.extend({
    hasClass: function(selector) {//检查是否有这个类
      var arr = selector.split(','),
      l = this.length,
      len = arr.length;
      for (var n = 0; n < len; n++) {
        for (var i = 0; i < l; i++) {
          if (this[i].nodeType === 1 && (" " + this[i].className + " ").replace(rclass, " ").indexOf(arr[n]) >= 0) {
            return true;
          }
        }
      }
      return false;
    }
  });



$(function () {
	//设置操作界面高度
	var wH = $(window).height(),
		ulH = $('.second-nav').outerHeight(true),
		navH = $('.zsXq-bg').height()
	var secondshowH = wH - ulH - navH - 10,
		sideBarH = wH - navH - 10;
	$(".second-show").css('height',secondshowH+'px');
	$('.navigation-first-link').height(sideBarH);
    $('.navigation-first-link').niceScroll({
        cursorcolor: "#ccc",//#CC0071 光标颜色
        cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0
        touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备
        cursorwidth: "5px", //像素光标的宽度
        cursorborder: "0", // 游标边框css定义
        cursorborderradius: "5px",//以像素为光标边界半径
        autohidemode: true //是否隐藏滚动条
    });
    $('body').on('change', ':input', function () {
		$(this).attr('title', $(this).val())
    });
	
	//导航页面点击
	// debugger;
	$("body").on('click',".clickthis",function(){
		var $this = $(this),
			navName = $this.find('p').text(),
			men_co = $this.find('p').attr('men_co'),
			NavHtml = getHtmlUrl(men_co),
			isCreated = false;
		
		$("#second-nav-ul").find('li').each(function(){
			if($(this).attr('title') == navName){
			  isCreated = true;
			}
		});
		// if($this.hasClass('isCreated')) {
			// isCreated = true;
		// }
		if(!isCreated) { 
			$this.addClass('isCreated')
			var navLi = '<li class="hidden actived" title="'+navName+'"><span class="created" title="'+navName+'" men_co='+men_co+'>'+navName+	'</span><i class="icon-cross3"></i></li>';
			$("#second-nav-ul li.actived").removeClass('actived');
			$("#second-nav-ul").append(navLi);
			
			fixTabSize();

			if(NavHtml == undefined){
				$("#second-nav-ul").find('li:last').remove();
				// tips("暂没开发完成！");
			}else{
				$("#second-nav-ul").find('li:last').removeClass('hidden');
				$("#navName").text(navName);
				// getHtml(NavHtml);
				var $clone = $('#mainContent').clone().attr('id', 'mainContent1');
				$clone.insertAfter('#mainContent').hide();
				ruizhi.OpenPageLoad(NavHtml, function(){
					$('.second-show').hide().siblings('#mainContent').show();
					window.openpageItl = setInterval(function(){
						if($('.loadingBox').length === 0 && $('#mainContent')[0].children.length > 0){
							$('#mainContent').attr('id', NavHtml).addClass('newContent').show();
							$('#mainContent1').attr('id', 'mainContent')
							clearInterval(window.openpageItl)
						}
					}, 1000);
				});
				
			}
		} else {
			$("#navName").text(navName);
			var mainObj = document.getElementById(NavHtml);
			$(mainObj).show().siblings('.second-show').hide();
			$('#second-nav-ul li[title="'+navName+'"]').addClass('actived').siblings().removeClass('actived')
		}

	});
	
	var firstN;
	//标签栏点击
	// debugger;
	$("#second-nav-ul").on('click','span',function(){
		$(this).parent('li').addClass('actived').siblings().removeClass('actived');
		var navName = $(this).attr('title');
		var men_co = $(this).attr('men_co');
		var NavHtml = getHtmlUrl(men_co);
		if(NavHtml == undefined){
			// tips("暂没开发完成！");
		}else{
			try {
				$("#navName").text(navName);
				if($(this).hasClass('daoHang')){
					var mainObj = document.getElementById(men_co);
					$('#'+men_co).show().siblings('.second-show').hide();
				} else {
					var mainObj = document.getElementById(NavHtml);
					$(mainObj).show().siblings('.second-show').hide();
					//ruizhi.OpenPage(NavHtml);
				}
			} catch (e) {
				console.error(e)
			}
		}
	});
	
	//显示一级导航
	$("#showFirstNav-btn").on('click',function(){
	  // $(this).hide();
	  // $(".showFirstnav-body").fadeIn(100).find('.navigation-first-link').addClass('show-fist-link');
	  
	  $('.navigation-first-link').toggleClass('nav-hide');
	  $('.content-wrapper').toggleClass('content-wrapper-fs')
	});

	$(".showFirstnav-body").on('click',function(event){
	  if(!$(event.target).closest(".navigation-first-link").length){
		if(event.button == 0){
		  $('.navigation-first-link').removeClass('show-fist-link').parent(".showFirstnav-body").fadeOut(100);
		  $('#showFirstNav-btn').show();
		}
	  }
	});
	$("#ulNav").find('li').off("click");
	//一级导航栏点击
	$("#ulNav").find('li').on('click',function(){
		// debugger;
		var $this = $(this),
			firstNavName = $this.find('span').text(),
			firstMenu_co = $this.find('span').attr('men_co'),
			firstNavHtml = getBashUrl(firstMenu_co);
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
					fixTabSize();
					var $clone = $('#mainContent').clone().attr('id', 'mainContent1');
					$clone.insertAfter('#mainContent').hide();
					$("#mainContent").load(firstNavHtml, function(){
						$('.second-show').hide();
						checkBtnUse();
						$('#mainContent').attr('id', firstMenu_co).show().addClass(firstMenu_co);
						$('#mainContent1').attr('id', 'mainContent').hide();			
					})
					
				}
				
				$this.find('img:last').show();
				$this.siblings().find('img:last').hide();
				$this.addClass('actived').siblings().removeClass('actived');
				
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
	});
	
	//关闭标签页
	$("#second-nav-ul").on('click','i',function(){
		var $this = $(this),
			$parentLi = $this.parent(),
			parent_li_index = $parentLi.index('#second-nav-ul li')
		if ($("#second-nav-ul li").length > 1) {
			try {
				var navName = $parentLi.text();
				var men_co = $parentLi.find('span').attr('men_co');
				var NavHtml = getHtmlUrl(men_co);
				
				var new_NavHtml = '', $nextShowTab, new_men_co = '';
				
				if(parent_li_index >= 1){
					$nextShowTab = $('#second-nav-ul li').eq(parent_li_index - 1)
					new_men_co = $nextShowTab.find('span').attr('men_co');
				} else {
					$nextShowTab = $('#second-nav-ul li').eq(parent_li_index + 1)
					new_men_co = $nextShowTab.find('span').attr('men_co');
				}
				
				$parentLi.remove();
				// var firstNavName = $("#second-nav-ul").find('li:first').find('span').attr('title');
				// var firstNavName_men_co =  $("#second-nav-ul").find('li:first').find('span').attr('men_co');
				if($('#'+men_co+'.second-show')) $('#'+men_co+'.second-show').remove();
				$(document.getElementById(NavHtml)).remove();
				fixTabSize()	
				
				if ($parentLi.hasClass('actived')) {

					$nextShowTab.addClass('actived')
					new_NavHtml = getHtmlUrl(new_men_co)
					if($('#'+new_men_co+'.second-show')) $('#'+new_men_co+'.second-show').show();
					$(document.getElementById(new_NavHtml)).show()
					
				}
			} catch (e) {
				console.error(e)
			}
		} 
	});
	
	//关闭提示框
	$(".tips-body").on('click',function(event){
	  if(!$(event.target).closest(".tips-main").length){
		if(event.button == 0){
		  $(".tips-body").fadeOut(100);
		}
	  }
	});
	$(".tips-top").on('click','img',function(){
	 $(".tips-body").fadeOut(100);
	});

})






// //关闭标签页
// $("#mainContent").on('click','#closepage',function(){
//   var navName = $("#navName").text();
//   $("#second-nav-ul").find('li').each(function(){
//     if($(this).text() == navName){
//       $(this).remove();
//     }
//   });

//   var firstNavName =  $("#second-nav-ul").find('li:first').find('span').attr('title');
//   $("#navName").text(firstNavName+"导航");
// //  var firstNavHtml;
// //  getBashUrl(firstNavName);
// var firstNavHtml = getBashUrl(firstNavName);
// getHtml(firstNavHtml);
// // ruizhi.OpenPage(firstNavHtml);
// })





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

