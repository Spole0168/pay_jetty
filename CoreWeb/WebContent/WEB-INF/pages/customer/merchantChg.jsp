<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
var operaType = 0;//operaType  : 0 -新增，1-查看，2-审核，3-修改

function initPage(){
	clearWarningInfo();
	operaType = ${operaType};//operaType
	$("#operaType").val(operaType);
	console.log("operaType="+operaType);
	//设置只读属性时，可能会影响父页面的文本框内容。
	//设置下拉框数据。
	if(0==operaType){			//新增逻辑控制
		$("#titleTopic").html("新增页面");
		setSelectInfos();
		$("tr:[class='addNotOprea']").hide();//隐藏  -新增时商户编号
		$("tr:[class='auditOprea']").hide(); //隐藏 审核信息
		$("tr:[class='createOprea']").hide();  //隐藏 创建人、审核人、及时间
		$("tr:[class='reviewOprea']").hide(); //隐藏 审核信息
		$("tr:[class='webOprea']").hide();//商户网站隐藏
		$("tr:[class='revOprea']").hide();
		$("tr:[class='reviewStatusOprea']").hide();
		$("tr:[class='updateOprea']").hide();
		$("tr:[class='modifyOprea']").hide();
		$("tr:[class='isCheckOprea']").hide();
		$("tr:[class='initOprea']").hide();
		$("tr:[class='mertStatusOprea']").hide();
		$("tr:[class='tempAuditOprea']").hide();
	}else if(1==operaType){	//查看逻辑控制
		setSelectInfos();
		$("#titleTopic").html("查看页面");
		$("tr:[class='addNotOprea']").show();//显示 企业会员编号
		$("tr:[class='uplodOprea']").hide();  //隐藏 上传凭证
		$("tr:[class='reviewStatusOprea']").show();
		$("tr:[class='revOprea']").show();
		$("tr:[class='reviewOprea']").show();//显示审核失败原因
		$("tr:[class='auditOprea']").hide(); //隐藏  审核信息
		$("tr:[class='createOprea']").show();  //显示  创建人、审核人、及时间
		$("#save").hide(); 					//隐藏保存按钮
		$("#merchatEditForm  input[class='Wdate']").attr("disabled","disabled"); 
		$("#merchatEditForm  input").attr("readonly","readonly");   		//设置input  只读
		$("#merchatEditForm  select").attr("disabled","disabled");  		//设置input  只读
	}else if(2==operaType){	//审核逻辑控制。
		setSelectInfos();
		$("#titleTopic").html("审核页面");
		$("#merchatEditForm  input").attr("disabled","disabled");   		//设置input  只读
		$("#merchatEditForm  select").attr("disabled","disabled");   		//设置input  只读
		$("#merchatEditForm  input[class='Wdate']").attr("disabled","disabled"); 
		$("tr:[class='auditOprea'] select").attr("disabled",""); 
		$("tr:[class='revOprea']").hide();
		$("tr:[class='reviewStatusOprea']").show();
		$("tr:[class='addNotOprea']").show();//显示 企业会员编号
		$("tr:[class='uplodOprea']").show();  //隐藏 上传凭证
		$("tr:[class='auditOprea']").show(); //隐藏  审核信息
		$("tr:[class='createOprea']").show();  //显示  创建人、审核人、及时间
		$("tr:[class='modifyOprea']").show();
		$("tr:[class='tempAuditOprea']").hide();
	}else if(3==operaType){//修改
		setSelectInfos();
		$("#titleTopic").html("修改界面");
		$("tr:[class='custOprea'] input").attr("readonly","readonly");
		$("tr:[class='auditOprea'] select").attr("disabled","disabled");
		$("tr:[class='industryOprea'] select").attr("disabled","disabled");
		$("#activeDate").attr("disabled","disabled");   
		$("tr:[class='addNotOprea']").hide();//显示 企业会员编号
		$("tr:[class='auditOprea']").hide(); //显示  审核信息
		$("tr:[class='createOprea']").show();  //显示  创建人、审核人、及时间
		$("tr:[class='reviewOprea']").hide(); //隐藏 审核信息
		$("tr:[class='statusOprea']").hide();//隐藏下拉式状态
		$("tr:[class='revOprea']").hide();
		$("tr:[class='reviewStatusOprea']").hide();
		$("tr:[class='updateOprea']").hide();
		$("tr:[class='isCheckOprea']").hide();
		$("tr:[class='initOprea']").hide();
		$("tr:[class='mertStatusOprea']").hide();
		$("tr:[class='initOprea'] input").attr("disabled","disabled");
		$("tr:[class='tempAuditOprea']").show();
	}
}
function setSelectInfos(){
	$("#status").val('${merchantDto.status}');
	$("#industry").val('${merchantChg.industry}');
	$("#isCheckin").val('${merchantChg.isCheckin}');
	$("#isCheckout").val('${merchantChg.isCheckout}');
	$("#riskLevel").val('${merchantChg.riskLevel}');
	$("#merchantDtostatus").val('${merchantChg.status}');
	
	$("#activeDate").val(transDateTime2Date('${merchantChg.activeDate}'));
	$("#inactiveDate").val(transDateTime2Date('${merchantChg.inactiveDate}'));
	$("#settStartDate").val(transDateTime2Date('${merchantChg.settStartDate}'));
	//设置国家 省 市  下拉框的信息
	$("#country").val('${corMertBankAcntChg.country}');
	
	 var provinceInfo = getProvince('${corMertBankAcntChg.country}');
	 setValueToSelector("provience",provinceInfo);
	 $("#provience").val('${corMertBankAcntChg.provience}');
	 
	 var cityInfo = getCity('${corMertBankAcntChg.provience}');
	 setValueToSelector("city",cityInfo);
	 $("#city").val('${corMertBankAcntChg.city}');
	 
	 var bankInfo = getBankName('${corMertBankAcntChg.city}');
	 setValueToSelector("bankName",bankInfo);
	$("#bankName").val('${corMertBankAcntChg.bankCode}');
		
	 var bankBranchInfo = getBankBranchName('${corMertBankAcntChg.bankCode}','${corMertBankAcntChg.city}');
	 setValueToSelector("bankBranchName",bankBranchInfo);
	 $("#bankBranchName").val('${corMertBankAcntChg.bankBranchCode}');
	 
	//设置商户状态 数据转义
		$("#tempAudit").val(getSelectValue('${statusTypeRender}','${merchantDto.status}'));
}

