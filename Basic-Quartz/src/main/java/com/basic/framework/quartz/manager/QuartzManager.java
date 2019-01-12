package com.basic.framework.quartz.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.basic.framework.quartz.model.ScheduleJob;

/**
 * 
 *
 * @date 2017年8月3日 下午5:33:05
 * 
 * @Description: 动态设置定时器
 * 由于有的定时器是xml定义的，所以调度对象不能新建，而是注入
 *
 */
@Component
public class QuartzManager {
	
	private static final Logger logger = LoggerFactory.getLogger(QuartzManager.class);
	
	private static String JOB_GROUP_NAME = "JOB_GROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "TRIGGER_GROUP_NAME";

    //SchedulerFactoryBean是一个工厂bean，注入后得到的应该是Scheduler
    @Autowired
    private Scheduler scheduler;

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	/**
     * 
     * @date 2016年11月3日 上午10:06:42
     * @author 杰
     * @Description: 创建一个调度对象
     * 已改为spring管理，时间：2017-8-3 16:46:19
     * @return
     *
     */
//    private static Scheduler getScheduler(){
//        SchedulerFactory sf = new StdSchedulerFactory();  
//        Scheduler scheduler=null;  
//        try {  
//            scheduler = sf.getScheduler();  
//        } catch (SchedulerException e) {  
//        	logger.error("QuartzManager 创建一个调度对象错误 ERROR:{}", e);
//        }  
//        return scheduler;  
//    }
    
    /**
     * 
     * @date 2016年11月3日 上午10:07:32
     * @author 杰
     * @Description: 获取一个调度对象
     * @return
     *
     */
//	public Scheduler getInstanceScheduler(){  
//		
//		return scheduler;  
//	} 	
	
	
	/**
	 * 
	 * @date 2017年8月3日 下午5:02:03
	 * 
	 * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * @param jobName
	 * @param cls
	 * @param time
	 * @throws SchedulerException 
	 *
	 */
	public void addJob(String jobName, @SuppressWarnings("rawtypes") Class cls, String time) throws SchedulerException {
		if (logger.isDebugEnabled()) {
			logger.debug("addJob 添加一个定时任务:jobName{},cls:{},time:{}", jobName, cls, time);
		}

		if (StringUtils.isBlank(jobName) || cls == null || StringUtils.isBlank(time)) {
			throw new NullPointerException("jobName or cls or time can not be null");
		}

		@SuppressWarnings("unchecked")
		JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(jobName, JOB_GROUP_NAME).build();

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME).startNow()
				// .withSchedule(SimpleScheduleBuilder.simpleSchedule()
				// .withIntervalInSeconds(10) //时间间隔
				// .withRepeatCount(5)) //重复次数(将执行6次)
				.withSchedule(CronScheduleBuilder.cronSchedule(time))// 固定时间
				.build();

		scheduler.scheduleJob(jobDetail, trigger);
		// 启动
		if (!scheduler.isShutdown()) {
			scheduler.start();
		}

		// 1.x版本的写法
		/*
		 * JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, cls);//
		 * 任务名，任务组，任务执行类 // 触发器 CronTrigger trigger = new CronTrigger(jobName,
		 * TRIGGER_GROUP_NAME);// 触发器名,触发器组 trigger.setCronExpression(time);//
		 * 触发器时间设定 scheduler.scheduleJob(jobDetail, trigger); // 启动 if
		 * (!scheduler.isShutdown()) { scheduler.start(); }
		 */

	}

    /**
     * 
     * @date 2017年8月3日 下午5:02:34
     * 
     * @Description: 添加一个定时任务
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @param jobClass
     * @param time
     *
     */
