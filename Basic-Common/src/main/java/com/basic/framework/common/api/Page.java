package com.basic.framework.common.api;

import java.io.Serializable;

/**
 * 分页对象接口。
 * <pre> 
 * 构建组：x5-base-api
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-11-26-下午11:17:07
 * 版权：company
 * </pre>
 */
public interface Page  extends Serializable {
	 /**
     * 默认每页显示的记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 20;
    
    /**
     * 返回每页大小
     * @return 
     */
    public Integer getPageSize();
    /**
     * 返回总页数
     * @return 
     */
    public Integer getTotal();
    /**
     * 返回总页码
     * @return 
     */
    public Integer getPageNo();
    /**
     * 是否显示总记录数
     * @return 
     */
    public boolean isShowTotal();
    /**
     * 获取当前页的偏移量
     * @return 
     */
    public Integer getStartIndex();
}
