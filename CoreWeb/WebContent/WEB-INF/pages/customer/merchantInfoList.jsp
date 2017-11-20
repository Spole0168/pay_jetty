<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	var addUrl 		= "merchantInfoAdd.action";
	var listUrl 		= "";
	var searchUrl 		= "";
	var operaType = "0";   //operaType  : 0 -新增，1-查看，2-审核，3-修改
	var statusTypeRender = "";
	var success = "S";		//激活成功
	var failed = "E";		//激活失败
	var riskLevelRender = "";
	var isCheckinRender="";
	var isCheckoutRender="";
	var industryRender="";
	var mertStatusRender= "";
	var dayEnd = "";//校验查询的结束日期
	//页面初始化
	function initPage(){
		//设置数据字典转义串的值
		statusTypeRender = $('#statusTypeRender').attr('value');
		riskLevelRender = $('#riskLevelRender').attr('value');
		isCheckinRender = $('#isCheckinRender').attr('value');
		isCheckoutRender = $('#isCheckoutRender').attr('value');
		industryRender= $('#industryRender').attr('value');
		mertStatusRender= $('#mertStatusRender').attr('value');
		getTodayTime();
	}
	
	function getTodayTime(){
		var d = new Date();
	    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
	    var date=d.getFullYear().toString() +"-"+ addzero(d.getMonth() + 1) +"-" + addzero(d.getDate())
	    var s1 = date +" 00:00:00";
	    var s2 = date +" 23:59:59";
	    dayEnd = s2;
	    $("#createStartTime").val(s1);
	    $("#createEndTime").val(s2);
	}
	function clearWarning() {
		$("div.warning span").html("");
		$("div.warning").hide();
	}
	function showWarning(msg) {
		$("div.warning span").html(msg);
		$("div.warning").show();
		$("html,body").animate({scrollTop:0}, 500);
	}
	function checkForm(){
		var startTime = $("#createStartTime").val();
		var endTime = $("#createEndTime").val();
		if (null != startTime && startTime.length > 0 && (null == endTime || endTime.length == 0)) {
// 			showWarning("开始时间不为空, 结束时间必填");
			return "开始时间不为空, 结束时间必填";
		}
		if(null!=startTime&&startTime.length>0&&null!=endTime&&endTime.length>0){
			startTime = startTime.replace(/-/g,'/');
			endTime = endTime.replace(/-/g,'/');
			startTime = new Date(startTime);
			endTime = new Date(endTime);
			var times = startTime.getTime() - endTime.getTime();
			var days = Math.abs(parseInt(times/(1000*60*60*24)));
			if(times >= 0) {
// 				showWarning("开始时间不能大于结束时间");
				return "开始时间不能大于结束时间";
			} else if (days > 30) {
// 				showWarning("时间差距不能大于30天");
				return "时间差距不能大于30天";
			}
		}
		var custCode=$("#cust_code").val();
		var mertCode=$("#merchant_code").val();
		if (null != custCode&&$.trim(custCode) != '') {
			var reg = new RegExp("^[a-zA-Z0-9]+$");
			if (custCode.length>15||!reg.test(custCode)) {
				return "请输入正确的会员编号";
			}
		}
		
		if (null != mertCode&&$.trim(mertCode) != '') {
			var reg = new RegExp("^[0-9]*$");
			if (mertCode.length>18||!reg.test(mertCode)) {
				return "请输入正确的商户号";
			}
		}
		return "";
	}
	
	


	function doSearch(){
		// 设置查询参数
		setQueryCondition();
		
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url: "merchantSearch.action" }).trigger("reloadGrid");
	}
	
	function setQueryCondition(){
		// 设置查询参数
		$("#gridTable").setPostDataItem("cust_code", $("#cust_code").val());
		$("#gridTable").setPostDataItem("cust_name", $("#cust_name").val());
		$("#gridTable").setPostDataItem("merchant_code", $("#merchant_code").val());
		$("#gridTable").setPostDataItem("merchant_name", $("#merchant_name").val());
		$("#gridTable").setPostDataItem("status", $("#newStatus").val());
		$("#gridTable").setPostDataItem("risk_level", $("#risk_level").val());
		$("#gridTable").setPostDataItem("createStartTime", $("#createStartTime").val());
		$("#gridTable").setPostDataItem("createEndTime", $("#createEndTime").val());
		$("#gridTable").setPostDataItem("mert_status", $("#mert_status").val());
	}
	//跳转页面事件
	function jumpToJsp(obj){
		var url = addUrl+"?operaType="+obj.operaType+"&id="+obj.id;
	//window.location = url;
 		$(dlgUserId).dialog({width:650,height:750,modal:true});
 		openDialog(dlgUserId,obj.title,url);
	}
	
	function activeAccount(obj){
	//	alert("==="+obj);
		 $.ajax({
	          type: "post",
	          url: "merchantActiveAccount.action",
	          data: {id:obj},
	          dataType: "json",
	          async:false,
	          success: function(data){
	        	  var msg=data.message;
        	  	var  msgs =  "账户激活成功";
        	  	if(msg == "01"){//不能操作
        	  		msgs = "账户激活失败";
	          	}else{
	          		msgs = "激活成功 ";
	          	}
        	  	//更新列表
        	  	doSearch();
       		  	showWarning(msgs);
       			return;
	          }
	      });
	}
	
	function mertIscheck(id,check,checkType){
		 $.ajax({
	          type: "post",
	          url: "mertIscheck.action",
	          data: {id:id,checkSelect:check,checkType:checkType},
	          dataType: "json",
	          async:false,
	          success: function(data){
	        	  var msg=data.message;
       	  	var  msgs =  "";
       	  	if(msg == "01"){//不能操作
       	  		msgs = "变更成功";
	          	}else{
	          		msgs = "变更失败 ";
	          	}
       	  	//更新列表
       	  	doSearch();
      		  	showWarning(msgs);
      			return;
	          }
	      });
		
		
	}
	
	function applyChange(id){
		 $.ajax({
	          type: "post",
	          url: "applyChange.action",
	          data: {id:id},
	          dataType: "json",
	          async:false,
	          success: function(data){
	        	  var msg=data.message;
      	  	var  msgs =  "";
      	  	if(msg == "03"){//不能操作
      	  		msgs = "变更申请已生成，请到变更管理界面进行修改";
	          	}else if(msg =="02"){
	          		msgs = "已有申请待处理,无法重复提交！";
	          	}else{
	          		msgs = "变更失败 ";
	          	}
      	  	//更新列表
      	  	doSearch();
     		  	showWarning(msgs);
     			return;
	          }
	      });
		
		
	}
	
	
	var dlgUserId ="#dialog-ajax-cnl-view";
	$().ready(function() {
		initPage();
		getTodayTime();
		
		$("#searchBt").click(function(){
			clearWarning();
			var flag = checkForm();
			if (flag != "") {
				showWarning(flag);
				return;
		}
			doSearch();
		});
		
		$("#resetBt").click(function(){
			// 设置查询参数为空
			$("#queryField :input").val("");
			$('select').val("");
			getTodayTime();
			clearWarning();
		});
		//新增
		$("#addBtn").click(function(){
			clearWarning();
			id = "11";
			operaType = "0";//0 -新增，1-查看，2-审核，3-修改
			var title = "新增商户";
			var obj = new Object();
			obj.id = id;
			obj.operaType = operaType;
			obj.title = title;
			jumpToJsp(obj);
			return;
		});
		//修改
		$("#updateBtn").click(function(){
			clearWarning();
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			//根据id获取商户状态
			var status= $("#gridTable").getCell(id,"status");
			var custName= $("#gridTable").getCell(id,"custName");
			
			if(id == null ){
				showWarning("请选择一条记录进行修改！");
		    	return false;
			}
			if(status !="03"){
				showWarning("只有审核失败的记录可以进行修改！");
		    	return false;
			}
			if(id!=null){
				operaType = "3";//0 -新增，1-查看，2-审核，3-修改
				var url = addUrl+"?operaType="+operaType+"&id"+id;
				var title = "商户信息修改";
				var obj = new Object();
				obj.id = id;
				obj.operaType = operaType;
				obj.title = title;
				jumpToJsp(obj);
				return;
			}
		});
		//明细
		$("#viewBtn").click(function(){
			clearWarning();
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			if(id!=null){
				operaType = "1";//0 -新增，1-查看，2-审核，3-修改
				var url = addUrl+"?operaType="+operaType+"&id"+id;
				var title = "商户信息查看";
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
		//审核
		$("#reviewBtn").click(function(){
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			//根据id获取商户状态
			var status= $("#gridTable").getCell(id,"status");
		//	 alert("提示信息="+status);
			if(id == null ){
				showWarning("请选择一条记录进行审核！");
		    	return false;
			}
			if(status !="01"){
				showWarning("请选择待审核的记录进行审核！");
		    	return false;
			}
			if(id!=null){
				operaType = "2";//0 -新增，1-查看，2-审核，3-修改
				$("div.warning").hide();
				var url = addUrl+"?operaType="+operaType+"&id"+id;
				var title = "商户信息审核";
				var obj = new Object();
				obj.id = id;
				obj.operaType = operaType;
				obj.title = title;
				jumpToJsp(obj);
				return;
			}
			
		});
		
		
		//提交变更申请
		$("#applyBtn").click(function(){
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			//根据id获取商户状态
			var status= $("#gridTable").getCell(id,"status");
		//	 alert("提示信息="+status);
			if(id == null ){
				showWarning("请选择一条记录进行变更！");
		    	return false;
			}
			if(status !="02"){
				showWarning("请选择审核通过的记录进行申请！");
		    	return false;
			}
			if(id!=null){
				$("div.warning").hide();
				applyChange(id);
			}
		
		});
		
		
		
		
		$("#gridTable").gridUtil().simpleGrid({
			url: listUrl,
			editurl:"#deleteUrl",
			sortname:'updateTime',
			sortorder:'desc',
			multiselect:false,
 	        shrinkToFit: false,
	        autowidth:true,
			colNames:[
			          'ID',
			          'ACCID',
					   '${app:i18n('merchant.cust_code')}',
					   '${app:i18n('merchant.cust_name')}',
					   '${app:i18n('merchant.merchant_code')}',
					   '${app:i18n('merchant.merchant_name')}',
					   '${app:i18n('merchant.industry')}',
// 					   '${app:i18n('merchant.mccCode')}',
					   '${app:i18n('merchant.riskLevel')}',
// 					   '${app:i18n('merchant.initialDeposit')}',
// 					   '${app:i18n('merchant.settPeriod')}',
// 					   '${app:i18n('merchant.settStartDate')}',
					   
					   '${app:i18n('merchant.isCheckin')}',
					   '${app:i18n('merchant.isCheckout')}',
					   '${app:i18n('merchant.creator')}',
					   '${app:i18n('merchant.create_time')}',
					   '${app:i18n('merchant.update_time')}',
					  
					   '${app:i18n('merchant.review_person')}',
					   '${app:i18n('merchant.review_time')}',
					   '${app:i18n('merchant.status')}',
					   '${app:i18n('merchant.mertStatus')}',
					   '${app:i18n('merchant.remark')}',
					   '${app:i18n('merchant.opera')}'
					   ],
			colModel:[
					    {name : 'id', 			index :'id',hidden: true},
					    {name : 'accId', 			index :'accId',hidden: true},
					    {name : 'custCode', 	index:'custCode'},
					    {name : 'custName', 	index:'custName'},
					    {name : 'merchantCode', 	index:'merchantCode'    },
					    {name : 'merchantName', 		index:'merchantName'},
					    {name : 'industry', 	index:'industry',formatter:'select', editoptions:{value:industryRender}},
// 					    {name : 'mccCode', 	index:'mccCode'},
					    {name : 'riskLevel', 	index:'riskLevel',formatter:'select', editoptions:{value:riskLevelRender}},
// 					    {name : 'initialDeposit',  index:'initialDeposit'},
// 					    {name : 'settPeriod',  index:'settPeriod'},
// 					    {name : 'settStartDate',  index:'asettStartDate',formatter:'date', formatoptions: {newformat:'Y-m-d', srcformat:'Y-m-d H:i:s'}},
					   
					    {name : 'isCheckin',  index:'isCheckin',formatter:'select', editoptions:{value:isCheckinRender}},
					    {name : 'isCheckout',  index:'isCheckout',formatter:'select', editoptions:{value:isCheckoutRender}},
					    {name : 'creator',  index:'creator'},
					    {name : 'createTime',  index:'createTime',formatter:'date', formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
					    {name : 'updateTime',  index:'updateTime',formatter:'date', formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
					    {name : 'reviewPerson',  index:'reviewPerson'},
					    {name : 'reviewTime',  index:'reviewTime',formatter:'date', formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
					    {name : 'status',  index:'status',formatter:'select', editoptions:{value:statusTypeRender}},
					    {name : 'mertStatus',  index:'mertStatus',formatter:'select', editoptions:{value:mertStatusRender}},
					    {name : 'remark',  index:'remark'},
					    {name:  'operation',align:'center', search:false,sortable:false,editable:true,title:false,width:250}
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('merchant.merchantInfoListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs');
				//id长度小于等于零时 提示请输入警告
				if(ids.length<=0||ids==null){
					showWarning("没有记录"); 
					return;
				}else{
					//增加激活账户按钮
					for(var i=0;i < ids.length;i++) {
						//1.激活帐户按钮只适用于审核通过的记录，第一次审核通过，激活帐户后，按纽变灰，不可重复激活。
						//2.点击“开通账户“按钮.
						//必须是第一次审核通过才会显示  激活按钮
						var idNum = ids[i];
						var status= $("#gridTable").getCell(ids[i],"status");//审核状态
						var mertStatus= $("#gridTable").getCell(ids[i],"mertStatus");//商户状态
						var isCheckin= $("#gridTable").getCell(ids[i],"isCheckin");//止入状态
						var isCheckout= $("#gridTable").getCell(ids[i],"isCheckout");//止出状态
						var accId= $("#gridTable").getCell(idNum,"accId");
						var checkType="";
						var all = "";
						if(status=="02"&&(null==accId||""==accId)){
							var activeBtn = "<a href='#' class='icon-edit m-r ' onclick=\"activeAccount('" + idNum + "')\"><em>${app:i18n('merchant.activeOperaBt')}</em></a>";
							all = all +" "+ activeBtn;
						}
						if(status=="02"&&mertStatus=="02"){
							checkType= "01";//01=是否止入
							var ischeckinStatus="";
							if(isCheckin=="01"){
								ischeckinStatus="02";
								var mod = "<a href='#' class='icon-edit m-r ' onclick=\"mertIscheck('" + idNum + "','"+ischeckinStatus+"','"+checkType+"')\"><em>${app:i18n('merchant.Checkin')}</em></a>";
								all = all +" "+ mod;
							}else{
								ischeckinStatus="01";
								var mod = "<a href='#' class='icon-edit m-r ' onclick=\"mertIscheck('" + idNum + "','"+ischeckinStatus+"','"+checkType+"')\"><em>${app:i18n('merchant.setCheckin')}</em></a>";
								all = all +" "+ mod;
							}
						}
						if(status=="02"&&mertStatus=="02"){
							checkType= "02";//01=是否止入
							var isCheckoutStatus="";
							if(isCheckout=="01"){
								isCheckoutStatus="02";
								var mod = "<a href='#' class='icon-edit m-r ' onclick=\"mertIscheck('" + idNum + "','"+isCheckoutStatus+"','"+checkType+"')\"><em>${app:i18n('merchant.setCheck')}</em></a>";
								all = all +" "+ mod;
							}else{
								isCheckoutStatus="01";
								var mod = "<a href='#' class='icon-edit m-r ' onclick=\"mertIscheck('" + idNum + "','"+isCheckoutStatus+"','"+checkType+"')\"><em>${app:i18n('merchant.setCheckout')}</em></a>";
								all = all +" "+ mod;
							}

						}
						if(status!="02"){
							var all = "";
							$("#gridTable").jqGrid('setRowData',idNum,{merchantCode:all});
							
						}
						$("#gridTable").jqGrid('setRowData',idNum,{operation : all});						
						
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
		//$(dlgId).html("");
		$(dlgId).dialog({
			autoOpen:false,
			position:'center',
			modal:true,
			resizable: false,
			title:title,
// 			buttons: {
// 	        	'${app:i18n("global.jsp.ok")}': function(){
// 	        		//$(diaDetail).dialog('close');
// 	        		closeDialog();
// 	            }
// 	        }
			
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
				<h3>${app:i18n('merchant.merchantInfoListJsp.Title')}</h3>
			</div>
			<input id="mertStatusRender" type="hidden"
				value="${mertStatusRender}" /> <input id="riskLevelRender"
				type="hidden" value="${riskLevelRender}" /> <input
				id="statusTypeRender" type="hidden" value="${statusTypeRender}" />
			<input id="isCheckinRender" type="hidden" value="${isCheckinRender}" />
			<input id="isCheckoutRender" type="hidden"
				value="${isCheckoutRender}" /> <input id="industryRender"
				type="hidden" value="${industryRender}" />
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
									<th>${app:i18n('merchant.business_cust_code')}：</th>
									<td><input name="cust_code" id="cust_code" class="width_c" /></td>

									<th>${app:i18n('merchant.business_cust_name')}：</th>
									<td><input name="cust_name" id="cust_name" class="width_c" onchange="chinese()"/></td>

									<th>${app:i18n('merchant.merchant_code')}：</th>
									<td><input name="merchant_code" id="merchant_code"
										class="width_c" /></td>

								</tr>
								<tr>
									<th>${app:i18n('merchant.merchant_name')}：</th>
									<td><input name="merchant_name" id="merchant_name"
										class="width_c" /></td>

									<th>${app:i18n('vipNumber.status')}：</th>
									<td>
										<!-- 请选择” “待审核”，“审核失败”，“审核通过” --> <select name="status"
										id="newStatus" style="width: 190px">
											<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
											<s:iterator value="%{newStatusOperaList}" id="statusTypeItem">
												<option value="<s:property value="#statusTypeItem.key" />">
													<s:property value="#statusTypeItem.value" />
												</option>
											</s:iterator>
									</select>
									</td>

									<th>${app:i18n('merchant.riskLevel')}：</th>
									<td><select name="riskLevel" id="risk_level"
										style="width: 190px">
											<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
											<s:iterator value="%{riskLevelList}" id="riskLevelItem">
												<option value="<s:property value="#riskLevelItem.key" />">
													<s:property value="#riskLevelItem.value" />
												</option>
											</s:iterator>
									</select></td>
								</tr>
								<tr>

									<th>${app:i18n('merchant.mertStatus')}：</th>
									<td><select name="mert_status" id="mert_status"
										style="width: 190px">
											<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
											<s:iterator value="%{mertStatusList}" id="mertStatusItem">
												<option value="<s:property value="#mertStatusItem.key" />">
													<s:property value="#mertStatusItem.value" />
												</option>
											</s:iterator>
									</select></td>


									<th>${app:i18n('merchant.startDate')}：</th>
									<td><input name="createStartTime" id="createStartTime"
										class="Wdate" value="${startTime}"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'createEndTime\')}'})"
										style="width: 188px" /></td>

									<th>${app:i18n('merchant.endDate')}：</th>
									<td><input name="createEndTime" id="createEndTime"
										class="Wdate" value="${endTime}"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:dayEnd})"
										style="width: 188px" /></td>
								</tr>
							</tbody>
						</table>
						<div class="btn_layout">
							<a name="search" id="searchBt" class="easyui-linkbutton l-btn"
								href="#"><span class="l-btn-left"><span
									class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
							<a name="reset" id="resetBt" class="easyui-linkbutton l-btn"
								href="#"><span class="l-btn-left"><span
									class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
						</div>
					</div>
				</div>

				<div class="block">
					<table id="gridTable">
					</table>
					<div id="gridPager"></div>
					<div id="gridToolbar">
					<app:isPermission resource='CORE_MERT_INFO_CREATE'>
						<a id="addBtn" class="l-btn-plain l-btn m-l4"><span
							class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a>
						</app:isPermission>
							<app:isPermission resource='CORE_MERT_INFO_UPDATE'>
						<a id="updateBtn" class="l-btn-plain l-btn m-l4"><span
							class="l-btn-left"><span class="l-btn-text icon-edit">${app:i18n('global.jsp.modify')}</span></span></a>
						</app:isPermission>
						<app:isPermission resource='CORE_MERT_INFO_REVIEW'>
						<a id="reviewBtn" class="l-btn-plain l-btn m-l4"><span
							class="l-btn-left"><span class="l-btn-text icon-select">${app:i18n('merchant.review')}</span></span></a>
						</app:isPermission>
						<a id="viewBtn" class="l-btn-plain l-btn m-l4"><span
							class="l-btn-left"><span class="l-btn-text icon-view">${app:i18n('global.jsp.detail')}</span></span></a>
						<app:isPermission resource='CORE_MERT_INFO_APPLY'>
						<a id="applyBtn" class="l-btn-plain l-btn m-l4"><span
							class="l-btn-left"><span class="l-btn-text icon-view">${app:i18n('merchant.changeStatus')}</span></span></a>
						</app:isPermission>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="dialog-ajax-cnl-view"></div>
</s:form>