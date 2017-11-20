/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.customer.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.DbTimestampType;
import org.hibernate.type.StringType;

import com.ibs.core.module.customer.dao.ICustPersonalDao;
import com.ibs.core.module.customer.domain.CorCustPersonal;
import com.ibs.core.module.customer.dto.CustCompanyDto;
import com.ibs.core.module.customer.dto.CustPersonalDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CUST_PERSONAL
 * @modify		: your comments goes here (author,date,reason).
 */
public class CustPersonalDaoImpl extends BaseEntityDao<CorCustPersonal> implements
		ICustPersonalDao {
   
    @Override
    public IPage<CustPersonalDto> findCustPersonalDtosBySql(QueryPage queryPage, CustPersonalDto custPersonalCondition) {
        
        QueryPage queryPagetemp = dealQueryPage(queryPage, custPersonalCondition);
        
        return super.findPageBySql(queryPagetemp, CustPersonalDto.class);
        
    }

    /**
     * <p> 
     * <p>
     * <p>
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月17日
     * <p>Title			: 	CorCustPersonalDaoImpl
     * <p>return_type	:	QueryPage
     */
    private QueryPage dealQueryPage(QueryPage queryPage, CustPersonalDto custPersonalCondition) {
        queryPage.clearSortMap();
        queryPage.clearQueryCondition();
        // 生成查询sql语句
        String querySql = makeSql(custPersonalCondition);
        queryPage.setSqlString(querySql);
        // 设置查询显示列
        queryPage
        .addScalar("id", new StringType())
        .addScalar("cid", new StringType())
        .addScalar("cpid", new StringType())
        
        .addScalar("custCode", new StringType())
        .addScalar("custType", new StringType())
        .addScalar("country", new StringType())
        .addScalar("provience", new StringType())
        .addScalar("city", new StringType())
        .addScalar("region", new StringType())
        .addScalar("localName", new StringType())
        .addScalar("realNameLeve", new StringType())
        .addScalar("firstName", new StringType())
        .addScalar("lastName", new StringType())
        .addScalar("certType", new StringType())
        .addScalar("certNum", new StringType())
        .addScalar("certExpireDate", new DbTimestampType())
        .addScalar("certCopy", new StringType())
        .addScalar("custStatus", new StringType())
        .addScalar("regTime", new DbTimestampType())
        .addScalar("dataSource", new StringType())
        .addScalar("creator", new StringType())
        .addScalar("createTime", new DbTimestampType())
        .addScalar("creator", new StringType())
        .addScalar("updateTime", new DbTimestampType())
        .addScalar("updator", new StringType())
        .addScalar("remark", new StringType())
        .addScalar("isValid", new StringType())
        .addScalar("postcode", new StringType())
        
        .addScalar("gender", new StringType())
        .addScalar("birthday", new DbTimestampType())
        .addScalar("district", new StringType())
        .addScalar("addr", new StringType())
        .addScalar("highestEdu", new StringType())
        .addScalar("occupation", new StringType())
        .addScalar("jobTitle", new StringType())
        .addScalar("employer", new StringType())
        .addScalar("email", new StringType())
        .addScalar("isMarried", new StringType())
        .addScalar("spouseCountry", new StringType())
        .addScalar("spouseLocalName", new StringType())
        .addScalar("spouseFirstName", new StringType())
        .addScalar("spouseLastName", new StringType())
        .addScalar("spouseCertType", new StringType())
        .addScalar("spouseCertNum", new StringType())
        .addScalar("spouseCertExpireDate", new DbTimestampType())
        .addScalar("spousePhonenum", new StringType())
        .addScalar("mobilePhone", new StringType())
        .addScalar("telephone", new StringType())
        .addScalar("fax", new StringType())
        .addScalar("qq", new StringType())
        .addScalar("industry", new StringType());

        return queryPage;
    }

    /**
     * <p> 
     * <p>
     * <p>
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月17日
     * <p>Title			: 	CorCustPersonalDaoImpl
     * <p>return_type	:	String
     */
    private static String makeSql(CustPersonalDto custPersonalCondition) {
        StringBuffer sql = new StringBuffer();
        sql
        .append(" select ")
        .append("c.ID as cid , ")
        .append("c.CUST_CODE as custCode, ")
        .append("c.CUST_TYPE as custType, ")
        .append("c.COUNTRY as country , ")
        .append("c.PROVIENCE as provience , ")
        .append("c.CITY as city , ")
        .append("c.REGION as region, ")
        .append("c.POSTCODE as postcode, ")
        .append("c.LOCAL_NAME as localName, ")
        .append("c.REAL_NAME_LEVE as realNameLeve , ")
        .append("c.FIRST_NAME as firstName, ")
        .append("c.LAST_NAME as lastName, ")
        .append("c.CERT_TYPE as certType, ")
        .append("c.CERT_NUM as certNum , ")
        .append("c.CERT_EXPIRE_DATE as certExpireDate, ")
        .append("c.CERT_COPY as certCopy, ")
        .append("c.STATUS  as  custStatus  , ")
        .append("c.REG_TIME  as regTime , ")
        .append("c.DATA_SOURCE as  dataSource, ")
//        .append("c.CREATOR as  creator , ")
//        .append("c.CREATE_TIME as  createTime, ")
//        .append("c.UPDATOR as  updator , ")
//        .append("c.UPDATE_TIME as  updateTime, ")
//        .append("c.IS_VALID  as isValid , ")
        .append("c.REMARK  as  remark ,")
        
        .append("cp.ID as cpid, ")
        .append("cp.ID as id, ")
//      .append("cp.CUST_CODE as custCode, ")
        .append("cp.GENDER as gender , ")
        .append("cp.BIRTHDAY as birthday, ")
//        .append("cp.COUNTRY as country, ")
//        .append("cp.PROVIENCE as provience , ")
//        .append("cp.CITY as city , ")
        .append("cp.DISTRICT as district, ")
        .append("cp.ADDR as addr , ")
        .append("cp.HIGHEST_EDU as highestEdu, ")
        .append("cp.OCCUPATION as occupation, ")
        .append("cp.JOB_TITLE as jobTitle, ")
        .append("cp.EMPLOYER as employer, ")
        .append("cp.EMAIL as email, ")
//      .append("cp.POSTCODE as postcode, ")
        .append("cp.IS_MARRIED as isMarried , ")
        .append("cp.SPOUSE_COUNTRY as spouseCountry, ")
        .append("cp.SPOUSE_LOCAL_NAME as spouseLocalName, ")
        .append("cp.SPOUSE_FIRST_NAME as spouseFirstName, ")
        .append("cp.SPOUSE_LAST_NAME as spouseLastName , ")
        .append("cp.SPOUSE_CERT_TYPE as spouseCertType , ")
        .append("cp.SPOUSE_CERT_NUM as spouseCertNum, ")
        .append("cp.SPOUSE_CERT_EXPIRE_DATE as spouseCertExpireDate, ")
        .append("cp.SPOUSE_PHONENUM as spousePhonenum , ")
        .append("cp.INDUSTRY as industry, ")
        .append("cp.CREATOR as creator, ")
        .append("cp.CREATE_TIME as createTime, ")
        .append("cp.UPDATOR as updator, ")
        .append("cp.UPDATE_TIME as updateTime, ")
        .append("cp.IS_VALID as isValid, ")
        .append("cp.MOBILE_PHONE as mobilePhone, ")
        .append("cp.TELEPHONE as telephone, ")
        .append("cp.FAX as fax , ")
        .append("cp.QQ as qq ")
 

        .append(" from  T_COR_CUST_PERSONAL cp  ")
        // T_COR_CUST c,
        .append(" left join T_COR_CUST c on  cp.CUST_CODE=c.CUST_CODE ")
        .append(" where 1=1")
        .append(" and c.IS_VALID ='").append(CorConstants.DATA_DICT__IS_VALID).append("'")
        .append(" and cp.IS_VALID ='").append(CorConstants.DATA_DICT__IS_VALID).append("'");

        String id = custPersonalCondition.getId();
        String custCode = custPersonalCondition.getCustCode();
        String localName = custPersonalCondition.getLocalName();
        String certType = custPersonalCondition.getCertType();
        String certNum = custPersonalCondition.getCertNum();
        String telephone = custPersonalCondition.getTelephone();
        String realNameLeve = custPersonalCondition.getRealNameLeve();
        String startCreateTime = custPersonalCondition.getStartCreateTime();
        String endCreateTime = custPersonalCondition.getEndCreateTime();
        String custStatus = custPersonalCondition.getCustStatus();
        if (StringUtils.isNotEmpty(id)) {
            sql.append(" and cp.id ='").append(id).append("'");
        } else {
            if (StringUtils.isNotEmpty(custCode)) {
                sql.append(" and cp.CUST_CODE ='").append(custCode).append("'");
            }
            if (StringUtils.isNotEmpty(telephone)) {
                sql.append(" and cp.TELEPHONE ='").append(telephone).append("'");
            }
            if (StringUtils.isNotEmpty(certType)) {
                sql.append(" and c.CERT_TYPE ='").append(certType).append("'");
            }
            if (StringUtils.isNotEmpty(certNum)) {
                sql.append(" and c.CERT_NUM ='").append(certNum).append("'");
            }
            if (StringUtils.isNotEmpty(realNameLeve)) {
                sql.append(" and c.REAL_NAME_LEVE ='").append(realNameLeve).append("'");
            }
            if (StringUtils.isNotEmpty(localName)) {
                sql.append(" and c.LOCAL_NAME like '%" + localName + "%'");
            }
            if (StringUtils.isNotEmpty(startCreateTime)) {
                sql.append(" and TO_CHAR(cp.CREATE_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(
                        " >= '" + startCreateTime + "' ");
            }
            if (StringUtils.isNotEmpty(endCreateTime)) {
                sql.append(" and TO_CHAR(cp.CREATE_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(
                        " <= '" + endCreateTime + "' ");
            }
            if (StringUtils.isNotEmpty(custStatus)) {
                sql.append(" and c.status  ='").append(custStatus).append("'");
            }
        }
        sql.append(" order by cp.UPDATE_TIME desc");
        return sql.toString();
    }
public static void main(String[] args) {
    System.out.println(makeSql(new CustPersonalDto()));
}
    /* (non-Javadoc)
     * @see com.ibs.core.module.customer.dao.ICorCustPersonalDao#getCustPersonalDtoByCondition(com.ibs.core.module.customer.dto.CustPersonalDto)
     */
    @Override
    public CustPersonalDto getCustPersonalDtoByCondition(CustPersonalDto custPersonalCondition) {
        QueryPage queryPage = new QueryPage(10, 1);
        IPage<CustPersonalDto> page = this.findCustPersonalDtosBySql(queryPage, custPersonalCondition);
        Collection<CustPersonalDto> rows = page.getRows();
        if (rows.isEmpty()) {
            return null;
        }
        return rows.iterator().next();
    }

	public CorCustPersonal load(String id) {
		return super.load(id);
	}

	public void saveOrUpdate(CorCustPersonal corCustPersonal) {
		super.saveOrUpdate(corCustPersonal);
	}

    /* (non-Javadoc)
     * @see com.ibs.core.module.customer.dao.ICorCustPersonalDao#getCorCustPersonalsList(com.ibs.core.module.customer.dto.CustCompanyDto)
     */
    @Override
    public List<CorCustPersonal> getCorCustPersonalsList(CustPersonalDto custPersonalDto) {
        Criteria criteria = super.getSession().createCriteria(CorCustPersonal.class);
        criteria.add(Restrictions.eq("isValid", CorConstants.DATA_DICT__IS_VALID));
        if (null != custPersonalDto && null != custPersonalDto.getCustCode()) {
            criteria.add(Restrictions.eq("custCode", custPersonalDto.getCustCode()));
        }
        
        
        List<CorCustPersonal> list = criteria.list();
        if (null != list && list.size() > 0) {
            return list;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.ibs.core.module.customer.dao.ICorCustPersonalDao#findCustCompanyDtosBySql(com.ibs.portal.framework.server.metadata.QueryPage, com.ibs.core.module.customer.dto.CustPersonalDto)
     */
   

}
