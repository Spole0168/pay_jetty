<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	var operaType = 0;//operaType  : 0 -新增，1-查看，2-审核，3-修改
	var cardPropertyIsMust = "01";  //01- xuanshu  02-bishu
	function initEditPage(){
		clearWarningInfo();
		operaType = ${operaType};//operaType
		$("#operaType").val(operaType);
		//设置只读属性时，可能会影响父页面的文本框内容。
		//设置下拉框数据。
		if(0==operaType){			//新增逻辑控制
			$("tr:[class='addNotOprea']").hide();//隐藏  -新增时 企业会员编号
			$("#currency").val('50');
		}else if(1==operaType){	//查看逻辑控制
			$("tr:[class='addNotOprea']").show();//显示 企业会员编号
			$("#saveBt").hide(); //隐藏保存按钮
			//设置下拉框当前值
			setSelectInfos();
			$("#corMertRateEditForm  input").attr("readonly","readonly");   		//设置input  只读
			$("#corMertRateEditForm  input[class='Wdate']").attr("disabled","disabled");   
			$("#corMertRateEditForm  select").attr("disabled","disabled");   		//设置input  只读
		}else if(3==operaType){//修改
			$("tr:[class='addNotOprea']").show();//显示 企业会员编号
			//设置下拉框当前值
			setSelectInfos();
			var effectDateStr = $("#effectDate").val();
			var expireDateStr = $("#expireDate").val();
			if(getDaysDiff(effectDateStr,nowDate)< 0){//手续费未生效
				setFeeData();
				$("tr:[class='addNotOprea'] input").attr("disabled","disabled"); 
				$("tr:[class='editNotOpera'] select").attr("disabled","disabled");
			}
			//effectDateStr < nowDate < expireDateStr
			if(getDaysDiff(effectDateStr,nowDate)>=0 && getDaysDiff(expireDateStr,nowDate)<=0){ //已生效 但未失效
				//只能修改失效日期
				$("#corMertRateEditForm  input").attr("readonly","readonly");   		//设置input  只读
				$("#effectDate_Temp").attr("disabled","disabled");   
				$("#corMertRateEditForm  select").attr("disabled","disabled");   		//设置input  只读
				$("#expireDate").attr("readonly",""); 
			}
			
		}
	}
	
	function setSelectInfos(){
		$("#cardProperty").val('${corMertRateDto.cardProperty}');
		$("#cardType").val('${corMertRateDto.cardType}');
		$("#settType").val('${corMertRateDto.settType}');
		$("#currency").val('${corMertRateDto.currency}');
		$("#productCode").val('${corMertRateDto.productCode}');
		$("#productName").val(getSelectValue('${productListRender}','${corMertRateDto.productCode}'));
		$("#effectDate").val(transDateTime2Date('${corMertRateDto.effectDate}'));
		$("#effectDate_Temp").val(transDateTime2Date('${corMertRateDto.effectDate}'));
		$("#expireDate").val(transDateTime2Date('${corMertRateDto.expireDate}'));
	
	}
	//校验表单
	function checkForm(){
		if(0==operaType||3==operaType){//0 -新增，1-查看，2-审核，3-修改
			
			if(isNullOrBlank("mertRateName")){//必输项校验
				return "手续费名称  不能为空";
			}
			if(isNullOrBlank("productCode")){//必输项校验
				return "产品编码  不能为空";
			}
			if(isNullOrBlank("cardType")){//必输项校验
				return "卡种类型  不能为空";
			}
			if(cardPropertyIsMust == "02"){//必输项校验
				if(isNullOrBlank("cardProperty")){//必输项校验
					return "卡种属性  不能为空";
				}
			}
			if(isNullOrBlank("settType")){//必输项校验
				return "结算方式  不能为空";
			}
			if(isNullOrBlank("currency")){//必输项校验
				return "币种  不能为空";
			}
			if(isNullOrBlank("fixedFeeValue") && isNullOrBlank("rateFeeValue")){//必输项校验
				return "固定费用和比例费率  不能同时为空";
			}
			if(!isNullOrBlank("fixedFeeValue")){
				if(!isAmount("fixedFeeValue")){
					return "固定费用  非法";
				}
			}
			if(!isNullOrBlank("rateFeeValue")){
				if(isNullOrBlank("lowFeeValue")){//必输项校验
					return "最低费用  不能为空";
				}
				if(isNullOrBlank("hightFeeValue")){//必输项校验
					return "最高费用  不能为空";
				}
				if(!isNumber("rateFeeValue") || $("#rateFeeValue").val() < 0){
					return "比例费率  非法";
				}
				if(!isAmount("lowFeeValue")){
					return "最低费用 非法";
				}
				if(!isAmount("hightFeeValue")){
					return "最高费用  非法";
				}
				var fee = $("#hightFeeValue").val() - $("#lowFeeValue").val();
				if(fee < 0){
					return "最低费用必须小于或等于最高费用";
				}
				$("#serviceFeeType").val("02");//01-固定费用 02-按比例计费
			}
			if(isNullOrBlank("effectDate_Temp")){//必输项校验
				return "生效日期  不能为空";
			}
			if(isNullOrBlank("expireDate")){//必输项校验
				return "失效日期  不能为空";
			}
			if(!isNullOrBlank("merchantCode")&&isNullOrBlank("merchantName")){
				return "商户信息 不完整";
			}
			if(getDaysDiff($("#effectDate_Temp").val(),$("#expireDate").val())<=0){
				return "生效日期必须小于失效日期";
			}
			if(existsInFeeRateInfo()){
				return "新增手续费方案信息与系统中已存在的手续费方案信息冲突";
			}
		}
		return "";
	}
	function getMerchantName(obj){
		if(isNullOrBlank(obj.id)){
			$("#merchantName").val("");
			return;
		}
		clearWarningInfo();
		$("#merchantName").val("");
		 $.ajax({
	          type: "post",
	          url: "rateGetMerchantName.action",
	          data: {id : $("#"+obj.id).val()},
	          dataType: "json",
	          async:false,
	          success: function(data){
	        	if(CORE_AJAX_RESULT_Y ==data.status){
	        		$("#merchantName").val(data.merchantName);
	        	}else{
	        		showWarningInfo("商户编号不存在或者无效");
	        		return;
	        	}
	        	
	          }
	      });
	}
	/*
		2)	系统检查新增手续费方案信息是否与系统中已存在的手续费方案信息冲突
	*/
	function  existsInFeeRateInfo(){
		var result = false; 
		$("#effectDate").val(transDateTime2Date($("#effectDate_Temp").val()));
		 $.ajax({
	          type: "post",
	          url: "existsInFeeRateInfo.action",
	          data: $('#corMertRateEditForm').serialize(),
	          dataType: "json",
	          async:false,
	          success: function(data){
	        	if(CORE_AJAX_RESULT_Y == data.status){
	        		result = true;
	        	}
	          }
	      });
		 return result;
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
	function getProductNameByCode(){
		var code = $("#productCode").val();
		$("#cardPropertyDiv").hide();
		cardPropertyIsMust = "01";
		if(null!=code&&""!=code){
			var pName = getSelectValue('${productListRender}',code);
			$("#productName").val(pName);
			if(pName.indexOf("代付") != -1){
				cardPropertyIsMust = "02";
				$("#cardPropertyDiv").show();
			}
		}else{
			$("#productName").val("");
		}
	}
	function setFeeData(){
		var fixVal = $("#fixedFeeValue").val();
		var rateVal = $("#rateFeeValue").val();
		if(null!=fixVal&& ""!=fixVal){
// 			$("#lowFeeValue").val(fixVal);
// 			$("#hightFeeValue").val(fixVal);
			$("#serviceFeeType").val("01");//01-固定费用 02-按比例计费
			$("#rateFeeValue").attr("disabled","disabled");
			$("#lowFeeValue").attr("disabled","disabled");
			$("#hightFeeValue").attr("disabled","disabled");
			$("#rateFeeValueEm").hide();
		}else if(null!=rateVal&& ""!=rateVal){
// 			$("#lowFeeValue").val("");
// 			$("#hightFeeValue").val("");
			$("#serviceFeeType").val("02");//01-固定费用 02-按比例计费
			$("#fixedFeeValue").attr("disabled","disabled");
			$("#lowFeeValue").attr("disabled","");
			$("#hightFeeValue").attr("disabled","");
			$("#fixedFeeValueEm").hide();
		}else{
			$("#lowFeeValue").val("");
			$("#hightFeeValue").val("");
			$("#fixedFeeValue").attr("disabled","");
			$("#rateFeeValue").attr("disabled","");
			$("#fixedFeeValueEm").show();
			$("#rateFeeValueEm").show();
		}
	}
	
	$().ready(function() {
		
		initEditPage();
		
		$("#saveBt").click(function(){
			clearWarningInfo();
			var flag = checkForm();
			if(flag!=""){
				showWarningInfo(flag);
				return;
			}
			$.boxUtil.confirm(
				'请确认是否保存信息？', 
				null, 
				function(){
					 $("#corMertRateEditForm").submit(); 
				}, 
				function(){
					return false;
				});
			return false;
		});
	
		$("#undoBt").click(function(){
			$(dlgUserId).dialog('close');
		});
		
		// 初始化所有只读字段的样式
		$("* [readonly]").addClass("read_only");
		$("input[readonly]").input().disableBackSpace();
	});
</script>

<s:form id="corMertRateEditForm" method="post" action="corMertRateSave.action?loadPageCache=true" namespace="/custCompany"  enctype="multipart/form-data">
<div class="layout">
	<div class="block m-b">
		<div class="block_container">
			<div class="fieldset_border fieldset_bg m-b">
				<div class="fieldset_container">
					<div class="warning" id="warningInfo" style="display:none;">
						<span></span>
					</div>
					<input type="hidden" id="operaType" name="operaType"  value="${operaType}" />
					<input type="hidden" id="id"  name="id"  value="${corMertRateDto.id}" />
					<table cellpadding="0" cellspacing="0" class="table_form" id="tableInfo">
						<thead>
						</thead>
						<tfoot>
						</tfoot>
						<tbody>
							<tr class="addNotOprea">
								<th>${app:i18n('corMertRateDto.mertRateCode') }：</th>
								<td><input name="corMertRateDto.custCode" id="mertRateCode" class="width_c" value="${corMertRateDto.mertRateCode}" maxlength="50"  disabled="disabled" /></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corMertRateDto.mertRateName') }：</th>
								<td><input name="corMertRateDto.mertRateName" id="mertRateName" class="width_c" value="${corMertRateDto.mertRateName}" maxlength="50" />
									<input type="hidden" name="corMertRateDto.mertRateCode" id="mertRateCodeValue"  value="${corMertRateDto.mertRateCode}" maxlength="50" />
								</td>
							</tr>
							<tr>
								<th>${app:i18n('corMertRateDto.merchantCode') }：</th>
								<td><input name="corMertRateDto.merchantCode" id="merchantCode" class="width_c" value="${corMertRateDto.merchantCode }" maxlength="50" onchange="getMerchantName(this)"/></td>
							</tr>
							<tr>
								<th>${app:i18n('corMertRateDto.merchantName') }：</th>
								<td><input name="corMertRateDto.merchantName" id="merchantName" class="width_c" value="${corMertRateDto.merchantName}" maxlength="50" readonly="readonly" /></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corMertRateDto.productCode') }：</th>
								<td>
									<select name="corMertRateDto.productCode" id="productCode" style="width:190px" value="${corMertRateDto.productCode}" onchange="getProductNameByCode()">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{productList}" id="cardTypeItem">
									        <option value="<s:property value="#cardTypeItem.key" />">
									        	<s:property value="#cardTypeItem.key" />
									        </option>
										</s:iterator>
							        </select>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corMertRateDto.productName') }：</th>
								<td><input name="corMertRateDto.productName" id="productName" class="width_c" value="${corMertRateDto.productName}"  maxlength="50" readonly="readonly" /></td>
							</tr>
							
							<tr>
								<th><em>*</em>${app:i18n('corMertRateDto.cardType') }：</th>
								<td>
									<select name="corMertRateDto.cardType" id="cardType" style="width:190px" value="${corMertRateDto.cardType}">
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
								<th><em id="cardPropertyDiv">*</em>${app:i18n('corMertRateDto.cardProperty') }：</th>
								<td>
									<select name="corMertRateDto.cardProperty" id="cardProperty" style="width:190px" value="${corMertRateDto.cardProperty}">
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
								<th><em>*</em>${app:i18n('corMertRateDto.settType') }：</th>
								<td>
									<select name="corMertRateDto.settType" id="settType" style="width:190px" value="${corMertRateDto.settType}">
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
								<th><em>*</em>${app:i18n('corMertRateDto.currency') }：</th>
								<td>
									<select name="corMertRateDto.currency" id="currency" style="width:190px" value="${corMertRateDto.currency}">
										<s:iterator value="%{currencyTypeList}" id="item">
									        <option value="<s:property value="#item.key" />">
									        	<s:property value="#item.value" />
									        </option>
										</s:iterator>
							        </select>
								</td>
							</tr>
							<tr>
								<th><em id="fixedFeeValueEm">*</em>${app:i18n('corMertRateDto.fixedFeeValue') }：</th>
								<td><input name="corMertRateDto.fixedFeeValue" id="fixedFeeValue" value="${corMertRateDto.fixedFeeValue}" class="width_c"  maxlength="50" onchange="setFeeData()"/></td>
							</tr>
							<tr>
								<th><em id="rateFeeValueEm">*</em>${app:i18n('corMertRateDto.rateFeeValue') }：</th>
								<td><input name="corMertRateDto.rateFeeValue" id="rateFeeValue" value="${corMertRateDto.rateFeeValue}" style="width:175px"   onchange="setFeeData()" />%</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corMertRateDto.lowFeeValue') }：</th>
								<td><input name="corMertRateDto.lowFeeValue" id="lowFeeValue" value="${corMertRateDto.lowFeeValue}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corMertRateDto.hightFeeValue') }：</th>
								<td><input name="corMertRateDto.hightFeeValue" id="hightFeeValue" value="${corMertRateDto.hightFeeValue}" class="width_c"  maxlength="50" /></td>
								<td style="display: none;"><input name="corMertRateDto.serviceFeeType" id="serviceFeeType" value="${corMertRateDto.serviceFeeType}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corMertRateDto.effectDate') }：</th>
								<td>
									<input type="hidden" name="corMertRateDto.effectDate" id="effectDate" value="${corMertRateDto.effectDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:nextDate})" class="Wdate" style="width:188px"/>
									<input name="corMertRateDto.effectDate" id="effectDate_Temp" value="${corMertRateDto.effectDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:nextDate})" class="Wdate" style="width:188px"/>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('corMertRateDto.expireDate') }：</th>
								<td><input name="corMertRateDto.expireDate" id="expireDate" value="${corMertRateDto.expireDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:nextDate})" class="Wdate" style="width:188px"/></td>
							</tr>
							<tr>
								<th>${app:i18n('corMertRateDto.remark') }：</th>
								<td><input name="corMertRateDto.remark" id="remark" value="${corMertRateDto.remark}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr class="addNotOprea">
								<th>${app:i18n('corMertRateDto.createTime') }：</th>
								<td><input name="corMertRateDto.createTime" id="createTime" value="${corMertRateDto.createTime}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr class="addNotOprea">
								<th>${app:i18n('corMertRateDto.creator') }：</th>
								<td><input name="corMertRateDto.creator" id="creator" value="${corMertRateDto.creator}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr class="addNotOprea">
								<th>${app:i18n('corMertRateDto.updateTime') }：</th>
								<td><input name="corMertRateDto.updateTime" id="updateTime" value="${corMertRateDto.updateTime}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr class="addNotOprea">
								<th>${app:i18n('corMertRateDto.updator') }：</th>
								<td><input name="corMertRateDto.updator" id="updator" value="${corMertRateDto.updator}" class="width_c"  maxlength="50" /></td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
			<div id="tabs-1">
				<a id="saveBt" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.save')}</span></span></a>
				<a id="undoBt" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
