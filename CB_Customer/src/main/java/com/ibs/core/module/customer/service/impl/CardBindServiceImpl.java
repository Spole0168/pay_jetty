package com.ibs.core.module.customer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibs.core.module.basefunc.service.ICodeRuleService;
import com.ibs.core.module.customer.dao.ICorCardbindInfoDao;
import com.ibs.core.module.customer.dao.ICorCardbindMertCustDao;
import com.ibs.core.module.customer.dao.ICustPersonalDao;
import com.ibs.core.module.customer.dao.ICustDao;
import com.ibs.core.module.customer.dao.IMerchantDao;
import com.ibs.core.module.customer.domain.CorCardbindInfo;
import com.ibs.core.module.customer.domain.CorCardbindMertCust;
import com.ibs.core.module.customer.domain.CorCust;
import com.ibs.core.module.customer.domain.CorCustPersonal;
import com.ibs.core.module.customer.dto.CardBindDto;
import com.ibs.core.module.customer.dto.CustPersonalDto;
import com.ibs.core.module.customer.dto.MerchantDto;
import com.ibs.core.module.customer.service.ICardBindService;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.exception.BizCheckedException;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.service.BaseService;
import com.ibs.portal.framework.util.BeanUtils;
import com.ibs.portal.framework.util.StringUtils;

public class CardBindServiceImpl extends BaseService implements ICardBindService {
    private IMerchantDao merchantDao;

    private ICorCardbindInfoDao corCardbindInfoDao;
    private ICorCardbindMertCustDao corCardbindMertCustDao;
    private ICustPersonalDao custPersonalDao;
    private ICustDao custDao;
    
    private ICodeRuleService codeRuleService;
    

    public IMerchantDao getMerchantDao() {
        return merchantDao;
    }

    public void setMerchantDao(IMerchantDao merchantDao) {
        this.merchantDao = merchantDao;
    }


    public ICustPersonalDao getCustPersonalDao() {
        return custPersonalDao;
    }

    public void setCustPersonalDao(ICustPersonalDao custPersonalDao) {
        this.custPersonalDao = custPersonalDao;
    }

    public ICustDao getCustDao() {
        return custDao;
    }

    public void setCustDao(ICustDao custDao) {
        this.custDao = custDao;
    }

    public ICodeRuleService getCodeRuleService() {
        return codeRuleService;
    }

    public void setCodeRuleService(ICodeRuleService codeRuleService) {
        this.codeRuleService = codeRuleService;
    }

    public ICorCardbindInfoDao getCorCardbindInfoDao() {
        return corCardbindInfoDao;
    }

    public void setCorCardbindInfoDao(ICorCardbindInfoDao corCardbindInfoDao) {
        this.corCardbindInfoDao = corCardbindInfoDao;
    }

    public ICorCardbindMertCustDao getCorCardbindMertCustDao() {
        return corCardbindMertCustDao;
    }

