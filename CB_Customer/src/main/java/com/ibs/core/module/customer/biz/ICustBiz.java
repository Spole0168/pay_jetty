package com.ibs.core.module.customer.biz;

import com.ibs.core.module.customer.domain.CorCust;

public interface ICustBiz {

    CorCust getCustByCustCode(String custCode);

    CorCust getCustByLocalName(String localName);

}
