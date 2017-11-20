/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.mdmbasedata.dao.impl;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.type.StringType;

import com.ibs.core.module.mdmbasedata.dao.IPblBankDictDao;
import com.ibs.core.module.mdmbasedata.domain.PblBankDict;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.exception.BizException;
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
		PblBankDict result = super.loadBy("bankBranchCode", bankBranchCode);
		if(result == null){
			throw new BizException("根据分行编码：" + bankBranchCode + "没有查到银行分行信息");
		}
		return result;
		
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

	@Override
	public List<PblBankDict> findBankList() {
		logger.info("enter into PblBankDictDaoImpl.findBankList...");
		QueryPage queryPage = new QueryPage(1000, 0);
		String sql = "select distinct BANK_NAME as bankName,BANK_CODE as bankCode from T_PBL_BANK_DICT";
		queryPage.setSqlString(sql);
		StringType stringType = new StringType();
		queryPage.addScalar("bankName",stringType);
		queryPage.addScalar("bankCode",stringType);
		IPage<PblBankDict> page = findPageBySql(queryPage, PblBankDict.class);
		return (List<PblBankDict>) page.getRows();
	}

	@Override
	public List<PblBankDict> findBankBranchList(String bankCode) {
		logger.info("enter into PblBankDictDaoImpl.findBankBranchList...");
		String hqlString = "from PblBankDict a where a.bankCode = ? order by a.bankBranchCode";
		List<Object> args = new LinkedList<>();
		args.add(bankCode);
//		List<PblBankDict> result = super.findByHql(hqlString, args, null);
//		if(result == null || result.size() < 1){
//			throw new BizException("根据传入的银行编码：" + bankCode + "没有获取到相关分行信息");
//		}
		return super.findByHql(hqlString, args, null);
	}
	
}
