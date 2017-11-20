package com.ibs.core.module.mdmbasedata.common;

/**
 * 全局使用的常量定义
 * 以DATA_DICT__开头的常量是在数据库中有对应存在的数据
 * 
 * @author
 *
 */
public class CorConstants {
	
	/**************************BASE常量*********************************/
	/**
	 * 常量类可重复使用的，命名时不要加限定，要添加同种类型的常量可直接接着编码往下写
	 */
    // 系统操作用户
    public final static String CREATOR = "SYSADMIN";//创建人
    public final static String UPDATOR = "SYSADMIN";//修改人
    
    // 审核类型
    public final static String AUDIT_TYPE_MERCHANT = "MERCHANT";// 商户
    public final static String AUDIT_TYPE_CUSTOMER = "CUSTOMER";// 会员
    public final static String AUDIT_TYPE_OUTCOME_ORDER = "OUTCOME_ORDER";// 出款订单
    public final static String AUDIT_TYPE_INCOMEORDER = "INCOMEORDER";// 入款订单
    public final static String AUDIT_TYPE_RISK_NAMELIST = "RISK_NAMELIST";// 黑名单
    public final static String AUDIT_TYPE_SETTLE_ORDER = "SETTLE_ORDER";// 结算单

    // 操作类型
    public static final String OPERA_ADD = "0";//新增
    public static final String OPERA_INF = "1";//查看
    public static final String OPERA_AUD = "2";//审核
    public static final String OPERA_UPD = "3";//修改
    
    //账户冻结解冻操作类型
    public static final String CORE_ACCOUNT_BALANCE_FROZEN = "01";//冻结
    public static final String CORE_ACCOUNT_BALANCE_CANCELFROZEN = "02";//解冻
    
    //Ajax 异步操作返回结果类型
    public static final String CORE_AJAX_RESULT_Y = "Y";
    public static final String CORE_AJAX_RESULT_N = "N";
    
    //商户多个IP之间的分隔符
    public static final String MERCHANT_IP_SEPARATOR = ";";
    

    
    //商户止入止出查询状态
    public static final String MERT_CHECKIN = "01";//止入
    public static final String MERT_CHECKOUT = "02";//止出
    public static final String MERT_ISCHECK = "03";//止入止出
    public static final String MERT_NORMAL = "04";//正常
    
    //时间类型
  	public static final String TIME_TYPE_CREATE = "01";//创建时间
  	public static final String TIME_TYPE_UPDATE = "02";//修改时间
  	public static final String TIME_TYPE_RECIEVE = "03";//接收时间
  	public static final String TIME_TYPE_HANDLE = "04";//处理时间
  	
  	// 生成编码规则类型
    public static final String CODE_TYPE_COR_CUST_COMPANY = "COR_CUST_COMPANY";//企业会员编码
    public static final String CODE_TYPE_MENCHANT = "MENCHANT";//商户编码
    public static final String CODE_TYPE_SECOND_PRODUCT = "SECOND_PRODUCT";//二级产品编码
    public static final String CODE_TYPE_FUND_ORDER = "FUND_ORDER";//资金单编码
    public static final String CODE_TYPE_COR_CUST_LOGIN_NAME = "COR_CUST_LOGIN_NAME";//用户登录名
    public static final String CODE_TYPE_COR_CUST_LOGIN_PWD = "COR_CUST_LOGIN_PWD";//用户登录密码
    public static final String CODE_TYPE_FUND_CHANNEL = "FUND_CHANNEL";//资金渠道编码
    public static final String CODE_TYPE_ACCOUNT = "ACCOUNT";//账户编码
    public static final String CODE_TYPE_PAY_ORDER = "PAY_ORDER";//支付订单编码
    public static final String CODE_TYPE_FEE_ORDER = "FEE_ORDER";//手续费编码
    public static final String CODE_TYPE_COR_CUST_PERSONAL = "COR_CUST_PERSONAL";//个人会员编码
    public static final String CODE_TYPE_INCOME_ORDER = "INCOME_ORDER";//入款订单编码
    public static final String CODE_TYPE_OUT_ORDER = "OUT_ORDER";//出款订单编码
    public static final String CODE_TYPE_SETTLE_ORDER = "SETTLE_ORDER";//结算订单编码
    public static final String CODE_TYPE_TRANSFER_ORDER = "TRANSFER_ORDER";//转账订单编码
    
    
    
  	//订单类型
  	public static final String ORDER_TYPE_INCOME= "01";//入款订单
  	public static final String ORDER_TYPE_OUTCOME= "02";//出款订单
  	public static final String ORDER_TYPE_REFUND= "03";//退款订单
  	
    // 卡绑定状态
    public final static String DATA_DICT__CARD_BIND_YES = "01";//已绑卡
    public final static String DATA_DICT__CARD_BIND_NO = "02";//已解绑
    
    /**
     * 信息分隔符
     */
    public static final String INFOR_SPLIT = "|";

    public static final String NAME_SPLIT = ",";

    public static final String PERHAPS_SPLIT = ",，;；|";

    /**
     * 是否全网
     **/
    public static final String IS_FOR_ALL = "Y";
    public static final String NOT_FOR_ALL = "N";

    public static final String TRUE_KEY = "Y";
    public static final String FALSE_KEY = "N";
    
    public static final String SCAN_RULE_CACHE_KEY = "SCAN_RULE_CACHE_KEY";

    public static final String CUSTOMER_CACHE = "_customer_cache_region";

    public static final String CACHE_KEY_SEPERATOR = "#";
    
    
    /**
     * 主数据中的Cache
     */
    public final static String ORG_CACHE = "_org_cache_region";
    public final static String ORG_BUSINESS_RULE_CACHE = "_org_business_rule_cache_region";
    public final static String DATA_DICT_CACHE = "_data_dict_cache_region";
    public final static String ORG_LAYER_CACHE = "_org_layer_vo_cache_region";
    public final static String DISTRICT_CACHE = "_district_cache_region";
    public final static String ORG_GROUP_CACHE = "_org_group_cache_region";
    public final static String PRODUCT_CACHE = "_product_cache_region";
    public final static String FREIGHT_DETAIL_CACHE = "_freight_detail_cache_region";
    public final static String EFFECTIVE_TYPE_CACHE = "_effective_type_cache_region";
    public final static String EXPRESS_CONTENT_CACHE = "_express_content_cache_region";
    public final static String OP_FREQUENCY_CACHE = "_op_frequency_cache_region";
    public final static String SCAN_RULE_CACHE = "_sacn_rule_cache_region";
    public final static String EMP_NAME_CACHE = "_emp_name_region";
    public final static String DISTRICT_LAYER_CACHE = "_district_layer_cache_region";

