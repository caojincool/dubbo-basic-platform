package com.basic.framework.process.test.impl;

import com.basic.framework.common.utils.datatype.DoubleUtils;
import com.basic.framework.common.utils.datatype.LongUtils;
import com.basic.framework.threadframe.api.Producer;
import com.basic.framework.threadframe.dto.MsgAbstractDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzj on 2017/6/29.
 */
public class ProducerBusiImpl implements Producer {

    private static final Logger logger = LoggerFactory.getLogger(ProducerBusiImpl.class);

    /**
     *
     * @param processCode     进程id,因程序可能放多台机子或在同台机子上开启多个进程,故以该字段区分
     * @param threadGroupName 线程组名称
     * @param threadIndex     线程序号
     * @param threadCount     线程数量
     * @throws Exception
     */
    @Override
    public void init(String processCode, String threadGroupName, int threadIndex, int threadCount) throws Exception {
        logger.info("生产者初始化！processCode:{},threadGroupName:{},threadIndex:{},threadCount:{}", processCode, threadGroupName, threadIndex, threadCount);

        // 将当前进程中正在处理中的消息属于当前线程的更新为未处理
    }

    /**
     * 生产数据
     * @param processCode     进程编码
     * @param threadGroupName 线程组名称
     * @param threadIndex     线程序号
     * @param threadCount     线程数量
     * @param produceCount        一次查询数量
     * @return
     * @throws Exception
     */
    int count =  0;
    @Override
    public List<MsgAbstractDto> produceMsg(String processCode, String threadGroupName, int threadIndex, int threadCount, int produceCount) throws Exception {
        List<MsgAbstractDto> list = new ArrayList<>();
        
        //模拟数据，模拟数据最大数量为50条
        if(count > 50){
        	return null;
        }
        for(int i=0;i<produceCount;i++){
            MsgDto msgDto = new MsgDto();
            Long msgId = DoubleUtils.valueOf(Math.random()*10000000).longValue();
            msgDto.setMsgId(msgId);
            msgDto.setUserName("userName:"+i);
            msgDto.setUserNum(LongUtils.valueOf(i));
            list.add(msgDto);
            count += produceCount;
        }
        Thread.sleep(500);
        //将消息状态修改为处理中
        return list;
    }
}
