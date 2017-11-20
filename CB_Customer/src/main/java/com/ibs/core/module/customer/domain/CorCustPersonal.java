/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_PERSONAL
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCustPersonal extends BaseEntity {
	
	private String id; // ID
	private String custCode; // CUST_CODE
	private String gender; // GENDER
	private Date birthday; // BIRTHDAY
	private String country; // COUNTRY
	private String provience; // PROVIENCE
	private String city; // CITY
	private String district; // DISTRICT
	private String addr; // ADDR
	private String highestEdu; // HIGHEST_EDU
	private String occupation; // OCCUPATION
	private String jobTitle; // JOB_TITLE
	private String employer; // EMPLOYER
	private String email; // EMAIL
	private String postcode; // POSTCODE
	private String isMarried; // IS_MARRIED
	private String spouseCountry; // SPOUSE_COUNTRY
	private String spouseLocalName; // SPOUSE_LOCAL_NAME
	private String spouseFirstName; // SPOUSE_FIRST_NAME
	private String spouseLastName; // SPOUSE_LAST_NAME
	private String spouseCertType; // SPOUSE_CERT_TYPE
	private String spouseCertNum; // SPOUSE_CERT_NUM
	private Date spouseCertExpireDate; // SPOUSE_CERT_EXPIRE_DATE
	private String spousePhonenum; // SPOUSE_PHONENUM
	private String industry; // INDUSTRY
	private String creator; // CREATOR
	private Date createTime; // CREATE_TIME
	private String updator; // UPDATOR
	private Date updateTime; // UPDATE_TIME
	private String isValid; // IS_VALID
	private String mobilePhone; // MOBILE_PHONE
	private String telephone; // TELEPHONE
	private String fax; // FAX
	private String qq; // QQ
	
	private String isCheckin;  //是否止入
	private String isCheckout; //是否止出 
	
	
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getIsCheckin() {
        return isCheckin;
    }
    public void setIsCheckin(String isCheckin) {
        this.isCheckin = isCheckin;
    }
    public String getIsCheckout() {
        return isCheckout;
    }
    public void setIsCheckout(String isCheckout) {
        this.isCheckout = isCheckout;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getCustCode(){
		return custCode;
	}
	public void setCustCode(String custCode){
		this.custCode = custCode;
	}

	public String getGender(){
		return gender;
	}
	public void setGender(String gender){
		this.gender = gender;
	}

	public Date getBirthday(){
		return birthday;
	}
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	public String getCountry(){
		return country;
	}
	public void setCountry(String country){
		this.country = country;
	}

	public String getProvience(){
		return provience;
	}
	public void setProvience(String provience){
		this.provience = provience;
	}

	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}

	public String getDistrict(){
		return district;
	}
	public void setDistrict(String district){
		this.district = district;
	}

	public String getAddr(){
		return addr;
	}
	public void setAddr(String addr){
		this.addr = addr;
	}

	public String getHighestEdu(){
		return highestEdu;
	}
	public void setHighestEdu(String highestEdu){
		this.highestEdu = highestEdu;
	}

	public String getOccupation(){
		return occupation;
	}
	public void setOccupation(String occupation){
		this.occupation = occupation;
	}

	public String getJobTitle(){
		return jobTitle;
	}
	public void setJobTitle(String jobTitle){
		this.jobTitle = jobTitle;
	}

	public String getEmployer(){
		return employer;
	}
	public void setEmployer(String employer){
		this.employer = employer;
	}

	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}

	public String getPostcode(){
		return postcode;
	}
	public void setPostcode(String postcode){
		this.postcode = postcode;
	}

	public String getIsMarried(){
		return isMarried;
	}
	public void setIsMarried(String isMarried){
		this.isMarried = isMarried;
	}

	public String getSpouseCountry(){
		return spouseCountry;
	}
	public void setSpouseCountry(String spouseCountry){
		this.spouseCountry = spouseCountry;
	}

	public String getSpouseLocalName(){
		return spouseLocalName;
	}
	public void setSpouseLocalName(String spouseLocalName){
		this.spouseLocalName = spouseLocalName;
	}

	public String getSpouseFirstName(){
		return spouseFirstName;
	}
	public void setSpouseFirstName(String spouseFirstName){
		this.spouseFirstName = spouseFirstName;
	}

	public String getSpouseLastName(){
		return spouseLastName;
	}
	public void setSpouseLastName(String spouseLastName){
		this.spouseLastName = spouseLastName;
	}

	public String getSpouseCertType(){
		return spouseCertType;
	}
	public void setSpouseCertType(String spouseCertType){
		this.spouseCertType = spouseCertType;
	}

	public String getSpouseCertNum(){
		return spouseCertNum;
	}
	public void setSpouseCertNum(String spouseCertNum){
		this.spouseCertNum = spouseCertNum;
	}

	public Date getSpouseCertExpireDate(){
		return spouseCertExpireDate;
	}
	public void setSpouseCertExpireDate(Date spouseCertExpireDate){
		this.spouseCertExpireDate = spouseCertExpireDate;
	}

	public String getSpousePhonenum(){
		return spousePhonenum;
	}
	public void setSpousePhonenum(String spousePhonenum){
		this.spousePhonenum = spousePhonenum;
	}

	public String getIndustry(){
		return industry;
	}
	public void setIndustry(String industry){
		this.industry = industry;
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
    @Override
    public String toString() {
        return "CorCustPersonal [id=" + id + ", custCode=" + custCode + ", gender=" + gender + ", birthday=" + birthday
                + ", country=" + country + ", provience=" + provience + ", city=" + city + ", district=" + district
                + ", addr=" + addr + ", highestEdu=" + highestEdu + ", occupation=" + occupation + ", jobTitle="
                + jobTitle + ", employer=" + employer + ", email=" + email + ", postcode=" + postcode + ", isMarried="
                + isMarried + ", spouseCountry=" + spouseCountry + ", spouseLocalName=" + spouseLocalName
                + ", spouseFirstName=" + spouseFirstName + ", spouseLastName=" + spouseLastName + ", spouseCertType="
                + spouseCertType + ", spouseCertNum=" + spouseCertNum + ", spouseCertExpireDate="
                + spouseCertExpireDate + ", spousePhonenum=" + spousePhonenum + ", industry=" + industry + ", creator="
                + creator + ", createTime=" + createTime + ", updator=" + updator + ", updateTime=" + updateTime
                + ", isValid=" + isValid + ", mobilePhone=" + mobilePhone + ", telephone=" + telephone + ", fax=" + fax
                + ", qq=" + qq + ", isCheckin=" + isCheckin + ", isCheckout=" + isCheckout + "]";
    }

	
}
