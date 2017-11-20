/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.customer.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.ibs.core.module.customer.dao.ICorCardbindInfoDao;
import com.ibs.core.module.customer.domain.CorCardbindInfo;
import com.ibs.core.module.customer.dto.CardBindDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CARDBIND_INFO
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCardbindInfoDaoImpl extends BaseEntityDao<CorCardbindInfo> implements ICorCardbindInfoDao {

    public CorCardbindInfo load(String id) {
        logger.info("entering action::CorCardbindInfoDaoImpl.load()...");
        return super.load(id);
    }

    public void saveOrUpdate(CorCardbindInfo corCardbindInfo) {
        logger.info("entering action::CorCardbindInfoDaoImpl.saveOrUpdate()...");
        super.saveOrUpdate(corCardbindInfo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibs.core.module.customer.dao.ICorCardbindInfoDao#
     * getCorCardbindInfoList(com.ibs.core.module.customer.dto.CardBindDto)
     */
    @Override
    public List<CorCardbindInfo> getCorCardbindInfoList(CardBindDto cardBindDto) {
        Criteria criteria = super.getSession().createCriteria(CorCardbindInfo.class);
        criteria.add(Restrictions.eq("isValid", CorConstants.DATA_DICT__IS_VALID));
        
        if(null != cardBindDto && null != cardBindDto.getPersonalCustCodeList()&&cardBindDto.getPersonalCustCodeList().size()>0){
            criteria.add(Restrictions.in("custCode",cardBindDto.getPersonalCustCodeList()));
        }else{
            if(null != cardBindDto && null != cardBindDto.getStatus()){
                criteria.add(Restrictions.eq("status",cardBindDto.getStatus()));
            }
            if (null != cardBindDto && null != cardBindDto.getCustCode()) {
                criteria.add(Restrictions.eq("custCode", cardBindDto.getCustCode()));
            }
            if (null != cardBindDto && null != cardBindDto.getBankCardNum()) {
                criteria.add(Restrictions.eq("bankCardNum", cardBindDto.getBankCardNum()));
            }
        }
        List<CorCardbindInfo> list = criteria.list();
        if (null != list && list.size() > 0) {
            return list;
        }
        return null;
    }

}
