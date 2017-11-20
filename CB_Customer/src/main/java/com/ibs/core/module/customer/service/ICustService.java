package com.ibs.core.module.customer.service;

import com.ibs.core.module.customer.domain.CorFuzzyCust;
import com.ibs.core.module.customer.dto.CustCompanyDto;
import com.ibs.core.module.customer.dto.CustPersonalDto;

public interface ICustService {
    /**
     * 新增潜在用户
     * 
     * @param corFuzzyCust 潜在用户
     * @return
     */
    boolean saveCorFuzzyCust(CorFuzzyCust corFuzzyCust);

    /**
     * 新增会员用户
     * 
     * @param corCust 会员用户
     * @return
     */
    boolean saveCustPersonal(CustPersonalDto custPersonalDto);

    /**
     * 查询会员联系人手机号和邮箱
     * 
     * @param custCode 会员号
     * @return 会员联系人手机号和邮箱
     * <p>返回格式为：手机号#邮箱
     * <p>返回数据：13888888888#test@ibm.com
     */
    String queryCustContactInfo(String custCode);

    /**
     * 
     * <p>
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2016年12月26日
     * <p>Title		: 	ICustService
     * <p>return_type	:	CustCompanyDto
     */
    CustCompanyDto queryCustDtoInfo(CustCompanyDto cdto);
    /**
     * <p> 对个人会员进行限制操作： 【止入||止出||取消止入||取消止出】
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2017年2月9日
     * <p>Title			: 	ICustService
     * <p>return_type	:	String
     */
    public boolean personalAccountLimit(String id,String personalAccountOpera);
}
