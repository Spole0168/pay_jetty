package com.ibs.core.module.demo.dao;

import com.ibs.core.module.demo.domain.Demo;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/**
 * @author XXX
 * @date   YYYY-MM-DD
 * @version 1.0
 * @description This is action description
 */
public interface IDemoDao {

	/**
	 * find demo list by page
	 * @param queryPage
	 * @return
	 */
	public IPage<Demo> findDemoByPage(QueryPage queryPage);
	
	/**
	 * save or update demo by demo entity
	 * @param demo
	 */
	public void saveOrUpdate(Demo demo);

	/**
	 * load demo by id
	 * @param id
	 * @return
	 */
	public Demo load(String id);
	
}
