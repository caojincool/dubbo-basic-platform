package com.basic.framework.threadframe.thread;

import com.basic.framework.common.utils.datatype.IntegerUtils;
import com.basic.framework.threadframe.dto.DispatchMode;
import com.basic.framework.threadframe.dto.MsgAbstractDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lzj on 2017/6/29.
 * 任务派发器
 */
public class Dispatcher {

    private static final Logger logger = LoggerFactory.getLogger(Dispatcher.class);

    private final LinkedBlockingQueue<MsgAbstractDto>[] msgQueueArr;
    private final int bufferSize;
    private final String diaptchMode;
    private final int queueCount;
    private final Random random;

    Dispatcher(LinkedBlockingQueue<MsgAbstractDto>[] msgQueueArr, int bufferSize, String diaptchMode) {
        this.msgQueueArr = msgQueueArr;
        this.bufferSize = bufferSize;
        this.diaptchMode = diaptchMode;
        queueCount = msgQueueArr.length;
        random = new Random();
    }

    /**
     * 是否可派发，生产者在生产前调用此方法，判断是否可以生产
     *
     * @return
     */
    boolean canDispatch() {
        boolean flag = false;
        for (LinkedBlockingQueue<MsgAbstractDto> msgQueue : msgQueueArr) {
            if (msgQueue.size() < bufferSize) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    /**
     * 派发消息
     * @param msgDto
     * @throws InterruptedException
     */
    void dispatchMsg(MsgAbstractDto msgDto) throws InterruptedException {
        int queueIndex = 0;
        if (DispatchMode.BALANCE_MODE.getCode().equals(diaptchMode)) {
            queueIndex = this.getFreeQueueIndex();
        } else if (DispatchMode.HASH_MODE.getCode().equals(diaptchMode)) {
            queueIndex = this.getQueueIndexByHashCode(msgDto.getHashCode());
        }else {
            queueIndex = this.getQueueIndexByRandom();
        }
        logger.debug("派发消息到queueIndex:{}/{},msgId:{},msgHashCode:{}",queueIndex,queueCount,msgDto.getMsgId(),msgDto.getHashCode());
        LinkedBlockingQueue<MsgAbstractDto> msgQueue =msgQueueArr[queueIndex];

        msgQueue.put(msgDto);
    }

    private int getQueueIndexByRandom(){
        int index = random.nextInt(queueCount);
        return index;
    }

    private int getQueueIndexByHashCode(long hashCode ){
        int index = IntegerUtils.valueOf(hashCode % queueCount);
        return index;
    }

    private int getFreeQueueIndex() {
        int index = 0;
        int count = Integer.MAX_VALUE;
        for (int i = 0; i < msgQueueArr.length; i++) {
            int curCount = msgQueueArr[i].size();
            if (curCount <= 0) {
                index = i;
                break;
            } else if (curCount < count) {
                index = i;
                count = curCount;
            }
        }
        return index;
    }

}
