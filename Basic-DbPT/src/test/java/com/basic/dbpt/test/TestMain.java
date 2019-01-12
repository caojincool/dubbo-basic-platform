package com.basic.dbpt.test;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.basic.dbpt.model.DbPt;
import com.basic.dbpt.service.DbPtService;
import com.basic.framework.common.utils.PropertiesUtils;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.IntegerUtils;

/**
 * 
 *
 * @date 2017年8月11日 上午10:51:21
 * 
 * @Description: 数据库性能测试
 *
 */
public class TestMain {
	
	
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
	
	
    public static void main(String[] args) {/*  
    	
		//配置路径
		String path = "/config/properties/dbpt.properties";
		
    	DbPtService dbPtService = (DbPtService) context.getBean("dbPtService");
    	
    	Properties properties = PropertiesUtils.loadProperties(path);
    	String tableCount = properties.getProperty("tableCount");//表数量
    	String threadCount = properties.getProperty("threadCount");//每张表线程数量
    	String dbName = properties.getProperty("dbName");//数据库
    	String tableName = properties.getProperty("tableName");//表名前缀
    	
        //创建一个可变尺寸的线程池
    	ExecutorService pool = Executors.newCachedThreadPool();
    	
    	DbPt ibean = new DbPt();
    	ibean.setDbName(dbName);
    	int tblength = IntegerUtils.valueOf(tableCount);
    	String[] tbNames = new String[tblength];
    	for(int i=0;i<tblength;i++){
    		String tbName = tableName+i;//表名
    		ibean.setTableName(tbName);
    		tbNames[i] = tbName;
    		long count = dbPtService.qryIsExistTable(ibean);
    		if(count == 0){//没有表
    			dbPtService.createTable(ibean);
    		}
    	}
    	
    	for(String tbName : tbNames){
//    		ThreadGroup threadGroup = new ThreadGroup(tbName+"线程组");//每组线程写一张表
    		int thlength = IntegerUtils.valueOf(threadCount);
    		for(int i=0;i<thlength;i++){
    			DbPt dbPt = new DbPt();
    			dbPt.setId((long)i);
//    			dbPt.setName1(tbName+"线程组");
    			dbPt.setName2(tbName+"线程"+i);
    			dbPt.setName3("name3");
    			dbPt.setName4("name4");
    			dbPt.setName5("name5");
    			dbPt.setName6("name6");
    			dbPt.setName7("name7");
    			dbPt.setName8("name8");
//    			dbPt.setCreateTime(createTime);
    			dbPt.setDbName(dbName);
    			dbPt.setTableName(tbName);
//    			new MyThread(threadGroup, tbName+"线程"+i, dbPtService, dbPt).start();
//    			pool.execute(new MyThread(threadGroup, tbName+"线程"+i, dbPtService, dbPt));//用了线程池好像就用不了线程组
    			pool.execute(new MyThread(tbName+"线程"+i, dbPtService, dbPt));
        	}
    	}
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
    */}  
}  
  
class MyThread extends Thread {
	static Logger logger = LoggerFactory.getLogger(MyThread.class);
	DbPtService dbPtService;
	DbPt dbPt;
	
	public MyThread(ThreadGroup threadGroup, String threadName, DbPtService dbPtService, DbPt dbPt){
		super(threadGroup, threadName);
		this.dbPtService = dbPtService;
		this.dbPt = dbPt;
	}
	
	public MyThread(String threadName, DbPtService dbPtService, DbPt dbPt){
		this.dbPtService = dbPtService;
		this.dbPt = dbPt;
	}
	
    @Override  
    public void run() {  
    	boolean flag = true;
    	while (flag) {  
    		logger.debug("线程组：" + Thread.currentThread().getThreadGroup().getName() + "正在执行。。。");
    		logger.debug("线程：" + Thread.currentThread().getName() + "正在执行。。。");
    		try {
    			Date now = DateUtils.now();
    			dbPt.setCreateTime(now);
    			dbPtService.create(dbPt);
//				Thread.sleep(5000);
			} catch (Exception e) {
//				flag = false;
				e.printStackTrace();
				//中断抛出异常的线程组里的所有线程的运行
//				Thread.currentThread().getThreadGroup().interrupt();
//				logger.debug("------------------------------------------------------------");
//				logger.debug("中断抛出异常的线程组里的所有线程的运行：" + Thread.currentThread().getThreadGroup().getName());
//				logger.debug("当前线程组里面存活的线程的数量：" + Thread.currentThread().getThreadGroup().activeCount());
			}  
    	}
    }  
    
}