//	@SuppressWarnings("unchecked")
//	public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
//			@SuppressWarnings("rawtypes") Class jobClass, String time) {
//		if (logger.isDebugEnabled()) {
//			logger.debug("addJob 添加一个定时任务jobName:{},jobGroupName:{},triggerName:{},triggerGroupName:{},jobClass:{},time:{}",
//					jobName, jobGroupName, triggerName, triggerGroupName, jobClass, time);
//		}
//
//		if (StringUtils.isBlank(jobName) || jobClass == null || StringUtils.isBlank(time)) {
//			throw new NullPointerException("jobName or jobClass or time can not be null");
//		}
//
//		try {
//			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
//
//			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).startNow()
//					// .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//					// .withIntervalInSeconds(10) //时间间隔
//					// .withRepeatCount(5)) //重复次数(将执行6次)
//					.withSchedule(CronScheduleBuilder.cronSchedule(time))// 固定时间
//					.build();
//
//			scheduler.scheduleJob(jobDetail, trigger);
//			// 启动
//			if (!scheduler.isShutdown()) {
//				scheduler.start();
//			}
//
//			// 1.x版本的写法
//			/*
//			 * JobDetail jobDetail = new JobDetail(jobName, jobGroupName,
//			 * jobClass);// 任务名，任务组，任务执行类 // 触发器 CronTrigger trigger = new
//			 * CronTrigger(triggerName, triggerGroupName);// 触发器名,触发器组
//			 * trigger.setCronExpression(time);// 触发器时间设定
//			 * scheduler.scheduleJob(jobDetail, trigger);
//			 */
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}

    /**
     * 
     * @date 2017年8月3日 下午5:02:50
     * 
     * @Description: 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     * @param jobName
     * @param time
     * @throws SchedulerException 
     *
     */
	@SuppressWarnings("rawtypes")
	public void modifyJobTime(String jobName, String time) throws SchedulerException {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyJobTime 修改一个定时任务jobName:{},time:{}", jobName, time);
		}

		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		if (trigger == null) {
			return;
		}

		String oldTime = trigger.getCronExpression();
		if (!oldTime.equalsIgnoreCase(time)) {
			JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			Class objJobClass = jobDetail.getJobClass();
			removeJob(jobName);
			addJob(jobName, objJobClass, time);
		}

		// 1.x版本的写法
		/*
		 * CronTrigger trigger = (CronTrigger) scheduler.getTrigger(jobName,
		 * TRIGGER_GROUP_NAME); if (trigger == null) { return; } String oldTime
		 * = trigger.getCronExpression(); if (!oldTime.equalsIgnoreCase(time)) {
		 * JobDetail jobDetail = scheduler.getJobDetail(jobName,
		 * JOB_GROUP_NAME); Class objJobClass = jobDetail.getJobClass();
		 * removeJob(scheduler, jobName); addJob(scheduler, jobName,
		 * objJobClass, time); }
		 */
	}

    /**
     * 
     * @date 2017年8月3日 下午5:03:10
     * 
     * @Description: 修改一个任务的触发时间
     * @param triggerName
     * @param triggerGroupName
     * @param time
     *
     */
//    public void modifyJobTime(String triggerName, String triggerGroupName, String time) {
//    	if (logger.isDebugEnabled()) {
//			logger.debug("modifyJobTime 修改一个定时任务:{},:{},:{}", triggerName, triggerGroupName, time);
//		}
//    	
//    	try {
//        	TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
//        	CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerKey);
//        	
//        	if (trigger == null) {
//                return;
//            }
//        	
//        	String oldTime = trigger.getCronExpression();
//            if (!oldTime.equalsIgnoreCase(time)) {
//            	//设置新的时间
//                CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(time);
//                //trigger已存在，更新相应的定时设置，按新的cronExpression表达式重新构建trigger
//                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(schedBuilder).build();
//                //按新的trigger重新设置job执行
//            	scheduler.rescheduleJob(triggerKey, trigger);
//            }
//            
//            //1.x版本的写法
///*            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerName, triggerGroupName);
//            if (trigger == null) {
//                return;
//            }
//            String oldTime = trigger.getCronExpression();
//            if (!oldTime.equalsIgnoreCase(time)) {
//                CronTrigger ct = (CronTrigger) trigger;
//                // 修改时间
//                ct.setCronExpression(time);
//                // 重启触发器
//                scheduler.resumeTrigger(triggerName, triggerGroupName);
//            }*/
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * 
     * @date 2017年8月3日 下午5:03:23
     * 
     * @Description: 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
     * @param jobName
     * @throws SchedulerException 
     *
     */
	public void removeJob(String jobName) throws SchedulerException {
		if (logger.isDebugEnabled()) {
			logger.debug("removeJob 移除一个定时任务jobName:{}", jobName);
		}

		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
		JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
		scheduler.pauseTrigger(triggerKey);
		scheduler.unscheduleJob(triggerKey);
		scheduler.deleteJob(jobKey);

		// 1.x版本的写法
		/*
		 * scheduler.pauseTrigger(jobName, TRIGGER_GROUP_NAME);// 停止触发器
		 * scheduler.unscheduleJob(jobName, TRIGGER_GROUP_NAME);// 移除触发器
		 * scheduler.deleteJob(jobName, JOB_GROUP_NAME);// 删除任务
		 */
	}

    /**
     * 
     * @date 2017年8月3日 下午5:03:36
     * 
     * @Description: 移除一个任务
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     *
     */
