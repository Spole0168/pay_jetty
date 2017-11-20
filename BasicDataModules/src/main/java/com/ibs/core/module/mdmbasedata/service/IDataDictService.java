package com.ibs.core.module.mdmbasedata.service;

import java.util.List;
import java.util.Map;

import com.ibs.core.module.mdmbasedata.domain.DataDict;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;

/**
 * 数据字典对外服务类接口
 * 
 * @author 
 * 
 */
public interface IDataDictService {
    /**
     * 加载数据字典信息
     * @param id主键 数据ID
     * @return DataDict
     */
    public DataDict load(String id);
    
    /**
     * 查看所有的有效的数据字典数据，根据用户的语言，返回数据会有不同。
     * @param type 数据类型 必须使用该接口定义的常量传入 例如：DATA_DICT_TYPE__PAY_METHOD 指的是支付方式
     * @return DataDict
     */
    public List<DataDict> list(String type);
    
    public List<DataDict> list(String type, String language);
    
    /**
     * 查看所有的有效的数据字典数据，根据用户的语言，返回数据会有不同。
     * 
     * @param type 数据类型 必须使用该接口定义的常量传入 例如：DATA_DICT_TYPE__PAY_METHOD 指的是支付方式
     * @return List<OptionObjectPair> 用在下拉列表框
     */
    public List<OptionObjectPair> listOptions(String type);
    
    /**
     * 查看所有的有效的数据字典数据
     * @param type 数据类型 必须使用该接口定义的常量传入 例如：DATA_DICT_TYPE__PAY_METHOD 指的是支付方式
     * @return List<OptionObjectPair> 用在下拉列表框
     */
    public List<OptionObjectPair> listOptionsByLanguage(String type,
            String language);
    
    /**
     * 查看所有的有效的数据字典数据,根据用户的语言，返回数据会有不同。
     * @param type 数据类型 必须使用该接口定义的常量传入 例如：DATA_DICT_TYPE__PAY_METHOD 指的是支付方式
     * @param defaultValue 默认值，例如“请选择” ，如果传入该参数，则会在返回结果中加入key=“” value=defaultValue的数据
     * @return List<OptionObjectPair> 可用在下拉列表框
     */
    public List<OptionObjectPair> listOptions(String type, String defaultValue);
    
    /**
     * 根据类型和字典代码查询名称
     * @param type 字典类型
     * @param code 代码
     * @return
     */
    public String getCodeName(String type, String code);
    
    /**
     * 查询系统支持的语言种类 key 装code ；extend装ID ；value 装name
     * @return
     */
    public List<OptionObjectPair> findSupportLangeage();

    /**
     * @param type 数据类型 必须使用该接口定义的常量传入 例如：DATA_DICT_TYPE__PAY_METHOD 指的是支付方式
     * @return key 为Code value 为DataDict 的map
     */
    public Map<String, DataDict> listMap(String type);

    public DataDict getObject(String type, String code, String language);
    
    
    /////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////添加需要从数据字典查询的常量////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    
    /***  Core  核心数据字典 加载*****begin ******/
    // 审核状态
    public static final String DATA_DICT_TYPE__CORE_AUDITE_STATUS = "CORE_AUDIT_STATUS";
    public static final String DATA_DICT_TYPE__CORE_AUDITE_STATUS_VALUE = "审核状态"; 
    
    // 会员商户管理 -  核心商户管理 - 审核状态
    public static final String DATA_DICT_TYPE__CORE_MERCHANT_AUDIT_STATUS = "CORE_MERCHANT_AUDIT_STATUS";
    public static final String DATA_DICT_TYPE__CORE_MERCHANT_AUDIT_STATUS_VALUE = "审核状态"; 
    
    // 支付清分 - 出款审核状态 - 审核状态
    public static final String DATA_DICT_TYPE__CORE_OUTCOME_AUDIT_STATUS = "CORE_OUTCOME_AUDIT_STATUS";
    public static final String DATA_DICT_TYPE__CORE_OUTCOME_AUDIT_STATUS_VALUE = "审核状态"; 
    
    // 会员商户管理 -  核心会员管理 - 企业类型
    public static final String DATA_DICT_TYPE__CORE_UNIT_TYPE = "CORE_UNIT_TYPE";
    public static final String DATA_DICT_TYPE__CORE_UNIT_TYPE_VALUE = "企业类型"; 
    