    public void setCorCardbindMertCustDao(ICorCardbindMertCustDao corCardbindMertCustDao) {
        this.corCardbindMertCustDao = corCardbindMertCustDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibs.core.module.customer.service.ICardBindService#
     * queryCardMerchantcode(com.ibs.core.module.customer.dto.CardBindDto)
     */
    @Override
    public boolean queryCardBindStatus(CardBindDto cardBindDto) throws BizCheckedException {
       logger.info("CardBindServiceImpl.java---queryCardBindStatus(CardBindDto cardBindDto) Paramters="+cardBindDto);
        CardBindDto  queryCardBindDto = new CardBindDto();
        queryCardBindDto.setBankCardNum(cardBindDto.getBankCardNum());
        queryCardBindDto.setStatus(CorConstants.DATA_DICT__CARD_BIND_YES);
        // 查询绑卡信息
        List<CorCardbindInfo> CardbindInfoList = corCardbindInfoDao.getCorCardbindInfoList(queryCardBindDto);
        if (null != CardbindInfoList && CardbindInfoList.size() != 0) {
            logger.info("CardBindServiceImpl.java---queryCardBindStatus(CardBindDto cardBindDto) Result="+ true+"已绑定");
            return true;//已绑定
        }
        return false;//未绑定
    }
    /* (non-Javadoc)
     * @see com.ibs.core.module.customer.service.ICardBindService#queryCardBindInfo(java.lang.String)
     */
    @Override
    public CorCardbindInfo queryCardBindInfo(String bankCardNum) throws BizCheckedException {
        logger.info("CardBindServiceImpl.java---queryCardBindInfo(String bankCardNum) Paramters="+bankCardNum);
        CardBindDto  queryCardBindDto = new CardBindDto();
        queryCardBindDto.setBankCardNum(bankCardNum);
        // 查询绑卡信息
        List<CorCardbindInfo> CardbindInfoList = corCardbindInfoDao.getCorCardbindInfoList(queryCardBindDto);
        if (null != CardbindInfoList && CardbindInfoList.size() != 0) {
            logger.info("CardBindServiceImpl.java---queryCardBindInfo(String bankCardNum) Result="+CardbindInfoList.get(0));
            return CardbindInfoList.get(0);
        }
        return null;//未绑定
    }
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ibs.core.module.customer.service.ICardBindService#bindBankCard(com.
     * ibs.core.module.customer.dto.CardBindDto)
     */
    @Override
    public String bindBankCard(CardBindDto cardBindDto) throws BizCheckedException {
        logger.info("CardBindServiceImpl.java---bindBankCard(CardBindDto cardBindDto) Paramters="+cardBindDto);
        String personalCustCode = null;
        String token = null;
        
        cardBindDto.setCreator(CorConstants.CREATOR);
        cardBindDto.setUpdator(CorConstants.UPDATOR);
        //判断指定的商户号 在系统中是否存在
        MerchantDto mdto = new MerchantDto();
        mdto.setMerchantCode(cardBindDto.getMerchantCode());
        MerchantDto md = merchantDao.getMerchantDetailInfoById(mdto);
        if (null != md) {
            String companyCustCode = md.getCustCode();     //企业会员编号
        } else {
            throw new BizCheckedException("商户号不存在","01");
        }
        
        Date dt = new Date();
        boolean isHasCustPersonal = false;
        // 先检查数据库中是否存在该用户的会员信息， 如果不存在，先建立该用户的个人会员。存在，则继续操作。
        CorCust cc = new CorCust();
        cc.setCertType(cardBindDto.getCertType());
        cc.setCertNum(cardBindDto.getCertNum());
        cc.setLocalName(cardBindDto.getCardholderName());
      //判断该系统中 是否存在该持卡人的个人会员
        List<CorCust> cList = custDao.getCorCustsList(cc);
        isHasCustPersonal = (null==cList?false:(!cList.isEmpty()));
        
        if(!isHasCustPersonal){//不存在该用户的个人会员信息。
            CustPersonalDto custPersonalDto = new CustPersonalDto();
            BeanUtils.copyObjectTypeProperties(custPersonalDto,cardBindDto);
            // 设置数据
            custPersonalDto.setLocalName(cardBindDto.getCardholderName());
            custPersonalDto.setId(null);
            try {
                personalCustCode = codeRuleService.getCorCustPersonalCode();
            } catch (Exception e) {
                logger.info("调用生成客户编码接口出现异常：" + e.getLocalizedMessage());
                return null;
            }
            //开通持卡人的个人会员
            custPersonalDto.setCustCode(personalCustCode);
            custPersonalDto.setCustType(CorConstants.DATA_DICT__CORE_CUST_TYPE_PERSONAL);
            custPersonalDto.setCustStatus(CorConstants.DATA_DICT__CORE_CUST_STATUS_INVALID); //未生效
            custPersonalDto.setRegTime(dt);
            custPersonalDto.setCreateTime(dt);                  
            custPersonalDto.setUpdateTime(dt);                  
            custPersonalDto.setIsValid(CorConstants.DATA_DICT__IS_VALID);  
            
            CorCust cust = new CorCust();
            CorCustPersonal corCustPersonal = new CorCustPersonal();
            
            BeanUtils.copyObjectTypeProperties(cust, custPersonalDto);
            BeanUtils.copyObjectTypeProperties(corCustPersonal, custPersonalDto);
            cust.setStatus(CorConstants.DATA_DICT__CORE_CUST_STATUS_VALID);
            cust.setRealNameLeve(CorConstants.DATA_DICT__CORE_AUTHENTICATION_LEVEL_FIRST); //实名认证一级
            custDao.saveOrUpdate(cust);
            custPersonalDao.saveOrUpdate(corCustPersonal);
            
        }else {
            personalCustCode = cList.get(0).getCustCode();
        }
        
        cardBindDto.setCreateTime(dt);
        cardBindDto.setUpdateTime(dt);
        cardBindDto.setBindTime(dt);
        cardBindDto.setIsValid(CorConstants.DATA_DICT__IS_VALID);
        cardBindDto.setStatus(CorConstants.DATA_DICT__CARD_BIND_YES);
        
        CardBindDto  queryCardBindDto = new CardBindDto();
        queryCardBindDto.setBankCardNum(cardBindDto.getBankCardNum());
        queryCardBindDto.setStatus(CorConstants.DATA_DICT__CARD_BIND_YES);
        // 查询绑卡信息
        List<CorCardbindInfo> CardbindInfoList = corCardbindInfoDao.getCorCardbindInfoList(queryCardBindDto);
        CorCardbindInfo ccbi = new CorCardbindInfo();
        // 绑卡信息表中不存在该卡信息
        if (null == CardbindInfoList || CardbindInfoList.size() == 0) {//绑卡信息中 不存在该卡信息
            BeanUtils.copyObjectTypeProperties(ccbi, cardBindDto);
            ccbi.setCustCode(personalCustCode);
            ccbi.setId(null);
            corCardbindInfoDao.saveOrUpdate(ccbi);
        } else {//存在持卡信息
            personalCustCode = ccbi.getCustCode();
        }
        CardBindDto temp = new CardBindDto();
        temp.setStatus(CorConstants.DATA_DICT__CARD_BIND_YES);  //已绑定 状态
//        temp.setMerchantCode(cardBindDto.getMerchantCode());    //指定商户号
        temp.setMertCustCode(cardBindDto.getMertCustCode());    //电商持卡人会员号
        temp.setCustCode(personalCustCode);                       //
        List<CorCardbindMertCust> CardbindMertCustList = corCardbindMertCustDao.getCorCardbindMertCustList(temp);
        if (null != CardbindMertCustList && CardbindMertCustList.size() != 0) {
            return CardbindMertCustList.get(0).getToken();
        }else{
            CorCardbindMertCust mCust = new CorCardbindMertCust();
            token = StringUtils.getUUID();
            BeanUtils.copyObjectTypeProperties(mCust, cardBindDto);
            mCust.setCustCode(personalCustCode);
            mCust.setToken(token);
            mCust.setId(null);
            mCust.setBankCardNum(null);
            corCardbindMertCustDao.saveOrUpdate(mCust);
        }
        return token;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ibs.core.module.customer.service.ICardBindService#unBindBankCard(com.
     * ibs.core.module.customer.dto.CardBindDto)
     */
    @Override
    public boolean unBindBankCard(CardBindDto cardBindDto) throws BizCheckedException {
        logger.info("CardBindServiceImpl.java---unBindBankCard(CardBindDto cardBindDto) Paramters="+cardBindDto);
        if(null == cardBindDto ||null == cardBindDto.getToken()||null==cardBindDto.getBankCardNum()){
             throw new BizException("参数有误");
        }
        Date dt = new Date();

        CardBindDto temp = new CardBindDto();
        temp.setBankCardNum(cardBindDto.getBankCardNum());
        temp.setStatus(CorConstants.DATA_DICT__CARD_BIND_YES);//状态 --已绑定
        // 查询绑卡信息
        List<CorCardbindInfo> CardbindInfoList = corCardbindInfoDao.getCorCardbindInfoList(temp);
        
        if(null!=CardbindInfoList&&!CardbindInfoList.isEmpty()){
            for (CorCardbindInfo cbi : CardbindInfoList) {
                cbi.setUnbindTime(dt);
                cbi.setUpdateTime(dt);
                cbi.setStatus(CorConstants.DATA_DICT__CARD_BIND_NO);
                corCardbindInfoDao.saveOrUpdate(cbi);
            }
        }
        
        // 查询客户信息  根据 商户号 + 银行卡号   
        temp = new CardBindDto();
        temp.setStatus(CorConstants.DATA_DICT__CARD_BIND_YES);  //已绑定 状态
//        tempp.setBankCardNum(cardBindDto.getBankCardNum());      //指定银行卡号
        temp.setToken(cardBindDto.getToken());
        List<CorCardbindMertCust> CardbindMertCustList = corCardbindMertCustDao.getCorCardbindMertCustList(temp);
        String personalCustCode = null;
        if(null!=CardbindMertCustList&&!CardbindMertCustList.isEmpty()){
            personalCustCode = CardbindMertCustList.get(0).getCustCode();
            CardBindDto queryCardlist = new CardBindDto();
            queryCardlist.setStatus(CorConstants.DATA_DICT__CARD_BIND_YES);//状态 --已绑定
            queryCardlist.setCustCode(personalCustCode);
            // 查询绑卡信息
            List<CorCardbindInfo> cardlist = corCardbindInfoDao.getCorCardbindInfoList(queryCardlist);
            if(null==cardlist||cardlist.isEmpty()){
                for (CorCardbindMertCust mc : CardbindMertCustList) {
                    mc.setUpdateTime(dt);
                    mc.setStatus(CorConstants.DATA_DICT__CARD_BIND_NO);
                    corCardbindMertCustDao.saveOrUpdate(mc);
                }
            }
        }
        
        return true;
    }

