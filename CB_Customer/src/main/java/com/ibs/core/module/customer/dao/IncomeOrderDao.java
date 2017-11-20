/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.customer.dao;

import com.ibs.core.module.customer.domain.IncomeOrder;
import com.ibs.core.module.customer.dto.IncomeOrderDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_INCOME_ORDER
 * @modify		: your comments goes here (author,date,reason).
 */
public interface IncomeOrderDao {
    //查询手动入账
	public IPage<IncomeOrderDto> findCorIncomeOrderByPage(QueryPage queryPage,IncomeOrderDto incomeOrderDtoCondition);
	
	public IncomeOrder load(String id);
	
	 //手动入账
    IncomeOrderDto getInCorAccountDtosByCondition(IncomeOrderDto incomeOrderDtoCondition);
	
}
