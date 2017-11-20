/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.customer.dao.impl;

import com.ibs.core.module.customer.dao.ICorFuzzyCustDao;
import com.ibs.core.module.customer.domain.CorFuzzyCust;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_FUZZY_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorFuzzyCustDaoImpl extends BaseEntityDao<CorFuzzyCust> implements
		ICorFuzzyCustDao {

	public IPage<CorFuzzyCust> findCorFuzzyCustByPage(QueryPage queryPage) {
		logger.info("entering action::CorFuzzyCustDaoImpl.findCorFuzzyCustByPage()...");
		return super.findPageBy(queryPage);
	}

	public CorFuzzyCust load(String id) {
		logger.info("entering action::CorFuzzyCustDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CorFuzzyCust corFuzzyCust) {
		logger.info("entering action::CorFuzzyCustDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(corFuzzyCust);
	}

}
