/**
 * companyName
 * copyright 2015-2020
 *
 * @date 2016年8月3日 下午3:58:47
 * @author zwj
 * @Description: 数据实体
 */
package com.basic.framework.threadframe.dto;

/**
 * @author zwj
 * @date 2016年8月3日 下午3:58:47
 * @Description: 数据实体
 */
public class MsgAbstractDto {

    private Long msgId;

    private int producerThreadIndex;//生产者线程序号
    private int producerThreadCound;//生产者线程数量

    private int consumerThreadIndex;// 消费者线程序号
    private int consumerThreadCount;// 消费者线程数量
    private String processCode;//进程编码

    private long beginProduceTime;// 开始生产时间
    private long finishProduceTime;// 完成生产时间
    private long beginConsumeTime;// 开始消费时间
    private long finishConsumeTime;// 完成消费时间
    private int hashCode;//散列值

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public int getProducerThreadIndex() {
        return producerThreadIndex;
    }

    public void setProducerThreadIndex(int producerThreadIndex) {
        this.producerThreadIndex = producerThreadIndex;
    }

    public int getProducerThreadCound() {
        return producerThreadCound;
    }

    public void setProducerThreadCound(int producerThreadCound) {
        this.producerThreadCound = producerThreadCound;
    }

    public int getConsumerThreadIndex() {
        return consumerThreadIndex;
    }

    public void setConsumerThreadIndex(int consumerThreadIndex) {
        this.consumerThreadIndex = consumerThreadIndex;
    }

    public int getConsumerThreadCount() {
        return consumerThreadCount;
    }

    public void setConsumerThreadCount(int consumerThreadCount) {
        this.consumerThreadCount = consumerThreadCount;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public long getBeginProduceTime() {
        return beginProduceTime;
    }

    public void setBeginProduceTime(long beginProduceTime) {
        this.beginProduceTime = beginProduceTime;
    }

    public long getFinishProduceTime() {
        return finishProduceTime;
    }

    public void setFinishProduceTime(long finishProduceTime) {
        this.finishProduceTime = finishProduceTime;
    }

    public long getBeginConsumeTime() {
        return beginConsumeTime;
    }

    public void setBeginConsumeTime(long beginConsumeTime) {
        this.beginConsumeTime = beginConsumeTime;
    }

    public long getFinishConsumeTime() {
        return finishConsumeTime;
    }

    public void setFinishConsumeTime(long finishConsumeTime) {
        this.finishConsumeTime = finishConsumeTime;
    }

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }
}
