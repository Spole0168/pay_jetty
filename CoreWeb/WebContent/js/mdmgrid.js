
/**********************************************************
* 
* 验证时间区域是否合法
* stime 开始日期
* etime 结束日期
* 验证开始日期是否在结束日期之前，是返回true,否返回false
*
**********************************************************/
$.validator.addMethod("codeExpValidator",function(value,element){
		return (/^[a-z0-9A-Z]*$/).test(value);
	},"请输入数字或字母");

$.validator.addMethod("englishExpValidator",function(value,element){
	return (/^[a-zA-Z]*$/).test(value);
},"请输入英文字母");

$.validator.addMethod("unusualExpValidator",function(value,element){
	return (/^[a-zA-Z]*$/).test(value);
},"请输入英文字母");

$.validator.addMethod("nameMdmExpValidator",function(value,element){
	var check1=(/^[\uFF00-\uFFFF\u0391-\uFFE5-0-9]+$/).test(value); 
	return check1;
},"请输入中文或者数字");
function isValidPeriod(stime, etime) {
	if(stime==null||stime==""||etime==null||etime=="")
	{
		return true;
	}
	var sy = parseInt(getTimeByKey(1, stime));
	var sm = parseInt(getTimeByKey(2, stime));
	var sd = parseInt(getTimeByKey(3, stime));
	var ey = parseInt(getTimeByKey(1, etime));
	var em = parseInt(getTimeByKey(2, etime));
	var ed = parseInt(getTimeByKey(3, etime));
	if (sy == ey) {
		if (sm == em) {
			if (sd < ed) {
				return true;
			} else {
				return false;
			}
		} else {
			if (sm < em) {
				return true;
			} else {
				return false;
			}
		}
	} else {
		if (sy < ey) {
			return true;
		} else {
			return false;
		}
	}
}

/**********************************************************
* 按位置获取日期的值
* 如stime = 2010-10-01
* getTimeByKey(1,stime) = 2010
* getTimeByKey(2,stime) = 10
* getTimeByKey(3,stime) = 1
**********************************************************/
function getTimeByKey(key, stime) {
	var num = key;
	var str = stime;
	var returnStr = "";
	while (num > 0) {
		if (str.indexOf("-") != -1) {
			returnStr = str.substr(0, str.indexOf("-"));
			str = str.substr(str.indexOf("-") + 1);
		} else {
			returnStr = str;
		}
		num--;
	}
	while (returnStr.length > 1) {
		if (returnStr.substr(0, 1) == "0") {
			returnStr = returnStr.substr(1);
		} else {
			break;
		}
	}
	return returnStr;
}

function isValidOrgFunction(orgType, orgFunction) {
	 
 
	 
	
	if(containsContent(orgFunction,"SETTLEMENT"))
	{
		if(orgType!="MANAGE_AREA")
		{
			alert("只有管理区才能设置‘结算中心’职能");
			return false;
		} 
		
	}
	 
	
	if(containsContent(orgFunction,"SETTLEMENTORG"))
	{
		if(orgType!="BRANCH"&&orgType!="TRANSFER_CENTER")
		{
			alert("只有分公司和转运中心才能设置‘结算网点’职能");
			return false;
		} 
		
	}
	 
	return true;
	
	

}
function containsContent(srcIDs, id){
	if(srcIDs.indexOf("|"+id+"|")!=-1){
		return true;
	}else if(id == srcIDs){
		return true;
	}else if(srcIDs.indexOf(id+"|")==0){
		return true;
	}else if(srcIDs.substring(srcIDs.lastIndexOf("|") + 1, srcIDs.length)==id){
		return true;
	}
	return false;
}

