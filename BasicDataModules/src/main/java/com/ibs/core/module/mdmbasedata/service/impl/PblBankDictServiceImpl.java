package com.ibs.core.module.mdmbasedata.service.impl;

import java.util.List;

import com.ibs.core.module.mdmbasedata.dao.IPblBankDictDao;
import com.ibs.core.module.mdmbasedata.domain.PblBankDict;
import com.ibs.core.module.mdmbasedata.service.IPblBankDictService;
import com.ibs.portal.framework.server.service.BaseService;

public class PblBankDictServiceImpl extends BaseService implements IPblBankDictService {

	private IPblBankDictDao pblBankDictDao;
	
	@Override
	public List<PblBankDict> getBankList() {
		logger.info("enter into PblBankDictServiceImpl.getBankList");
		return pblBankDictDao.findBankList();
	}
	
	@Override
	public List<PblBankDict> getBankBranchList(String bankCode) {
		logger.info("enter into PblBankDictServiceImpl.getBankBranchList");
		return pblBankDictDao.findBankBranchList(bankCode);
	}
	
	@Override
	public PblBankDict getByBankBranchCode(String bankBranchCode) {
		logger.info("enter into PblBankDictServiceImpl.getByBankBranchCode");
		return pblBankDictDao.findByBankBranchCode(bankBranchCode);
	}

	public IPblBankDictDao getPblBankDictDao() {
		return pblBankDictDao;
	}

	public void setPblBankDictDao(IPblBankDictDao pblBankDictDao) {
		this.pblBankDictDao = pblBankDictDao;
	}

}
