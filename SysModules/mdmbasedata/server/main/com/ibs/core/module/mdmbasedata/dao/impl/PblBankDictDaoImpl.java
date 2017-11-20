/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.mdmbasedata.dao.impl;



import java.util.ArrayList;
import java.util.List;

import com.ibs.core.module.mdmbasedata.common.Constants;
import com.ibs.core.module.mdmbasedata.dao.IPblBankDictDao;
import com.ibs.core.module.mdmbasedata.domain.PblBankDict;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_PBL_BANK_DICT
 * @modify		: your comments goes here (author,date,reason).
 */
public class PblBankDictDaoImpl extends BaseEntityDao<PblBankDict> implements
		IPblBankDictDao {

	public IPage<PblBankDict> findPblBankDictByPage(QueryPage queryPage) {
		logger.info("entering action::PblBankDictDaoImpl.findPblBankDictByPage()...");
		return super.findPageBy(queryPage);
	}

	public PblBankDict load(String id) {
		logger.info("entering action::PblBankDictDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(PblBankDict pblBankDict) {
		logger.info("entering action::PblBankDictDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(pblBankDict);
	}
	
	public PblBankDict findByBankBranchCode(String bankBranchCode){
		logger.info("entering action::PblBankDictDaoImpl.findByBankBranchCode()...");
		return super.loadBy("bankBranchCode", bankBranchCode);
		/*Criteria criteria = getSession().createCriteria(PblBankDict.class);
		criteria.add(Restrictions.eq("bankBranchCode", bankBranchCode));
		return (criteria.list().size()>0)?(PblBankDict)criteria.list().get(0):null;*/
		
	}

	// qiyongping add 2016-09-12
	public List<PblBankDict> findByPblBankDict(PblBankDict pblBankDict) {
		logger.info("entering action::PblBankDictDaoImpl.findByPblBankDict()...");
		return super.findBy(pblBankDict);
	}
	
	public PblBankDict findByCityAndProvince(String cityCode,String provinceCode){
		
		String  hql = "select pbd  from PblBankDict pbd where  "
				+ "pbd.cityCode=? and pbd.provienceCode=? ";
		List<Object>  list = new ArrayList<Object>();
 
		list.add(cityCode);
		list.add(provinceCode);
	 
		List<PblBankDict> pblBankDictList = super.find(hql, list);
		
		logger.info("exit action::PblBankDictDaoImpl.findByPblBankDict()...");
		
		return (pblBankDictList == null || pblBankDictList.isEmpty()) ? null : pblBankDictList.get(0);
	}
}
