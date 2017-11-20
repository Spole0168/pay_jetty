package com.ibs.core.module.demo.biz;

import com.ibs.core.module.demo.domain.Demo;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/**
 * @author XXX
 * @date   YYYY-MM-DD
 * @version 1.0
 * @description This is action description
 */
public interface IDemoBiz {

	/**
	 * find demo list by page
	 * @param queryPage
	 * @return IPage
	 */
	public IPage<Demo> findDemoByPage(QueryPage queryPage);

	/**
	 * get demo entity by id
	 * @param id
	 * @return Demo
	 */
	public Demo getDemoById(String id);
	
	/**
	 * save a new demo
	 * @param object
	 * @return Demo
	 */
	public Demo saveDemo(Demo object);
	
	/**
	 * update the demo
	 * @param object
	 * @return Demo
	 */
	public Demo updateDemo(Demo object);
	
	/**
	 * export demo list to excel
	 * @param exportSetting
	 */
	public void exportDemo(ExportSetting exportSetting);

}
