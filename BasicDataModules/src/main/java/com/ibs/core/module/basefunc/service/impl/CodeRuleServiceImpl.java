package com.ibs.core.module.basefunc.service.impl;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import com.ibs.core.module.basefunc.dao.ICodeRuleDao;
import com.ibs.core.module.basefunc.domain.CodeRule;
import com.ibs.core.module.basefunc.service.ICodeRuleService;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.util.DateUtils;

public class CodeRuleServiceImpl implements ICodeRuleService {

	private ICodeRuleDao codeRuleDao;

	/**
	 * 生成账户编码 ZH+账户属性（2位）+账户类型（2位）+币种（3位）+随机数（12位）；
	 * 账户属性：个人01，企业02，内部03；账户类型基本账户01，保证金账户02；币种：人民币156。 长度 21位
	 * ZH0101156984589484838
	 * 
	 * @param accountAttr
	 * @param accountType
	 * @param currency
	 * @return
	 */
	public String getAccountCode(String accountAttr, String accountType, String currency) {

		String result = null;
		CodeRule rule = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_ACCOUNT);
		String str = "0123456789";
		Random random = new Random(new Long(DateUtils.convert(new Date(), "yyyyMMddHHmmss")));
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < rule.getSuffixLength(); i++) {
			int num = random.nextInt(10);
			buf.append(str.charAt(num));
		}
		synchronized (rule) {
			if (rule != null) {
				result = rule.getPrefix() + accountAttr + accountType + currency + buf.toString();
				rule.setCurrentValue(result);
				// to do
				rule.setUpdator(CorConstants.UPDATOR);
				rule.setUpdateTime(new Date());
				codeRuleDao.update(rule);
			}
			return result;
		}
	}

	/**
	 * 生成登录密码 必须要包含数字与字母不同组合，，字母大写，共8位，系统随机产生 长度 8位 SE1237FR
	 * 
	 * @return
	 */
	public String getCorCustLoginPwd() {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random(new Long(DateUtils.convert(new Date(), "yyyyMMddHHmmss")));
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			int num = random.nextInt(36);
			buf.append(str.charAt(num));
		}
		return buf.toString();
	}

	/**
	 * 生成登录名 同会员编号 长度 14位 C2016111700001
	 * 
	 * @param corCustCode
	 * @return
	 */
	public String getCorCustLoginName(String corCustCode) {

		return corCustCode;
	}

	/**
	 * 生成二级产品编码 CP+一级产品代码（2位）+二级产品代码（2位） 长度 6位 CP0101
	 * 
	 * @param firProductCode
	 * @param secProductCode
	 * @return
	 */
	public String getSecondProductCode(String firProductCode, String secProductCode) {
		String result = null;
		CodeRule rule = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_SECOND_PRODUCT);
		if (rule != null) {
			result = rule.getPrefix() + firProductCode + secProductCode;
		}
		return result;
	}

	/**
	 * 生成资金单编号 ZJ+交易方向（2位）+yyyyMMddHHMM（12位）+流水号（5位） 长度 21位
	 * ZJ0120161209153000001
	 * 
	 * @param direction
	 * @return
	 */
	public String getFundOrderCode(String direction) {
		String result = null;
		CodeRule rule = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_FUND_ORDER);
		String dateString = DateUtils.convert(new Date(), "yyyyMMddHHmm");
		synchronized (rule) {
			if (rule != null) {
				String suffixCurVal = "" + (new Long(rule.getSuffixCurrentValue()) + 1);
				if(suffixCurVal.length()>rule.getSuffixLength()){
					suffixCurVal = suffixCurVal.substring(0, suffixCurVal.length()-1);
				}
				result = rule.getPrefix() + direction + dateString + suffixCurVal;
				rule.setSuffixCurrentValue(suffixCurVal);
				rule.setCurrentValue(result);
				// to do
				rule.setUpdator(CorConstants.UPDATOR);
				rule.setUpdateTime(new Date());
				codeRuleDao.update(rule);
			}
			return result;
		}
	}

	/**
	 * 生成资金通道编码 国家代码（2位）+ 合作机构类型（2位）+ 合作机构代码（4位）+ 合作机构自定义代码（4位）+二级产品类型（2位）
	 * 国家代码：采用ISO 3166-1 国际标准，2位字母的国家代码。
	 * 合作机构类型：01：合作商业银行；02：合作支付机构；03：合作卡组织；99：其他。 合作机构代码：4位数字编码。例：1234。
	 * 合作机构自定义代码：4位英文(数字)编码。例如：ICBC。 长度 14位 CN011234ICBC01
	 * 
	 * @param countryCode
	 * @param coopOrgType
	 * @param coopOrgCode
	 * @param coopOrgSelfCode
	 * @param seProductType
	 * @return
	 */
	public String getFundChannelCode(String countryCode, String coopOrgType, String coopOrgCode, String coopOrgSelfCode,
			String seProductType) {
		String result = countryCode + countryCode + coopOrgType + coopOrgCode + coopOrgSelfCode + seProductType;
		return result;
	}

	/**
	 * 生成商户编码 4位行政区代码+4位行业代码+5位序列号 长度 13位 C2016111700001
	 * 
	 * @return
	 */
	public String getMenchantCode(String districtCode, String cocationCode) {
		String result = null;
		CodeRule rule = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_MENCHANT);
		synchronized (rule) {
			if (rule != null) {
				String suffixCurVal = "" + (new Long(rule.getSuffixCurrentValue()) + 1);
				if(suffixCurVal.length()>rule.getSuffixLength()){
					suffixCurVal = suffixCurVal.substring(0, suffixCurVal.length()-1);
				}
				result = districtCode + cocationCode + suffixCurVal;
				rule.setSuffixCurrentValue(suffixCurVal);
				rule.setCurrentValue(result);
				// to do
				rule.setUpdator(CorConstants.UPDATOR);
				rule.setUpdateTime(new Date());
				codeRuleDao.update(rule);
			}
			return result;
		}
	}

	/**
	 * 生成核心用户编码 C+8位时间戳+5位数字流水 长度 14位 C2016111700001
	 * 
	 * @return
	 */
	public String getCorCustCode() {
		String result = null;
		CodeRule rule = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_COR_CUST_COMPANY);
		String dateString = DateUtils.convert(new Date(), "yyyyMMdd");
		synchronized (rule) {
			if (rule != null) {
				String suffixCurVal = "" + (new Long(rule.getSuffixCurrentValue()) + 1);
				if(suffixCurVal.length()>rule.getSuffixLength()){
					suffixCurVal = suffixCurVal.substring(0, suffixCurVal.length()-1);
				}
				result = rule.getPrefix() + dateString + suffixCurVal;
				rule.setSuffixCurrentValue(suffixCurVal);
				rule.setCurrentValue(result);
				// to do
				rule.setUpdator(CorConstants.UPDATOR);
				rule.setUpdateTime(new Date());
				codeRuleDao.update(rule);
			}
			return result;
		}
	}

	/**
	 * 生成支付单编码 ZF+交易类型（4位）+yyyyMMddHHMM（12位）+流水号（5位） 长度 21位
	 * ZF0120161209153000001
	 * 
	 * @param operateType
	 * @return
	 */
	@Override
	public String getPayOrderCode(String operateType) {
		String result = null;
		CodeRule rule = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_PAY_ORDER);
		String dateString = DateUtils.convert(new Date(), "yyyyMMddHHmm");
		synchronized (rule) {
			if (rule != null) {
				String suffixCurVal = "" + (new Long(rule.getSuffixCurrentValue()) + 1);
				if(suffixCurVal.length()>rule.getSuffixLength()){
					suffixCurVal = suffixCurVal.substring(0, suffixCurVal.length()-1);
				}
				result = rule.getPrefix() + operateType + dateString + suffixCurVal;
				rule.setSuffixCurrentValue(suffixCurVal);
				rule.setCurrentValue(result);
				rule.setUpdator(CorConstants.UPDATOR);
				rule.setUpdateTime(new Date());
				codeRuleDao.update(rule);
			}
			return result;
		}
	}

	@Override
	public String getAccountDetailCode() {
		String result = UUID.randomUUID().toString().replace("-", "");
		return result;
	}


	@Override
	public String getTimeStamp() {
		return DateUtils.convert(new Date(), "yyyyMMddHHmmssSSS");

	}

	@Override
	public String getFeeCode(boolean isMerchant, String seProductCode) {
		String result = null;
		String attr = isMerchant ? "02" : "01";
		CodeRule rule = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_FEE_ORDER);
		synchronized (rule) {
			if (rule != null) {
				String suffixCurVal = "" + (new Long(rule.getSuffixCurrentValue()) + 1);
				if(suffixCurVal.length()>rule.getSuffixLength()){
					suffixCurVal = suffixCurVal.substring(0, suffixCurVal.length()-1);
				}
				result = rule.getPrefix() + attr + seProductCode.substring(2, 6) + suffixCurVal;
				rule.setSuffixCurrentValue(suffixCurVal);
				rule.setCurrentValue(result);
				rule.setUpdator(CorConstants.UPDATOR);
				rule.setUpdateTime(new Date());
				codeRuleDao.update(rule);
			}
			return result;
		}
	}

	@Override
	public String getCorCustPersonalCode() {
		String result = null;
		CodeRule rule = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_COR_CUST_PERSONAL);
		String dateString = DateUtils.convert(new Date(), "yyyyMMdd");
		synchronized (rule) {
			if (rule != null) {
				String suffixCurVal = "" + (new Long(rule.getSuffixCurrentValue()) + 1);
				if(suffixCurVal.length()>rule.getSuffixLength()){
					suffixCurVal = suffixCurVal.substring(0, suffixCurVal.length()-1);
				}
				result = rule.getPrefix() + dateString + suffixCurVal;
				rule.setSuffixCurrentValue(suffixCurVal);
				rule.setCurrentValue(result);
				rule.setUpdator(CorConstants.UPDATOR);
				rule.setUpdateTime(new Date());
				codeRuleDao.update(rule);
			}
			return result;
		}
	}

	@Override
	public String getIncomeOrderCode(String operateType) {
		String result = null;
		CodeRule rule = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_INCOME_ORDER);
		String dateString = DateUtils.convert(new Date(), "yyyyMMddHHmm");
		synchronized (rule) {
			if (rule != null) {
				String suffixCurVal = "" + (new Long(rule.getSuffixCurrentValue()) + 1);
				if(suffixCurVal.length()>rule.getSuffixLength()){
					suffixCurVal = suffixCurVal.substring(0, suffixCurVal.length()-1);
				}
				result = rule.getPrefix() + operateType + dateString + suffixCurVal;
				rule.setSuffixCurrentValue(suffixCurVal);
				rule.setCurrentValue(result);
				rule.setUpdator(CorConstants.UPDATOR);
				rule.setUpdateTime(new Date());
				codeRuleDao.update(rule);
			}
			return result;
		}
	}

	@Override
	public String getOutOrderCode(String operateType) {
		String result = null;
		CodeRule rule = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_OUT_ORDER);
		String dateString = DateUtils.convert(new Date(), "yyyyMMddHHmm");
		synchronized (rule) {
			if (rule != null) {
				String suffixCurVal = "" + (new Long(rule.getSuffixCurrentValue()) + 1);
				if(suffixCurVal.length()>rule.getSuffixLength()){
					suffixCurVal = suffixCurVal.substring(0, suffixCurVal.length()-1);
				}
				result = rule.getPrefix() + operateType + dateString + suffixCurVal;
				rule.setSuffixCurrentValue(suffixCurVal);
				rule.setCurrentValue(result);
				rule.setUpdator(CorConstants.UPDATOR);
				rule.setUpdateTime(new Date());
				codeRuleDao.update(rule);
			}
			return result;
		}
	}

	@Override
	public String getSettleOrderCode() {
		String result = null;
		CodeRule rule = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_SETTLE_ORDER);
		String dateString = DateUtils.convert(new Date(), "yyyyMMdd");
		synchronized (rule) {
			if (rule != null) {
				String suffixCurVal = "" + (new Long(rule.getSuffixCurrentValue()) + 1);
				if(suffixCurVal.length()>rule.getSuffixLength()){
					suffixCurVal = suffixCurVal.substring(0, suffixCurVal.length()-1);
				}
				result = rule.getPrefix() + dateString + suffixCurVal;
				rule.setSuffixCurrentValue(suffixCurVal);
				rule.setCurrentValue(result);
				rule.setUpdator(CorConstants.UPDATOR);
				rule.setUpdateTime(new Date());
				codeRuleDao.update(rule);
			}
			return result;
		}
	}
	
	@Override
	public String getTransferOrderCode(String operateType) {
		String result = null;
		CodeRule rule = codeRuleDao.getByCodeType(CorConstants.CODE_TYPE_TRANSFER_ORDER);
		String dateString = DateUtils.convert(new Date(), "yyyyMMddHHmm");
		synchronized (rule) {
			if (rule != null) {
				String suffixCurVal = "" + (new Long(rule.getSuffixCurrentValue()) + 1);
				if(suffixCurVal.length()>rule.getSuffixLength()){
					suffixCurVal = suffixCurVal.substring(0, suffixCurVal.length()-1);
				}
				result = rule.getPrefix() + operateType + dateString + suffixCurVal;
				rule.setSuffixCurrentValue(suffixCurVal);
				rule.setCurrentValue(result);
				rule.setUpdator(CorConstants.UPDATOR);
				rule.setUpdateTime(new Date());
				codeRuleDao.update(rule);
			}
			return result;
		}
	}

	public ICodeRuleDao getCodeRuleDao() {
		return codeRuleDao;
	}
	
	public void setCodeRuleDao(ICodeRuleDao codeRuleDao) {
		this.codeRuleDao = codeRuleDao;
	}
}
