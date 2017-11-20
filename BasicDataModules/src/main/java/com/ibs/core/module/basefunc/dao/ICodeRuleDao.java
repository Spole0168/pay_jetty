package com.ibs.core.module.basefunc.dao;

import com.ibs.core.module.basefunc.domain.CodeRule;

public interface ICodeRuleDao {
    CodeRule getById();
    CodeRule getByCodeType(String codeType);
    void update(CodeRule corCodeRule);
}