    public final static String DISTRICT_LAYER_MAP_CODE_KEY = "DISTRICT_LAYER_MAP_CODE_KEY";
    public final static String DISTRICT_LAYER_MAP_ID_KEY = "DISTRICT_LAYER_MAP_ID_KEY";
    public final static String DISTRICT_LAYER_ENTRY_KEY = "DISTRICT_LAYER_ENTRY_KEY";

    
    
    
    
    
    
    
    
    
    
    
    
    
    /**************************DATA_DICT__常量****************************************/
    // 共通 - 有效状态
    public final static String DATA_DICT__IS_VALID = "01";
    public final static String DATA_DICT__IS_VALID_VALUE = "有效";
    public final static String DATA_DICT__IS_NOT_VALID = "02";
    public final static String DATA_DICT__IS_NOT_VALID_VALUE = "无效";
    
    // 共通 - 上传标志
    public static final String DATA_DICT__UPLOAD_YES= "01";
    public static final String DATA_DICT__UPLOAD_YES_VALUE= "已上传";
    public static final String DATA_DICT__UPLOAD_NO= "02";
    public static final String DATA_DICT__UPLOAD_NO_VALUE= "未上传";
    
    // 共通 - 打印标记
  	public final static String IS_PRINTED_NOT_PRINTED = "01";
  	public final static String IS_PRINTED_NOT_PRINTED_VALUE = "未打印";
  	public final static String IS_PRINTED_PRINTED = "02";
  	public final static String IS_PRINTED_PRINTED_VALUE = "已打印";
    
    // 共通 - 币种
    public static final String DATA_DICT__CURRENCY_CNY = "50";
    public static final String DATA_DICT__CURRENCY_CNY_VALUE = "人民币";
    public static final String DATA_DICT__CURRENCY_USD = "05";
    public static final String DATA_DICT__CURRENCY_USD_VALUE = "美元";
    public static final String DATA_DICT__CURRENCY_EUR = "02";
    public static final String DATA_DICT__CURRENCY_EUR_VALUE = "欧元";
    
    // 共通 - 审核状态
    public final static String DATA_DICT__CHECK_STATUS_UNCHECK = "01";
    public final static String DATA_DICT__CHECK_STATUS_UNCHECK_VALUE = "待审核";
    public final static String DATA_DICT__CHECK_STATUS_CHECK_SUCCESS = "02";
    public final static String DATA_DICT__CHECK_STATUS_CHECK_SUCCESS_VALUE = "审核通过";
    public final static String DATA_DICT__CHECK_STATUS_CHECK_FAIL = "03";
    public final static String DATA_DICT__CHECK_STATUS_CHECK_FAIL_VALUE = "审核失败";
    public final static String DATA_DICT__CHECK_STATUS_RESUBMIT = "04";
    public final static String DATA_DICT__CHECK_STATUS_RESUBMIT_VALUE = "提交变更申请";
    public final static String DATA_DICT__CHECK_STATUS_SUSPEND = "05";
    public final static String DATA_DICT__CHECK_STATUS_SUSPEND_VALUE = "挂起";
    public final static String DATA_DICT__CHECK_STATUS_RETURN = "06";
    public final static String DATA_DICT__CHECK_STATUS_RETURN_VALUE = "退回";
    public final static String DATA_DICT__CHECK_STATUS_RECHECK_SUCCESS = "07";
    public final static String DATA_DICT__CHECK_STATUS_RECHECK_SUCCESS_VALUE = "复核通过";
    public final static String DATA_DICT__CHECK_STATUS_RECHECK_FAIL = "08";
    public final static String DATA_DICT__CHECK_STATUS_RECHECK_FAIL_VALUE = "复核失败";
    
    // 会员商户管理 - 核心会员管理 - 风险等级
    public final static String DATA_DICT__RISK_LEVEL_FIRST = "01";
    public final static String DATA_DICT__RISK_LEVEL_FIRST_VALUE = "一级";
    public final static String DATA_DICT__RISK_LEVEL_SECOND = "02";
    public final static String DATA_DICT__RISK_LEVEL_SECOND_VALUE = "二级";
    public final static String DATA_DICT__RISK_LEVEL_THIRD = "03";
    public final static String DATA_DICT__RISK_LEVEL_THIRD_VALUE = "三级";
    public final static String DATA_DICT__RISK_LEVEL_FOURTH = "04";
    public final static String DATA_DICT__RISK_LEVEL_FOURTH_VALUE = "四级";
    public final static String DATA_DICT__RISK_LEVEL_FIFTH = "05";
    public final static String DATA_DICT__RISK_LEVEL_FIFTH_VALUE = "五级";
    
    // 会员商户管理 - 核心商户管理 - 商户接入类型
    public final static String DATA_DICT__CORE_MERCHANT_INTERFACE_WEB = "01";
    public final static String DATA_DICT__CORE_MERCHANT_INTERFACE_WEB_VALUE = "网页";
    public final static String DATA_DICT__CORE_MERCHANT_INTERFACE_SDK = "02";
    public final static String DATA_DICT__CORE_MERCHANT_INTERFACE_SDK_VALUE = "SDK";
    
    // 会员商户管理 - 核心会员管理 - 会员状态
    public final static String DATA_DICT__CORE_CUST_STATUS_INVALID = "01";
    public final static String DATA_DICT__CORE_CUST_STATUS_INVALID_VALUE = "未生效";
    public final static String DATA_DICT__CORE_CUST_STATUS_VALID = "02";
    public final static String DATA_DICT__CORE_CUST_STATUS_VALID_VALUE = "已生效";
    public final static String DATA_DICT__CORE_CUST_STATUS_UNVALID = "03";
    public final static String DATA_DICT__CORE_CUST_STATUS_UNVALID_VALUE = "已失效";
    
    // 会员商户管理 - 核心商户管理 - 商户状态
    public final static String DATA_DICT__CORE_MERCHANT_STATUS_INVALID = "01";
    public final static String DATA_DICT__CORE_MERCHANT_STATUS_INVALID_VALUE = "未生效";
    public final static String DATA_DICT__CORE_MERCHANT_STATUS_VALID = "02";
    public final static String DATA_DICT__CORE_MERCHANT_STATUS_VALID_VALUE = "已生效";
    public final static String DATA_DICT__CORE_MERCHANT_STATUS_UNVALID = "03";
    public final static String DATA_DICT__CORE_MERCHANT_STATUS_UNVALID_VALUE = "已失效";
    
