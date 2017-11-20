<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/metas.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
		var modifyUrl = "corPreOutcomeModify.action";
		var deleteUrl = "corPreOutcomeDelete.action";
		var createUrl = "corPreOutcomeCreate.action";
		var searchUrl = "corPreOutcomeSearch.action";
		var exportUrl = "corPreOutcomeExport.action";
		var dlgUserId ="#dialog-ajax-pre-view";
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
	$().ready(function() {
		
		
		$("#search").click(function(){
			clearWarning();
			doSearch();
		});

		$("#reset").click(function(){
			// 设置查询参数为空
			clearWarning();
			$("#queryField :input").val("");
			$("#queryField :select").val("");
		});

		$("#export").click(function(){
			clearWarning();
			// 设置查询参数
			setQueryCondition();
			$("#gridTable").gridUtil().exportExcel({url: exportUrl});
		});
		
		$("#create").click(function(){
			window.location.href=createUrl;
		});
		
		$("#view").click(function(){
			clearWarning();
			//获取选中行的id，即数据库id
			var id = $('#gridTable').jqGrid('getGridParam','selrow');
			if(id == null||id==""){
				showWarning("请选择一条记录查看！");
		    	return;
			} else {
				var title = "预出款查看";
				var obj = new Object();
				obj.id = id;
				obj.title = title;
				jumpToJsp(obj);
			}
		});

		$("#delete").click( function() {
			var gr;
			gr = $("#gridTable").jqGrid('getGridParam','selarrrow');
			if (gr.length != 1) {
				alert("请选择一行修改");
				return;
			}
			//$("#gridTable").jqGrid('delGridRow',gr,{reloadAfterSubmit:false}); 
			$("#corPreOutcomeListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#corPreOutcomeListForm").submit();
		});
		
		$("#gridTable").gridUtil().simpleGrid({
			url: '',
			editurl:"#deleteUrl",
			sortname:'updateTime', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			colNames:['ID', 
					'${app:i18n('corPreOutcome.merchantCode')}',
					'${app:i18n('corPreOutcome.merchantName')}',
					'${app:i18n('corPreOutcome.settPeriod')}',
					'${app:i18n('corPreOutcome.currency')}',
					'${app:i18n('corPreOutcome.transDate')}',
					'${app:i18n('corPreOutcome.transAmount')}',
					'${app:i18n('corPreOutcome.refundAmount')}',
					'${app:i18n('corPreOutcome.serviceCharge')}',
					/* '${app:i18n('corPreOutcome.frozenAmount')}',
					'${app:i18n('corPreOutcome.unfrozenAmount')}', */
					'${app:i18n('corPreOutcome.preOutcomeAmount')}',
					'${app:i18n('corPreOutcome.preOutcomeDate')}',
					'${app:i18n('corPreOutcome.preOutcomeBankCode')}',
					/* '${app:i18n('corPreOutcome.creator')}',
					'${app:i18n('corPreOutcome.createTime')}',
					'${app:i18n('corPreOutcome.updator')}',
					'${app:i18n('corPreOutcome.updateTime')}',
					'${app:i18n('corPreOutcome.isValid')}',
					   '${app:i18n('global.jsp.operation')}' */ ],
			colModel:[
						{name : 'id', hidden: true},
						{name : 'merchantCode',index : 'merchantCode'},
						{name : 'merchantName',index : 'merchantName'},
						{name : 'settPeriod',index : 'settPeriod'},
						{name : 'currency',index : 'currency'},
						{name : 'transDate',index : 'transDate'},
						{name : 'transAmount',index : 'transAmount',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
						{name : 'refundAmount',index : 'refundAmount',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
						{name : 'serviceCharge',index : 'serviceCharge',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
						/* {name : 'frozenAmount',index : 'frozenAmount',width : '10%'},
						{name : 'unfrozenAmount',index : 'unfrozenAmount',width : '10%'}, */
						{name : 'preOutcomeAmount',index : 'preOutcomeAmount',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
						{name : 'preOutcomeDate',index : 'preOutcomeDate'},
						{name : 'preOutcomeBankCode',index : 'preOutcomeBankCode'},
						/* {name : 'creator',index : 'creator',width : '10%'},
						{name : 'createTime',index : 'createTime',width : '10%'},
						{name : 'updator',index : 'updator',width : '10%'},
						{name : 'updateTime',index : 'updateTime',width : '10%'},
						{name : 'isValid',index : 'isValid',width : '10%'},
					    {name:  'operation',width:'10%',align:'center', search:false,sortable:false,editable:true,title:false}, */
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('corPreOutcome.corPreOutcomeListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
				if(ids==null||ids.length<=0){
					showWarning("没有记录");
				}
				for(var i=0;i < ids.length;i++) {
					var all = "";
					var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id\"'><em>${app:i18n('global.jsp.modify')}</em></a>";

					mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]);
					
					var id = ids[i];
					var rowData = $("#gridTable").getRowData(id);

					all = all + mod;

					$("#gridTable").jqGrid('setRowData',ids[i],{operation:all});
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
	
	function doSearch(){
		// 设置查询参数
		setQueryCondition();
		
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url:searchUrl}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数

		$("#gridTable").setPostDataItem("merchantCode", $("#merchantCode").val());
		$("#gridTable").setPostDataItem("settPeriod", $("#settPeriod").val());
		$("#gridTable").setPostDataItem("currency", $("#currency").val());
		$("#gridTable").setPostDataItem("transDate", $("#transDate").val());
		$("#gridTable").setPostDataItem("transAmount", $("#transAmount").val());
		$("#gridTable").setPostDataItem("refundAmount", $("#refundAmount").val());
		$("#gridTable").setPostDataItem("serviceCharge", $("#serviceCharge").val());
		$("#gridTable").setPostDataItem("frozenAmount", $("#frozenAmount").val());
		$("#gridTable").setPostDataItem("unfrozenAmount", $("#unfrozenAmount").val());
		$("#gridTable").setPostDataItem("preOutcomeAmount", $("#preOutcomeAmount").val());
		$("#gridTable").setPostDataItem("preOutcomeDate", $("#preOutcomeDate").val());
		$("#gridTable").setPostDataItem("preOutcomeBankCode", $("#preOutcomeBankCode").val());
		$("#gridTable").setPostDataItem("creator", $("#creator").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("updator", $("#updator").val());
		$("#gridTable").setPostDataItem("updateTime", $("#updateTime").val());
		$("#gridTable").setPostDataItem("isValid", $("#isValid").val());

	}
	
	//跳转页面事件
	function jumpToJsp(obj){
		var url = modifyUrl+"?id="+obj.id;
		$(dlgUserId).dialog({width:350,height:400,modal:true});
		openDialog(dlgUserId,obj.title,url);
	}
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
	function refreshGrid(){
		$("#gridTable").trigger("reloadGrid");//重新载入
	}
	
</script>

<s:form id="corPreOutcomeListForm" method="post" action="corPreOutcomeList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('corPreOutcome.corPreOutcomeListJsp.headTitle')}</h3>
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
								<th width="15%">${app:i18n('corPreOutcome.preOutcomeBankCode')}：</th>
								<td width="20%">
									<select style="width:188px" name="preOutcomeBankCode" id="preOutcomeBankCode" class="width_c"  >
										<option value="">请选择</option>
										<s:iterator  value="preOutcomeBankCodeList" var="pre">
											<option value="${pre.key}">${pre.value}</option>
										</s:iterator>
									</select>
								</td>
								
								<th width="15%">${app:i18n('corPreOutcome.currency')}：</th>
								<td width="20%">
									<select style="width:188px" name ="currency" id ="currency">
										<s:iterator  value="currencyList" var="s">
											<c:if test="${s.key == 50 }">
												<option value="${s.key}">${s.value}</option>
											</c:if>
										</s:iterator>
										<s:iterator  value="currencyList" var="s">
											<c:if test="${s.key != 50 }">
												<option value="${s.key}">${s.value}</option>
											</c:if>
										</s:iterator>
									</select>
								</td>

								<th width="15%">${app:i18n('corPreOutcome.preOutcomeDate')}：</th>
								<td width="20%">
									<input name="preOutcomeDate" id="preOutcomeDate" value="${validDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"  style="width:188px"/>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="btn_layout">
						<a name="search" id="search" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
						<a name="reset" id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
						<a name="export" id="export" class="easyui-linkbutton l-btn" href="#"><span	class="l-btn-left"><span class="l-btn-text icon-excel">${app:i18n('global.jsp.export')}</span></span></a> 
					</div>
				</div>
			</div>
					
			<div class="block">
				<table id="gridTable">
				</table>
				<div id="gridPager"></div>
				<div id="gridToolbar">
					<a id="view" class="l-btn-plain l-btn m-14"><span class="l-btn-left"><span class="l-btn-text icon-view">${app:i18n('global.jsp.view')}</span></span></a>
				</div>
			</div>
		</div>
	</div>
</div>
</s:form>
<div id="dialog-ajax-pre-view"></div>
