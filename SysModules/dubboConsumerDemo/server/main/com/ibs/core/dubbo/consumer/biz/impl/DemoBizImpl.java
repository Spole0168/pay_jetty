package com.ibs.core.dubbo.consumer.biz.impl;


import com.ibs.core.dubbo.consumer.biz.IDemoBiz;
import com.ibs.core.dubbo.dto.DemoDto;
import com.ibs.core.dubbo.service.IDemoService;

public class DemoBizImpl implements IDemoBiz{
	private IDemoService demoService;
	@Override
	public void getDemoById(String id) {
		DemoDto demoDto = new DemoDto();
		demoDto.setId(id);
		demoService.findDemoByDto(demoDto);
	}
	
	public IDemoService getDemoService() {
		return demoService;
	}
	public void setDemoService(IDemoService demoService) {
		this.demoService = demoService;
	}
	
	
}