    // 会员商户管理 - 会员管理 - 会员类型
    public static final String DATA_DICT__CORE_CUST_TYPE_PERSONAL = "01";
    public static final String DATA_DICT__CORE_CUST_TYPE_PERSONAL_VALUE = "个人";
    public static final String DATA_DICT__CORE_CUST_TYPE_NORMAL_COMPANY = "02";
    public static final String DATA_DICT__CORE_CUST_TYPE_NORMAL_COMPANY_VALUE = "一般企业";
    public static final String DATA_DICT__CORE_CUST_TYPE_E_BUSINESS = "03";
    public static final String DATA_DICT__CORE_CUST_TYPE_E_BUSINESS_VALUE = "电商";
    public static final String DATA_DICT__CORE_CUST_TYPE_BOURSE = "04";
    public static final String DATA_DICT__CORE_CUST_TYPE_BOURSE_VALUE = "交易所";
    public static final String DATA_DICT__CORE_CUST_TYPE_AGENT = "05";
    public static final String DATA_DICT__CORE_CUST_TYPE_AGENT_VALUE = "代理商";
    
    // 会员商户管理 - 会员账户管理 - 会员账户类型
    public static final String DATA_DICT__ACCOUNT_TYPE_BASE = "01";
    public static final String DATA_DICT__ACCOUNT_TYPE_BASE_VALUE = "基本账户";
    public static final String DATA_DICT__ACCOUNT_TYPE_MARGIN = "02";
    public static final String DATA_DICT__ACCOUNT_TYPE_MARGIN_VALUE = "保证金账户";
    
    // 会员商户管理 - 会员账户管理 - 记账方向
    public final static String DATA_DICT__BANK_TRANS_DIRECTION_BORROW = "01";
    public final static String DATA_DICT__BANK_TRANS_DIRECTION_BORROW_VALUE = "借";
    public final static String DATA_DICT__BANK_TRANS_DIRECTION_CREDIT = "02";
    public final static String DATA_DICT__BANK_TRANS_DIRECTION_CREDIT_VALUE = "贷";
    
    // 会员商户管理 - 会员账户管理 - 结算状态
    public final static String DATA_DICT__CORE_BLOCKED_SETTLEMENT_STATUS_UNSETTLE = "01";
    public final static String DATA_DICT__CORE_BLOCKED_SETTLEMENT_STATUS_UNSETTLE_VALUE = "未结算";
    public final static String DATA_DICT__CORE_BLOCKED_SETTLEMENT_STATUS_SETTLING = "02";
    public final static String DATA_DICT__CORE_BLOCKED_SETTLEMENT_STATUS_SETTLING_VALUE = "结算中";
    public final static String DATA_DICT__CORE_BLOCKED_SETTLEMENT_STATUS_SETTLED = "03";
    public final static String DATA_DICT__CORE_BLOCKED_SETTLEMENT_STATUS_SETTLED_VALUE = "已结算";
    
    // 会员商户管理 - 会员账户管理 - 会员账户统计类型
    public static final String DATA_DICT__COLLECT_TYPE_BALANCE_AVALIBLE = "01";
    public static final String DATA_DICT__COLLECT_TYPE_BALANCE_AVALIBLE_VALUE = "可用余额";
    public static final String DATA_DICT__COLLECT_TYPE_BALANCE_IN_TRANSIT = "02";
    public static final String DATA_DICT__COLLECT_TYPE_BALANCE_IN_TRANSIT_VALUE = "在途余额";
    public static final String DATA_DICT__COLLECT_TYPE_BALANCE_FROZEN = "03";//
    public static final String DATA_DICT__COLLECT_TYPE_BALANCE_FROZEN_VALUE = "冻结余额";
    public static final String DATA_DICT__COLLECT_TYPE_BALANCE = "04";
    public static final String DATA_DICT__COLLECT_TYPE_BALANCE_VALUE = "余额";
    
    // 资金渠道 - 风控管理 - 风控管理事件来源
    public static final String DATA_DICT__CORE_EVENT_SOURCE_IP_EXCEPTION = "01";
    public static final String DATA_DICT__CORE_EVENT_SOURCE_IP_EXCEPTION_VALUE = "IP异常";
    public static final String DATA_DICT__CORE_EVENT_SOURCE_BLACK_LIST = "02";
    public static final String DATA_DICT__CORE_EVENT_SOURCE_BLACK_LIST_VALUE = "黑名单";
    public static final String DATA_DICT__CORE_EVENT_SOURCE_MONEY_LIMIT = "03";
    public static final String DATA_DICT__CORE_EVENT_SOURCE_MONEY_LIMIT_VALUE = "金额超限";
    public static final String DATA_DICT__CORE_EVENT_SOURCE_TIMES_LIMIT = "04";
    public static final String DATA_DICT__CORE_EVENT_SOURCE_TIMES_LIMIT_VALUE = "次数超限";
    
    // 资金渠道 - 风控管理 - 行业支持卡种配置 - 卡组织
    public static final String DATA_DICT__CORE_CARD_ORGANIZATION_UNIONPAY = "01";
    public static final String DATA_DICT__CORE_CARD_ORGANIZATION_UNIONPAY_VALUE = "银联";
    public static final String DATA_DICT__CORE_CARD_ORGANIZATION_VISA = "02";
    public static final String DATA_DICT__CORE_CARD_ORGANIZATION_VISA_VALUE = "VISA";
    public static final String DATA_DICT__CORE_CARD_ORGANIZATION_MASTER = "03";
    public static final String DATA_DICT__CORE_CARD_ORGANIZATION_MASTER_VALUE = "MASTER";
    public static final String DATA_DICT__CORE_CARD_ORGANIZATION_JCB = "04";
    public static final String DATA_DICT__CORE_CARD_ORGANIZATION_JCB_VALUE = "JCB";
    public static final String DATA_DICT__CORE_CARD_ORGANIZATION_AE = "05";
    public static final String DATA_DICT__CORE_CARD_ORGANIZATION_AE_VALUE = "American Express";
    public static final String DATA_DICT__CORE_CARD_ORGANIZATION_DC = "06";
    public static final String DATA_DICT__CORE_CARD_ORGANIZATION_DC_VALUE = "Diners Club";
    public static final String DATA_DICT__CORE_CARD_ORGANIZATION_EUROPAY = "07";
    public static final String DATA_DICT__CORE_CARD_ORGANIZATION_EUROPAY_VALUE = "EUROPAY";
    
    // 资金渠道 - 风控管理 - 行业支持卡种配置 - 发行地
    public static final String DATA_DICT__CORE_CARD_ISSUE_PLACE_INTERNAL = "01";
    public static final String DATA_DICT__CORE_CARD_ISSUE_PLACE_INTERNAL_VALUE = "国内";
    public static final String DATA_DICT__CORE_CARD_ISSUE_PLACE_OVERSEA = "02";
    public static final String DATA_DICT__CORE_CARD_ISSUE_PLACE_OVERSEA_VALUE = "国外";
    
