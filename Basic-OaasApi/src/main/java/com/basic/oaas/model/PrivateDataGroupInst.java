package com.basic.oaas.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年9月13日 下午2:38:30
 * @author Kevin
 * @Description: 数据权限分组实例关系表
 *
 */
public class PrivateDataGroupInst implements Serializable {
	private static final long serialVersionUID = -1036857741345445622L;

	private Long dataGrpInstId;

    private Long dataGroupId;

    private Long dataInstId;

    public Long getDataGrpInstId() {
        return dataGrpInstId;
    }

    public void setDataGrpInstId(Long dataGrpInstId) {
        this.dataGrpInstId = dataGrpInstId;
    }

    public Long getDataGroupId() {
        return dataGroupId;
    }

    public void setDataGroupId(Long dataGroupId) {
        this.dataGroupId = dataGroupId;
    }

    public Long getDataInstId() {
        return dataInstId;
    }

    public void setDataInstId(Long dataInstId) {
        this.dataInstId = dataInstId;
    }
}