    // 会员商户管理 -  核心会员管理 - 企业风险等级
    public static final String DATA_DICT_TYPE__CORE_RISK_LEVEL = "CORE_RISK_LEVEL";
    public static final String DATA_DICT_TYPE__CORE_RISK_LEVEL_VALUE = "企业风险等级"; 
    
    // 会员商户管理 -  核心商户管理 - 行业属性
    public static final String DATA_DICT_TYPE__CORE_INDUSTRY = "CORE_INDUSTRY";
    public static final String DATA_DICT_TYPE__CORE_INDUSTRY_VALUE = "行业属性"; 
    
    // 会员商户管理 -  核心商户管理 - 行业类型
    public static final String DATA_DICT_TYPE__CORE_GLOBEBILL_INDUSTRY = "CORE_GLOBEBILL_INDUSTRY";
    public static final String DATA_DICT_TYPE__CORE_GLOBEBILL_INDUSTRY_VALUE = "行业类型"; 
    
    // 会员商户管理 -  核心商户管理 - 商户接入类型
    public static final String DATA_DICT_TYPE__CORE_MERCHANT_INTERFACE = "CORE_MERCHANT_INTERFACE";
    public static final String DATA_DICT_TYPE__CORE_MERCHANT_INTERFACE_VALUE = "行业类型"; 
    
    // 会员商户管理 - 核心会员管理 - 会员状态 
    public static final String DATA_DICT_TYPE__CORE_CUST_STATUS = "CORE_CUST_STATUS";
    public static final String DATA_DICT_TYPE__CORE_CUST_STATUS_VALUE = "会员状态"; 

    // 会员商户管理 - 核心商户管理 - 商户状态
    public static final String DATA_DICT_TYPE__CORE_MERT_STATUS_TYPE = "CORE_MERCHANT_STATUS";
    public static final String DATA_DICT_TYPE__CORE_MERT_STATUS_TYPE_VALUE = "商户状态";
    
    // 会员商户管理 -  核心会员管理 - 会员类型
    public static final String DATA_DICT_TYPE__CORE_CUST_TYPE = "CORE_CUST_TYPE";
    public static final String DATA_DICT_TYPE__CORE_CUST_TYPE_VALUE = "会员类型"; 
    
    // 会员商户管理 -  核心会员管理 - 证件类型
    public static final String DATA_DICT_TYPE__CORE_CERT_TYPE = "CORE_CERT_TYPE";
    public static final String DATA_DICT_TYPE__CORE_CERT_TYPE_VALUE = "证件类型"; 
    
    // 会员商户管理 - 会员管理 - 币种
    public static final String DATA_DICT_TYPE__CORE_CURRENCY = "CORE_CURRENCY";
    public static final String DATA_DICT_TYPE__CORE_CURRENCY_VALUE = "币种";
    
    // 会员商户管理 - 会员管理 - 止入止出
    public static final String DATA_DICT_TYPE__CORE_IS_OR_NO = "CORE_IS_OR_NO";
    public static final String DATA_DICT_TYPE__CORE_IS_OR_NO_VALUE = "止入止出"; 
    
    // 会员商户管理 - 会员账户管理 - 账户类型
    public static final String DATA_DICT_TYPE__CORE_ACCOUNT_TYPE = "CORE_ACCOUNT_TYPE";
    public static final String DATA_DICT_TYPE__CORE_ACCOUNT_TYPE_VALUE = "账户类型";
    
    // 会员商户管理 - 会员账户管理 - 统计类型
    public static final String DATA_DICT_TYPE__CORE_COLLECT_TYPE = "CORE_COLLECT_TYPE";
    public static final String DATA_DICT_TYPE__CORE_COLLECT_TYPE_VALUE = "统计类型";
    
    // 会员商户管理 - 商户手续费管理 - 卡种属性
    public static final String DATA_DICT_TYPE__CORE_BANK_CARD_ATTRIBUTE = "CORE_BANK_CARD_ATTRIBUTE";
    public static final String DATA_DICT_TYPE__CORE_BANK_CARD_ATTRIBUTE_VALUE = "卡种属性";
    
    // 会员商户管理 - 商户手续费管理 - 结算方式
    public static final String DATA_DICT_TYPE__CORE_SETTLEMENT_MODE = "CORE_SETTLEMENT_MODE";
    public static final String DATA_DICT_TYPE__CORE_SETTLEMENT_MODE_VALUE = "结算方式";
    
