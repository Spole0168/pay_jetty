/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.customer.dao;

import java.util.List;

import com.ibs.core.module.customer.domain.CorCustPersonal;
import com.ibs.core.module.customer.dto.CustPersonalDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
/**
 * 
 * Creator		:	Spole
 * Date			:	2017年1月17日
 */
public interface ICustPersonalDao {
    /**
     * <p>  分页查询并显示个人会员信息
     * <p>
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月17日
     * <p>Title			: 	ICorCustPersonalDao
     * <p>return_type	:	IPage<CustPersonalDto>
     */
    public IPage<CustPersonalDto> findCustPersonalDtosBySql(QueryPage queryPage, CustPersonalDto custPersonalCondition);
	/**
	 * <p>查询指定条件的个人会员相关信息
	 * <p>
	 * <p>
	 * <p>Creator		:	Spole
	 * <p>Date			:	2017年1月17日
	 * <p>Title			: 	ICorCustPersonalDao
	 * <p>return_type	:	CustPersonalDto
	 */
    public CustPersonalDto getCustPersonalDtoByCondition(CustPersonalDto custPersonalCondition);
    /**
     * <p> 新增或者修改个人会员信息
     * <p>
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月17日
     * <p>Title			: 	ICorCustPersonalDao
     * <p>return_type	:	void
     */
    public void saveOrUpdate(CorCustPersonal corCustPersonal);
    /**
     * <p>
     * <p>
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月17日
     * <p>Title			: 	ICorCustPersonalDao
     * <p>return_type	:	CorCustPersonal
     */
	public CorCustPersonal load(String id);
	/**
	 * <p> 展示符合条件的所有个人会员信息
	 * <p>
	 * <p>
	 * <p>Creator		:	Spole
	 * <p>Date			:	2017年1月17日
	 * <p>Title			: 	ICorCustPersonalDao
	 * <p>return_type	:	List<CorCustPersonal>
	 */
	public List<CorCustPersonal> getCorCustPersonalsList(CustPersonalDto custPersonalDto);
	
}
