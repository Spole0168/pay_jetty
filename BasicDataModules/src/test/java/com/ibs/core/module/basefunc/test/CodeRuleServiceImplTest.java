package com.ibs.core.module.basefunc.test;

import org.junit.Test;

import com.ibs.core.module.basefunc.dao.ICodeRuleDao;
import com.ibs.core.module.basefunc.domain.CodeRule;
import com.ibs.core.module.basefunc.service.IAuditService;
import com.ibs.core.module.basefunc.service.ICityService;
import com.ibs.core.module.basefunc.service.ICodeRuleService;
import com.ibs.core.module.mdmbasedata.common.CorConstants;

public class CodeRuleServiceImplTest extends BasefuncTestBase {
    private ICodeRuleService codeRuleService;
    private ICityService cityServiceImpl;
    private IAuditService auditService;
    private ICodeRuleDao codeRuleDao;

    @Test
    public void testGetFundOrderCode() {
        logger.info("into testGetFundOrderCode method...");
        String result = codeRuleService.getFundOrderCode("01");
        CodeRule queryResult = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_FUND_ORDER);
        assertEquals(result, queryResult.getCurrentValue());
    }
    
    @Test
    public void testGetAccountCode(){
        logger.info("into testGetAccountCode method...");
        String result = codeRuleService.getAccountCode("01", "01", "01");
        CodeRule queryResult = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_ACCOUNT);
        assertEquals(result, queryResult.getCurrentValue());
    }
    
    @Test
    public void testGetMenchantCode(){
        logger.info("into testGetMenchantCode method...");
        String result = codeRuleService.getMenchantCode("01", "01");
        CodeRule queryResult = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_MENCHANT);
        assertEquals(result, queryResult.getCurrentValue());
    }
    
    @Test
    public void testGetCorCustCode(){
        logger.info("into testGetCorCustCode method...");
        String result = codeRuleService.getCorCustCode();
        CodeRule queryResult = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_COR_CUST_COMPANY);
        assertEquals(result, queryResult.getCurrentValue());
    }
    
    @Test
    public void testGetPayOrderCode(){
        logger.info("into testGetPayOrderCode method...");
        String result = codeRuleService.getPayOrderCode("01");
        System.out.println(result);
        CodeRule queryResult = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_PAY_ORDER);
        assertEquals(result, queryResult.getCurrentValue());
    }
    
    @Test
    public void testGetFeeCode(){
    	logger.info("into testGetFeeCode method...");
    	String result = codeRuleService.getFeeCode(false, "CP0102");
    	System.out.println(result);
    	CodeRule queryResult = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_FEE_ORDER);
    	assertEquals(result, queryResult.getCurrentValue());
    }
    
    @Test
    public void testGetCorCustPersonalCode(){
    	logger.info("into testGetCorCustPersonalCode method...");
    	String result = codeRuleService.getCorCustPersonalCode();
    	System.out.println("生成个人会员编码：" + result);
    	CodeRule queryResult = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_COR_CUST_PERSONAL);
    	assertEquals(result, queryResult.getCurrentValue());
    }
    
    @Test
    public void testGetIncomeOrderCode(){
    	logger.info("into testGetIncomeOrderCode method...");
    	String result = codeRuleService.getIncomeOrderCode("01");
    	System.out.println("生成入款订单编码：" + result);
    	CodeRule queryResult = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_INCOME_ORDER);
    	assertEquals(result, queryResult.getCurrentValue());
    }

    @Test
    public void testGetOutOrderCode(){
    	logger.info("into testGetOutOrderCode method...");
    	String result = codeRuleService.getOutOrderCode("01");
    	System.out.println("生成出款订单编码：" + result);
    	CodeRule queryResult = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_OUT_ORDER);
    	assertEquals(result, queryResult.getCurrentValue());
    }

    @Test
    public void testGetSettleOrderCode(){
    	logger.info("into testGetSettleOrderCode method...");
    	String result = codeRuleService.getSettleOrderCode();
    	System.out.println("生成结算订单编码：" + result);
    	CodeRule queryResult = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_SETTLE_ORDER);
    	assertEquals(result, queryResult.getCurrentValue());
    }
    
    @Test
    public void testGetTransferOrderCode(){
    	logger.info("into testGetTransferOrderCode method...");
    	String result = codeRuleService.getTransferOrderCode("01");
    	System.out.println("生成转账订单编码：" + result);
    	CodeRule queryResult = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_TRANSFER_ORDER);
    	assertEquals(result, queryResult.getCurrentValue());
    }

    public ICodeRuleService getCodeRuleService() {
        return codeRuleService;
    }

    public void setCodeRuleService(ICodeRuleService codeRuleService) {
        this.codeRuleService = codeRuleService;
    }

    public ICityService getCityServiceImpl() {
        return cityServiceImpl;
    }

    public void setCityServiceImpl(ICityService cityServiceImpl) {
        this.cityServiceImpl = cityServiceImpl;
    }

    public IAuditService getAuditService() {
        return auditService;
    }

    public void setAuditService(IAuditService auditService) {
        this.auditService = auditService;
    }

    public ICodeRuleDao getCodeRuleDao() {
        return codeRuleDao;
    }

    public void setCodeRuleDao(ICodeRuleDao codeRuleDao) {
        this.codeRuleDao = codeRuleDao;
    }
}
