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

import com.ibs.core.module.customer.dao.ICorMertRateDao;
import com.ibs.core.module.customer.domain.CorMertRate;
import com.ibs.core.module.customer.dto.CorMertRateDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

public class CorMertRateDaoImpl extends BaseEntityDao<CorMertRate> implements ICorMertRateDao {

    public IPage<CorMertRate> findCorMertRateByPage(QueryPage queryPage) {
        logger.info("entering action::CorMertRateDaoImpl.findCorMertRateByPage()...");
        return super.findPageBy(queryPage);
    }

    public CorMertRate load(String id) {
        logger.info("entering action::CorMertRateDaoImpl.load()...");
        return super.load(id);
    }

    public void saveOrUpdate(CorMertRate corMertRate) {
        logger.info("entering action::CorMertRateDaoImpl.saveOrUpdate()...");
        super.saveOrUpdate(corMertRate);
    }

    @Override
    public IPage<CorMertRateDto> findCorMertRateDtoBySql(QueryPage queryPage, CorMertRateDto corMertRateDto) {
        QueryPage queryPagetemp = dealQueryPage(queryPage, corMertRateDto);
        return super.findPageBySql(queryPagetemp, CorMertRateDto.class);
    }

    @Override
    public CorMertRateDto getCorMertRateDtoByCondition(CorMertRateDto corMertRateDto) {
        QueryPage queryPage = new QueryPage(10, 1);
        IPage<CorMertRateDto> page = this.findCorMertRateDtoBySql(queryPage, corMertRateDto);
        Collection<CorMertRateDto> rows = page.getRows();
        if (rows.isEmpty()) {
            return null;
        }
        return rows.iterator().next();
    }

    private QueryPage dealQueryPage(QueryPage queryPage, CorMertRateDto corMertRateDto) {
        queryPage.clearSortMap();
        queryPage.clearQueryCondition();
        // 生成查询sql语句
        String querySql = makeSql(corMertRateDto);
        queryPage.setSqlString(querySql);
        // 设置查询显示列
        queryPage.addScalar("id", new StringType()).addScalar("mertRateCode", new StringType())
                .addScalar("mertRateName", new StringType()).addScalar("custCode", new StringType())
                .addScalar("merchantCode", new StringType()).addScalar("productCode", new StringType())
                .addScalar("cardType", new StringType()).addScalar("cardProperty", new StringType())
                .addScalar("settType", new StringType()).addScalar("currency", new StringType())
                .addScalar("serviceFeeType", new StringType()).addScalar("fixedFeeValue", new BigDecimalType())
                .addScalar("rateFeeValue", new BigDecimalType()).addScalar("lowFeeValue", new BigDecimalType())
                .addScalar("hightFeeValue", new BigDecimalType()).addScalar("effectDate", new DbTimestampType())
                .addScalar("expireDate", new DbTimestampType()).addScalar("creator", new StringType())
                .addScalar("createTime", new DbTimestampType()).addScalar("updator", new StringType())
                .addScalar("updateTime", new DbTimestampType()).addScalar("isValid", new StringType())
                .addScalar("remark", new StringType()).addScalar("merchantName", new StringType());
        return queryPage;
    }

    private static String makeSql(CorMertRateDto corMertRateDto) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select ").append("cmr.ID as id ,").append("cmr.MERT_RATE_CODE as mertRateCode ,")
                .append("cmr.MERT_RATE_NAME as mertRateName ,").append("cmr.CUST_CODE as custCode ,")
                .append("cmr.MERCHANT_CODE as merchantCode ,").append("cmr.PRODUCT_CODE as productCode ,")
                .append("cmr.CARD_TYPE as cardType ,").append("cmr.CARD_PROPERTY as cardProperty ,")
                .append("cmr.SETT_TYPE as settType ,").append("cmr.CURRENCY as currency ,")
                .append("cmr.SERVICE_FEE_TYPE as serviceFeeType ,").append("cmr.FIXED_FEE_VALUE as fixedFeeValue ,")
                .append("cmr.RATE_FEE_VALUE as rateFeeValue ,").append("cmr.LOW_FEE_VALUE as lowFeeValue ,")
                .append("cmr.HIGHT_FEE_VALUE as hightFeeValue ,").append("cmr.EFFECT_DATE as effectDate ,")
                .append("cmr.EXPIRE_DATE as expireDate ,").append("cmr.CREATOR as creator ,")
                .append("cmr.CREATE_TIME as createTime ,").append("cmr.UPDATOR as updator ,")
                .append("cmr.UPDATE_TIME as updateTime ,").append("cmr.IS_VALID as isValid ,")
                .append("cmr.REMARK as remark ,").append("cm.MERCHANT_NAME as merchantName  ")

                .append(" from T_COR_MERT_RATE cmr ").append(" left join  T_COR_MERCHANT cm ")
                .append(" on cmr.MERCHANT_CODE = cm.MERCHANT_CODE ")
                .append(" where  cmr.IS_VALID = '" + CorConstants.DATA_DICT__IS_VALID + "'");

