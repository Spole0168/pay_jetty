package com.ibs.portal.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Md5Encoder extends MessageDigestAdapter {
	
	private static final String FILTER_FIELD_ENTITY_ID = "id";
	private static final String FILTER_FIELD_ENTITY_ENCRYPT_DATA = "encryptData";
	
    private static final Logger logger = LoggerFactory.getLogger(Md5Encoder.class);

	public Md5Encoder() {
		super("MD5");
	}
	
	/**
	 * 对数据库实体中的数据加密，去除字段id和encryptData
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public static String encryptEntity(Object entity) throws Exception {
		String json = JacksonUtil.toJsonWithFilter(entity, FILTER_FIELD_ENTITY_ID, FILTER_FIELD_ENTITY_ENCRYPT_DATA);
		String value = new Md5Encoder().encode(json);
		
		logger.debug(String.format("%s - %s", json, value));
		
        return value;
	}
	
	public static void main(String[] args) throws Exception{
		Md5Encoder m5 = new Md5Encoder();
		System.out.println(m5.encode("password")); // X03MO1qnZdYdgyfeuILPmQ==
		
		String str = "{\"paymentCode\":\"ZF010220170118163310153\",\"custCode\":\"C2017010910040\",\"merchantCode\":\"1101000210025\",\"operateType\":\"0102\",\"sourceOrderCode\":\"jhj1369\",\"amount\":1369,\"hcAmount\":2,\"hcCurrency\":null,\"currentBalance\":null,\"currency\":\"50\",\"recevie_currency\":null,\"recevie_amount\":null,\"accountType\":\"01\",\"status\":\"02\",\"fundReconStatus\":\"01\",\"mertReconStatus\":\"01\",\"fundReconTime\":1484582520000,\"mertReconTime\":1484668860000,\"paymentExplanation\":null,\"paymentRequestTime\":1484728399000,\"paymentTime\":1484728413321,\"handleTime\":1484728413321,\"payProductCode\":\"CP0205\",\"fundChannel\":\"672672011310005\",\"fundTransCode\":null,\"bankTransNum\":null,\"bandCardType\":\"02\",\"bankCode\":\"102\",\"bankName\":\"工商银行\",\"bankBranchName\":null,\"bankBranchCode\":null,\"bankCustName\":\"stephanie\",\"bankCardNum\":\"626262199408131111\",\"reserveBankCode\":null,\"reserveBankName\":null,\"reserveBankBranchName\":null,\"reservebankBranchCode\":null,\"reserveBankCustName\":null,\"reserveBankCardNum\":null,\"creator\":\"SYSADMIN\",\"createTime\":1484728413321,\"updator\":\"SYSADMIN\",\"updateTime\":1484728413321,\"isValid\":\"01\",\"isCreatedSett\":\"02\",\"isTamper\":null,\"sourceAccountCode\":null,\"targetAccountCode\":null,\"reason\":\"Thu Jan 19 14:30:04 CST 2017\"}";
		System.out.println(m5.encode(str));
	}
}
