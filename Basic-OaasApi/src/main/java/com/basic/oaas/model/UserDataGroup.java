package com.basic.oaas.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年9月17日 下午9:23:19
 * @author Kevin
 * @Description: 用户数据权限分组
 *
 */
public class UserDataGroup  implements Serializable{
	private static final long serialVersionUID = 4233692137859761769L;

	private Long userDataGrpId;

    private Long dataGroupId;

    private Long userId;

    public Long getUserDataGrpId() {
        return userDataGrpId;
    }

    public void setUserDataGrpId(Long userDataGrpId) {
        this.userDataGrpId = userDataGrpId;
    }

    public Long getDataGroupId() {
        return dataGroupId;
    }

    public void setDataGroupId(Long dataGroupId) {
        this.dataGroupId = dataGroupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}