package com.basic.oaas.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年9月8日 上午10:42:23
 * @author Kevin
 * @Description: 数据权限实例数据
 *
 */
public class PrivateDataInstData implements Serializable {
	private static final long serialVersionUID = -4786682168088232555L;

	private Long dataInstDataId;

    private Long dataInstId;

    private Long dataDataId;

    private String sourceId;

    private String sourceName;

    public Long getDataInstDataId() {
        return dataInstDataId;
    }

    public void setDataInstDataId(Long dataInstDataId) {
        this.dataInstDataId = dataInstDataId;
    }

    public Long getDataInstId() {
        return dataInstId;
    }

    public void setDataInstId(Long dataInstId) {
        this.dataInstId = dataInstId;
    }

    public Long getDataDataId() {
        return dataDataId;
    }

    public void setDataDataId(Long dataDataId) {
        this.dataDataId = dataDataId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}