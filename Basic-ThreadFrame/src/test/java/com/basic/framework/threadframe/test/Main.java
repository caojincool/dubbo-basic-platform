package com.basic.framework.threadframe.test;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.threadframe.dto.DispatchMode;
import com.basic.framework.threadframe.dto.ThreadConfDto;
import com.basic.framework.threadframe.dto.ThreadState;
import com.basic.framework.threadframe.thread.ThreadManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by lzj on 2017/6/29.
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.doTest();
    }

    private void doTest() throws Exception {
        ThreadConfDto confDto = new ThreadConfDto();
        confDto.setBufferSize(50);
        confDto.setConsumer(new ConsumerBusiImpl());
        confDto.setConsumerThreadCount(5);
        confDto.setDispatchMode(DispatchMode.BALANCE_MODE.getCode());
        confDto.setErrSleepTime(4000);
        confDto.setNoDataSleepTime(4000);
        confDto.setOverSleepTime(4000);
        confDto.setProcessCode("TestProcess1");
        confDto.setProduceCount(100);
        confDto.setProducer(new ProducerBusiImpl());
        confDto.setProducerThreadCount(4);
        confDto.setSleepTime(1000);
        confDto.setThreadGroupName("TestThreadGrp");

        ThreadManager threadManager = new ThreadManager(confDto);
        threadManager.startThread();

        printState(threadManager);

        Thread.sleep(10000);

        logger.error("暂停");
        threadManager.pauseThread();

        Thread.sleep(10000);
        logger.error("暂停10S后恢复");
        threadManager.reStartThread();

    }


    private void printState(ThreadManager threadManager ) throws InterruptedException {
        for(int i=0;i<100;i++){
            Thread.sleep(5000);
            List<ThreadState> list = threadManager.getThreadState();
            String s = JsonUtils.getInstance().objectToJson(threadManager.getThreadState());
            logger.error("---------------------------------------------");
            logger.error("---------------------------------------------");
            logger.error("---------------------------------------------");
            logger.error("---------------------------------------------");
            logger.error("运行10S后线程状态"+s);
            logger.error("---------------------------------------------");
            logger.error("---------------------------------------------");
            logger.error("---------------------------------------------");
            logger.error("---------------------------------------------");

        }

    }

}
