package com.ibs.core.module.basefunc.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ibs.core.module.basefunc.dao.IAuditDao;
import com.ibs.core.module.basefunc.dao.IAuditLogDao;
import com.ibs.core.module.basefunc.domain.Audit;
import com.ibs.core.module.basefunc.domain.AuditLog;
import com.ibs.core.module.basefunc.service.IAuditService;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.util.BeanUtils;

public class AuditServiceImpl implements IAuditService {
    private IAuditDao auditDao;
    private IAuditLogDao auditLogDao;

    public IAuditDao getAuditDao() {
        return auditDao;
    }

    public void setAuditDao(IAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public IAuditLogDao getAuditLogDao() {
        return auditLogDao;
    }

    public void setAuditLogDao(IAuditLogDao auditLogDao) {
        this.auditLogDao = auditLogDao;
    }

    @Override
    public void saveAudit(Audit audit) {
    	if(null == audit ||null == audit.getAuditType() ||null == audit.getSourceCode()
    			|| null == audit.getStatus()){
    		throw new BizException("参数有问题","-1");
    	}
    	//防止出现事物 实体不一致问题。
    	audit.setId(null);
        Audit auditTemp = auditDao.getAuditByCondition(audit);
        if (null == auditTemp) {// 新增
            auditTemp = new Audit();
            try {
                BeanUtils.copyBasicTypeProperties(auditTemp, audit);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            auditTemp.setId(null);
            auditDao.saveOrUpdateAudit(auditTemp);
        } else {// 修改
            BeanUtils.copyObjectTypeProperties(auditTemp, audit);
            auditDao.saveOrUpdateAudit(auditTemp);
        }
        AuditLog auditLog = new AuditLog();
        try {
            BeanUtils.copyBasicTypeProperties(auditLog, auditTemp);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        auditLog.setId(null);
        auditLogDao.saveAuditLog(auditLog);
    }

    @Override
    public void updateMert(Audit audit, String temCode) {
        if (audit != null) {
            //成功更新
            if (CorConstants.DATA_DICT__CHECK_STATUS_CHECK_SUCCESS.equals(audit.getStatus())) {
                Audit newAudit = auditDao.findAuditById(audit.getId());
                AuditLog auditLog1 = new AuditLog();
                newAudit.setSourceCode(audit.getSourceCode());
                newAudit.setStatus(audit.getStatus());
                newAudit.setCreator(audit.getCreator());
                newAudit.setCreateTime(audit.getCreateTime());
                auditDao.saveOrUpdateAudit(newAudit);
                
                try {
                    BeanUtils.copyBasicTypeProperties(auditLog1, audit);
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                auditLog1.setId(null);
                auditLogDao.saveAuditLog(auditLog1);

                List<AuditLog> auditLog = auditLogDao.findByMerchantCode(temCode);
                if (auditLog != null && auditLog.size() > 0) {
                    for (AuditLog newLog : auditLog) {
                        newLog.setSourceCode(audit.getSourceCode());
                        auditLogDao.saveAuditLog(newLog);
                        
                    }

                }

            }else{
                //失败不变
                saveAudit(audit);
            }
        }

    }

    public Audit queryAudit(String statementCode) {
        Audit audit = auditLogDao.queryAudit(statementCode);
        return audit;
    }

}
