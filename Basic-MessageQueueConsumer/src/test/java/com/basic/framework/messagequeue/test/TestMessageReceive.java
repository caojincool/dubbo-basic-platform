package com.basic.framework.messagequeue.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 *
 * @date 2017年7月12日 下午4:12:59
 * 
 * @Description: 测试接收消息
 *
 */
public class TestMessageReceive {
	
	public static void main(String[] args) {
		@SuppressWarnings({ "unused", "resource" })
		ClassPathXmlApplicationContext context = new  ClassPathXmlApplicationContext(
				"config/spring/application-busiservice-context.xml");
	}
	
	
}