    // 会员商户管理 - 会员账户服务 - 记账方向
    public static final String DATA_DICT_TYPE__CORE_ACCOUNT_DIRECTION = "CORE_ACCOUNT_DIRECTION";
    public static final String DATA_DICT_TYPE__CORE_ACCOUNT_DIRECTION_VALUE = "记账方向";
    
    // 资金渠道 - 产品管理 - 一级产品类型
    public static final String DATA_DICT_TYPE__CORE_PRODUCT_LEVEL1_TYPE = "CORE_PRODUCT_LEVEL1";
    public static final String DATA_DICT_TYPE__CORE_PRODUCT_LEVEL1_TYPE_VALUE = "一级产品";
    
    // 资金渠道 - 产品管理 - 二级产品类型
    public static final String DATA_DICT_TYPE__CORE_PRODUCT_LEVEL2_TYPE = "CORE_PRODUCT_LEVEL2";
    public static final String DATA_DICT_TYPE__CORE_PRODUCT_LEVEL2_TYPE_VALUE = "二级产品";
    
    // 资金渠道 - 产品管理 - 产品状态
    public static final String DATA_DICT_TYPE__CORE_PRODUCT_STATUS = "CORE_PRODUCT_STATUS";
    public static final String DATA_DICT_TYPE__CORE_PRODUCT_STATUS_VALUE = "产品状态";
    
    // 资金渠道 - 产品管理 - 资金渠道类型
    public static final String DATA_DICT_TYPE__COOP_ORG_TYPE = "COOP_ORG_TYPE";
    public static final String DATA_DICT_TYPE__COOP_ORG_TYPE_VALUE = "资金渠道类型";
    
    // 资金渠道 - 产品管理 - 合作机构名称
    public static final String DATA_DICT_TYPE__CORE_CAPITAL_CHANNEL = "CORE_CAPITAL_CHANNEL";
    public static final String DATA_DICT_TYPE__CORE_CAPITAL_CHANNEL_VALUE = "合作机构名称";
    
    // 风控管理 - 黑名单管理 - 黑名单类型
    public static final String DATA_DICT_TYPE__CORE_BLACKLIST_TYPE = "CORE_BLACKLIST_TYPE";
    public static final String DATA_DICT_TYPE__CORE_BLACKLIST_TYPE_VALUE = "黑名单类型";
    
    // 风控管理 - 黑名单管理 - 黑名单状态
    public static final String DATA_DICT_TYPE__CORE_BLACKLIST_STATUS = "CORE_BLACKLIST_STATUS";
    public static final String DATA_DICT_TYPE__CORE_BLACKLIST_STATUS_VALUE = "黑名单状态";
    
    // 风控管理 - 交易限额限次管理 - 统计类型
    public static final String DATA_DICT_TYPE__CORE_COLLECT_TYPE1 = "CORE_COLLECT_TYPE1";
    public static final String DATA_DICT_TYPE__CORE_COLLECT_TYPE1_VALUE = "统计类型";
    
    // 风控管理 - 风控拦截事件管理 - 事件来源
    public static final String DATA_DICT_TYPE__CORE_EVENT_SOURCE = "CORE_EVENT_SOURCE";
    public static final String DATA_DICT_TYPE__CORE_EVENT_SOURCE_VALUE = "事件来源";
    
    // 资金渠道 - 资金单，支付单 - 对账状态
    public static final String DATA_DICT_TYPE__CORE_BANK_TRANS_STATUS = "CORE_BANK_TRANS_STATUS";
    public static final String DATA_DICT_TYPE__CORE_BANK_TRANS_STATUS_VALUE = "对账状态";
    
    // 资金渠道 - 资金单，支付单 - 商户对账状态
    public static final String DATA_DICT_TYPE__CORE_CUST_RECONCILIATION_STATUS = "CORE_CUST_RECONCILIATION_STATUS";
    public static final String DATA_DICT_TYPE__CORE_CUST_RECONCILIATION_STATUS_VALUE = "对账状态";
    
    // 资金渠道 - 对账管理 - 账务状态
    public static final String DATA_DICT_TYPE__CORE_FINANCE_STATUS = "CORE_FINANCE_STATUS";
    public static final String DATA_DICT_TYPE__CORE_FINANCE_STATUS_VALUE = "账务状态";
    
