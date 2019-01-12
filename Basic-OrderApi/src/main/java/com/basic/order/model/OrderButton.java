package com.basic.order.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年9月13日 上午10:15:11
 * 
 * @Description: 单据按钮
 *
 */
public class OrderButton implements Serializable {

	private static final long serialVersionUID = -4502931098945041683L;

	private Long orderButtonId;

    private Long buttonId;

    private String orderType;

    public Long getOrderButtonId() {
        return orderButtonId;
    }

    public void setOrderButtonId(Long orderButtonId) {
        this.orderButtonId = orderButtonId;
    }

    public Long getButtonId() {
        return buttonId;
    }

    public void setButtonId(Long buttonId) {
        this.buttonId = buttonId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}