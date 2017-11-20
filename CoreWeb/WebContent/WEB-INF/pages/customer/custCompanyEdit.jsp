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
			$("#titleTopic").html("新增页面");
			$("tr:[class='addNotOprea']").hide();//隐藏  -新增时 企业会员编号
			$("tr:[class='handleOpera']").hide();//隐藏 新增和修改 【人和时间】
			$("tr:[class='auditOprea']").hide(); //隐藏 审核信息
			$("tr:[class='viewOprea']").hide();  //隐藏 审核【人和时间】
			$("#statusValue").hide();
		}else if(1==operaType){	//查看逻辑控制
			$("#titleTopic").html("查看页面");
			$("tr:[class='addNotOprea']").show();//显示 企业会员编号
			$("tr:[class='handleOpera']").show();//显示  新增和修改 【人和时间】
			$("tr:[class='auditOprea']").show(); //显示  审核信息
			$("tr:[class='viewOprea']").show();  //显示  审核【人和时间】
			$("#status").hide();  		//显示  审核【人和时间】
			$("#statusValue").show();  //显示  审核【人和时间】
			$("#submitBt").hide();					//显示提交按钮
			//设置下拉框当前值
			setSelectInfos();
			
			$("#companyEditForm  input").attr("readonly","readonly");   		//设置input  只读
			$("#companyEditForm  input[class='Wdate']").attr("disabled","disabled");   		//设置input  只读
			$("#companyEditForm  input[name='file']").attr("disabled","disabled");   		//设置input  只读
			$("#companyEditForm  select").attr("disabled","disabled");   		//设置input  只读
		}else if(2==operaType){	//审核逻辑控制。
			$("#titleTopic").html("审核页面");
			$("tr:[class='addNotOprea']").show();//显示 企业会员编号
			$("tr:[class='handleOpera']").show();//显示新增和修改 【人和时间】
			$("tr:[class='auditOprea']").show(); //显示 审核信息
			$("tr:[class='viewOprea']").hide();  //隐藏 审核【人和时间】
			$("#statusValue").hide();
			//设置下拉框当前值
			setSelectInfos();
			auditStatusChange();//置灰审核失败原因
			$("#companyEditForm  input").attr("readonly","readonly");   		//设置input  只读
			$("#companyEditForm  input[class='Wdate']").attr("disabled","disabled");   		//设置input  只读
			$("#companyEditForm  input[name='file']").attr("disabled","disabled");   		//设置input  只读
			$("#companyEditForm  select").attr("disabled","disabled");   		//设置input  只读
			$("tr:[class='auditOprea'] input").attr("readonly",""); 
			$("tr:[class='auditOprea'] select").attr("disabled","");  
		}else if(3==operaType){//修改
			$("#titleTopic").html("修改界面");
			$("tr:[class='addNotOprea']").show();//显示 企业会员编号
			$("tr:[class='handleOpera']").show();//显示  新增和修改 【人和时间】
			$("tr:[class='auditOprea']").show(); //显示  审核信息
			$("tr:[class='viewOprea']").show();  //显示  审核【人和时间】
			$("#statusValue").hide();
			//设置下拉框当前值
			setSelectInfos();
			$("tr:[class='handleOpera'] input").attr("readonly","readonly");
			$("tr:[class='auditOprea'] select").attr("disabled","disabled");
			$("tr:[class='auditOprea'] input").attr("readonly","readonly");
			$("tr:[class='viewOprea'] input").attr("readonly","readonly");
			var status= '${custCompanyDto.status}';
			var custStatus= '${custCompanyDto.custStatus}';
			var cmuid = '${custCompanyDto.cmuid}';
			if(custStatus=="01" && (null==cmuid||""==cmuid)){//会员状态为未生效审核状态为审核失败或者空的记录
				//企业会员编号，会员类型，创建人，创建时间，审核人，审核时间，修改人，修改时间不可修改
				$("#custType").attr("disabled","disabled");
			}else if(custStatus=="02" || (cmuid != null && cmuid.length > 0)){
				$("#companyEditForm  input").attr("readonly","readonly");   		//设置input  只读
				$("#companyEditForm  select").attr("disabled","disabled");   		//设置input  只读
				$("#companyEditForm  input[name='file']").attr("disabled","disabled");   		//设置input  只读
				$("tr:[class='editOpera'] input").attr("readonly","");
				$("#companyEditForm  input[class='Wdate']").attr("disabled","disabled");   	
			}
			
		}
	}
	function setSelectInfos(){
		$("#custType").val('${custCompanyDto.custType}');
		$("#unitType").val('${custCompanyDto.unitType}');
		$("#corporateCertType").val('${custCompanyDto.corporateCertType}');
		$("#status").val('${custCompanyDto.status}');
		//设置国家 省 市  下拉框的信息
		$("#country").val('${custCompanyDto.country}');
		getAreasInfo("country",'${custCompanyDto.country}');
		getAreasInfo("provience",'${custCompanyDto.provience}');
		$("#provience").val('${custCompanyDto.provience}');
		$("#city").val('${custCompanyDto.city}');
		// 设置日期
		$("#businessLicenseExpiry").val(transDateTime2Date('${custCompanyDto.businessLicenseExpiry}'));
		$("#regTime").val(transDateTime2Date('${custCompanyDto.regTime}'));
		
		//设置客户状态 数据转义
		$("#custStatusValue").val(getSelectValue('${custStatusTypeRender}','${custCompanyDto.custStatus}'));
		$("#statusValue").val(getSelectValue('${statusTypeRender}','${custCompanyDto.status}'));
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
	function selectChange(obj){
		var idName = obj.id;
		var code = $("#"+idName).val();
		if(idName=="city"){
			$("#region").val(code);
			return;
		}else if($("#"+idName).val!=""){
			getAreasInfo(idName,code);
		}else if($("#"+idName).val==""){
			resetSelect("provience");
			resetSelect("city");
		}
		$("#region").val(code);
	}
	//String type, String code
	function  getAreasInfo(idName,code){
		 $.ajax({
	          type: "post",
	          url: "custGetAreasInfo.action",
	          data: {idName:idName,code:code},
	          dataType: "json",
	          async:false,
	          success: function(data){
	        	var datas=data.message;
	        	var selectId = "provience";
	        	if(idName!=""&&idName=="provience"){
	        		selectId = "city";
	        	}
	        	console.log("selectId="+selectId);
	         	//给select选择器赋值
         		if(datas != null){
         			resetSelect(selectId);
         			for(var i=0; i<datas.length; i++){
         				$("#"+selectId).append("<option value='"+datas[i].key+"'>"+datas[i].value+"</option>");
         		   	} 
         		}
	          }
	      });
	}
	/*
	  Bug #1599
	  2017年1月5日17:32:38
	  Spole
	  	不选择证件类型，不能填写证件号码
	*/
	function setCorporateCertNum(){
		$("#corporateCertNum").val("");
		$("#corporateCertNum").attr("disabled","disabled");
		var temp = $("#corporateCertType").val();
		if(null != temp && ""!= $.trim(temp)){
			$("#corporateCertNum").attr("disabled","");
		}
	}
	
	function auditStatusChange(){
		$("#auditReason").val("");
		$("#auditReason").attr("disabled","disabled");
		$("#auditReasonEm").hide();
		if($("#status").val()=="03"){
			$("#auditReason").attr("disabled","");
			$("#auditReasonEm").show();
			return;
		}
	}
	function resetSelect(selectId){
		$("#"+selectId).empty();
		$("#"+selectId).append("<option value=''>${app:i18n('global.jsp.defaultSelect') }</option>");
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
			if(isNullOrBlank("localName")){//必输项校验
				return "企业名称  不能为空";
			}
			if(isNullOrBlank("custType")){//必输项校验
				return "会员类型  不能为空";
			}
			if(isNullOrBlank("unitType")){//必输项校验
				return "行业类型  不能为空";
			}
			if(isNullOrBlank("corporateName")){//必输项校验
				return "法人姓名  不能为空";
			}
			if(isNullOrBlank("corporateCertType")){//必输项校验
				return "法人证件类型  不能为空";
			}
			if(isNullOrBlank("corporateCertNum")){//必输项校验
				return "法人证件号码  不能为空";
			}
			if(isNullOrBlank("corporatePhonenum")){//必输项校验
				return "法人联系方式  不能为空";
			}
			//4.  如果“社会信用代码”已填，则“税务登记证、组织机构代码证、营业执照证件号码”可以为空；否则“税务登记证、组织机构代码证、营业执照证件号码”为必填项；
			if(		isNullOrBlank("socialCreditId")
				&&(	isNullOrBlank("taxId")
				  ||isNullOrBlank("companyCode")
				  ||isNullOrBlank("businessLicense")
						)	
			){
				return "三证和社会信用代码 不能同时为空！";
			}
			if(isNullOrBlank("businessLicenseExpiry")){//必输项校验
				return "营业执照有效期  不能为空";
			}
			if(isNullOrBlank("regTime")){//必输项校验
				return "注册日期  不能为空";
			}
			
			if(isNullOrBlank("companyRegAddr")){//必输项校验
				return "注册地址  不能为空";
			}
			if(isNullOrBlank("operateScope")){//必输项校验
				return "经营范围  不能为空";
			}
			if(isNullOrBlank("registeredFund")){//必输项校验
				return "注册资金  不能为空";
			}
			if(isNullOrBlank("registeredCurrency")){//必输项校验
				return "注册币种  不能为空";
			}
			if(isNullOrBlank("country")){//必输项校验
				return "所属国家  不能为空";
			}
			if(isNullOrBlank("provience")){//必输项校验
				return "所属省  不能为空";
			}
			if(isNullOrBlank("city")){//必输项校验
				return "所属市  不能为空";
			}
			if(isNullOrBlank("region")){//必输项校验
				return "行政区代码  不能为空";
			}
			if(isNullOrBlank("contact")){//必输项校验
				return "企业联系人  不能为空";
			}
			if(isNullOrBlank("contactPhone")){//必输项校验
				return "企业联系人电话  不能为空";
			}
			if(isNullOrBlank("contactEmail")){//必输项校验
				return "企业联系人邮箱   不能为空";
			}
			if($("#corporateCertType").val()== CORE_CERT_TYPE_01 && !isIDCard("corporateCertNum")){
				return "身份证号码 不合法";
			}
			if(!isContact("corporatePhonenum")){
				return "法人联系方式  非法";
			}
			if(!isAmount("registeredFund")){
				return "注册资金  非法";
			}
			if(!isContact("contactPhone")){
				return "企业联系人电话  非法";
			}
			if(!isEmail("contactEmail")){//必输项校验
				return "企业联系人邮箱   非法";
			}
			
// 			if($().val()==&&!isPostcode("postcode")){
// 				return "企业邮编 非法";
// 			}
// 			if(!isNullOrBlank("email")&&!isEmail("email")){
// 				return "企业邮箱 非法";
// 			}
// 			if(!isNullOrBlank("companyWebsite")&&!isPostcode("companyWebsite")){
// 				return "企业网址 非法";
// 			}
			var oldfile = '${custCompanyDto.voucher}';
			var file = $("#file").val();
			
			if((null==oldfile||oldfile=="")&&(null==file||file=="")){
				return "凭证 不能为空";
			}else if(null!=file&&file!=""){
				var filePic = file.toLowerCase();
				if(filePic.length > 0 && !/.(pdf|zip|rar)$/.test(filePic)){
					return "凭证类型必须是PDF,zip,rar中的一种";
		        }
			}
			if(0==operaType){  //新增时校验 重复性 和 黑名单
				if(existsInCustInfo()){
					return "该会员信息已存在，无法新建成功";
				}
				if(existsInBlackList()){
					return "该企业在黑名单范围内，无法新建成功";
				}
				
			}
		}else if(2==operaType){	//审核逻辑校验
			if(isNullOrBlank("status")){//必输项校验
				return "审核状态   不能为空";
			}
			if($.trim($("#status").val())=='03'&&isNullOrBlank("auditReason")){
				return "审核失败原因  不能为空";
			}
		}
		return "";
	}
	/*
		需要用企业名称或者社会信用代码或者营业执照编码校验是否存在重复会员，
			如存在提示“该会员信息已存在，无法新建成功”
	*/
	function  existsInCustInfo(){
		var result = false; 
		 $.ajax({
	          type: "post",
	          url: "existsInCustInfo.action",
	          data: $('#companyEditForm').serialize(),
	          dataType: "json",
	          async:false,
	          success: function(data){
	        	var datas=data.status;
	        	if(CORE_AJAX_RESULT_Y == datas){
	        		result =  true;
	        	}
	          }
	      });
		 return result;
	}
	/*
		根据企业名称或者社会信用代码或者营业执照编码校验此会员是否在黑名单列表里，
		如果存在，提示“该企业在黑名单范围内，无法新建成功”,	同时插入一条黑名单数据到会员黑名单管理表中。
	*/
	function  existsInBlackList(){
		var result = false; 
		 $.ajax({
	          type: "post",
	          url: "existsInBlackList.action",
	          data: $('#companyEditForm').serialize(),
	          dataType: "json",
	          async:false,
	          success: function(data){
	        	var datas=data.status;
	        	if(CORE_AJAX_RESULT_Y == datas){
	        		result =  true;
	        	}
	          }
	      });
		 return result;
	}
	   
	$().ready(function() {
		
		initEditPage();
		
		$("#undoBt").click(function(){
			$(dlgUserId).dialog('close');
		});
		
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
					$("#companyEditForm").submit(); 
				}, 
				function(){
					return false;
				});
			return false;
		});
		
