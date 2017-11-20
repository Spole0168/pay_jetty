package com.ibs.core.module.customer.dto;

import java.util.Date;
import java.util.List;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class CardBindDto extends BaseEntity {

	private static final long serialVersionUID = 7067812546094562159L;

	private String id; // ID
	private String bindOrderCode; // BIND_ORDER_CODE
	private String custCode; // CUST_CODE
	private String bankCode; // BANK_CODE
	private String bankCardNum; // BANK_CARD_NUM
	private String certType; // CERT_TYPE
	private String certNum; // CERT_NUM
	private String cardholderName; // CARDHOLDER_NAME
	private String bankCardType; // BANK_CARD_TYPE
	private String bankPhoneNum; // BANK_PHONE_NUM
	private String expiryDate; // EXPIRY_DATE
	private String cvv2Code; // CVV2_CODE
	private Date bindTime; // BIND_TIME
	private Date unbindTime; // UNBIND_TIME
	private String secondLevelProduct; // SECOND_LEVEL_PRODUCT
	private String status; // STATUS
	private String creator; // CREATOR
	private Date createTime; // CREATE_TIME
	private String updator; // UPDATOR
	private Date updateTime; // UPDATE_TIME
	private String isValid; // IS_VALID

	// private String id; // ID
	// private String custCode; // CUST_CODE
	private String merchantCode; // MERCHANT_CODE
	private String mertCustCode; // MERT_CUST_CODE
	// private String bankCardNum; // BANK_CARD_NUM
	// private String status; // STATUS
	// private String creator; // CREATOR
	// private Date createTime; // CREATE_TIME
	// private String updator; // UPDATOR
	// private Date updateTime; // UPDATE_TIME
	// private String isValid; // IS_VALID

	private String token;
	
	private List<String> personalCustCodeList;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBindOrderCode() {
		return bindOrderCode;
	}

    public List<String> getPersonalCustCodeList() {
        return personalCustCodeList;
    }

    public void setPersonalCustCodeList(List<String> personalCustCodeList) {
        this.personalCustCodeList = personalCustCodeList;
    }

    public void setBindOrderCode(String bindOrderCode) {
		this.bindOrderCode = bindOrderCode;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankCardNum() {
		return bankCardNum;
	}

	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNum() {
		return certNum;
	}

	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public String getBankCardType() {
		return bankCardType;
	}

	public void setBankCardType(String bankCardType) {
		this.bankCardType = bankCardType;
	}

	public String getBankPhoneNum() {
		return bankPhoneNum;
	}

	public void setBankPhoneNum(String bankPhoneNum) {
		this.bankPhoneNum = bankPhoneNum;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCvv2Code() {
		return cvv2Code;
	}

	public void setCvv2Code(String cvv2Code) {
		this.cvv2Code = cvv2Code;
	}

	public Date getBindTime() {
		return bindTime;
	}

	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}

	public Date getUnbindTime() {
		return unbindTime;
	}

	public void setUnbindTime(Date unbindTime) {
		this.unbindTime = unbindTime;
	}

	public String getSecondLevelProduct() {
		return secondLevelProduct;
	}

	public void setSecondLevelProduct(String secondLevelProduct) {
		this.secondLevelProduct = secondLevelProduct;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getMertCustCode() {
		return mertCustCode;
	}

	public void setMertCustCode(String mertCustCode) {
		this.mertCustCode = mertCustCode;
	}

    @Override
    public String toString() {
        return "CardBindDto [id=" + id + ", bindOrderCode=" + bindOrderCode + ", custCode=" + custCode + ", bankCode="
                + bankCode + ", bankCardNum=" + bankCardNum + ", certType=" + certType + ", certNum=" + certNum
                + ", cardholderName=" + cardholderName + ", bankCardType=" + bankCardType + ", bankPhoneNum="
                + bankPhoneNum + ", expiryDate=" + expiryDate + ", cvv2Code=" + cvv2Code + ", bindTime=" + bindTime
                + ", unbindTime=" + unbindTime + ", secondLevelProduct=" + secondLevelProduct + ", status=" + status
                + ", creator=" + creator + ", createTime=" + createTime + ", updator=" + updator + ", updateTime="
                + updateTime + ", isValid=" + isValid + ", merchantCode=" + merchantCode + ", mertCustCode="
                + mertCustCode + ", token=" + token + ", personalCustCodeList=" + personalCustCodeList + "]";
    }
	
	

}
