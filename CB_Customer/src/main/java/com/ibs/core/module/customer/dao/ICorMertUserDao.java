/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.customer.dao;

import com.ibs.core.module.customer.domain.CorMertUser;
import com.ibs.core.module.customer.dto.CorMertUserDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_MERT_USER
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorMertUserDao {

    public IPage<CorMertUserDto> findCorMertUserByPage(QueryPage queryPage, CorMertUserDto corMertUserDto);

    public void saveOrUpdate(CorMertUser corMertUser);

    public CorMertUser load(String id);

    public CorMertUser getUserByUserCode(String userCode);

}