    // 支付清分 - 资金单，支付单 - 交易状态
    public static final String DATA_DICT_TYPE__CORE_FUNDS_TRANS_STATUS = "CORE_FUNDS_TRANS_STATUS";
    public static final String DATA_DICT_TYPE__CORE_FUNDS_TRANS_STATUS_VALUE = "交易状态";
    
    // 支付清分 - 商户结算单管理 - 结算单状态
    public static final String DATA_DICT_TYPE__CORE_SETTLE_ORDER_STATUS = "CORE_SETTLE_ORDER_STATUS";
    public static final String DATA_DICT_TYPE__CORE_SETTLE_ORDER_STATUS_VALUE = "结算单状态";
    
    // 支付清分 - 商户结算单管理 - 结算状态
    public static final String DATA_DICT_TYPE__CORE_SETTLE_STATUS = "CORE_SETTLE_STATUS";
    public static final String DATA_DICT_TYPE__CORE_SETTLE_STATUS_VALUE = "结算状态";
    
    // 支付清分 - 商户结算单管理 - 时间类型
    public static final String DATA_DICT_TYPE__MERCHANT_RECON_TIME_TYPE = "MERCHANT_RECON_TIME_TYPE";
    public static final String DATA_DICT_TYPE__MERCHANT_RECON_TIME_TYPE_VALUE = "时间类型";
    
    // 支付清分 - 商户结算单管理 - 银行对账状态
    public static final String DATA_DICT_TYPE__CORE_FUND_RECON_STATUS = "CORE_FUND_RECON_STATUS";
    public static final String DATA_DICT_TYPE__CORE_FUND_RECON_STATUS_VALUE = "银行对账状态";
    
    // 支付清分 - 商户结算单管理 - 商户对账状态
    public static final String DATA_DICT_TYPE__CORE_MERT_RECON_STATUS = "CORE_MERT_RECON_STATUS";
    public static final String DATA_DICT_TYPE__CORE_MERT_RECON_STATUS_VALUE = "商户对账状态";
    
    // 支付清分 - 支付服务 - 银行卡种
    public static final String DATA_DICT_TYPE__CORE_BANK_CARD_TYPE = "CORE_BANK_CARD_TYPE";
    public static final String DATA_DICT_TYPE__CORE_BANK_CARD_TYPE_VALUE = "银行卡种";
    
    // 支付清分 - 支付单 - 处理状态
    public static final String DATA_DICT_TYPE__CORE_HANDLE_STATUS = "CORE_HANDLE_STATUS";
    public static final String DATA_DICT_TYPE__CORE_HANDLE_STATUS_VALUE = "处理状态";
    
    // 支付清分 - 业务类型
    public static final String DATA_DICT_TYPE__CORE_PAY_OPERATE_TYPE = "CORE_PAY_OPERATE_TYPE";
    public static final String DATA_DICT_TYPE__CORE_PAY_OPERATE_TYPE_VALUE = "业务类型";
    
    // 支付清分 - 入款订单 - 业务类型
    public static final String DATA_DICT_TYPE__CORE_ORDER_OPERATE_TYPE_INCOME = "CORE_ORDER_OPERATE_TYPE_INCOME";
    public static final String DATA_DICT_TYPE__CORE_ORDER_OPERATE_TYPE_INCOME_VALUE = "入款订单业务类型";
    
    // 支付清分 - 出款订单 - 业务类型
    public static final String DATA_DICT_TYPE__CORE_ORDER_OPERATE_TYPE_OUT = "CORE_ORDER_OPERATE_TYPE_OUT";
    public static final String DATA_DICT_TYPE__CORE_ORDER_OPERATE_TYPE_OUT_VALUE = "出款订单业务类型";
    
    // 支付清分 - 退款订单 - 业务类型
    public static final String DATA_DICT_TYPE__CORE_ORDER_OPERATE_TYPE_REFUND = "CORE_ORDER_OPERATE_TYPE_REFUND";
    public static final String DATA_DICT_TYPE__CORE_ORDER_OPERATE_TYPE_REFUND_VALUE = "退款订单业务类型";
    
    // 支付清分 - 内部订单 - 业务类型
    public static final String DATA_DICT_TYPE__CORE_ORDER_OPERATE_TYPE_INNER = "CORE_ORDER_OPERATE_TYPE_INNER";
    public static final String DATA_DICT_TYPE__CORE_ORDER_OPERATE_TYPE_INNER_VALUE = "内部订单业务类型";
    
