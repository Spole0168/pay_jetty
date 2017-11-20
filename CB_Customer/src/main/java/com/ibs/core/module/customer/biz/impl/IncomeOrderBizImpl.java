/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.biz.impl;

import com.ibs.core.module.basefunc.service.IAuditService;
import com.ibs.core.module.basefunc.service.ICodeRuleService;
import com.ibs.core.module.customer.biz.IIncomeOrderBiz;
import com.ibs.core.module.customer.dao.IMerchantDao;
import com.ibs.core.module.customer.dao.IncomeOrderDao;
import com.ibs.core.module.customer.dto.IncomeOrderDto;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_INCOME_ORDER
 * @modify		: your comments goes here (author,date,reason).
 */
public class IncomeOrderBizImpl extends BaseBiz implements IIncomeOrderBiz {

    private IncomeOrderDao incomeOrderDao;
    private IMerchantDao merchantDao;
    private IAuditService auditService;
    private ICodeRuleService codeRuleService;
    // private ICorIncomeOrderService corIncomeOrderService;
    // private ICorPaymentService corPaymentService;

    public IncomeOrderDao getIncomeOrderDao() {
        return incomeOrderDao;
    }

    public IAuditService getAuditService() {
        return auditService;
    }

    public IMerchantDao getMerchantDao() {
        return merchantDao;
    }

    public void setMerchantDao(IMerchantDao merchantDao) {
        this.merchantDao = merchantDao;
    }

    public ICodeRuleService getCodeRuleService() {
        return codeRuleService;
    }

    public void setCodeRuleService(ICodeRuleService codeRuleService) {
        this.codeRuleService = codeRuleService;
    }

    public void setAuditService(IAuditService auditService) {
        this.auditService = auditService;
    }

    public void setIncomeOrderDao(IncomeOrderDao incomeOrderDao) {
        this.incomeOrderDao = incomeOrderDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ibs.core.module.customer.biz.IincomeOrderBiz#findCorIncomeOrderByPage
     * (com.ibs.portal.framework.server.metadata.QueryPage,
     * com.ibs.core.module.customer.dto.IncomeOrderDto)
     */
    @Override
    public IPage<IncomeOrderDto> findCorIncomeOrderByPage(QueryPage queryPage, IncomeOrderDto incomeOrderDtoCondition) {
        return incomeOrderDao.findCorIncomeOrderByPage(queryPage, incomeOrderDtoCondition);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibs.core.module.customer.biz.IincomeOrderBiz#
     * getInCorAccountDtosByCondition(com.ibs.core.module.customer.dto.
     * IncomeOrderDto)
     */
    @Override
    public IncomeOrderDto getInCorAccountDtosByCondition(IncomeOrderDto incomeOrderDtoCondition) {
        return incomeOrderDao.getInCorAccountDtosByCondition(incomeOrderDtoCondition);
    }
}
