package com.ibs.core.dubbo.service.impl;

import com.ibs.core.dubbo.dao.IDemoDao;
import com.ibs.core.dubbo.dto.DemoDto;
import com.ibs.core.dubbo.service.IDemoService;

public class DemoServiceImpl implements IDemoService{
	private IDemoDao demoDao;
	
	public void setDemoDao(IDemoDao demoDao) {
		this.demoDao = demoDao;
	}
	
	@Override
	public void sayHello(String userName) {
		System.out.println("hello,"+userName);
	}
	@Override
	public DemoDto findDemoByDto(DemoDto demoDto) {
		return demoDao.findDemoByDto(demoDto);
	}
}
