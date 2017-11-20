package com.ibs.core.module.mdmbasedata.common;


/**
 * 全局使用的常量定义
 * @author 
 *
 */
public class CorConstants {
	//数据有效状态
	public final static String IS_VALID="01";
	public final static String IS_VALID_VALUE="有效";
	public final static String IS_NOT_VALID="02";
	public final static String IS_NOT_VALID_VALUE="无效";
	
	
	//审核状态
	public final static String UNCHECK="01";//待审核
	public final static String UNCHECK_VALUE= "待审核";
	public final static String CHECKED_SUCCESS="02";//审核通过
	public final static String CHECKED_SUCCESS_VALUE= "审核通过";
	public final static String CHECKED_FAIL="03";//审核失败
	public final static String CHECKED_FAIL_VALUE= "审核失败";
	
	//审核类型
	public final static String AUDIT_TYPE_MERCHANT ="MERCHANT";//商户
	public final static String AUDIT_TYPE_CUSTOMER ="CUSTOMER";//会员
	
	//会员类型
	public final static String CUST_TYPE_PERSON="01";//个人会员
	public final static String CUST_TYPE_PERSON_VALUE= "个人会员";
	public final static String CUST_TYPE_COMPANY="02";//企业会员
	public final static String CUST_TYPE_COMPANY_VALUE= "企业会员";
	
	//交易类型
	public final static String TRANS_TYPE_INCOME="01";
	public final static String TRANS_TYPE_INCOME_VALUE="入款";
	public final static String TRANS_TYPE_PAY="01";
	public final static String TRANS_TYPE_PAY_VALUE="出款";
	public final static String TRANS_TYPE_RETURN="01";
	public final static String TRANS_TYPE_RETURN_VALUE="退款";
	public final static String TRANS_TYPE_INNER_CIRCULATE="01";
	public final static String TRANS_TYPE_INNER_CIRCULATE_VALUE="内部转账";
	
	//交易方向
	public final static String TRANS_DIRECTION_BORROW="01";
	public final static String TRANS_DIRECTION_BORROW_VALUE="借";
	public final static String TRANS_DIRECTION_CREDIT="01";
	public final static String TRANS_DIRECTION_CREDIT_VALUE="贷";
	
	//币种
	public final static String CURRENCY_RMB="01";
	public final static String CURRENCY_RMB_VALUE="人民币";
	
	//生成编码标识符
	public static final String CUSTOMER_CODE = "customer";
	public static final String MERCHANT_CODE = "business";
	
	//生成编码含有时间戳
	public static final String IS_TIME_MARK = "01";
	
}
