/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.customer.dao.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Hibernate;

import com.ibs.core.module.customer.dao.ICorMertBankAcntDao;
import com.ibs.core.module.customer.domain.CorMertBankAcnt;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.ColumnType;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_MERT_BANK_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorMertBankAcntDaoImpl extends BaseEntityDao<CorMertBankAcnt> implements ICorMertBankAcntDao {

    public IPage<CorMertBankAcnt> findCorMertBankAcntByPage(QueryPage queryPage) {
        logger.info("entering action::CorMertBankAcntDaoImpl.findCorMertBankAcntByPage()...");
        return super.findPageBy(queryPage);
    }

    public CorMertBankAcnt load(String id) {
        logger.info("entering action::CorMertBankAcntDaoImpl.load()...");
        return super.load(id);
    }

    public void saveOrUpdate(CorMertBankAcnt corMertBankAcnt) {
        logger.info("entering action::CorMertBankAcntDaoImpl.saveOrUpdate()...");
        super.saveOrUpdate(corMertBankAcnt);
    }

    public CorMertBankAcnt findCorMertBankAcnt(String merchantCode) {

        logger.info("entering dao::CorMertBankAcntDaoImpl.find()...");
        String hql = " from CorMertBankAcnt where isValid ='" + CorConstants.DATA_DICT__IS_VALID + "' and merchantCode = '"
                + merchantCode + "'";
        List<Object> list = findByHql(hql, null, null);
        if (list != null && list.size() > 0) {
            return (CorMertBankAcnt) list.get(0);
        }
        return null;
    }
    @Override
    public CorMertBankAcnt findCorMertBankAcntInfo(CorMertBankAcnt corMertBankAcnt){
        String custCode = corMertBankAcnt.getCustCode();
        String merchantCode = corMertBankAcnt.getMerchantCode();
        StringBuffer sb = new StringBuffer(
                "select distinct a.ID id, "
                + " a.BANK_ACNT_CODE bankAcntCode,a.CUST_CODE custCode,a.MERCHANT_CODE merchantCode,"
                + " a.BANK_CUST_NAME bankCustName,a.CERT_TYPE certType,a.CERT_NUM certNum,"
                + " a.BANK_NAME bankName,a.BANK_CODE bankCode,a.BANK_BRANCH_NAME bankBranchName,"
                + " a.BANK_BRANCH_CODE bankBranchCode,a.BANK_CARD_NUM bankCardNum,a.CURRENCY currency,"
                + " a.ACNT_TYPE acntType,c.COUNTRY_NAME country,c.PROVINCE_NAME provience,c.CITY_NAME city "
                + " from T_COR_MERT_BANK_ACNT a "
                + " left join T_COR_CITY  c on a.COUNTRY=c.COUNTRY_CODE "
                + " and a.PROVIENCE = c.PROVINCE_CODE and a.CITY=c.CITY_CODE "
                + " where  a.is_Valid ='" + CorConstants.DATA_DICT__IS_VALID + "'"
        );
        if (StringUtils.isNotEmpty(custCode)) {
            sb.append(" and a.cust_code  = '" + custCode + "' ");
        }
        if (StringUtils.isNotEmpty(merchantCode)) {
            sb.append(" and a.MERCHANT_CODE = '" + merchantCode + "' ");
        }
        List<ColumnType> scallarList = new ArrayList<ColumnType>();
        scallarList.add(new ColumnType("id", Hibernate.STRING));
        scallarList.add(new ColumnType("bankAcntCode", Hibernate.STRING));
        scallarList.add(new ColumnType("custCode", Hibernate.STRING));
        scallarList.add(new ColumnType("merchantCode", Hibernate.STRING));
        scallarList.add(new ColumnType("bankCustName", Hibernate.STRING));
        scallarList.add(new ColumnType("certType", Hibernate.STRING));
        scallarList.add(new ColumnType("certNum", Hibernate.STRING));
        scallarList.add(new ColumnType("bankName", Hibernate.STRING));
        scallarList.add(new ColumnType("bankCode", Hibernate.STRING));
        scallarList.add(new ColumnType("bankBranchName", Hibernate.STRING));
        scallarList.add(new ColumnType("bankBranchCode", Hibernate.STRING));
        scallarList.add(new ColumnType("bankCardNum", Hibernate.STRING));
        scallarList.add(new ColumnType("currency", Hibernate.STRING));
        scallarList.add(new ColumnType("acntType", Hibernate.STRING));
        scallarList.add(new ColumnType("country", Hibernate.STRING));
        scallarList.add(new ColumnType("provience", Hibernate.STRING));
        scallarList.add(new ColumnType("city", Hibernate.STRING));
        List<CorMertBankAcnt> lists = findBySql(sb.toString(), null, null, scallarList, CorMertBankAcnt.class);
        if (lists != null && lists.size() > 0) {
            return lists.get(0);
        }
        return null;
    }

	@Override
	public CorMertBankAcnt getDefault(String merchantCode) {
		logger.info("enter into findCorMertBankAcntByPage.getDefault...");
		String hqlString = "from CorMertBankAcnt where merchantCode = ? and isDefault = ? and isValid = ?";
		List<Object> args = new LinkedList<>();
		args.add(merchantCode);
		// 待添加数据字典，01 是，02 否
		args.add("01");
		args.add(CorConstants.DATA_DICT__IS_VALID);
		List<CorMertBankAcnt> list = super.find(hqlString, args);
		if(list != null && list.size() > 0){
			logger.info("获取到默认银行卡，卡号为：" + list.get(0).getBankCardNum() + ",户名为：" + list.get(0).getBankCustName());
			return list.get(0);
		}
		return null;
	}

    @Override
    public CorMertBankAcnt getCountryByMerChantCode(String merchantCode) {
        logger.info("===[getCountryByMerChantCode]===开始执行....");
        String hql = " from CorMertBankAcnt where merchantCode = '" + merchantCode + "'";
        List<CorMertBankAcnt> list = super.findByHql(hql, null, null);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
