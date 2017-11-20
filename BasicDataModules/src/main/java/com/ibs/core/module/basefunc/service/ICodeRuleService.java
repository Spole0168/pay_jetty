package com.ibs.core.module.basefunc.service;

public interface ICodeRuleService {
    /**
     * 生成账户编码
     * ZH+账户属性（2位）+账户类型（2位）+币种（3位）+随机数（12位）；
     * 账户属性：个人01，企业02，内部03；账户类型基本账户01，保证金账户02；币种：人民币156。
     * 长度 21位    ZH0101156984589484838
     * @param accountAttr
     * @param accountType
     * @param currency
     * @return
     */
    public String getAccountCode(String accountAttr, String accountType, String currency);
    /**
     * 生成登录密码
     * 必须要包含数字与字母不同组合，，字母大写，共8位，系统随机产生
     * 长度 8位   SE1237FR
     * @return
     */
    public String getCorCustLoginPwd();
    /**
     * 生成登录名
     * 同会员编号
     * 长度 14位   C2016111700001
     * @param corCustCode
     * @return
     */
    public String getCorCustLoginName(String corCustCode);
    /**
     * 生成二级产品编码
     * CP+一级产品代码（2位）+二级产品代码（2位）
     * 长度 6位   CP0101
     * @param firProductCode
     * @param secProductCode
     * @return
     */
    public String getSecondProductCode(String firProductCode, String secProductCode);
    /**
     * 生成资金单编号
     * ZJ+交易方向（2位）+YYYYMMDDHHMM（12位）+流水号（5位）
     * 长度 21位   ZJ0120161209153000001
     * @param direction
     * @return
     */
    public String getFundOrderCode(String direction);
    /**
     * 生成资金通道编码
     * 国家代码（2位）+ 合作机构类型（2位）+ 合作机构代码（4位）+ 合作机构自定义代码（4位）+二级产品类型（2位）
     * 国家代码：采用ISO 3166-1 国际标准，2位字母的国家代码。
     * 合作机构类型：01：合作商业银行；02：合作支付机构；03：合作卡组织；99：其他。
     * 合作机构代码：4位数字编码。例：1234。
     * 合作机构自定义代码：4位英文(数字)编码。例如：ICBC。
     * 长度 14位   CN011234ICBC01
     * @param countryCode
     * @param coopOrgType
     * @param coopOrgCode
     * @param coopOrgSelfCode
     * @param seProductType
     * @return
     */
    public String getFundChannelCode(String countryCode, 
            String coopOrgType, String coopOrgCode, String coopOrgSelfCode,String seProductType);
    /**
     * 生成商户编码
     * 4位行政区代码+4位行业代码+5位序列号
     * 长度 13位    C2016111700001
     * @return
     */
    public String getMenchantCode(String districtCode, String cocationCode);
    /**
     * 生成核心企业用户编码
     * C+8位时间戳+5位数字流水
     * 长度 14位    C2016111700001
     * @return
     */
    public String getCorCustCode();
    /**
     * 生成支付单编码
     * ZF+交易类型(4)+YYYYMMDDHHMM（12位）+流水号（5位）
     * 长度 21位    ZF0120161209153000001
     * @param operateType
     * @return
     */
    public String getPayOrderCode(String operateType);
    
    /**
     * 生成账户明细编码
     * 长度 32位
     * @return 
     */
    public String getAccountDetailCode();
    /**
     * 生成时间戳  格式为：yyyyMMddHHssSSS
     * 长度 14位
     * @return
     */
    public String getTimeStamp();
    /**
     * 生成手续费编码
     * SXF+通用属性2位（通用（01），指定商户（02））+产品编码4位（去掉CP）+数字流水号（12位）
     * 长度 21位
     * @return
     */
    public String getFeeCode(boolean isMerchant,String seProductCode);
    /**
     * 生成核心个人会员编码
     * P+8位时间戳+5位数字流水
     * 长度 14位
     * @return
     */
    public String getCorCustPersonalCode();
    /**
     * 生成入款订单编码
     * RK+业务类型（2位）+YYYYMMDDHHMM（12位）+流水号（5位）
     * 长度 21位
     * @param operateType  业务类型
     * @return
     */
    public String getIncomeOrderCode(String operateType);
    /**
     * 生成出款订单编码
     * CK+业务类型（2位）+YYYYMMDDHHMM（12位）+流水号（5位）
     * 长度 21位
     * @param operateType 业务类型
     * @return
     */
    public String getOutOrderCode(String operateType);
    /**
     * 生成结算单编码
     * JS+YYYYMMDD（8位）+流水号（11位）
     * 长度 21位
     * @return
     */
    public String getSettleOrderCode();
    
    /**
     * 生成转账订单编码
     * ZZ+业务类型（2位）+YYYYMMDDHHMM（12位）+流水号（5位）
     * 长度 21位
     * @param operateType 业务类型
     * @return
     */
    public String getTransferOrderCode(String operateType);
    
}