    /* (non-Javadoc)
     * @see com.ibs.core.module.customer.service.ICardBindService#getCardbindInfoListByMerchantCode(java.lang.String)
     */
    @Override
    public List<CorCardbindInfo> getCardbindInfoListByMerchantCode(CardBindDto cardBindDto) throws BizCheckedException {
        logger.debug("enter CardBindServiceImpl.getCardbindInfoListByMerchantCode # merchantCode=" + cardBindDto);
        if(null == cardBindDto ||(null == cardBindDto.getToken() && null==cardBindDto.getMertCustCode()&& null==cardBindDto.getMerchantCode())){
             throw new BizException("参数有误");
        }
        CardBindDto temp = new CardBindDto();
        temp.setStatus(CorConstants.DATA_DICT__CARD_BIND_YES);  //已绑定 状态
        if(null != cardBindDto.getToken()){
            temp.setToken(cardBindDto.getToken());                  //持卡人唯一标识
        }else  if(null != cardBindDto.getMertCustCode()){
            temp.setMertCustCode(cardBindDto.getMertCustCode());    //电商持卡人会员号
        }else  if(null != cardBindDto.getMerchantCode()){
            temp.setMerchantCode(cardBindDto.getMerchantCode());    //商户号
        }
        List<CorCardbindMertCust> CardbindMertCustList = corCardbindMertCustDao.getCorCardbindMertCustList(temp);
        if(null != CardbindMertCustList && CardbindMertCustList.size()>0){
            List<String> personCustCodeList = new ArrayList<String>();
            for(CorCardbindMertCust ccmTemp : CardbindMertCustList){
                personCustCodeList.add(ccmTemp.getCustCode());
            }
            temp.setPersonalCustCodeList(personCustCodeList);
        }else{
            return null;
        }
        temp.setStatus(CorConstants.DATA_DICT__CARD_BIND_YES);//状态 --已绑定
        temp.setMertCustCode(cardBindDto.getMertCustCode());
        // 查询绑卡信息
        List<CorCardbindInfo> cardbindInfoList = corCardbindInfoDao.getCorCardbindInfoList(temp);
        if(null!=cardbindInfoList&&!cardbindInfoList.isEmpty()){
            return cardbindInfoList;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.ibs.core.module.customer.service.ICardBindService#bindCardCheck(com.ibs.core.module.customer.dto.CardBindDto)
     */
    @Override
    public String bindCardCheck(CardBindDto cardBindDto) throws BizCheckedException {
        logger.debug("enter CardBindServiceImpl.bindCardCheck # cardBindDto=" + cardBindDto);
        if(null == cardBindDto || null == cardBindDto.getBankCardNum() || null == cardBindDto.getMertCustCode()    
                 ){
             throw new BizException("参数有误");
        }
        String token = null;
        String personalCustCode = null;
        //根据
        CardBindDto temp = new CardBindDto();
        temp.setBankCardNum(cardBindDto.getBankCardNum());
        temp.setStatus(CorConstants.DATA_DICT__CARD_BIND_YES);//状态 --已绑定
        // 查询绑卡信息
        List<CorCardbindInfo> cardbindInfoList = corCardbindInfoDao.getCorCardbindInfoList(temp);
        if(null==cardbindInfoList || cardbindInfoList.isEmpty()){//不存在信息。
            return token;
        }else if(null!=cardbindInfoList&&!cardbindInfoList.isEmpty()){//存在已绑定数据
            //去判断
            temp = new CardBindDto();
            
            personalCustCode = cardbindInfoList.get(0).getCustCode();
//            temp.setMerchantCode(cardBindDto.getMerchantCode());    //指定商户号
            temp.setCustCode(personalCustCode);
            
            temp.setStatus(CorConstants.DATA_DICT__CARD_BIND_YES);  //已绑定 状态
            List<CorCardbindMertCust> CardbindMertCustList = corCardbindMertCustDao.getCorCardbindMertCustList(temp);
            if (null != CardbindMertCustList && CardbindMertCustList.size() != 0) {
                return CardbindMertCustList.get(0).getToken();
            }else{
                return token;
            }
        }
        return token;
    }

   

}