    // 支付清分 - 入款订单- 订单状态
    public static final String DATA_DICT_TYPE__CORE_INCOME_ORDER_STATUS = "CORE_INCOME_ORDER_STATUS";
    public static final String DATA_DICT_TYPE__CORE_INCOME_ORDER_STATUS_VALUE = "入款订单订单状态";
    
    // 支付清分 - 出款订单- 订单状态
    public static final String DATA_DICT_TYPE__CORE_OUTCOME_ORDER_STATUS = "CORE_OUTCOME_ORDER_STATUS";
    public static final String DATA_DICT_TYPE__CORE_OUTCOME_ORDER_STATUS_VALUE = "出款订单订单状态";
    
    // 支付清分 - 出款审核管理- 时间类型
    public static final String DATA_DICT_TYPE__CORE_OUTCOME_TIME_TYPE = "CORE_OUTCOME_TIME_TYPE";
    public static final String DATA_DICT_TYPE__CORE_OUTCOME_TIME_TYPE_VALUE = "时间类型";
    
    // 备付金管理 - 备付金账户维护- 备付金类型
    public static final String DATA_DICT_TYPE__CORE_RESERVE_FOUND_TYPE = "CORE_RESERVE_FOUND_TYPE";
    public static final String DATA_DICT_TYPE__CORE_RESERVE_FOUND_TYPE_VALUE = "备付金类型";
    
    // 备付金管理 - 备付金账户维护- 备付金账户状态
    public static final String DATA_DICT_TYPE__CORE_RESERVE_ACCOUNT_STATUS = "CORE_RESERVE_ACCOUNT_STATUS";
    public static final String DATA_DICT_TYPE__CORE_RESERVE_ACCOUNT_STATUS_VALUE = "备付金账户状态";
    
    // 支付清分 - 入款订单出款订单 - 时间类型
    public static final String DATA_DICT_TYPE__CORE_ORDER_TIME_TYPE = "CORE_ORDER_TIME_TYPE";
    public static final String DATA_DICT_TYPE__CORE_ORDER_TIME_TYPE_VALUE = "时间类型";
    
    // 支付清分 - 入款订单出款订单 - 来源标识
    public static final String DATA_DICT_TYPE__CORE_SOURCE_TYPE = "CORE_SOURCE_TYPE";
    public static final String DATA_DICT_TYPE__CORE_SOURCE_TYPE_VALUE = "来源标识";
    
    // 支付清分 - 退款订单 - 退款状态
    public static final String DATA_DICT_TYPE__CORE_REFOUND_STATUS = "CORE_REFOUND_STATUS";
    public static final String DATA_DICT_TYPE__CORE_REFOUND_STATUS_VALUE = "退款状态";
    
    // 支付清分 - 转账订单 - 转账状态
    public static final String DATA_DICT_TYPE__CORE_TRANSFER_STATUS = "CORE_TRANSFER_STATUS";
    public static final String DATA_DICT_TYPE__CORE_TRANSFER_STATUS_VALUE = "转账状态";
    
    // 会员商户管理 - 会员账户管理 - 记账方向
    public static final String DATA_DICT_TYPE__CORE_BLOCKED_SETTLEMENT_STATUS = "CORE_ BLOCKED_SETTLEMENT_STATUS";
    public static final String DATA_DICT_TYPE__CORE_BLOCKED_SETTLEMENT_STATUS_VALUE = "结算状态";
    
    // 支付清分 - 商户结算单管理 - 是否被篡改
    public static final String DATA_DICT_TYPE__CORE_IF_DISTORT = "CORE_IF_DISTORT";
    public static final String DATA_DICT_TYPE__CORE_IF_DISTORT_VALUE = "是否被篡改";
    
    // 支付清分 - 商户结算单管理 - 作废原因
    public static final String DATA_DICT_TYPE__CORE_CANCELLATION_REASON = "CORE_CANCELLATION_REASON";
    public static final String DATA_DICT_TYPE__CORE_CANCELLATION_REASON_VALUE = "作废原因";
    
