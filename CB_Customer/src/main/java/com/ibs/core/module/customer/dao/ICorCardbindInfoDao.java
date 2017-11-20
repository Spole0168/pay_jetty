/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.customer.dao;

import java.util.List;

import com.ibs.core.module.customer.domain.CorCardbindInfo;
import com.ibs.core.module.customer.dto.CardBindDto;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CARDBIND_INFO
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorCardbindInfoDao {

    public List<CorCardbindInfo> getCorCardbindInfoList(CardBindDto cardBindDto);

    public void saveOrUpdate(CorCardbindInfo corCardbindInfo);

    public CorCardbindInfo load(String id);

}