//构造简单表
function buildSimpleGrid(simpleGrid)
{
		//表格变量
		var gridVar = "#" + simpleGrid.gridName;
		var formVar = "#" + simpleGrid.formName;
		var pagerVar = "#" + simpleGrid.pagerName;
		
		var searchButtonVar = "#" + simpleGrid.searchButtonName;
		var deleteButtonVar = "#" + simpleGrid.deleteButtonName;
		var createButtonVar = "#" + simpleGrid.createButtonName;
		var modifyButtonVar = "#" + simpleGrid.modifyButtonName;
		var detailButtonVar = "#" + simpleGrid.detailButtonName;
		var level2Head = simpleGrid.level2Head;
		var columnButtonVar = simpleGrid.columnButtonVar;
		var exportButtonVar = "#" + simpleGrid.exportButtonName;
		var exportUrl = simpleGrid.exportUrl;
		
		var searchParameter = simpleGrid.searchParameter;
		
		var modifyButtonText = "修改";
		if(simpleGrid.modifyButtonText != null) {
			modifyButtonText = simpleGrid.modifyButtonText;
		}
		
		var detailButtonText = "明细";
		if(simpleGrid.detailButtonText != null) {
			detailButtonText = simpleGrid.detailButtonText;
		}
		
		var pageCacheVar = "false";
		if($('#pageCache') != null && $('#pageCache').attr('value') == 'true') {
			pageCacheVar = "true";
		}
		var url;
		if(simpleGrid.searchUrl.indexOf("?") >=0){
			url = simpleGrid.searchUrl+'&pageCache=' + pageCacheVar + searchParameter;
		} else {
			url = simpleGrid.searchUrl+'?pageCache=' + pageCacheVar + searchParameter;
		}

		$(gridVar).jqGrid({
		   	url:url,
			datatype: "json",
			mtype: "POST",
			sortable: true, 
			rownumbers: true,
		   	colNames:simpleGrid.colNames,
		   	colModel:simpleGrid.colModel,
		   	rowNum:10,
		   	rowList:[10,50,100],
		   	//导航条对象
		   	pager: pagerVar,
		   	//默认的排序字段名称
		   	sortname: simpleGrid.sortName,
		    //默认的排序方式,可选值asc或desc
		   	sortorder: simpleGrid.sortOrder,
		   	//自动宽度
		   	autowidth:true,
		   	//高度
		   	height: 'auto',
		   	//是否多选
		   	multiselect:true,
		   	//multiboxonly设为ture后，点击其它没有被选中的行，那么点中的行被选择，其它行取消选择
		   	multiboxonly:true,
		   	//是否隐藏
		   	hiddengrid: false,
		   	//显示斑马线
			altRows:true,
			altclass:'altClass',
		   	//添加Toolbar
		   	toolbar: [true,"top"],
		   	//是否看到总条目
		    viewrecords: true,
		    //设置编辑的URL地址，此处只用于删除
		    editurl:simpleGrid.deleteUrl, 
		    //
		    //定义json数据的格式描述信息
		    jsonReader: {
	      		root: "rows",
	      		page: "page",
	     		total: "total",
	     		records: "records", 
	        	repeatitems : false
	     	},
	     	loadError : function(xhr,st,err) {
	     		alert('err:' + err);
	        	$("#tblMasterMessage").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText);
	    	},
	    	//onPaging : function(pgButton){
	    	//	$(searchButtonVar).click();
	    	//},
	    	//标题
		    caption:simpleGrid.caption,
		    
		    //加载结束后处理,处理session过期
		    loadComplete: function(data){
	    		if(data.success=="false"){
	    			alert(data.text);
	    		}	
	    	},
		    //增加操作列	    	
		    gridComplete: function(){
	    		
                var ids = $(gridVar).getDataIDs();//jqGrid('getDataIDs');
                for(var i=0;i<ids.length;i++){
                    var id = ids[i];
                    modifyButton = "<a onclick='window.location.href=\"" + simpleGrid.modifyUrl + "?id=" + id + "\"' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">"+modifyButtonText+"</span></span></a>";
                    if(simpleGrid.detailUrl != null && simpleGrid.detailUrl.indexOf("?") > 0){
                    	detailButton = "<a onclick='window.location.href=\"" + simpleGrid.detailUrl + "&superId=" + id + "\"' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">"+detailButtonText+"</span></span></a>";
                    } else {
                    	 detailButton = "<a onclick='window.location.href=\"" + simpleGrid.detailUrl + "?superId=" + id + "\"' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">"+detailButtonText+"</span></span></a>";
                    }
                    
                    if( simpleGrid.detailUrl == null || simpleGrid.detailUrl == "" ) {
                	   $(gridVar).jqGrid('setRowData',id,{operation:modifyButton});
                   } else {
                       $(gridVar).jqGrid('setRowData',id,{operation:modifyButton+detailButton});
                   }             
                } 
                //增加二级表头
                if(level2Head!=null&&level2Head.length>0){
	        	    var divElement = $("#gbox_"+subGrid.gridName);
	    			var tableElement=divElement.find("table");
	
	    			var tempTableHtml = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" aria-labelledby=\"gbox_itemListGrid\" role=\"grid\" style=\"width: 800px;\" class=\"ui-jqgrid-htable\"><thead><tr role=\"rowheader\" class=\"ui-jqgrid-labels\" id=\"pro_header\"><th style=\"width: 25px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th><th style=\"width: 20px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th><th style=\"width: 20px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th>";
	    			
	    		    for(var i=0;i<level2Head.length;i++){
	    		    	var th = level2Head[i];
	    		    	if(th==null)break;
	    		    	if(th.colspan!=null && (th.colspan!=1 || th.colspan!=0)){
	    		    		tempTableHtml+="<th colspan=\""+th.colspan+"\" style=\"text-align: center; width: "+th.width+";\" class=\"ui-state-default ui-th-ltr\">"+th.name+"</th>";
	    		    	}else{
	    		    		tempTableHtml+="<th style=\"text-align: center; width: "+th.width+";\" class=\"ui-state-default ui-th-ltr\">"+th.name+"</th>";
	    		    	}
	    		    }
	    			tempTableHtml+="</tr></thead></table>";
	    			tableElement.eq(0).before(tempTableHtml);
                }             
            }
	    	
		});
		$("#toolbar").appendTo($("#t_"+simpleGrid.gridName));
		// 添加分页导航条
		$(gridVar).jqGrid("navGrid",pagerVar,{edit:false,add:false,del:false,search:false,refresh:true});
		//添加/删除列
		//$(gridVar).jqGrid('navButtonAdd',pagerVar,{
		//	caption: columnButtonVar,
		//	title: columnButtonVar,
		//	onClickButton : function (){ jQuery(gridVar).jqGrid('columnChooser'); } 
		//});
		//排序
		//$(gridVar).jqGrid('sortableRows'); 
		
		// 删除 ajax
		$(deleteButtonVar).click(function(){
			var selectIds = $(gridVar).jqGrid('getGridParam','selarrrow');
			if (selectIds!=null && selectIds!="")	{
				$(gridVar).jqGrid('delGridRow',selectIds,{ajaxDelOptions:{dataType:"JSON"} 		 
				});
				
			} else{
				alert('请选择实例记录!');
			}
		});
		
		// 查询 ajax
		$(searchButtonVar).click(function(){ 
			 
			$(gridVar).jqGrid('clearGridData');
			
			var url ;
			
			if(simpleGrid.searchUrl.indexOf("?") >=0){
				url = simpleGrid.searchUrl + "&rowNum=" + $(gridVar).jqGrid('getGridParam','rowNum');
			} else {
				url = simpleGrid.searchUrl + "?rowNum=" + $(gridVar).jqGrid('getGridParam','rowNum');
			}
			    	
			var postData = new Object();
			for(var i = 0 ; i < simpleGrid.searchParameters.length; i++) {
				var name = simpleGrid.searchParameters[i];
				var currentValue = $("#" + simpleGrid.searchParameters[i]).attr('value');
				
			    eval("postData."+name+"=currentValue");
			}
			$(gridVar).appendPostData(postData);
			
			$(gridVar).jqGrid("setGridParam",{url:url}).trigger("reloadGrid");
			
			// 修正分页 如:当前浏览第2页,输入查询条件后查询结果共1页,jqgrid仍然会停留在第2页,需要手工翻到第1页
			$(gridVar).jqGrid("setGridParam",{page:1});
		});
		
		// 导出
		$(exportButtonVar).click(function(){
//			var url = exportUrl + "?rowNum=" + $(gridVar).jqGrid('getGridParam','rowNum');
	    	var url = exportUrl;
			//准备post数据
			var postData = new Object();
			
			//当前导出参数
			for(var i = 0 ; i < simpleGrid.exportParameters.length; i++) {
				var name = simpleGrid.exportParameters[i];
				var currentValue = $("#" + simpleGrid.exportParameters[i]).attr('value');
				
			    eval("postData."+name+"=currentValue");
			}
			
			//导出文件名
			var fileName = encodeURI(simpleGrid.exportFileName);
			eval("postData.fileName=fileName");
			
			$(gridVar).appendPostData(postData);
			$(gridVarId).gridUtil().exportExcel({url: exportUrl});

		});		

		// 增加 页面跳转
		$(createButtonVar).click(function(){
			window.location.href=simpleGrid.createUrl;
		});
}	

