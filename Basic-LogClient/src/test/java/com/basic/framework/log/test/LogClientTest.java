package com.basic.framework.log.test;

import com.basic.framework.common.BeanFactoryUtils;
import com.basic.framework.log.client.LogClient;
import com.basic.log.model.SystemLog;

/**
 * 
 *
 * @date 2017年8月1日 下午3:44:56
 * 
 * @Description: 测试客户端的流程
 *
 */
public class LogClientTest {

	public static void main(String args[]){
		LogClientTest logClientTest = new LogClientTest();
		logClientTest.test();
	}
	
    public void test() {
    	LogClient logClient = BeanFactoryUtils.getBeanByName("logClient", LogClient.class);
		SystemLog systemLog = new SystemLog();
//		systemLog.setlogId
		systemLog.setRequestUrl("url");
		systemLog.setRequestContent("内容");
		systemLog.setLogType("MENU");
		systemLog.setUserId(1L);
//		systemLog.setCreateTime(createTime);
		systemLog.setCustId("ip");
    	logClient.sendLog(systemLog);
    }


}
