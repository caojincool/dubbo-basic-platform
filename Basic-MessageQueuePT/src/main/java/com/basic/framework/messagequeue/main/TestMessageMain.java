package com.basic.framework.messagequeue.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.basic.framework.messagequeue.impl.MessageSenderActiveMQ;

/**
 * 
 *
 * @date 2017年5月25日 下午5:51:19
 * 
 * @Description: 测试消息队列的压力和稳定性
 *
 */
public class TestMessageMain {
	
	static Logger logger = LoggerFactory.getLogger(TestMessageMain.class);
	
	static ClassPathXmlApplicationContext context = new  ClassPathXmlApplicationContext(
			"config/spring/application-busiservice-context.xml");
	
//	public static void main(String[] args) {
//        new Thread(new Runnable() {
// 
//            @Override
//            public void run() {
//                while (true) {
//                    System.out.println("数据");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//    }
	
	
    public static void main(String[] args) {  
    	
    	
    	MessageSenderActiveMQ sender = (MessageSenderActiveMQ) context.getBean("messageSenderActiveMQ");
        //创建一个可变尺寸的线程池
    	/*ExecutorService pool = Executors.newCachedThreadPool();
    	for(int i=0;i<100;i++){
    		//创建线程
    		logger.debug("创建线程"+i);
    		pool.execute(new MyThread(sender, logger));  
    	}*/
//        Thread t2 = new MyThread();  
//        Thread t3 = new MyThread();  
//        Thread t4 = new MyThread();  
//        Thread t5 = new MyThread();  
//        Thread t6 = new MyThread();  
//        Thread t7 = new MyThread();  
//        // 将线程放入池中进行执行  
//        pool.execute(t2);  
//        pool.execute(t3);  
//        pool.execute(t4);  
//        pool.execute(t5);  
//        pool.execute(t6);  
//        pool.execute(t7);  
        
//        pool.shutdown();  
    }  
}  
  
class MyThread extends Thread {
	
	MessageSenderActiveMQ sender;
	Logger logger;
	
	public MyThread(MessageSenderActiveMQ sender, Logger logger){
		this.sender = sender;
		this.logger = logger;
	}
	
	
    @Override  
    public void run() {  
    	while (true) {  
//    		System.out.println(Thread.currentThread().getName() + "正在执行。。。");
    		logger.debug(Thread.currentThread().getName() + "正在执行。。。");
    		try {
    			sender.sendMessage("1", "testProducerQueue1", "测试testProducerQueue1");
    			sender.sendMessage("2", "testProducerQueue2", "测试testProducerQueue2");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}  
    	}
    }  
    
}
