package com.basic.framework.threadframe.api;

import com.basic.framework.threadframe.dto.MsgAbstractDto;

/**
 * Created by lzj on 2017/6/28.
 *
 */
public interface Consumer {

    /**
     *
     * @param processCode
     * @param threadGroupName
     * @param threadIndex
     * @param threadCount
     * @throws Exception
     */
    public void init(String processCode, String threadGroupName, int threadIndex, int threadCount) throws Exception;

    /**
     *
     * @param msgDto
     * @throws Exception
     */
    public void consumeMsg(MsgAbstractDto msgDto) throws Exception;

    /**
     *
     * @param processCode
     * @param threadGroupName
     * @param threadIndex
     * @param threadCount
     * @throws Exception
     * @Discrption 停止线程时调用,采用kill时，无法调用此方法

    public void destroy(String processCode, String threadGroupName, int threadIndex, int threadCount) throws Exception;\
     */
}
