//////////////////////////////////////////
//单据类型
ruizhi.Package("ruizhi.reportManager");

ruizhi.reportManager.table = function() {
	var tableId;
	var NOW_DATE = new Date().format("yyyy-MM-dd");
	return {
		init : function(tableId_,msg,searchArgs,otherParams){
			debugger; 
			tableId = tableId_;
			var colNames = msg.title;
			var propertyNames = msg.columnName;
			ruizhi.reportManager.table.initTable(colNames,propertyNames,searchArgs,otherParams);
		},
		initTable : function(colNames,propertyNames,searchArgs,otherParams){
//			$("#" + tableId).bootstrapTable('destroy'); 
			$("#" + tableId).bootstrapTable({
				url : 	WEB_ROOT + "/reportManager/getReportData.do",
	            method: 'post', 					//请求方式（*）  
	            contentType: "application/x-www-form-urlencoded",
//	            locale:'zh-CN',						//中文支持
	            striped: true,                      //是否显示行间隔色  
	            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）  
	            pagination: true,                   //是否显示分页（*）  
	            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）  
	            pageNumber:1,                       //初始化加载第一页，默认第一页  
	            pageSize: 50,                       //每页的记录行数（*）  
	            pageList: [50, 100, 150,"All"],     //可供选择的每页的行数（*）  
	            showColumns: true,                  //是否显示所有的列  
	            clickToSelect: true,                //是否启用点击选中行  
	            strictSearch: true,
	            queryParamsType : "",
	            columns: ruizhi.reportManager.table.createCols(colNames,propertyNames,otherParams),  
	            queryParams: function queryParams(params) {  //传递参数（*），这里应该返回一个object，即形如{param1:val1,param2:val2}  
	            	debugger;
	            	var searchParam = {}; 
	            	if(searchArgs != null && searchArgs != ''){
	            		var formId = searchArgs.formId;
	            		var fileName = searchArgs.fileName;
	            		var reportForm = new ruizhi.FormExt(formId);
	            		searchParam = reportForm.formToObject();
	            		var createTime = searchParam.createTime;
	            		var createTimeEnd = searchParam.createTimeEnd;
	            		if(ruizhi.IsNull(createTime)){
	            			createTime = ruizhi.reportManager.table.get3MonthBefor();
	            		}
	            		if(ruizhi.IsNull(createTimeEnd)){
	            			createTimeEnd = NOW_DATE;
	            		}
	            		searchParam.createTime = createTime;
	            		searchParam.createTimeEnd = createTimeEnd;
	            		searchParam.fileName = fileName;
	            		searchParam.pageNumber = params.pageNumber;
	            		searchParam.pageSize = params.pageSize;
	            	}else{
	            		ruizhi.Alert("操作失败");
	            		return;
//	            		searchParam = {  
//                            pageNumber: params.pageNumber,    
//                            pageSize: params.pageSize
//                        }; 
	            	}
                    return searchParam; 
                  },
                  onLoadSuccess : function(data) {                                
                      //合并单元格
//                      ruizhi.reportManager.table.mergeCells(data.rows, otherParams, 1, $('#' + tableId));
//                	  if(!ruizhi.IsNull(otherParams) && otherParams.length > 0){
//                		  for(var i in otherParams){
//                			  var data = $('#' + tableId).bootstrapTable('getData', true);
//                			  var cellName = otherParams[i];
//                              ruizhi.reportManager.table.mergeCells(data, cellName, 1, $('#' + tableId));
//                		  }
//                	  }
                  },
	        });
		},
		/**
		 * 合并单元格
		 * @param data  原始数据（在服务端完成排序）
		 * @param fieldName 合并属性名称
		 * @param colspan  	合并列
		 * @param target    目标表格对象
		 */
		mergeCells : function(data,othereParams,colspan,target){
			debugger;
			var mergeCellNames = othereParams.mergeCellNames;
			if(ruizhi.IsNull(mergeCellNames)){
				return;
			}
			//声明一个map计算相同属性值在data对象出现的次数和
			var retultMap = null;
			for(var i in mergeCellNames){
				var fieldName = mergeCellNames[i];
				retultMap = ruizhi.reportManager.table.test(fieldName,retultMap,data,colspan,target);
			}
		},
		test : function(fieldName,sortMap,data,colspan,target){
			if(sortMap == null || sortMap.length < 0){
				//声明一个map计算相同属性值在data对象出现的次数和
				var countMap = {};
				for(var i = 0; i < data.length ; i++){
					for(var prop in data[i]){
						if(prop == fieldName){
							var key = data[i][prop]
							if(countMap.hasOwnProperty(key)){
								countMap[key] = countMap[key] * 1 + 1;
							} else {
								countMap[key] = 1;
							}
							break;
						} 
					}
				}
				var index = 0;
				for(var key in countMap){
					var count = countMap[key] * 1;
					$(target).bootstrapTable('mergeCells',{index:index, field:fieldName, colspan: colspan, rowspan: count});   
					index += count;
				}
				return countMap;
			}else{
				//声明一个map计算相同属性值在data对象出现的次数和
				var sortMap2 = {};
				var start = 0;
				var length = 0;
				for(var prop2 in sortMap){
					var rows = sortMap[prop2] * 1;
					length += rows;
					for(var j = start ; j < length ; j++){
						for(var prop in data[j]){
							if(prop == fieldName){
								var key = data[j][prop]
								if(sortMap2.hasOwnProperty(key)){
									sortMap2[key] = sortMap2[key] * 1 + 1;
								} else {
									sortMap2[key] = 1;
								}
								break;
							} 
						}
					}
					start += rows;
				}
				var index2 = 0;
				for(var prop3 in sortMap2){
					var count2 = sortMap2[prop3] * 1;
					$(target).bootstrapTable('mergeCells',{index:index2, field:fieldName, colspan: colspan, rowspan: count2});   
					index2 += count2;
				}
				return sortMap2;
			}
		},
		/**
		 * 合并单元格
		 * @param data  原始数据（在服务端完成排序）
		 * @param fieldName 合并属性名称
		 * @param colspan  	合并列
		 * @param target    目标表格对象
		 */
		mergeCells3 : function(data,othereParams,colspan,target){
			debugger;
			var mergeCellNames = othereParams.mergeCellNames;
			//声明一个map计算相同属性值在data对象出现的次数和
			var sortMap = {};
			for(var i = 0 ; i < data.length ; i++){
				for(var prop in data[i]){
					if(prop == fieldName){
						var key = data[i][prop]
						if(sortMap.hasOwnProperty(key)){
							sortMap[key] = sortMap[key] * 1 + 1;
						} else {
							sortMap[key] = 1;
						}
						break;
					} 
				}
			}
			for(var prop1 in sortMap){
				console.log(prop1,sortMap[prop1])
			}
			var index = 0;
			for(var prop2 in sortMap){
				var count = sortMap[prop2] * 1;
				$(target).bootstrapTable('mergeCells',{index:index, field:fieldName, colspan: colspan, rowspan: count});   
				index += count;
			}
		},
		//获取当前时间前三个月的时间
		get3MonthBefor : function(){
		    var resultDate,year,month,date,hms;
		    var currDate = new Date();
		    year = currDate.getFullYear();
		    month = currDate.getMonth()+1;
		    date = currDate.getDate();
		    hms = currDate.getHours() + ':' + currDate.getMinutes() + ':' + (currDate.getSeconds() < 10 ? '0'+currDate.getSeconds() : currDate.getSeconds());
		    switch(month)
		    {
		      case 1:
		      case 2:
		      case 3:
		        month += 9;
		        year--;
		        break;
		      default:
		        month -= 3;
		        break;
		    }
		    month = (month < 10) ? ('0' + month) : month;
		    resultDate = year + '-'+month+'-'+date;
		  return resultDate;
		},
		createCols : function(colNames,propertyNames,otherParams){
			if(colNames.length != propertyNames.length){
				return null;  
			}  
	        var arr = [];  
	        for(var i = 0;i < colNames.length; i++){  
	            var obj = {};  
	            obj.field = propertyNames[i];  
	            obj.title = colNames[i]; 
	            if(otherParams != null && otherParams != ""){
	            	var cellNames = otherParams.cellNames;
	            	if(cellNames != null && cellNames.length > 0){
	            		for(var j in cellNames){
	            			var cellName = cellNames[j];
	            			if(cellName == propertyNames[i]){
	            				obj.formatter = ruizhi.reportManager.table.funcCell;
	            			}
	            		}
	            	}
	            }
	            arr.push(obj);  
	        }  
	        return arr; 
		},
		//格式化
		funcCell : function(val,row,index){
			if(val == null || val == ""){
				return "<div style=\"font-family:'微软雅黑'\"></div>";
			}else if(val.length > 15) {
		        return "<div style=\"font-family:'微软雅黑'\" title='" + val + "'><a>" + val.substring(0,10) + "...</a></div>";
		    } else {
		    	return "<div style=\"font-family:'微软雅黑'\">" + val + "</div>";
		    }
		},
		/**
		 * 合并单元格
		 * @param data  原始数据（在服务端完成排序）
		 * @param fieldName 合并属性名称
		 * @param colspan  	合并列
		 * @param target    目标表格对象
		 */
		mergeCells2 : function(data,mergeCellsNames,colspan,target){
      	    if(!ruizhi.IsNull(mergeCellsNames) && mergeCellsNames.length > 0){
      	    	console.info("合并参数为null");
      	    	return;
      	    }
      	    var fieldName = mergeCellsNames[0];
			debugger;
		    //声明一个map计算相同属性值在data对象出现的次数和
		    var sortMap = {};
		    for(var i = 0 ; i < data.length ; i++){
		        for(var prop in data[i]){
		            if(prop == fieldName){
		                var key = data[i][prop]
		                if(sortMap.hasOwnProperty(key)){
		                    sortMap[key] = sortMap[key] * 1 + 1;
		                } else {
		                    sortMap[key] = 1;
		                }
		                break;
		            } 
		        }
		    }
		    var sortMap2 = {};
		    var index = 0;
		    for(var prop1 in sortMap){
		        var count = sortMap[prop1] * 1;//fieldName : count
		        $(target).bootstrapTable('mergeCells',{index:index, field:fieldName, colspan: colspan, rowspan: count});   
		        index += count;
		        //合并后面参数
		        if(count > 1 && mergeCellsNames.length > 1){
		        	var fieldName2 = mergeCellsNames[1];
		        	sortMap2 = {};
		        	for(var k = 0 ; k < index; k++){
						for(var prop3 in data[k]){
							if(prop3 == fieldName2){
								var key3 = data[k][prop3]
								if(sortMap2.hasOwnProperty(key3)){
									sortMap2[key3] = sortMap2[key3] * 1 + 1;
								} else {
									sortMap2[key3] = 1;
								}
								break;
							} 
						}
					}
		        }
		    }
		},
	}
}();

