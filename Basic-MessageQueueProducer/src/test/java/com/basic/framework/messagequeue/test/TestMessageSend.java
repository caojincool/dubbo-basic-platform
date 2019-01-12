package com.basic.framework.messagequeue.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.basic.framework.messagequeue.impl.MessageSenderActiveMQ;

/**
 * 
 *
 * @date 2017年7月12日 下午4:11:26
 * 
 * @Description: 测试发送消息
 *
 */
public class TestMessageSend {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new  ClassPathXmlApplicationContext(
				"config/spring/application-busiservice-context.xml");
		MessageSenderActiveMQ sender = (MessageSenderActiveMQ) context.getBean("messageSenderActiveMQ");
		sender.sendMessage("1", "testProducerQueue1", "测试testProducerQueue1");
		sender.sendMessage("2", "testProducerQueue2", "测试testProducerQueue2");
	}
	
	
}
