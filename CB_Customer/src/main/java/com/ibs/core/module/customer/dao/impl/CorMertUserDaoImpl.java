/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.customer.dao.impl;

import org.hibernate.Hibernate;

import com.ibs.core.module.customer.dao.ICorMertUserDao;
import com.ibs.core.module.customer.domain.CorMertUser;
import com.ibs.core.module.customer.dto.CorMertUserDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_MERT_USER
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorMertUserDaoImpl extends BaseEntityDao<CorMertUser> implements ICorMertUserDao {

    public IPage<CorMertUserDto> findCorMertUserByPage(QueryPage queryPage, CorMertUserDto corMertUserDto) {
        logger.info("entering action::CorMertUserDaoImpl.findCorMertUserByPage()...");
        String custCode = corMertUserDto.getCustCode();
        String custName = corMertUserDto.getCustName();
         String merchantCode = corMertUserDto.getMerchantCode();
         String merchantName = corMertUserDto.getMerchantName();
        String timeType = corMertUserDto.getTimeType();
        String startDate = corMertUserDto.getStartDate();
        String endDate = corMertUserDto.getEndDate();

        StringBuffer sb = new StringBuffer(
                "select tc.ID id,tc.CUST_CODE as custCode,c.LOCAL_NAME as custName,m.MERCHANT_CODE merchantCode,cc.CORPORATE_CERT_TYPE as certType,"
                        + "m.MERCHANT_NAME merchantName,cc.CORPORATE_CERT_NUM as certNum,tc.USER_CODE as userCode,tc.PHONE_NUM as phoneNum,"
                        + "tc.EMAIL as email,tc.LAST_LOGIN_TIME as lastLoginTime, tc.LAST_LOGIN_IP as lastLoginIP,"
                        + " tc.PWD_UPDATE_TIME as pwdUpdateTime, tc.STATUS as status, tc.CREATOR as creator,"
                        + "tc.CREATE_TIME as createTime,tc.UPDATOR as updator,tc.UPDATE_TIME as updateTime"
                        + " from T_COR_MERT_USER tc inner join T_COR_CUST c on tc.cust_code = c.cust_code "
                        + " inner join T_COR_CUST_COMPANY cc on tc.cust_code = cc.cust_code "
                        + "left join T_COR_MERCHANT m on tc.CUST_CODE = m.CUST_CODE and  m.is_valid = '" + CorConstants.DATA_DICT__IS_VALID + "'"
                        + "  where  tc.is_valid = '" + CorConstants.DATA_DICT__IS_VALID + "' and c.is_valid = '"
                        + CorConstants.DATA_DICT__IS_VALID + "'");
        if (StringUtils.isNotEmpty(custCode)) {
            sb.append(" and tc.CUST_CODE  = '" + custCode + "' ");
        }
        if (StringUtils.isNotEmpty(custName)) {
            sb.append(" and c.LOCAL_NAME like '%" + custName + "%' ");
        }
         if (StringUtils.isNotEmpty(merchantCode)) {
         sb.append(" and m.MERCHANT_CODE = '" + merchantCode + "' ");
         }
         if (StringUtils.isNotEmpty(merchantName)) {
         sb.append(" and m.MERCHANT_NAME like '%" + merchantName + "%' ");
         }
        if (StringUtils.isNotEmpty(timeType)) {
            if (CorConstants.TIME_TYPE_UPDATE.equals(timeType)) {
                if (StringUtils.isNotEmpty(startDate)) {
                    sb.append(" and TO_CHAR(tc.UPDATE_TIME, 'yyyy-mm-dd hh24:mi:ss')")
                            .append(" >= '" + startDate + "' ");
                }
                if (StringUtils.isNotEmpty(endDate)) {
                    sb.append(" and TO_CHAR(tc.UPDATE_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(" <= '" + endDate + "' ");
                }
            }else{            
                if (StringUtils.isNotEmpty(startDate)) {
                    sb.append(" and TO_CHAR(tc.CREATE_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(" >= '" + startDate + "' ");
                }
                if (StringUtils.isNotEmpty(endDate)) {
                    sb.append(" and TO_CHAR(tc.CREATE_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(" <= '" + endDate + "' ");
                }
                
            }
        } 
        System.out.println("sql====" + sb.toString());
        queryPage.setSqlString(sb.toString());

        queryPage.addScalar("id", Hibernate.STRING);
        queryPage.addScalar("custCode", Hibernate.STRING);
        queryPage.addScalar("custName", Hibernate.STRING);
         queryPage.addScalar("merchantCode", Hibernate.STRING);
         queryPage.addScalar("merchantName", Hibernate.STRING);
        queryPage.addScalar("certType", Hibernate.STRING);
        queryPage.addScalar("certNum", Hibernate.STRING);
        queryPage.addScalar("userCode", Hibernate.STRING);
        queryPage.addScalar("phoneNum", Hibernate.STRING);
        queryPage.addScalar("email", Hibernate.STRING);
        queryPage.addScalar("lastLoginTime", Hibernate.STRING);
        queryPage.addScalar("lastLoginIP", Hibernate.STRING);
        queryPage.addScalar("pwdUpdateTime", Hibernate.TIMESTAMP);
        queryPage.addScalar("status", Hibernate.STRING);
        queryPage.addScalar("creator", Hibernate.STRING);
        queryPage.addScalar("createTime",Hibernate.TIMESTAMP);
        queryPage.addScalar("updator", Hibernate.STRING);
        queryPage.addScalar("updateTime", Hibernate.TIMESTAMP);

        IPage<CorMertUserDto> page = findPageBySql(queryPage, CorMertUserDto.class);
        return page;
    }

    public CorMertUser load(String id) {
        logger.info("entering action::CorMertUserDaoImpl.load()...");
        return super.load(id);
    }

    public void saveOrUpdate(CorMertUser corMertUser) {
        logger.info("entering action::CorMertUserDaoImpl.saveOrUpdate()...");
        super.saveOrUpdate(corMertUser);
    }

    @Override
    public CorMertUser getUserByUserCode(String userCode) {
        return super.loadBy("userCode", userCode);
    }

}
