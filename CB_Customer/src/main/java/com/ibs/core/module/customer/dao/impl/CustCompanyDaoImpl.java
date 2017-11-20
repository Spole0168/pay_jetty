/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.customer.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.DbTimestampType;
import org.hibernate.type.StringType;

import com.ibs.core.module.customer.dao.ICustCompanyDao;
import com.ibs.core.module.customer.domain.CorCustCompany;
import com.ibs.core.module.customer.dto.CustCompanyDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

/**
 * shangzhuzi
 * 
 * @author jh
 *
 */
public class CustCompanyDaoImpl extends BaseEntityDao<CorCustCompany> implements ICustCompanyDao {

    @Override
    public IPage<CustCompanyDto> findCustCompanyDtosBySql(QueryPage queryPage, CustCompanyDto custCompanyCondition) {

        QueryPage queryPagetemp = dealQueryPage(queryPage, custCompanyCondition);

        return super.findPageBySql(queryPagetemp, CustCompanyDto.class);
    }


    private QueryPage dealQueryPage(QueryPage queryPage, CustCompanyDto custCompanyCondition) {
        queryPage.clearSortMap();
        queryPage.clearQueryCondition();
        // 生成查询sql语句
        String querySql = makeSql(custCompanyCondition);
        queryPage.setSqlString(querySql);
        // 设置查询显示列
        queryPage.addScalar("id", new StringType())
        .addScalar("cid", new StringType())
                .addScalar("ccid", new StringType())
                .addScalar("caid", new StringType())
                .addScalar("cmuid", new StringType())
                .addScalar("custCode", new StringType())
                .addScalar("localName", new StringType())
                .addScalar("custType", new StringType())
                .addScalar("unitType", new StringType())
                .addScalar("corporateName", new StringType())
                .addScalar("corporateCertType", new StringType())
                .addScalar("corporateCertNum", new StringType())
                .addScalar("corporatePhonenum", new StringType())
                .addScalar("socialCreditId", new StringType())
                .addScalar("taxId", new StringType())
                .addScalar("companyCode", new StringType())
                .addScalar("businessLicense", new StringType())
                .addScalar("custStatus", new StringType())
                .addScalar("businessLicenseExpiry", new DbTimestampType())
                .addScalar("regTime", new DbTimestampType())
                .addScalar("companyRegAddr", new StringType())
                .addScalar("operateScope", new StringType())
                .addScalar("registeredFund", new BigDecimalType())
                .addScalar("registeredCurrency", new StringType())
                .addScalar("country", new StringType())
                .addScalar("provience", new StringType())
                .addScalar("city", new StringType())
                .addScalar("region", new StringType())
                .addScalar("contact", new StringType())
                .addScalar("contactPhone", new StringType())
                .addScalar("contactEmail", new StringType())
                .addScalar("companyFax", new StringType())
                .addScalar("postcode", new StringType())
                .addScalar("email", new StringType())
                .addScalar("companyWebsite", new StringType())
                .addScalar("voucher", new StringType())
                .addScalar("createTime", new DbTimestampType())
                .addScalar("creator", new StringType())
                .addScalar("updateTime", new DbTimestampType())
                .addScalar("updator", new StringType())
                .addScalar("remark", new StringType())
                .addScalar("status", new StringType())
                .addScalar("auditReason", new StringType())
                .addScalar("auditPerson", new StringType())
                .addScalar("sourceCode", new StringType())
                .addScalar("isValid", new StringType())
                .addScalar("auditTime", new DbTimestampType());
        return queryPage;
    }

