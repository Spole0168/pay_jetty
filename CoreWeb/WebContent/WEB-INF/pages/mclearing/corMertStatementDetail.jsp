<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
$().ready(function() {
	//除去时间戳后毫秒时间
	$("[name='time']").each(function(){
		var time=$(this).text();
		time=time.split(".")[0];
		$(this).text(time);
		
	});
	
	$("[name='money']").each(function(){
		var money=$(this).text();
		money=fmoney(money,2);
		$(this).text(money);
		
	});
	
	//金额格式化
	function fmoney(s, n){
		var str = parseInt(s);
		var flag = false;
		if(str < 0){
			flag = true;//负数
			s = -str;
		}
	  // var symbol = s.substring(1,2);
	   n = n > 0 && n <= 20 ? n : 2;
	   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	   var l = s.split(".")[0].split("").reverse(),
	   r = s.split(".")[1];
	   t = "";
	   for(i = 0; i < l.length; i ++ )
	   {
	      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	   }
	   if(true==flag){
		   return "-"+(t.split("").reverse().join("") + "." + r);
	   }
	   return t.split("").reverse().join("") + "." + r;
}
	
	var status = "${status}";
	if(status=='01'){
		$('#cancels').css('display','none');
		$("#reasons").css('display', 'none');
	}
	//ajax定时器 是基于jquery的方法
	setTimeout(function() {
		$().ready(function() {
			//构建表格，并给删除、新增和修改按钮增加操作
			//alert("${statementCode}");
			$("#appList").gridUtil().simpleGrid({
				sortname:'statementCode', // 默认的排序字段
				sortorder:'desc',
				multiselect:true,
				url:"detailList.action?statementCode=${statementCode}",
				colNames:[
							'${app:i18n('mClearing.statementCode')}',
							'${app:i18n('mClearing.paymentType')}',
							'${app:i18n('mClearing.paymentCode')}',
							'${app:i18n('mClearing.paymentConfirmTime')}',
							'${app:i18n('mClearing.sourceOrderNum')}',
							'${app:i18n('mClearing.mertOrderNum')}',
							'${app:i18n('mClearing.orderRecieveDate')}',
							'${app:i18n('mClearing.orderRecieveTime')}',
							'${app:i18n('mClearing.amount')}',
							'${app:i18n('mClearing.currency')}',
							'${app:i18n('mClearing.hcAmount')}',
							'${app:i18n('mClearing.hcCurrency')}',
							'${app:i18n('mClearing.mertReconStatus')}',
							'${app:i18n('mClearing.fundReconStatus')}',
							'${app:i18n('mClearing.complainReason')}',
							'${app:i18n('mClearing.fundTransNum')}',
							'${app:i18n('mClearing.paymentSummary')}',
							'${app:i18n('mClearing.logisicsNum')}',
							//'${app:i18n('mClearing.fundReconStatus')}',
							'${app:i18n('mClearing.creator')}',
							'${app:i18n('mClearing.createTime')}',
							'${app:i18n('mClearing.updator')}',
							'${app:i18n('mClearing.updateTime')}',
							//'${app:i18n('mClearing.isValid')}',
							'${app:i18n('mClearing.isTampered')}'
							],
					colModel:[
								{name : 'statementCode',index : 'statementCode'},
								{name : 'paymentType',index : 'paymentType'},
								{name : 'paymentCode',index : 'paymentCode'},
								{name : 'paymentConfirmTime',index : 'paymentConfirmTime'},
								{name : 'sourceOrderNum',index : 'sourceOrderNum'},
								{name : 'mertOrderNum',index : 'mertOrderNum'},
								{name : 'orderRecieveDate',index : 'orderRecieveDate'},
								{name : 'orderRecieveTime',index : 'orderRecieveTime'},
								{name : 'amount',index : 'amount',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
								{name : 'currency',index : 'currency',formatter:'select',editoptions:{value: '${currencyListRender}'}},
								{name : 'hcAmount',index : 'hcAmount',formatter:'number', formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2}},
								{name : 'hcCurrency',index : 'hcCurrency',formatter:'select',editoptions:{value: '${currencyListRender}'}},
								{name : 'mertReconStatus',index : 'mertReconStatus'},
								{name : 'fundReconStatus',index : 'fundReconStatus'},
								{name : 'complainReason',index : 'complainReason'},
								{name : 'fundTransNum',index : 'fundTransNum'},
								{name : 'paymentSummary',index : 'paymentSummary'},
								{name : 'logisicsNum',index : 'logisicsNum'},
								//{name : 'fundReconStatus',index : 'fundReconStatus'},
								{name : 'creator',index : 'creator'},
								{name : 'createTime',index : 'createTime'},
								{name : 'updator',index : 'updator'},
								{name : 'updateTime',index : 'updateTime'},
								//{name : 'isValid',index : 'isValid'},
								{name : 'isTampered',index : 'isTampered',formatter:'select',editoptions:{value: '${isTamperedListRender}'}}
					],
					caption:"商户结算单明细列表",
					shrinkToFit:false,  //水平滚动条
					autoScroll: true, 
					multiselect: false //勾选框
			});
		
		});
	     
	       
	}, 0);//65s后查询
});

	
</script>



