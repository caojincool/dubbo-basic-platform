//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

//类定义
//////////////////////////////////////////
ruizhi.Package("ruizhi.demo");

ruizhi.demo.demoMap = function() {

	// 私有成员定义;
	var _this = this;
	var map = null;
	// 私有方法定义

	return {

		init : function() {

			map = new ruizhi.BaiduMap("filed1",'l-map','suggestId');
			map.webGeolocation();
			map.clickEventListener();
			map.geolocationControl();
			map.autoSearch();
			
		},
		
		xx : function (){
			alert(map.returnPoint());
			alert(map.returnAddress());
			
		}

	}

}();

// 初始化
// ////////////////////////////////////////

ruizhi.ExecWait(function() {
	ruizhi.demo.demoMap.init();
});
/*

// 百度地图API功能

var map = new BMap.Map("l-map");
var marker;
map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。

map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
缩放控件type有四种类型:
BMAP_NAVIGATION_CONTROL_SMALL：仅包含平移和缩放按钮；
BMAP_NAVIGATION_CONTROL_PAN:仅包含平移按钮；
BMAP_NAVIGATION_CONTROL_ZOOM：仅包含缩放按钮

map.addControl(top_left_control);        
map.addControl(top_left_navigation);     

//浏览器自动定位
function webGeolocation(map){
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var marker = getMarker(r.point);
			//alert(JSON.stringify(r));
			map.centerAndZoom(r.point, 15);
			map.addOverlay(marker); // 将标注添加到地图中
			map.panTo(r.point);
			//alert('您的位置：'+r.point.lng+','+r.point.lat);
			var address = r.address.province +  r.address.city +  r.address.district +  r.address.street;
			//alert(address);
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
//关于状态码
//BMAP_STATUS_SUCCESS	检索成功。对应数值“0”。
//BMAP_STATUS_CITY_LIST	城市列表。对应数值“1”。
//BMAP_STATUS_UNKNOWN_LOCATION	位置结果未知。对应数值“2”。
//BMAP_STATUS_UNKNOWN_ROUTE	导航结果未知。对应数值“3”。
//BMAP_STATUS_INVALID_KEY	非法密钥。对应数值“4”。
//BMAP_STATUS_INVALID_REQUEST	非法请求。对应数值“5”。
//BMAP_STATUS_PERMISSION_DENIED	没有权限。对应数值“6”。(自 1.1 新增)
//BMAP_STATUS_SERVICE_UNAVAILABLE	服务不可用。对应数值“7”。(自 1.1 新增)
//BMAP_STATUS_TIMEOUT	超时。对应数值“8”。(自 1.1 新增)
}

//单击获取点击的经纬度和地址
function clickEventListener(map){
	map.addEventListener("click",function(e){
		map.clearOverlays();    //清除地图上所有覆盖物
		alert(e.point.lng + "," + e.point.lat);
		map.addOverlay(getMarker(e.point));//标注
		
		var geoc = new BMap.Geocoder();   
		var pt = e.point;
		geoc.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + 
					
					addComp.streetNumber);
		});    
	});
}

//创建一个标注
function getMarker(point){
	marker = new BMap.Marker(point);// 创建标注
	return marker;
}
//可以拖拽
function enableDragging(marker){
	marker.enableDragging();	    
}
//不可拖拽
function disableDragging(marker){
	marker.disableDragging();           
}

//添加定位控件
function geolocationControl(map){
	var geolocationControl = new BMap.GeolocationControl();
	geolocationControl.addEventListener("locationSuccess", function(e){
		// 定位成功事件
		var address = '';
		address += e.addressComponent.province;
		address += e.addressComponent.city;
		address += e.addressComponent.district;
		address += e.addressComponent.street;
		address += e.addressComponent.streetNumber;
		alert("当前定位地址为：" + address);
	});
	geolocationControl.addEventListener("locationError",function(e){
		// 定位失败事件
		alert(e.message);
	});
	map.addControl(geolocationControl);
}

//搜索框输入搜索
function autoSearch(map){
	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
			{"input" : "suggestId"
				,"location" : map
			});
	
	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		setPlace();
	});
	
	function setPlace(){
		map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp));    //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
			onSearchComplete: myFun
		});
		local.search(myValue);
	}
}*/