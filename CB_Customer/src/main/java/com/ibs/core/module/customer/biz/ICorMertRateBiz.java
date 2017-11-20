/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.biz;

import com.ibs.core.module.customer.domain.CorMertRate;
import com.ibs.core.module.customer.dto.CorMertRateDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_MERT_RATE
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorMertRateBiz {

    public IPage<CorMertRate> findCorMertRateByPage(QueryPage queryPage);

    public IPage<CorMertRateDto> findCorMertRateDtoBySql(QueryPage queryPage, CorMertRateDto corMertRateDto);

    public CorMertRateDto getCorMertRateDtoById(String id);

    public void opera(CorMertRateDto corMertRateDto);

    public String getMerchantName(String id);

    public boolean existsInFeeRateInfo(CorMertRateDto corMertRateDto);
}
