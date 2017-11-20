package com.ibs.core.module.mdmbasedata.biz.impl;

import java.util.List;

import com.ibs.core.module.mdmbasedata.biz.IPblBankDictBiz;
import com.ibs.core.module.mdmbasedata.dao.IPblBankDictDao;
import com.ibs.core.module.mdmbasedata.domain.PblBankDict;

public class PblBankDictBizImpl implements IPblBankDictBiz {

	private IPblBankDictDao pblBankDictDao;
	
	@Override
	public List<PblBankDict> getBankList() {
		
		List<PblBankDict> list = pblBankDictDao.findBankList();
		
		return list;
	}

	public IPblBankDictDao getPblBankDictDao() {
		return pblBankDictDao;
	}

	public void setPblBankDictDao(IPblBankDictDao pblBankDictDao) {
		this.pblBankDictDao = pblBankDictDao;
	}

}
