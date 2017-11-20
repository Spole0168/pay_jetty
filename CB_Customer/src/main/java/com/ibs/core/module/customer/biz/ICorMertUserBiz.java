package com.ibs.core.module.customer.biz;

import com.ibs.core.module.customer.domain.CorMertUser;
import com.ibs.core.module.customer.dto.CorMertUserDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface ICorMertUserBiz {

    public IPage<CorMertUserDto> findCorMertUserByPage(QueryPage queryPage, CorMertUserDto corMertUserDto);

    public CorMertUser getCorMertUserById(String id);

    public CorMertUser saveCorMertUser(CorMertUser object);

    public CorMertUser updateCorMertUser(CorMertUser object);
    
    public CorMertUser getUserByUserCode(String userCode);

}