//    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
//    	if (logger.isDebugEnabled()) {
//			logger.debug("removeJob 移除一个定时任务:{},:{},:{},:{}", jobName, jobGroupName, triggerName, triggerGroupName);
//		}
//    	
//    	try {
//        	TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
//        	JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
//        	scheduler.pauseTrigger(triggerKey);
//        	scheduler.unscheduleJob(triggerKey);
//        	scheduler.deleteJob(jobKey);
//        	
//        	//1.x版本的写法
///*            scheduler.pauseTrigger(triggerName, triggerGroupName);// 停止触发器
//            scheduler.unscheduleJob(triggerName, triggerGroupName);// 移除触发器
//            scheduler.deleteJob(jobName, jobGroupName);// 删除任务
//*/        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * 
     * @date 2017年8月3日 下午5:03:51
     * 
     * @throws SchedulerException 
     * @Description: 启动所有定时任务
     *
     */
	public void startJobs() throws SchedulerException {
		if (logger.isDebugEnabled()) {
			logger.debug("startJobs 启动所有定时任务");
		}

		scheduler.start();

	}

    /**
     * 
     * @date 2017年8月3日 下午5:04:04
     * 
     * @throws SchedulerException 
     * @Description: 关闭所有定时任务
     *
     */
	public void shutdownJobs() throws SchedulerException {
		if (logger.isDebugEnabled()) {
			logger.debug("shutdownJobs 关闭所有定时任务");
		}
		if (!scheduler.isShutdown()) {
			scheduler.shutdown();
		}

	}
    
    /**
     * 
     * @date 2017年8月3日 下午5:04:12
     * 
     * @Description: 查询所有正在运行的任务
     * @return
     * @throws SchedulerException
     *
     */
    public List<ScheduleJob> getAllRunJobs() throws SchedulerException {
    	if (logger.isDebugEnabled()) {
    		logger.debug("getAllRunJobs 查询所有正在运行的任务");
    	}
    	
//    	Scheduler scheduler = schedulerFactoryBean.getScheduler();
    	List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
    	List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
    	for (JobExecutionContext executingJob : executingJobs) {
    	    ScheduleJob job = new ScheduleJob();
    	    JobDetail jobDetail = executingJob.getJobDetail();
    	    JobKey jobKey = jobDetail.getKey();
    	    Trigger trigger = executingJob.getTrigger();
    	    job.setJobName(jobKey.getName());
    	    job.setJobGroup(jobKey.getGroup());
    	    job.setRemarks("触发器:" + trigger.getKey());
    	    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
    	    job.setJobStatus(triggerState.name());
    	    if (trigger instanceof CronTrigger) {
    	        CronTrigger cronTrigger = (CronTrigger) trigger;
    	        String cronExpression = cronTrigger.getCronExpression();
    	        job.setCronExpression(cronExpression);
    	    }
    	    jobList.add(job);
    	}
		return jobList;
    }
    
    /**
     * 
     * @date 2017年8月3日 下午5:05:57
     * 
     * @Description: 查询所有的任务
     * @return
     * @throws SchedulerException
     *
     */
    public List<ScheduleJob> getAllJobs() throws SchedulerException {
    	if (logger.isDebugEnabled()) {
    		logger.debug("getAllJobs 查询所有的任务");
    	}
    	
//    	Scheduler scheduler = schedulerFactoryBean.getScheduler();
    	GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
    	Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
    	List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
    	for (JobKey jobKey : jobKeys) {
    	    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
    	    for (Trigger trigger : triggers) {
    	        ScheduleJob job = new ScheduleJob();
    	        job.setJobName(jobKey.getName());
    	        job.setJobGroup(jobKey.getGroup());
    	        job.setRemarks("触发器:" + trigger.getKey());
    	        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
    	        job.setJobStatus(triggerState.name());
    	        if (trigger instanceof CronTrigger) {
    	            CronTrigger cronTrigger = (CronTrigger) trigger;
    	            String cronExpression = cronTrigger.getCronExpression();
    	            job.setCronExpression(cronExpression);
    	        }
    	        jobList.add(job);
    	    }
    	}
    	
    	return jobList;
    }
}
