package com.ibs.core.dubbo.dao.impl;

import com.ibs.core.dubbo.dao.IDemoDao;
import com.ibs.core.dubbo.domain.Demo;
import com.ibs.core.dubbo.dto.DemoDto;
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

	@Override
	public DemoDto findDemoByDto(DemoDto demoDto) {
		Demo demo = load(demoDto.getId());
		logger.info(demo.toString());
		return null;
	}

}