    // 资金渠道 - 风控管理 - 行业支持卡种配置 - 状态
    public static final String DATA_DICT__CORE_CARD_STATUS_VALID = "01";
    public static final String DATA_DICT__CORE_CARD_STATUS_VALID_VALUE = "有效";
    public static final String DATA_DICT__CORE_CARD_STATUS_UNVALID = "02";
    public static final String DATA_DICT__CORE_CARD_STATUS_UNVALID_VALUE = "无效";
    
    // 资金渠道 - 银行对账 - 对账结果
    public static final String DATA_DICT__CORE_RECONCILIATION_STATUS_MONEY_LONG = "01";
    public static final String DATA_DICT__CORE_RECONCILIATION_STATUS_MONEY_LONG_VALUE = "金额差异长款";
    public static final String DATA_DICT__CORE_RECONCILIATION_STATUS_MONEY_SHORT = "02";
    public static final String DATA_DICT__CORE_RECONCILIATION_STATUS_MONEY_SHORT_VALUE = "金额差异短款";
    public static final String DATA_DICT__CORE_RECONCILIATION_STATUS_SIDE_LONG = "03";
    public static final String DATA_DICT__CORE_RECONCILIATION_STATUS_SIDE_LONG_VALUE = "单边帐长款";
    public static final String DATA_DICT__CORE_RECONCILIATION_STATUS_SIDE_SHORT = "04";
    public static final String DATA_DICT__CORE_RECONCILIATION_STATUS_SIDE_SHORT_VALUE = "单边帐短款";
    public static final String DATA_DICT__CORE_RECONCILIATION_STATUS_COMPLETED = "05";
    public static final String DATA_DICT__CORE_RECONCILIATION_STATUS_COMPLETED_VALUE = "已对账";
    
    // 资金渠道 - 银行对账 - 账务状态
    public static final String DATA_DICT__CORE_FINANCE_STATUS_TODO = "01";
    public static final String DATA_DICT__CORE_FINANCE_STATUS_TODO_VALUE = "待处理";
    public static final String DATA_DICT__CORE_FINANCE_STATUS_SUSPEND = "02";
    public static final String DATA_DICT__CORE_FINANCE_STATUS_SUSPEND_VALUE = "已挂账";
    public static final String DATA_DICT__CORE_FINANCE_STATUS_ERASE = "03";
    public static final String DATA_DICT__CORE_FINANCE_STATUS_ERASE_VALUE = "已抹帐";
    public static final String DATA_DICT__CORE_FINANCE_STATUS_COMPLETED = "04";
    public static final String DATA_DICT__CORE_FINANCE_STATUS_COMPLETED_VALUE = "已对账";
    
    // 资金渠道 - 银行对账 - 资金单交易状态
    public static final String DATA_DICT__CORE_FUNDS_TRANS_STATUS_SUCCESS = "01";
    public static final String DATA_DICT__CORE_FUNDS_TRANS_STATUS_SUCCESS_VALUE = "成功";
    public static final String DATA_DICT__CORE_FUNDS_TRANS_STATUS_FAILED = "02";
    public static final String DATA_DICT__CORE_FUNDS_TRANS_STATUS_FAILED_VALUE = "失败";
    
    // 资金渠道 - 银行对账 - 银行对账交易状态
    public static final String DATA_DICT__CORE_BANK_TRANS_STATUS_SUCCESS = "01";
    public static final String DATA_DICT__CORE_BANK_TRANS_STATUS_SUCCESS_VALUE = "成功";
    public static final String DATA_DICT__CORE_BANK_TRANS_STATUS_FAILED = "02";
    public static final String DATA_DICT__CORE_BANK_TRANS_STATUS_FAILED_VALUE = "失败";
    
    // 资金渠道 - 产品管理 - 一级产品类型
    public static final String DATA_DICT__CORE_PRODUCT_LEVEL1_CASHIER = "01";
    public static final String DATA_DICT__CORE_PRODUCT_LEVEL1_CASHIER_VALUE = "收银台";
    public static final String DATA_DICT__CORE_PRODUCT_LEVEL1_API = "02";
    public static final String DATA_DICT__CORE_PRODUCT_LEVEL1_API_VALUE = "API";
    public static final String DATA_DICT__CORE_PRODUCT_LEVEL1_SDK = "03";
    public static final String DATA_DICT__CORE_PRODUCT_LEVEL1_SDK_VALUE = "SDK";
    
    // 资金渠道 - 产品管理 - 二级产品类型
    public static final String DATA_DICT__SE_PRODUCT_TYPE_B2C = "01";
    public static final String DATA_DICT__SE_PRODUCT_TYPE_B2C_VALUE = "B2C";
    public static final String DATA_DICT__SE_PRODUCT_TYPE_B2B = "02";
    public static final String DATA_DICT__SE_PRODUCT_TYPE_B2B_VALUE = "B2B";
    public static final String DATA_DICT__SE_PRODUCT_TYPE_INCOME = "03";
    public static final String DATA_DICT__SE_PRODUCT_TYPE_INCOME_VALUE = "代扣";
    public static final String DATA_DICT__SE_PRODUCT_TYPE_PAYMENT = "04";
    public static final String DATA_DICT__SE_PRODUCT_TYPE_PAYMENT_VALUE = "代付";
    public static final String DATA_DICT__SE_PRODUCT_TYPE_QUICKPAY = "05";
    public static final String DATA_DICT__SE_PRODUCT_TYPE_QUICKPAY_VALUE = "快捷支付";
    public static final String DATA_DICT__SE_PRODUCT_TYPE_AUTH = "06";
    public static final String DATA_DICT__SE_PRODUCT_TYPE_AUTH_VALUE = "实名认证";
    
    // 资金渠道 - 产品管理 - 产品状态
    public static final String DATA_DICT__CORE_PRODUCT_STATUS_ONLINE = "01";
    public static final String DATA_DICT__CORE_PRODUCT_STATUS_ONLINE_VALUE = "已上线";
    public static final String DATA_DICT__CORE_PRODUCT_STATUS_OFFLINE = "02";
    public static final String DATA_DICT__CORE_PRODUCT_STATUS_OFFLINE_VALUE = "已下线";
    
