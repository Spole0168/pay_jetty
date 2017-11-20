<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	var createUrl 		= "incomeOrderCreate.action";
	var listUrl 		= "incomeOrderList.action";
	var searchUrl 		= "incomeOrderSearch.action";
	var operaType = "0";   //operaType  : 0 -新增，1-查看，2-审核，3-修改
	var dlgUserId ="#dialog-ajax-cnl-view";
	var dayEnd = "";//校验查询的结束日期
	var nowDate = "";		
	//数据字典  转义串
	var statusTypeRender = "";
	var currencyTypeRender = "";
	var operateTypeRender = "";
	var incomeOrderStatusRender = "";
	//页面初始化
	function initPage(){
		//设置数据字典转义串的值
		statusTypeRender = '${statusTypeRender}';
		currencyTypeRender = '${currencyTypeRender}';
		operateTypeRender = '${operateTypeRender}';
		incomeOrderStatusRender = '${incomeOrderStatusRender}';
		getTodayTime();
	}
	//设置默认时间
	function getTodayTime(){
		var d = new Date();
	    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
	    var date=d.getFullYear().toString() +"-"+ addzero(d.getMonth() + 1) +"-" + addzero(d.getDate())
	    nowDate = date;
	    var s1 = date +" 00:00:00";
	    var s2 = date +" 23:59:59";
	    dayEnd = s2;
	    $("#sstartTime").val(s1);
	    $("#sendTime").val(s2);
	    $("#dayEndId").val(s2);
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
		var startTime = $("#sstartTime").val();
		var endTime = $("#sendTime").val();
		if(""==startTime||""==endTime){
			return "请输入必填信息";
		}
		if(null!=startTime&&startTime.length>0&&null!=endTime&&endTime.length>0){
			startTime = startTime.replace(/-/g,'/');
			endTime = endTime.replace(/-/g,'/');
			dayEndTime = dayEnd.replace(/-/g,'/');
			startTime = new Date(startTime);
			endTime = new Date(endTime);
			dayEndTime = new Date(dayEndTime);
			
			var times = startTime.getTime() - endTime.getTime();
			var days = Math.abs(parseInt(times/(1000*60*60*24)));
			var nowEnd = parseInt((dayEndTime.getTime() - endTime.getTime())/(1000*60*60*24));
			if(nowEnd<0){
				return "结束时间不能大于当前时间";
			}else if(times >= 0) {
				return "开始时间不能大于结束时间";
			}else if (days > 31) {
				return "时间差距不能大于31天";
			}
		}
		return "";
	}
	//查询事件
	function doSearch(){
		// 设置查询参数
		setQueryCondition();
		
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url: searchUrl }).trigger("reloadGrid");
	}
	// 设置查询参数
	function setQueryCondition(){
		$("#gridTable").setPostDataItem("incomeOrderCode",$("#sincomeOrderCode").val());
		$("#gridTable").setPostDataItem("merchantCode",$("#smerchantCode").val());
		$("#gridTable").setPostDataItem("merchantName",$("#smerchantName").val());
		$("#gridTable").setPostDataItem("custName",$("#scustName").val());
		$("#gridTable").setPostDataItem("operateType",$("#soperateType").val());
		$("#gridTable").setPostDataItem("currency",$("#scurrency").val());
		$("#gridTable").setPostDataItem("amount",$("#samount").val());
		$("#gridTable").setPostDataItem("status", $("#audit_Status").val());
		$("#gridTable").setPostDataItem("timeType",$("#stimeType").val());
		$("#gridTable").setPostDataItem("startTime",$("#sstartTime").val());
		$("#gridTable").setPostDataItem("endTime",$("#sendTime").val());
	}
	//跳转页面事件
	function jumpToJsp(obj){
		var url = createUrl+"?operaType="+obj.operaType+"&id="+obj.id;
// 		window.location = url;
// 		return;
		$(dlgUserId).dialog({width:650,height:550,modal:true});
		openDialog(dlgUserId,obj.title,url);
	}
	//页面出事化加载信息
	$().ready(function() {
		initPage();
		//查询按钮事件
		$("#searchBt").click(function(){
			clearWarning();
			var flag = checkSearch();
			if(flag!=""){
				showWarning(flag);
				return;
			}
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
			var title = "新增信息";
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
		//审核按钮事件
		$("#auditBtn").click(function(){
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			if(id == null ){
				showWarning("请选择一条记录进行审核！");
		    	return false;
			}
			//根据id获取商户状态
			var status= $("#gridTable").getCell(id,"status");
			if(status !="01"){
				showWarning("请选择待审核的记录进行审核！");
		    	return false;
			}
			if(id!=null){
				operaType = "2";//0 -新增，1-查看，2-审核，3-修改
				$("div.warning").hide();
				var url = createUrl+"?operaType="+operaType+"&id"+id;
				var title = "信息审核";
				var obj = new Object();
				obj.id = id;
				obj.operaType = operaType;
				obj.title = title;
				jumpToJsp(obj);
				return;
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
			var auditStatus= $("#gridTable").getCell(id,"auditStatus");
			if(auditStatus !="03"){
				showWarning("只有审核失败的记录可以进行修改！");
		    	return;
			}
			operaType = "3";//0 -新增，1-查看，2-审核，3-修改
			var url = createUrl+"?operaType="+operaType+"&id"+id;
			var title = "信息修改";
			var obj = new Object();
			obj.id = id;
			obj.operaType = operaType;
			obj.title = title;
			jumpToJsp(obj);
			return;
		});
		//查询结果列表
		$("#gridTable").gridUtil().simpleGrid({
			url: listUrl,
			editurl:"#deleteUrl",
			sortname:'id',
			sortorder:'desc',
			multiselect:false,
			shrinkToFit: false,
	        autowidth:true,
			colNames:[
			          'ID', 
			          '${app:i18n('incomeOrderDto.incomeOrderCode')}',
			          '${app:i18n('incomeOrderDto.merchantCode')}',
			          '${app:i18n('incomeOrderDto.merchantName')}',
			          '${app:i18n('incomeOrderDto.custName')}',
			          '${app:i18n('incomeOrderDto.operateType')}',
			          '${app:i18n('incomeOrderDto.currency')}',
			          '${app:i18n('incomeOrderDto.transRate')}',
			          '${app:i18n('incomeOrderDto.amount')}',
			          '${app:i18n('incomeOrderDto.incomeStatus')}',
			          '${app:i18n('incomeOrderDto.bankTransNum')}',
			          '${app:i18n('incomeOrderDto.bankAcntCode')}',
			          '${app:i18n('incomeOrderDto.bankName')}',
			          '${app:i18n('incomeOrderDto.bankCardNum')}',
			          '${app:i18n('incomeOrderDto.reserveBankAcntCode')}',
			          '${app:i18n('incomeOrderDto.reservebankName')}',
			          '${app:i18n('incomeOrderDto.reserveBankCardNum')}',
			          '${app:i18n('incomeOrderDto.inAccountTime')}',
			          '${app:i18n('incomeOrderDto.transExplanation')}',
// 			          '${app:i18n('incomeOrderDto.')}',   //凭证文件
			          '${app:i18n('incomeOrderDto.status')}',
			          '${app:i18n('incomeOrderDto.auditReason')}',
			          '${app:i18n('incomeOrderDto.auditPerson')}',
			          '${app:i18n('incomeOrderDto.auditTime')}',
			          '${app:i18n('incomeOrderDto.creator')}',
			          '${app:i18n('incomeOrderDto.createTime')}'
					   ],
			colModel:[
					    {name : 'id', index :'id',hidden: true},
					    {name : 'incomeOrderCode',index :'incomeOrderCode'},
					    {name : 'merchantCode', index :'merchantCode'},
					    {name : 'merchantName', index :'merchantName'},
					    {name : 'custName', index :'custName'},
					    {name : 'operateType',     index :'operateType',formatter:'select', editoptions:{value:operateTypeRender}},
					    {name : 'currency', index :'currency',formatter:'select', editoptions:{value:currencyTypeRender}},
					    {name : 'transRate',  index :'transRate'},
					    {name : 'amount',   index :'amount',align:'right',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
					    {name : 'status',     index :'status',formatter:'select', editoptions:{value: incomeOrderStatusRender }},
					    {name : 'bankTransNum',     index :'bankTransNum'},
					    {name : 'bankCode',     index :'bankCode'},
					    {name : 'bankName',     index :'bankName'},
					    {name : 'bankCardNum',     index :'bankCardNum'},
					    {name : 'reserveBankCode',     index :'reserveBankCode'},
					    {name : 'reserveBankName',     index :'reserveBankName'},
					    {name : 'reserveBankCardNum',     index :'reserveBankCardNum'},
					    {name : 'inAccountTime',     index :'inAccountTime'},
					    {name : 'transExplanation',     index :'transExplanation'},
// 					    {name : '',     index :''},//
					    {name : 'auditStatus',     index :'auditStatus',formatter:'select', editoptions:{value:statusTypeRender}},
					    {name : 'auditReason',     index :'auditReason'},
					    {name : 'auditPerson',     index :'auditPerson'},
					    {name : 'auditTime',     index :'auditTime'},
					    {name : 'creator',     index :'creator'},
					    {name : 'createTime',     index :'createTime'}
					    
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('incomeOrderDto.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs');
				//id长度小于等于零时 提示请输入警告
				if(ids==null||ids.length<=0){
					showWarning("没有记录"); 
					return;
				}else{
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
			<h3>${app:i18n('incomeOrderDto.Title')}</h3>
		</div>
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
								<th>${app:i18n('incomeOrderDto.operateType')}：</th>
								<td>
									<select name="operateType" id="soperateType" style="width:190px">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{operateTypeList}" id="operateTypeItem">
									        <option value="<s:property value="#operateTypeItem.key" />">
									        	<s:property value="#operateTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
							    </td>
								<th>${app:i18n('incomeOrderDto.merchantCode')}：</th>
								<td><input name="merchantCode" id="smerchantCode" class="width_c" /></td>
								<th>${app:i18n('incomeOrderDto.merchantName')}：</th>
								<td><input name="merchantName" id="smerchantName" class="width_c" /></td>
							    
							</tr>
							<tr>
								<th>${app:i18n('incomeOrderDto.currency')}：</th>
								<td>
									<select name="currency" id="scurrency" style="width:190px">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{currencyTypeList}" id="currencyTypeItem">
									        <option value="<s:property value="#currencyTypeItem.key" />">
									        	<s:property value="#currencyTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
							    </td>
								<th>${app:i18n('incomeOrderDto.custName')}：</th>
								<td><input name="custName" id="scustName" class="width_c" /></td>
								<th>${app:i18n('incomeOrderDto.incomeOrderCode')}：</th>
								<td><input name="incomeOrderCode" id="sincomeOrderCode" class="width_c" /></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('incomeOrderDto.timeType')}：</th>
								<td>
									<select name="timeType" id="stimeType" style="width:190px">
										<option value="C" selected>创建时间</option>
										<option value="A" >审核时间</option>
							        </select>
							    </td>
								<th><em>*</em>${app:i18n('incomeOrderDto.startTime')}：</th>									  		   							   
								<td><input name="startTime" id="sstartTime" class="Wdate" value="${startTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'dayEndId\',{d:-90})}',maxDate:'#F{dayEnd}'})" style="width:188px"/></td>
								<th><em>*</em>${app:i18n('incomeOrderDto.endTime')}：</th>
								<td>
									<input type="hidden" id="dayEndId" class="Wdate" value="${dayEnd}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
									<input name="endTime" id="sendTime" class="Wdate" value="${dayEnd}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{dayEnd}'})" style="width:188px"/>
								</td>
							</tr>
							<tr>
								<th>${app:i18n('incomeOrderDto.status')}：</th>
								<td><!-- 请选择” “待审核”，“审核失败”，“审核通过” -->
									<select name="auditStatus" id="audit_Status" style="width:190px">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{statusTypeList}" id="statusTypeItem">
									        <option value="<s:property value="#statusTypeItem.key" />">
									        	<s:property value="#statusTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
							    </td>
								<th>${app:i18n('incomeOrderDto.amount')}：</th>
								<td><input name="amount" id="samount" class="width_c" /></td>
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
					<app:isPermission resource='CORE_CUST_INORDER_ADD'>
						<a id="addBtn" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a> 
					</app:isPermission>
					<app:isPermission resource='CORE_CUST_INORDER_EDIT'>
						<a id="editBtn" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-edit">${app:i18n('global.jsp.modify')}</span></span></a> 
					</app:isPermission>
					<app:isPermission resource='CORE_CUST_INORDER_AUDIT'>
						<a id="auditBtn" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-select">${app:i18n('global.jsp.audit')}</span></span></a> 
					</app:isPermission>
					<app:isPermission resource='CORE_CUST_INORDER_VIEW'>
						<a id="viewBtn" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-view">${app:i18n('global.jsp.view')}</span></span></a> 
					</app:isPermission>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="dialog-ajax-cnl-view"></div>
</s:form>
