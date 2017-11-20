<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">


function initPage(){

}

 $().ready(function() {
	
	initPage();
		
	$("#save").click(function(){
		clearWarning();
		
		$.boxUtil.confirm(
			'请确认是否发送？', 
			null, 
			function(){
				doSave();
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


});	

//清空提示信息
function clearWarning() {
	$("div.warning span").html("");
	$("div.warning").hide();
}
function doSave(){
	
		$("#merchatEditForm").submit(); 
	
}

</script>

<div id="alertDialog"></div>
<s:form id="merchatEditForm" method="post"
	action="sendUserPwdEmail.action?loadPageCache=true"
	namespace="/corMertUser" enctype="multipart/form-data">
	<input type="hidden" name="id" id="userId" value="${id}" />
	<div class="layout">
		<div class="block m-b">
			<div class="block_container">
				<div class="fieldset_border fieldset_bg m-b">
					<div class="fieldset_container">
						<form id="validate_form">
							<div class="warning" id="warning" style="display: none;">
								<span></span>
							</div>
							
							<table cellpadding="0" cellspacing="0" class="table_form">
								<thead>
								</thead>
								<tfoot>
								</tfoot>
								<tbody>
									<tr >
										<th>${app:i18n('merchant.cust_code') }：</th>
										<td><input name="corMertUser.custCode" id="custCode"
											class="width_c" value="${corMertUser.custCode}" maxlength="50" readonly="readonly"/></td>
									</tr>
									<tr >
										<th>${app:i18n('mertUser.merchantCode') }：</th>
										<td><input name="corMertUser.merchantCode" id="custCode"
											class="width_c" value="${merchantDto.merchantCode}" maxlength="50" readonly="readonly"/></td>
									</tr>
									<tr>
										<th>${app:i18n('mertUser.userCode') }：</th>
										<td><input name="corMertUser.userCode" id="userCode"
											class="width_c" value="${corMertUser.userCode}" maxlength="225" readonly="readonly"/></td>
									</tr>
									<tr>
										<th>${app:i18n('mertUser.email') }：</th>
										<td><input name="corMertUser.email" id="email"
											class="width_c" value="${corMertUser.email}" maxlength="225" readonly="readonly"/></td>
									</tr>
									
								</tbody>
							</table>
						</form>
					</div>
				</div>
				<div id="tabs-1">
					<a id="save" class="easyui-linkbutton l-btn" href="#"><span
						class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('mertUser.emailResetPassword')}</span></span></a>
					<a id="undo" class="easyui-linkbutton l-btn" href="#"><span
						class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
				</div>
			</div>
		</div>
	</div>
</s:form>
