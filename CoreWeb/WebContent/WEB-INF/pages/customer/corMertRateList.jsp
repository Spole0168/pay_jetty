<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	var createUrl 		= "corMertRateCreate.action";
	var listUrl 		= "corMertRateList.action";
	var searchUrl 		= "corMertRateSearch.action";
	var operaType = "0";   //operaType  : 0 -新增，1-查看，2-审核，3-修改
	var dlgUserId ="#dialog-ajax-cnl-view";
	var nowDate = "";
	var nextDate = "";
	//数据字典  转义串
	var productListRender = "";
	var cardTypeRender = "";
	var cardPropertyRender = "";
	var settTypeRender = "";
	var currencyTypeRender = "";
	//页面初始化
	function initPage(){
		//设置数据字典转义串的值
		productListRender = '${productListRender}';
		cardTypeRender = '${cardTypeRender}';
		cardPropertyRender = '${cardPropertyRender}';
		settTypeRender = '${settTypeRender}';
		currencyTypeRender = '${currencyTypeRender}';
		getTodayTime();
		
	}
	//设置默认时间
	function getTodayTime(){
		var d = new Date();
	    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
	    nowDate=d.getFullYear().toString() +"-"+ addzero(d.getMonth() + 1) +"-" + addzero(d.getDate())
	   	$("#svalidDate").val(nowDate);
	    
		d.setTime(d.getTime() + 24*60*60*1000);
	   	nextDate=d.getFullYear().toString() +"-"+ addzero(d.getMonth() + 1) +"-" + addzero(d.getDate())
	}
	//清空提示信息
	function clearWarning() {
		$("div.warning span").html("");
		$("div.warning").hide();
	}
	//提示信息
	function showWarning(msg) {
		$("div.warning span").html(msg);
		$("div.warning").show();
		$("html,body").animate({scrollTop:0}, 500);
	}
	//校验查询参数
	function checkSearch(){
		return true;
	}
	function getProductName(){
		var code = $("#sproductCode").val();
		
		if(null!=code&&""!=code){
			$("#sproductName").val(getSelectValue('${productListRender}',code));
		}else{
			$("#sproductName").val("");
		}
	}
	// 转换下拉框的值
	function getSelectValue(render,key) {
		var arr=render.split(/:|;/);
		for (i=0;i<arr.length;) {
			if (arr[i]==key) {
				return arr[i+1];
			}
			i=i+2;
		}
		return key;
	}
	//查询事件
	function doSearch(){
		// 设置查询参数
		setQueryCondition();
		
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url: "corMertRateSearch.action" }).trigger("reloadGrid");
	}
	// 设置查询参数
	function setQueryCondition(){
		$("#gridTable").setPostDataItem("mertRateCode", 			$("#smertRateCode").val());
		$("#gridTable").setPostDataItem("mertRateName", 			$("#smertRateName").val());
		$("#gridTable").setPostDataItem("cardType", 				$("#scardType").val());
		
		$("#gridTable").setPostDataItem("merchantCode", 			$("#smerchantCode").val());
		$("#gridTable").setPostDataItem("merchantName", 			$("#smerchantName").val());
		$("#gridTable").setPostDataItem("cardProperty", 			$("#scardProperty").val());
		
		$("#gridTable").setPostDataItem("productCode", 				$("#sproductCode").val());
		$("#gridTable").setPostDataItem("settType", 				$("#ssettType").val());
		$("#gridTable").setPostDataItem("validDate", 				$("#svalidDate").val());
	}
	//跳转页面事件
	function jumpToJsp(obj){
		var url = createUrl+"?operaType="+obj.operaType+"&id="+obj.id;
		$(dlgUserId).dialog({width:450,height:600,modal:true});
		openDialog(dlgUserId,obj.title,url);
	}
	//页面出事化加载信息
	$().ready(function() {
		initPage();
		//查询按钮事件
		$("#searchBt").click(function(){
			clearWarning();
			doSearch();
		});
		//重置按钮事件
		$("#resetBt").click(function(){
			// 设置查询参数为空
			$("#queryField :input").val("");
			$('select').val("");
			getTodayTime();
			clearWarning();
		});
		//新增按钮事件0 -新增，1-查看，2-审核，3-修改
		$("#addBtn").click(function(){
			clearWarning();
			id = "11";
			operaType = "0";//0 -新增，1-查看，2-审核，3-修改
			var title = "信息新增";
			var obj = new Object();
			obj.id = id;
			obj.operaType = operaType;
			obj.title = title;
			jumpToJsp(obj);
			return;
		});
		
		//查看按钮事件0 -新增，1-查看，2-审核，3-修改
		$("#viewBtn").click(function(){
			clearWarning();
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			if(id!=null){
				operaType = "1";//0 -新增，1-查看，2-审核，3-修改
				var url = createUrl+"?operaType="+operaType+"&id"+id;
				var title = "信息查看";
				var obj = new Object();
				obj.id = id;
				obj.operaType = operaType;
				obj.title = title;
				jumpToJsp(obj);
				return;
			}else{
					showWarning("请选择一条信息查看！");
			    	return false;
			}
		});
		//修改按钮事件
		$("#editBtn").click(function(){
			clearWarning();
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			if(id == null ){
				showWarning("请选择一条记录进行修改！");
		    	return false;
			}
			var expireDate= $("#gridTable").getCell(id,"expireDate");
			if(getDaysDiff(expireDate,nowDate)>0){//手续费已失效  ，不可修改
				showWarning("该记录已失效，不能修改");
				return false;
			}
			if(id!=null){
				operaType = "3";//0 -新增，1-查看，2-审核，3-修改
				var url = createUrl+"?operaType="+operaType+"&id"+id;
				var title = "信息修改";
				var obj = new Object();
				obj.id = id;
				obj.operaType = operaType;
				obj.title = title;
				jumpToJsp(obj);
				return;
			}
		});
		//查询结果列表
		$("#gridTable").gridUtil().simpleGrid({
			url: listUrl,
			editurl:"#deleteUrl",
			sortname:'id',
			sortorder:'desc',
			multiselect : false,
 	        shrinkToFit: false,
	        autowidth:true,
			colNames:[
			          'ID', 
					   '${app:i18n('corMertRateDto.mertRateCode')}',
					   '${app:i18n('corMertRateDto.mertRateName')}',
					   '${app:i18n('corMertRateDto.merchantCode')}',
					   '${app:i18n('corMertRateDto.merchantName')}',
					   '${app:i18n('corMertRateDto.productCode')}',
					   '${app:i18n('corMertRateDto.productName')}',
					   
					   '${app:i18n('corMertRateDto.cardType')}',
					   '${app:i18n('corMertRateDto.cardProperty')}',
					   '${app:i18n('corMertRateDto.settType')}',
					   '${app:i18n('corMertRateDto.currency')}',
					   
					   '${app:i18n('corMertRateDto.serviceFeeType')}',//01-固定费率 02-按比例计费
					   '${app:i18n('corMertRateDto.fixedFeeValue')}',
					   '${app:i18n('corMertRateDto.rateFeeValue')}',
					   '${app:i18n('corMertRateDto.lowFeeValue')}',
					   '${app:i18n('corMertRateDto.hightFeeValue')}',
					   
					   '${app:i18n('corMertRateDto.effectDate')}',
					   '${app:i18n('corMertRateDto.expireDate')}',
					   '${app:i18n('corMertRateDto.remark')}',
					   
					   '${app:i18n('corMertRateDto.creator')}',
					   '${app:i18n('corMertRateDto.createTime')}',
					   '${app:i18n('corMertRateDto.updator')}',
					   '${app:i18n('corMertRateDto.updateTime')}'
					   ],
			colModel:[
					    {name : 'id', 				index :'id',hidden: true},
					    {name : 'mertRateCode', 	index:'mertRateCode'},
					    {name : 'mertRateName', 	index:'mertRateName'},
					    {name : 'merchantCode', 	index:'merchantCode'},
					    {name : 'merchantName', 	index:'merchantName'},
					    {name : 'productCode', 		index:'productCode'},
					    {name : 'productCode', 		index:'productCode',formatter:'select', editoptions:{value: productListRender}},
					    
					    {name : 'cardType', 		index:'cardType',formatter:'select', editoptions:{value: cardTypeRender}},
					    {name : 'cardProperty',  	index:'cardProperty',formatter:'select', editoptions:{value: cardPropertyRender}},
					    {name : 'settType',  		index:'settType',formatter:'select', editoptions:{value: settTypeRender}},
					    {name : 'currency',  		index:'currency',formatter:'select', editoptions:{value: currencyTypeRender}},
					    
					    {name : 'serviceFeeType',  index:'serviceFeeType',hidden: true},
					    {name : 'fixedFeeValue',  index:'fixedFeeValue'},
					    {name : 'rateFeeValue',  index:'rateFeeValue'},
					    {name : 'lowFeeValue',  index:'lowFeeValue'},
					    {name : 'hightFeeValue',  index:'hightFeeValue'},
					    
					    {name : 'effectDate',  index:'effectDate',formatter:'date', formatoptions: {newformat:'Y-m-d', srcformat:'Y-m-d H:i:s'}},
					    {name : 'expireDate',  index:'expireDate',formatter:'date', formatoptions: {newformat:'Y-m-d', srcformat:'Y-m-d H:i:s'}},
					    {name : 'remark',  index:'remark'},
					    
					    {name : 'creator',  index:'creator'},
					    {name : 'createTime',  index:'createTime',formatter:'date', formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
					    {name : 'updator',  index:'updator'},
					    {name : 'updateTime',  index:'updateTime',formatter:'date', formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}}
					    
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('corMertRateDto.corMertRateDtoListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs');
				//id长度小于等于零时 提示请输入警告
				if(ids==null||ids.length<=0){
					showWarning("没有记录"); 
					return;
				}else{
					for(var i=0;i < ids.length;i++) {
						var idNum = ids[i];
						
						var serviceFeeType = $("#gridTable").getCell(idNum,"serviceFeeType");
						if(null!=serviceFeeType&&"01"==serviceFeeType){//01-固定费率 02-按比例计费
							$("#gridTable").jqGrid('setRowData',idNum,{rateFeeValue : ""});
						}else if(null!=serviceFeeType&&"02"==serviceFeeType){
							var rateFeeValue = $("#gridTable").getCell(idNum,"rateFeeValue");
							$("#gridTable").jqGrid('setRowData',idNum,{fixedFeeValue : ""});
							$("#gridTable").jqGrid('setRowData',idNum,{rateFeeValue : rateFeeValue+"%"});
						}
					}
				}
			}
		});

		$("#gridTable").gridUtil().customizeColumn();
		$("#gridToolbar").appendTo($("#t_gridTable"));

		// sub form toggle
		$( function() {
			$(".legend_title a").toggle(function(){
					$(this).parent().next(".fieldset_container:first").hide();
					$(this).removeClass("container_show");
					$(this).addClass("container_hide");
				},function(){
					$(this).parent().next(".fieldset_container:first").show();
					$(this).removeClass("container_hide");
					$(this).addClass("container_show");
			});
		})
		
		// input blur event for trimming
		$("#queryField input").input().trim();
		$("#queryField input").input().enter(doSearch);
		
	});
	//弹出层
	function openDialog(dlgId, title, url){
		$(dlgId).dialog({
			autoOpen:false,
			position:'center',
			modal:true,
			resizable: false,
			title:title
			});
		
		$(dlgId).load(url);
		$(dlgId).dialog('open');
	}
	
	function closeDialog(){
		$(dlgUserId).dialog('close');
		
	}