    // 资金渠道 - 风控管理 - 行业支持卡种配置 - 卡组织
    public static final String DATA_DICT_TYPE__CORE_CARD_ORGANIZATION = "CORE_CARD_ORGANIZATION";
    public static final String DATA_DICT_TYPE__CORE_CARD_ORGANIZATION_VALUE = "卡组织";
    
    // 资金渠道 - 风控管理 - 行业支持卡种配置 - 发行地
    public static final String DATA_DICT_TYPE__CORE_CARD_ISSUE_PLACE = "CORE_CARD_ISSUE_PLACE";
    public static final String DATA_DICT_TYPE__CORE_CARD_ISSUE_PLACE_VALUE = "发行地";
    
    // 资金渠道 - 风控管理 - 行业支持卡种配置 - 状态
    public static final String DATA_DICT_TYPE__CORE_CARD_STATUS = "CORE_CARD_STATUS";
    public static final String DATA_DICT_TYPE__CORE_CARD_STATUS_VALUE = "状态";
    
    // 支付清分 - 支付单 - 是否生成结算单
    public static final String DATA_DICT_TYPE__CORE_IF_SETTLE = "CORE_IF_SETTLE";
    public static final String DATA_DICT_TYPE__CORE_IF_SETTLE_VALUE = "是否生成结算单";

    // 商户网关 - 支付接口管理 - 绑卡 - 绑卡状态
    public static final String DATA_DICT_TYPE__CORE_CARDBIND_STATUS = "CORE_CARDBIND_STATUS";
    public static final String DATA_DICT_TYPE__CORE_CARDBIND_STATUS_VALUE = "绑卡状态";
    
    // 商户网关 - 支付接口管理 - 绑卡 - 绑卡订单状态
    public static final String DATA_DICT_TYPE__CORE_BIND_ORDER_STATUS = "CORE_BIND_ORDER_STATUS";
    public static final String DATA_DICT_TYPE__CORE_BIND_ORDER_STATUS_VALUE = "绑卡订单状态";
    
    // 商户网关 - 支付接口管理 - 解绑 - 解绑订单状态
    public static final String DATA_DICT_TYPE__CORE_UNBIND_ORDER_STATUS = "CORE_UNBIND_ORDER_STATUS";
    public static final String DATA_DICT_TYPE__CORE_UNBIND_ORDER_STATUS_VALUE = "解绑订单状态";
    
    // 资金渠道 - 对账差异表 - 对账结果
    public static final String DATA_DICT_TYPE__CORE_RECONCILIATION_STATUS = "CORE_RECONCILIATION_STATUS";
    public static final String DATA_DICT_TYPE__CORE_RECONCILIATION_STATUS_VALUE = "对账结果";
    
    // 商户网关 - 通知重发 - 时间类型
    public static final String DATA_DICT_TYPE__CORE_CALLBACK_TIME_TYPE = "CORE_CALLBACK_TIME_TYPE";
    public static final String DATA_DICT_TYPE__CORE_CALLBACK_TIME_TYPE_VALUE = "时间类型";
    
    // 支付清分 - 出款审核管理 - 审核失败原因分类
    public static final String DATA_DICT_TYPE__CORE_OUTCOME_FAIL_REASON_TYPE = "CORE_OUTCOME_FAIL_REASON_TYPE";
    public static final String DATA_DICT_TYPE__CORE_OUTCOME_FAIL_REASON_TYPE_VALUE = "审核失败原因分类";
    
    // 会员商户 - 个人会员查询 - 实名认证级别
    public static final String DATA_DICT_TYPE__CORE_AUTHENTICATION_LEVEL = "CORE_AUTHENTICATION_LEVEL";
    public static final String DATA_DICT_TYPE__CORE_AUTHENTICATION_LEVEL_VALUE = "实名认证级别";
    
    // 商户网关 - 通知重发 - 报文类型
    public static final String DATA_DICT_TYPE__CORE_CALLBACK_REQ_TYPE_NOTICE = "CORE_CALLBACK_REQ_TYPE";
    public static final String DATA_DICT_TYPE__CORE_CALLBACK_REQ_TYPE_NOTICE_VALUE = "报文类型";
    
    // 商户网关 - 通知重发 - 发送状态
    public static final String DATA_DICT_TYPE__CORE_CALLBACK_REQ_STATUS = "CORE_CALLBACK_REQ_STATUS";
    public static final String DATA_DICT_TYPE__CORE_CALLBACK_REQ_STATUS_VALUE = "发送状态";
    
}