    // 资金渠道 - 风控管理 - 黑名单类型
    public static final String DATA_DICT__CORE_BLACKLIST_TYPE_PHONE_NUM= "01";
    public static final String DATA_DICT__CORE_BLACKLIST_TYPE_PHONE_NUM_VALUE= "手机号码";
    public static final String DATA_DICT__CORE_BLACKLIST_TYPE_BANKCARD_NUM= "02";
    public static final String DATA_DICT__CORE_BLACKLIST_TYPE_BANKCARD_NUM_VALUE= "银行卡号";
    public static final String DATA_DICT__CORE_BLACKLIST_TYPE_HOLDER_IP= "03";
    public static final String DATA_DICT__CORE_BLACKLIST_TYPE_HOLDER_IP_VALUE= "持卡人IP";
    public static final String DATA_DICT__CORE_BLACKLIST_TYPE_BIZ_LICENSE= "04";
    public static final String DATA_DICT__CORE_BLACKLIST_TYPE_BIZ_LICENSE_VALUE= "营业执照编码";
    public static final String DATA_DICT__CORE_BLACKLIST_TYPE_ENTERPRISE_NAME= "05";
    public static final String DATA_DICT__CORE_BLACKLIST_TYPE_ENTERPRISE_NAME_VALUE= "企业名称";
    public static final String DATA_DICT__CORE_BLACKLIST_TYPE_CREDIT_LETTER_CODE= "06";
    public static final String DATA_DICT__CORE_BLACKLIST_TYPE_CREDIT_LETTER_CODE_VALUE= "社会信用证代码";
    
    // 资金渠道 - 风控管理 - 黑名单状态
    public static final String DATA_DICT__CORE_BLACKLIST_STATUS_DOTO= "01";
    public static final String DATA_DICT__CORE_BLACKLIST_STATUS_DOTO_VALUE= "待审核";
    public static final String DATA_DICT__CORE_BLACKLIST_STATUS_VALID= "02";
    public static final String DATA_DICT__CORE_BLACKLIST_STATUS_VALID_VALUE= "已审核";
    public static final String DATA_DICT__CORE_BLACKLIST_STATUS_REFUSE= "03";
    public static final String DATA_DICT__CORE_BLACKLIST_STATUS_REFUSE_VALUE= "拒绝";
    
    // 支付清分 - 商户结算单管理 - 结算单状态
    public static final String DATA_DICT__CORE_SETTLE_ORDER_STATUS_NORMAL = "01";
    public static final String DATA_DICT__CORE_SETTLE_ORDER_STATUS_NORMAL_VALUE = "正常";
    public static final String DATA_DICT__CORE_SETTLE_ORDER_STATUS_EXCEPTION = "02";
    public static final String DATA_DICT__CORE_SETTLE_ORDER_STATUS_EXCEPTION_VALUE = "异常";
    public static final String DATA_DICT__CORE_SETTLE_ORDER_STATUS_ABANDON = "03";
    public static final String DATA_DICT__CORE_SETTLE_ORDER_STATUS_ABANDON_VALUE = "作废";
    
    // 支付清分 - 商户结算单管理 - 是否被篡改
    public static final String DATA_DICT__CORE_IF_DISTORT_YES = "01";
    public static final String DATA_DICT__CORE_IF_DISTORT_YES_VALUE = "是";
    public static final String DATA_DICT__CORE_IF_DISTORT_NO = "02";
    public static final String DATA_DICT__CORE_IF_DISTORT_NO_VALUE = "否";
    
    // 支付清分 - 商户结算单管理 - 作废原因
    public static final String DATA_DICT__CORE_CANCELLATION_REASON_DISTORT = "01";
    public static final String DATA_DICT__CORE_CANCELLATION_REASON_DISTORT_VALUE = "数据被篡改";
    public static final String DATA_DICT__CORE_CANCELLATION_REASON_AMOUNT_EXCEPTION= "02";
    public static final String DATA_DICT__CORE_CANCELLATION_REASON_AMOUNT_EXCEPTION_VALUE= "金额异常";
    
    // 支付清分 - 商户结算单管理 - 结算状态
    public static final String DATA_DICT__SETTLE_STATUS_TODO = "01";
    public static final String DATA_DICT__SETTLE_STATUS_TODO_VALUE = "待结算";
    public static final String DATA_DICT__SETTLE_STATUS_COMPLETED = "02";
    public static final String DATA_DICT__SETTLE_STATUS_COMPLETED_VALUE = "已结算";
    public static final String DATA_DICT__SETTLE_STATUS_DELAY = "03";
    public static final String DATA_DICT__SETTLE_STATUS_DELAY_VALUE = "延期结算";
    public static final String DATA_DICT__SETTLE_STATUS_STOP = "04";
    public static final String DATA_DICT__SETTLE_STATUS_STOP_VALUE = "停止结算";
    
    // 支付清分 - 商户结算单管理  - 时间类型
    public static final String DATA_DICT__MERCHANT_RECON_TIME_TYPE_CREATE = "01";
    public static final String DATA_DICT__MERCHANT_RECON_TIME_TYPE_CREATE_VALUE = "生成时间";
    public static final String DATA_DICT__MERCHANT_RECON_TIME_TYPE_SETTLE = "02";
    public static final String DATA_DICT__MERCHANT_RECON_TIME_TYPE_SETTLE_VALUE = "结算时间";
    public static final String DATA_DICT__MERCHANT_RECON_TIME_TYPE_AUDIT = "03";
    public static final String DATA_DICT__MERCHANT_RECON_TIME_TYPE_AUDIT_VALUE = "审核时间";
    
    // 支付清分 - 支付服务 - 银行卡类型
    public static final String DATA_DICT__CORE_BANK_CARD_TYPE_DEPOSIT = "01";
    public static final String DATA_DICT__CORE_BANK_CARD_TYPE_DEPOSIT_VALUE = "储蓄卡";
    public static final String DATA_DICT__CORE_BANK_CARD_TYPE_CREDIT = "02";
    public static final String DATA_DICT__CORE_BANK_CARD_TYPE_CREDIT_VALUE = "信用卡";
    
    // 备付金管理 - 备付金账户维护 - 备付金类型
    public static final String DATA_DICT__CORE_RESERVE_FOUND_TYPE_DEPOSITORY = "01";
    public static final String DATA_DICT__CORE_RESERVE_FOUND_TYPE_DEPOSITORY_VALUE = "存管账户";
    public static final String DATA_DICT__CORE_RESERVE_FOUND_TYPE_REMITTED = "02";
    public static final String DATA_DICT__CORE_RESERVE_FOUND_TYPE_REMITTED_VALUE = "汇缴账户";
    public static final String DATA_DICT__CORE_RESERVE_FOUND_TYPE_PAYMENT = "03";
    public static final String DATA_DICT__CORE_RESERVE_FOUND_TYPE_PAYMENT_VALUE = "收付账户";
    
