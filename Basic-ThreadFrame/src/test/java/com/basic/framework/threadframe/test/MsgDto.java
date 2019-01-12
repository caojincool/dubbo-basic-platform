package com.basic.framework.threadframe.test;

import com.basic.framework.threadframe.dto.MsgAbstractDto;

/**
 * Created by lzj on 2017/6/29.
 */
public class MsgDto extends MsgAbstractDto{

    private String userName;
    private Long userNum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserNum() {
        return userNum;
    }

    public void setUserNum(Long userNum) {
        this.userNum = userNum;
    }
}
