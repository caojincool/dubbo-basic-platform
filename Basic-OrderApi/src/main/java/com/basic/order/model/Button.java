package com.basic.order.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年9月13日 上午10:14:19
 * 
 * @Description: 按钮
 *
 */
public class Button implements Serializable {

	private static final long serialVersionUID = -959788000595455038L;

	private Long buttonId;

    private String buttonName;

    private String jsFile;

    private String jsMethod;

    private String buttoState;

    private String privateCode;

    public Long getButtonId() {
        return buttonId;
    }

    public void setButtonId(Long buttonId) {
        this.buttonId = buttonId;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getJsFile() {
        return jsFile;
    }

    public void setJsFile(String jsFile) {
        this.jsFile = jsFile;
    }

    public String getJsMethod() {
        return jsMethod;
    }

    public void setJsMethod(String jsMethod) {
        this.jsMethod = jsMethod;
    }

    public String getButtoState() {
        return buttoState;
    }

    public void setButtoState(String buttoState) {
        this.buttoState = buttoState;
    }

    public String getPrivateCode() {
        return privateCode;
    }

    public void setPrivateCode(String privateCode) {
        this.privateCode = privateCode;
    }
}