package com.ibs.core.module.demo.action;

import com.ibs.core.module.demo.biz.IDemoBiz;
import com.ibs.core.module.demo.domain.Demo;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.StringUtils;

/**
 * @author XXX
 * @date   YYYY-MM-DD
 * @version 1.0
 * @description This is action description
 */

@SuppressWarnings("serial")
public class DemoManagerAction extends CrudBaseAction {

	/**
	 * Demo biz interface
	 */
	private IDemoBiz demoBiz;
	
	/**
	 * The key for demo entity 
	 */
	private String id;
	
	/**
	 * The entity is used to input and output parameters
	 */
	private Demo demo;
	
	/**
	 * Action method，show list page
	 * 
	 * @return
	 */
	@Override
	public String list() {
		return SUCCESS;
	}
	
	/**
	 * Action method, translate to create page
	 * 
	 * @return
	 */
	public String create() {
		return SUCCESS;
	}
	
	/**
	 * Action method, translate to modify page
	 * 
	 * @return
	 */
	public String modify() {
		demo = demoBiz.getDemoById(id);
		return SUCCESS;
	}
	
	/**
	 * Action method, query list data
	 * 
	 * @return
	 */
	public String search() {

		logger.trace("entering action...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		/**
		 * 第二步，执行查询,并设置结果
		 */
		Page<Demo> result = (Page<Demo>) demoBiz
				.findDemoByPage(queryPage);
		setResult(result);

		/**
		 * 第三步，返回
		 */
		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * Action method, save create and modify entity
	 * 
	 * @return
	 */
	public String saveOrUpdate() {

		logger.trace("entering action...");

		// 如果是新增
		if (this.getIsModify() == false) {
			demoBiz.saveDemo(demo);
		}
		// 如果是修改
		else {
			demoBiz.updateDemo(demo);
		}

		return SUCCESS;
	}
	
	/**
	 * Action method, batch delete entities
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() {

		logger.trace("entering action...");

		return AJAX_RETURN_TYPE;
	}
	
	/**
	 * Action method, export excel
	 * 
	 * @return
	 * @throws Exception
	 */
	public String export() {

		logger.trace("entering action...");

		/**
		 * 第一步，获取查询参数，并设置到queryPage
		 */
		setQueryCondition();

		// 设置导出文件名
		exportSetting.setFileName("列表.xls");

		/**
		 * 第二步，执行查询,并设置结果
		 */
		demoBiz.exportDemo(exportSetting);

		/**
		 * 第三步，设置导出文件
		 */
		return SUCCESS;
	}

	/**
	 * set query condition to query page and export setting
	 */
	private void setQueryCondition() {
		String demoName = getSearchFields().get("demoName");
		if (StringUtils.isNotEmpty(demoName)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("demoName", "%"
						+ demoName + "%");
				queryPage.addLikeSearch("demoName", demoName);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("demoName", "%"
						+ demoName + "%");
				exportSetting.addLikeSearch("demoName",
						demoName);
			}
		}
		String demoDescription = getSearchFields().get("demoDescription");
		if (StringUtils.isNotEmpty(demoDescription)) {
			if (null != queryPage) {
				queryPage.addQueryCondition("demoDescription", "%" + demoDescription + "%");
				queryPage.addLikeSearch("demoDescription", demoDescription);
			}
			if (null != exportSetting) {
				exportSetting.addQueryCondition("demoDescription", "%" + demoDescription + "%");
				exportSetting.addLikeSearch("demoDescription", demoDescription);
			}
		}
	}
	
	public IDemoBiz getDemoBiz() {
		return demoBiz;
	}

	public void setDemoBiz(IDemoBiz demoBiz) {
		this.demoBiz = demoBiz;
	}

	public Demo getDemo() {
		return demo;
	}

	public void setDemo(Demo demo) {
		this.demo = demo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