// 		// 初始化所有只读字段的样式
		$("* [readonly]").addClass("read_only");
		$("input[readonly]").input().disableBackSpace();
	});
</script>

<s:form id="companyEditForm" method="post" action="custCompanySave.action?loadPageCache=true" namespace="/custCompany"  enctype="multipart/form-data">
<div class="layout">
	<div class="block m-b">
		<div class="block_container">
			<div class="fieldset_border fieldset_bg m-b">
				<div class="fieldset_container">
					<div class="warning" id="warningInfo" style="display:none;">
						<span></span>
					</div>
					<input type="hidden" id="operaType" name="operaType"  value="${operaType}" />
					<input type="hidden" id="id"  name="id"  value="${custCompanyDto.id}" />
					<table cellpadding="0" cellspacing="0" class="table_form" id="tableInfo">
						<thead>
						</thead>
						<tfoot>
						</tfoot>
						<tbody>
							<tr class="addNotOprea">
								<th>${app:i18n('vipNumber.custCode') }：</th>
								<td><input name="custCompanyDto.custCode" id="custCode" class="width_c" value="${custCompanyDto.custCode}" maxlength="50"  disabled="disabled" /></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.localName') }：</th>
								<td><input name="custCompanyDto.localName" id="localName" class="width_c" value="${custCompanyDto.localName}" maxlength="50"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.custType') }：</th>
								<td><!-- 01=企业,02=电商平台,03=交易所,04=代理商 -->
									<select name="custCompanyDto.custType" id="custType" style="width:190px" value="${custCompanyDto.custType}">
										<option value="" >${app:i18n('global.jsp.defaultSelect') }</option>
										<s:iterator value="%{custTypeList}" id="custTypeItem">
									        <option value="<s:property value="#custTypeItem.key" />">
									        	<s:property value="#custTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.unitType') }：</th>
								<td>
									<select name="custCompanyDto.unitType" id="unitType" style="width:190px" value="${custCompanyDto.unitType}">
										<option value="">${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{unitTypeList}" id="unitTypeItem">
									        <option value="<s:property value="#unitTypeItem.key" />">
									        	<s:property value="#unitTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.corporateName') }：</th>
								<td><input name="custCompanyDto.corporateName" id="corporateName" class="width_c" value="${custCompanyDto.corporateName }" maxlength="50" /></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.corporateCertType') }：</th>
								<td>
									<select name="custCompanyDto.corporateCertType" id="corporateCertType" style="width:190px" onchange="setCorporateCertNum()">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator value="%{corporateCertTypeList}" id="corporateCertTypeItem">
									        <option value="<s:property value="#corporateCertTypeItem.key" />">
									        	<s:property value="#corporateCertTypeItem.value" />
									        </option>
										</s:iterator>
							        </select>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.corporateCertNum') }：</th>
								<td><input name="custCompanyDto.corporateCertNum" id="corporateCertNum" class="width_c" value="${custCompanyDto.corporateCertNum}" maxlength="50" disabled="disabled"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.corporatePhonenum') }：</th>
								<td><input name="custCompanyDto.corporatePhonenum" id="corporatePhonenum" class="width_c" value="${custCompanyDto.corporatePhonenum}" maxlength="50" /></td>
							</tr>
							<!--社会信用代码 -->
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.socialCreditId') }：</th>
								<td><input name="custCompanyDto.socialCreditId" id="socialCreditId" class="width_c" value="${custCompanyDto.socialCreditId }" maxlength="50" /></td>
							</tr>
							<!--税务登记证 -->
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.taxId') }：</th>
								<td><input name="custCompanyDto.taxId" id="taxId" class="width_c" value="${custCompanyDto.taxId}" maxlength="50" /></td>
							</tr>
							<!--组织机构代码证 -->
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.companyCode') }：</th>
								<td><input name="custCompanyDto.companyCode" id="companyCode" class="width_c" value="${custCompanyDto.companyCode}" maxlength="50" /></td>
							</tr>
							<!-- 企业营业执照号码 -->
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.businessLicense') }：</th>
								<td><input name="custCompanyDto.businessLicense" id="businessLicense" class="width_c" value="${custCompanyDto.businessLicense }" maxlength="50" /></td>
							</tr>
							<!-- 营业执照有效期截止日-->
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.businessLicenseExpiry')}：</th>
								<td><input name="custCompanyDto.businessLicenseExpiry" id="businessLicenseExpiry"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:nowDate})" class="Wdate" style="width:188px"/></td>
							</tr>
							<!-- 注册日期-->
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.regTime')}：</th>
								<td><input name="custCompanyDto.regTime" id="regTime"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:nowDate})" class="Wdate" style="width:188px"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.companyRegAddr')}：</th>
								<td><input name="custCompanyDto.companyRegAddr" id="companyRegAddr" class="width_c" value="${custCompanyDto.companyRegAddr }" maxlength="50" /></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.operateScope')}：</th>
								<td><input name="custCompanyDto.operateScope" id="operateScope" class="width_c" value="${custCompanyDto.operateScope }" maxlength="50" /></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.registeredFund')}：</th>
								<td><input name="custCompanyDto.registeredFund" id="registeredFund" value="${custCompanyDto.registeredFund }" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.registeredCurrency')}：</th>
								<td>
									<select name="custCompanyDto.registeredCurrency" id="registeredCurrency" style="width:190px">
										<option value="50">人民币</option>
									</select>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.country') }：</th>
								<td>
									<select id="country" style="width:190px" name="custCompanyDto.country" value="${custCompanyDto.country}" onchange="selectChange(this)">
										<option value="" selected>${app:i18n('global.jsp.defaultSelect')}</option>
										<s:iterator  value="countryList"   var ="country">
											<option value="${country.key}">${country.value}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.provience') }：</th>
								<td>
									<select id="provience" style="width:190px;"  name="custCompanyDto.provience"   value="${custCompanyDto.provience}" onchange="selectChange(this)" >
										<option value="" >${app:i18n('global.jsp.defaultSelect') }</option>	
									</select>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.city') }：</th>
								<td>
									<select id="city" style="width:190px" name="custCompanyDto.city" value="${custCompanyDto.city}"  onchange="selectChange(this)">
										<option value="" >${app:i18n('global.jsp.defaultSelect') }</option>
									</select>
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.region') }：</th>
								<td><input name="custCompanyDto.region" id="region" value="${custCompanyDto.region}" class="width_c"  maxlength="50" /></td>
							</tr>
							
							<tr class="editOpera">
								<th><em>*</em>${app:i18n('vipNumber.contact') }：</th>
								<td><input name="custCompanyDto.contact" id="contact" class="width_c" value="${custCompanyDto.contact }" maxlength="50" /></td>
							</tr>
							<tr class="editOpera">
								<th><em>*</em>${app:i18n('vipNumber.contactPhone') }：</th>
								<td><input name="custCompanyDto.contactPhone" id="contactPhone" class="width_c" value="${custCompanyDto.contactPhone }" maxlength="50" /></td>
							</tr>
							<tr class="editOpera">
								<th><em>*</em>${app:i18n('vipNumber.contactEmail') }：</th>
								<td><input name="custCompanyDto.contactEmail" id="contactEmail" class="width_c" value="${custCompanyDto.contactEmail }" maxlength="50" /></td>
							<tr class="editOpera">
							<tr class="editOpera">
								<th>${app:i18n('vipNumber.companyFax') }：</th>
								<td><input name="custCompanyDto.companyFax" id="companyFax" class="width_c" value="${custCompanyDto.companyFax }" maxlength="50" /></td>
							</tr>
							<tr class="editOpera">
								<th>${app:i18n('vipNumber.postcode') }：</th>
								<td><input name="custCompanyDto.postcode" id="postcode" class="width_c" value="${custCompanyDto.postcode}" maxlength="50" /></td>
							</tr>
							<tr class="editOpera">
								<th>${app:i18n('vipNumber.email') }：</th>
								<td><input name="custCompanyDto.email" id="email" class="width_c" value="${custCompanyDto.email}" maxlength="50" /></td>
							</tr>
							<tr class="editOpera">
								<th>${app:i18n('vipNumber.companyWebsite') }：</th>
								<td><input name="custCompanyDto.companyWebsite" id="companyWebsite" class="width_c" value="${custCompanyDto.companyWebsite }" maxlength="50" /></td>
							</tr>
							<!--上传凭证 -->
							<tr>
								<th><em>*</em>${app:i18n('vipNumber.voucherUpload') }：</th>
								<td>
									<s:if test="custCompanyDto.voucher != null">
										<a href="<%=request.getContextPath()%>/downloadFile.action?fileId=${custCompanyDto.voucher}" target="_blank">${fileFileName}</a>
									</s:if>
									<!-- //operaType  : 0 -新增，1-查看，2-审核，3-修改 -->
									<s:if test="operaType != 1 and operaType != 2">
										<input id="file" type="file"  name="file" />
									</s:if>
								</td>
							</tr>
							<tr class="handleOpera">
								<th>${app:i18n('vipNumber.createTime') }：</th>
								<td><input name="custCompanyDto.createTime" id="createTime" value="${custCompanyDto.createTime}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr class="handleOpera">
								<th>${app:i18n('vipNumber.creator') }：</th>
								<td><input name="custCompanyDto.creator" id="creator" value="${custCompanyDto.creator}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr class="handleOpera">
								<th>${app:i18n('vipNumber.updateTime') }：</th>
								<td><input name="custCompanyDto.updateTime" id="updateTime" value="${custCompanyDto.updateTime}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr class="handleOpera">
								<th>${app:i18n('vipNumber.updator') }：</th>
								<td><input name="custCompanyDto.updator" id="updator" value="${custCompanyDto.updator}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr class="addNotOprea">
								<th>${app:i18n('vipNumber.custStatus') }：</th>
								<td>
								<input  id="custStatusValue" class="width_c" value="" maxlength="50"  disabled="disabled"/>
								<input hidden="hidden" name="custCompanyDto.custStatus" id="custStatus" class="width_c" value="${custCompanyDto.custStatus}" maxlength="50"  />
								
								</td>
							</tr>
							<tr class="auditOprea">
								<th><em>*</em>${app:i18n('vipNumber.status') }：</th>
								<td>
									<select name="custCompanyDto.status" id="status" style="width:190px" value="${custCompanyDto.status}" onchange="auditStatusChange()">
										<option value="" >${app:i18n('global.jsp.defaultSelect') }</option>
										<s:iterator value="%{statusOperaList}" id="statusOpera">
									        <option value="<s:property value="#statusOpera.key" />">
									        	<s:property value="#statusOpera.value" />
									        </option>
										</s:iterator>
							        </select>
							        
							        <input  id="statusValue" class="width_c" value="" maxlength="50" style="display: none"/>
								</td>
							</tr>
							<tr class="auditOprea">
								<th><em id="auditReasonEm" style="display:none;">*</em>${app:i18n('vipNumber.auditReason') }：</th>
								<td><input name="custCompanyDto.auditReason" id="auditReason" value="${custCompanyDto.auditReason}" class="width_c"  maxlength="50" /></td>
							</tr>
							
							<tr class="viewOprea">
								<th>${app:i18n('vipNumber.auditPerson') }：</th>
								<td><input name="custCompanyDto.auditPerson" id="auditPerson" value="${custCompanyDto.auditPerson}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr class="viewOprea">
								<th>${app:i18n('vipNumber.auditTime') }：</th>
								<td><input name="custCompanyDto.auditTime" id="auditTime" value="${custCompanyDto.auditTime}" class="width_c"  maxlength="50" /></td>
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