    /**
     * 构建查询sql语句
     * 
     * @param custCompanyCondition T_COR_CUST, T_COR_CUST_COMPANY, T_COR_AUDIT
     * @return
     */
    private static String makeSql(CustCompanyDto custCompanyCondition) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select ")
                .append("cc.ID as id , ")
                .append("cc.ID as ccid , ")
                .append("cmu.ID as cmuid , ")
                .append("cc.CUST_CODE as custCode , ")
                .append("c.ID as cid , ")
                .append("c.LOCAL_NAME as localName , ")
                .append("c.CUST_TYPE as custType , ")
                .append("cc.UNIT_TYPE as unitType , ")
                .append("cc.CORPORATE_NAME as corporateName , ")
                .append("cc.CORPORATE_CERT_TYPE as corporateCertType , ")
                .append("cc.CORPORATE_CERT_NUM as corporateCertNum , ")
                .append("cc.CORPORATE_PHONENUM as corporatePhonenum , ")
                .append("cc.SOCIAL_CREDIT_ID as socialCreditId , ")
                .append("cc.TAX_ID as taxId , ")
                .append("cc.COMPANY_CODE as companyCode , ")
                .append("cc.BUSINESS_LICENSE as businessLicense , ")
                .append("cc.BUSINESS_LICENSE_EXPIRY as businessLicenseExpiry , ")
                .append("cc.REG_TIME as regTime , ")
                .append("cc.COMPANY_REG_ADDR as companyRegAddr , ")
                .append("cc.OPERATE_SCOPE as operateScope , ")
                .append("cc.REGISTERED_FUND as registeredFund , ")
                .append("cc.REGISTERED_CURRENCY as registeredCurrency , ")
                .append("c.COUNTRY as country , ")
                .append("c.PROVIENCE as provience , ")
                .append("c.CITY as city , ")
                .append("c.REGION as region , ")
                .append("cc.CONTACT as contact , ")
                .append("cc.CONTACT_PHONE as contactPhone , ")
                .append("cc.CONTACT_EMAIL as contactEmail , ")
                .append("cc.COMPANY_FAX as companyFax , ")
                .append("c.POSTCODE as postcode , ")
                .append("cc.EMAIL as email , ")
                .append("cc.COMPANY_WEBSITE as companyWebsite , ")
                .append("cc.VOUCHER as voucher, ")
                .append("cc.CREATE_TIME as createTime , ")
                .append("cc.CREATOR as creator , ")
                .append("cc.UPDATE_TIME as updateTime , ")
                .append("cc.UPDATOR as updator , ")
                .append("cc.IS_VALID as isValid , ")
                .append("c.REMARK as remark , ")
                .append("c.STATUS as custStatus , ")
                .append("ca.ID as caid , ")
                .append("ca.STATUS as status , ")
                .append("ca.AUDIT_REASON as auditReason , ")
                .append("ca.CREATOR as auditPerson , ")
                .append("ca.SOURCE_CODE as sourceCode , ")
                .append("ca.CREATE_TIME as auditTime  ")

                // .append(" from T_COR_CUST c, T_COR_CUST_COMPANY cc , T_COR_AUDIT ca ")
                .append(" from  T_COR_CUST_COMPANY cc  ")
                // T_COR_CUST c,
                .append(" left join T_COR_CUST c on   cc.CUST_CODE=c.CUST_CODE ")
                .append(" left join T_COR_AUDIT ca on   cc.CUST_CODE = ca.SOURCE_CODE ")
                .append(" and ca.AUDIT_TYPE = '")
                .append(CorConstants.AUDIT_TYPE_CUSTOMER)
                .append("'")
                .append(" left join T_COR_MERT_USER cmu on   cmu.CUST_CODE=cc.CUST_CODE ")

                .append(" where 1=1")
                .append(" and c.IS_VALID = '" + CorConstants.DATA_DICT__IS_VALID + "' and cc.IS_VALID = '" + CorConstants.DATA_DICT__IS_VALID + "'");

