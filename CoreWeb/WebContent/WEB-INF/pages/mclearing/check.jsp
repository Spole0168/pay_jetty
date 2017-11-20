<%@ page language="java" pageEncoding="UTF-8" 
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	$().ready(function() {
		$("#undo").click(function(){
			$("#check_status").dialog('close');
		});
		
		$("#auditSave").click(function(){
			clearWarningInner();
			if(!checkRequired()){
				return;
			}
			//showWarningInner("功能还在开发中...");


				$.boxUtil.confirm(
						'确认提交审核吗？', 
						null, 
						function(){
							//执行操作
							$.ajax({
				                url : 'submitCheck.action',
				                type : 'post',
				                dataType : 'json',
				                data : {
				                	id:"${id}",
				                	statementCode:"${statementCode}",
				                	auditStatusAudit:$("#auditStatusAudit").val(),
				                	failedReason:$("#failedReason").val(),
				                }, 
				                success : function(data) {
				                	$("#check_status").dialog('close');
				                	//alert(data.result);
				                },
				                error : function(data) {
				                }

				          });    
				
			}, function() {
				$("#check_status").dialog('close');
			});
		})
	});
	
	

	function checkOnblur() {
		if ($("#auditStatusAudit").val() != "02") {
			$("#reason").css('display', '');
		} else {
			$("#reason").css('display', 'none');
		}
	}
	function checkRequired() {
		if ($("#auditStatusAudit").val() == null
				|| $("#auditStatusAudit").val().trim() == "") {
			showWarningInner("审核状态不能为空");
			return false;
		}
		if ($("#auditStatusAudit").val() != "02"
				&& ($("#failedReason").val() == null || $("#failedReason")
						.val().trim() == "")) {
			showWarningInner("审核不通过时审核失败原因不能为空");
			return false;
		}

		return true;
	}

	//清空提示信息
	function clearWarningInner() {
		$("#warning span").html("");
		$("#warning").hide();
	}
	//提示信息
	function showWarningInner(msg) {
		$("#warning span").html(msg);
		$("#warning").show();
	}
</script>

<s:form id="formSubmit" action="submitCheck.action" method="post">
<div class="layout">
	<div class="block m-b">
		<div class="block_container">
			<div id="warning" class="warning" style="display:none;">
				<span></span>
			</div>
			<div class="fieldset_border fieldset_bg m-b">
				<div class="fieldset_container">
					<form id="validate_form">
						<input hidden="hidden" name="corOutcomeOrderHandleAuditDto.outcomeOrderCode" value="${corOutcomeOrderHandleAuditDto.outcomeOrderCode }" />
						<table cellpadding="0" cellspacing="0" style="align:center" class="table_form">
							<tr>
								<th width="20%">审核状态：</th>
								<td width="30%">
								<select id="auditStatusAudit" name="auditStatus" onchange="checkOnblur()" style="width:188px">
									<option value="">请选择...</option>
									
											<option value="02">审核通过</option> 
									
											<option value="03">审核失败</option> 
									
									
								</select>
<!-- 									<option value="">请选择...</option> -->
<%-- 									<c:forEach items="${auditStatusList}" var="list"> --%>
<%-- 											<option value="${list.key}">${list.value}</option>  --%>
<%-- 									</c:forEach> --%>
								</select>
							</tr>
							
							<tr id="reason" style="display: none;align:left">
								<th width="20%">审核失败原因：</th>
									<td width="30%">
									<textarea rows="5" cols="18" id="failedReason"></textarea>
									</td>
								</tr>
						
						</table>
					</form>
				</div>
			</div>
			<div id="tabs-1" style="text-align: center;">
				<a id="auditSave" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.save')}</span></span></a>
				<a id="undo" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
