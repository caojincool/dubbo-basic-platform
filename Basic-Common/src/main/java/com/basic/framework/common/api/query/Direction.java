package com.basic.framework.common.api.query;


import java.io.Serializable;

/**
 * 字段排序方向。
 * <pre> 
 * 构建组：x5-base-api
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2013-12-22-上午11:35:40
 * 版权：company
 * </pre>
 */
public enum Direction implements Serializable {
	/**
	 * 升序
	 */
    ASC, 
    /**
	 * 倒序
	 */
    DESC;
    
    
    public static Direction fromString(String value) {
        try {
            return Direction.valueOf(value.toUpperCase());
        } catch (Exception e) {
            return ASC;
        }
    }
}

