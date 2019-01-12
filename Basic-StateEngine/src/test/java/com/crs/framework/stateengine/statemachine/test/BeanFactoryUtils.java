package com.basic.framework.stateengine.statemachine.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring beanFactory
 *  
 *
 */
public class BeanFactoryUtils {
	private static Logger logger = LoggerFactory.getLogger(BeanFactoryUtils.class);
	private static BeanFactory beanFactory = null;
	
	private static String SPRING_FILE_PATH = "config/spring/application-stateeingine-context.xml";
	
	static{
		logger.error("Spring init......begin");
		beanFactory = new ClassPathXmlApplicationContext(SPRING_FILE_PATH);
		logger.error("Spring init......finish");
	}

	private BeanFactoryUtils() {
	}

	
	public static Object getBeanByName(String beanName) {
		
		return beanFactory.getBean(beanName);
	}

	public static <T> T getBeanByName(String beanName, Class<T> arg1) {
		return beanFactory.getBean(beanName, arg1);
	}

}