        String id = custCompanyCondition.getId();
        String custCode = custCompanyCondition.getCustCode();
        String localName = custCompanyCondition.getLocalName();
        String unitType = custCompanyCondition.getUnitType();
        String socialCreditId = custCompanyCondition.getSocialCreditId();
        String businessLicense = custCompanyCondition.getBusinessLicense();
        String startCreateTime = custCompanyCondition.getStartCreateTime();
        String endCreateTime = custCompanyCondition.getEndCreateTime();
        String status = custCompanyCondition.getStatus();
        String custStatus = custCompanyCondition.getCustStatus();
        if (StringUtils.isNotEmpty(id)) {
            sql.append(" and cc.id ='").append(id).append("'");
        } else {
            if (StringUtils.isNotEmpty(custCode)) {
                sql.append(" and cc.CUST_CODE ='").append(custCode).append("'");
            }
            if (StringUtils.isNotEmpty(localName)) {
                sql.append(" and c.LOCAL_NAME like '%" + localName + "%'");
            }
            if (StringUtils.isNotEmpty(unitType)) {
                sql.append(" and cc.UNIT_TYPE ='").append(unitType).append("'");
            }
            if (StringUtils.isNotEmpty(socialCreditId)) {
                sql.append(" and cc.SOCIAL_CREDIT_ID  ='").append(socialCreditId).append("'");
            }
            if (StringUtils.isNotEmpty(businessLicense)) {
                sql.append(" and cc.BUSINESS_LICENSE  ='").append(businessLicense).append("'");
            }
            if (StringUtils.isNotEmpty(startCreateTime)) {
                sql.append(" and TO_CHAR(cc.CREATE_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(
                        " >= '" + startCreateTime + "' ");
            }
            if (StringUtils.isNotEmpty(endCreateTime)) {
                sql.append(" and TO_CHAR(cc.CREATE_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(
                        " <= '" + endCreateTime + "' ");
            }
            if (StringUtils.isNotEmpty(status)) {
                sql.append(" and ca.status  ='").append(status).append("'");
            }
            if (StringUtils.isNotEmpty(custStatus)) {
                sql.append(" and c.status  ='").append(custStatus).append("'");
            }
        }
        sql.append(" order by cc.UPDATE_TIME desc");
        return sql.toString();
    }
    

    @Override
    public void saveOrUpdate(CorCustCompany corCustCompany) {
        super.saveOrUpdate(corCustCompany);

    }

    @Override
    public CorCustCompany loadById(String id) {
        return load(id);
    }

    @Override
    public CustCompanyDto getCustCompanyDtoByCondition(CustCompanyDto custCompanyCondition) {
        QueryPage queryPage = new QueryPage(10, 1);
        IPage<CustCompanyDto> page = this.findCustCompanyDtosBySql(queryPage, custCompanyCondition);
        Collection<CustCompanyDto> rows = page.getRows();
        if (rows.isEmpty()) {
            return null;
        }
        return rows.iterator().next();
    }

    @Override
    public List<CorCustCompany> getCorCustCompanyByConditionAND(CorCustCompany corCustCompany) {
        Criteria criteria = super.getSession().createCriteria(CorCustCompany.class);
        criteria.add(Restrictions.eq("isValid", CorConstants.DATA_DICT__IS_VALID));
        if (null != corCustCompany && null != corCustCompany.getCustCode()) {
            criteria.add(Restrictions.eq("custCode", corCustCompany.getCustCode()));
        }
        if (null != corCustCompany && null != corCustCompany.getSocialCreditId()) {
            criteria.add(Restrictions.eq("socialCreditId", corCustCompany.getSocialCreditId()));
        }
        if (null != corCustCompany && null != corCustCompany.getBusinessLicense()) {
            criteria.add(Restrictions.eq("businessLicense", corCustCompany.getBusinessLicense()));
        }
        if (null != corCustCompany && null != corCustCompany.getCompanyCode()) {
            criteria.add(Restrictions.eq("companyCode", corCustCompany.getCompanyCode()));
        }
        List<CorCustCompany> list = criteria.list();
        if (null != list && list.size() > 0) {
            return list;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibs.core.module.customer.dao.ICustCompanyDao#
     * getCorCustCompanyByCondition(com.ibs.core.module.customer.domain.
     * CorCustCompany)
     */
    @Override
    public IPage<CustCompanyDto> getCorCustCompanyByConditionOR(CustCompanyDto corCustCompany) {
        QueryPage queryPagetemp = dealCheckQueryPage(new QueryPage(100, 1), corCustCompany);
        
        return super.findPageBySql(queryPagetemp, CustCompanyDto.class);
        
    }
    private QueryPage dealCheckQueryPage(QueryPage queryPage, CustCompanyDto custCompanyCondition) {
        queryPage.clearSortMap();
        queryPage.clearQueryCondition();
        // 生成查询sql语句
        String querySql = makeSqlOR(custCompanyCondition);
        queryPage.setSqlString(querySql);
        // 设置查询显示列
        queryPage.addScalar("id", new StringType())
        .addScalar("cid", new StringType())
                .addScalar("ccid", new StringType())
                .addScalar("caid", new StringType())
                .addScalar("cmuid", new StringType())
                .addScalar("custCode", new StringType())
                .addScalar("localName", new StringType())
                .addScalar("custType", new StringType())
                .addScalar("unitType", new StringType())
                .addScalar("corporateName", new StringType())
                .addScalar("corporateCertType", new StringType())
                .addScalar("corporateCertNum", new StringType())
                .addScalar("corporatePhonenum", new StringType())
                .addScalar("socialCreditId", new StringType())
                .addScalar("taxId", new StringType())
                .addScalar("companyCode", new StringType())
                .addScalar("businessLicense", new StringType())
                .addScalar("custStatus", new StringType())
                .addScalar("businessLicenseExpiry", new DbTimestampType())
                .addScalar("regTime", new DbTimestampType())
                .addScalar("companyRegAddr", new StringType())
                .addScalar("operateScope", new StringType())
                .addScalar("registeredFund", new BigDecimalType())
                .addScalar("registeredCurrency", new StringType())
                .addScalar("country", new StringType())
                .addScalar("provience", new StringType())
                .addScalar("city", new StringType())
                .addScalar("region", new StringType())
                .addScalar("contact", new StringType())
                .addScalar("contactPhone", new StringType())
                .addScalar("contactEmail", new StringType())
                .addScalar("companyFax", new StringType())
                .addScalar("postcode", new StringType())
                .addScalar("email", new StringType())
                .addScalar("companyWebsite", new StringType())
                .addScalar("voucher", new StringType())
                .addScalar("createTime", new DbTimestampType())
                .addScalar("creator", new StringType())
                .addScalar("updateTime", new DbTimestampType())
                .addScalar("updator", new StringType())
                .addScalar("remark", new StringType())
                .addScalar("status", new StringType())
                .addScalar("auditReason", new StringType())
                .addScalar("auditPerson", new StringType())
                .addScalar("sourceCode", new StringType())
                .addScalar("isValid", new StringType())
                .addScalar("auditTime", new DbTimestampType());
        return queryPage;
    }


    /**
     * <p>
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月5日
     * <p>Title		: 	CustCompanyDaoImpl
     * <p>return_type	:	String
     */
    private static String makeSqlOR(CustCompanyDto custCompanyCondition) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select ")
                .append("cc.ID as id , ")
                .append("cc.ID as ccid , ")
                .append("cmu.ID as cmuid , ")
                .append("cc.CUST_CODE as custCode , ")
                .append("c.ID as cid , ")
                .append("c.LOCAL_NAME as localName , ")
                .append("c.CUST_TYPE as custType , ")
                .append("cc.UNIT_TYPE as unitType , ")
                .append("cc.CORPORATE_NAME as corporateName , ")
                .append("cc.CORPORATE_CERT_TYPE as corporateCertType , ")
                .append("cc.CORPORATE_CERT_NUM as corporateCertNum , ")
                .append("cc.CORPORATE_PHONENUM as corporatePhonenum , ")
                .append("cc.SOCIAL_CREDIT_ID as socialCreditId , ")
                .append("cc.TAX_ID as taxId , ")
                .append("cc.COMPANY_CODE as companyCode , ")
                .append("cc.BUSINESS_LICENSE as businessLicense , ")
                .append("cc.BUSINESS_LICENSE_EXPIRY as businessLicenseExpiry , ")
                .append("cc.REG_TIME as regTime , ")
                .append("cc.COMPANY_REG_ADDR as companyRegAddr , ")
                .append("cc.OPERATE_SCOPE as operateScope , ")
                .append("cc.REGISTERED_FUND as registeredFund , ")
                .append("cc.REGISTERED_CURRENCY as registeredCurrency , ")
                .append("c.COUNTRY as country , ")
                .append("c.PROVIENCE as provience , ")
                .append("c.CITY as city , ")
                .append("c.REGION as region , ")
                .append("cc.CONTACT as contact , ")
                .append("cc.CONTACT_PHONE as contactPhone , ")
                .append("cc.CONTACT_EMAIL as contactEmail , ")
                .append("cc.COMPANY_FAX as companyFax , ")
                .append("c.POSTCODE as postcode , ")
                .append("cc.EMAIL as email , ")
                .append("cc.COMPANY_WEBSITE as companyWebsite , ")
                .append("cc.VOUCHER as voucher, ")
                .append("cc.CREATE_TIME as createTime , ")
                .append("cc.CREATOR as creator , ")
                .append("cc.UPDATE_TIME as updateTime , ")
                .append("cc.UPDATOR as updator , ")
                .append("cc.IS_VALID as isValid , ")
                .append("c.REMARK as remark , ")
                .append("c.STATUS as custStatus , ")
                .append("ca.ID as caid , ")
                .append("ca.STATUS as status , ")
                .append("ca.AUDIT_REASON as auditReason , ")
                .append("ca.CREATOR as auditPerson , ")
                .append("ca.SOURCE_CODE as sourceCode , ")
                .append("ca.CREATE_TIME as auditTime  ")

                // .append(" from T_COR_CUST c, T_COR_CUST_COMPANY cc , T_COR_AUDIT ca ")
                .append(" from  T_COR_CUST_COMPANY cc  ")
                // T_COR_CUST c,
                .append(" left join T_COR_CUST c on   cc.CUST_CODE=c.CUST_CODE ")
                .append(" left join T_COR_AUDIT ca on   cc.CUST_CODE = ca.SOURCE_CODE ")
                .append(" and ca.AUDIT_TYPE = '")
                .append(CorConstants.AUDIT_TYPE_CUSTOMER)
                .append("'")
                .append(" left join T_COR_MERT_USER cmu on   cmu.CUST_CODE=cc.CUST_CODE ")

                .append(" where 1=1")
                .append(" and c.IS_VALID = '" + CorConstants.DATA_DICT__IS_VALID + "' and cc.IS_VALID = '" + CorConstants.DATA_DICT__IS_VALID + "'");

        String custCode = custCompanyCondition.getCustCode();
        String localName = custCompanyCondition.getLocalName();
        String socialCreditId = custCompanyCondition.getSocialCreditId();
        String businessLicense = custCompanyCondition.getBusinessLicense();
        sql.append(" and (  ");
//        if (StringUtils.isNotEmpty(custCode)) {
//            sql.append(" or cc.CUST_CODE ='").append(custCode).append("'");
//        }
        if (StringUtils.isNotEmpty(localName)) {
            sql.append(" c.LOCAL_NAME ='" + localName + "'");
        }
        if (StringUtils.isNotEmpty(socialCreditId)) {
            sql.append(" or cc.SOCIAL_CREDIT_ID  ='").append(socialCreditId).append("'");
        }
        if (StringUtils.isNotEmpty(businessLicense)) {
            sql.append(" or cc.BUSINESS_LICENSE  ='").append(businessLicense).append("'");
        }
        sql.append(")");
        sql.append(" order by cc.UPDATE_TIME desc");
        return sql.toString();
    }
    public static void main(String[] args) {
        CustCompanyDto cd = new CustCompanyDto();
        cd.setLocalName("TT");
        cd.setSocialCreditId("socialCreditId");
        cd.setBusinessLicense("businessLicense");
        System.out.println(makeSqlOR(cd));
    }
}
