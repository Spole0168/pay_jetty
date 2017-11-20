/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.customer.dao.impl;

import com.ibs.core.module.customer.dao.ICorMertUserLogDao;
import com.ibs.core.module.customer.domain.CorMertUserLog;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_MERT_USER_LOG
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorMertUserLogDaoImpl extends BaseEntityDao<CorMertUserLog> implements
		ICorMertUserLogDao {

	public IPage<CorMertUserLog> findCorMertUserLogByPage(QueryPage queryPage) {
		logger.info("entering action::CorMertUserLogDaoImpl.findCorMertUserLogByPage()...");
		return super.findPageBy(queryPage);
	}

	public CorMertUserLog load(String id) {
		logger.info("entering action::CorMertUserLogDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(CorMertUserLog corMertUserLog) {
		logger.info("entering action::CorMertUserLogDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(corMertUserLog);
	}

}