function buildPopupGrid(simpleGrid)
{
		//表格变量
		var gridVar = "#" + simpleGrid.gridName;
		var formVar = "#" + simpleGrid.formName;
		var pagerVar = "#" + simpleGrid.pagerName;
		
		var searchButtonVar = "#" + simpleGrid.searchButtonName;
		var deleteButtonVar = "#" + simpleGrid.deleteButtonName;
		var createButtonVar = "#" + simpleGrid.createButtonName;
		var modifyButtonVar = "#" + simpleGrid.modifyButtonName;
		var modifyFunctionName = simpleGrid.modifyFunctionName;
		var detailFunctionName = simpleGrid.detailFunctionName;
		var detailButtonVar = "#" + simpleGrid.detailButtonName;
		var level2Head = simpleGrid.level2Head;
		var columnButtonVar = simpleGrid.columnButtonVar;
		var exportButtonVar = "#" + simpleGrid.exportButtonName;
		var exportUrl = simpleGrid.exportUrl;
		
		var searchParameter = simpleGrid.searchParameter;
		
		var modifyButtonText = "修改";
		if(simpleGrid.modifyButtonText != null) {
			modifyButtonText = simpleGrid.modifyButtonText;
		}
		
		var detailButtonText = "明细";
		if(simpleGrid.detailButtonText != null) {
			detailButtonText = simpleGrid.detailButtonText;
		}
		
		var pageCacheVar = "false";
		if($('#pageCache') != null && $('#pageCache').attr('value') == 'true') {
			pageCacheVar = "true";
		}
		var url;
		if(simpleGrid.searchUrl.indexOf("?") >=0){
			url = simpleGrid.searchUrl+'&pageCache=' + pageCacheVar + searchParameter;
		} else {
			url = simpleGrid.searchUrl+'?pageCache=' + pageCacheVar + searchParameter;
		}

		$(gridVar).jqGrid({
		   	url:url,
			datatype: "json",
			mtype: "POST",
			sortable: true, 
			rownumbers: true,
		   	colNames:simpleGrid.colNames,
		   	colModel:simpleGrid.colModel,
//		   	rowNum:10,
//		   	rowList:[10,50,100],
		   	rowNum:50,
		   	rowList:[50,100,200],
		   	//导航条对象
		   	pager: pagerVar,
		   	//默认的排序字段名称
		   	sortname: simpleGrid.sortName,
		    //默认的排序方式,可选值asc或desc
		   	sortorder: simpleGrid.sortOrder,
		   	//自动宽度
		   	autowidth:true,
		   	//高度
		   	height: 'auto',
		   	//是否多选
		   	multiselect:true,
		   	//multiboxonly设为ture后，点击其它没有被选中的行，那么点中的行被选择，其它行取消选择
		   	multiboxonly:true,
		   	//是否隐藏
		   	hiddengrid: false,
		   	//显示斑马线
			altRows:true,
			altclass:'altClass',
		   	//添加Toolbar
		   	toolbar: [true,"top"],
		   	//是否看到总条目
		    viewrecords: true,
		    //设置编辑的URL地址，此处只用于删除
		    editurl:simpleGrid.deleteUrl, 
		    //
		    //定义json数据的格式描述信息
		    jsonReader: {
	      		root: "rows",
	      		page: "page",
	     		total: "total",
	     		records: "records", 
	        	repeatitems : false
	     	},
	     	loadError : function(xhr,st,err) {
	     		alert('err:' + err);
	        	$("#tblMasterMessage").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText);
	    	},
	    	//onPaging : function(pgButton){
	    	//	$(searchButtonVar).click();
	    	//},
	    	//标题
		    caption:simpleGrid.caption,
		    
		    //加载结束后处理,处理session过期
		    loadComplete: function(data){
	    		if(data.success=="false"){
	    			alert(data.text);
	    		}	
	    	},
		    //增加操作列	    	
		    gridComplete: function(){
	    		
                var ids = $(gridVar).getDataIDs();//jqGrid('getDataIDs');
                for(var i=0;i<ids.length;i++){
                    var id = ids[i];
                    modifyButton = "<a onclick='" + modifyFunctionName + "(\"" + id + "\")' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">"+modifyButtonText+"</span></span></a>";
                    detailButton = "<a onclick='" + detailFunctionName + "(\"" + id + "\")' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">"+detailButtonText+"</span></span></a>";
                    
//                    if(simpleGrid.detailUrl != null && simpleGrid.detailUrl.indexOf("?") >= 0){
//                    	detailButton = "<a onclick='window.location.href=\"" + simpleGrid.detailUrl + "&superId=" + id + "\"' href=\"#\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">"+detailButtonText+"</span></span></a>";
//                    } else {
//                    	 detailButton = "<a onclick='window.location.href=\"" + simpleGrid.detailUrl + "?superId=" + id + "\"' href=\"#\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">"+detailButtonText+"</span></span></a>";
//                    }
                    
                    if( simpleGrid.detailUrl == null || simpleGrid.detailUrl == "" ) {
                	   $(gridVar).jqGrid('setRowData',id,{operation:modifyButton});
                   } else {
                       $(gridVar).jqGrid('setRowData',id,{operation:modifyButton+detailButton});
                   }             
                } 
                //增加二级表头
                if(level2Head!=null&&level2Head.length>0){
	        	    var divElement = $("#gbox_"+subGrid.gridName);
	    			var tableElement=divElement.find("table");
	
	    			var tempTableHtml = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" aria-labelledby=\"gbox_itemListGrid\" role=\"grid\" style=\"width: 800px;\" class=\"ui-jqgrid-htable\"><thead><tr role=\"rowheader\" class=\"ui-jqgrid-labels\" id=\"pro_header\"><th style=\"width: 25px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th><th style=\"width: 20px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th><th style=\"width: 20px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th>";
	    			
	    		    for(var i=0;i<level2Head.length;i++){
	    		    	var th = level2Head[i];
	    		    	if(th==null)break;
	    		    	if(th.colspan!=null && (th.colspan!=1 || th.colspan!=0)){
	    		    		tempTableHtml+="<th colspan=\""+th.colspan+"\" style=\"text-align: center; width: "+th.width+";\" class=\"ui-state-default ui-th-ltr\">"+th.name+"</th>";
	    		    	}else{
	    		    		tempTableHtml+="<th style=\"text-align: center; width: "+th.width+";\" class=\"ui-state-default ui-th-ltr\">"+th.name+"</th>";
	    		    	}
	    		    }
	    			tempTableHtml+="</tr></thead></table>";
	    			tableElement.eq(0).before(tempTableHtml);
                }             
            }
	    	
		});
		$("#toolbar").appendTo($("#t_"+simpleGrid.gridName));
		// 添加分页导航条
		$(gridVar).jqGrid("navGrid",pagerVar,{edit:false,add:false,del:false,search:false,refresh:true});
		//添加/删除列
		//$(gridVar).jqGrid('navButtonAdd',pagerVar,{
		//	caption: columnButtonVar,
		//	title: columnButtonVar,
		//	onClickButton : function (){ jQuery(gridVar).jqGrid('columnChooser'); } 
		//});
		//排序
		//$(gridVar).jqGrid('sortableRows'); 
		
		// 删除 ajax
		$(deleteButtonVar).click(function(){
			var selectIds = $(gridVar).jqGrid('getGridParam','selarrrow');
			if (selectIds!=null && selectIds!="")	{
				$(gridVar).jqGrid('delGridRow',selectIds,{drag:false,
					afterSubmit:function(resp, postdata){
					var result = $.parseJSON(resp.responseText);
					if(result.success == 'false'){
						alert(result.text);		
						return [true,"",""];
					} else {
						return [true,"",""];
					}
				}
				});
			
				
			}else{
				alert('请选择实例记录!');
			}
		});
		
		// 查询 ajax
		$(searchButtonVar).click(function(){ 
			 
			$(gridVar).jqGrid('clearGridData');
			
			var url ;
			
			if(simpleGrid.searchUrl.indexOf("?") >=0){
				url = simpleGrid.searchUrl + "&rowNum=" + $(gridVar).jqGrid('getGridParam','rowNum');
			} else {
				url = simpleGrid.searchUrl + "?rowNum=" + $(gridVar).jqGrid('getGridParam','rowNum');
			}
			    	
			var postData = new Object();
			for(var i = 0 ; i < simpleGrid.searchParameters.length; i++) {
				var name = simpleGrid.searchParameters[i];
				var currentValue = $("#" + simpleGrid.searchParameters[i]).attr('value');
				
			    eval("postData."+name+"=currentValue");
			}
			$(gridVar).appendPostData(postData);
			
			$(gridVar).jqGrid("setGridParam",{url:url}).trigger("reloadGrid");
			
			// 修正分页 如:当前浏览第2页,输入查询条件后查询结果共1页,jqgrid仍然会停留在第2页,需要手工翻到第1页
			$(gridVar).jqGrid("setGridParam",{page:1});
		});
		
		// 导出
		$(exportButtonVar).click(function(){
			var url = exportUrl;
			//准备post数据
			var postData = new Object();
			
			//当前导出参数
			for(var i = 0 ; i < simpleGrid.exportParameters.length; i++) {
				var name = simpleGrid.exportParameters[i];
				var currentValue = $("#" + simpleGrid.exportParameters[i]).attr('value');
				
			    eval("postData."+name+"=currentValue");
			}
			
			//导出文件名
			var exportFileName = encodeURI(simpleGrid.exportFileName);
			eval("postData.exportFileName=exportFileName");
			
			//导出Excel列头
			for(var i = 0 ; i < simpleGrid.exportColNames.length; i++) {
				var name = simpleGrid.exportColNames[i];
			    eval("postData.exportColNames"+i+"=name");
			}
			
			//导出Excel列值
			for(var i = 0 ; i < simpleGrid.exportColModels.length; i++) {
				var model = simpleGrid.exportColModels[i];
			    eval("postData.exportColModels"+i+"=model");
			}	
			
			$(gridVar).appendPostData(postData);
			$(gridVar).gridUtil().exportExcel({url: exportUrl});

		});		

		// 增加 页面跳转
		$(createButtonVar).click(function(){
			
		});
}
//构造航班信息表
function buildFlightGrid(simpleGrid)
{
		//表格变量
		var gridVar = "#" + simpleGrid.gridName;
		var formVar = "#" + simpleGrid.formName;
		var pagerVar = "#" + simpleGrid.pagerName;
		
		var searchButtonVar = "#" + simpleGrid.searchButtonName;
		var deleteButtonVar = "#" + simpleGrid.deleteButtonName;
		var createButtonVar = "#" + simpleGrid.createButtonName;
		var modifyButtonVar = "#" + simpleGrid.modifyButtonName;
		var level2Head = simpleGrid.level2Head;
		var columnButtonVar = simpleGrid.columnButtonVar;
		var exportButtonVar = "#" + simpleGrid.exportButtonName;
		var exportUrl = simpleGrid.exportUrl;
		
		var modifyButtonText = "修改";
		if(simpleGrid.modifyButtonText != null) {
			modifyButtonText = simpleGrid.modifyButtonText;
		}
		
		var pageCacheVar = "false";
		if($('#pageCache') != null && $('#pageCache').attr('value') == 'true') {
			pageCacheVar = "true";
		}
		
		$(gridVar).jqGrid({
		   	url:simpleGrid.searchUrl + '?pageCache=' + pageCacheVar,
			datatype: "json",
			mtype: "POST",
			sortable: true, 
			rownumbers: true,
		   	colNames:simpleGrid.colNames,
		   	colModel:simpleGrid.colModel,
		   	rowNum:10,
		   	rowList:[10,50,100],
		   	//导航条对象
		   	pager: pagerVar,
		   	//默认的排序字段名称
		   	sortname: simpleGrid.sortName,
		    //默认的排序方式,可选值asc或desc
		   	sortorder: simpleGrid.sortOrder,
		   	//自动宽度
		   	autowidth:true,
		   	//高度
		   	height: 'auto',
		   	//是否多选
		   	multiselect:true,
		   	//multiboxonly设为ture后，点击其它没有被选中的行，那么点中的行被选择，其它行取消选择
		   	multiboxonly:true,
		   	//是否隐藏
		   	hiddengrid: false,
		   	//显示斑马线
			altRows:true,
			altclass:'altClass',
		   	//添加Toolbar
		   	toolbar: [true,"top"],
		   	//是否看到总条目
		    viewrecords: true,
		    //设置编辑的URL地址，此处只用于删除
		    editurl:simpleGrid.deleteUrl, 
		    //
		    //定义json数据的格式描述信息
		    jsonReader: {
	      		root: "rows",
	      		page: "page",
	     		total: "total",
	     		records: "records", 
	        	repeatitems : false
	     	},
	     	loadError : function(xhr,st,err) {
	     		alert('err:' + err);
	        	$("#tblMasterMessage").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText);
	    	},
	    	onPaging : function(pgButton){
	    		$(searchButtonVar).click();
	    	},
	    	//标题
		    caption:simpleGrid.caption,
		    
		    //加载结束后处理,处理session过期
		    loadComplete: function(data){
	    		if(data.success=="false"){
	    			alert(data.text);
	    		}	
	    	},
		    //增加操作列	    	
		    gridComplete: function(){
	    		
                var ids = $(gridVar).getDataIDs();//jqGrid('getDataIDs');
                for(var i=0;i<ids.length;i++){
                    var id = ids[i];
                    var rowData = $(gridVar).getRowData(id);
                    
                	var allButton = null;

                	allButton = "<a onclick='window.location.href=\"" + simpleGrid.modifyUrl + "?id=" + id + "\"' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">"+modifyButtonText+"</span></span></a>";

                    var adjButton = null;
                    var endTime = rowData.endTime;
                    if(endTime != null){
                    	if(endTime.length < 2){
	                    	if(simpleGrid.adjButtonText!=null){
	                    		adjButton = "<a onclick='window.location.href=\"" + simpleGrid.adjUrl + "?id=" + id + "\"' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">"+simpleGrid.adjButtonText+"</span></span></a>";
	                    	}
	                    	allButton = allButton + adjButton;
	                    }
                    }
                    
                    var auditButton = null;
                    var revokeButton = null;
                    var status = rowData.flightAuditStatus;
                    if(status != null){
                    	if(status.length > 0 && status == "2"){
                    		if(simpleGrid.auditButtonText!=null){
                    			auditButton = "<a onclick='window.location.href=\"" + simpleGrid.auditUrl + "?id=" + id + "\"' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">"+simpleGrid.auditButtonText+"</span></span></a>";
                        		allButton = allButton + auditButton;
                    		}
                    	}else if(status.length > 0 && status == "4"){
                    		if(simpleGrid.revokeButtonText!=null){
                    			revokeButton = "<a onclick='window.location.href=\"" + simpleGrid.revokeUrl + "?id=" + id + "\"' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">"+simpleGrid.revokeButtonText+"</span></span></a>";
                        		allButton = allButton + revokeButton;
                    		}
                    	}
                    }

                    $(gridVar).jqGrid('setRowData',id,{operation:allButton});
                } 
                //增加二级表头
                if(level2Head!=null&&level2Head.length>0){
	        	    var divElement = $("#gbox_"+subGrid.gridName);
	    			var tableElement=divElement.find("table");
	
	    			var tempTableHtml = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" aria-labelledby=\"gbox_itemListGrid\" role=\"grid\" style=\"width: 800px;\" class=\"ui-jqgrid-htable\"><thead><tr role=\"rowheader\" class=\"ui-jqgrid-labels\" id=\"pro_header\"><th style=\"width: 25px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th><th style=\"width: 20px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th><th style=\"width: 20px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th>"
	    			
	    		    for(var i=0;i<level2Head.length;i++){
	    		    	var th = level2Head[i];
	    		    	if(th==null)break;
	    		    	if(th.colspan!=null && (th.colspan!=1 || th.colspan!=0)){
	    		    		tempTableHtml+="<th colspan=\""+th.colspan+"\" style=\"text-align: center; width: "+th.width+";\" class=\"ui-state-default ui-th-ltr\">"+th.name+"</th>";
	    		    	}else{
	    		    		tempTableHtml+="<th style=\"text-align: center; width: "+th.width+";\" class=\"ui-state-default ui-th-ltr\">"+th.name+"</th>";
	    		    	}
	    		    }
	    			tempTableHtml+="</tr></thead></table>";
	    			tableElement.eq(0).before(tempTableHtml);
                }             
            }
	    	
		});
		$("#gridToolbar").appendTo($("#t_"+simpleGrid.gridName));
		// 添加分页导航条
		$(gridVar).jqGrid("navGrid",pagerVar,{edit:false,add:false,del:false,search:false,refresh:true});
		//添加/删除列
		//$(gridVar).jqGrid('navButtonAdd',pagerVar,{
		//	caption: columnButtonVar,
		//	title: columnButtonVar,
		//	onClickButton : function (){ jQuery(gridVar).jqGrid('columnChooser'); } 
		//});
		//排序
		//$(gridVar).jqGrid('sortableRows'); 
		
		// 删除 ajax
		$(deleteButtonVar).click(function(){
			var selectIds = $(gridVar).jqGrid('getGridParam','selarrrow');
			if (selectIds!=null && selectIds!="")	{
				$(gridVar).jqGrid('delGridRow',selectIds,{drag:false,
					afterSubmit:function(resp, postdata){
						var result = JSON.parse(resp.responseText);
						
						if(result.success == 'false'){
							alert('Delete failed:' + result.text);		
							return [true,"",""];
						} else {
						    //alert('Delete is succefully');	
							return [true,"",""];
						}
						
					}
				});
				
			} else{
				alert('请选择实例记录!');
			}
		});
		
		// 查询 ajax
		$(searchButtonVar).click(function(){
			var url = simpleGrid.searchUrl + "?rowNum=" + $(gridVar).jqGrid('getGridParam','rowNum');
	    	
			var postData = new Object();
			for(var i = 0 ; i < simpleGrid.searchParameters.length; i++) {
				var name = simpleGrid.searchParameters[i];
				var currentValue = $("#" + simpleGrid.searchParameters[i]).attr('value');
				
			    eval("postData."+name+"=currentValue");
			}
			$(gridVar).appendPostData(postData);
			
			$(gridVar).jqGrid("setGridParam",{url:url}).trigger("reloadGrid");
			
			// 修正分页 如:当前浏览第2页,输入查询条件后查询结果共1页,jqgrid仍然会停留在第2页,需要手工翻到第1页
			$(gridVar).jqGrid("setGridParam",{page:1})
		});
		
		// 导出
		$(exportButtonVar).click(function(){
			var url = exportUrl + "?rowNum=" + $(gridVar).jqGrid('getGridParam','rowNum');
	    	
			//准备post数据
			var postData = new Object();
			
			//当前导出参数
			for(var i = 0 ; i < simpleGrid.exportParameters.length; i++) {
				var name = simpleGrid.exportParameters[i];
				var currentValue = $("#" + simpleGrid.exportParameters[i]).attr('value');
				
			    eval("postData."+name+"=currentValue");
			}
			
			//导出Excel列头
			for(var i = 0 ; i < simpleGrid.exportColNames.length; i++) {
				var name = simpleGrid.exportColNames[i];
			    eval("postData.exportColNames"+i+"=name");
			}
			
			//导出Excel列值
			for(var i = 0 ; i < simpleGrid.exportColModels.length; i++) {
				var model = simpleGrid.exportColModels[i];
			    eval("postData.exportColModels"+i+"=model");
			}	
			
			//导出文件名
			var fileName = simpleGrid.exportFileName;
			eval("postData.fileName=fileName");
			
			$(gridVar).appendPostData(postData);
			postData = $(gridVar).getPostData(postData);

			//调用下载方法，通过post方式发送参数到下载链接
			$.download(url, postData, "post", "");

		});		

		// 增加 页面跳转
		$(createButtonVar).click(function(){
			window.location.href=simpleGrid.createUrl;
		});
}	

