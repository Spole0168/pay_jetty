package com.ibs.core.module.customer.service;

import java.util.List;
import java.util.Map;

import com.ibs.core.module.customer.domain.CorMertBankAcnt;
import com.ibs.core.module.customer.domain.CorMertRate;
import com.ibs.core.module.customer.domain.CorMertRateResult;
import com.ibs.core.module.customer.domain.Merchant;
import com.ibs.core.module.customer.dto.CorMertRateDto;
import com.ibs.core.module.customer.dto.MerchantDto;

public interface IMerchantService {

    /**
     * 查询费率
     * 
     * @param merchantCode 商户号
     * @param productCode 产品编码
     * @param cardType 卡类型
     * @param cardProperty 卡属性
     * @param settType 结算方式
     * @return 费率信息：固定费率或者费率范围
     * 
     *         <p>
     *         返回对象包括：
     *         <p>
     *         serviceFeeType 计费类型 固定费率or比例计费
     *         <p>
     *         fixedFeeValue 固定费率值
     *         <p>
     *         rateFeeValue 比例费率值
     *         <p>
     *         lowFeeValue 最低费率
     *         <p>
     *         hightFeeValue 最高费率
     */
    CorMertRateResult queryMertRate(String merchantCode, String productCode, String cardType, String cardProperty,
            String settType);

    /**
     * 
     * 查询商户止入止出状态以及对应的会员号
     * <p>
     * 返回数据格式：会员号#是否止入#是否止出
     * <p>
     * 返回数据样例: C2016111700001#01#02
     * <p>
     * 止入止出状态中 01代表是 02代表否
     * 
     * @param merchantCode 商户号
     * @return 商户对应的会员号以及止入止出状态以的拼接字符串
     */
    String queryMertStatus(String merchantCode);

    /**
     * 查询商户名称
     * 
     * @param merchantCode 商户号
     * @return Map [商户名，客户名，客户号]
     */
    Map<String, String> queryMerchantInfo(String merchantCode);

    /**
     * 查询商户名称
     * 
     * @param merchantCode 商户号
     * @return 商户名称
     */
    String queryMertName(String merchantCode);

    /**
     * 查询所有商户信息
     * 
     * @param merchantCode 商户号
     * @return List<Merchant> 商户列表
     */
    List<Merchant> queryAllMerchantInfo();
    
    /**
     * 查询商户对象
     * 
     * @param merchantCode 商户号
     * @return 商户对象
     */
    Merchant queryMerchant(String merchantCode);
    
    /**
     * 
     * <p>
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2016年12月26日
     * <p>Title		: 	IMerchantService
     * <p>return_type	:	Merchant
     */
    MerchantDto queryMerDtoInfo(MerchantDto md);
    
    /**
     * 查询商户联系人手机号
     * 
     * @param merchantCode 商户号
     * @return 商户联系人手机号
     */
    String queryMertPhoneNum(String merchantCode);
    /**
     * 查询商户银行信息
     * @param corMertBankAcnt
     * @return
     */
    CorMertBankAcnt findCorMertBankAcntInfo(CorMertBankAcnt corMertBankAcnt);
    /**
     * 查询商户费率集合
     * @param queryPage
     * @param corMertRateDto
     * @return
     */
     List<CorMertRate> getCorMertRateDto(CorMertRateDto corMertRate);

	CorMertBankAcnt getDefaultBankAcnt(String merchantCode);
	/**
	 * 根据商户查询商户银行账户表
	 * @param merchantCode
	 * @return
	 */
	CorMertBankAcnt getCountryByMerChantCode(String merchantCode);
	
	
	
}
