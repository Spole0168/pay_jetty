<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	var operaType = 1;//operaType  : 0 -新增，1-查看，2-审核，3-修改
	$().ready(function() {
			
			initEditPage();
			$("#undoBt").click(function(){
				$(dlgUserId).dialog('close');
			});
			
			// 		// 初始化所有只读字段的样式
			$("* [readonly]").addClass("read_only");
			$("input[readonly]").input().disableBackSpace();
	});
	function initEditPage(){
		setSelectInfos();
		$("#personalEditForm  input").attr("readonly","readonly");   		//设置input  只读
		$("#personalEditForm  input").attr("disabled","disabled");  		//设置input  只读
	}
	function setSelectInfos(){
		//设置客户状态 数据转义
		$("#custStatus").val(getSelectValue('${custStatusTypeRender}','${custPersonalDto.custStatus}'));
		$("#realNameLeve").val(getSelectValue('${realNameLeveTypeRender}','${custPersonalDto.realNameLeve}'));
		$("#certType").val(getSelectValue('${certTypeRender}','${custPersonalDto.certType}'));
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
</script>

<s:form id="personalEditForm" method="post" action="" namespace=""  enctype="multipart/form-data">
<div class="layout">
	<div class="block m-b">
		<div class="block_container">
			<div class="fieldset_border fieldset_bg m-b">
				<div class="fieldset_container">
					<div class="warning" id="warningInfo" style="display:none;">
						<span></span>
					</div>
					<input type="hidden" id="operaType" name="operaType"  value="${operaType}" />
					<input type="hidden" id="id"  name="id"  value="${custPersonalDto.id}" />
					<table cellpadding="0" cellspacing="0" class="table_form" id="tableInfo">
						<thead>
						</thead>
						<tfoot>
						</tfoot>
						<tbody>
							<tr>
								<th>${app:i18n('custPersonalDto.custCode') }：</th>
								<td><input name="custPersonalDto.custCode" id="custCode" class="width_c" value="${custPersonalDto.custCode}" maxlength="50"  disabled="disabled" /></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.custStatus') }：</th>
								<td><input name="custPersonalDto.custStatus" id="custStatus" class="width_c" value="${custPersonalDto.custStatus}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.localName') }：</th>
								<td><input name="custPersonalDto.localName" id="localName" class="width_c" value="${custPersonalDto.localName}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.country') }：</th>
								<td><input name="custPersonalDto.country" id="country" class="width_c" value="${custPersonalDto.country}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.provience') }：</th>
								<td><input name="custPersonalDto.provience" id="provience" class="width_c" value="${custPersonalDto.provience}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.city') }：</th>
								<td><input name="custPersonalDto.city" id="city" class="width_c" value="${custPersonalDto.city}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.postcode') }：</th>
								<td><input name="custPersonalDto.postcode" id="postcode" class="width_c" value="${custPersonalDto.postcode}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.gender') }：</th>
								<td><input name="custPersonalDto.gender" id="gender" class="width_c" value="${custPersonalDto.gender}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.birthday') }：</th>
								<td><input name="custPersonalDto.birthday" id="birthday" class="width_c" value="${custPersonalDto.birthday}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.realNameLeve') }：</th>
								<td><input name="custPersonalDto.realNameLeve" id="realNameLeve" class="width_c" value="${custPersonalDto.realNameLeve}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.occupation') }：</th>
								<td><input name="custPersonalDto.occupation" id="occupation" class="width_c" value="${custPersonalDto.occupation}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.addr') }：</th>
								<td><input name="custPersonalDto.addr" id="addr" class="width_c" value="${custPersonalDto.addr}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.telephone') }：</th>
								<td><input name="custPersonalDto.telephone" id="telephone" class="width_c" value="${custPersonalDto.telephone}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.email') }：</th>
								<td><input name="custPersonalDto.email" id="email" class="width_c" value="${custPersonalDto.email}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.certType') }：</th>
								<td><input name="custPersonalDto.certType" id="certType" class="width_c" value="${custPersonalDto.certType}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.certNum') }：</th>
								<td><input name="custPersonalDto.certNum" id="certNum" class="width_c" value="${custPersonalDto.certNum}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.mobilePhone') }：</th>
								<td><input name="custPersonalDto.mobilePhone" id="mobilePhone" class="width_c" value="${custPersonalDto.mobilePhone}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.fax') }：</th>
								<td><input name="custPersonalDto.fax" id="fax" class="width_c" value="${custPersonalDto.fax}" maxlength="50"/></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.qq') }：</th>
								<td><input name="custPersonalDto.qq" id="qq" class="width_c" value="${custPersonalDto.qq}" maxlength="50"/></td>
							</tr>
							<tr >
								<th>${app:i18n('custPersonalDto.createTime') }：</th>
								<td><input name="custPersonalDto.createTime" id="createTime" value="${custPersonalDto.createTime}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.creator') }：</th>
								<td><input name="custPersonalDto.creator" id="creator" value="${custPersonalDto.creator}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.updateTime') }：</th>
								<td><input name="custPersonalDto.updateTime" id="updateTime" value="${custPersonalDto.updateTime}" class="width_c"  maxlength="50" /></td>
							</tr>
							<tr>
								<th>${app:i18n('custPersonalDto.updator') }：</th>
								<td><input name="custPersonalDto.updator" id="updator" value="${custPersonalDto.updator}" class="width_c"  maxlength="50" /></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div id="tabs-1">
				<a id="undoBt" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
