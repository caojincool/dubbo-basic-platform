package com.basic.framework.threadframe.api;

import com.basic.framework.threadframe.dto.MsgAbstractDto;

import java.util.List;

/**
 *
 */
public interface Producer {


    /**
     * 线程初始化，在查询线程初始化时调用
     *
     * @param processCode     进程id,因程序可能放多台机子或在同台机子上开启多个进程,故以该字段区分
     * @param threadGroupName 线程组名称
     * @param threadIndex     线程序号
     * @param threadCount     线程数量
     * @throws Exception
     */
    public void init(String processCode, String threadGroupName, int threadIndex, int threadCount) throws Exception;

    /**
     * 一次查询生产一批消息，由框架定时调用. 如果是扫描数据库表的情况
     *
     * @param processCode     进程编码
     * @param threadGroupName 线程组名称
     * @param threadIndex     线程序号
     * @param threadCount     线程数量
     * @param produceCount        一次查询数量
     * @return
     * @throws Exception
     */
    public List<MsgAbstractDto> produceMsg(String processCode, String threadGroupName, int threadIndex, int threadCount, int produceCount) throws Exception;

    /**
     * @param processCode     进程编码
     * @param threadGroupName 线程组名称
     * @param threadIndex     线程序号
     * @param threadCount     线程数量
     * @throws Exception
     * @date 2016年8月3日 下午10:47:18
     * @Description: 停止线程时调用,采用kill时，无法调用此方法

    public void destroy(String processCode, String threadGroupName, int threadIndex, int threadCount) throws Exception;
     */

}