</script>

<s:form id="demoListForm" method="post" action="">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('corMertRateDto.corMertRateDtoListJsp.Title')}</h3>
		</div>
		<input id="cardTypeRender" type="hidden" value="${cardTypeRender}" />
		<input id="cardPropertyRender"  type="hidden" value="${cardPropertyRender}" />
		<input id="settTypeRender"  type="hidden" value="${settTypeRender}" />
		
		<div class="block_container">
			<div class="warning" style="display: none;">
				<span></span>
			</div>
			<div class="fieldset_border fieldset_bg m-b" id="queryField">
				<div class="legend_title">
					<a href="#" class="container_show">${app:i18n('global.jsp.search')}</a>
				</div>
				<div class="fieldset_container" id="test1">
					<table cellpadding="0" cellspacing="0" class="table_form">
						<thead>
						</thead>
						<tfoot>
						</tfoot>
						<tbody>
							<tr>
								<th>${app:i18n('corMertRateDto.mertRateCode')}：</th>
								<td><input name="mertRateCode" id="smertRateCode" class="width_c" /></td>
								<th>${app:i18n('corMertRateDto.mertRateName')}：</th>
								<td><input name="mertRateName" id="smertRateName" class="width_c" /></td>
								<th>${app:i18n('corMertRateDto.cardType')}：</th>
								<td>
									<select name="cardType" id="scardType" style="width:190px">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{cardTypeList}" id="cardTypeItem">
									        <option value="<s:property value="#cardTypeItem.key" />">
									        	<s:property value="#cardTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
							    </td>
							</tr>
							<tr>
								<th>${app:i18n('corMertRateDto.merchantCode')}：</th>
								<td><input name="merchantCode" id="smerchantCode" class="width_c" /></td>
								<th>${app:i18n('corMertRateDto.merchantName')}：</th>
								<td><input name="merchantName" id="smerchantName" class="width_c" /></td>
								<th>${app:i18n('corMertRateDto.cardProperty')}：</th>
								<td>
									<select name="cardProperty" id="scardProperty" style="width:190px">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{cardPropertyList}" id="cardTypeItem">
									        <option value="<s:property value="#cardTypeItem.key" />">
									        	<s:property value="#cardTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
							    </td>
							</tr>
							<tr>
								<th>${app:i18n('corMertRateDto.productCode')}：</th>
								<td>
									<select name="productCode" id="sproductCode" style="width:190px" onchange="getProductName()">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{productList}" id="cardTypeItem">
									        <option value="<s:property value="#cardTypeItem.key" />">
									        	<s:property value="#cardTypeItem.key" />
									        </option>
										</s:iterator>
							        </select>
								
								</td>
								<th>${app:i18n('corMertRateDto.productName')}：</th>
								<td><input name="productName" id="sproductName" class="width_c" disabled="disabled"/></td>
								<th>${app:i18n('corMertRateDto.settType')}：</th>
								<td>
									<select name="settType" id="ssettType" style="width:190px">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{settTypeList}" id="cardTypeItem">
									        <option value="<s:property value="#cardTypeItem.key" />">
									        	<s:property value="#cardTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
							    </td>
							</tr>
							<tr>	
								<th>${app:i18n('corMertRateDto.validDate')}：</th>									  		   
								<td><input name="validDate" id="svalidDate" value="${validDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" style="width:188px"/></td>
							</tr>
						</tbody>
					</table>
					<div class="btn_layout">
						<a name="search" id="searchBt" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
						<a name="reset" id="resetBt" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
					</div>
				</div>
			</div>
					
			<div class="block">
				<table id="gridTable">
				</table>
				<div id="gridPager"></div>
				<div id="gridToolbar">
					<app:isPermission resource='CORE_CUST_MERT_RATE_ADD'>
						<a id="addBtn" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a> 
					</app:isPermission>
					<app:isPermission resource='CORE_CUST_MERT_RATE_EDIT'>
						<a id="editBtn" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-edit">${app:i18n('global.jsp.modify')}</span></span></a> 
					</app:isPermission>
					<app:isPermission resource='CORE_CUST_MERT_RATE_VIEW'>
						<a id="viewBtn" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-view">${app:i18n('global.jsp.view')}</span></span></a> 
					</app:isPermission>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="dialog-ajax-cnl-view"></div>
</s:form>
