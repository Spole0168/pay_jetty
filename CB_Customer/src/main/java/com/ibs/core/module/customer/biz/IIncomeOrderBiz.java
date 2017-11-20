/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.biz;

import com.ibs.core.module.customer.dto.IncomeOrderDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;


/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_INCOME_ORDER
 * @modify		: your comments goes here (author,date,reason).
 */
public interface IIncomeOrderBiz {
    /**
     *   
     * <p>
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2016年12月23日
     * <p>Title		: 	IincomeOrderBiz
     * <p>return_type	:	IPage<IncomeOrderDto>
     */
    public IPage<IncomeOrderDto> findCorIncomeOrderByPage(QueryPage queryPage,IncomeOrderDto incomeOrderDtoCondition);
    /**
     * 
     * <p>
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2016年12月23日
     * <p>Title		: 	IincomeOrderBiz
     * <p>return_type	:	IncomeOrderDto
     */
    public IncomeOrderDto getInCorAccountDtosByCondition(IncomeOrderDto incomeOrderDtoCondition);
}
