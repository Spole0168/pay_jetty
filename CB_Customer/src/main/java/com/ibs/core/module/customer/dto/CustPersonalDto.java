package com.ibs.core.module.customer.dto;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class CustPersonalDto extends BaseEntity {

    private static final long serialVersionUID = 7067812546094562159L;
    //cust
    private String id; // ID
    private String cid; // ID
    private String custCode; // CUST_CODE
    private String custType; // CUST_TYPE
    private String country; // COUNTRY
    private String provience;
    private String city;
    private String region;
    private String localName; // LOCAL_NAME
    private String realNameLeve; // REAL_NAME_LEVE
    private String firstName; // FIRST_NAME
    private String lastName; // LAST_NAME
    private String certType; // CERT_TYPE
    private String certNum; // CERT_NUM
    private Date certExpireDate; // CERT_EXPIRE_DATE
    private String certCopy; // CERT_COPY
    private String custStatus; // STATUS
    private Date regTime; // REG_TIME
    private String dataSource; // DATA_SOURCE
    private String creator; // CREATOR
    private Date createTime; // CREATE_TIME
    private String updator; // UPDATOR
    private Date updateTime; // UPDATE_TIME
    private String remark; // REMARK
    private String isValid; // IS_VALID
    private String postcode; // IS_VALID
    
    //personal
    private String cpid; // ID
//    private String id; // ID
//    private String custCode; // CUST_CODE
    private String gender; // GENDER
    private Date birthday; // BIRTHDAY
//    private String country; // COUNTRY
//    private String provience; // PROVIENCE
//    private String city; // CITY
    private String district; // DISTRICT
    private String addr; // ADDR
    private String highestEdu; // HIGHEST_EDU
    private String occupation; // OCCUPATION
    private String jobTitle; // JOB_TITLE
    private String employer; // EMPLOYER
    private String email; // EMAIL
//    private String postcode; // POSTCODE
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
//    private String creator; // CREATOR
//    private Date createTime; // CREATE_TIME
//    private String updator; // UPDATOR
//    private Date updateTime; // UPDATE_TIME
//    private String isValid; // IS_VALID
    private String mobilePhone; // MOBILE_PHONE
    private String telephone; // TELEPHONE
    private String fax; // FAX
    private String qq; // QQ
    
    private String isCheckin;  //是否止入
    private String isCheckout; //是否止出 
    //查询条件
    private String startCreateTime;
    private String endCreateTime;
    // opreaType = 0;//opreaType : 0 -新增，1-查看，2-审核，3-修改
    private String operaType; // 操作类型
    
    
    public String getCid() {
        return cid;
    }
    public void setCid(String cid) {
        this.cid = cid;
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
    public String getCpid() {
        return cpid;
    }
    public void setCpid(String cpid) {
        this.cpid = cpid;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCustCode() {
        return custCode;
    }
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }
    public String getCustType() {
        return custType;
    }
    public void setCustType(String custType) {
        this.custType = custType;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getProvience() {
        return provience;
    }
    public void setProvience(String provience) {
        this.provience = provience;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getLocalName() {
        return localName;
    }
    public void setLocalName(String localName) {
        this.localName = localName;
    }
    public String getRealNameLeve() {
        return realNameLeve;
    }
    public void setRealNameLeve(String realNameLeve) {
        this.realNameLeve = realNameLeve;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public Date getCertExpireDate() {
        return certExpireDate;
    }
    public void setCertExpireDate(Date certExpireDate) {
        this.certExpireDate = certExpireDate;
    }
    public String getCertCopy() {
        return certCopy;
    }
    public void setCertCopy(String certCopy) {
        this.certCopy = certCopy;
    }
    public String getCustStatus() {
        return custStatus;
    }
    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
    }
    public Date getRegTime() {
        return regTime;
    }
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }
    public String getDataSource() {
        return dataSource;
    }
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
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
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getIsValid() {
        return isValid;
    }
    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getHighestEdu() {
        return highestEdu;
    }
    public void setHighestEdu(String highestEdu) {
        this.highestEdu = highestEdu;
    }
    public String getOccupation() {
        return occupation;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getEmployer() {
        return employer;
    }
    public void setEmployer(String employer) {
        this.employer = employer;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getIsMarried() {
        return isMarried;
    }
    public void setIsMarried(String isMarried) {
        this.isMarried = isMarried;
    }
    public String getSpouseCountry() {
        return spouseCountry;
    }
    public void setSpouseCountry(String spouseCountry) {
        this.spouseCountry = spouseCountry;
    }
    public String getSpouseLocalName() {
        return spouseLocalName;
    }
    public void setSpouseLocalName(String spouseLocalName) {
        this.spouseLocalName = spouseLocalName;
    }
    public String getSpouseFirstName() {
        return spouseFirstName;
    }
    public void setSpouseFirstName(String spouseFirstName) {
        this.spouseFirstName = spouseFirstName;
    }
    public String getSpouseLastName() {
        return spouseLastName;
    }
    public void setSpouseLastName(String spouseLastName) {
        this.spouseLastName = spouseLastName;
    }
    public String getSpouseCertType() {
        return spouseCertType;
    }
    public void setSpouseCertType(String spouseCertType) {
        this.spouseCertType = spouseCertType;
    }
    public String getSpouseCertNum() {
        return spouseCertNum;
    }
    public void setSpouseCertNum(String spouseCertNum) {
        this.spouseCertNum = spouseCertNum;
    }
    public Date getSpouseCertExpireDate() {
        return spouseCertExpireDate;
    }
    public void setSpouseCertExpireDate(Date spouseCertExpireDate) {
        this.spouseCertExpireDate = spouseCertExpireDate;
    }
    public String getSpousePhonenum() {
        return spousePhonenum;
    }
    public void setSpousePhonenum(String spousePhonenum) {
        this.spousePhonenum = spousePhonenum;
    }
    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    public String getStartCreateTime() {
        return startCreateTime;
    }
    public void setStartCreateTime(String startCreateTime) {
        this.startCreateTime = startCreateTime;
    }
    public String getEndCreateTime() {
        return endCreateTime;
    }
    public void setEndCreateTime(String endCreateTime) {
        this.endCreateTime = endCreateTime;
    }
    public String getOperaType() {
        return operaType;
    }
    public void setOperaType(String operaType) {
        this.operaType = operaType;
    }
    @Override
    public String toString() {
        return "CustPersonalDto [id=" + id + ", cid=" + cid + ", custCode=" + custCode + ", custType=" + custType
                + ", country=" + country + ", provience=" + provience + ", city=" + city + ", region=" + region
                + ", localName=" + localName + ", realNameLeve=" + realNameLeve + ", firstName=" + firstName
                + ", lastName=" + lastName + ", certType=" + certType + ", certNum=" + certNum + ", certExpireDate="
                + certExpireDate + ", certCopy=" + certCopy + ", custStatus=" + custStatus + ", regTime=" + regTime
                + ", dataSource=" + dataSource + ", creator=" + creator + ", createTime=" + createTime + ", updator="
                + updator + ", updateTime=" + updateTime + ", remark=" + remark + ", isValid=" + isValid
                + ", postcode=" + postcode + ", cpid=" + cpid + ", gender=" + gender + ", birthday=" + birthday
                + ", district=" + district + ", addr=" + addr + ", highestEdu=" + highestEdu + ", occupation="
                + occupation + ", jobTitle=" + jobTitle + ", employer=" + employer + ", email=" + email
                + ", isMarried=" + isMarried + ", spouseCountry=" + spouseCountry + ", spouseLocalName="
                + spouseLocalName + ", spouseFirstName=" + spouseFirstName + ", spouseLastName=" + spouseLastName
                + ", spouseCertType=" + spouseCertType + ", spouseCertNum=" + spouseCertNum + ", spouseCertExpireDate="
                + spouseCertExpireDate + ", spousePhonenum=" + spousePhonenum + ", industry=" + industry
                + ", mobilePhone=" + mobilePhone + ", telephone=" + telephone + ", fax=" + fax + ", qq=" + qq
                + ", isCheckin=" + isCheckin + ", isCheckout=" + isCheckout + ", startCreateTime=" + startCreateTime
                + ", endCreateTime=" + endCreateTime + ", operaType=" + operaType + "]";
    }
    
    
    
}
