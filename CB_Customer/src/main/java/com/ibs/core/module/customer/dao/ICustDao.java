package com.ibs.core.module.customer.dao;

import java.util.List;

import com.ibs.core.module.customer.domain.CorCust;
import com.ibs.core.module.customer.domain.CorCustPersonal;
import com.ibs.core.module.customer.dto.CustPersonalDto;

public interface ICustDao {

    CorCust getCustByCustCode(String custCode);

    CorCust getCustByLocalName(String localName);

    void saveOrUpdate(CorCust cust);

    /**
     * 根据会员编号和状态查询会员记录
     * 
     * @param custCode
     * @param stauts
     * @return list of CorCust
     */
    public List<CorCust> findListByCustCode(String custCode, String status, String isValid);

    CorCust getCustById(String id);
    /**
     * 根据指定条件查询出符合条件的所有数据。
     * <p>
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2016年12月22日
     * <p>Title		: 	ICustDao
     * <p>return_type	:	List<CorCust>
     */
    public List<CorCust> getCorCustsList(CorCust corCust);
    
    
}
