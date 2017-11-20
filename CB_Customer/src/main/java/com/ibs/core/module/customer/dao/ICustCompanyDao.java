/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.customer.dao;

import java.util.List;

import com.ibs.core.module.customer.domain.CorCustCompany;
import com.ibs.core.module.customer.dto.CustCompanyDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/**
 * Creator : Spole Date : 2016年12月14日
 */
public interface ICustCompanyDao {
    /**
     * 分页查询dto数据展示页面 Creator : Spole Date : 2016年12月14日 Title : ICustCompanyDao
     * return_type : IPage<CustCompanyDto>
     */
    public IPage<CustCompanyDto> findCustCompanyDtosBySql(QueryPage queryPage, CustCompanyDto corCustCompanyCondition);

    /**
     * 更新或者新增会员操作 Creator : Spole Date : 2016年12月14日 Title : ICustCompanyDao
     * return_type : void
     */
    public void saveOrUpdate(CorCustCompany corCustCompany);

    /**
     * 根据id获得指定的会员信息 Creator : Spole Date : 2016年12月14日 Title : ICustCompanyDao
     * return_type : CorCustCompany
     */
    public CorCustCompany loadById(String id);

    /**
     * 根据指定的条件获得对用的会员相关信息 Creator : Spole Date : 2016年12月14日 Title :
     * ICustCompanyDao return_type : CustCompanyDto
     */
    public CustCompanyDto getCustCompanyDtoByCondition(CustCompanyDto custCompanyDtoCondition);

    /**
     * 根据指定条件获得满足条件的会员信息 Creator : Spole Date : 2016年12月14日 Title :
     * ICustCompanyDao return_type : CorCustCompany
     */
    public List<CorCustCompany> getCorCustCompanyByConditionAND(CorCustCompany corCustCompany);

    /**
     * 查询所有符合任何一个条件的 数据 Creator : Spole Date : 2016年12月16日 Title :
     * ICustCompanyDao return_type : List<CorCustCompany>
     */
    public IPage<CustCompanyDto> getCorCustCompanyByConditionOR(CustCompanyDto custCompanyDtoCondition);

}
