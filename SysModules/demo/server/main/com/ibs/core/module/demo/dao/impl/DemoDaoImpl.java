package com.ibs.core.module.demo.dao.impl;

import com.ibs.core.module.demo.dao.IDemoDao;
import com.ibs.core.module.demo.domain.Demo;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/**
 * @author XXX
 * @date   YYYY-MM-DD
 * @version 1.0
 * @description This is action description
 */
public class DemoDaoImpl extends BaseEntityDao<Demo> implements
		IDemoDao {

	@Override
	public IPage<Demo> findDemoByPage(QueryPage queryPage) {
		return super.findPageBy(queryPage);
	}

	@Override
	public Demo load(String id) {
		return super.load(id);
	}

	@Override
	public void saveOrUpdate(Demo demo) {
		super.saveOrUpdate(demo);
	}

}
