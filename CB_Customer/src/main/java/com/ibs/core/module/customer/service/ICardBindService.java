package com.ibs.core.module.customer.service;

import java.util.List;

import com.ibs.core.module.customer.domain.CorCardbindInfo;
import com.ibs.core.module.customer.dto.CardBindDto;
import com.ibs.portal.framework.server.exception.BizCheckedException;

public interface ICardBindService {
    /**
     * 
     * <p>绑卡查询 
     * <p>必输项【bankCardNum-银行卡号】 
     * <p>true ： 已绑定   --- 不可绑定该卡
     * <p>false : 未绑定  ---可以绑定改卡和对应商户
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月9日
     * <p>Title			: 	ICardBindService
     * <p>return_type	:	boolean     
     */
    public boolean queryCardBindStatus(CardBindDto cardBindDto) throws BizCheckedException;
    /**
     * 
     * <p> 根据银行卡查询指定银行卡信息
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月12日
     * <p>Title			: 	ICardBindService
     * <p>return_type	:	CorCardbindInfo
     */
    public CorCardbindInfo queryCardBindInfo(String bankCardNum) throws BizCheckedException;

    /**
     * 
     * <p> 绑卡
     * <p> 必输项【merchantCode-商户号,bankCardNum-银行卡号,mertCustCode-电商系统持卡人会员号 ***】
     * <p> return ： String - token 唯一标识
     * <p>  null-绑卡失败；否则绑卡成功
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月10日
     * <p>Title			: 	ICardBindService
     * <p>return_type	:	String
     */
    public String bindBankCard(CardBindDto cardBindDto) throws BizCheckedException;

    /**
     * 
     * <p> 解绑卡
     * <p> 必输项【token 唯一标识,bankCardNum-银行卡号】
     * <p> true ：解绑成功；false : 解绑失败
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月10日
     * <p>Title			: 	ICardBindService
     * <p>return_type	:	boolean
     */
    public boolean unBindBankCard(CardBindDto cardBindDto) throws BizCheckedException;

    /**
     * 
     * <p>根据传入的指定(商户号||电商系统持卡人会员号||持卡人唯一标识)查询该商户下所有绑定（已绑定）的银行卡。   []
     * <p>必输项【merchantCode-商户号||mertCustCode-电商系统持卡人会员号|| token -持卡人唯一标识）】 
     * <p> 1.如果系统中不存在该商户，抛异常；2.该商户存在，不存在绑卡信息，返回null 3，返回绑卡信息
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月5日
     * <p>Title		: 	ICardBindService
     * <p>return_type	:	List<CorCardbindInfo>
     */
    public List<CorCardbindInfo> getCardbindInfoListByMerchantCode(CardBindDto cardBindDto) throws BizCheckedException;
    
    /**
     * 
     * <p>根据 商户号 和 银行卡号 查询该（商户号+银行卡）信息是否存在绑卡信息表（T_COR_CARDBIND_INFO）中
     * <p>必输项【merchantCode-商户号,bankCardNum-银行卡号,mertCustCode-电商系统持卡人会员号***】 
     * <p>不存在，返回null;
     * <p>存在，判断T_COR_CARDBIND_MERT_CUST 表中是否存在已绑定的信息， 
     * <p>          存在，返回String - token 唯一标识
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月7日
     * <p>Title		: 	ICardBindService
     * <p>return_type	:	boolean
     */
     public String bindCardCheck(CardBindDto cardBindDto)throws BizCheckedException;

    
}
