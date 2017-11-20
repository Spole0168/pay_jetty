/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.customer.dao;

import com.ibs.core.module.customer.domain.CorFuzzyCust;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_FUZZY_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorFuzzyCustDao {

	public IPage<CorFuzzyCust> findCorFuzzyCustByPage(QueryPage queryPage);
	
	public void saveOrUpdate(CorFuzzyCust corFuzzyCust);

	public CorFuzzyCust load(String id);
	
}