function resetSelect(selectId,value){
	alert("----"+country);
	$("#"+selectId).empty();
	$("#"+selectId).append("<option value=''>"+value+"</option>");
}

//转换下拉框的值
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

function testStatus(){
	$("#reviewFailureReasons").val("");
	$("#reviewFailureReasons").attr("disabled","disabled");
	
	$("#riskLevel").val("");
	$("#riskLevel").attr("disabled","disabled");
	
	$("#initialDeposit").val("");
	$("#initialDeposit").attr("disabled","disabled");
	$("#merchantId").attr("disabled","");
	$("#operaType").attr("disabled","");
	
	if($("#status").val()=="03"){
		$("#reviewFailureReasons").attr("disabled","");
		$("#riskLevel").attr("disabled","disabled");
		$("#initialDeposit").attr("disabled","disabled");
		return;
	}
	if($("#status").val()=="02"){
		$("#reviewFailureReasons").attr("disabled","disabled");
		$("#riskLevel").attr("disabled","");
		$("#initialDeposit").attr("disabled","");
	}
}

//表单校验
  function checkForms(){
	  if(0==operaType){//新增
		  	if(isNullOrBlank("custCode")){//必输项校验
				return "会员编号  不能为空";
			}
		  	if(isNullOrBlank("custName")){
				return "会员名称  不能为空";
			}
		  	if(isNullOrBlank("merchantName")){
				return "商户名称 不能为空";
			}
		  	if(isNullOrBlank("industry")){
				return "行业类型  不能为空";
			}
		  	if(isNullOrBlank("mccCode")){
				return "行业类别码 不能为空";
			}
		  	if(isNullOrBlank("operateScope")){
				return "经营范围  不能为空";
			}
		  	if(isNullOrBlank("website")){
				return "商户网站 不能为空";
			}
		  	if(isNullOrBlank("contractCode")){
				return "合同编号  不能为空";
			}
		  	if(isNullOrBlank("activeDate")){
				return "合同起始日 不能为空";
			}
		  	if(isNullOrBlank("inactiveDate")){
				return "合同结束日 不能为空";
			}
		  	if(isNullOrBlank("settPeriod")){
				return "结算周期  不能为空";
			}
		  	if(isNullOrBlank("settStartDate")){
				return "结算起始日 不能为空";
			}
		  	if(isNullOrBlank("bankCustName")){
				return "银行开户名 不能为空";
			}
		  	if(isNullOrBlank("country")){
				return "开户行所在国家  不能为空";
			}
		  	if(isNullOrBlank("provience")){
				return "开户行所在省 不能为空";
			}
		  	if(isNullOrBlank("city")){
				return "开户行所在市  不能为空";
			}
		  	if(isNullOrBlank("bankName")){
				return "开户银行 不能为空";
			}
		  	if(isNullOrBlank("bankBranchName")){
				return "开户支行  不能为空";
			}
		  	if(isNullOrBlank("bankCardNum")){
				return "公司对公账户 不能为空";
			}
		  	if(isNullOrBlank("total3mCount")){
				return "近三个月交易量  不能为空";
			}
		  	if(isNullOrBlank("businessResponse")){
				return "业务负责人 不能为空";
			}if(isNullOrBlank("businessResponsePhone")){
				return "业务负责人电话 不能为空";
			}
			if(isNullOrBlank("businessResponseEmail")){
				return "业务负责人邮箱 不能为空";
			}
		  	
			var filePic = $("#file").val()+"".toLowerCase();
			if(null!=filePic&&filePic!=""){
				if(!/.(pdf|zip|rar)$/.test(filePic)){
					return "凭证图片类型必须是PDF,zip,rar中的一种";
		        }				
			}else{
				return "凭证图片   必输";
			}  
	  }else if(3==operaType){//修改
		  	if(isNullOrBlank("merchantName")){
				return "商户名称 不能为空";
			}
		  	if(isNullOrBlank("website")){
				return "商户网站 不能为空";
			}
		  	if(isNullOrBlank("activeDate")){
				return "合同起始日 不能为空";
			}
		  	if(isNullOrBlank("inactiveDate")){
				return "合同结束日 不能为空";
			}
		  	if(isNullOrBlank("settPeriod")){
				return "结算周期  不能为空";
			}
		  	if (isNullOrBlank("settStartDate")) {
				return "结算起始日 不能为空";
			}
		  	if(isNullOrBlank("bankCustName")){
				return "银行开户名 不能为空";
			}
		  	if(isNullOrBlank("country")){
				return "开户行所在国家  不能为空";
			}
		  	if(isNullOrBlank("provience")){
				return "开户行所在省 不能为空";
			}
		  	if(isNullOrBlank("city")){
				return "开户行所在市  不能为空";
			}
		  	if(isNullOrBlank("bankName")){
				return "开户银行 不能为空";
			}
		  	if(isNullOrBlank("bankBranchName")){
				return "开户支行  不能为空";
			}
		  	if(isNullOrBlank("bankCardNum")){
				return "公司对公账户 不能为空";
			}
		  	if(isNullOrBlank("modifyContent")){
				return "修改内容 不能为空";
			}
		  	if (isNullOrBlank("businessResponse")) {
				return "业务负责人 不能为空";
			}
		  	if (isNullOrBlank("businessResponsePhone")) {
				return "业务负责人电话 不能为空";
			}
			if (isNullOrBlank("businessResponseEmail")) {
				return "业务负责人邮箱 不能为空";
			}
			if (!isChinese("bankCustName")) {
				return "银行开户名  非法";
			}
		  	if (!isIntNumber("bankCardNum")) {
				return "公司对公账户 非法";
			}
		  	if (null != $("#tecResponse").val()
					&&$.trim($("#tecResponse").val()) != '') {
				if (!isCh("tecResponse")) {
					return "技术负责人  非法";
				}
			}
		  	if (null != $("#tecResponsePhone").val()
					&&$.trim($("#tecResponsePhone").val()) != '') {

				if (!isContactBase("tecResponsePhone")) {
					return "技术负责人电话  非法";
				}
			}
			if (null != $("#tecResponseEmail").val()
					&&$.trim($("#tecResponseEmail").val()) != '') {
				if (!isEmailBase("tecResponseEmail")) {
					return "技术负责人邮箱  非法";
				}
			}
			if (!isChinese("businessResponse")) {
				return "业务负责人 非法";
			}
				if (!isContact("businessResponsePhone")) {
					return "业务负责人电话  非法";
				}
			
			if (!isEmail("businessResponseEmail")) {//必输项校验
				return "业务负责人邮箱   非法";
			}
			var filePic = $("#file").val()+"".toLowerCase();
			if(null!=filePic&&filePic!=""){
				if(!/.(pdf|zip|rar)$/.test(filePic)){
					return "凭证图片类型必须是PDF,zip,rar中的一种";
		        }
			}else{
				return "凭证图片   必输";
			}  
	  }else if(2==operaType){
		  if(isNullOrBlank("status")){
				return "审核状态 不能为空";
			}
		  if($.trim($("#status").val())=='03'&&isNullOrBlank("reviewFailureReasons")){
				return "审核失败 失败原因  不能为空";
			}
		  if($.trim($("#status").val())=='02'&&isNullOrBlank("riskLevel")){
				return "审核成功  风险等级 必填";
			}
		  if($.trim($("#status").val())=='02'&&isNullOrBlank("initialDeposit")){
				return "审核成功 保证金必填 必填";
			}
		  
		  if ($.trim($("#status").val()) == '02'
				&& !isNullOrBlank("initialDeposit")) {
				if (!isAmount("initialDeposit")) {
					return "保证金值  非法";
				}
		}
	  }
	  return "";
}
//合同日期校验
	function checkDate(){
		var startTime = $("#activeDate").val();
		var endTime = $("#inactiveDate").val();
		if(null!=startTime&&startTime.length>0&&null!=endTime&&endTime.length>0){
			startTime = startTime.replace(/-/g,'/');
			endTime = endTime.replace(/-/g,'/');
			startTime = new Date(startTime);
			endTime = new Date(endTime);
			var times = startTime.getTime() - endTime.getTime();
			var days = Math.abs(parseInt(times/(1000*60*60*24)));
			if(times >= 0) {
				return "合同开始时间不能大于结束时间";
			} 
		}
		return "";
	}

  /**
	 * 校验是否是 电话号码 或者 手机号
	 */
	function isContactBase(id){
		var value = $.trim($("#"+id).val());
		var regTel = /^1[34578]\d{9}$/; 
		var regPhone  = /^(\d{3,4}-)?\d{7,8}$/;
		if(!value||(!regTel.test(value)&&!regPhone.test(value))){
	        return false;
	    }
	    return true;
	}
	
	function isEmailBase(id){
		var value = $.trim($("#"+id).val());
		var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/gi; 
	    if(!value||!reg.test(value)){
	        return false;
	    }
	    return true;  
	} 
	
	function isIntNumberBase(id) {
		var reg = new RegExp("^[0-9]*$");
		var value = $.trim($("#" + id).val());
		if (!value || !reg.test(value)) {
			return false;
		}
		return true;
	}
	function isChinese(id){
		var reg = /^[\u4E00-\u9FA5]+$/;
		var value = $.trim($("#" + id).val());
		if (!value || !reg.test(value)) {
			$("#" + id).css("border-color", "red");
			$("#" + id).focus();
			$("#" + id).bind('change', function() {
				removeRed(id);
			});
			return false;
		}
		return true;	
		
	}
	function isCh(id){
		var reg = /^[\u4E00-\u9FA5]+$/;
		var value = $.trim($("#" + id).val());
		if (!value || !reg.test(value)) {
			return false;
		}
		return true;	
		
	}
 $().ready(function() {
	
	initPage();
		
	$("#save").click(function(){
		clearWarningInfo();
		var flags = checkForms();
		if(flags!=""){
			showWarningInfo(flags);
			return;
		}
		var check = checkDate();
		if (check != "") {
			showWarningInfo(check);
			return;
		}
		$.boxUtil.confirm(
			'请确认是否保存信息？', 
			null, 
			function(){
// 				var option = {
// 					url : 'saveOrUpdateMerchant.action',
// 					type : 'POST',
// 					dataType : 'json',
// 					headers : {"ClientCallMode" : "ajax"},
// 					success : function(data) {
// 						$(dlgUserId).dialog('close');
// 			        	doSearch();
// 		             }
// 		        };
// 		       $("#merchatEditForm").ajaxSubmit(option);
				$("#merchatEditForm").submit();
		       return false;
			}, 
			function(){
				//return false;
			});
		return false;
	});

	$("#undo").click(function(){
		$(dlgUserId).dialog('close');
	});
	
	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();


	// 根据countryCode获取province
	$("#country").change(function(){
		 var countryCode =$("#country option:selected").val();
		 if(countryCode == ""){
			 delOptionsSelector("provience");
			 delOptionsSelector("city");
			 delOptionsSelector("bankName");
			 delOptionsSelector("bankBranchName");
		 }else{
			 delOptionsSelector("provience");
			 delOptionsSelector("city");
			 delOptionsSelector("bankName");
			 delOptionsSelector("bankBranchName");
			 var provinceInfo = getProvince(countryCode);
			 setValueToSelector("provience",provinceInfo);
		 }
	});
	
	//根据provienceCode获取city
	$("#provience").change(function(){
		 var provinceCode =$("#provience option:selected").val();
		 if(provinceCode!=""){
			 delOptionsSelector("city");
			 delOptionsSelector("bankName");
			 delOptionsSelector("bankBranchName");
			 var cityInfo = getCity(provinceCode);
			 setValueToSelector("city",cityInfo);
		 }else{
			 delOptionsSelector("city");
			 delOptionsSelector("bankName");
			 delOptionsSelector("bankBranchName");
		 }
	});

	//根据citCode获取bank
	$("#city").change(function(){
		 var cityCode =$("#city option:selected").val();
		 if(cityCode!=""){
			 delOptionsSelector("bankName");
			 delOptionsSelector("bankBranchName");
			 var bankInfo = getBankName(cityCode);
			 setValueToSelector("bankName",bankInfo);
		 }else{
			 delOptionsSelector("bankName");
			 delOptionsSelector("bankBranchName");
		 }
	});
	
	//根据bankCode获取bank
	$("#bankName").change(function(){
		 var bankCode =$("#bankName option:selected").val();
		 var cityCode =$("#city option:selected").val();
		 if(bankCode!=""){
			 var bankBranchInfo = getBankBranchName(bankCode,cityCode);
			 setValueToSelector("bankBranchName",bankBranchInfo);
		 }else{
			 delOptionsSelector("bankBranchName");
		 }
	});
		
});	