<style>
	#custpersonalinfo{
		font-family:隶书;
	}
	#custpersonalinfo td{
		border-bottom:1px dashed #85F2F2;
		color:#141D28;
		text-align:left;
	}
	#custpersonalinfo th{
		border-bottom:1px dashed #85F2F2;
		color:#141D28;
		text-align:right;
	}
	</style>
<form id="appForm" action="" method="post">
<div class="layout">
<div class="block m-b">
	<!-- <div class="block_title">
	   <h3>商户结算单明细</h3>
	</div> -->
	<div class="block_container">
	<div id="app_search_table" class="fieldset_border fieldset_bg m-b">
<!-- 	<div class="legend_title"><a href="#" class="container_show">应用查询</a></div> -->
	<div class="fieldset_container">

						<table width="100%" border="0" cellspacing="2" cellpadding="2">
							<input type="hidden" name="statementCodes"
								value="${statementCode}">
							<tbody id="custpersonalinfo">
								<tr>
									<th width="25%">${app:i18n('mClearing.custCode') }：</th>
									<td>${corMertStatement.custCode}</td>
									<th width="25%">${app:i18n('mClearing.merchantCode') }：</th>
									<td>${corMertStatement.merchantCode}</td>
								</tr>
								<tr>
									<th width="25%">${app:i18n('mClearing.statementCode')}：</th>
									<td>${corMertStatement.statementCode}</td>
									<th width="25%">${app:i18n('mClearing.transAmount') }：</th>
									<td name="money">${corMertStatement.transAmount}</td>
								</tr>
								<tr>
									<th width="25%">${app:i18n('mClearing.transExpAmout') }：</th>
									<td name="money">${corMertStatement.transExpAmout}</td>
									<th width="25%">${app:i18n('mClearing.netAmount') }：</th>
									<td name="money">${corMertStatement.netAmount}</td>
								</tr>
								<tr>
									<th width="25%">${app:i18n('mClearing.incomeTotalAmount') }：</th>
									<td name="money">${corMertStatement.incomeTotalAmount}</td>
									<th width="25%">${app:i18n('mClearing.incomeTotalCount') }：</th>
									<td >${corMertStatement.incomeTotalCount}</td>
								</tr>
								<tr>
									<th width="25%">${app:i18n('mClearing.refundTotalAmount') }：</th>
									<td name="money">${corMertStatement.refundTotalAmount}</td>
									<th width="25%">${app:i18n('mClearing.refundTotalCount') }：</th>
									<td>${corMertStatement.refundTotalCount}</td>
								</tr>
								<tr>
									<th width="25%">${app:i18n('mClearing.serviceCharge') }：</th>
									<td name="money">${corMertStatement.serviceCharge}</td>
									<th width="25%">${app:i18n('mClearing.depositAmount') }：</th>
									<td name="money">${corMertStatement.depositAmount}</td>
								</tr>
								<tr>
									<th width="25%">${app:i18n('mClearing.isUpload') }：</th>
									<td>${corMertStatement.isUpload}</td>
									<th width="25%">${app:i18n('mClearing.uploadLog') }：</th>
									<td>${corMertStatement.uploadLog}</td>
								</tr>
								<tr>
									<th width="25%">${app:i18n('mClearing.creator') }：</th>
									<td>${corMertStatement.creator}</td>
									<th width="25%" >${app:i18n('mClearing.createTime') }：</th>
									<td id="createTime" name="time">
										${corMertStatement.createTime}
									</td>
								</tr>
								<tr>
									<th width="25%">${app:i18n('mClearing.updator') }：</th>
									<td>${corMertStatement.updator}</td>
									<th width="25%">${app:i18n('mClearing.updateTime') }：</th>
									<td name="time">${corMertStatement.updateTime}</td>
								</tr>
								<tr>
									<th width="25%">${app:i18n('mClearing.frozenAmount') }：</th>
									<td name="money">${corMertStatement.frozenAmount}</td>
									<th width="25%">${app:i18n('mClearing.unFrozenAmount') }：</th>
									<td name="money">${corMertStatement.unFrozenAmount}</td>
								</tr>
								<tr>
									<th width="25%" id="cancels">${app:i18n('mClearing.cancelReson') }：</th>
									<td id="reasons">${corMertStatement.cancelReson}</td>
									<th width="25%">${app:i18n('mClearing.remark') }：</th>
									<td colspan="3">${corMertStatement.remark}</td>
								</tr>

							</tbody>
						</table>

	</div>
	</div>

<div id="tblMasterMessage"></div>
	</div>
	
	</div>
	<!-- 列表展示 -->
					<table id="appList" style="overflow:scroll;" ></table>
				</div>
				<div id="appPager"></div>
<!-- <div id="dialog-ajax-app-edit"></div> -->
</form>
