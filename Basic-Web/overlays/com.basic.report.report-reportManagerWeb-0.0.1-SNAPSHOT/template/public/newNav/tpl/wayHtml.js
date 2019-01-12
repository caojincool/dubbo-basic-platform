var BASE_PATH = [
// {firstNavName:'采购管理流程',url:'./template/includes/navShow/purchasing-man/purchasing-nav.html'},
// {firstNavName:'销售管理流程',url:'./template/includes/navShow/sales-man/sales-nav.html'},
// {firstNavName:'资金管理流程',url:'./template/includes/navShow/capital-man/capital-nav.html'},
// {firstNavName:'库存管理流程',url:'./template/includes/navShow/inventory-man/inventory-nav.html'},
// {firstNavName:'磅差管理流程',url:'./template/includes/navShow/Pounds-man/Pounds-nav.html'},
// {firstNavName:'基础信息流程',url:'./template/includes/navShow/basicdata-man/basicdata-nav.html'},
// {firstNavName:'我的工作台',url:'./template/includes/navShow/myWorkBench/myWorkBench.html'},
];

var HtmlName = [
// {secondNavName:'采购管理流程',url:'./template/includes/navShow/purchasing-man/purchasing-nav.html'},
// {secondNavName:'销售管理流程',url:'./template/includes/navShow/sales-man/sales-nav.html'},
// {secondNavName:'资金管理流程',url:'./template/includes/navShow/capital-man/capital-nav.html'},
// {secondNavName:'库存管理流程',url:'./template/includes/navShow/inventory-man/inventory-nav.html'},
// {secondNavName:'磅差管理流程',url:'./template/includes/navShow/Pounds-man/Pounds-nav.html'},
// {secondNavName:'基础信息流程',url:'./template/includes/navShow/basicdata-man/basicdata-nav.html'},
// {secondNavName:'我的工作台',url:'./template/includes/navShow/myWorkBench/myWorkBench.html'},


// // 采购管理
// {secondNavName:'采购年度协议',url:'demo/menuPage/demoOpenWin.do'},
// {secondNavName:'采购合同',url:'./template/includes/navShow/purchasing-man/contract_for_purchase.html'},
// {secondNavName:'应付账款查询',url:'./template/includes/navShow/purchasing-man/Accounts_payable.html'},
// {secondNavName:'采购付款单',url:'./template/includes/navShow/purchasing-man/purchasePayform.html'},
// {secondNavName:'异常合同处理单',url:'./template/includes/navShow/purchasing-man/Exception_contract_statement.html'},
// {secondNavName:'待入库清单',url:'./template/includes/navShow/purchasing-man/Inventory_pending.html'},
// {secondNavName:'入库提单',url:'./template/includes/navShow/purchasing-man/Bill_of_lading.html'},
// {secondNavName:'采购入库',url:'./template/includes/navShow/purchasing-man/Purchasing_warehousing.html'},
// {secondNavName:'异常合同统计表',url:'./template/includes/navShow/purchasing-man/Abnormal_contract_statistics.html'},
// {secondNavName:'采购折让单',url:'./template/includes/navShow/purchasing-man/Purchase_discount_sheet.html'},
// {secondNavName:'待结算采购长单点',url:'./template/includes/navShow/purchasing-man/Settlement_purchase_point.html'},
// {secondNavName:'采购长单点价结算',url:'./template/includes/navShow/purchasing-man/Purchasing_single_point_pay.html'},

]
function getFirstNav(){
	$(".firstNav").each(function(){
		var add = {};
		add.url = $(this).text();
		//add.url = $(this).next("ul.search-my-power").find('li:first-child').find("div:last").text();
		add.firstNavName = $(this).parent().find("span").text();
		add.menucode = $(this).parent().find("span").attr('men_co');
		BASE_PATH.push(add);
	})
	console.log(BASE_PATH);
}

function getmypower(){
	$.each(BASE_PATH,function(index,obj){
		var add = {};
		add.secondNavName = obj.firstNavName;
		add.url = obj.url;
		add.menucode = obj.menucode;
		HtmlName.push(add);
	})
	$(".search-my-power").find('li').each(function(){
		var add = {};
		$(this).find('div').each(function(index){
			if(index == 0){
				add.url = $(this).text();
			}
			if(index == 1){
				add.secondNavName = $(this).text();
			}
			if(index == 2){
				add.menucode = $(this).text();
			}
		})
		HtmlName.push(add);
	})
	console.log(HtmlName)
}
$("#ulNav").find("li:first-child").find(".arrow-r").css('display','block');
getFirstNav();
getmypower();
