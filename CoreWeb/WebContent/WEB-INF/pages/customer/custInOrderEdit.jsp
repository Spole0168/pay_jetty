<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	var operaType = 0;//operaType  : 0 -新增，1-查看，2-审核，3-修改
	function initEditPage(){
		clearWarningInfo();
		operaType = ${operaType};//operaType
		$("#operaType").val(operaType);
		//设置只读属性时，可能会影响父页面的文本框内容。
		//设置下拉框数据。
		if(0==operaType){			//新增逻辑控制
			$("tr:[class='addNotOprea']").hide();//隐藏  -新增时 企业会员编号
			$("tr:[class='handleOpera']").hide();//隐藏 新增和修改 【人和时间】
			$("tr:[class='auditOprea']").hide(); //隐藏 审核信息
			$("tr:[class='viewOprea']").hide();  //隐藏 审核【人和时间】
			$("#statusValue").hide();
			$("tr:[class='incomeStatus']").hide();
			$("tr:[class='statusValue']").hide();	
		}else if(1==operaType){	//查看逻辑控制
			$("#incomeOrderEditForm input").attr("disabled","disabled");
			$("tr:[class='addNotOprea']").show();//显示 企业会员编号
			$("tr:[class='handleOpera']").show();//显示  新增和修改 【人和时间】
			$("tr:[class='auditOprea']").show(); //显示  审核信息
			$("tr:[class='viewOprea']").show();  //显示  审核【人和时间】
			$("#status").show();  		//显示  审核【人和时间】
			$("#statusValue").show();  //显示  审核【人和时间】
			$("#submitBt").hide();					//显示提交按钮
			//设置下拉框当前值
			setSelectInfos();
			$("#incomeOrderEditForm  input").attr("readonly","readonly");   		//设置input  只读
			$("#incomeOrderEditForm  select").attr("disabled","disabled");   		//设置input  只读
		}else if(2==operaType){	//审核逻辑控制。
			$("#incomeOrderEditForm  input").attr("readonly","readonly");   		//设置input  只读
			$("#incomeOrderEditForm  select").attr("disabled","disabled"); 
			$("tr:[class='addNotOprea']").show();//显示 企业会员编号
			$("tr:[class='handleOpera']").show();//显示新增和修改 【人和时间】
			$("tr:[class='auditOprea']").show(); //显示 审核信息
			$("tr:[class='viewOprea']").hide();  //隐藏 审核【人和时间】
			$("tr:[class='statusValue']").hide();
			//设置下拉框当前值
			setSelectInfos();
			auditStatusChange();//置灰审核失败原因
			$("tr:[class='auditOprea'] input").attr("readonly",""); 
			$("tr:[class='auditOprea'] select").attr("disabled","");  
		}else if(3==operaType){//修改
			$("tr:[class='addNotOprea']").show();//显示 企业会员编号
			$("tr:[class='handleOpera']").show();//显示  新增和修改 【人和时间】
			$("tr:[class='auditOprea']").hide(); //显示  审核信息
			$("tr:[class='viewOprea']").show();  //显示  审核【人和时间】
			$("tr:[class='statusValue']").show();
			$("#statusValue").show();
			//设置下拉框当前值
			setSelectInfos();
			$("tr:[class='handleOpera'] input").attr("readonly","readonly");
			$("tr:[class='auditOprea'] select").attr("disabled","disabled");
			$("tr:[class='auditOprea'] input").attr("readonly","readonly");
			$("tr:[class='viewOprea'] input").attr("readonly","readonly");
			$("tr:[class='statusValue'] input").attr("readonly","readonly");
			var status= '${incomeOrderDto.status}';
			
		}
	}
	function setSelectInfos(){
		$("#statusValue").val(getSelectValue('${statusTypeRender}','${incomeOrderDto.auditStatus}'));
		$("#currency").val('${incomeOrderDto.currency}');
		$("#operateType").val('${incomeOrderDto.operateType}');
		$('#status').val(getSelectValue('${incomeOrderStatusRender}','${incomeOrderDto.status}'));
		
		//设置客户状态 数据转义
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
	function auditStatusChange(){
// 		alert("====="+$("#auditStatus").val());
		$("#auditReason").val("");
		$("#auditReason").attr("disabled","disabled");
		$("#auditReasonEm").attr("disabled","disabled");
		$("#auditReasonEm").hide();
		if($("#auditStatus").val()=="03"){
			$("#auditReasonEm").attr("disabled","");
			$("#auditReason").attr("disabled","");
			$("#auditReasonEm").show();
			return;
		}
	}
	//清空提示信息
	function clearWarningInfo() {
		$("#warningInfo span").html("");
		$("#warningInfo").hide();
	}
	//提示信息
	function showWarningInfo(msg) {
		$("#warningInfo span").html(msg);
		$("#warningInfo").show();
		$(dlgUserId).animate({scrollTop:0}, 500);
	}
	
	
	//校验表单
	function checkForm(){
		if(0==operaType||3==operaType){//新增逻辑校验
			if(isNullOrBlank("merchantCode")){//必输项校验
				return "商户编号   不能为空";
			}
			if(isNullOrBlank("merchantName")){//必输项校验
				return "商户名称   不能为空";
			}
			if(isNullOrBlank("custName")){//必输项校验
				return "会员名称   不能为空";
			}
			if(isNullOrBlank("operateType")){//必输项校验
				return "业务编号   不能为空";
			}
			if(isNullOrBlank("currency")){//必输项校验
				return "币种   不能为空";
			}
			if(isNullOrBlank("transRate")){//必输项校验
				return "汇率   不能为空";
			}
			if(isNullOrBlank("amount")){//必输项校验
				return "金额  不能为空";
			}
			if(isNullOrBlank("bankCardNum")){//必输项校验
				return "对方银行账号   不能为空";
			}
			if(isNullOrBlank("reserveBankCardNum")){//必输项校验
				return "备付金银行账号   不能为空";
			}
			if(isNullOrBlank("inAccountTime")){//必输项校验
				return "到账时间   不能为空";
			}
			if (!isAmount("amount")) {
				return "金额  非法";
			}
			if (!isIntNumber("bankCardNum")) {
				return "对方银行账号  非法";
			}
			if (!isIntNumber("reserveBankCardNum")) {
				return "备付金银行账号  非法";
			}
			//新增修改必输项校验
		}else if(2==operaType){	//审核逻辑校验
			if(isNullOrBlank("auditStatus")){//必输项校验
				return "审核状态   不能为空";
			}
			if($.trim($("#auditStatus").val())=='03'&&isNullOrBlank("auditReason")){
				return "审核失败原因  不能为空";
			}
		}
		return "";
	}
	/**
		设置商户相关信息
	*/
	function  getMerchantDatoInfo(){
		if($.trim($("#merchantCode").val())==""){
			$("#merchantName").val("");
    		$("#custName").val("");
			return;
		}
		 $.ajax({
	          type: "post",
	          url: "getMerchantDatoInfo.action",
	          data: $('#incomeOrderEditForm').serialize(),
	          dataType: "json",
	          async:false,
	          success: function(data){
	        	var datas=data.status;
	        	if(CORE_AJAX_RESULT_Y == datas){
	        		$("#merchantName").val(data.merchantName);
	        		$("#custName").val(data.custName);
	        		return;
	        	}else{
	        		showWarningInfo("不存在给商户号的商户信息");
	        		return;
	        	}
	          }
	      });
	}
	$().ready(function() {
		
		initEditPage();
		
		
		$("#submitBt").click(function(){
			clearWarningInfo();
			var flag = checkForm();
			if(flag!=""){
				showWarningInfo(flag);
				return;
			}
			$.boxUtil.confirm(
				'请确认是否 提交 信息？', 
				null, 
				function(){
			       $("#incomeOrderEditForm").submit(); 
				}, 
				function(){
					//return false;
				});
			return false;
		});
		
		$("#undoBt").click(function(){
			$(dlgUserId).dialog('close');
		});
		
// 		// 初始化所有只读字段的样式
		$("* [readonly]").addClass("read_only");
		$("input[readonly]").input().disableBackSpace();
	});
</script>

<s:form id="incomeOrderEditForm" method="post" action="incomeOrderSave.action?loadPageCache=true" namespace="/incomeOrder"  enctype="multipart/form-data">
<div class="layout">
	<div class="block m-b">
		<div class="block_container">
			<div class="fieldset_border fieldset_bg m-b">
				<div class="fieldset_container">
					<div class="warning" id="warningInfo" style="display:none;">
						<span></span>
					</div>
					<input type="hidden" id="operaType" name="operaType"  value="${operaType}" />
					<input type="hidden" id="id"  name="id"  value="${incomeOrderDto.id}" />
					<table cellpadding="0" cellspacing="0" class="table_form" id="tableInfo">
						<thead>
						</thead>
						<tfoot>
						</tfoot>
						<tbody>
							<tr class="addNotOprea">
								<th>${app:i18n('incomeOrderDto.incomeOrderCode') }：</th>
								<td><input name="incomeOrderDto.incomeOrderCode" id="incomeOrderCode" class="width_c" value="${incomeOrderDto.incomeOrderCode}" maxlength="50"  disabled="disabled" /></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('incomeOrderDto.merchantCode') }：</th>
								<td><input name="incomeOrderDto.merchantCode" id="merchantCode" class="width_c" value="${incomeOrderDto.merchantCode}" onchange="getMerchantDatoInfo()" maxlength="50"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('incomeOrderDto.merchantName') }：</th>
								<td><input name="incomeOrderDto.merchantName" id="merchantName" class="width_c" value="${incomeOrderDto.merchantName }" maxlength="50" readonly="readonly"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('incomeOrderDto.custName') }：</th>
								<td><input name="incomeOrderDto.custName" id="custName" class="width_c" value="${incomeOrderDto.custName}" maxlength="50" readonly="readonly"/></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('incomeOrderDto.operateType') }：</th>
								<td>
									<select name="incomeOrderDto.operateType" id="operateType" style="width:190px">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{operateTypeList}" id="operateTypeItem">
									        <option value="<s:property value="#operateTypeItem.key" />">
									        	<s:property value="#operateTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
							    </td>
							</tr>
							<tr>
								<th>${app:i18n('incomeOrderDto.currency')}：</th>
								<td>
									<select name="incomeOrderDto.currency" id="currency" style="width:190px">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{currencyTypeList}" id="currencyTypeItem">
									        <option value="<s:property value="#currencyTypeItem.key" />">
									        	<s:property value="#currencyTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
							    </td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('incomeOrderDto.transRate') }：</th>
								<td><input name="incomeOrderDto.transRate" id="transRate" class="width_c" value="${incomeOrderDto.transRate}" maxlength="50" /></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('incomeOrderDto.amount') }：</th>
								<td><input name="incomeOrderDto.amount" id="amount" class="width_c" value="${incomeOrderDto.amount}" maxlength="50" /></td>
							</tr>
							<tr class="incomeStatus">
								<th>${app:i18n('incomeOrderDto.incomeStatus')}：</th>
								<td><input name="incomeOrderDto.status" id="status" class="width_c" value="${incomeOrderDto.status}" maxlength="50" disabled="disabled" /></td>
							</tr>
							<tr>
								<th>${app:i18n('incomeOrderDto.bankTransNum')}：</th>
								<td><input name="incomeOrderDto.bankTransNum" id="bankTransNum" class="width_c" value="${incomeOrderDto.bankTransNum}" maxlength="50" /></td>
							</tr>
							<tr>
								<th>${app:i18n('incomeOrderDto.bankAcntCode')}：</th>
								<td><input name="incomeOrderDto.bankCode" id="bankCode" class="width_c" value="${incomeOrderDto.bankCode}" maxlength="50" /></td>
							</tr>
							<tr>
								<th>${app:i18n('incomeOrderDto.bankName')}：</th>
								<td><input name="incomeOrderDto.bankName" id="bankName" value="${incomeOrderDto.bankName }" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('incomeOrderDto.bankCardNum') }：</th>
								<td><input name="incomeOrderDto.bankCardNum" id="bankCardNum" value="${incomeOrderDto.bankCardNum}" class="width_c"  maxlength="50" /></td>
							</tr>
							
							<tr class="editOpera">
								<th>${app:i18n('incomeOrderDto.reserveBankAcntCode') }：</th>
								<td><input name="incomeOrderDto.reserveBankCode" id="reserveBankCode" class="width_c" value="${incomeOrderDto.reserveBankCode}" maxlength="50" /></td>
							</tr>
							<tr class="editOpera">
								<th>${app:i18n('incomeOrderDto.reservebankName') }：</th>
								<td><input name="incomeOrderDto.reserveBankName" id="reserveBankName" class="width_c" value="${incomeOrderDto.reserveBankName}" maxlength="50" /></td>
							</tr>
							<tr class="editOpera">
								<th><em>*</em>${app:i18n('incomeOrderDto.reserveBankCardNum') }：</th>
								<td><input name="incomeOrderDto.reserveBankCardNum" id="reserveBankCardNum" class="width_c" value="${incomeOrderDto.reserveBankCardNum}" maxlength="50" /></td>
							</tr>
					
							<tr>
							<th><em>*</em>${app:i18n('incomeOrderDto.inAccountTime') }：</th>
							<td><input name="incomeOrderDto.inAccountTime" id="inAccountTime"
											class="Wdate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:nowDate})"
											value="${incomeOrderDto.inAccountTime}" style="width: 188px" /></td>
							</tr>
							<tr class="editOpera">
								<th>${app:i18n('incomeOrderDto.transExplanation') }：</th>
								<td><input name="incomeOrderDto.transExplanation" id="transExplanation" class="width_c" value="${incomeOrderDto.transExplanation }" maxlength="50" /></td>
							</tr>
							<tr>
								<th>${app:i18n('incomeOrderDto.voucher') }：</th>
								<td>
									<s:if test="incomeOrderDto.voucher != null">
										<a href="<%=request.getContextPath()%>/downloadFile.action?fileId=${incomeOrderDto.voucher}" target="_blank">${app:i18n('incomeOrderDto.voucherImg') }</a>
									</s:if>
									<s:elseif test="type != 1">
										<input id="file" type="file"  name="file" />
									</s:elseif>
								</td>
							</tr>
							<tr class="handleOpera">
								<th>${app:i18n('incomeOrderDto.createTime') }：</th>
								<td><input name="incomeOrderDto.createTime" id="createTime" value="${incomeOrderDto.createTime}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr class="handleOpera">
								<th>${app:i18n('incomeOrderDto.creator') }：</th>
								<td><input name="incomeOrderDto.creator" id="creator" value="${incomeOrderDto.creator}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr class="handleOpera">
								<th>${app:i18n('incomeOrderDto.updateTime') }：</th>
								<td><input name="incomeOrderDto.updateTime" id="updateTime" value="${incomeOrderDto.updateTime}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr class="handleOpera">
								<th>${app:i18n('incomeOrderDto.updator') }：</th>
								<td><input name="incomeOrderDto.updator" id="updator" value="${incomeOrderDto.updator}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr class="auditOprea">
								<th><em>*</em>${app:i18n('incomeOrderDto.status') }：</th>
								<td>
									<select name="incomeOrderDto.auditStatus" id="auditStatus" style="width:190px" value="${incomeOrderDto.auditStatus}" onchange="auditStatusChange()">
										<option value="" >${app:i18n('global.jsp.defaultSelect') }</option>
										<s:iterator value="%{statusOperaList}" id="statusOpera">
									        <option value="<s:property value="#statusOpera.key" />">
									        	<s:property value="#statusOpera.value" />
									        </option>
										</s:iterator>
							        </select>
							        
							        
								</td>
							</tr>
							<tr class ="statusValue"><th><em>*</em>${app:i18n('incomeOrderDto.status') }：</th>
							<td><input  id="statusValue" class="width_c" value="" maxlength="50" style="display: none"/><td></tr>
							<tr class="auditOprea">
								<th><em id="auditReasonEm" style="display:none;">*</em>${app:i18n('incomeOrderDto.auditReason') }：</th>
								<td><input name="incomeOrderDto.auditReason" id="auditReason" value="${incomeOrderDto.auditReason}" class="width_c"  maxlength="50" /></td>
							</tr>
							
							<tr class="viewOprea">
								<th>${app:i18n('incomeOrderDto.auditPerson') }：</th>
								<td><input name="incomeOrderDto.auditPerson" id="auditPerson" value="${incomeOrderDto.auditPerson}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr class="viewOprea">
								<th>${app:i18n('incomeOrderDto.auditTime') }：</th>
								<td><input name="incomeOrderDto.auditTime" id="auditTime" value="${incomeOrderDto.auditTime}" class="width_c"  maxlength="50" /></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div id="tabs-1">
				<a id="submitBt" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.submit')}</span></span></a>
				<a id="undoBt" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
