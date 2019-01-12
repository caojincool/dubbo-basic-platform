package com.basic.framework.threadframe.test;

import com.basic.framework.threadframe.api.Consumer;
import com.basic.framework.threadframe.dto.MsgAbstractDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lzj on 2017/6/29.
 */
public class ConsumerBusiImpl implements Consumer {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerBusiImpl.class);

    /**
     * @param processCode
     * @param threadGroupName
     * @param threadIndex
     * @param threadCount
     * @throws Exception
     */
    @Override
    public void init(String processCode, String threadGroupName, int threadIndex, int threadCount) throws Exception {
        logger.info("消费者初始化！processCode:{},threadGroupName:{},threadIndex:{},threadCount:{}", processCode, threadGroupName, threadIndex, threadCount);
    }

    /**
     * @param msgDto
     * @throws Exception
     */
    @Override
    public void consumeMsg(MsgAbstractDto msgDto) throws Exception {
        logger.info("消费消息，msgId:{}",msgDto.getMsgId());
        Thread.sleep(500);
    }
}
