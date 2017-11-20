package com.ibs.core.module.customer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.ibs.core.module.customer.dao.ICustDao;
import com.ibs.core.module.customer.domain.CorCust;
import com.ibs.core.module.customer.domain.CorCustCompany;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.util.StringUtils;

public class CustDaoImpl extends BaseEntityDao<CorCust> implements ICustDao {
    // 根据custCode获取Cust信息
    @Override
    public CorCust getCustByCustCode(String custCode) {
        logger.info("entering dao::CustDaoImpl.getCustByCustCode()...");
        String hql = " from CorCust where isValid ='" + CorConstants.DATA_DICT__IS_VALID + "' and custCode = '" + custCode + "'";
        List<Object> list = findByHql(hql, null, null);
        if (list != null && list.size() > 0) {
            return (CorCust) list.get(0);
        }
        return null;
    }

    // 根据localName获取Cust信息
    @Override
    public CorCust getCustByLocalName(String localName) {
        logger.info("entering dao::CustDaoImpl.getCustByLocalName()...");
        String hql = " from CorCust where isValid ='" + CorConstants.DATA_DICT__IS_VALID + "' and localName = '" + localName + "'";
        List<Object> list = findByHql(hql, null, null);
        if (list != null && list.size() > 0) {
            return (CorCust) list.get(0);
        }
        return null;
    }

    @Override
    public void saveOrUpdate(CorCust cust) {
        logger.info("entering dao::CustDaoImpl.saveOrUpdate()...");
        super.saveOrUpdate(cust);
    }

    @Override
    public List<CorCust> findListByCustCode(String custCode, String status, String isValid) {
        logger.info("");
        List<Object> args = new ArrayList<Object>();
        StringBuffer hql = new StringBuffer();

        hql.append("from CorCust where 1=1");
        if (StringUtils.isNotEmpty(custCode)) {
            args.add(custCode);
            hql.append(" and custCode = ?");
        }
        if (StringUtils.isNotEmpty(status)) {
            args.add(status);
            hql.append(" and status = ?");
        }
        if (StringUtils.isNotEmpty(isValid)) {
            args.add(isValid);
            hql.append(" and isValid = ?");
        }
        return super.findByValue(hql.toString(), args);
    }

    @Override
    public CorCust getCustById(String id) {
        return super.load(id);
    }

    /* (non-Javadoc)
     * @see com.ibs.core.module.customer.dao.ICustDao#getCorCustsList(com.ibs.core.module.customer.domain.CorCust)
     */
    @Override
    public List<CorCust> getCorCustsList(CorCust corCust) {
        Criteria criteria = super.getSession().createCriteria(CorCust.class);
        criteria.add(Restrictions.eq("isValid", CorConstants.DATA_DICT__IS_VALID));
        criteria.add(Restrictions.eq("custType",CorConstants.DATA_DICT__CORE_CUST_TYPE_PERSONAL));
        if (null != corCust && null != corCust.getCustCode()) {
            criteria.add(Restrictions.eq("custCode", corCust.getCustCode()));
        }
        if (null != corCust && null != corCust.getCertType()) {
            criteria.add(Restrictions.eq("certType", corCust.getCertType()));
        }
        if (null != corCust && null != corCust.getCertNum()) {
            criteria.add(Restrictions.eq("certNum", corCust.getCertNum()));
        }
        if (null != corCust && null != corCust.getLocalName()) {
            criteria.add(Restrictions.eq("localName", corCust.getLocalName()));
        }
        List<CorCust> list = criteria.list();
        if (null != list && list.size() > 0) {
            return list;
        }
        return null;
    }

}
