/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.biz;

import java.util.List;

import com.ibs.core.module.customer.dto.CustCompanyDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICustCompanyBiz {
    /**
     * 分页查询页面所需数据 Creator : Spole Date : 2016年12月14日 Title : ICustCompanyBiz
     * return_type : IPage<CustCompanyDto>
     */
    public IPage<CustCompanyDto> findCustCompanyDtosBySql(QueryPage queryPage, CustCompanyDto custCompanyCondition);

    /**
     * 根据id 获得 该条记录信息 Creator : Spole Date : 2016年12月14日 Title : ICustCompanyBiz
     * return_type : CustCompanyDto
     */
    public CustCompanyDto getCustCompanyDtoById(String id);

    /**
     * 获得指定条件的 区域信息 Creator : Spole Date : 2016年12月14日 Title : ICustCompanyBiz
     * return_type : List<OptionObjectPair>
     */
    public List<OptionObjectPair> getAreasInfo(String type, String code);

    /**
     * 进行操作【新增，修改，审核，查看】 Creator : Spole Date : 2016年12月14日 Title :
     * ICustCompanyBiz return_type : void
     */
    public void opera(CustCompanyDto custCompanyDto);

    /**
     * 
     * Creator : Spole Date : 2016年12月15日 Title : ICustCompanyBiz return_type :
     * boolean
     */
    public boolean activeAccount(CustCompanyDto custCompanyDto);

    /**
     * 
     * Creator : Spole Date : 2016年12月16日 Title : ICustCompanyBiz return_type :
     * boolean
     */
    public boolean existsInCustInfo(CustCompanyDto custCompanyDto);

}
