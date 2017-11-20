package com.ibs.core.module.customer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.ibs.core.module.customer.dao.IMerchantChgDao;
import com.ibs.core.module.customer.domain.MerchantChg;
import com.ibs.core.module.customer.dto.MerchantDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.ColumnType;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

public class MerchantChgDaoImpl extends BaseEntityDao<MerchantChg> implements IMerchantChgDao {

    public IPage<MerchantDto> findMerchantChgListByPage(QueryPage queryPage, MerchantDto merchantDto) {

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
                        + " m.RISK_LEVEL riskLevel,m.INITIAL_DEPOSIT initialDeposit, "
                        + " m.SETT_PERIOD settPeriod,m.SETT_START_DATE settStartDate,m.NEXT_SETT_DATE nextSettDate, "
                        + " m.IS_CHECKIN isCheckin,m.IS_CHECKOUT isCheckout, "
                        + " m.CREATOR creator,m.CREATE_TIME createTime,m.Status mertStatus,a.ID accId, "
                        + " b.STATUS status,m.REMARK remark,b.CREATOR reviewPerson,b.CREATE_TIME reviewTime "
                        + " from T_COR_MERCHANT_CHG m "
                        + " inner join T_COR_CUST c on m.cust_code = c.cust_code "
                        + " left join T_COR_AUDIT b on m.ID = b.SOURCE_CODE "
                        + " left join T_COR_ACCOUNT a on m.MERCHANT_CODE = a.MERCHANT_CODE and a.ACCOUNT_TYPE='"
                        + CorConstants.DATA_DICT__ACCOUNT_TYPE_BASE + "' " + " where m.is_valid = '" + CorConstants.DATA_DICT__IS_VALID
                        + "'  and c.is_valid = '" + CorConstants.DATA_DICT__IS_VALID + "'");

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
        queryPage.addScalar("riskLevel", Hibernate.STRING);
        queryPage.addScalar("initialDeposit", Hibernate.BIG_DECIMAL);
        queryPage.addScalar("settPeriod", Hibernate.INTEGER);
        queryPage.addScalar("settStartDate", Hibernate.TIMESTAMP);
        queryPage.addScalar("nextSettDate", Hibernate.TIMESTAMP);
        queryPage.addScalar("isCheckin", Hibernate.STRING);
        queryPage.addScalar("isCheckout", Hibernate.STRING);
        queryPage.addScalar("creator", Hibernate.STRING);
        queryPage.addScalar("createTime", Hibernate.TIMESTAMP);
        queryPage.addScalar("status", Hibernate.STRING);
        queryPage.addScalar("remark", Hibernate.STRING);
        queryPage.addScalar("reviewPerson", Hibernate.STRING);
        queryPage.addScalar("reviewTime", Hibernate.TIMESTAMP);
        queryPage.addScalar("mertStatus", Hibernate.STRING);
        queryPage.addScalar("accId", Hibernate.STRING);
        IPage<MerchantDto> page = findPageBySql(queryPage, MerchantDto.class);
        return page;
    }

    public MerchantDto getMerchantChgBymertCode(String merchantCode) {
        String sql = "select distinct c.ID id,c.STATUS mertStatus,b.status status " + " from T_COR_MERCHANT_CHG c "
                + " inner join T_COR_AUDIT b on c.id=b.SOURCE_CODE " + " where  b.Status not in ('"
                + CorConstants.DATA_DICT__CHECK_STATUS_CHECK_SUCCESS_VALUE + "') and c.MERCHANT_CODE = '" + merchantCode
                + "' and c.Status = '" + CorConstants.DATA_DICT__CORE_CUST_STATUS_VALID + "'";
        List<ColumnType> scallarList = new ArrayList<ColumnType>();
        scallarList.add(new ColumnType("id", Hibernate.STRING));
        scallarList.add(new ColumnType("mertStatus", Hibernate.STRING));
        scallarList.add(new ColumnType("status", Hibernate.STRING));
        List<MerchantDto> lists = findBySql(sql, null, null, scallarList, MerchantDto.class);
        if (lists != null && lists.size() > 0) {
            return lists.get(0);
        }
        return null;
    }

    @Override
    public MerchantChg getMerchantChg(String merchantCode) {
        String hql = " from MerchantChg where isValid ='" + CorConstants.DATA_DICT__IS_VALID + "' and merchantCode = '"
                + merchantCode + "' and status ='" + CorConstants.DATA_DICT__CORE_CUST_STATUS_VALID + "'"
                        + " order by updateTime desc ";
        List<Object> list = findByHql(hql, null, null);
        if (list != null && list.size() > 0) {
            return (MerchantChg) list.get(0);
        }
        return null;
    }

    public MerchantDto getMerchantChgAut(String merchantCode) {
        String sql = "select distinct c.ID id,c.STATUS mertStatus,b.status status " + " from T_COR_MERCHANT_CHG c "
                + " inner join T_COR_AUDIT b on c.id=b.SOURCE_CODE " + " where  b.Status not in ('"
                + CorConstants.DATA_DICT__CHECK_STATUS_CHECK_SUCCESS + "') and c.MERCHANT_CODE = '" + merchantCode
                + "' ";
        List<ColumnType> scallarList = new ArrayList<ColumnType>();
        scallarList.add(new ColumnType("id", Hibernate.STRING));
        scallarList.add(new ColumnType("mertStatus", Hibernate.STRING));
        scallarList.add(new ColumnType("status", Hibernate.STRING));
        List<MerchantDto> lists = findBySql(sql, null, null, scallarList, MerchantDto.class);
        if (lists != null && lists.size() > 0) {
            return lists.get(0);
        }
        return null;
    }
    
    
    public void saveOrUpdate(MerchantChg merchantChg) {

        super.saveOrUpdate(merchantChg);
    }

    @Override
    public MerchantChg findMerchatChgById(String id) {

        return load(id);
    }

    @Override
    public void delete(String id) {
        super.remove(id);
    }

}
