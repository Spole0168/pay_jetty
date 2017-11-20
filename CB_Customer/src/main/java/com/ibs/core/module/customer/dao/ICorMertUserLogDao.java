/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.customer.dao;

import com.ibs.core.module.customer.domain.CorMertUserLog;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_MERT_USER_LOG
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorMertUserLogDao {

	public IPage<CorMertUserLog> findCorMertUserLogByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CorMertUserLog corMertUserLog);

	public CorMertUserLog load(String id);
	
}