        String id = corMertRateDto.getId();
        String mertRateCode = corMertRateDto.getMertRateCode();
        String mertRateName = corMertRateDto.getMertRateName();
        String merchantCode = corMertRateDto.getMerchantCode();
        String merchantName = corMertRateDto.getMerchantName();
        String productCode = corMertRateDto.getProductCode();
        String cardType = corMertRateDto.getCardType();
        String cardProperty = corMertRateDto.getCardProperty();
        String settType = corMertRateDto.getSettType();
        String validDate = corMertRateDto.getValidDate();

        if (StringUtils.isNotEmpty(id)) {
            sql.append(" and cmr.id ='").append(id).append("'");
        } else {
            if (StringUtils.isNotEmpty(mertRateCode)) {
                sql.append(" and cmr.MERT_RATE_CODE ='").append(mertRateCode).append("'");
            }
            if (StringUtils.isNotEmpty(mertRateName)) {
                sql.append(" and cmr.MERT_RATE_NAME like '%" + mertRateName + "%'");
            }
            if (StringUtils.isNotEmpty(merchantCode)) {
                sql.append(" and cmr.MERCHANT_CODE ='").append(merchantCode).append("'");
            }
            if (StringUtils.isNotEmpty(merchantName)) {
                sql.append(" and cm.MERCHANT_NAME like '%" + merchantName + "%'");
            }
            if (StringUtils.isNotEmpty(productCode)) {
                sql.append(" and cmr.PRODUCT_CODE ='").append(productCode).append("'");
            }
            if (StringUtils.isNotEmpty(cardType)) {
                sql.append(" and cmr.CARD_TYPE ='").append(cardType).append("'");
            }
            if (StringUtils.isNotEmpty(cardProperty)) {
                sql.append(" and cmr.CARD_PROPERTY ='").append(cardProperty).append("'");
            }
            if (StringUtils.isNotEmpty(settType)) {
                sql.append(" and cmr.SETT_TYPE ='").append(settType).append("'");
            }
            if (StringUtils.isNotEmpty(validDate)) {
                sql.append(" and TO_CHAR(cmr.EFFECT_DATE, 'yyyy-mm-dd')").append(" <= '" + validDate + "' ");
                sql.append(" and TO_CHAR(cmr.EXPIRE_DATE, 'yyyy-mm-dd')").append(" >= '" + validDate + "' ");
            }
        }
        sql.append(" order by cmr.UPDATE_TIME desc");
        return sql.toString();
    }

    public static void main(String[] args) {
        System.out.println(makeSql(new CorMertRateDto()));
    }

    @Override
    public List<CorMertRate> getCorMertRateByCondition(CorMertRate corMertRate) {
        Criteria criteria = super.getSession().createCriteria(CorMertRate.class);
        criteria.add(Restrictions.eq("isValid", CorConstants.DATA_DICT__IS_VALID));

        if (null != corMertRate && null != corMertRate.getCustCode()) {
            criteria.add(Restrictions.eq("custCode", corMertRate.getCustCode()));
        }
        if (null != corMertRate && null != corMertRate.getMertRateCode()) {
            criteria.add(Restrictions.eq("mertRateCode", corMertRate.getMertRateCode()));
        }
        if (null != corMertRate && null != corMertRate.getMerchantCode()) {
            criteria.add(Restrictions.eq("merchantCode", corMertRate.getMerchantCode()));
        }
        if (null != corMertRate && null != corMertRate.getMertRateName()) {
            criteria.add(Restrictions.eq("mertRateName", corMertRate.getMertRateName()));
        }
        List<CorMertRate> list = criteria.list();
        if (null != list && list.size() > 0) {
            return list;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibs.core.module.customer.dao.ICorMertRateDao#
     * getCorMertRateDtoByConditionAND(com.ibs.core.module.customer.domain.
     * CorMertRate)
     */
    @Override
    public List<CorMertRate> getCorMertRateDtoByConditionAND(CorMertRateDto corMertRate) {
        Criteria criteria = super.getSession().createCriteria(CorMertRate.class);
        criteria.add(Restrictions.eq("isValid", CorConstants.DATA_DICT__IS_VALID));
        if (null != corMertRate && null != corMertRate.getId()) {
            criteria.add(Restrictions.ne("id", corMertRate.getId()));
        }
        if (null != corMertRate && null != corMertRate.getMertRateCode()) {
            criteria.add(Restrictions.ne("mertRateCode", corMertRate.getMertRateCode()));
        }
        
        if (null != corMertRate && null != corMertRate.getMerchantCode()
                && corMertRate.getMerchantCode().trim().length() > 0) {
            criteria.add(Restrictions.eq("merchantCode", corMertRate.getMerchantCode()));
        }else{
            criteria.add(Restrictions.isNull("merchantCode"));
        }
       
        if (null != corMertRate && null != corMertRate.getProductCode()) {
            criteria.add(Restrictions.eq("productCode", corMertRate.getProductCode()));
        }
        if (null != corMertRate && null != corMertRate.getCardType()) {
            criteria.add(Restrictions.eq("cardType", corMertRate.getCardType()));
        }
        if (null != corMertRate && null != corMertRate.getCardProperty()) {
            criteria.add(Restrictions.eq("cardProperty", corMertRate.getCardProperty()));
        }

        if (null != corMertRate && null != corMertRate.getSettType()) {
            criteria.add(Restrictions.eq("settType", corMertRate.getSettType()));
        }
        List<CorMertRate> list = criteria.list();
        if (null != list && list.size() > 0) {
            return list;
        }
        return null;
    }
}
