package com.ibs.core.module.customer.service.impl;

import java.util.Date;
import java.util.List;

import com.ibs.core.module.basefunc.service.ICodeRuleService;
import com.ibs.core.module.customer.dao.ICustPersonalDao;
import com.ibs.core.module.customer.dao.ICorFuzzyCustDao;
import com.ibs.core.module.customer.dao.ICustCompanyDao;
import com.ibs.core.module.customer.dao.ICustDao;
import com.ibs.core.module.customer.service.ICustService;
import com.ibs.portal.framework.server.service.BaseService;
import com.ibs.portal.framework.util.BeanUtils;
import com.ibs.core.module.customer.domain.CorCust;
import com.ibs.core.module.customer.domain.CorCustCompany;
import com.ibs.core.module.customer.domain.CorCustPersonal;
import com.ibs.core.module.customer.domain.CorFuzzyCust;
import com.ibs.core.module.customer.dto.CustCompanyDto;
import com.ibs.core.module.customer.dto.CustPersonalDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;

public class CustServiceImpl extends BaseService implements ICustService {

    private ICustDao custDao;
    

    private ICustCompanyDao custCompanyDao;
    
    private ICustPersonalDao custPersonalDao;
    
    private ICorFuzzyCustDao corFuzzyCustDao;
    
    private ICodeRuleService codeRuleService;
    

    public ICustPersonalDao getCustPersonalDao() {
        return custPersonalDao;
    }

    public void setCustPersonalDao(ICustPersonalDao custPersonalDao) {
        this.custPersonalDao = custPersonalDao;
    }

    public ICodeRuleService getCodeRuleService() {
        return codeRuleService;
    }

    public void setCodeRuleService(ICodeRuleService codeRuleService) {
        this.codeRuleService = codeRuleService;
    }

    public ICorFuzzyCustDao getCorFuzzyCustDao() {
        return corFuzzyCustDao;
    }

    public void setCorFuzzyCustDao(ICorFuzzyCustDao corFuzzyCustDao) {
        this.corFuzzyCustDao = corFuzzyCustDao;
    }

    public ICustDao getCustDao() {
        return custDao;
    }

    public void setCustDao(ICustDao custDao) {
        this.custDao = custDao;
    }

    public ICustCompanyDao getCustCompanyDao() {
        return custCompanyDao;
    }

    public void setCustCompanyDao(ICustCompanyDao custCompanyDao) {
        this.custCompanyDao = custCompanyDao;
    }

    @Override
    public boolean saveCorFuzzyCust(CorFuzzyCust corFuzzyCust) {
        
        logger.debug("enter CustServiceImpl.saveCorFuzzyCust # corFuzzyCust=" + corFuzzyCust);
        
        Date dt = new Date();
        corFuzzyCust.setId(null);
        corFuzzyCust.setCreateTime(dt);
        corFuzzyCust.setUpdateTime(dt);
        corFuzzyCust.setIsValid(CorConstants.DATA_DICT__IS_VALID);
        corFuzzyCustDao.saveOrUpdate(corFuzzyCust);
        return true;
    }

    @Override
    public boolean saveCustPersonal(CustPersonalDto custPersonalDto) {
        logger.debug("enter CustServiceImpl.saveCustPersonal # custPersonalDto=" + custPersonalDto);
        
        Date dt = new Date();
        custPersonalDto.setId(null); 
        String custCode = "";
        try {
            custCode = codeRuleService.getCorCustPersonalCode();
        } catch (Exception e) {
            logger.error("调用生成客户编码接口出现异常：" + e.getLocalizedMessage());
            return false;
        }
        custPersonalDto.setCustCode(custCode);
        custPersonalDto.setCustType(CorConstants.DATA_DICT__CORE_CUST_TYPE_PERSONAL);
        custPersonalDto.setCustStatus(CorConstants.DATA_DICT__CORE_CUST_STATUS_VALID);
        custPersonalDto.setRegTime(dt);
        custPersonalDto.setCreateTime(dt);                  
        custPersonalDto.setUpdateTime(dt);                  
        custPersonalDto.setIsValid(CorConstants.DATA_DICT__IS_VALID);  
        CorCust cust = new CorCust();
        CorCustPersonal corCustPersonal = new CorCustPersonal();
        
        BeanUtils.copyObjectTypeProperties(cust, custPersonalDto);
        BeanUtils.copyObjectTypeProperties(corCustPersonal, custPersonalDto);
        custDao.saveOrUpdate(cust);
        custPersonalDao.saveOrUpdate(corCustPersonal);
        return false;
    }

    @Override
    public String queryCustContactInfo(String custCode) {
        logger.debug("enter CustServiceImpl.queryCustContactInfo # custCode=" + custCode);
        CorCustCompany corCustCompany = new CorCustCompany();
        corCustCompany.setCustCode(custCode);

        List<CorCustCompany> list = custCompanyDao.getCorCustCompanyByConditionAND(corCustCompany);
        if (null != list && list.size() > 0) {
            corCustCompany = list.get(0);
        }
        StringBuffer contactInfo = new StringBuffer();
        if (corCustCompany != null) {
            contactInfo.append(corCustCompany.getContactPhone()).append("#");
            contactInfo.append(corCustCompany.getContactEmail());
        }
        return contactInfo.toString();
    }

    /* (non-Javadoc)
     * @see com.ibs.core.module.customer.service.ICustService#queryCustDtoInfo(com.ibs.core.module.customer.dto.CustCompanyDto)
     */
    @Override
    public CustCompanyDto queryCustDtoInfo(CustCompanyDto cdto) {
        return custCompanyDao.getCustCompanyDtoByCondition(cdto);
    }

    /* (non-Javadoc)
     * @see com.ibs.core.module.customer.service.ICustService#personalAccountLimit(java.lang.String, java.lang.String)
     */
    @Override
    public boolean personalAccountLimit(String id, String personalAccountOpera) {
        //personalAccountOpera : 0 -止入，1-取消止入，2-止出，3-取消止出
        CorCustPersonal cp = custPersonalDao.load(id);
        cp.setUpdateTime(new Date());
        cp.setUpdator("Admin");
        String isCheckin = CorConstants.MERT_NORMAL;   //
        String isCheckout = CorConstants.MERT_NORMAL;
        if("0".equals(personalAccountOpera)){//止入
            isCheckin = CorConstants.MERT_CHECKIN;
            cp.setIsCheckin(isCheckin);     // 开通个人账户 止入默认状态
        }
        if("1".equals(personalAccountOpera)){//止入
            isCheckin = CorConstants.MERT_NORMAL;
            cp.setIsCheckin(isCheckin);     // 开通个人账户 止入默认状态
        }
        if("2".equals(personalAccountOpera)){//止出
            isCheckout = CorConstants.MERT_CHECKOUT;
            cp.setIsCheckout(isCheckout);   // 开通个人账户 止出默认状态
        }
        if("3".equals(personalAccountOpera)){//止出
            isCheckout = CorConstants.MERT_NORMAL;
            cp.setIsCheckout(isCheckout);   // 开通个人账户 止出默认状态
        }
        custPersonalDao.saveOrUpdate(cp);
        return true;
    }

}
