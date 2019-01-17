//////////////////////////////////////////
//RuizhiSoft corp. 2016年8月10日20:33:10
//Author :zheng.zhijie
//commits:ZTree
//////////////////////////////////////////

//类定义
ruizhi.Package("ruizhi");

ruizhi.ZTree = function(zTreeId, initParam) {
	
	var zTreeObj = null;
	var setting = null;
	var _this = this;
	var curStatus = "init", curAsyncCount = 0, asyncForAll = false, goAsync = false;
	var lastValue = "", nodeList = [], fontCss = {}, clickCount = 0;  
	var demoMsg = {
			async:"正在进行异步加载，请等一会儿再点击...",
			expandAllOver: "全部展开完毕",
			asyncAllOver: "后台异步加载完毕",
			asyncAll: "已经异步加载完毕，不再重新加载",
			expandAll: "已经异步加载完毕，使用 expandAll 方法"
		};
	
	/* 初始化--私有方法  */
	var init = function() {
		var zTreeTable = $("#" + zTreeId);

		if(!ruizhi.IsNull(initParam)){
			if(!ruizhi.IsNull(initParam.callback))
			onAsyncSuccess = initParam.callback.onAsyncSuccess;
		}
		
		var url = zTreeTable.attr("url");
		var showCheck = zTreeTable.attr("showCheck") == "true" ? true : false;//是否有复选框，true是有，否则没有
		//var asyncLoad = zTreeTable.attr("asyncLoad") == "true" ? true : false;//是否异步加载，true是有，否则没有
		var expandAllNodes = zTreeTable.attr("expandAllNodes") == "true" ? true : false;//是否展开全部，true是有，否则没有
		var asyncAllNodes = zTreeTable.attr("asyncAllNodes") == "true" ? true : false;//是否异步加载全部，但不展开，true是有，否则没有
		var chkboxType = zTreeTable.attr("chkboxType") == "false" ? { "Y": "", "N": "" } : { "Y": "ps", "N": "ps" };//是否勾选 checkbox 对于父子节点的关联关系，true是有，否则没有，默认关联
		var showSearch = zTreeTable.attr("showSearch") == "true" ? true : false;//是否展示根据名称过滤的搜索框，启用搜索功能
		//alert(zTreeTable.attr("chkboxType"));
		//alert(zTreeTable.attr("asyncLoad"));
		var zTreeOnClick = zTreeTable.attr("zTreeOnClick");//单击方法
		var zTreeOnCheck = zTreeTable.attr("zTreeOnCheck");//勾选事件
		//alert(zTreeOnCheck);

		//setting-check-enable，默认是false，设置复选框
		setting = {
				view: {
					dblClickExpand: false,//双击节点时，是否自动展开父节点的标识
					showLine: true,//是否显示节点之间的连线
					//fontCss:{'color':'black','font-weight':'bold'},//字体样式函数
					//fontCss:{'height': '22px'},//字体样式函数
					selectedMulti: false, //设置是否允许同时选中多个节点
					showIcon: false //设置 zTree 不显示图标
				},
				check: {
					enable: showCheck,
					autoCheckTrigger: zTreeOnCheck != "undefined" ? true : false,//设置自动关联勾选时是否触发 beforeCheck / onCheck 事件回调函数。
					chkboxType: chkboxType
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				async: {
					enable: true,//true是异步加载
					url:url,//请求url
					autoParam:["id"],//每次自动提交的参数，第一次为null
					otherParam:{},//额外提交的参数
					// dataType: "text",//默认text 
					// type:"get",//默认post 
					//dataFilter: filter //异步返回后经过Filter 
				},
				callback: {
					onClick: onClick,//单击事件
					onCheck: onCheck,//勾选事件
					beforeAsync: beforeAsync,
					onAsyncSuccess: onAsyncSuccess,
					onAsyncError: onAsyncError
				}
			};
		
		
		$.extend(setting.async,initParam);
		
		//alert(ruizhi.ToJson(initParam));
		//alert(ruizhi.ToJson(setting));
		
		//异步请求之后的过滤器
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		
		function beforeAsync() {
			curAsyncCount++;
		}
		
		function onAsyncSuccess(event, treeId, treeNode, msg) {
			curAsyncCount--;
//			console.info(curAsyncCount);
			if (curStatus == "expand") {
				expandNodes(treeNode.children);
			} else if (curStatus == "async") {
				asyncNodes(treeNode.children);
			}

			if (curAsyncCount <= 0) {
				if (curStatus != "init" && curStatus != "") {
					console.info((curStatus == "expand") ? demoMsg.expandAllOver : demoMsg.asyncAllOver);
					asyncForAll = true;
					ruizhi.WaitDivHidden();
				}
				curStatus = "";
			}
		}

		function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
			curAsyncCount--;

			if (curAsyncCount <= 0) {
				curStatus = "";
				if (treeNode!=null) asyncForAll = true;
			}
		}
		
		/*
		 * 单击方法，传回去的是当前的对象
		 */
		function onClick(event, treeId, treeNode, clickFlag) {
			//alert(zTreeOnClick);
			if (zTreeOnClick != null)
				eval(zTreeOnClick + "(" + ruizhi.ToJson(treeNode) + ")");
		}	
		
		/*
		 * 用于捕获 checkbox / radio 被勾选 或 取消勾选的事件回调函数
		 */
		function onCheck(event, treeId, treeNode) {
			//alert(zTreeOnCheck);
			if (zTreeOnCheck != null)
				eval(zTreeOnCheck + "(" + ruizhi.ToJson(treeNode) + ")");
		}	

		zTreeObj = $.fn.zTree.init($("#"+zTreeId), setting);
		
		if(expandAllNodes){
            setTimeout(function(){  
            	_this.expandAll();
            },1000);//延迟加载  
		}
		
		//如果全部展开是true的话，这个就不执行了
		if(asyncAllNodes && !expandAllNodes){
			setTimeout(function(){  
				_this.asyncAll();
			},1000);//延迟加载  
		}
		
		var searchHtml = ''+
	'<div class="row">'+
	    '<div class="col-md-4">'+
			'<div class="form-group">'+				
				'<div class="col-lg-12">'+
					'<input type="text" class="span2" placeholder="名称" id="'+zTreeId+'-searchName">'+
					'<label id="'+zTreeId+'-number"></label>'+  
//				    '<a id="'+zTreeId+'-clickUp" class="glyphicon glyphicon-menu-up"></a>'+  
//        			'<a id="'+zTreeId+'-clickDown" class="glyphicon glyphicon-menu-down"></a>'+ 
				'</div>'+
			'</div>'+
		'</div>'+
	'</div>'; 
		if(showSearch){//启用搜索功能
			$(searchHtml).insertBefore(zTreeTable);
			var searchNameObject = $("#"+zTreeId+"-searchName");
//			var clickUpObject = $("#"+zTreeId+"-clickUp");
//			var clickDownObject = $("#"+zTreeId+"-clickDown");
			searchNameObject.val(""); //清空搜索框中的内容
			searchNameObject
			.bind("focus", focusKey)//获得焦点事件
			.bind("blur", blurKey)//失去焦点事件
			.bind("propertychange", searchNode)//输入框的值发生改变事件（IE专属的）
			.bind("input", searchNode)//输入框的值发生改变事件
			//.bind("keyup", callNumber);//onkeyup 事件会在键盘按键被松开时发生。
			.bind("keydown", callNumber);//键盘按下事件
//			clickUpObject
//			.bind("click", clickUp);//点击事件
//			clickDownObject
//			.bind("click", clickDown);//点击事件
		}

		
		//键盘释放：当输入框的键盘按键被松开时，把查询到的数据结果显示在标签中  
		function callNumber(e){ 
			var ev = document.all ? window.event : e;
		    if(ev.keyCode!=13) {//回车事件
		        return ;
		     }
		    
		    //如果结果有值，则显示比例；如果结果为0，则显示"[0/0]"；如果结果为空，则清空标签框；  
		    if(nodeList.length == 0){
		  	    document.getElementById(zTreeId+"-number").innerHTML="[0/0]";  
		  	    zTreeObj.cancelSelectedNode(); //取消焦点  
		    }else if(nodeList.length == clickCount){
		  	  clickCount = 0;
		  	  alert("您已位于最后一条记录上！");
		    }else if(nodeList.length){  
		        //让结果集里边的第一个获取焦点（主要为了设置背景色），再把焦点返回给搜索框  
		    	zTreeObj.selectNode(nodeList[clickCount],false );  
		        document.getElementById(zTreeId+"-searchName").focus(); 
		        
		        clickCount=clickCount+1; //每次加1，可以遍历下一个节点
		        document.getElementById(zTreeId+"-number").innerHTML="["+clickCount+"/"+nodeList.length+"]";
		    }		    
		    		  
		    //如果输入框中没有搜索内容，则清空标签框  
		    if(document.getElementById(zTreeId+"-searchName").value ==""){  
		        document.getElementById(zTreeId+"-number").innerHTML="";  
		        zTreeObj.cancelSelectedNode();  
		    }  
		}  
		function focusKey(e) {  
		    if (searchNameObject.hasClass("empty")) {  
		    	searchNameObject.removeClass("empty");  
		    }  
		}  
		function blurKey(e) {  
		    if (searchNameObject.get(0).value === "") {  
		    	searchNameObject.addClass("empty");  
		    }  
		}  
		//搜索节点  
		function searchNode(e) {  
		    var value = $.trim(searchNameObject.get(0).value);  
		    var keyType = "name";  
		  
		    if (searchNameObject.hasClass("empty")) {  
		        value = "";  
		    }  
		    if (lastValue === value) 
		    	return;  
		    lastValue = value;
		    clickCount = 0;//值不一样，重新计算
		    if (value === ""){
		        updateNodes(false);  
		        return;  
		    };  
		    nodeList = zTreeObj.getNodesByParamFuzzy(keyType, value); //调用ztree的模糊查询功能，得到符合条件的节点  
		    updateNodes(true); //更新节点  
		}
		
		//高亮显示被搜索到的节点  
		function updateNodes(highlight) {  
		    for( var i=0, l=nodeList.length; i<l; i++) {  
		        nodeList[i].highlight = highlight; //高亮显示搜索到的节点(highlight是自己设置的一个属性)  
		        zTreeObj.expandNode(nodeList[i].getParentNode(), true, false, false); //将搜索到的节点的父节点展开  
		        zTreeObj.updateNode(nodeList[i]); //更新节点数据，主要用于该节点显示属性的更新  
		    }  
		}
		
		//设置颜色  
		function getFontCss(treeId, treeNode) {  
		    return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};  
		}
		
		//点击向上按钮时，将焦点移向上一条数据  
		function clickUp(){  
		    //如果焦点已经移动到了最后一条数据上，就返回第一条重新开始，否则继续移动到下一条  
		    if(nodeList.length==0){  
		        alert("没有搜索结果！");  
		        return ;  
		    }else if(clickCount==1) {  
		        alert("您已位于第一条记录上！");  
		        return;  
		        //让结果集里边的下一个节点获取焦点（主要为了设置背景色），再把焦点返回给搜索框  
		        //zTree.selectNode(nodeList[clickCount], false)  
		    }else{  
		        //让结果集里边的第一个获取焦点（主要为了设置背景色），再把焦点返回给搜索框  
		    	zTreeObj.selectNode(nodeList[clickCount], false);  
		        clickCount --;  
		    }  
		    document.getElementById(zTreeId+"-searchName").focus();  
		    //显示当前所在的是条数  
		    document.getElementById(zTreeId+"-number").innerHTML = "[" + clickCount + "/" + nodeList.length + "]";  
		}  
		//点击向上按钮时，将焦点移向下一条数据  
		function clickDown(){  
		    //如果焦点已经移动到了最后一条数据上，则提示用户（或者返回第一条重新开始），否则继续移动到下一条  
		    if(nodeList.length==0){  
		        alert("没有搜索结果！");  
		        return ;  
		    }else if(nodeList.length==clickCount)  
		    {  
		        alert("您已位于最后一条记录上！")  
		        return;  
		    }else{  
		        //让结果集里边的第一个获取焦点（主要为了设置背景色），再把焦点返回给搜索框  
		    	zTreeObj.selectNode(nodeList[clickCount], false)  
		        clickCount ++;  
		    }  
		    document.getElementById(zTreeId+"-searchName").focus();  
		    //显示当前所在的条数  
		    document.getElementById(zTreeId+"-number").innerHTML = "[" + clickCount + "/" + nodeList.length + "]";  
		}  
		
	}

	var expandNodes = function(nodes) {
		if (!nodes) return;
		curStatus = "expand";
		for (var i=0, l=nodes.length; i<l; i++) {
			zTreeObj.expandNode(nodes[i], true, false, false);
			if (nodes[i].isParent && nodes[i].zAsync) {
				expandNodes(nodes[i].children);
			} else {
				goAsync = true;
			}
		}
	}
	
	var asyncNodes = function(nodes) {
		if (!nodes) return;
		curStatus = "async";
		for (var i=0, l=nodes.length; i<l; i++) {
			if (nodes[i].isParent && nodes[i].zAsync) {
				asyncNodes(nodes[i].children);
			} else {
				goAsync = true;
				zTreeObj.reAsyncChildNodes(nodes[i], "refresh", true);
			}
		}
	}
	
	var check = function() {
		if (curAsyncCount > 0) {
			console.info(demoMsg.async);
			ruizhi.WaitDivHidden();
			return false;
		}
		return true;
	}
	
	/* 加载数据，异步加载  */
	this.loadData = function(url, paramObj) {
		if(url != null && url != "" && url != "undefined"){
			//var zNodes = ruizhi.InvokeMethod(url,paramObj);
			//zTreeObj = $.fn.zTree.init($("#"+zTreeId), setting, zNodes);
			var initParam = {};
			initParam.url = url;
			initParam.otherParam = paramObj;
			$.extend(setting.async,initParam);
			$.fn.zTree.init($("#"+zTreeId), setting);
		}else{
			ruizhi.Alert("请求出错！");
		}
	}
	

	/**
	 * 增加节点
	 * parentNode：指定的父节点，如果增加根节点，请设置 parentNode 为 null 即可。
	 * index：新节点插入的位置（从 0 开始）index = -1 时，插入到最后
	 * newNodes：需要增加的节点数据 JSON 对象集合，数据只需要满足 zTree 的节点数据必需的属性即可
	 * isSilent：设定增加节点后是否自动展开父节点。isSilent = true 时，不展开父节点，其他值或缺省状态都自动展开。
	 */
	this.addNodes = function(parentNode, index, newNodes, isSilent) {
		zTreeObj.addNodes(parentNode, index, newNodes, isSilent);
	}
	
	/**
	 * 更新某节点数据，主要用于该节点显示属性的更新。
	 * treeNode JSON 指定需要更新的节点 JSON 数据
	 * checkTypeFlag Boolean 
	 * checkTypeFlag = true 表示按照 setting.check.chkboxType 属性进行父子节点的勾选联动操作
	 * checkTypeFlag = false 表示只修改此节点勾选状态，无任何勾选联动操作
	 */
	this.updateNode = function(treeNode, checkTypeFlag) {
		zTreeObj.updateNode(treeNode, checkTypeFlag);
	}
	
	/**
	 * 删除节点（支持多个）
	 * treeNode：需要被删除的节点数据
	 * callbackFlag：callbackFlag = true 表示执行此方法时触发 beforeRemove & onRemove 事件回调函数
		callbackFlag = false 表示执行此方法时不触发事件回调函数，省略此参数，等同于 false
	 */
	this.removeNode = function(treeNode, callbackFlag) {
		//var nodes=zTreeObj.getSelectedNodes();
		//zTreeObj.removeNode(nodes[0], callbackFlag);
		zTreeObj.removeNode(treeNode, callbackFlag);
	}
	
	/**
	 * 强行异步加载父节点的子节点。
	 * parentNode JSON 指定需要异步加载的父节点 JSON 数据
	 * reloadType String reloadType = "refresh" 表示清空后重新加载。
	 * 						reloadType != "refresh" 时，表示追加子节点处理。
		isSilent Boolean 设定异步加载后是否自动展开父节点。isSilent = true 时，不展开父节点，其他值或缺省状态都自动展开。
	 */
	this.reAsyncChildNodes = function(parentNode, reloadType, isSilent) {
		zTreeObj.reAsyncChildNodes(parentNode, reloadType, isSilent);
	}
	
	/**
	 * 获取选中节点（选择的是选中的节点，非有复选框的，单个）
	 * 获取 zTree 当前被选中的节点数据集合
	 */
	this.getSelectedNodes = function() {
		return zTreeObj.getSelectedNodes();
	}
	
	/**
	 * 获取选中节点（选择的是选中的节点，有复选框的，可以多个）
	 * checked = true 表示获取 被勾选 的节点集合
		checked = false 表示获取 未勾选 的节点集合
		省略此参数，等同于 true。
		对于 treeNode.nochecked = true 的节点不进行获取。
	 */
	this.getCheckedNodes = function(checked) {
		return zTreeObj.getCheckedNodes(checked);
	}

	/**
	 * 获取选中节点的id（选择的是选中的节点，有复选框的，可以多个）
	 * checked = true 表示获取 被勾选 的节点集合
		checked = false 表示获取 未勾选 的节点集合
		省略此参数，等同于 true。
		对于 treeNode.nochecked = true 的节点不进行获取。
	 */
	this.getCheckedNodeIds = function(checked) {
		var nodes = zTreeObj.getCheckedNodes(checked);
		var ids = [];
        if(nodes != null && nodes.length > 0){
			for(var i in nodes){
				var item = nodes[i];
				ids.push(item.id);
			}
		}
		return ids;
	}
	
	/**
	 * 获取选中节点，不包括半选的
	 * nodes[i].getCheckStatus()
	 * checked true：是选中状态， false：未选中；half true：半选 ，false：全选
	 */
	this.getCheckNotHalfNodes = function() {
		var nodes = zTreeObj.getCheckedNodes();
		var new_nodes = [];
        if(nodes != null && nodes.length > 0){
			for(var i in nodes){
				var item = nodes[i];
				var checkStatus = item.getCheckStatus();
				if(!checkStatus.half){
					//全选状态下的
					new_nodes.push(item);
				}
			}
		}
		return new_nodes;
	}
	
	/**
	 * 获取选中节点的id，不包括半选的
	 * nodes[i].getCheckStatus()
	 * checked true：是选中状态， false：未选中；half true：半选 ，false：全选
	 */
	this.getCheckNotHalfNodeIds = function() {
		var nodes = zTreeObj.getCheckedNodes();
		var ids = [];
		if(nodes != null && nodes.length > 0){
			for(var i in nodes){
				var item = nodes[i];
				var checkStatus = item.getCheckStatus();
				if(!checkStatus.half){
					//全选状态下的
					ids.push(item.id);
				}
			}
		}
		return ids;
	}
	
	/**
	 * 获取输入框勾选状态被改变的节点集合（与原始数据 checkedOld 对比）
	 * 返回全部勾选状态被改变的节点集合 Array
	 * 如果需要获取每次操作后全部被改变勾选状态的节点数据，请在每次勾选操作后，遍历所有被改变勾选状态的节点数据，让其 checkedOld = checked 就可以了。
	 */
	this.getChangeCheckedNodes = function() {
		return zTreeObj.getChangeCheckedNodes();
	}
	
	/**
	 * 展开 / 折叠 指定的节点
	 * 此方法可以触发 beforeExpand / onExpand 或 beforeCollapse / onCollapse 事件回调函数。
	 * treeNode : 需要 展开 / 折叠 的节点数据
	 * expandFlag : expandFlag = true 表示 展开 节点,expandFlag = false 表示 折叠 节点，省略此参数，则根据对此节点的展开状态进行 toggle 切换
	 * sonSign ： sonSign = true 表示 全部子孙节点 进行与 expandFlag 相同的操作，sonSign = false 表示 只影响此节点，对于其 子孙节点无任何影响，
	   sonSign = false 且 treeNode.open = expandFlag 时，不会触发回调函数，直接返回，省略此参数，等同于 false
	 * focus ： focus = true 表示 展开 / 折叠 操作后，通过设置焦点保证此焦点进入可视区域内，focus = false 表示 展开 / 折叠 操作后，不设置任何焦点，省略此参数，等同于 true
	 * callbackFlag：callbackFlag = true 表示执行此方法时触发 beforeExpand / onExpand 或 beforeCollapse / onCollapse 事件回调函数，省略此参数，等同于 false
	 */
	this.expandNode = function(treeNode, expandFlag, sonSign, focus, callbackFlag) {
		return zTreeObj.expandNode(treeNode, expandFlag, sonSign, focus, callbackFlag);
	}
	
	/**
	 * 展开 / 折叠 全部节点
	 * 此方法不会触发 beforeExpand / onExpand 和 beforeCollapse / onCollapse 事件回调函数。
		expandFlag = true 表示 展开 全部节点
		expandFlag = false 表示 折叠 全部节点
	 */
	this.expandAll = function(expandFlag) {
		return zTreeObj.expandAll(expandFlag);
	}
	
	/**
	 * 获取 zTree 的全部节点数据
	 * 1、Array 仅仅是根节点的集合（默认情况子节点都处于 children 属性下）；
		2、如需遍历全部节点需要利用递归，或利用 transformToArray 方法 将数据变成简单的 Array 集合
		3、对于异步加载模式下，尚未加载的子节点是无法通过此方法获取的。
	 */
	this.getNodes = function() {
		return zTreeObj.getNodes();
	}
	
	/**
	 * 强行异步加载父节点的子节点。[setting.async.enable = true 时有效]
	 * 1、parentNode = null 时，相当于从根节点 Root 进行异步加载
		2、parentNode.isParent = false 时，不进行异步加载
		reloadType = "refresh" 表示清空后重新加载。
		reloadType != "refresh" 时，表示追加子节点处理。
		isSilent:设定异步加载后是否自动展开父节点。
		isSilent = true 时，不展开父节点，其他值或缺省状态都自动展开
	 */
	this.reAsyncChildNodes = function(parentNode, reloadType, isSilent) {
		return zTreeObj.reAsyncChildNodes(parentNode, reloadType, isSilent);
	}
	
	/**
	 * 勾选 或 取消勾选 全部节点
	 * checked = true 表示勾选全部节点
		checked = false 表示全部节点取消勾选
		不会影响 treeNode.nochecked = true 的节点。
		不会影响未加载的节点。
	 */
	this.checkAllNodes = function(checked) {
		return zTreeObj.checkAllNodes(checked);
	}
	
	/**
	 * 勾选 或 取消勾选 单个节点
	 * treeNode：需要勾选 或 取消勾选 的节点数据
		checked： true 表示勾选节点，false 表示节点取消勾选，省略此参数，则根据对此节点的勾选状态进行 toggle 切换
		checkTypeFlag：true 表示按照 setting.check.chkboxType 属性进行父子节点的勾选联动操作，
						 false 表示只修改此节点勾选状态，无任何勾选联动操作
		callbackFlag：true 表示执行此方法时触发 beforeCheck & onCheck 事件回调函数
					false 表示执行此方法时不触发事件回调函数
	 */
	this.checkNode = function(treeNode, checked, checkTypeFlag, callbackFlag) {
		return zTreeObj.checkNode(treeNode, checked, checkTypeFlag, callbackFlag);
	}
	
	/**
	 * 选中指定节点
	 * treeNode JSON ：需要被选中的节点数据
	 * addFlag Boolean ：true 表示追加选中，会出现多点同时被选中的情况，
	   					false （默认）表示单独选中，原先被选中的节点会被取消选中状态
	   					setting.view.selectedMulti = false 时，此参数无效，始终进行单独选中
	 * isSilent Boolean ：true 选中节点时，不会让节点自动滚到到可视区域内
	 					 false （默认）表示选中节点时，会让节点自动滚到到可视区域内
	 */
	this.selectNode = function(treeNode, addFlag, isSilent) {
		return zTreeObj.selectNode(treeNode, addFlag, isSilent);
	}
	
	/**
	 * 根据节点数据的属性搜索，获取条件完全匹配的节点数据 JSON 对象
	 * key String ：需要精确匹配的属性名称
	 * value ：需要精确匹配的属性值，可以是任何类型，只要保证与 key 指定的属性值保持一致即可
	 * parentNode JSON ：搜索范围，指定在某个父节点下的子节点中进行搜索，忽略此参数，表示在全部节点中搜索
	 * 返回值：匹配精确搜索的节点数据 1、如无结果，返回 null
			2、如有多个节点满足查询条件，只返回第一个匹配到的节点
	 */
	this.getNodeByParam = function(key, value, parentNode) {
		return zTreeObj.getNodeByParam(key, value, parentNode);
	}
	
	/**
	 * 异步加载的时候，加载整棵树出来
	 */
	this.expandAll = function() {
		if (!check()) {
			return;
		}
		if (asyncForAll) {
			console.info(demoMsg.expandAll);
			zTreeObj.expandAll(true);
		} else {
			expandNodes(zTreeObj.getNodes());
			if (!goAsync) {
				console.info(demoMsg.expandAll);
				curStatus = "";
			}
		}
	}
	
	/**
	 * 异步加载的时候，后台加载全部的出来了，但是不展开
	 */
	this.asyncAll = function() {
		if (!check()) {
			return;
		}
		ruizhi.WaitDivShow();
		if (asyncForAll) {
			console.info(demoMsg.asyncAll);
			ruizhi.WaitDivHidden();
		} else {
			asyncNodes(zTreeObj.getNodes());
			if (!goAsync) {
				console.info(demoMsg.asyncAll);
				curStatus = "";
				ruizhi.WaitDivHidden();
			}
			
			if(curAsyncCount == 0){
				console.info('第一次完毕');
				ruizhi.WaitDivHidden();
			}
		}
	}
	
	/**
	 * 刷新，重新加载
	 */
	this.reset = function() {
		if (!check()) {
			return;
		}
		asyncForAll = false;
		goAsync = false;
		console.info("重新初始化");
		$.fn.zTree.init($("#"+zTreeId), setting);
	}
	
	/**
	 * 获取当前结点的，子点的Ids
	 */
	this.getChildNodes=function (treeNode) {  
		  
        var childNodes = zTreeObj.transformToArray(treeNode);  
 
        var nodes = new Array();  
 
        for(i = 0; i < childNodes.length; i++) {  
 
                   nodes[i] = childNodes[i].id;  
 
        }  
        return nodes.join(",");  
	}  

	init();// 初始化

};