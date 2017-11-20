package com.ibs.core.test.module.demo.biz;




import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ibs.core.module.demo.biz.IDemoBiz;
import com.ibs.core.module.demo.dao.IDemoDao;
import com.ibs.core.module.demo.domain.Demo;
import com.ibs.core.test.module.demo.ContextHelper;

import junit.framework.JUnit4TestAdapter;

public class DemoBizTest {
	protected final static Log logger = LogFactory.getLog(DemoBizTest.class);
	
	private IDemoDao demoDao;
	private IDemoBiz demoBiz;

	
	@Before
	public void runBefore() {
		ContextHelper.getContext();
		this.demoDao = (IDemoDao) ContextHelper.getBean("demoDao");
		this.demoBiz = (IDemoBiz) ContextHelper.getBean("demoBiz");
	}

	@After
	public void runAfter() {
		demoDao = null;
		demoBiz = null;
	}

	@Test
	public void test1() throws Exception {
		try {
			logger.info("Starting");
			Demo demo = new Demo();
//			demo.setId("AAAAAAAAAA02");
			demo.setDemoName("AAAAAAA");
			demo.setDemoDescription("AAAAAA");
			demoDao.saveOrUpdate(demo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	@Test
	public void test2() throws Exception {
		try {
			logger.info("Starting");
			Demo demo = new Demo();
			demo.setId("AAAAAAAAAA01");
			demo.setDemoName("AAAAAAA");
			demo.setDemoDescription("AAAAAA");
			demoBiz.saveDemo(demo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(DemoBizTest.class);
	}
	
}
