package com.basic.framework.quartz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.quartz.manager.QuartzManager;
import com.basic.framework.quartz.model.ScheduleJob;

/**
 *
 * @date 2016年9月8日 下午4:54:28
 * @author 杰
 * @Description: tomcat启动10s之后自动执行的初始化任务，用于中途服务器停止或者重启，某些定时器重新跑起来
 * 
 */
//@Service("quartzTestServiceImpl")
public class InitTask{
	
	//@Autowired
	//private OaasOrgService oaasOrgService;
	@Autowired
	private QuartzManager quartzManager;
	
    public void execute() {
        try {
        	
            String jobName = "demo-test-task1";
            System.out.println("------------"+jobName+"定时任务开始(每1秒输出一次)----------------");
            quartzManager.addJob(jobName, TestTask.class, "0/1 * * * * ?");
//          quartzManager.addJob(job_name, TestTask.class, "0/1 * * * * ?");//同样的job_name不能添加同一个任务
            String jobName2 = "demo-test-task2";
            System.out.println("------------"+jobName2+"定时任务开始(每1秒输出一次)----------------");
            quartzManager.addJob(jobName2, TestTask.class, "0/1 * * * * ?");
            Thread.sleep(4000);
            System.out.println("------------"+jobName2+"定时任务修改(每10秒输出一次)----------------");
            quartzManager.modifyJobTime(jobName2, "*/10 * * * * ?");
            Thread.sleep(30000);
            System.out.println("------------"+jobName2+"定时任务移除----------------");
            quartzManager.removeJob(jobName2);
            System.out.println("------------"+jobName2+"定时任务移除成功----------------");
            
            
            List<ScheduleJob> runjobs = quartzManager.getAllRunJobs();
            System.out.println("------------运行中的所有任务----------------");
            System.out.println(JsonUtils.getInstance().objectToJson(runjobs));
            
            List<ScheduleJob> jobs = quartzManager.getAllJobs();
            System.out.println("------------所有任务----------------");
            System.out.println(JsonUtils.getInstance().objectToJson(jobs));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
