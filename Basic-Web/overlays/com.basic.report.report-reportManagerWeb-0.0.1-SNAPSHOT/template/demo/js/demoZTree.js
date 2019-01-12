//////////////////////////////////////////
//ZTESoft corp. 2016-03-01
//Author :yu.xiao
//commits:演示页面
//////////////////////////////////////////

/* 初始化 */

//////////////////////////////////////////
//类定义
ruizhi.Package("ruizhi.demo");
ruizhi.demo.demoZTree = function(){
	
	//var grid1;

	 var zTreeObj;
	 var treeDemo = null;
	 
	return {
		init:function(){
			//grid1 = new ruizhi.DataGrid("demo-demoZTree-grid1");
			treeDemo = new ruizhi.ZTree("treeDemo");
			//setting-check-enable，默认是false，设置复选框
			var setting = {
					check: {
						enable: true
					},
					data: {
						simpleData: {
							enable: true
						}
					}
				};
			
			  
			   // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）

			   //checked默认为false，不打勾
			   var zNodes =[
			    			{ id:1, pId:0, name:"随意勾选 1", open:true},
			    			{ id:11, pId:1, name:"随意勾选 1-1", open:true},
			    			{ id:111, pId:11, name:"随意勾选 1-1-1"},
			    			{ id:112, pId:11, name:"随意勾选 1-1-2"},
			    			{ id:12, pId:1, name:"随意勾选 1-2", open:true},
			    			{ id:121, pId:12, name:"随意勾选 1-2-1"},
			    			{ id:122, pId:12, name:"随意勾选 1-2-2"},
			    			{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
			    			{ id:21, pId:2, name:"随意勾选 2-1"},
			    			{ id:22, pId:2, name:"随意勾选 2-2", open:true},
			    			{ id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
			    			{ id:222, pId:22, name:"随意勾选 2-2-2"},
			    			{ id:23, pId:2, name:"随意勾选 2-3"}
			    		];
			   
			   $(document).ready(function(){
			      zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			     
			   });
			   
		},

		loadData2 : function(){//加载数据
			/*var url = WEB_ROOT + '/demo/func/demoZTree/qryTree.do';
			var paramObj = {
				id:1,
				userName:"world"
			}
			grid1.loadData(url,paramObj);*/
			var url = WEB_ROOT + '/demo/func/demoZTree/qryTreeAll2.do';
			var qryParams = {};
			//qryParams.testId = testId;
			var zNodes = ruizhi.InvokeMethod(url,qryParams);
			alert(JSON.stringify(zNodes));
			var setting = {
					check: {
						enable: true
					},
					data: {
						simpleData: {
							enable: true
						}
					}
				};
			zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		},
		
		add : function(){
			
			/*var newNodes = [
			                { "id":"101", "name":"xxx"},
			                { "id":"101", "name":"xx1x"}
			               ];
			//var parentNode = { "id":"1", "name":"随意勾选 1"};
			var parentNode = zTreeObj.getNodeByParam("id", 11, null);
			alert(JSON.stringify(parentNode));
		      zTreeObj.addNodes(parentNode,-1,newNodes,true);*/
			var newNodes = [
			                { "id":"101", "name":"xxx"},
			                { "id":"101", "name":"xx1x"}
			               ];
			var parentNode = zTreeObj.getNodeByParam("id", 11, null);
			treeDemo.addNodes(parentNode,newNodes);
		},
		
		onCheck: function(){
           // var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
            var nodes=treeDemo.getCheckedNodes(true);
            alert(JSON.stringify(nodes));
            var v="";
            for(var i=0;i<nodes.length;i++){
            v+=nodes[i].name + ",";
            alert(nodes[i].id); //获取选中节点的值
            }
            
            
            },
            
            onSelect: function(){
            	//var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
            	var nodes=treeDemo.getSelectedNodes();
            	alert(JSON.stringify(nodes));
            	var v="";
            	for(var i=0;i<nodes.length;i++){
            		v+=nodes[i].name + ",";
            		alert(nodes[i].id); //获取选中节点的值
            	}
            	
            },
            
            remove:function(){
            	var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
            	var nodes=treeObj.getSelectedNodes();
            	treeDemo.removeNode(nodes[0]);
            	
            },
            loaddate:function(){
            	var url = WEB_ROOT + '/demo/func/demoZTree/qryTreeAll2.do';
    			var qryParams = {};
    			alert("12");
    			treeDemo.loadData(url,qryParams);
            },
	}
	
}();

ruizhi.ExecWait(function() {
	ruizhi.demo.demoZTree.init();
});

//////////////////////////////////////////
//function定义