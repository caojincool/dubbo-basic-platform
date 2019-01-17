ruizhi.Package("ruizhi");

ruizhi.Tab = function(tabId) {

	// 私有成员定义;
	var _this = this;
	var activeTab = null;//当前标签页
	var previousTab = null;//上一个标签页
	// 私有方法定义
	var init = function() {
		//alert("初始化标签页切换");
/*		$('#myTab a').click(function (e) {
			  e.preventDefault()
			  $(this).tab('show')
			})*/
		$('#'+tabId+' a').on('shown.bs.tab', function (e) {
			//获取已激活的标签页的名称
			//console.info(e.target);
			//activeTab = $(e.target).text(); 
			activeTab = $(e.target); 
			//alert("当前标签页--"+activeTab);
			//获取前一个激活的标签页的名称
			//previousTab = $(e.relatedTarget).text(); 
			previousTab = $(e.relatedTarget); 
			//alert("上个标签页--"+previousTab);
		});
		//$('a[data-toggle="tab"]:eq(0)').tab('show');
		$('#'+tabId+' a:first').tab('show');
		//_this.setTabDisplay(0);
		//show.bs.tab     该事件在标签页显示时触发，但是必须在新标签页被显示之前。分别使用 event.target 和 event.relatedTarget 来定位到激活的标签页和前一个激活的标签页。
		//shown.bs.tab    该事件在标签页显示时触发，但是必须在某个标签页已经显示之后。分别使用 event.target 和 event.relatedTarget 来定位到激活的标签页和前一个激活的标签页。
	}
	
	//返回当前标签页对象
	this.getActiveTab = function(){
		return activeTab;
	}
	
	//返回上一个标签页对象
	this.getPreviousTab = function(){
		return previousTab;
	}
	
	//设置第几个标签页显示（从 0 开始索引）
	this.setTabDisplay = function(index){
		$('#'+tabId+' li:eq('+index+') a').tab('show');
		//$('a[data-toggle="tab"]:eq('+index+')').tab('show');
	}
	
	//设置某个标签页隐藏
	this.setHide = function(href){
		$('#'+tabId).find('a[href="#'+href+'"]').hide();
		$('#'+href).removeClass("active in");
	}
	
	//设置某个标签页显示
	this.setShow = function(href){
		//a标签展示
		$('#'+tabId).find('a[href="#'+href+'"]').show();
		//a标签对应的div展示
		$('#'+href).addClass("active in");
		//单击一下对应的a标签
		$('#'+tabId).find('a[href="#'+href+'"]').click();
		//_this.setTabDisplay(0);
	}
	
	init();// 初始化
};