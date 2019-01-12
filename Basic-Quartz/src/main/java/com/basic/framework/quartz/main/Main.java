package com.basic.framework.quartz.main;

//import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @date 2016年11月2日 上午11:38:42
 * @author 杰
 * @Description: 启动的main方法
 * 
 */
public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		//ApplicationContext context = new ClassPathXmlApplicationContext("config/spring/application-busiservice-context.xml");
		new ClassPathXmlApplicationContext("config/spring/application-busiservice-context.xml");
	}

}
