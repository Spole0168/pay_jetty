package com.ibs.core.dubbo.service;

import com.ibs.core.dubbo.dto.DemoDto;

public interface IDemoService {
	void sayHello(String userName);
	public DemoDto findDemoByDto(DemoDto demoDto);
}
