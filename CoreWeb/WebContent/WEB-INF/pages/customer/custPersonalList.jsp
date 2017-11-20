<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	var createUrl 		= "custPersonalCreate.action";
	var listUrl 		= "custPersonalList.action";
	var searchUrl 		= "custPersonalSearch.action";
	var activeUrl 		= "activePersonalAccount.action";
	
	var operaType = "0";   //operaType  : 0 -新增，1-查看，2-审核，3-修改
	var dlgUserId ="#dialog-ajax-cnl-view";
	var dayEnd = "";//校验查询的结束日期
	var nowDate = "";		
	//数据字典  转义串
	var certTypeRender = "";
	var custStatusTypeRender = "";
	var realNameLeveTypeRender = "";
	//页面初始化
	function initPage(){
		//设置数据字典转义串的值
		certTypeRender = '${certTypeRender}';
		custStatusTypeRender = '${custStatusTypeRender}';
		realNameLeveTypeRender = '${realNameLeveTypeRender}';
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
		$("#gridTable").jqGrid("setGridParam",{url: "custPersonalSearch.action" }).trigger("reloadGrid");
	}
	// 设置查询参数
	function setQueryCondition(){
		$("#gridTable").setPostDataItem("custCode", 			$("#scustCode").val());
		$("#gridTable").setPostDataItem("realNameLeve", 		$("#srealNameLeve").val());
		$("#gridTable").setPostDataItem("localName", 			$("#slocalName").val());
		$("#gridTable").setPostDataItem("certType", 			$("#scertType").val());
		$("#gridTable").setPostDataItem("certNum", 				$("#scertNum").val());
		$("#gridTable").setPostDataItem("telephone", 			$("#stelephone").val());
		$("#gridTable").setPostDataItem("custStatus", 			$("#scustStatus").val());
		$("#gridTable").setPostDataItem("startCreateTime", 		$("#startCreateTime").val());
		$("#gridTable").setPostDataItem("endCreateTime", 		$("#endCreateTime").val());
		
	}
	//跳转页面事件
	function jumpToJsp(obj){
		var url = createUrl+"?operaType="+obj.operaType+"&id="+obj.id;
		$(dlgUserId).dialog({width:650,height:750,modal:true});
		openDialog(dlgUserId,obj.title,url);
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
		
		//查看按钮事件0 -新增，1-查看，2-审核，3-修改
		$("#viewBtn").click(function(){
			clearWarning();
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			if(id!=null){
				operaType = "1";//0 -新增，1-查看，2-审核，3-修改
				var url = createUrl+"?operaType="+operaType+"&id"+id;
				var title = "个人会员信息查看";
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
		$("#activeBtn").click(function(){
			clearWarning();
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			if(id!=null){
				
				var custStatus = $("#gridTable").getCell(id,"custStatus");
				if(custStatus != "01"){
					showWarning("只允许激活未生效的个人会员");
				    return false;
				}
				$.ajax({
			          type: "post",
			          url: "activePersonalAccount.action",
			          data: {id:id},
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
			}else{
				showWarning("请选择一条信息查看！");
			    return false;
			}
		});
		
		//查询结果列表
		$("#gridTable").gridUtil().simpleGrid({
			url: listUrl,
			sortname:'id',
			sortorder:'desc',
			multiselect:false,
 	        shrinkToFit: false,
	        autowidth:true,
			colNames:[
			          'ID', 
					   '${app:i18n('custPersonalDto.custCode')}',
					   '${app:i18n('custPersonalDto.custStatus')}',
					   '${app:i18n('custPersonalDto.localName')}',
					   '${app:i18n('custPersonalDto.gender')}',
					   '${app:i18n('custPersonalDto.realNameLeve')}',
					   '${app:i18n('custPersonalDto.telephone')}',
					   
					   '${app:i18n('custPersonalDto.email')}',
					   '${app:i18n('custPersonalDto.certType')}',
					   '${app:i18n('custPersonalDto.certNum')}',
					   
					   
					   '${app:i18n('custPersonalDto.creator')}',
					   '${app:i18n('custPersonalDto.createTime')}',
					   '${app:i18n('custPersonalDto.updator')}',
					   '${app:i18n('custPersonalDto.updateTime')}'
					   
					   ],
			colModel:[
					    {name : 'id', 			index :'id',hidden: true},
					    {name : 'custCode', 	index:'custCode'},
					    {name : 'custStatus', 	index:'custStatus',formatter:'select', editoptions:{value : custStatusTypeRender}},
					    {name : 'localName', 	index:'localName'    },
					    {name : 'gender', 		index:'gender' },
					    {name : 'realNameLeve', 	index:'realNameLeve' ,formatter:'select', editoptions:{value : realNameLeveTypeRender}},
					    {name : 'telephone', 		index:'telephone'},
					    
					    {name : 'email',  index:'email'},
					    {name : 'certType',  index:'certType'  ,formatter:'select', editoptions:{value : certTypeRender}},
					    {name : 'certNum',  index:'certNum'},
					    
					    {name : 'creator',  index:'creator'},
					    {name : 'createTime',  index:'createTime',formatter:'date', formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
					    {name : 'updator',  index:'updator'},
					    {name : 'updateTime',  index:'updateTime',formatter:'date', formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}}
					   
					    
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('custPersonalDto.ListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs');
				//id长度小于等于零时 提示请输入警告
				if(ids==null||ids.length<=0){
					showWarning("没有记录"); 
					return;
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
			<h3>${app:i18n('custPersonalDto.ListJsp.Title')}</h3>
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
								<th>${app:i18n('custPersonalDto.realNameLeve')}：</th>
								<td>
									<select  id="srealNameLeve" style="width:190px" >
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{realNameLeveTypeList}" id="item">
									        <option value="<s:property value="#item.key" />">
									        	<s:property value="#item.value" />
									        </option>
										</s:iterator>
							        </select>
							    </td>
								<th>${app:i18n('custPersonalDto.custCode')}：</th>
								<td><input  id="scustCode" class="width_c" /></td>
								<th>${app:i18n('custPersonalDto.localName')}：</th>
								<td><input  id="slocalName" class="width_c" /></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.certType')}：</th>
								<td>
									<select id="scertType" style="width:190px">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{certTypeList}" id="item">
									        <option value="<s:property value="#item.key" />">
									        	<s:property value="#item.value" />
									        </option>
										</s:iterator>
							        </select>
							    </td>
								<th>${app:i18n('custPersonalDto.certNum')}：</th>
								<td><input  id="scertNum" class="width_c" /></td>
								<th>${app:i18n('custPersonalDto.telephone')}：</th>
								<td><input  id="stelephone" class="width_c" /></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.custStatus')}：</th>
								<td>
									<select  id="scustStatus" style="width:190px">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{custStatusTypeList}" id="statusTypeItem">
									        <option value="<s:property value="#statusTypeItem.key" />">
									        	<s:property value="#statusTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
							    </td>

								<th>${app:i18n('custPersonalDto.startCreateTime')}：</th>									  		   
								<td><input name="startCreateTime" id="startCreateTime" class="Wdate" value="${startTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endCreateTime\')}'})" style="width:188px"/></td>
								<th>${app:i18n('custPersonalDto.endCreateTime')}：</th>
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
					<app:isPermission resource='CORE_CUST_PERSONAL_VIEW'>
						<a id="viewBtn" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-view">${app:i18n('global.jsp.detail')}</span></span></a> 
					</app:isPermission>
					<app:isPermission resource='CORE_CUST_PERSONAL_ACTIVE'>
						<a id="activeBtn" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-view">${app:i18n('custPersonalDto.activeBtn')}</span></span></a> 
					</app:isPermission>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="dialog-ajax-cnl-view"></div>
</s:form>
