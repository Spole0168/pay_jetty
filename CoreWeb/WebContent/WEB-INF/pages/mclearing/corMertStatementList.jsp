<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/metas.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	var searchUrl = "corMertStatementSearch.action";
	//93天钱的时间
	function get93DaysBefore(){
		var today=new Date();
		 var ago93Day = new Date(Date.parse(new Date().toString()) - 86400000*93);
		 function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
		 var date = ago93Day.getFullYear().toString() +"-"+ addzero(ago93Day.getMonth() + 1) +"-" + addzero(ago93Day.getDate())
		 var resultDate = date +" 00:00:00";
		 $("#ago93Day").val(resultDate);
	}
	//当前时间
	function getTodayTime(){
		var d = new Date();
	    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
	    var date=d.getFullYear().toString() +"-"+ addzero(d.getMonth() + 1) +"-" + addzero(d.getDate())
	    var s1 = date +" 00:00:00";
	    var s2 = date +" 23:59:59";
	    $("#startTime").val(s1);
	    $("#endTime").val(s2);
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
	//时间格式校验
	function checkTime(){
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		
		if(null!=startTime&&startTime.length>0&&null!=endTime&&endTime.length>0){
			startTime = startTime.replace(/-/g,'/');
			endTime = endTime.replace(/-/g,'/');
			startTime = new Date(startTime);
			endTime = new Date(endTime);
			var times = startTime.getTime() - endTime.getTime();
			var days = Math.abs(parseInt(times/(1000*60*60*24)));
			if(times >= 0) {
				showWarning("开始时间不能大于结束时间");
				return false;
			} else if (days > 93) {
				showWarning("时间差距不能大于93天");
				return false;
			}else{
				return true;
			}
		}else{
			showWarning("请输入正确的开始时间和结束时间");
			return false;
		}
		
	}
	function checkSearch(){
		var merchantCode = $("#merchantCode").val();
		var merchantName = $("#merchantName").val();
		var statementCode = $("#statementCode").val();
		var settStatus = $("#settStatus option:selected").val();
		var status = $("#status option:selected").val();
		var timeType = $("#timeType option:selected").val();
		var pattern=/[`~!@#\$%\^\&\*\(\)_\+<>\?:"\{\},\.\\\/;'\[\]]/im; 
		
		if(merchantCode != null && merchantCode.trim() !="" ){
		    if(pattern.test(merchantCode)){  
		    	showWarning("请输入正确的商户编号");
		    	return false;     
		    }     
		}
		if(merchantName != null && merchantName.trim() !="" ){
		    if(pattern.test(merchantName)){  
		    	showWarning("请输入正确的商户名称");
		    	return false;     
		    }     
		}
		if(statementCode != null && statementCode.trim() !="" ){
		    if(pattern.test(statementCode)){  
		    	showWarning("请输入正确的对账单编号");
		    	return false;     
		    }     
		}
		return checkTime();
	}
	
	
	
	$().ready(function() {
		get93DaysBefore();
		getTodayTime();
		
		$("#search").click(function(){
			clearWarning();
			if(checkSearch()){
				doSearch();
			}
			
		});
		$("#reset").click(function(){
			// 设置查询参数为空
			$("#queryField :input").val("");
		});
		
		//上传功能
		$("#upload").click(function(){
			clearWarning();
			var id = $('#gridTable').jqGrid('getGridParam','selrow');
			var ids = $("#gridTable").jqGrid('getGridParam', 'selarrrow');
			if(ids.length > 1){
				showWarning("只能选择一条记录进行操作");
				return;
			}
			if( id == null || id == ""){
				showWarning("请选择一条记录进行操作");
				return;
			}
			var status = $("#gridTable").getCell(id,"status");
			var settStatus = $("#gridTable").getCell(id,"settStatus");
			var isUpload = $("#gridTable").getCell(id,"isUpload");
			if(isUpload=="01"){
				showWarning("该结算单已上传，请重新选择");
				return;
			}
			
			if(status == "01" && settStatus == "02"){
				//账单状态为“正常”01，结算状态为“已结算”02，且查询列表处的“是否已上传到FTP=否”02
				$("#id").val('');//清空输入狂内容
				$("#id").val(id);//输入框赋值
				$.boxUtil.confirm(
						'确定上传这条记录吗？', 
						null, 
						function(){
							$("#uploadFileForm").submit();
					});
			}else{
				//提示信息待确定
				showWarning("只有结算单状态为正常，结算状态为已结算才可以上传");
				return;
			}
		});
		
		
		//下载
		$("#download").click(function(){
			clearWarning();
			var ids = $("#gridTable").jqGrid('getGridParam', 'selarrrow');
			if( ids == null || ids.length == 0){
				showWarning("请至少选择一条记录进行操作");
				return;
			}
			if(ids.length > 1){
				showWarning("只能选择一条记录进行操作");
				return;
			}
			 	//多条记录
				var idLists=ids[0] ;//所有的id
				var isUpload = $("#gridTable").getCell(ids[0],"isUpload");
				if( isUpload == "01" ){
					$("#idList").val('');//清空输入狂内容
					$("#idList").val(idLists);//输入框赋值
					$.boxUtil.confirm(
							'确定下载这些记录吗？', 
							null, 
							function(){
								$("#downloadFileForm").submit();
								//showWarning("下载成功...");
							});
				}else{
					//提示信息待确定
					showWarning("只能下载已上传到FTP的数据");
					return;
				}
		});
		
		
		
		//通知商户
		$("#notifyMerchant").click(function(){
			clearWarning();
			var ids = $("#gridTable").jqGrid('getGridParam', 'selarrrow');
			if( ids == null || ids.length == 0){
				showWarning("请至少选择一条记录进行操作");
				return;
			}
			//多条记录
			var idLists='' ;//所有的id
			for(var i=0;i<ids.length;i++){
				var id = ids[i];
				var status = $("#gridTable").getCell(id,"status");
				var settStatus = $("#gridTable").getCell(id,"settStatus");
				var isUpload = $("#gridTable").getCell(id,"isUpload");
				if( isUpload == "01" ){
					//可以勾选多条 “是否上传到FTP=是”的数据进行该操作
					if(id != ''){
						idLists += ',';
						idLists += id;
					}
				}else{
					//提示信息待确定
					showWarning("该结算单为未上传，不能通知商户");
					return;
				}
				
			}
			
			$.boxUtil.confirm(
					'确定通知这些商户吗？', 
					null, 
					function(){
						//执行操作
						$.ajax({
			                url : 'sendMsg2Merchant.action',
			                type : 'post',
			                dataType : 'json',
			                data : {
			                	idList:idLists
			                }, //前台到后台的参数
			                success : function(data) {
			                	showWarning("通知商户"+data.result);
			                },
			                error : function(data) {
			                	showWarning("通知商户"+data.result);
			                }

			          });    
					});
		});
//--------->>
		//作废
		$("#invalid").click(function(){
			clearWarning();
			var ids = $("#gridTable").jqGrid('getGridParam', 'selarrrow');
			if( ids == null || ids.length == 0){
				showWarning("请选择一条记录进行操作");
				return;
			}
			if(ids.length > 1){
				showWarning("只能选择一条结算单状态为异常的数据，请重新选择");
				return;
			}
			var status = $("#gridTable").getCell(ids[0],"status");
			var settStatus = $("#gridTable").getCell(ids[0],"settStatus");
			var checkStatus = $("#gridTable").getCell(ids[0],"checkStatus");
			if( status == "02" && settStatus == "03" || checkStatus == "03"){
				//账单状态为“异常”，结算状态为“延期结算” 或者审核失败
				var url ="openAbandon.action?id="+ids[0];
				var title = "作废订单";
				$(dlgId).dialog({width:500,height:220,modal:true});
				 openDialog(dlgId,title,url);
			}else{
				//提示信息待确定
				showWarning("只能选择一条结算单状态为异常、结算状态为延期结算的数据，请重新选择");
				return;
			}
		});
		//重新生成
		$("#reGenerator").click(function(){
			clearWarning();
			var ids = $("#gridTable").jqGrid('getGridParam', 'selarrrow');
			if( ids == null || ids.length == 0){
				showWarning("请至少选择一条记录进行操作");
				return;
			}
			
				
			//多条记录
			var idList='' ;//所有的id
			for(var i=0;i<ids.length;i++){
				var id = ids[i];
				var status = $("#gridTable").getCell(id,"status");
				var settStatus = $("#gridTable").getCell(id,"settStatus");
				//status == "异常" && settStatus == "延期结算" 
				if(status == "03" && settStatus == "04"){
					//可以勾选多条账单状态为“异常”，结算状态为“延期结算”的数据进行该操作
					if(id != ''){
						idList += ',';
						idList += id;
					}
				}else{
					//提示信息待确定
					showWarning("所选数据不符合条件，请重新选择");
					return;
				}
			}
				$.boxUtil.confirm(
						'确定重新生成这些记录吗？', 
						null, 
						function(){
							//执行操作
							$.ajax({
				                url : 'rebuild.action',
				                type : 'post',
				                dataType : 'json',
				                data : {
				                	idList:idList
				                }, //前台到后台的参数
				                success : function(data) {
				                	showWarning(data.result);
				                },
				                error : function(data) {
				                	showWarning(data.result);
				                }

				          });    
						});
				

		});
		//弹框
		$("#detail").click(function(){
			//获取选中行的id，即数据库id
			clearWarning();
			var ids = $("#gridTable").jqGrid('getGridParam', 'selarrrow');
			if( ids == null || ids.length == 0){
				showWarning("请至少选择一条记录进行操作");
				return;
			}
			if(ids.length > 1){
				showWarning("请选择一条记录进行操作");
				return;
			}
			var status = $("#gridTable").getCell(ids[0],"status");
			var statementCode = $("#gridTable").getCell(ids[0],"statementCode");
			var url ="corMertStatementDetail.action?id="+ids[0]+"&statementCode="+statementCode+"&status="+status;
			var title = "商户结算单基本信息";
			$(dlgId).dialog({width:1000,height:600,modal:true});
			openDialog(dlgId,title,url);
		});
		
		
		//审核
		$("#check").click(function(){
			//获取选中行的id，即数据库id
			clearWarning();
			var ids = $("#gridTable").jqGrid('getGridParam', 'selarrrow');
			if( ids == null || ids.length == 0){
				showWarning("请至少选择一条记录进行操作");
				return;
			}
			if(ids.length > 1){
				showWarning("请选择一条记录进行操作");
				return;
			}
			var status = $("#gridTable").getCell(ids[0],"status");
			var statementCode = $("#gridTable").getCell(ids[0],"statementCode");
			if('01' == status){
				var statementCode = $("#gridTable").getCell(ids[0],"statementCode");
				var url ="success.action?id="+ids[0]+"&statementCode="+statementCode;
				var title = "商户结算单审核";
				//执行操作
				$.ajax({
	                url : 'openCheck.action',
	                type : 'post',
	                dataType : 'json',
	                data : {
	                	id:ids[0],
	                	statementCode:statementCode
	                }, //前台到后台的参数
	                success : function(data) {
	                	if(data.data=="0"){
	        				$("#check_status").dialog({width:500,height:300,modal:true});
	        				openDialog("#check_status",title,url);
	                	}else{
	                		showWarning("该结算单已经审核过，请勿重复操作");
	                	}
	                },
	                error : function(data) {
	                	showWarning("该结算单已经审核过，请勿重复操作");
	                }

	          });    
				
			}else{
				showWarning("只有选择结算单状态为“正常”状态，才可以进行审核");
			}
		});
//<<-----------		
		$("#gridTable").gridUtil().simpleGrid({
			sortname:'updateTime', // 默认的排序字段
			sortorder:'desc',
			multiselect:true,
			colNames:['ID', 
					'${app:i18n('mertStatement.list.statementCode')}',
					'${app:i18n('mertStatement.list.merchantCode')}',
					'${app:i18n('mertStatement.list.merchantName')}',
					'${app:i18n('mertStatement.list.generateDate')}',
					'${app:i18n('mertStatement.list.settPeriod')}',
					'${app:i18n('mertStatement.list.startDate')}',
					'${app:i18n('mertStatement.list.endDate')}',
					'${app:i18n('mertStatement.list.depositAmount')}',
					'${app:i18n('mertStatement.list.serviceCharge')}',
					'${app:i18n('mertStatement.list.transAmount')}',
					'${app:i18n('mertStatement.list.transExceptionAmount')}',
					'${app:i18n('mertStatement.list.netAmount')}',
					'${app:i18n('mertStatement.list.otherAmount')}',
					'${app:i18n('mertStatement.list.currency')}',
					'${app:i18n('mertStatement.list.status')}',
					'${app:i18n('mertStatement.list.settStatus')}',
					'${app:i18n('mertStatement.list.settleTime')}',
					'${app:i18n('mertStatement.list.uploadLog')}',
					'${app:i18n('mertStatement.list.isUpload')}',
					'${app:i18n('mertStatement.list.updateTime')}',
					'${app:i18n('mertStatement.list.updator')}',
					'${app:i18n('mertStatement.list.checkTime')}',
					'${app:i18n('mertStatement.list.frozenAmount')}',
					'${app:i18n('mertStatement.list.unFrozenAmount')}',
					'${app:i18n('mertStatement.query.checkStatus')}'
					],
			colModel:[
						{name : 'id',width : 10, hidden: true},
						{name : 'statementCode',index : 'statementCode'},
						{name : 'merchantCode',index : 'merchantCode'},
						{name : 'merchantName',index : 'merchantName'},
						{name : 'createTime',index : 'createTime'},
						{name : 'settPeriod',index : 'settPeriod'},
						{name : 'startDate',index : 'startDate',formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
						{name : 'endDate',index : 'endDate',formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
						{name : 'depositAmount',index : 'depositAmount',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
						{name : 'serviceCharge',index : 'serviceCharge',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
						{name : 'transAmount',index : 'transAmount',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
						{name : 'transExpAmout',index : 'transExpAmout',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
						{name : 'netAmount',index : 'netAmount',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
						{name : 'otherAmount',index : 'otherAmount',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
						{name : 'currency',index : 'currency',formatter:'select',editoptions:{value: '${currencyListRender}'}},
						{name : 'status',index : 'status',formatter:'select',editoptions:{value: '${statusListRender}'}},
						{name : 'settStatus',index : 'settStatus',formatter:'select',editoptions:{value: '${settStatusListRender}'}},
						{name : 'settleTime',index : 'settleTime',formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
						{name : 'uploadLog',index : 'uploadLog'},
						{name : 'isUpload',index : 'isUpload',formatter:'select',editoptions:{value: '${isTamperedListRender}'}},
						{name : 'updateTime',index : 'updateTime',formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
						{name : 'updator',index : 'updator'},
						{name : 'checkTime',index : 'checkTime',formatoptions: {newformat:'Y-m-d H:i:s', srcformat:'Y-m-d H:i:s'}},
						{name : 'frozenAmount',index : 'frozenAmount',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
						{name : 'unFrozenAmount',index : 'unFrozenAmount',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
						{name : 'checkStatus',index : 'checkStatus',formatter:'select',editoptions:{value: '${checkStatusListRender}'}}
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
             footerrow: false,
             shrinkToFit:false,
             autoScroll: false,
			caption: "${app:i18n('corMertStatement.corMertStatementListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
				if(ids.length<=0){
					$("div.warning span").html("暂无满足条件的数据"); 
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
	
	function doSearch(){

		// 设置查询参数
		setQueryCondition();
		
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url:searchUrl}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数

		$("#gridTable").setPostDataItem("statementCode", $("#statementCode").val());
		$("#gridTable").setPostDataItem("merchantCode", $("#merchantCode").val());
		$("#gridTable").setPostDataItem("merchantName", $("#merchantName").val());
		$("#gridTable").setPostDataItem("status", $("#status").val());
		$("#gridTable").setPostDataItem("settStatus", $("#settStatus").val());
		$("#gridTable").setPostDataItem("timeType", $("#timeType").val());
		$("#gridTable").setPostDataItem("startTime", $("#startTime").val());
		$("#gridTable").setPostDataItem("endTime", $("#endTime").val());
		//新增审核状态
		$("#gridTable").setPostDataItem("checkStatus", $("#checkStatus").val());

	}
	
	//-----------------tengbei-------------
	var dlgId = "#t_detail";
	
	function openDialog(dlgId, title, url){
		//$(dlgId).html("");
		$(dlgId).dialog({
			autoOpen:false,
			position:'center',
			modal:true,
			resizable: false,
			title:title
			});
		$(dlgId).load(url);
		$(dlgId).dialog('open');
		$(dlgId).dialog({
			   close: function(event, ui) { 			   
			  		$(dlgId).html(" ");			   
	  			}  
		});
	}
	function closeDialog(){
		$(dlgEditId).dialog('close');
	}	
</script>
<!-- style="overflow-x:hidden"去掉水平滚动条 -->
<div id="t_detail" style="overflow-x:hidden"></div>
<!-- 审核界面 -->
<div id="check_status" style="overflow-x:hidden"></div>
<s:form id="corMertStatementListForm" method="post" action="corMertStatementList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('corMertStatement.corMertStatementListJsp.headTitle')}</h3>
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
								<th width="10%">${app:i18n('mertStatement.query.merchantCode')}：</th>
								<td width="30%"><input name="merchantCode" id="merchantCode" class="width_c" /></td>

								<th width="10%">${app:i18n('mertStatement.query.merchantName')}：</th>
								<td width="30%"><input name="merchantName" id="merchantName" class="width_c" /></td>

								<th width="10%">${app:i18n('mertStatement.query.statementCode')}：</th>
								<td width="30%"><input name="statementCode" id="statementCode" class="width_c" /></td>
							</tr>
							<tr>	
								<th width="10%"><em>*</em>${app:i18n('mertStatement.query.status')}：</th>
								<td width="30%">
									<select style="width:188px" id="status" name="status">
									<!-- <option value="" selected="selected">请选择...</option> -->
										<c:forEach items="${statusList}" var ="status">
												<c:choose>
													<c:when test="${status.key=='01'}">
														<option value="${status.key}" selected="selected">${status.value}</option>
													</c:when>
													<c:otherwise>
														<option value="${status.key}">${status.value}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
									</select>
								</td>
								
								<th width="10%"><em>*</em>${app:i18n('mertStatement.query.settStatus')}：</th>
								<td width="30%">
									<select style="width:188px" id="settStatus" name="settStatus">
											<!-- <option value="" selected="selected">请选择...</option> -->
											<c:forEach items="${settStatusList}" var="settStatus">
												<c:if test="${settStatus.key=='01'}">
													<option value="${settStatus.key}" selected="selected">${settStatus.value}</option>
												</c:if>
												<c:if test="${settStatus.key!='01'}">
													<option value="${settStatus.key}">${settStatus.value}</option>
												</c:if>

											</c:forEach>
									</select>
								</td>
								
								<th width="10%">${app:i18n('mertStatement.query.checkStatus')}：</th>
								<td width="30%">
									<select name="checkStatus" style="width:188px" id="checkStatus" >
										<option value="" selected="selected">请选择...</option>
										<c:forEach items="${checkStatusList}" var ="checkStatus">
										<c:if test="${checkStatus.key=='02'}">
											<option value="${checkStatus.key}">${checkStatus.value}</option>
										</c:if>
										<c:if test="${checkStatus.key=='03'}">
													<option value="${checkStatus.key}">${checkStatus.value}</option>
												</c:if>
										</c:forEach>
									</select>
								</td>
								 
							</tr>
							<tr>	
								<th width="10%"><em>*</em>${app:i18n('mertStatement.query.timeType')}：</th>
								<td width="30%">
									<select name="" style="width:188px" id="timeType" name="timeType">
										<!-- <option value="" selected="selected">请选择...</option> -->
										<c:forEach items="${timeTypeList}" var ="timeType">
										<c:if test="${timeType.key=='01'}">
											<option value="${timeType.key}" selected="selected">${timeType.value}</option>
										</c:if>
										<c:if test="${timeType.key!='01'}">
													<option value="${timeType.key}">${timeType.value}</option>
												</c:if>
										</c:forEach>
									</select>
								</td>
								
								<th width="10%"><em>*</em>${app:i18n('mertStatement.query.startTime')}：</th>
									<td width="30%">
									<input name="ago93Day" id="ago93Day"  type="hidden"/>
									<input name="startTime" id="startTime" class="Wdate"
										value="${startTime}" readonly="readonly"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'ago93Day\')}',maxDate:'#F{$dp.$D(\'endTime\')}'})"
										style="width: 188px" />
									</td>

									<th width="10%"><em>*</em>${app:i18n('mertStatement.query.endTime')}：</th>
								<td width="30%">
									<input name="endTime" id="endTime" class="Wdate" value="${endTime}"  readonly="readonly" 
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'#F{$dp.$D(\'endTime\')}'})" style="width:188px"/>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="btn_layout">
						<a name="search" id="search" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
						<a name="reset" id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
					</div>
				</div>
			</div>
			<div class="block">
				<table id="gridTable">
				</table>
				<div id="gridPager"></div>
				<div id="gridToolbar">
					<a id="detail" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-view">${app:i18n('mertStatement.button.detail')}</span></span></a> 
					<a id="upload" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('mertStatement.button.upload')}</span></span></a> 
					<a id="notifyMerchant" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('mertStatement.button.notify')}</span></span></a> 
					 <a id="download"  class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('mertStatement.button.download')}</span></span></a>
					<a id="invalid" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('mertStatement.button.invalid')}</span></span></a> 
					<a id="reGenerator" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('mertStatement.button.reGenerator')}</span></span></a>
					<app:isPermission resource='O_CNL_CHECK_AUDIT'>
						<a id="check" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('mertStatement.button.check')}</span></span></a> 
					</app:isPermission>
				</div>
			</div>
		</div>
	</div>
</div>

</s:form>

 <form id="downloadFileForm" action="fileDown.action" enctype="multipart/form-data" method="post" >  
	 <input id="idList"  name="idList"  value="" type="hidden"></input>
</form>  

 <form id="uploadFileForm" action="fileUp.action" enctype="multipart/form-data" method="post" >  
	 <input id="id"  name="id"  value="" type="hidden"></input>
</form>  
