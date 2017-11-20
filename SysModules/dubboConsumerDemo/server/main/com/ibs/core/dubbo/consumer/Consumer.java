package com.ibs.core.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ibs.core.dubbo.consumer.biz.IDemoBiz;

public class Consumer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		context.start();

		IDemoBiz demoBiz = (IDemoBiz) context.getBean("demoBiz");
		
		System.out.println("demoBiz:"+demoBiz);
		
		demoBiz.getDemoById("1");
		
	}
}
