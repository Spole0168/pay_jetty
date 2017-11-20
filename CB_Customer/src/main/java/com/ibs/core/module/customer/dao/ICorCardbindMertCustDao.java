/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.customer.dao;

import java.util.List;

import com.ibs.core.module.customer.domain.CorCardbindMertCust;
import com.ibs.core.module.customer.dto.CardBindDto;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CARDBIND_MERT_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorCardbindMertCustDao {

    public List<CorCardbindMertCust> getCorCardbindMertCustList(CardBindDto cardBindDto);

    public void saveOrUpdate(CorCardbindMertCust corCardbindMertCust);

    public CorCardbindMertCust load(String id);

}