//构造简单子表
function buildSubGrid(subGrid)
{
		//表格变量
		var gridVar = "#" + subGrid.gridName;
		var formVar = "#" + subGrid.formName;
		var pagerVar = "#" + subGrid.pagerName;
		
		var searchButtonVar = "#" + subGrid.searchButtonName;
		var deleteButtonVar = "#" + subGrid.deleteButtonName;
		var createButtonVar = "#" + subGrid.createButtonName;
		var modifyButtonVar = "#" + subGrid.modifyButtonName;
		var level2Head = subGrid.level2Head;
		var columnButtonVar = subGrid.columnButtonVar;
		$(gridVar).jqGrid({
		   	url:subGrid.searchUrl,
			datatype: "json",
			mtype: "POST",
			rownumbers: true,
		   	colNames:subGrid.colNames,
		   	colModel:subGrid.colModel,
		   	rowNum:10,
		   	rowList:[10,50,100],
		   	//导航条对象
		   	pager: pagerVar,
		   	//默认的排序字段名称
		   	sortname: subGrid.sortName,
		    //默认的排序方式,可选值asc或desc
		   	sortorder: subGrid.sortOrder,
		   	//自动宽度
		   	autowidth:true,
		   	//高度
		   	height: 'auto',
		   	//是否多选
		   	multiselect:true,
		   	//multiboxonly设为ture后，点击其它没有被选中的行，那么点中的行被选择，其它行取消选择
		   	multiboxonly:true,
		   	//是否隐藏
		   	hidegrid: false, 
		   	//添加toolbar
		   	toolbar: [true,"top"] ,
		   	//是否看到总条目
		    viewrecords: true,
		    //设置编辑的URL地址，此处只用于删除
		    editurl:subGrid.deleteUrl, 
		    //定义json数据的格式描述信息
		    jsonReader: {
	      		root: "rows",
	      		page: "page",
	     		total: "total",
	     		records: "records", 
	        	repeatitems : false,
	            subgrid: { 
			  		root: "rows",
			  		page: "page",
			 		total: "total",
			 		records: "records", 
			    	repeatitems : false
		      } 
		        		          	
	     	},
	     	loadError : function(xhr,st,err) {
	     		alert('err:' + err);
	        	$("#tblMasterMessage").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText);
	    	},
	    	onPaging : function(pgButton){
	    		$(searchButtonVar).click();
	    	},
	    	//标题
		    caption:subGrid.caption,
		    
		    //加载结束后处理,处理session过期
		    loadComplete: function(data){
	    		if(data.success=="false"){
	    			alert(data.text);
	    		}	
	    	},		    
		    subGrid: true,
			subGridUrl: subGrid.subGridUrl,
			subGridModel: subGrid.subGridModel, 		    
		    //增加操作列
		    gridComplete: function(){
                var ids = $(gridVar).getDataIDs();//jqGrid('getDataIDs');
                for(var i=0;i<ids.length;i++){
                    var id = ids[i];
                    modifyButton = "<a onclick='window.location.href=\"" + subGrid.modifyUrl + "?id=" + id + "\"' class=\"easyui-linkbutton l-btn\" href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">修 改</span></span></a>"
                    $(gridVar).jqGrid('setRowData',id,{operation:modifyButton});
                } 
                
                //增加二级表头
                if(level2Head!=null&&level2Head.length>0){
	        	    var divElement = $("#gbox_"+subGrid.gridName);
	    			var tableElement=divElement.find("table");
	
	    			var tempTableHtml = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" aria-labelledby=\"gbox_itemListGrid\" role=\"grid\" style=\"width: 800px;\" class=\"ui-jqgrid-htable\"><thead><tr role=\"rowheader\" class=\"ui-jqgrid-labels\" id=\"pro_header\"><th style=\"width: 25px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th><th style=\"width: 20px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th><th style=\"width: 20px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th>"
	    			
	    		    for(var i=0;i<level2Head.length;i++){
	    		    	var th = level2Head[i];
	    		    	if(th==null)break;
	    		    	if(th.colspan!=null && (th.colspan!=1 || th.colspan!=0)){
	    		    		tempTableHtml+="<th colspan=\""+th.colspan+"\" style=\"text-align: center; width: "+th.width+";\" class=\"ui-state-default ui-th-ltr\">"+th.name+"</th>";
	    		    	}else{
	    		    		tempTableHtml+="<th style=\"text-align: center; width: "+th.width+";\" class=\"ui-state-default ui-th-ltr\">"+th.name+"</th>";
	    		    	}
	    		    }
	    			tempTableHtml+="</tr></thead></table>";
	    			tableElement.eq(0).before(tempTableHtml);
                }              
            }
		});
		$("#toolbar").appendTo($("#t_itemListGrid"));
		// 添加分页导航条
		$(gridVar).jqGrid("navGrid",pagerVar,{edit:false,add:false,del:false,search:false,refresh:true});	
		
		//排序
		//$(gridVar).jqGrid('sortableRows'); 
		//添加/删除列
		$(gridVar).jqGrid('navButtonAdd',pagerVar,{
			caption: columnButtonVar,
			title: columnButtonVar,
			onClickButton : function (){ jQuery(gridVar).jqGrid('columnChooser'); } 
		});
		// 删除 ajax
		$(deleteButtonVar).click(function(){
			var selectIds = $(gridVar).jqGrid('getGridParam','selarrrow');
			if (selectIds!=null && selectIds!="")	{
				$(gridVar).jqGrid('delGridRow',selectIds,{drag:false,
					afterSubmit:function(resp, postdata){
						var result = JSON.parse(resp.responseText);
						
						if(result.success == 'false'){
							alert('Delete failed:' + result.text);		
							return [true,"",""]
						} else {
							// alert('Delete is succefully');	
							return [true,"",""]
						}
						
					}
				});
				
			} else{
				alert('请选择记录!');
			}
		});
		
		// 查询 ajax
		$(searchButtonVar).click(function(){
			var url = subGrid.searchUrl + "?rowNum=" + $(gridVar).jqGrid('getGridParam','rowNum');
	    	
			var postData = new Object();
			for(var i = 0 ; i < subGrid.searchParameters.length; i++) {
				var name = subGrid.searchParameters[i];
				var currentValue = $("#" + subGrid.searchParameters[i]).attr('value');
				
			    eval("postData."+name+"=currentValue");
			}
			$(gridVar).appendPostData(postData);
			
			$(gridVar).jqGrid("setGridParam",{url:url}).trigger("reloadGrid");
			
			// 修正分页 如:当前浏览第2页,输入查询条件后查询结果共1页,jqgrid仍然会停留在第2页,需要手工翻到第1页
			$(gridVar).jqGrid("setGridParam",{page:1})
		});
		
		// 增加页面跳转
		$(createButtonVar).click(function(){
			window.location.href=subGrid.createUrl;
		});
		
}	

