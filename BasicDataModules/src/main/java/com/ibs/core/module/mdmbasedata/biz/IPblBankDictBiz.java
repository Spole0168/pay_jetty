package com.ibs.core.module.mdmbasedata.biz;

import java.util.List;

import com.ibs.common.module.frameworkimpl.security.dto.UserSimpleInfo;
import com.ibs.core.module.mdmbasedata.domain.PblBankDict;

public interface IPblBankDictBiz {
	
	public List<PblBankDict> getBankList();
}