function selectBank(bankValue){
	$("#bankValue").val(bankValue);
}
function selectBankBranch(bankBranchValue){
	$("#bankBranchValue").val(bankBranchValue);
}

//获取分行信息
function getBankBranchName(bankCode,cityCode){
	var bankBranchName;
	 $.ajax({
          type: "post",
          url: "getBankBranchName.action",
          data: {bankCode:bankCode,cityCode:cityCode},
          dataType: "json",
          async:false,
          success: function(data){
        	  bankBranchName=data.message;
          }
      });
	 return bankBranchName;
}

//获取省份信息
function getProvince(countryCode){
	var province;
	 $.ajax({
          type: "post",
          url: "getProvince.action",
          data: {countryCode:countryCode},
          dataType: "json",
          async:false,
          success: function(data){
         	 province=data.message;
          }
      });
	 return province;
}
//获取城市信息
function getCity(provinceCode){
	var citys;
	 $.ajax({
          type: "post",
          url: "getCity.action",
          data: {provinceCode:provinceCode},
          dataType: "json",
          async:false,
          success: function(data){
        	  citys=data.message;
          }
      });
	 return citys;
}
//获取银行信息
function getBankName(cityCode){
	var bankName;
	 $.ajax({
          type: "post",
          url: "getBankName.action",
          data: {cityCode:cityCode},
          dataType: "json",
          async:false,
          success: function(data){
        	  bankName=data.message;
          }
      });
	 return bankName;
}
//给select选择器赋值
function setValueToSelector(selectId,datas){
	if(datas != null){
		for(var i=0; i<datas.length; i++){
			$("#"+selectId).append("<option value='"+datas[i].key+"'>"+datas[i].value+"</option>");
	   	} 
	}
}
//移除select中的option
function delOptionsSelector(selectId){
	$("#"+selectId).empty();
	$("#"+selectId).append("<option value=''>${app:i18n('merchant.selected_default') }</option>");
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
function doSave(){
	<c:if test="${isModify}">
		$("#merchatEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#merchatEditForm").submit(); 
	</c:if>
}

</script>

<!-- <div id="alertDialog"></div> -->
<s:form id="merchatEditForm" method="post"
	action="saveOrUpdateMerchant.action?loadPageCache=true"
	namespace="/merchantManagementChg" enctype="multipart/form-data">
	<s:hidden name="isModify" />
	<input type="hidden" name="id" id="merchantId" value="${id}" />
	<div class="layout">
		<div class="block m-b">
			<div class="block_container">
				<div class="fieldset_border fieldset_bg m-b">
					<div class="fieldset_container">
						<form id="validate_form">
							<div class="warning" id="warningInfo" style="display: none;">
								<span></span>
							</div>
							<input type="hidden" id="operaType" name="operaType"
								value="${operaType}"/>
							<table cellpadding="0" cellspacing="0" class="table_form" id="tableInfo">
								<thead>
								</thead>
								<tfoot>
								</tfoot>
								<tbody>
									<tr class="custOprea">
										<th><em>*</em>${app:i18n('merchant.cust_code') }：</th>
										<td><input name="merchantChg.custCode" id="custCode"
											class="width_c" value="${merchantChg.custCode}"
											maxlength="50" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('merchant.cust_name') }：</th>
										<td><input name="custName" id="custName" class="width_c"
											value="${custName}" readonly="readonly" /></td>
									</tr>
									<tr class="addNotOprea">
										<th><em>*</em>${app:i18n('merchant.merchant_code') }：</th>
										<td><input name="merchantChg.merchantCode"
											id="merchantCode" class="width_c"
											value="${merchantChg.merchantCode}" maxlength="50"
											readonly="readonly" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('merchant.merchant_name') }：</th>
										<td><input name="merchantChg.merchantName"
											id="merchantName" class="width_c"
											value="${merchantChg.merchantName}" maxlength="225" /></td>
									</tr>
									<tr class="industryOprea">
										<th><em>*</em>${app:i18n('merchant.industry') }：</th>
										<td><select name="merchantChg.industry" id="industry"
											value="${merchantChg.industry}" style="width: 190px">
												<option value="" selected>${app:i18n('merchant.selected_default')}</option>
												<s:iterator value="%{industryList}" id="industryItem">
													<option value="<s:property value="#industryItem.key" />">
														<s:property value="#industryItem.value" />
													</option>
												</s:iterator>
										</select></td>
									</tr>

									<tr>
										<th><em>*</em>${app:i18n('merchant.mccCode') }：</th>
										<td><input name="merchantChg.mccCode" id="mccCode"
											class="width_c" value="${merchantChg.mccCode}"
											maxlength="225" readonly="readonly" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('merchant.operate_scope') }：</th>
										<td><input name="merchantChg.operateScope"
											id="operateScope" class="width_c"
											value="${merchantChg.operateScope}" maxlength="225"
											readonly="readonly" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('merchant.website') }：</th>
										<td><input name="merchantChg.website" id="website"
											class="width_c" value="${merchantChg.website}"
											maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('merchant.contract_code') }：</th>
										<td><input name="merchantChg.contractCode"
											id="contractCode" class="width_c"
											value="${merchantChg.contractCode}" maxlength="225"
											readonly="readonly" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('merchant.active_date') }：</th>
										<td><input name="merchantChg.activeDate" id="activeDate"
											class="Wdate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
											value="${merchantChg.activeDate}" style="width: 188px"
											readonly="readonly" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('merchant.inactive_date') }：</th>
										<td><input name="merchantChg.inactiveDate"
											id="inactiveDate" class="Wdate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
											value="${merchantChg.inactiveDate}" style="width: 188px" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('merchant.settlement_period') }：</th>
										<td>T+ <%-- 								<input name="merchant.settlementPeriod" id="settlementPeriod" class="width_c" value="${merchant.settlementPeriod}" /> --%>
											<select name="merchantChg.settPeriod" id="settPeriod">
												<c:forEach var="i" begin="1" end="31" step="1">
													<c:if test="${i == merchantChg.settPeriod }">
														<option value="${i}" selected>${i}</option>
													</c:if>
													<c:if test="${i != merchantChg.settPeriod }">
														<option value="${i}">${i}</option>
													</c:if>
												</c:forEach>
										</select>
										</td>
									</tr>

									<tr>
										<th><em>*</em>${app:i18n('merchant.settStartDate') }：</th>
										<td><input name="merchantChg.settStartDate"
											id="settStartDate" class="Wdate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
											value="${merchantChg.settStartDate}" style="width: 188px" /></td>
									</tr>

									<tr>
										<th><em>*</em>${app:i18n('corMertBankAcnt.bankCustName') }：</th>
										<td><input name="corMertBankAcntChg.bankCustName"
											id="bankCustName" class="width_c"
											value="${corMertBankAcntChg.bankCustName}" maxlength="225" /></td>
									</tr>


									<tr>
										<th><em>*</em>${app:i18n('corMertBankAcnt.country') }：</th>
										<td><select name="corMertBankAcntChg.country"
											id="country" value="${corMertBankAcntChg.country}"
											style="width: 190px">
												<option value="">${app:i18n('merchant.selected_default') }</option>
												<s:iterator value="countryList" var="country">
													<option value="${country.key}">${country.value}</option>
												</s:iterator>
										</select></td>
									<tr>
										<th><em>*</em>${app:i18n('corMertBankAcnt.provience') }：</th>
										<td><select name="corMertBankAcntChg.provience"
											id="provience" value="${corMertBankAcntChg.provience}"
											style="width: 190px">
												<option value="">${app:i18n('merchant.selected_default') }</option>
										</select></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corMertBankAcnt.city') }：</th>
										<td><select name="corMertBankAcntChg.city" id="city"
											value="${corMertBankAcntChg.city}" style="width: 190px">
												<option value="">${app:i18n('merchant.selected_default') }</option>
										</select></td>
									</tr>

									<tr>
										<th><em>*</em>${app:i18n('corMertBankAcnt.bankName') }：</th>
										<td><select name="corMertBankAcntChg.bankCode"
											id="bankName" value="${corMertBankAcnt.bankCode}"
											style="width: 190px"
											onchange="selectBank(this.options[this.selectedIndex].text)">
												<option value="" selected>${app:i18n('merchant.selected_default')}</option>
										</select></td>
										<input type="hidden" id="bankValue"
											name="corMertBankAcntChg.bankName" value="${corMertBankAcntChg.bankName}"/>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('corMertBankAcnt.bankBranchName') }：</th>
										<td><select name="corMertBankAcntChg.bankBranchCode"
											value="${corMertBankAcntChg.bankBranchCode}"
											id="bankBranchName" style="width: 190px"
											onchange="selectBankBranch(this.options[this.selectedIndex].text)">
												<option value="" selected>${app:i18n('merchant.selected_default')}</option>
										</select></td>
										<input type="hidden" id="bankBranchValue"
											name="corMertBankAcntChg.bankBranchName"
											value="${corMertBankAcntChg.bankBranchName}"/>
									</tr>

									<tr>
										<th><em>*</em>${app:i18n('corMertBankAcnt.bankCardNum') }：</th>
										<td><input name="corMertBankAcntChg.bankCardNum"
											id="bankCardNum" class="width_c"
											value="${corMertBankAcntChg.bankCardNum}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('merchant.total3mCount') }：</th>
										<td><input name="merchantChg.total3mCount"
											id="total3mCount" class="width_c"
											value="${merchantChg.total3mCount}" maxlength="225"
											style="width: 165px" readonly="readonly" />笔</td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('merchant.total3mAmount') }：</th>
										<td><input name="merchantChg.total3mAmount"
											id="total3mAmount" class="width_c"
											value="${merchantChg.total3mAmount}" maxlength="225"
											readonly="readonly" /></td>
									</tr>

									<tr>
										<th>${app:i18n('merchant.tec_response') }：</th>
										<td><input name="merchantChg.tecResponse"
											id="tecResponse" class="width_c"
											value="${merchantChg.tecResponse}" maxlength="225" /></td>
									</tr>
									<tr>
										<th>${app:i18n('merchant.tec_response_phone') }：</th>
										<td><input name="merchantChg.tecResponsePhone"
											id="tecResponsePhone" class="width_c"
											value="${merchantChg.tecResponsePhone}" maxlength="225" /></td>
									</tr>
									<tr>
										<th>${app:i18n('merchant.tec_response_email') }：</th>
										<td><input name="merchantChg.tecResponseEmail"
											id="tecResponseEmail" class="width_c"
											value="${merchantChg.tecResponseEmail}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('merchant.business_response') }：</th>
										<td><input name="merchantChg.businessResponse"
											id="businessResponse" class="width_c"
											value="${merchantChg.businessResponse}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('merchant.business_response_phone') }：</th>
										<td><input name="merchantChg.businessResponsePhone"
											id="businessResponsePhone" class="width_c"
											value="${merchantChg.businessResponsePhone}" maxlength="225" /></td>
									</tr>
									<tr>
										<th><em>*</em>${app:i18n('merchant.business_response_email') }：</th>
										<td><input name="merchantChg.businessResponseEmail"
											id="businessResponseEmail" class="width_c"
											value="${merchantChg.businessResponseEmail}" maxlength="225" /></td>
									</tr>
									<tr>
										<th>${app:i18n('merchant.remark') }：</th>
										<td><input name="merchantChg.remark" id="remark"
											class="width_c" value="${merchantChg.remark}" maxlength="225" /></td>
									</tr>
									<tr class="modifyOprea">
										<th><em>*</em>${app:i18n('merchant.modifyContent') }：</th>
										<td><input name="merchantChg.modifyContent"
											id="modifyContent" class="width_c"
											value="${merchantChg.modifyContent}" maxlength="225" /></td>
									</tr>

									<tr class="uplodOprea">
										<th><em>*</em>${app:i18n('merchant.upload') }：</th>
										<td><s:if test="merchantChg.voutherUploadPath != null">
												<a href="<%=request.getContextPath()%>/downloadFile.action?fileId=${merchantChg.voutherUploadPath}" target="_blank">${app:i18n('merchant.uploadLink') }</a>