//构造复杂子表
function buildComplexSubGrid(subGrid)
{
		//表格变量
		var gridVar = "#" + subGrid.gridName;
		var formVar = "#" + subGrid.formName;
		var pagerVar = "#" + subGrid.pagerName;
		var columnButtonVar = subGrid.columnButtonName;
		var searchButtonVar = "#" + subGrid.searchButtonName;
		var deleteButtonVar = "#" + subGrid.deleteButtonName;
		var createButtonVar = "#" + subGrid.createButtonName;
		var modifyButtonVar = "#" + subGrid.modifyButtonName;
		var columnButtonVar = subGrid.columnButtonVar;
		var level2Head = subGrid.level2Head;
		
		$(gridVar).jqGrid({
		   	url:subGrid.searchUrl,
			datatype: "json",
			mtype: "POST",
			rownumbers: true,
		   	colNames:subGrid.colNames,
		   	colModel:subGrid.colModel,
		   	rowNum:10,
		   	rowList:[10,50,100],
		   	//导航条对象
		   	pager: pagerVar,
		   	//默认的排序字段名称
		   	sortname: subGrid.sortName,
		    //默认的排序方式,可选值asc或desc
		   	sortorder: subGrid.sortOrder,
		   	//自动宽度
		   	autowidth:true,
		   	//高度
		   	height: 'auto',
		   	//是否多选
		   	multiselect:true,
		   	//multiboxonly设为ture后，点击其它没有被选中的行，那么点中的行被选择，其它行取消选择
		   	multiboxonly:true,
		   	//是否隐藏
		   	hidegrid: false, 
		   	//是否看到总条目
		    viewrecords: true,
		    //设置编辑的URL地址，此处只用于删除
		    editurl:subGrid.deleteUrl, 
		  //添加toolbar
		   	toolbar: [true,"top"] ,
		    //定义json数据的格式描述信息
		    jsonReader: {
	      		root: "rows",
	      		page: "page",
	     		total: "total",
	     		records: "records", 
	        	repeatitems : false
	     	},
	     	loadError : function(xhr,st,err) {
	     		alert('err:' + err);
	        	$("#tblMasterMessage").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText);
	    	},
	    	onPaging : function(pgButton){
	    		$(searchButtonVar).click();
	    	},
	    	//标题
		    caption:subGrid.caption,
		    
		    //加载结束后处理,处理session过期
		    loadComplete: function(data){
	    		if(data.success=="false"){
	    			alert(data.text);
	    		}	
	    	},		    
		    subGrid: true,
		    subGridRowExpanded: function(subgrid_id, row_id) {
	    	    // we pass two parameters
	    	    // subgrid_id is a id of the div tag created within a table
	    	    // the row_id is the id of the row
	    	    // If we want to pass additional parameters to the url we can use
	    	    // the method getRowData(row_id) - which returns associative array in type name-value
	    	    // here we can easy construct the following
	    	       var subgrid_table_id, pager_id; 
	    	       subgrid_table_id = subgrid_id+"_t";
	    	       pager_id = "p_"+subgrid_table_id; 
	    	       $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>"); 
	    	       jQuery("#"+subgrid_table_id).jqGrid({
	    	          url:subGrid.subGridUrl+"?id="+row_id,
	    		  		datatype: "json",
	    		  	    jsonReader: {
	    		        	root: "rows",
	    		        	page: "page",
	    		       		total: "total",
	    		       		records: "records", 
	    		          	repeatitems : false	          	
	    		       	},	          
	    		  	   	colNames: subGrid.subGridName,
	    			   	colModel: subGrid.subGridModel,
	    	          height: '100%',
	    	          width:'700',
	    	          rowNum:20,
	    	          pager: pager_id, 
	    	          sortname:'id',
	    	          sortorder:"asc"
	    	       });
	    	       jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:false,del:false,search:false})
	    	   },
	        	subGridRowColapsed: function(subgrid_id, row_id) { 
	    	       	// this function is called before removing the data 
	    	       	//var subgrid_table_id; 
	    	       	//subgrid_table_id = subgrid_id+"_t"; 
	    	       	//jQuery("#"+subgrid_table_id).remove(); 
	    	    }, 		    
		    //增加操作列
		    gridComplete: function(){
                var ids = $(gridVar).getDataIDs();//jqGrid('getDataIDs');
                for(var i=0;i<ids.length;i++){
                    var id = ids[i];
                    modifyButton = "<a onclick='window.location.href=\"" + subGrid.modifyUrl + "?id=" + id + "\"' class=\"easyui-linkbutton l-btn\" href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">修 改</span></span></a>"
                    $(gridVar).jqGrid('setRowData',id,{operation:modifyButton});
                } 
                //增加二级表头
                if(level2Head!=null&&level2Head.length>0){
	        	    var divElement = $("#gbox_"+subGrid.gridName);
	    			var tableElement=divElement.find("table");
	
	    			var tempTableHtml = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" aria-labelledby=\"gbox_itemListGrid\" role=\"grid\" style=\"width: 100%;\" class=\"ui-jqgrid-htable\"><thead><tr role=\"rowheader\" class=\"ui-jqgrid-labels\" id=\"pro_header\"><th style=\"width: 25px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th><th style=\"width: 20px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th><th style=\"width: 20px;\" class=\"ui-state-default ui-th-ltr\"><div id=\"jqgh_rn\"></div></th>"
	    			
	    		    for(var i=0;i<level2Head.length;i++){
	    		    	var th = level2Head[i];
	    		    	if(th==null)break;
	    		    	if(th.colspan!=null && (th.colspan!=1 || th.colspan!=0)){
	    		    		tempTableHtml+="<th colspan=\""+th.colspan+"\" style=\"text-align: center; width: "+th.width+";\" class=\"ui-state-default ui-th-ltr\">"+th.name+"</th>";
	    		    	}else{
	    		    		tempTableHtml+="<th style=\"text-align: center; width: "+th.width+";\" class=\"ui-state-default ui-th-ltr\">"+th.name+"</th>";
	    		    	}
	    		    }
	    			tempTableHtml+="</tr></thead></table>";
	    			tableElement.eq(0).before(tempTableHtml);
                }
               
            }
		});
		$("#toolbar").appendTo($("#t_"+subGrid.gridName));
		// 添加分页导航条
		$(gridVar).jqGrid("navGrid",pagerVar,{edit:false,add:false,del:false,search:false,refresh:true});	
		
		//排序
		//$(gridVar).jqGrid('sortableRows'); 
		//添加/删除列
		$(gridVar).jqGrid('navButtonAdd',pagerVar,{
			caption: columnButtonVar,
			title: columnButtonVar,
			onClickButton : function (){ jQuery(gridVar).jqGrid('columnChooser'); } 
		});
		// 删除 ajax
		$(deleteButtonVar).click(function(){
			var selectIds = $(gridVar).jqGrid('getGridParam','selarrrow');
			if (selectIds!=null && selectIds!="")	{
				$(gridVar).jqGrid('delGridRow',selectIds,{drag:false,
					afterSubmit:function(resp, postdata){
						var result = JSON.parse(resp.responseText);
						
						if(result.success == 'false'){
							alert('Delete failed:' + result.text);		
							return [true,"",""]
						} else {
							// alert('Delete is successfully');	
							return [true,"",""]
						}
						
					}
				});
				
			} else{
				alert('请选择记录!');
			}
		});
		
		// 查询 ajax
		$(searchButtonVar).click(function(){
			var url = simpleGrid.searchUrl + "?rowNum=" + $(gridVar).jqGrid('getGridParam','rowNum');
	    	
			var postData = new Object();
			for(var i = 0 ; i < simpleGrid.searchParameters.length; i++) {
				var name = subGrid.searchParameters[i];
				var currentValue = $("#" + simpleGrid.searchParameters[i]).attr('value');
				
			    eval("postData."+name+"=currentValue");
			}
			$(gridVar).appendPostData(postData);
			
			$(gridVar).jqGrid("setGridParam",{url:url}).trigger("reloadGrid");
			
			// 修正分页 如:当前浏览第2页,输入查询条件后查询结果共1页,jqgrid仍然会停留在第2页,需要手工翻到第1页
			$(gridVar).jqGrid("setGridParam",{page:1})
		});
		
		// 增加 页面跳转
		$(createButtonVar).click(function(){
			window.location.href=subGrid.createUrl;
		});
}	

$.download = function(url, data, method, callback){
	var inputs = "";
	var iframeX;
	var downloadInterval;
	if(url && data){
		// remove old iframe if has
		if($("#iframeX")) $("#iframeX").remove();
		// creater new iframe
		iframeX= $("<iframe src=\"[removed]false;\" name=\"iframeX\" id=\"iframeX\"></iframe>").appendTo("body").hide();
		if($.browser.msie){
			downloadInterval = setInterval(function(){
				// if loading then readyState is “loading” else readyState is “interactive”
				if(iframeX&& iframeX[0].readyState !=="loading"){
					callback();
					clearInterval(downloadInterval);
				}
			}, 23);
		} else {
			iframeX.load(function(){
				callback();
			});
		}

		//split params into form inputs
		$.each(data, function(p, val){
			alert(p + "-" + val);
			inputs+="<input type=\"hidden\" name='"+ p +"' value='"+ val +"' />";
		});
		
		//create form to send request
		$("<form action='"+ url +"' method='"+ (method||"post") + "' target=\"iframeX\">"+inputs+"</form>\"").appendTo("body").submit().remove();
	};
}; 