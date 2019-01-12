ruizhi.Package("ruizhi");

ruizhi.BaiduMap = function(modelWin_name,divId,inputId) {

	// 私有成员定义;
	var _this = this;
	var map = null;
	var marker = null;
	var address = null;

	// 私有方法定义
	var init = function() {
		//console.info(map);
		map = new BMap.Map(divId);
		//map.centerAndZoom("北京",12);   // 初始化地图,设置城市和地图级别。
		var point;
		var value = $('input[name="'+modelWin_name+'"]').val();
		var address = $('input[name="'+modelWin_name+'"]').attr("address");
		//alert(address);
		if(value != null && value != '' && value != undefined && value != 'undefined'){
			var p = value.split(",");
			point = new BMap.Point(p[0],p[1]);
		}else{
			point = new BMap.Point(116.404, 39.915);
			address = "北京";
		}
		marker = new BMap.Marker(point);  // 创建标注
		map.centerAndZoom(point, 15);
		_this.getMarker(point);
		//_this.getAddressByPoint(point);
		_this.setMapLabel(address);
		
		map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		
		var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
		var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
		/*缩放控件type有四种类型:
			BMAP_NAVIGATION_CONTROL_SMALL：仅包含平移和缩放按钮；
			BMAP_NAVIGATION_CONTROL_PAN:仅包含平移按钮；
			BMAP_NAVIGATION_CONTROL_ZOOM：仅包含缩放按钮*/
		
		map.addControl(top_left_control);        
		map.addControl(top_left_navigation);     
		
	}
	
	//浏览器自动定位
	this.webGeolocation = function(){
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				_this.getMarker(r.point);
				//alert(JSON.stringify(r));
				map.panTo(r.point);
				//alert('您的位置：'+r.point.lng+','+r.point.lat);
				address = r.address.province +  r.address.city +  r.address.district +  r.address.street;
				alert("当前定位地址为：" + address);
				_this.setPointAndAddress(r.point,address);
				_this.setMapLabel(address);
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
	this.clickEventListener = function(){
		map.addEventListener("click",function(e){
			//alert(e.point.lng + "," + e.point.lat);
			_this.getMarker(e.point);
			
			var geoc = new BMap.Geocoder();   
			var pt = e.point;
			geoc.getLocation(pt, function(rs){
				var addComp = rs.addressComponents;
				//alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + 
						//addComp.streetNumber);
				address = addComp.province + "" + addComp.city + "" + addComp.district + "" + addComp.street + "" + addComp.streetNumber;
				//alert(address);
				var lngAndLat = e.point.lng + "," + e.point.lat;
				//alert(lngAndLat);
				//$('input[name="'+modelWin_name+'"]').val(lngAndLat);
				//$('input[name="'+modelWin_name+'"]').attr("address", address);
				_this.setPointAndAddress(e.point,address);
				_this.setMapLabel(address);
			});    
		});
	}
	
	//地址转为坐标
	this.addressToPoint = function(address){
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		myGeo.getPoint(address, function(point){
			if (point) {
				_this.getMarker(point);
				//alert(JSON.stringify(point));
			}else{
				alert("您选择地址没有解析到结果!");
			}
		});
	}
	
	//根据坐标创建一个标注，将标注添加到地图中
	this.getMarker = function(point){
		marker = new BMap.Marker(point);// 创建标注
		map.clearOverlays();    //清除地图上所有覆盖物
		//map.centerAndZoom(point, 16); //设置为地图的中心，添加标注的时候设置不太好
		map.addOverlay(marker); // 将标注添加到地图中
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		marker.enableDragging();	//可以拖拽
		//marker.disableDragging();	   //不可拖拽 
	}
	
	//添加定位控件
	this.geolocationControl = function(){
		var geolocationControl = new BMap.GeolocationControl();
		geolocationControl.addEventListener("locationSuccess", function(e){
			// 定位成功事件
			address = '';
			address += e.addressComponent.province;
			address += e.addressComponent.city;
			address += e.addressComponent.district;
			address += e.addressComponent.street;
			address += e.addressComponent.streetNumber;
			alert("当前定位地址为：" + address);
			_this.getMarker(e.point);
			_this.setPointAndAddress(e.point,address);
			_this.setMapLabel(address);
		});
		geolocationControl.addEventListener("locationError",function(e){
			// 定位失败事件
			alert(e.message);
		});
		map.addControl(geolocationControl);  
	}
	
	///搜索框输入地址搜索
	this.autoSearch = function(){
		var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
				{"input" : inputId
					,"location" : map
				});
		
		var myValue;
		ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
			var _value = e.item.value;
			myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			setPlace();
			//$('input[name="'+modelWin_name+'"]').attr("address", myValue);
			address = myValue;
			//alert(myValue);
			//_this.setMapLabel(myValue);
		});
		
		function setPlace(){
			map.clearOverlays();    //清除地图上所有覆盖物
			function myFun(){
				var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
				//console.info(local);
				//var pp = _this.addressToPoint(myValue);
				_this.getMarker(pp);
				map.centerAndZoom(pp, 16); //设置为地图的中心
				//var lngAndLat = pp.lng + "," + pp.lat;
				//$('input[name="'+modelWin_name+'"]').val(lngAndLat);
				_this.setPointAndAddress(pp, address);
				_this.setMapLabel(address);
				//alert(address);
			}
			var local = new BMap.LocalSearch(map, { //智能搜索
				onSearchComplete: myFun
			});
			local.search(myValue);
		}
	}
	
	//返回地图上当前的坐标
	this.returnPoint = function(){
		var p = marker.getPosition();
		return (p.lng + "," + p.lat);
	}
	
	//返回地图上当前的地址
	this.returnAddress = function(){
		return address;
	}
	
	//根据坐标创建一个标注，将标注添加到地图中,并且置为图片的中心
	this.setMarker = function(point){
		marker = new BMap.Marker(point);// 创建标注
		map.clearOverlays();    //清除地图上所有覆盖物
		map.centerAndZoom(point, 16); //设置为地图的中心，添加标注的时候设置不太好
		map.addOverlay(marker); // 将标注添加到地图中
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		marker.enableDragging();	//可以拖拽
		//marker.disableDragging();	   //不可拖拽 
	}
	
	this.setPointAndAddress = function(point,address){
		var lngAndLat = point.lng + "," + point.lat;//纬度，经度
		$('input[name="'+modelWin_name+'"]').val(lngAndLat);
		$('input[name="'+modelWin_name+'"]').attr("address", address);
	}
	
	this.setMapLabel = function(address){
		var label = new BMap.Label(address,{offset:new BMap.Size(20,-10)});
	    label.setStyle({
	        color : "red",
	        fontSize : "20px",
	        height : "20px",
	        lineHeight : "20px",
	        fontFamily : "微软雅黑"
	      });
		marker.setLabel(label);
	}
	
	this.getAddressByPoint = function(point){
		var geoc = new BMap.Geocoder();   
		var pt = point;
		geoc.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			//alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + 
					//addComp.streetNumber);
			address = addComp.province + "" + addComp.city + "" + addComp.district + "" + addComp.street + "" + addComp.streetNumber;
			//alert(address);
			_this.setPointAndAddress(point,address);
			_this.setMapLabel(address);
			//var lngAndLat = e.point.lng + "," + e.point.lat;
			//alert(lngAndLat);
			//$('input[name="'+modelWin_name+'"]').val(lngAndLat);
			//$('input[name="'+modelWin_name+'"]').attr("address", address);
			//_this.setPointAndAddress(e.point,address);
			//_this.setMapLabel(address);
		});  
		//alert(address);
		//return address;
	}
	
	init();// 初始化
};