    // 备付金管理 - 备付金账户维护 - 备付金账户状态
    public static final String DATA_DICT__CORE_RESERVE_ACCOUNT_STATUS_NORMAL = "01";
    public static final String DATA_DICT__CORE_RESERVE_ACCOUNT_STATUS_NORMAL_VALUE = "正常";
    public static final String DATA_DICT__CORE_RESERVE_ACCOUNT_STATUS_EXCEPTION = "02";
    public static final String DATA_DICT__CORE_RESERVE_ACCOUNT_STATUS_EXCEPTION_VALUE = "异常";
    
    // 待定 - 金额流向
    public final static String DATA_DICT__MONEY_DIRECTION_INCOME = "01";
    public final static String DATA_DICT__MONEY_DIRECTION_INCOME_VALUE = "入款";
    public final static String DATA_DICT__MONEY_DIRECTION_OUTCOME = "02";
    public final static String DATA_DICT__MONEY_DIRECTION_OUTCOME_VALUE = "出款";

    // 支付清分 - 支付单管理 - 订单支付单业务类型
    public static final String DATA_DICT__PAY_OPERATE_TYPE_INCOME_CHARGE = "0101";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_INCOME_CHARGE_VALUE = "充值";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_INCOME_PAYMENT = "0102";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_INCOME_PAYMENT_VALUE = "订单支付";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_INCOME_MARGIN = "0103";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_INCOME_MARGIN_VALUE = "保证金充值";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_OUT_WITHDRAW = "0201";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_OUT_WITHDRAW_VALUE = "提现";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_OUT_CLEARING = "0202";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_OUT_CLEARING_VALUE = "结算";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_OUT_DEPOSIT = "0203";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_OUT_DEPOSIT_VALUE = "保证金出款";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_REFUND_NORMAL = "0301";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_REFUND_NORMAL_VALUE = "基本账户退款";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_REFUND_DEPOSIT = "0302";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_REFUND_DEPOSIT_VALUE = "保证金账户退款";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_INNER_DEPOSIT_SUPPLY = "0401";
    public static final String DATA_DICT__PAY_OPERATE_TYPE_INNER_DEPOSIT_SUPPLY_VALUE = "结算补足保证金";
    
    // 支付清分 - 订单综合查询 - 入款订单状态
    public static final String DATA_DICT__CORE_INCOME_ORDER_STATUS_TODO = "01";
    public static final String DATA_DICT__CORE_INCOME_ORDER_STATUS_TODO_VALUE = "待处理";
    public static final String DATA_DICT__CORE_INCOME_ORDER_STATUS_TOAUTH = "02";
    public static final String DATA_DICT__CORE_INCOME_ORDER_STATUS_TOAUTH_VALUE = "待验证";
    public static final String DATA_DICT__CORE_INCOME_ORDER_STATUS_TOPAY = "03";
    public static final String DATA_DICT__CORE_INCOME_ORDER_STATUS_TOPAY_VALUE = "待支付";
    public static final String DATA_DICT__CORE_INCOME_ORDER_STATUS_PAYING = "04";
    public static final String DATA_DICT__CORE_INCOME_ORDER_STATUS_PAYING_VALUE = "支付中";
    public static final String DATA_DICT__CORE_INCOME_ORDER_STATUS_SUCCESS = "05";
    public static final String DATA_DICT__CORE_INCOME_ORDER_STATUS_SUCCESS_VALUE = "支付成功";
    public static final String DATA_DICT__CORE_INCOME_ORDER_STATUS_FAILED = "06";
    public static final String DATA_DICT__CORE_INCOME_ORDER_STATUS_FAILED_VALUE = "支付失败";
    
    // 支付清分 - 订单综合查询 - 出款订单状态
    public static final String DATA_DICT__CORE_OUT_ORDER_STATUS_TODO = "01";
    public static final String DATA_DICT__CORE_OUT_ORDER_STATUS_TODO_VALUE = "待处理";
    public static final String DATA_DICT__CORE_OUT_ORDER_STATUS_TOAUDIT = "02";
    public static final String DATA_DICT__CORE_OUT_ORDER_STATUS_TOAUDIT_VALUE = "待审核";
    public static final String DATA_DICT__CORE_OUT_ORDER_STATUS_PAYING = "03";
    public static final String DATA_DICT__CORE_OUT_ORDER_STATUS_PAYING_VALUE = "支付中";
    public static final String DATA_DICT__CORE_OUT_ORDER_STATUS_SUCCESS = "04";
    public static final String DATA_DICT__CORE_OUT_ORDER_STATUS_SUCCESS_VALUE = "支付成功";
    public static final String DATA_DICT__CORE_OUT_ORDER_STATUS_FAILED = "05";
    public static final String DATA_DICT__CORE_OUT_ORDER_STATUS_FAILED_VALUE = "失败";
    
    // 支付清分 - 入款订单出款订单 - 时间类型
    public static final String DATA_DICT__CORE_ORDER_TIME_TYPE_RECEIVE= "01";
    public static final String DATA_DICT__CORE_ORDER_TIME_TYPE_RECEIVE_VALUE= "接收时间";
    public static final String DATA_DICT__CORE_ORDER_TIME_TYPE_PROCESS= "02";
    public static final String DATA_DICT__CORE_ORDER_TIME_TYPE_PROCESS_VALUE= "处理时间";
    public static final String DATA_DICT__CORE_ORDER_TIME_TYPE_CREATE= "03";
    public static final String DATA_DICT__CORE_ORDER_TIME_TYPE_CREATE_VALUE= "创建时间";
    public static final String DATA_DICT__CORE_ORDER_TIME_TYPE_UPDATE= "04";
    public static final String DATA_DICT__CORE_ORDER_TIME_TYPE_UPDATE_VALUE= "修改时间";
    
    // 支付清分 - 入款订单出款订单 - 来源标识
    public static final String DATA_DICT__CORE_SOURCE_TYPE_SYSTEM= "01";
    public static final String DATA_DICT__CORE_SOURCE_TYPE_SYSTEM_VALUE= "系统";
    public static final String DATA_DICT__CORE_SOURCE_TYPE_HANDLE= "02";
    public static final String DATA_DICT__CORE_SOURCE_TYPE_HANDLE_VALUE= "手工";
    
