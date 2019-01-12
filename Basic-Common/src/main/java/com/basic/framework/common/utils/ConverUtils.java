package com.basic.framework.common.utils;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @date 2017年5月16日 下午3:39:56
 * 
 * @Description: 转化
 *
 */
public class ConverUtils {

//	private static Logger logger = LoggerFactory.getLogger(ConverUtils.class);
	
	/**
	 * 
	 * @date 2017年5月16日 下午3:40:38
	 * 
	 * @Description: 把数组转换为一个用逗号分隔的字符串
	 * @param arrays
	 * @return
	 *
	 */
    public static String arraysToString(String[] arrays) {  
        String str = "";  
        if (arrays != null && arrays.length > 0) {  
            for (int i = 0; i < arrays.length; i++) {  
                str += arrays[i] + ",";  
            }  
        }  
        str = str.substring(0, str.length() - 1);  
        return str;  
    }
    
    /**
     * 
     * @date 2017年5月16日 下午3:42:00
     * 
     * @Description: 把list转换为一个用逗号分隔的字符串
     * @param list
     * @return
     *
     */
    public static String listToString(@SuppressWarnings("rawtypes") List list) {  
        StringBuilder sb = new StringBuilder();  
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {  
                if (i < list.size() - 1) {  
                    sb.append(list.get(i) + ",");  
                } else {  
                    sb.append(list.get(i));  
                }  
            }  
        }  
        return sb.toString();  
    } 
    
}
