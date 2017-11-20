/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.biz.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.ibs.core.module.basefunc.domain.Audit;
import com.ibs.core.module.basefunc.service.*;
import com.ibs.core.module.customer.biz.ICorMertUserBiz;
import com.ibs.core.module.customer.biz.ICustCompanyBiz;
import com.ibs.core.module.customer.dao.ICustCompanyDao;
import com.ibs.core.module.customer.dao.ICustDao;
import com.ibs.core.module.customer.domain.CorCust;
import com.ibs.core.module.customer.domain.CorCustCompany;
import com.ibs.core.module.customer.domain.CorMertUser;
import com.ibs.core.module.customer.dto.CustCompanyDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.BeanUtils;
import com.ibs.portal.framework.util.Md5Encoder;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public class CustCompanyBizImpl extends BaseBiz implements ICustCompanyBiz {
    private ICustDao custDao;
    private ICustCompanyDao custCompanyDao;

    private ICodeRuleService codeRuleService;
    private ICityService cityService;
    private IAuditService auditService;
    private ICorMertUserBiz corMertUserBiz;
    private IEmailService emailService;
    private ISMSService SMSService;

    

    public void setCustCompanyDao(ICustCompanyDao CustCompanyDao) {
        this.custCompanyDao = CustCompanyDao;
    }

    public void setCustDao(ICustDao custDao) {
        this.custDao = custDao;
    }

    public ICorMertUserBiz getCorMertUserBiz() {
        return corMertUserBiz;
    }

    public void setCorMertUserBiz(ICorMertUserBiz corMertUserBiz) {
        this.corMertUserBiz = corMertUserBiz;
    }

    public IEmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(IEmailService emailService) {
        this.emailService = emailService;
    }

    public ISMSService getSMSService() {
        return SMSService;
    }

    public void setSMSService(ISMSService sMSService) {
        SMSService = sMSService;
    }

    public ICodeRuleService getCodeRuleService() {
        return codeRuleService;
    }

    public void setCodeRuleService(ICodeRuleService codeRuleService) {
        this.codeRuleService = codeRuleService;
    }

    public void setCityService(ICityService cityService) {
        this.cityService = cityService;
    }

    public IAuditService getAuditService() {
        return auditService;
    }

    public void setAuditService(IAuditService auditService) {
        this.auditService = auditService;
    }

    public ICustDao getCustDao() {
        return custDao;
    }

    public ICustCompanyDao getCustCompanyDao() {
        return custCompanyDao;
    }

    public ICityService getCityService() {
        return cityService;
    }

    @Override
    public IPage<CustCompanyDto> findCustCompanyDtosBySql(QueryPage queryPage, CustCompanyDto custCompanyCondition) {
        return custCompanyDao.findCustCompanyDtosBySql(queryPage, custCompanyCondition);
    }

    public void saveOrUpdate(CorCustCompany corCustCompany) {
        custCompanyDao.saveOrUpdate(corCustCompany);
    }

    @Override
    public CustCompanyDto getCustCompanyDtoById(String id) {
        CustCompanyDto custCompanyCondition = new CustCompanyDto();
        custCompanyCondition.setId(id);
        return custCompanyDao.getCustCompanyDtoByCondition(custCompanyCondition);
    }

    @Override
    public List<OptionObjectPair> getAreasInfo(String type, String code) {
        if ("allCountry".equals(type)) {
            return cityService.getAllCountry();
        } else if ("country".equals(type)) {
            return cityService.getProvinceByCountryCode(code);
        } else if ("provience".equals(type)) {
            return cityService.getCityByProvinceCode(code);
        }
        return null;
    }

    @Override
    public void opera(CustCompanyDto custCompanyDto) {
        if (null != custCompanyDto) {
            this.saveOrUpdateInfo(custCompanyDto);
        }
    }

    /**
     * cust cust_company audit
     * 
     * @param custCompanyDto
     */
    private void saveOrUpdateInfo(CustCompanyDto custCompanyDto) {
        Date dt = new Date();
        CustCompanyDto custTemp = new CustCompanyDto();
        String operaType = custCompanyDto.getOperaType();
        String userName = custCompanyDto.getUpdator();
        //把法人的证件信息也存入客户信息表中
        custCompanyDto.setCertType(custCompanyDto.getCorporateCertType());
        custCompanyDto.setCertNum(custCompanyDto.getCorporateCertNum());
        if (CorConstants.OPERA_ADD.equals(operaType)) {// 新增
            String custCode = "";
            try {
                custCode = codeRuleService.getCorCustCode();
            } catch (Exception e) {
                logger.error("调用生成客户编码接口出现异常：", e);
                throw new BizException("调用生成客户编码接口出现异常：", e);
            }
            custCompanyDto.setCustCode(custCode);
            custCompanyDto.setCreateTime(dt);
            custCompanyDto.setCreator(userName);
            custCompanyDto.setUpdateTime(dt);
            custCompanyDto.setUpdator(userName);
            custCompanyDto.setSourceCode(custCode);
            custCompanyDto.setAuditType(CorConstants.AUDIT_TYPE_CUSTOMER);
            custCompanyDto.setStatus(CorConstants.DATA_DICT__CHECK_STATUS_UNCHECK);
            custCompanyDto.setIsValid(CorConstants.DATA_DICT__IS_VALID);
            custCompanyDto.setId(null);
        } else if (CorConstants.OPERA_AUD.equals(operaType) || CorConstants.OPERA_UPD.equals(operaType)) {// 审核
            custTemp = this.getCustCompanyDtoById(custCompanyDto.getId());

            custTemp.setAuditPerson(userName);
            custTemp.setAuditTime(dt);
            custTemp.setUpdateTime(dt);
            if (CorConstants.OPERA_AUD.equals(custCompanyDto.getOperaType())) {
                custTemp.setAuditReason(custCompanyDto.getAuditReason());
                custTemp.setAuditType(CorConstants.AUDIT_TYPE_CUSTOMER);
                custTemp.setStatus(custCompanyDto.getStatus());
            } else if (CorConstants.OPERA_UPD.equals(custCompanyDto.getOperaType())) {
                custTemp.setAuditType(CorConstants.AUDIT_TYPE_CUSTOMER);
                custTemp.setStatus(CorConstants.DATA_DICT__CHECK_STATUS_UNCHECK);
            }
        }
        BeanUtils.copyObjectTypeProperties(custTemp, custCompanyDto);
        CorCust cust = new CorCust();
        Audit audit = new Audit();
        CorCustCompany company = new CorCustCompany();

        BeanUtils.copyObjectTypeProperties(audit, custTemp);
        BeanUtils.copyObjectTypeProperties(cust, custTemp);
        BeanUtils.copyObjectTypeProperties(company, custTemp);
        if (CorConstants.OPERA_ADD.equals(operaType)) {
            cust.setStatus(CorConstants.DATA_DICT__CORE_CUST_STATUS_INVALID);// 未生效 -新增
        }
        if (CorConstants.OPERA_AUD.equals(operaType) || CorConstants.OPERA_UPD.equals(operaType)) {
            cust.setId(custTemp.getCid());
            audit.setId(custTemp.getCaid());
            company.setId(custTemp.getCcid());
            if (CorConstants.DATA_DICT__CHECK_STATUS_CHECK_SUCCESS.equals(audit.getStatus())) {
                cust.setStatus(CorConstants.DATA_DICT__CORE_CUST_STATUS_VALID);// 审核通过 已生效
            } else {
                cust.setStatus(CorConstants.DATA_DICT__CORE_CUST_STATUS_INVALID);// 未生效 -新增，审核失败
            }
        }
        custDao.saveOrUpdate(cust);
        custCompanyDao.saveOrUpdate(company);
        auditService.saveAudit(audit);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ibs.core.module.customer.biz.ICustCompanyBiz#activeAccount(com.ibs.
     * core.module.customer.dto.CustCompanyDto)
     */
    @Override
    public boolean activeAccount(CustCompanyDto custCompanyDto) {
        Date dt = new Date();
        boolean flag = false;
        String loginUser = "";
        String password = "";
        String smsCertCode = "";
        try {
            loginUser = codeRuleService.getCorCustLoginName(custCompanyDto.getCustCode());
            password = codeRuleService.getCorCustLoginPwd();
            smsCertCode = SMSService.getDefaultSMS();
        } catch (Exception e) {
            logger.error("调用【codeRuleService，codeRuleService，SMSService】接口出现异常：", e);
            throw new BizException("调用【codeRuleService，codeRuleService，SMSService】接口出现异常：", e);
        }
        String userName = custCompanyDto.getUpdator();
        CorMertUser corMertUser = new CorMertUser();
        corMertUser.setCustCode(custCompanyDto.getCustCode());
        corMertUser.setUserCode(loginUser);
        corMertUser.setPassword(new Md5Encoder().encode(password));
        corMertUser.setSmsCertCode(smsCertCode);
        corMertUser.setPhoneNum(custCompanyDto.getContactPhone());
        corMertUser.setEmail(custCompanyDto.getContactEmail());
        corMertUser.setStatus(CorConstants.MERT_USER_STATUS_01);
        corMertUser.setIsValid(CorConstants.DATA_DICT__IS_VALID);
        corMertUser.setUpdateTime(dt);
        corMertUser.setUpdator(userName);
        corMertUser.setCreateTime(dt);
        corMertUser.setCreator(userName);
        try {
            corMertUserBiz.saveCorMertUser(corMertUser);
            String content = "userName:=" + loginUser + "password:=" + password;
            emailService.sendEmail(custCompanyDto.getContactEmail(), "激活用户信息", content, null);
            flag = true;
        } catch (Exception e) {
            logger.error("调用[mertUserService,emailService]接口出现异常：", e);
            throw new BizException("调用[mertUserService,emailService]接口出现异常：", e);
        }
        return flag;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibs.core.module.customer.biz.ICustCompanyBiz#existsCustInfo()
     */
    @Override
    public boolean existsInCustInfo(CustCompanyDto custCompanyDto) {
        // 社会信用代码或者营业执照编码校验是否存在重复会员，如存在提示“该会员信息已存在，无法新建成功”
        IPage<CustCompanyDto> page = custCompanyDao.getCorCustCompanyByConditionOR(custCompanyDto);
        Collection<CustCompanyDto> rows = page.getRows();
        if (null==page||null==rows||rows.isEmpty()) {
            return false;
        }
        return true;
    }


}
