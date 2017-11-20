package com.ibs.core.module.customer.biz;

import com.ibs.core.module.basefunc.domain.City;
import com.ibs.core.module.customer.dto.CustPersonalDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface ICustPersonalBiz {
    /**
     * 
     * <p> 分页查询  个人会员相关信息
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月17日
     * <p>Title			: 	ICorCustPersonalBiz
     * <p>return_type	:	IPage<CustPersonalDto>
     */
    public IPage<CustPersonalDto> findCustPersonalDtosBySql(QueryPage queryPage, CustPersonalDto custPersonalCondition);
    /**
     * 
     * <p> 查询指定条件的个人会员 详细信息
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月17日
     * <p>Title			: 	ICorCustPersonalBiz
     * <p>return_type	:	CustPersonalDto
     */
    public CustPersonalDto getCustPersonalDtoById(String id);
    /**
     * <p> 根据cityCode 获得相关的城市信息
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月17日
     * <p>Title			: 	ICorCustPersonalBiz
     * <p>return_type	:	City
     */
    public City getCityInfoByCityCode(String cityCode);
    /**
     * 
     * <p> 激活个人会员的账户
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2017年2月7日
     * <p>Title			: 	ICustPersonalBiz
     * <p>return_type	:	boolean
     */
    public boolean activePersonalAccount(CustPersonalDto custPersonalDto);
}