<%-- 											</s:if> <s:elseif test="operaType == 3 "> --%>
<!-- 												<input id="file" type="file" name="file" /> -->
<%-- 											</s:elseif></td> --%>
												</s:if>
												<s:if test="operaType != 1 and operaType !=2 "> 
 												<input id="file" type="file" name="file" /> 
 												</s:if>
											</td> 
									</tr>
									<tr class="isCheckOprea">
										<th><em>*</em>${app:i18n('merchant.isCheckin') }：</th>
										<td><select name="merchantChg.isCheckin" id="isCheckin"
											value="${merchantChg.isCheckin}" style="width: 190px">

												<s:iterator value="%{isCheckinList}" id="isCheckinItem">
													<option value="<s:property value="#isCheckinItem.key" />">
														<s:property value="#isCheckinItem.value" />
													</option>
												</s:iterator>
										</select></td>
									</tr>
									<tr class="isCheckOprea">
										<th><em>*</em>${app:i18n('merchant.isCheckout') }：</th>
										<td><select name="merchantChg.isCheckout" id="isCheckout"
											value="${merchantChg.isCheckout}" style="width: 190px">
												<s:iterator value="%{isCheckoutList}" id="isCheckoutItem">
													<option value="<s:property value="#isCheckoutItem.key" />">
														<s:property value="#isCheckoutItem.value" />
													</option>
												</s:iterator>
										</select></td>
									</tr>

									<tr class="auditOprea">
										<th><em>*</em>${app:i18n('merchant.status') }：</th>
										<td><select name="merchantChg.status" id="status"
											style="width: 190px" value="${merchantDto.status}"
											onchange="testStatus()">
												<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
												<s:iterator value="%{statusOperaList}" id="statusOpera">
													<option value="<s:property value="#statusOpera.key" />">
														<s:property value="#statusOpera.value" />
													</option>
												</s:iterator>
										</select></td>
									</tr>
									<tr class="tempAuditOprea">
										<th><em>*</em>${app:i18n('merchant.status') }：</th>
										<td><input name="tempAudit" id="tempAudit"
											class="width_c" value="${tempAudit}"
											readonly="readonly" /></td>
									</tr>
									<tr class="mertStatusOprea">
										<th><em>*</em>${app:i18n('merchant.mertStatus') }：</th>
										<td><select name="merchantDto.mertStatus"
											id="merchantDtostatus" style="width: 190px"
											value="${merchantChg.status}">
												<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
												<s:iterator value="%{mertStatusList}" id="mertStatus">
													<option value="<s:property value="#mertStatus.key" />">
														<s:property value="#mertStatus.value" />
													</option>
												</s:iterator>
										</select></td>
									</tr>

									<tr class="createOprea">
										<th>${app:i18n('merchant.creator') }：</th>
										<td><input name="merchantChg.creator" id="creator"
											class="width_c" value="${merchantChg.creator}"
											readonly="readonly" /></td>
									</tr>
									<tr class="createOprea">
										<th>${app:i18n('merchant.create_time') }：</th>
										<td><input name="merchantChg.createTime" id="createTime"
											class="width_c" value="${merchantChg.createTime}"
											readonly="readonly" /></td>
									</tr>
									<tr class="createOprea">
										<th>${app:i18n('merchant.updator') }：</th>
										<td><input name="merchantChg.updator" id="updator"
											class="width_c" value="${merchantChg.updator}"
											readonly="readonly" /></td>
									</tr>
									<tr class="createOprea">
										<th>${app:i18n('merchant.update_time') }：</th>
										<td><input name="merchantChg.updateTime" id="updateTime"
											class="width_c" value="${merchantChg.updateTime}"
											readonly="readonly" /></td>
									</tr>
									<tr class="reviewOprea">
										<th><em>*</em>${app:i18n('merchant.review_failure_reasons') }：</th>
										<td><input name="merchantDto.reviewFailureReasons"
											id="reviewFailureReasons" class="width_c"
											value="${merchantDto.reviewFailureReasons}" /></td>
									</tr>
									<tr class="perTimeOprea">
										<th>${app:i18n('merchant.review_person') }：</th>
										<td><input name="merchantDto.reviewPerson"
											id="reviewPerson" class="width_c"
											value="${merchantDto.reviewPerson}" readonly="readonly"/></td>
									</tr>

										<tr class="perTimeOprea">
										<th>${app:i18n('merchant.review_time') }：</th>
										<td><input name="merchantDto.reviewTime"
											id="reviewTime" class="width_c"
											value="${merchantDto.reviewTime}" readonly="readonly"/></td>
									</tr>
									<tr class="reviewStatusOprea">
										<th><em>*</em>${app:i18n('merchant.riskLevel') }：</th>
										<td><select name="merchantChg.riskLevel" id="riskLevel"
											value="${merchantChg.riskLevel}" style="width: 190px">
												<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
												<s:iterator value="%{riskLevelList}" id="riskLevelItem">
													<option value="<s:property value="#riskLevelItem.key" />">
														<s:property value="#riskLevelItem.value" />
													</option>
												</s:iterator>
										</select></td>
									</tr>

									<tr class="initOprea">
										<th><em>*</em>${app:i18n('merchant.initialDeposit') }：</th>
										<td><input name="merchantChg.initialDeposit"
											id="initialDeposit" class="width_c"
											value="${merchantChg.initialDeposit}" /></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>
				<div id="tabs-1">
					<a id="save" class="easyui-linkbutton l-btn" href="#"><span
						class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.save')}</span></span></a>
					<a id="undo" class="easyui-linkbutton l-btn" href="#"><span
						class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
				</div>
			</div>
		</div>
	</div>
</s:form>