    // 支付清分 - 退款订单 - 退款状态
    public static final String DATA_DICT__CORE_REFOUND_STATUS_TODO= "01";
    public static final String DATA_DICT__CORE_REFOUND_STATUS_TODO_VALUE= "待处理";
    public static final String DATA_DICT__CORE_REFOUND_STATUS_PROCESS= "02";
    public static final String DATA_DICT__CORE_REFOUND_STATUS_PROCESS_VALUE= "退款中";
    public static final String DATA_DICT__CORE_REFOUND_STATUS_SUCCESS= "03";
    public static final String DATA_DICT__CORE_REFOUND_STATUS_SUCCESS_VALUE= "退款成功";
    public static final String DATA_DICT__CORE_REFOUND_STATUS_FAIL= "04";
    public static final String DATA_DICT__CORE_REFOUND_STATUS_FAIL_VALUE= "退款失败";
    public static final String DATA_DICT__CORE_REFOUND_STATUS_TOAUDIT= "05";
    public static final String DATA_DICT__CORE_REFOUND_STATUS_TOAUDIT_VALUE= "待审核";
    
    // 支付清分 - 转账订单 - 转账状态
    public static final String DATA_DICT__CORE_TRANSFER_STATUS_TODO= "01";
    public static final String DATA_DICT__CORE_TRANSFER_STATUS_TODO_VALUE= "待处理";
    public static final String DATA_DICT__CORE_TRANSFER_STATUS_PROCESS= "02";
    public static final String DATA_DICT__CORE_TRANSFER_STATUS_PROCESS_VALUE= "处理中";
    public static final String DATA_DICT__CORE_TRANSFER_STATUS_SUCCESS= "03";
    public static final String DATA_DICT__CORE_TRANSFER_STATUS_SUCCESS_VALUE= "转账成功";
    public static final String DATA_DICT__CORE_TRANSFER_STATUS_FAIL= "04";
    public static final String DATA_DICT__CORE_TRANSFER_STATUS_FAIL_VALUE= "转账失败";
    
    
    // 支付清分 - 支付单 - 处理状态
    public static final String DATA_DICT__PAY_ORDER_STATUS_PROCESSING = "01";
    public static final String DATA_DICT__PAY_ORDER_STATUS_PROCESSING_VALUE = "处理中";
    public static final String DATA_DICT__PAY_ORDER_STATUS_SUCCESS = "02";
    public static final String DATA_DICT__PAY_ORDER_STATUS_SUCCESS_VALUE = "成功";
    public static final String DATA_DICT__PAY_ORDER_STATUS_FAILED = "03";
    public static final String DATA_DICT__PAY_ORDER_STATUS_FAILED_VALUE = "失败";
    
    // 支付清分 - 支付单 - 是否生成结算单
    public static final String DATA_DICT__CORE_IF_SETTLE_YES = "01";
    public static final String DATA_DICT__CORE_IF_SETTLE_YES_VALUE = "是";
    public static final String DATA_DICT__CORE_IF_SETTLE_NO = "02";
    public static final String DATA_DICT__CORE_IF_SETTLE_NO_VALUE = "否";
    
    // 会员商户管理 - 商户手续费管理 -卡种属性
    public static final String DATA_DICT__CORE_BANK_BANK_COMPANY= "01";
    public static final String DATA_DICT__CORE_BANK_BANK_COMPANY_VALUE= "企业";
    public static final String DATA_DICT__CORE_BANK_BANK_PERSON= "02";
    public static final String DATA_DICT__CORE_BANK_BANK_PERSON_VALUE= "个人";
    // 会员商户管理 - 商户手续费管理 - 结算方式
    public static final String DATA_DICT__CORE_SETTLEMENT_MODE_INCARD_INUSE= "01";
    public static final String DATA_DICT__CORE_SETTLEMENT_MODE_INCARD_INUSE_VALUE= "内卡内用";
    public static final String DATA_DICT__CORE_SETTLEMENT_MODE_INCARD_OUTUSE= "02";
    public static final String DATA_DICT__CORE_SETTLEMENT_MODE_INCARD_OUTUSE_VALUE= "内卡外用";
    public static final String DATA_DICT__CORE_SETTLEMENT_MODE_OUTCARD_INUSE= "03";
    public static final String DATA_DICT__CORE_SETTLEMENT_MODE_OUTCARD_INUSE_VALUE= "外卡内用";
    public static final String DATA_DICT__CORE_SETTLEMENT_MODE_OUTCARD_OUTUSE= "04";
    public static final String DATA_DICT__CORE_SETTLEMENT_MODE_OUTCARD_OUTUSE_VALUE= "外卡外用";
  	// 待定 - 商户门户用户状态
  	public static final String MERT_USER_STATUS_01 = "01";
  	public static final String MERT_USER_STATUS_01_VALUE = "生效";
  	public static final String MERT_USER_STATUS_02 = "02";
  	public static final String MERT_USER_STATUS_02_VALUE = "失效";

    // 支付清分-商户结算单管理-商户对账状态
  	public static final String DATA_DICT__CORE_MERT_RECON_STATUS_NORMAL = "01";
  	public static final String DATA_DICT__CORE_MERT_RECON_STATUS_NORMAL_VALUE = "正常";
  	public static final String DATA_DICT__CORE_MERT_RECON_STATUS_APPEAL = "02";
  	public static final String DATA_DICT__CORE_MERT_RECON_STATUS_APPEAL_VALUE = "申诉";
  	public static final String DATA_DICT__CORE_MERT_RECON_STATUS_EXCEPTION = "03";
  	public static final String DATA_DICT__CORE_MERT_RECON_STATUS_EXCEPTION_VALUE = "异常";
  	public static final String DATA_DICT__CORE_MERT_RECON_STATUS_UNRECON = "04";
  	public static final String DATA_DICT__CORE_MERT_RECON_STATUS_UNRECON_VALUE = "未对账";
  	
  	// 支付清分-商户结算单管理-银行对账状态
  	public static final String DATA_DICT__CORE_FUND_RECON_STATUS_NORMAL = "01";
  	public static final String DATA_DICT__CORE_FUND_RECON_STATUS_NORMAL_VALUE = "已对账";
  	public static final String DATA_DICT__CORE_FUND_RECON_STATUS_EXCEPTION = "02";
  	public static final String DATA_DICT__CORE_FUND_RECON_STATUS_EXCEPTION_VALUE = "异常";
  	public static final String DATA_DICT__CORE_FUND_RECON_STATUS_DOUTE = "03";
  	public static final String DATA_DICT__CORE_FUND_RECON_STATUS_DOUTE_VALUE = "存疑";
  	public static final String DATA_DICT__CORE_FUND_RECON_STATUS_UNRECON = "04";
  	public static final String DATA_DICT__CORE_FUND_RECON_STATUS_UNRECON_VALUE = "未对账";
  	
