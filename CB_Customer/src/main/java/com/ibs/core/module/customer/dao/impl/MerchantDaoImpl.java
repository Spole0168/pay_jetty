package com.ibs.core.module.customer.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.type.StringType;

import com.ibs.core.module.customer.dao.IMerchantDao;
import com.ibs.core.module.customer.domain.Merchant;
import com.ibs.core.module.customer.dto.MerchantDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.ColumnType;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

public class MerchantDaoImpl extends BaseEntityDao<Merchant> implements IMerchantDao {

    @Override
    public IPage<MerchantDto> findMerchantListByPage(QueryPage queryPage, MerchantDto merchantDto) {
        logger.info("entering action::MerchantDaoImpl.findMerchantListByPage()...");
        String custCode = merchantDto.getCustCode();
        String custName = merchantDto.getCustName();
        String merchantCode = merchantDto.getMerchantCode();
        String merchantName = merchantDto.getMerchantName();
        String status = merchantDto.getStatus();
        String startDate = merchantDto.getStartDate();
        String endDate = merchantDto.getEndDate();
        String riskLevel = merchantDto.getRiskLevel();
        String mertStatus = merchantDto.getMertStatus();

        StringBuffer sb = new StringBuffer(
                "select m.id id,m.cust_code custCode,c.LOCAL_NAME custName,m.MERCHANT_CODE merchantCode, "
                        + " m.MERCHANT_NAME merchantName,m.INDUSTRY industry,m.MCC_CODE mccCode, "
                        + " m.OPERATE_SCOPE operateScope,m.CONTRACT_CODE contractCode,m.ACTIVE_DATE activeDate, "
                        + " m.INACTIVE_DATE inactiveDate,m.BUSINESS_RESPONSE businessResponse,m.BUSINESS_RESPONSE_PHONE businessResponsePhone, "
                        + " m.BUSINESS_RESPONSE_EMAIL businessResponseEmail,m.TEC_RESPONSE tecResponse,m.TEC_RESPONSE_PHONE tecResponsePhone, "
                        + " m.TEC_RESPONSE_EMAIL tecResponseEmail,m.WEBSITE website, "
                        + " m.RISK_LEVEL riskLevel,m.INITIAL_DEPOSIT initialDeposit, "
                        + " m.SETT_PERIOD settPeriod,m.SETT_START_DATE settStartDate,m.NEXT_SETT_DATE nextSettDate, "
                        + " m.IS_CHECKIN isCheckin,m.IS_CHECKOUT isCheckout, "
                        + " m.CREATOR creator,m.CREATE_TIME createTime,m.UPDATE_TIME updateTime,m.Status mertStatus,a.ID accId, "
                        + " b.STATUS status,m.REMARK remark,b.CREATOR reviewPerson,b.CREATE_TIME reviewTime "
                        + " from T_COR_MERCHANT m " + " inner join T_COR_CUST c on m.cust_code = c.cust_code "
                        + " left join T_COR_AUDIT b on m.MERCHANT_CODE = b.SOURCE_CODE "
                        + " left join T_COR_ACCOUNT a on m.MERCHANT_CODE = a.MERCHANT_CODE and a.ACCOUNT_TYPE='"
                        + CorConstants.DATA_DICT__ACCOUNT_TYPE_BASE + "' " + " where m.is_valid = '" + CorConstants.DATA_DICT__IS_VALID
                        + "' and c.is_valid = '" + CorConstants.DATA_DICT__IS_VALID + "'");

        // 查询条件
        if (StringUtils.isNotEmpty(custCode)) {
            sb.append(" and m.cust_code  = '" + custCode + "' ");
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
        if (StringUtils.isNotEmpty(riskLevel)) {
            sb.append(" and m.RISK_LEVEL = '" + riskLevel + "' ");
        }
        if (StringUtils.isNotEmpty(status)) {
            sb.append(" and b.STATUS = '" + status + "' ");
        }
        if (StringUtils.isNotEmpty(startDate)) {
            sb.append(" and TO_CHAR(m.CREATE_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(" >= '" + startDate + "' ");
        }
        if (StringUtils.isNotEmpty(endDate)) {
            sb.append(" and TO_CHAR(m.CREATE_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(" <= '" + endDate + "' ");
        }
        if (StringUtils.isNotEmpty(mertStatus)) {
            sb.append(" and m.STATUS = '" + mertStatus + "' ");
        }

        queryPage.setSqlString(sb.toString());

        queryPage.addScalar("id", Hibernate.STRING);
        queryPage.addScalar("custCode", Hibernate.STRING);
        queryPage.addScalar("custName", Hibernate.STRING);
        queryPage.addScalar("merchantCode", Hibernate.STRING);
        queryPage.addScalar("merchantName", Hibernate.STRING);
        queryPage.addScalar("industry", Hibernate.STRING);
        queryPage.addScalar("mccCode", Hibernate.STRING);
        
        queryPage.addScalar("operateScope", Hibernate.STRING);
        queryPage.addScalar("contractCode", Hibernate.STRING);
        queryPage.addScalar("activeDate", Hibernate.TIMESTAMP);
        queryPage.addScalar("inactiveDate",Hibernate.TIMESTAMP);
        queryPage.addScalar("businessResponse", Hibernate.STRING);
        queryPage.addScalar("businessResponsePhone", Hibernate.STRING);
        queryPage.addScalar("businessResponseEmail", Hibernate.STRING);
        queryPage.addScalar("tecResponse", Hibernate.STRING);
        queryPage.addScalar("tecResponsePhone", Hibernate.STRING);
        queryPage.addScalar("tecResponseEmail", Hibernate.STRING);
        queryPage.addScalar("website", Hibernate.STRING);
        
        queryPage.addScalar("riskLevel", Hibernate.STRING);
        queryPage.addScalar("initialDeposit", Hibernate.BIG_DECIMAL);
        queryPage.addScalar("settPeriod", Hibernate.INTEGER);
        queryPage.addScalar("settStartDate", Hibernate.TIMESTAMP);
        queryPage.addScalar("nextSettDate", Hibernate.TIMESTAMP);
        queryPage.addScalar("isCheckin", Hibernate.STRING);
        queryPage.addScalar("isCheckout", Hibernate.STRING);
        queryPage.addScalar("creator", Hibernate.STRING);
        queryPage.addScalar("createTime", Hibernate.TIMESTAMP);
        queryPage.addScalar("updateTime", Hibernate.TIMESTAMP);
        queryPage.addScalar("status", Hibernate.STRING);
        queryPage.addScalar("remark", Hibernate.STRING);
        queryPage.addScalar("reviewPerson", Hibernate.STRING);
        queryPage.addScalar("reviewTime", Hibernate.TIMESTAMP);
        queryPage.addScalar("mertStatus", Hibernate.STRING);
        queryPage.addScalar("accId", Hibernate.STRING);
        IPage<MerchantDto> page = findPageBySql(queryPage, MerchantDto.class);
        return page;
    }

    @Override
    public MerchantDto getMerchantDetailInfoById(MerchantDto md) {
        IPage<MerchantDto> page = this.findMerchantListByPage(new QueryPage(10, 1), md);
        Collection<MerchantDto> rows = page.getRows();
        if (rows.isEmpty()) {
            return null;
        }
        return rows.iterator().next();
    }

    @Override
    public void SaveOrUpdateMechant(Merchant merchant) {
        logger.info("entering dao::MerchantDaoImpl.SaveOrUpdateMechant()...");
        super.saveOrUpdate(merchant);
    }

    @Override
    public Merchant findMerchatById(String id) {
        logger.info("entering dao::MerchantDaoImpl.findMerchatById()...");
        return load(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OptionObjectPair> getBankName(String cityCode) {
        List<OptionObjectPair> list = new ArrayList<OptionObjectPair>();
        OptionObjectPair objectPair = null;

        String sql = "select distinct BANK_CODE bankCode，BANK_NAME bankName from T_PBL_BANK_DICT where CITY_CODE ='"
                + cityCode + "'";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.addScalar("bankCode", new StringType());
        query.addScalar("bankName", new StringType());
        List<Object[]> objs = query.list();
        if (objs != null) {
            for (Object[] obj : objs) {
                objectPair = new OptionObjectPair(obj[0].toString(), obj[1].toString());
                list.add(objectPair);
            }
        }
        return list;

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<OptionObjectPair> getBankBranchName(String bankCode, String cityCode) {

        List<OptionObjectPair> list = new ArrayList<OptionObjectPair>();
        OptionObjectPair objectPair = null;

        String sql = "select distinct BANK_BRANCH_CODE bankBranchCode，BANK_BRANCH_NAME bankBranchName from T_PBL_BANK_DICT where CITY_CODE ='"
                + cityCode + "' and BANK_CODE = '" + bankCode + "'";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.addScalar("bankBranchCode", new StringType());
        query.addScalar("bankBranchName", new StringType());
        List<Object[]> objs = query.list();
        if (objs != null) {
            for (Object[] obj : objs) {
                objectPair = new OptionObjectPair(obj[0].toString(), obj[1].toString());
                list.add(objectPair);
            }
        }
        return list;

    }

    @Override
    public MerchantDto findAudit(String merchantCode) {

        logger.info("entering dao::MerchantDaoImpl.findAudit()...");
        String sql = "select STATUS status,ID auId, AUDIT_REASON reviewFailureReasons, CREATOR reviewPerson, CREATE_TIME reviewTime "
                + " from T_COR_AUDIT where SOURCE_CODE = '" + merchantCode + "' and AUDIT_TYPE = '"
                + CorConstants.AUDIT_TYPE_MERCHANT + "'";
        List<ColumnType> scallarList = new ArrayList<ColumnType>();
        scallarList.add(new ColumnType("status", Hibernate.STRING));
        scallarList.add(new ColumnType("reviewFailureReasons", Hibernate.STRING));
        scallarList.add(new ColumnType("reviewPerson", Hibernate.STRING));
        scallarList.add(new ColumnType("reviewTime", Hibernate.TIMESTAMP));
        scallarList.add(new ColumnType("auId", Hibernate.STRING));
        List<MerchantDto> lists = findBySql(sql, null, null, scallarList, MerchantDto.class);
        if (lists != null && lists.size() > 0) {
            return lists.get(0);
        }
        return null;
    }

    @Override
    public List<Merchant> findByMerchantCode(String merchantCode) {
        return findByValue("from Merchant m where m.merchantCode = ?", merchantCode);
    }

    @Override
    public List<Merchant> findAll() {
        return findByValue("from Merchant m where m.isValid = ?", CorConstants.DATA_DICT__IS_VALID);
    }

    public List<OptionObjectPair> getCategoryInfo(String categoryCode,String mccCode) {

        List<OptionObjectPair> list = new ArrayList<OptionObjectPair>();
        OptionObjectPair objectPair = null;
        StringBuffer sb=new StringBuffer();
        sb.append("select MCC_CODE as mccCode,CATEGORY_TYPE as categoryType,CATEGORY_CODE as categoryCode from T_COR_INDUSTRY_CATEGORY where  1=1 ");
        if(StringUtils.isNotEmpty(categoryCode)){
            sb.append(" and CATEGORY_CODE = '" + categoryCode + "' ");
//            sb.append("and CATEGORY_TYPE like '%").append(code).append("%'");
        }
        if(StringUtils.isNotEmpty(mccCode)){
            sb.append(" and MCC_CODE = '" + mccCode + "' ");
//            sb.append("and CATEGORY_TYPE like '%").append(code).append("%'");
        }
        SQLQuery query = this.getSession().createSQLQuery(sb.toString());
        query.addScalar("mccCode", new StringType());
        query.addScalar("categoryType", new StringType());
        query.addScalar("categoryCode", new StringType());
        List<Object[]> objs = query.list();
        if(StringUtils.isNotEmpty(mccCode)){
            if (objs != null) {
                for (Object[] obj : objs) {
                    objectPair = new OptionObjectPair(obj[2].toString(), obj[1].toString());
                    list.add(objectPair);
                }
            }  
            
        }else{
            if (objs != null) {
                for (Object[] obj : objs) {
                    objectPair = new OptionObjectPair(obj[0].toString(), obj[1].toString());
                    list.add(objectPair);
                }
            }  
        }
       
        return list;

    }

}
