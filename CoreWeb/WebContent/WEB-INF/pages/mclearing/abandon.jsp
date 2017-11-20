<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<s:form method="post" action="abandon.action" id="abandon"
	namespace="/corMertStatement">

	<div class="layout">
		<div class="block m-b">
			<div class="block_container">
				<div class="fieldset_border fieldset_bg m-b">
					<div class="fieldset_container">
						<div class="warning" style="display: none;">
							<span></span>
						</div>
						<table cellpadding="0" cellspacing="0" class="table_form">
							<input type="hidden" name="id" value="${id}">
							<tr>
								<th width="13%">${app:i18n('mClearing.cancelReson')}：</th>
								<td width="30%"><select style="width: 188px" id="reason"
									name="reason">
										<option  selected="selected" >请选择</option>
								</select>
							</tr>
							<tr>
								<th width="13%">${app:i18n('mClearing.remark')}：</th>
								<td width="30%"><textarea id="remark" name="remark"
										cssClass="width_c" /></td>
							</tr>
						</table>
					</div>
				</div>
				<div id="tabs-1" style="text-align: center;">
					<a id="save" class="easyui-linkbutton l-btn" href="#"><span
						class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.save')}</span></span></a>
					<a id="cancel" class="easyui-linkbutton l-btn" href="#"><span
						class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.cancel')}</span></span></a>
				</div>
			</div>
		</div>
	</div>
</s:form>

<script type="text/javascript">

$().ready(function() {
		//初始化
		var anObject = ${json};
		$.each(anObject,function(name,value) {
		$("#reason").append(
				"<option value='" + name + "'>"
						+value + "</option>");
		});
		
		
		$("#save").click(function() {
			var reason = $("#reason").val();
			var remark = $("#remark").val().trim();

			if (null == reason || "" == reason) {
				showWarning("请选择异常原因");
				return;
			}
			if (remark == null || remark.trim() == "") {
				showWarning("请添加一个备注");
				return;
			}

			$.boxUtil.confirm('请确认是否保存信息？', null, function() {
				$("#abandon").submit();
				$("#t_detail").dialog('close');
			}, function() {
				$("#t_detail").dialog('close');
			});

		});

		$("#cancel").click(function() {
			$("#t_detail").dialog('close');
		});
		
	});

	
</script>