  	// 支付清分 - 出款审核管理 - 时间类型
  	public static final String DATA_DICT__CORE_OUTCOME_TIME_TYPE_CREATE = "01";
  	public static final String DATA_DICT__CORE_OUTCOME_TIME_TYPE_CREATE_VALUE = "创建时间";
  	public static final String DATA_DICT__CORE_OUTCOME_TIME_TYPE_AUDIT = "02";
  	public static final String DATA_DICT__CORE_OUTCOME_TIME_TYPE_AUDIT_VALUE = "审核时间";
  	public static final String DATA_DICT__CORE_OUTCOME_TIME_TYPE_REAUDIT = "03";
  	public static final String DATA_DICT__CORE_OUTCOME_TIME_TYPE_REAUDIT_VALUE = "复核时间";
  	
  	// 支付清分 - 出款审核管理 - 审核失败原因分类
  	
  	
    // 资金渠道 - 商户对账异常原因类型
  	public static final String DATA_DICT__SETTLE_EXCEPTION_REASON = "01";
    public static final String DATA_DICT__SETTLE_EXCEPTION_REASON_VALUE = "订单信息被篡改"; 
    
    // 商户网关 - 支付接口管理 - 绑卡 - 绑卡状态
    public static final String DATA_DICT__CORE_CARDBIND_STATUS_BIND = "01";
    public static final String DATA_DICT__CORE_CARDBIND_STATUS_BIND_VALUE = "已绑定";
    public static final String DATA_DICT__CORE_CARDBIND_STATUS_UNBIND = "02";
    public static final String DATA_DICT__CORE_CARDBIND_STATUS_UNBIND_VALUE = "已解绑";
    
    // 商户网关 - 支付接口管理 - 绑卡 - 绑卡订单状态
    public static final String DATA_DICT__CORE_BIND_ORDER_STATUS_TODO = "01";
    public static final String DATA_DICT__CORE_BIND_ORDER_STATUS_TODO_VALUE = "待处理";
    public static final String DATA_DICT__CORE_BIND_ORDER_STATUS_TOAUDIT = "02";
    public static final String DATA_DICT__CORE_BIND_ORDER_STATUS_TOAUDIT_VALUE = "待验证";
    public static final String DATA_DICT__CORE_BIND_ORDER_STATUS_SUCCESS = "03";
    public static final String DATA_DICT__CORE_BIND_ORDER_STATUS_SUCCESS_VALUE = "成功";
    public static final String DATA_DICT__CORE_BIND_ORDER_STATUS_FAIL = "04";
    public static final String DATA_DICT__CORE_BIND_ORDER_STATUS_FAIL_VALUE = "失败";
    
    // 商户网关 - 支付接口管理 - 解绑 - 解绑订单状态
    public static final String DATA_DICT__CORE_UNBIND_ORDER_STATUS_SUCCESS = "01";
    public static final String DATA_DICT__CORE_UNBIND_ORDER_STATUS_SUCCESS_VALUE = "成功";
    public static final String DATA_DICT__CORE_UNBIND_ORDER_STATUS_FAIL = "02";
    public static final String DATA_DICT__CORE_UNBIND_ORDER_STATUS_FAIL_VALUE = "失败";
    public static final String DATA_DICT__CORE_UNBIND_ORDER_STATUS_TODO = "03";
    public static final String DATA_DICT__CORE_UNBIND_ORDER_STATUS_TODO_VALUE = "待处理";
    public static final String DATA_DICT__CORE_UNBIND_ORDER_STATUS_PROCESS = "04";
    public static final String DATA_DICT__CORE_UNBIND_ORDER_STATUS_PROCESS_VALUE = "处理中";
    
    // 商户网关 - 通知重发 - 时间类型
    public static final String DATA_DICT__CORE_CALLBACK_TIME_TYPE_CREATE = "01";
    public static final String DATA_DICT__CORE_CALLBACK_TIME_TYPE_CREATE_VALUE = "创建时间";
    public static final String DATA_DICT__CORE_CALLBACK_TIME_TYPE_GENERATE = "02";
    public static final String DATA_DICT__CORE_CALLBACK_TIME_TYPE_GENERATE_VALUE = "报文生成时间";
    public static final String DATA_DICT__CORE_CALLBACK_TIME_TYPE_LAST = "03";
    public static final String DATA_DICT__CORE_CALLBACK_TIME_TYPE_LAST_VALUE = "最后一次重发时间";
    public static final String DATA_DICT__CORE_CALLBACK_TIME_TYPE_HANDLE = "04";
    public static final String DATA_DICT__CORE_CALLBACK_TIME_TYPE_HANDLE_VALUE = "手工发送时间";
    
    // 会员商户 - 个人会员查询 - 实名认证级别
    public static final String DATA_DICT__CORE_AUTHENTICATION_LEVEL_UNAUTH = "01";
    public static final String DATA_DICT__CORE_AUTHENTICATION_LEVEL_UNAUTH_VALUE = "未实名";
    public static final String DATA_DICT__CORE_AUTHENTICATION_LEVEL_FIRST = "02";
    public static final String DATA_DICT__CORE_AUTHENTICATION_LEVEL_FIRST_VALUE = "一级";
    public static final String DATA_DICT__CORE_AUTHENTICATION_LEVEL_SECOND = "03";
    public static final String DATA_DICT__CORE_AUTHENTICATION_LEVEL_SECOND_VALUE = "二级";
    public static final String DATA_DICT__CORE_AUTHENTICATION_LEVEL_THIRD = "04";
    public static final String DATA_DICT__CORE_AUTHENTICATION_LEVEL_THIRD_VALUE = "三级";
    
    //商户手续费
    
    // 商户网关 - 通知重发 - 报文类型
    public static final String DATA_DICT__CORE_CALLBACK_REQ_TYPE_NOTICE = "01";
    public static final String DATA_DICT__CORE_CALLBACK_REQ_TYPE_NOTICE_VALUE = "支付通知";
    public static final String DATA_DICT__CORE_CALLBACK_REQ_TYPE_ENSURE = "02";
    public static final String DATA_DICT__CORE_CALLBACK_REQ_TYPE_ENSURE_VALUE = "支付确认";
    
    // 商户网关 - 通知重发 - 发送状态
    public static final String DATA_DICT__CORE_CALLBACK_REQ_STATUS_YES = "01";
    public static final String DATA_DICT__CORE_CALLBACK_REQ_STATUS_YES_VALUE = "已发送";
    public static final String DATA_DICT__CORE_CALLBACK_REQ_STATUS_NO = "02";
    public static final String DATA_DICT__CORE_CALLBACK_REQ_STATUS_NO_VALUE = "未发送";
    
    
   
}
