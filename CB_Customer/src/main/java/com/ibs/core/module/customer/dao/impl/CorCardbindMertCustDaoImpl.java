/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.customer.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.ibs.core.module.customer.dao.ICorCardbindMertCustDao;
import com.ibs.core.module.customer.domain.CorCardbindMertCust;
import com.ibs.core.module.customer.dto.CardBindDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CARDBIND_MERT_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCardbindMertCustDaoImpl extends BaseEntityDao<CorCardbindMertCust> implements ICorCardbindMertCustDao {

    public CorCardbindMertCust load(String id) {
        logger.info("entering action::CorCardbindMertCustDaoImpl.load()...");
        return super.load(id);
    }

    public void saveOrUpdate(CorCardbindMertCust corCardbindMertCust) {
        logger.info("entering action::CorCardbindMertCustDaoImpl.saveOrUpdate()...");
        super.saveOrUpdate(corCardbindMertCust);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibs.core.module.customer.dao.ICorCardbindMertCustDao#
     * getCorCardbindMertCustList(com.ibs.core.module.customer.dto.CardBindDto)
     */
    @Override
    public List<CorCardbindMertCust> getCorCardbindMertCustList(CardBindDto cardBindDto) {
        Criteria criteria = super.getSession().createCriteria(CorCardbindMertCust.class);
        criteria.add(Restrictions.eq("isValid", CorConstants.DATA_DICT__IS_VALID));
        if(null != cardBindDto && null != cardBindDto.getStatus()){
            criteria.add(Restrictions.eq("status",cardBindDto.getStatus()));
        }
        
        if (null != cardBindDto && null != cardBindDto.getToken()) {
            criteria.add(Restrictions.eq("token", cardBindDto.getToken()));
        }
        if (null != cardBindDto && null != cardBindDto.getCustCode()) {
            criteria.add(Restrictions.eq("custCode", cardBindDto.getCustCode()));
        }
        if (null != cardBindDto && null != cardBindDto.getMerchantCode()) {
            criteria.add(Restrictions.eq("merchantCode", cardBindDto.getMerchantCode()));
        }
        if (null != cardBindDto && null != cardBindDto.getMertCustCode()) {
            criteria.add(Restrictions.eq("mertCustCode", cardBindDto.getMertCustCode()));
        }
        if (null != cardBindDto && null != cardBindDto.getBankCardNum()) {
            criteria.add(Restrictions.eq("bankCardNum", cardBindDto.getBankCardNum()));
        }

        List<CorCardbindMertCust> list = criteria.list();
        if (null != list && list.size() > 0) {
            return list;
        }
        return null;
    }

}
