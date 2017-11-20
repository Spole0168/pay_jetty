package com.ibs.core.module.customer.dao;

import java.util.List;

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
public interface ICorMertRateDao {
    public IPage<CorMertRate> findCorMertRateByPage(QueryPage queryPage);

    public IPage<CorMertRateDto> findCorMertRateDtoBySql(QueryPage queryPage, CorMertRateDto corMertRateDto);

    public CorMertRateDto getCorMertRateDtoByCondition(CorMertRateDto corMertRateDto);

    public void saveOrUpdate(CorMertRate corMertRate);

    public CorMertRate load(String id);

    public List<CorMertRate> getCorMertRateByCondition(CorMertRate corMertRate);

    public List<CorMertRate> getCorMertRateDtoByConditionAND(CorMertRateDto corMertRateDto);

}
