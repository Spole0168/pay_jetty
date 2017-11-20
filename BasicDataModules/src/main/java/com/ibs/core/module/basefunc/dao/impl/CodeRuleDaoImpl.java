package com.ibs.core.module.basefunc.dao.impl;

import java.util.LinkedList;
import java.util.List;

import com.ibs.core.module.basefunc.dao.ICodeRuleDao;
import com.ibs.core.module.basefunc.domain.CodeRule;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.util.StringUtils;

public class CodeRuleDaoImpl extends BaseEntityDao<CodeRule> implements ICodeRuleDao{

    @Override
    public CodeRule getById() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CodeRule getByCodeType(String codeType) {
        StringBuffer hql = new StringBuffer();
        if(StringUtils.isNotEmpty(codeType)){
            hql.append("from CodeRule where codeType = ? and isValid = '").append(
                    CorConstants.DATA_DICT__IS_VALID).append("'");  
        }
        List<Object> list = new LinkedList<>();
        list.add(codeType);
        List<CodeRule> result = super.findByHql(hql.toString(), list, null);
        if(result!=null && result.size()>0)
            return result.get(0);
        return null;
    }
    
    @Override
    public void update(CodeRule corCodeRule){
        super.saveOrUpdate(corCodeRule);
    }
    
}
