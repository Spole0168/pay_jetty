/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.customer.dao.impl;

import java.math.BigDecimal;
import java.util.Collection;

import org.hibernate.type.BigDecimalType;
import org.hibernate.type.DbTimestampType;
import org.hibernate.type.StringType;

import com.ibs.core.module.customer.dao.IncomeOrderDao;
import com.ibs.core.module.customer.domain.IncomeOrder;
import com.ibs.core.module.customer.dto.IncomeOrderDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_INCOME_ORDER
 * @modify		: your comments goes here (author,date,reason).
 */
public class IncomeOrderDaoImpl extends BaseEntityDao<IncomeOrder> implements
		IncomeOrderDao {

	public IPage<IncomeOrderDto> findCorIncomeOrderByPage(QueryPage queryPage,IncomeOrderDto incomeOrderDtoCondition) {
	    
        QueryPage queryPagetemp = accountDealQueryPage(queryPage, incomeOrderDtoCondition);

        return super.findPageBySql(queryPagetemp, IncomeOrderDto.class);
	    
	}

	 private QueryPage accountDealQueryPage(QueryPage queryPage, IncomeOrderDto incomeOrderDtoCondition) {
	        queryPage.clearSortMap();
	        queryPage.clearQueryCondition();
	        // 生成查询sql语句
	        String querySql = makeInAccountSql(incomeOrderDtoCondition);
	        queryPage.setSqlString(querySql);
	        // 设置查询显示列
	        queryPage.addScalar("id", new StringType())
	                    .addScalar("auId", new StringType())
	                .addScalar("incomeOrderCode", new StringType())
	                .addScalar("custCode", new StringType())
	                .addScalar("custName", new StringType())
	                .addScalar("merchantCode", new StringType())
	                .addScalar("merchantName", new StringType())
	                .addScalar("operateType", new StringType())
	                .addScalar("currency", new StringType())
	                .addScalar("transRate", new StringType())
	                .addScalar("amount", new BigDecimalType())
	                .addScalar("status", new StringType())
	                .addScalar("bankTransNum", new StringType())
	                .addScalar("bankCode", new StringType())
	                .addScalar("bankName", new StringType())
	                .addScalar("bankCardNum", new StringType())
	                .addScalar("reserveBankCode", new StringType())
	                .addScalar("reserveBankName", new StringType())
	                .addScalar("reserveBankCardNum", new StringType())
	                .addScalar("inAccountTime", new DbTimestampType())
	                .addScalar("transExplanation", new StringType())
	                .addScalar("voucher", new StringType())
	                .addScalar("auditStatus", new StringType())   
	                .addScalar("sourceCode", new StringType())
	                .addScalar("auditPerson", new StringType())
	                .addScalar("auditReason", new StringType())
	                .addScalar("auditTime", new DbTimestampType())
	                .addScalar("creator", new StringType())
                    .addScalar("createTime", new DbTimestampType())
                    .addScalar("isValid", new StringType())
	                ;

	        return queryPage;
	    }
	    private static String makeInAccountSql(IncomeOrderDto corAccountDtoCondition) {
	        StringBuffer sql = new StringBuffer();
	        sql.append(" select ")
	        .append("inc.ID as id , ")
	        .append("aud.ID as auId , ") 
	        .append("inc.INCOME_ORDER_CODE as incomeOrderCode , ")
	        .append("mer.CUST_CODE as custCode , ")
	        .append("cus.LOCAL_NAME as custName , ")
	        .append("inc.MERCHANT_CODE as merchantCode , ")
	        .append("mer.MERCHANT_NAME as merchantName , ")
	        .append("inc.OPERATE_TYPE as operateType , ")
	        .append("inc.CURRENCY as currency , ")
	        .append("inc.TRANS_RATE as transRate , ")
	        .append("inc.AMOUNT as amount , ")
	        .append("inc.STATUS as status , ")
	        .append("inc.BANK_TRANS_NUM as bankTransNum , ")
	        .append("inc.BANK_CODE as bankCode , ")
	        .append("inc.BANK_NAME as bankName , ")
	        .append("inc.BANK_CARD_NUM as bankCardNum , ")
	        .append("inc.RESERVE_BANK_CODE as reserveBankCode , ")
	        .append("inc.RESERVE_BANK_NAME as reserveBankName , ")
	        .append("inc.RESERVE_BANK_CARD_NUM as reserveBankCardNum , ")
	        .append("inc.IN_ACCOUNT_TIME as inAccountTime , ")
	        .append("inc.TRANS_EXPLANATION as transExplanation , ")
	        .append("inc.VOUCHER as voucher , ")
	        .append("aud.STATUS as auditStatus , ")
	        .append("aud.AUDIT_REASON as auditReason , ")
	        .append("aud.SOURCE_CODE as sourceCode , ")
	        .append("aud.CREATOR as auditPerson , ")
	        .append("aud.CREATE_TIME as auditTime , ")
	        .append("inc.CREATOR as creator , ")
	        .append("inc.CREATE_TIME as createTime , ")
	        .append("inc.IS_VALID as isValid  ")
	        
	        .append(" from T_COR_INCOME_ORDER inc")
	        .append(" left join T_COR_MERCHANT mer")
	        .append(" on inc.MERCHANT_CODE = mer.MERCHANT_CODE ")    
	        .append(" and mer.IS_VALID = '" + CorConstants.DATA_DICT__IS_VALID + "'")
	        .append(" left join T_COR_CUST cus")
	        .append(" on mer.CUST_CODE = cus.CUST_CODE ") 
	        .append(" and cus.IS_VALID = '" + CorConstants.DATA_DICT__IS_VALID + "'")
	        .append(" left join T_COR_AUDIT aud")
	        .append(" on inc.INCOME_ORDER_CODE = aud.SOURCE_CODE ")
	        .append(" and aud.AUDIT_TYPE = '" + CorConstants.AUDIT_TYPE_INCOMEORDER + "'")
//	        .append(" left join T_COR_PAYMENT pay")
//	        .append(" on inc.INCOME_ORDER_CODE = pay.SOURCE_ORDER_CODE ")
	        .append(" where 1=1 ")
	        .append(" and inc.IS_VALID = '" + CorConstants.DATA_DICT__IS_VALID + "'");
	       
	        String id=corAccountDtoCondition.getId();
	        String incomeOrderCode=corAccountDtoCondition.getIncomeOrderCode();
	        String merchantCode=corAccountDtoCondition.getMerchantCode();
	        String merchantName=corAccountDtoCondition.getMerchantName();
	        String custName=corAccountDtoCondition.getCustName();
	        String operateType=corAccountDtoCondition.getOperateType();
	        String currency=corAccountDtoCondition.getCurrency();
	        BigDecimal amount= corAccountDtoCondition.getAmount();
	        String auditStatus=corAccountDtoCondition.getAuditStatus();
	        String timeType=corAccountDtoCondition.getTimeType();//01-创建时间    02-审核时间
	        String startCreateTime=corAccountDtoCondition.getStartDate();
	        String endCreateTime=corAccountDtoCondition.getEndDate();
	        
	        if (StringUtils.isNotEmpty(id)) {
	            sql.append(" and inc.id ='").append(id).append("'");
	        } else {
	            if (StringUtils.isNotEmpty(incomeOrderCode)) {
	                sql.append(" and inc.INCOME_ORDER_CODE ='").append(incomeOrderCode).append("'");
	            }
	            if (StringUtils.isNotEmpty(merchantCode)) {
	                sql.append(" and inc.MERCHANT_CODE ='").append(merchantCode).append("'");
	            }
	            if (StringUtils.isNotEmpty(merchantName)) {
	                sql.append(" and mer.MERCHANT_NAME like '%" + merchantName + "%'");
	            }           
	            if (StringUtils.isNotEmpty(custName)) {
	                sql.append(" and cus.LOCAL_NAME like '%" + custName + "%'");
	            }
	            if (StringUtils.isNotEmpty(operateType)) {
	                sql.append(" and inc.OPERATE_TYPE ='").append(operateType).append("'");
	            }
	            if (StringUtils.isNotEmpty(currency)) {
	                sql.append(" and inc.CURRENCY ='").append(currency).append("'");
	            }
	            if (amount!=null&&!amount.equals(BigDecimal.ZERO)) {
	                sql.append(" and inc.AMOUNT ='").append(amount).append("'");
	            }
	            if (StringUtils.isNotEmpty(auditStatus)) {
	                sql.append(" and aud.STATUS ='").append(auditStatus).append("'");
	            }          
	            if(StringUtils.isNotEmpty(timeType)){
	                if("C".equals(timeType)){
	                    if (StringUtils.isNotEmpty(startCreateTime)) {
	                        sql.append(" and TO_CHAR(inc.CREATE_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(
	                                " >= '" + startCreateTime + "' ");
	                    }
	                    if (StringUtils.isNotEmpty(endCreateTime)) {
	                        sql.append(" and TO_CHAR(inc.CREATE_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(
	                                " <= '" + endCreateTime + "' ");
	                    }   
	                }else{ 
	                    if (StringUtils.isNotEmpty(startCreateTime)) {
	                        sql.append(" and TO_CHAR(aud.CREATE_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(
	                                " >= '" + startCreateTime + "' ");
	                    }
	                    if (StringUtils.isNotEmpty(endCreateTime)) {
	                        sql.append(" and TO_CHAR(aud.CREATE_TIME, 'yyyy-mm-dd hh24:mi:ss')").append(
	                                " <= '" + endCreateTime + "' ");
	                    }    
	                }
	  
	            }
	        }
	        return sql.toString();
	        
	    }
	        
	    
	 public IncomeOrderDto getInCorAccountDtosByCondition(IncomeOrderDto incomeOrderDtoCondition){
	        
	        QueryPage queryPage = new QueryPage(10, 1);
	        IPage<IncomeOrderDto> page = this.findCorIncomeOrderByPage(queryPage, incomeOrderDtoCondition);
	        Collection<IncomeOrderDto> rows = page.getRows();
	        if (rows.isEmpty()) {
	            return null;
	        }
	        return rows.iterator().next();
	    }

	
	public IncomeOrder load(String id) {
		logger.info("entering action::CorIncomeOrderDaoImpl.load()...");
		return super.load(id);
	}
}
