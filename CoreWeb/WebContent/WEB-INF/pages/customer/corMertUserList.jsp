<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	var listUrl = "";
	var resetPwd = "corMertUserSelect.action";
	var corporateCertTypeRender="";
	var mertUserStatusRender="";
	var dayEnd = "";//校验查询的结束日期
	//页面初始化
	function initPage(){
		corporateCertTypeRender = $('#corporateCertTypeRender').attr('value');
		mertUserStatusRender =  $('#mertUserStatusRender').attr('value');
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
				return "时间差距不能大于31天";
			}
		}		
		var custCode=$("#cust_code").val();
		var mertCode=$("#merchant_code").val();
		if (null != custCode&&$.trim(custCode) != '') {
			if (custCode.length>15) {
				return "会员号非法";
			}
		}
		
		if (null != mertCode&&$.trim(mertCode) != '') {
			var reg = new RegExp("^[0-9]*$");
			if (mertCode.length>15||!reg.test(mertCode)) {
				return "商户号非法";
			}
		}
		return "";
	}
	function doSearch(){
		// 设置查询参数
		setQueryCondition();
		
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url: "corMertUserSearch.action" }).trigger("reloadGrid");
	}
	
	function setQueryCondition(){
		// 设置查询参数
		$("#gridTable").setPostDataItem("cust_code", $("#cust_code").val());
		$("#gridTable").setPostDataItem("cust_name", $("#cust_name").val());
		$("#gridTable").setPostDataItem("merchant_code", $("#merchant_code").val());
		$("#gridTable").setPostDataItem("merchant_name", $("#merchant_name").val());
		$("#gridTable").setPostDataItem("timeType", $("#timeType").val());
		$("#gridTable").setPostDataItem("createStartTime", $("#createStartTime").val());
		$("#gridTable").setPostDataItem("createEndTime", $("#createEndTime").val());
	}
	//跳转页面事件
	function jumpToJsp(obj){
		var url = resetPwd+"?id="+obj.id;
	//window.location = url;
 		$(dlgUserId).dialog({width:450,height:230,modal:true});
 		openDialog(dlgUserId,obj.title,url);
	}
	
	var dlgUserId ="#dialog-ajax-cnl-view";
	$().ready(function() {
		initPage();
		getTodayTime();
		
		$("#searchBt").click(function(){
			clearWarning();
// 			checkSearch();
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
		});

		//修改
		$("#updateBtn").click(function(){
			$("div.warning").hide();
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			//根据id获取商户状态
		//	var custCode= $("#gridTable").getCell(id,"custCode");
			var merchantCode= $("#gridTable").getCell(id,"merchantCode");
			//var businessEmail=$("#gridTable").getCell(id,"businessResponseEmail");
			if(id == null ){
				$("div.warning").html("请选择用户重置密码！");
				$("div.warning").show();
		    	return false;
			}
			if(id!=null){
				//var url = addUrl+"?operaType="+operaType+"&id"+id;
				var title = "用户密码重置";
				var obj = new Object();
				obj.id = id;
				//obj.operaType = operaType;
				obj.title = title;
				jumpToJsp(obj);
				return;
			}
		});
		
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
					   '${app:i18n('merchant.cust_code')}',
					   '${app:i18n('merchant.cust_name')}',
					   '${app:i18n('mertUser.merchantCode')}',
					   '${app:i18n('mertUser.merchantName')}',
					   '${app:i18n('mertUser.certTye')}',
					   '${app:i18n('mertUser.certNum')}',
					   '${app:i18n('mertUser.userCode')}',
					   '${app:i18n('mertUser.phoneNum')}',
					   '${app:i18n('mertUser.email')}',
// 					   '${app:i18n('mertUser.lastLoginTime')}',
// 					   '${app:i18n('mertUser.lastLoginIP')}',
// 					   '${app:i18n('mertUser.pwdUpdateTime')}',
// 					   '${app:i18n('mertUser.status')}',
					   '${app:i18n('mertUser.creator')}',
					   '${app:i18n('mertUser.createTime')}',
					   '${app:i18n('mertUser.updator')}',
					   '${app:i18n('mertUser.updateTime')}'
					   ],
			colModel:[
					    {name : 'id', 			index :'id',hidden: true},
					    {name : 'custCode', 	index:'custCode'},
					    {name : 'custName', 	index:'custName'},
					    {name : 'merchantCode', 	index:'merchantCode'},
					    {name : 'merchantName', 	index:'merchantName'},
					    {name : 'certType', 	index:'certType',formatter:'select', editoptions:{value:corporateCertTypeRender}},
					    {name : 'certNum',  index:'certNum'},
					    {name : 'userCode',  index:'userCode'},
					    {name : 'phoneNum',  index:'phoneNum'},
					    {name : 'email',  index:'email'},
// 					    {name : 'lastLoginTime',  index:'lastLoginTime',formatter:'date', formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
// 					    {name : 'lastLoginIP',  index:'lastLoginIP'},
// 					    {name : 'pwdUpdateTime',  index:'pwdUpdateTime',formatter:'date', formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
// 					    {name : 'status', 	index:'status',formatter:'select', editoptions:{value:mertUserStatusRender}},			    
					    {name : 'creator',  index:'creator'},
					    {name : 'createTime',  index:'createTime',formatter:'date', formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
					    {name : 'updator',  index:'updator'},
					    {name : 'updateTime',  index:'updateTime',formatter:'date', formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}}  
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('mertUser.corMertUserListJSP.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs');
				//id长度小于等于零时 提示请输入警告
				if(ids.length<=0||ids==null){
					$("div.warning span").html("没有记录"); 
					$("div.warning").show(); 
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
				<h3>${app:i18n('mertUser.corMertUserListJSP.Title')}</h3>
			</div>
			<input id="corporateCertTypeRender"  type="hidden" value="${corporateCertTypeRender}" />
			<input id="mertUserStatusRender"  type="hidden" value="${mertUserStatusRender}" />
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
									<th>${app:i18n('merchant.cust_code')}：</th>
									<td><input name="cust_code" id="cust_code" class="width_c" /></td>

									<th>${app:i18n('merchant.cust_name')}：</th>
									<td><input name="cust_name" id="cust_name" class="width_c" /></td>
									<th>${app:i18n('merchant.merchant_code')}：</th>
									<td><input name="merchant_code" id="merchant_code"
										class="width_c" /></td>
									
								</tr>
								<tr>
								<th>${app:i18n('merchant.merchant_name')}：</th>
									<td><input name="merchant_name" id="merchant_name"
										class="width_c" /></td>								
								<th>${app:i18n('mertUser.timeType')}：</th>
									<td><select name="timeType" id="timeType"
										style="width: 190px">
											<option value="01">${app:i18n('mertUser.createTime')}</option>
											<option value="02">${app:i18n('mertUser.updateTime')}</option>
									</select></td>
								</tr>
								<tr>
								
									<th>${app:i18n('mertUser.startDate')}：</th>
									<td><input name="createStartTime" id="createStartTime"
										class="Wdate" value="${startTime}"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'createEndTime\')}'})"
										style="width: 188px" /></td>

									<th>${app:i18n('mertUser.endDate')}：</th>
									<td><input name="createEndTime" id="createEndTime"
										class="Wdate" value="${endTime}"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:dayEnd})"
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
						<app:isPermission resource='CORE_CUST_MERT_USER_RESETPWD'>
							<a id="updateBtn" class="l-btn-plain l-btn m-l4"><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('mertUser.resetPassword')}</span></span></a>
						</app:isPermission>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="dialog-ajax-cnl-view"></div>
</s:form>