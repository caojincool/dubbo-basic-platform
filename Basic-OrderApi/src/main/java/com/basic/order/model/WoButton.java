package com.basic.order.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年9月13日 上午10:15:30
 * 
 * @Description: 任务按钮
 *
 */
public class WoButton implements Serializable {
	
	private static final long serialVersionUID = -4191313972241750684L;

	private Long woButtonId;

    private Long buttonId;

    private Long tacheId;

    public Long getWoButtonId() {
        return woButtonId;
    }

    public void setWoButtonId(Long woButtonId) {
        this.woButtonId = woButtonId;
    }

    public Long getButtonId() {
        return buttonId;
    }

    public void setButtonId(Long buttonId) {
        this.buttonId = buttonId;
    }

    public Long getTacheId() {
        return tacheId;
    }

    public void setTacheId(Long tacheId) {
        this.tacheId = tacheId;
    }
}