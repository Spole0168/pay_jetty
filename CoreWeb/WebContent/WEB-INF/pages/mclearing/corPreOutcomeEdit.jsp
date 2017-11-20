<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {
	$("#corPreOutcomeEditForm  input").attr("disabled","disabled");
	$("#corPreOutcomeEditForm  select").attr("disabled","disabled");
	$("#undo").click(function(){
		$("#dialog-ajax-pre-view").dialog('close');
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

</script>

<s:form id="corPreOutcomeEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/corPreOutcome">
<s:hidden name="isModify"/>
<s:hidden name="corPreOutcome.id" id="corPreOutcomeId" />
<div class="layout">
	<div class="block m-b">
		<div class="block_container">
			<div class="fieldset_border fieldset_bg m-b">
				<div class="fieldset_container">
					<form id="validate_form">
						<div class="warning" style="display:none;">
							<span></span>
						</div>
						<input hidden="true" name="corPreOutcome.id" id="id" class="width_c" value="${corPreOutcome.id}" maxlength="225"/>
						<table cellpadding="0" cellspacing="0" class="table_form">
							<thead>
							</thead>
							<tfoot>
							</tfoot>
							<tbody>
						
							<tr>
								<th>${app:i18n('corPreOutcome.merchantCode') }：</th>
								<td><input name="corPreOutcome.merchantCode" id="merchantCode" class="width_c" value="${corPreOutcome.merchantCode}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('corPreOutcome.settPeriod') }：</th>
								<td><input name="corPreOutcome.settPeriod" id="settPeriod" class="width_c" value="${corPreOutcome.settPeriod}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('corPreOutcome.currency') }：</th>
								<td>
									<select id ="currency" name="corPreOutcome.currency" style="width: 188px" >
										<s:iterator  value="currencyList" var="s">
											<c:if test="${s.key == corPreOutcome.currency }">
												<option value="${s.key}">${s.value}</option>
											</c:if>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr><c:if test=""></c:if>
								<th>${app:i18n('corPreOutcome.transDate') }：</th>
								<td><input name="corPreOutcome.transDate" id="transDate" class="width_c" value="${corPreOutcome.transDate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('corPreOutcome.transAmount') }：</th>
								<td><input name="corPreOutcome.transAmount" id="transAmount" class="width_c" value="${corPreOutcome.transAmount}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('corPreOutcome.refundAmount') }：</th>
								<td><input name="corPreOutcome.refundAmount" id="refundAmount" class="width_c" value="${corPreOutcome.refundAmount}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('corPreOutcome.serviceCharge') }：</th>
								<td><input name="corPreOutcome.serviceCharge" id="serviceCharge" class="width_c" value="${corPreOutcome.serviceCharge}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('corPreOutcome.preOutcomeAmount') }：</th>
								<td><input name="corPreOutcome.preOutcomeAmount" id="preOutcomeAmount" class="width_c" value="${corPreOutcome.preOutcomeAmount}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('corPreOutcome.preOutcomeDate') }：</th>
								<td><input name="corPreOutcome.preOutcomeDate" id="preOutcomeDate" class="width_c" value="${corPreOutcome.preOutcomeDate}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('corPreOutcome.preOutcomeBankCode') }：</th>
								<td>
									<select id ="preOutcomeBankCode" name="corPreOutcome.preOutcomeBankCode" style="width: 188px" >
										<s:iterator  value="preOutcomeBankCodeList" var="s">
											<c:if test="${s.key == corPreOutcome.preOutcomeBankCode }">
												<option value="${s.key}">${s.value}</option>
											</c:if>
										</s:iterator>
									</select>
								</td>
							</tr>

							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div id="tabs-1">
				<a id="undo" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.button')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
