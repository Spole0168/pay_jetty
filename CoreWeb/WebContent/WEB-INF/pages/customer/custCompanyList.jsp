<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	var createUrl 		= "custCompanyCreate.action";
	var listUrl 		= "custCompanyList.action";
	var searchUrl 		= "custCompanySearch.action";
	var operaType = "0";   //operaType  : 0 -新增，1-查看，2-审核，3-修改
	var dlgUserId ="#dialog-ajax-cnl-view";
	var dayEnd = "";//校验查询的结束日期
	var nowDate = "";		
	//数据字典  转义串
	var custTypeRender = "";
	var unitTypeRender = "";
	var corporateCertTypeRender = "";
	var statusTypeRender = "";
	var custStatusTypeRender = "";
	//页面初始化
	function initPage(){
		//设置数据字典转义串的值
		custTypeRender = '${custTypeRender}';
		unitTypeRender = '${unitTypeRender}';
		corporateCertTypeRender = '${corporateCertTypeRender}';
		statusTypeRender = '${statusTypeRender}';
		custStatusTypeRender = '${custStatusTypeRender}';
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
	    $("#startCreateTime").val(s1);
	    $("#endCreateTime").val(s2);
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
		var startTime = $("#startCreateTime").val();
		var endTime = $("#endCreateTime").val();
		if (null != startTime && startTime.length > 0 && (null == endTime || endTime.length == 0)) {
			showWarning("开始时间不为空, 结束时间必填");
			return false;
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
				showWarning("结束时间不能大于当前时间");
				return false;
			}else if(times >= 0) {
				showWarning("开始时间不能大于结束时间");
				return false;
			} else if (days > 30) {
				showWarning("时间差距不能大于30天");
				return false;
			}
		}
		return true;
	}
	//查询事件
	function doSearch(){
		// 设置查询参数
		setQueryCondition();
		
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url: "custCompanySearch.action" }).trigger("reloadGrid");
	}
	// 设置查询参数
	function setQueryCondition(){
		$("#gridTable").setPostDataItem("custCode", 			$("#searchCustCode").val());
		$("#gridTable").setPostDataItem("localName", 			$("#searchLocalName").val());
		$("#gridTable").setPostDataItem("unitType", 			$("#searchUnitType").val());
		$("#gridTable").setPostDataItem("socialCreditId", 		$("#searchSocialCreditId").val());
		$("#gridTable").setPostDataItem("businessLicense", 		$("#searchBusinessLicense").val());
		$("#gridTable").setPostDataItem("status", 				$("#auditStatus").val());
		$("#gridTable").setPostDataItem("custStatus", 			$("#searchcustStatus").val());
		$("#gridTable").setPostDataItem("startCreateTime", 		$("#startCreateTime").val());
		$("#gridTable").setPostDataItem("endCreateTime", 		$("#endCreateTime").val());
		
	}
	//跳转页面事件
	function jumpToJsp(obj){
		var url = createUrl+"?operaType="+obj.operaType+"&id="+obj.id;
// 		window.location = url;
// 		return;
		$(dlgUserId).dialog({width:650,height:750,modal:true});
		openDialog(dlgUserId,obj.title,url);
	}
	function activeAccount(obj){
		//根据id获取 获得用户是否已激活
		 $.ajax({
	          type: "post",
	          url: "custActiveAccount.action",
	          data: {id:obj},
	          dataType: "json",
	          async:false,
	          success: function(data){
        	  	var  msgs =  "账户激活成功";
        	  	if(data.status == CORE_AJAX_RESULT_N){//不能操作
        	  		msgs = "账户激活失败";
	          	}
        	  	//更新列表
        	  	doSearch();
       		  	showWarning(msgs);
       			return;
	          }
	      });
		
	}
	//页面出事化加载信息
	$().ready(function() {
		initPage();
		//查询按钮事件
		$("#searchBt").click(function(){
			clearWarning();
			if(!checkSearch()){
				return false;
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
			var title = "企业会员新增";
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
				var title = "企业会员信息查看";
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
				var title = "会员信息信息审核";
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
			var status= $("#gridTable").getCell(id,"status");
			var custStatus= $("#gridTable").getCell(id,"custStatus");
			if("01"==status){
				showWarning("待审核的记录不可进行修改！");
				return;
			}
// 			if(		  (custStatus=="01"&&"03"==status)////只能对会员状态为未生效，审核状态为审核失败
// 					||(custStatus=="02"&&"02"==status)//会员状态为已生效，审核状态为审核通过的记录进行修改
// 					){
			operaType = "3";//0 -新增，1-查看，2-审核，3-修改
			var url = createUrl+"?operaType="+operaType+"&id"+id;
			var title = "企业会员信息修改";
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
			          'CMUID', 
					   '${app:i18n('vipNumber.custCode')}',
					   '${app:i18n('vipNumber.custType')}',
					   '${app:i18n('vipNumber.localName')}',
					   '${app:i18n('vipNumber.unitType')}',
					   '${app:i18n('vipNumber.socialCreditId')}',
					   '${app:i18n('vipNumber.taxId')}',
					   
					   '${app:i18n('vipNumber.companyCode')}',
					   '${app:i18n('vipNumber.businessLicense')}',
					   
					   '${app:i18n('vipNumber.corporateName')}',
					   '${app:i18n('vipNumber.corporateCertType')}',
					   '${app:i18n('vipNumber.corporateCertNum')}',
					   '${app:i18n('vipNumber.corporatePhonenum')}',
					   
					   '${app:i18n('vipNumber.creator')}',
					   '${app:i18n('vipNumber.createTime')}',
					   '${app:i18n('vipNumber.updator')}',
					   '${app:i18n('vipNumber.updateTime')}',
					   
					   '${app:i18n('vipNumber.status')}',
					   '${app:i18n('vipNumber.auditPerson')}',
					   '${app:i18n('vipNumber.auditTime')}',
					   '${app:i18n('vipNumber.auditReason')}',
					   '${app:i18n('vipNumber.custStatus')}',
					   '${app:i18n('vipNumber.activeOpera')}'
					   ],
			colModel:[
					    {name : 'id', 			index :'id',hidden: true},
					    {name : 'cmuid', 		index :'cmuid',hidden: true},
					    {name : 'custCode', 	index:'custCode'},
					    {name : 'custType', 	index:'custType',formatter:'select', editoptions:{value:custTypeRender}},
					    {name : 'localName', 	index:'localName'    },
					    {name : 'unitType', 	index:'unitType',formatter:'select', editoptions:{value:unitTypeRender}},
					    {name : 'socialCreditId', 	index:'socialCreditId'},
					    {name : 'taxId', 		index:'taxId'},
					    
					    {name : 'companyCode',  index:'companyCode'},
					    {name : 'businessLicense',  index:'businessLicense'},
					    {name : 'corporateName',  index:'corporateName'},
					    {name : 'corporateCertType',  index:'corporateCertType',formatter:'select', editoptions:{value:corporateCertTypeRender}}, 
					    {name : 'corporateCertNum',  index:'corporateCertNum'},
					    {name : 'corporatePhonenum',  index:'corporatePhonenum'},
					    
					    {name : 'creator',  index:'creator'},
					    {name : 'createTime',  index:'createTime',formatter:'date', formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
					    {name : 'updator',  index:'updator'},
					    {name : 'updateTime',  index:'updateTime',formatter:'date', formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
					   
					    {name : 'status',  index:'status', formatter:'select', editoptions:{value:statusTypeRender}},
					    {name : 'auditPerson',  index:'auditPerson'},
					    {name : 'auditTime',  index:'auditTime',formatter:'date', formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
					    {name : 'auditReason',  index:'auditReason'},
					    {name : 'custStatus',  index:'custStatus', formatter:'select', editoptions:{value : custStatusTypeRender}},
					    {name:  'operation',align:'center', search:false,sortable:false,editable:true,title:false},
					    
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('vipNumber.vipNumberListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs');
				//id长度小于等于零时 提示请输入警告
				if(ids==null||ids.length<=0){
					showWarning("没有记录"); 
					return;
				}else{
					//增加激活账户按钮
					for(var i=0;i < ids.length;i++) {
						//1.激活帐户按钮只适用于审核通过的记录，第一次审核通过，激活帐户后，按纽变灰，不可重复激活。
						//2.点击“激活账户“按钮，系统生成登录名，登陆密码。(登录名登录密码生成规则参考实体属性编号规则表）
								//，弹出提示框提示激活成功。同时将登录名和密码信息用邮件发送给企业联系人邮箱。
						//必须是第一次审核通过才会显示  激活按钮
						var idNum = ids[i];
						var status= $("#gridTable").getCell(idNum,"status");
						var cmuid= $("#gridTable").getCell(idNum,"cmuid");
						if(status=="02"&&(null==cmuid||""==cmuid)){
							var all = "";
							var mod = "<a href='#' class='icon-edit m-r ' onclick=\"activeAccount('" + idNum + "')\"><em>${app:i18n('vipNumber.activeOperaBt')}</em></a>";
							
							var rowData = $("#gridTable").getRowData(idNum);
							all = all + mod;
	
							$("#gridTable").jqGrid('setRowData',idNum,{operation:all});
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
			<h3>${app:i18n('vipNumber.vipNumberListJsp.Title')}</h3>
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
								<th>${app:i18n('vipNumber.unitType')}：</th>
								<td><!-- 中外合资，民营，国企，事业单位，其他 -->
									<select name="unitType" id="searchUnitType" style="width:190px" >
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{unitTypeList}" id="unitTypeItem">
									        <option value="<s:property value="#unitTypeItem.key" />">
									        	<s:property value="#unitTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
							    </td>
								<th>${app:i18n('vipNumber.custCode')}：</th>
								<td><input name="searchCustCode" id="searchCustCode" class="width_c" /></td>
								<th>${app:i18n('vipNumber.localName')}：</th>
								<td><input name="searchCustCode" id="searchLocalName" class="width_c" /></td>
							</tr>
							<tr>
								<th>${app:i18n('vipNumber.status')}：</th>
								<td><!-- 请选择” “待审核”，“审核失败”，“审核通过” -->
									<select name="auditStatus" id="auditStatus" style="width:190px">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{statusTypeList}" id="statusTypeItem">
									        <option value="<s:property value="#statusTypeItem.key" />">
									        	<s:property value="#statusTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
							    </td>
								<th>${app:i18n('vipNumber.socialCreditId')}：</th>
								<td><input name="searchSocialCreditId" id="searchSocialCreditId" class="width_c" /></td>
								<th>${app:i18n('vipNumber.businessLicense')}：</th>
								<td><input name="searchBusinessLicense" id="searchBusinessLicense" class="width_c" /></td>
							</tr>
							<tr>
								<th>${app:i18n('vipNumber.custStatus')}：</th>
								<td><!-- 请选择” “待审核”，“审核失败”，“审核通过” -->
									<select name="searchcustStatus" id="searchcustStatus" style="width:190px">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{custStatusTypeList}" id="statusTypeItem">
									        <option value="<s:property value="#statusTypeItem.key" />">
									        	<s:property value="#statusTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
							    </td>

								<th>${app:i18n('vipNumber.startCreateTime')}：</th>									  		   
								<td><input name="startCreateTime" id="startCreateTime" class="Wdate" value="${startTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateTime\')}'})" style="width:188px"/></td>
								<th>${app:i18n('vipNumber.endCreateTime')}：</th>
								<td><input name="endCreateTime" id="endCreateTime" class="Wdate" value="${endTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:dayEnd})" style="width:188px"/></td>
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
					<app:isPermission resource='CORE_CUST_COMPANY_ADD'>
						<a id="addBtn" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a> 
					</app:isPermission>
					<app:isPermission resource='CORE_CUST_COMPANY_EDIT'>
						<a id="editBtn" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-edit">${app:i18n('global.jsp.modify')}</span></span></a> 
					</app:isPermission>
					<app:isPermission resource='CORE_CUST_COMPANY_AUDIT'>
						<a id="auditBtn" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-select">${app:i18n('global.jsp.audit')}</span></span></a> 
					</app:isPermission>
					<app:isPermission resource='CORE_CUST_COMPANY_VIEW'>
						<a id="viewBtn" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-view">${app:i18n('global.jsp.view')}</span></span></a> 
					</app:isPermission>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="dialog-ajax-cnl-view"></div>
</s:form>
