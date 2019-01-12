package com.basic.framework.quartz.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 
 *
 * @date 2017年8月3日 下午5:52:54
 * 
 * @Description: 测试定时器
 *
 */
public class TestTask implements Job{

	//xml配置调用的方法，无参数
	public void execute(){
		System.out.println("---------------------------");
		System.out.println("定时任务开始了");
		System.out.println("---------------------------");
	}

	//代码调用的方法，有传入参数
	//例如：QuartzManager启动的定时器的方法入口
/*	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("123asd");
	}*/

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
//		execute();
        JobDetail jobDetail = context.getJobDetail();
        String jobName = jobDetail.getKey().getName();   //任务名称
        System.out.println("当前时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"，任务名称："+jobName);
        
        //String fullName = jobDetail.getKey().getFullName();   //任务名称
        //System.out.println(fullName);
//        JobDataMap dataMap = jobDetail.getJobDataMap();     //任务所配置的数据映射表
//
//        dataMap.getKeys();
//        for(String key : dataMap.getKeys()){
//        	System.out.println(key+":"+dataMap.get(key));
//        }
//        
//        Date now = new Date();
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(now);
//		calendar.add(Calendar.MINUTE, -1);//考试开始时间加上答题分钟数，延迟5分钟执行
//		now = calendar.getTime();
//		Timer timer = new Timer();
//		final Date now1 = now;
//		timer.schedule(new TimerTask() {
//            public void run() {
//            	try {
//            		System.out.println(now1+"----------123");
//				} catch (Exception e) {
//					//logger.error("试卷提交操作失败:{}", e);
//				}
//            }
//        }, now);
//		System.out.println(now+"-------456");
	}
	
}
