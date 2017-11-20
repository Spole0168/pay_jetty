package com.ibs.core.module.mdmbasedata.service;

import java.util.List;

import com.ibs.core.module.mdmbasedata.domain.PblBankDict;

/**
 * 银行列表对外服务类接口
 * 
 * @author 
 * 
 */
public interface IPblBankDictService {
   
	/**
	 * 获取所有总行信息
	 * @return
	 */
    List<PblBankDict> getBankList();
    
    /**
     * 指定总行编码获取总行下面所有的分行信息
     * @param bankCode
     * @return
     */
    List<PblBankDict> getBankBranchList(String bankCode);
    
    /**
     * 根据分行编码获取对应的银行对象
     * @param bankBranchCode
     * @return
     */
    PblBankDict getByBankBranchCode(String bankBranchCode);
}
