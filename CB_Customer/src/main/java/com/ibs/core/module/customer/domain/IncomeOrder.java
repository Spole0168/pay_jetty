/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_INCOME_ORDER
 * @modify		: your comments goes here (author,date,reason).
 */
public class IncomeOrder extends BaseEntity {
	
	private String id; // ID
	private String incomeOrderCode; // INCOME_ORDER_CODE
	private String custCode; // CUST_CODE
	private String merchantCode; // MERCHANT_CODE
	private String orderNum; // ORDER_NUM
	private String operateType; // OPERATE_TYPE
	private String sourceType; // SOURCE_TYPE
	private Date orderTime; // ORDER_TIME
	private String goodsDetail; // GOODS_DETAIL
	private BigDecimal amount; // AMOUNT
	private String currency; // CURRENCY
	private String transRate; // TRANS_RATE
	private String smsCertCode; // SMS_CERT_CODE
	private String status; // STATUS
	private String bankCode; // BANK_CODE
	private String bankName; // BANK_NAME
	private String bankBranchName; // BANK_BRANCH_NAME
	private String bankBranchCode; // BANK_BRANCH_CODE
	private String bankCustName; // BANK_CUST_NAME
	private String bankCardNum; // BANK_CARD_NUM
	private String reserveBankCode; // RESERVE_BANK_CODE
	private String reserveBankName; // RESERVE_BANK_NAME
	private String reserveBankCardNum; // RESERVE_BANK_CARD_NUM
	private String bankTransNum; // BANK_TRANS_NUM
	private Date inAccountTime; // IN_ACCOUNT_TIME
	private String transExplanation; // TRANS_EXPLANATION
	private String voucher; // VOUCHER
	private String interfaceIp; // INTERFACE_IP
	private String notifyUrl; // NOTIFY_URL
	private Date recieveTime; // RECIEVE_TIME
	private Date handleTime; // HANDLE_TIME
	private String creator; // CREATOR
	private Date createTime; // CREATE_TIME
	private String updator; // UPDATOR
	private Date updateTime; // UPDATE_TIME
	private String isValid; // IS_VALID
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getIncomeOrderCode(){
		return incomeOrderCode;
	}
	public void setIncomeOrderCode(String incomeOrderCode){
		this.incomeOrderCode = incomeOrderCode;
	}

	public String getCustCode(){
		return custCode;
	}
	public void setCustCode(String custCode){
		this.custCode = custCode;
	}

	public String getMerchantCode(){
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode){
		this.merchantCode = merchantCode;
	}

	public String getOrderNum(){
		return orderNum;
	}
	public void setOrderNum(String orderNum){
		this.orderNum = orderNum;
	}

	public String getOperateType(){
		return operateType;
	}
	public void setOperateType(String operateType){
		this.operateType = operateType;
	}

	public String getSourceType(){
		return sourceType;
	}
	public void setSourceType(String sourceType){
		this.sourceType = sourceType;
	}

	public Date getOrderTime(){
		return orderTime;
	}
	public void setOrderTime(Date orderTime){
		this.orderTime = orderTime;
	}

	public String getGoodsDetail(){
		return goodsDetail;
	}
	public void setGoodsDetail(String goodsDetail){
		this.goodsDetail = goodsDetail;
	}

	public BigDecimal getAmount(){
		return amount;
	}
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}

	public String getCurrency(){
		return currency;
	}
	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getTransRate(){
		return transRate;
	}
	public void setTransRate(String transRate){
		this.transRate = transRate;
	}

	public String getSmsCertCode(){
		return smsCertCode;
	}
	public void setSmsCertCode(String smsCertCode){
		this.smsCertCode = smsCertCode;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	public String getBankCode(){
		return bankCode;
	}
	public void setBankCode(String bankCode){
		this.bankCode = bankCode;
	}

	public String getBankName(){
		return bankName;
	}
	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankBranchName(){
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName){
		this.bankBranchName = bankBranchName;
	}

	public String getBankBranchCode(){
		return bankBranchCode;
	}
	public void setBankBranchCode(String bankBranchCode){
		this.bankBranchCode = bankBranchCode;
	}

	public String getBankCustName(){
		return bankCustName;
	}
	public void setBankCustName(String bankCustName){
		this.bankCustName = bankCustName;
	}

	public String getBankCardNum(){
		return bankCardNum;
	}
	public void setBankCardNum(String bankCardNum){
		this.bankCardNum = bankCardNum;
	}

	public String getReserveBankCode(){
		return reserveBankCode;
	}
	public void setReserveBankCode(String reserveBankCode){
		this.reserveBankCode = reserveBankCode;
	}

	public String getReserveBankName(){
		return reserveBankName;
	}
	public void setReserveBankName(String reserveBankName){
		this.reserveBankName = reserveBankName;
	}

	public String getReserveBankCardNum(){
		return reserveBankCardNum;
	}
	public void setReserveBankCardNum(String reserveBankCardNum){
		this.reserveBankCardNum = reserveBankCardNum;
	}

	public String getBankTransNum(){
		return bankTransNum;
	}
	public void setBankTransNum(String bankTransNum){
		this.bankTransNum = bankTransNum;
	}

	public Date getInAccountTime(){
		return inAccountTime;
	}
	public void setInAccountTime(Date inAccountTime){
		this.inAccountTime = inAccountTime;
	}

	public String getTransExplanation(){
		return transExplanation;
	}
	public void setTransExplanation(String transExplanation){
		this.transExplanation = transExplanation;
	}

	public String getVoucher(){
		return voucher;
	}
	public void setVoucher(String voucher){
		this.voucher = voucher;
	}

	public String getInterfaceIp(){
		return interfaceIp;
	}
	public void setInterfaceIp(String interfaceIp){
		this.interfaceIp = interfaceIp;
	}

	public String getNotifyUrl(){
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl){
		this.notifyUrl = notifyUrl;
	}

	public Date getRecieveTime(){
		return recieveTime;
	}
	public void setRecieveTime(Date recieveTime){
		this.recieveTime = recieveTime;
	}

	public Date getHandleTime(){
		return handleTime;
	}
	public void setHandleTime(Date handleTime){
		this.handleTime = handleTime;
	}

	public String getCreator(){
		return creator;
	}
	public void setCreator(String creator){
		this.creator = creator;
	}

	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public String getUpdator(){
		return updator;
	}
	public void setUpdator(String updator){
		this.updator = updator;
	}

	public Date getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	public String getIsValid(){
		return isValid;
	}
	public void setIsValid(String isValid){
		this.isValid = isValid;
	}

	
}
