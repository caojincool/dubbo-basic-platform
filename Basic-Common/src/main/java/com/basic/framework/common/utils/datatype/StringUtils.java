/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月1日 下午4:58:14
 * @author lzj
 * @Description: String 工具集
 * 
 */
package com.basic.framework.common.utils.datatype;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @date 2015年12月1日 下午4:58:14
 * @author lzj
 * @Description: String 工具集
 * 
 */
public class StringUtils {

	private static Pattern P = Pattern.compile("\\s+|\t|\r|\n");
	/**
	 * 空格替换
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		Matcher m = P.matcher(str);
		return m.replaceAll(" ");
	}

	/**
	 * 
	 * @date 2015年12月26日 上午10:28:55
	 * @author zhou.wenwei
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @param cs
	 * @return
	 *
	 */
//    public static boolean isNotBlank(CharSequence cs) {
//        int strLen;
//        if (cs == null || (strLen = cs.length()) == 0) {
//            return false;
//        }
//        for (int i = 0; i < strLen; i++) {
//            if (Character.isWhitespace(cs.charAt(i)) == false) {
//                return true;
//            }
//        }
//        return false;
//    }
	

	/**
	 * 判断是否为空，不包括空格
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(null == str || "".equals(str)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @date 2017年7月7日 下午6:27:14
	 * @author Kevin
	 * @Description: 把对象转换字符串
	 * @param object
	 * @return
	 *
	 */
	public static String valueOf(Object object) {
        
        return valueOf(object, "");
    }
    
	/**
	 * 
	 * @date 2017年7月7日 下午6:28:05
	 * @author Kevin
	 * @Description: 把对象转换字符串，如果是object == null 则返回默认字符串
	 * @param object
	 * @param defaultEmptyValue
	 * @return
	 *
	 */
    public static String valueOf(Object object, String defaultEmptyValue) {
        
        if(object == null) {
            return defaultEmptyValue;
        }
        
        return String.valueOf(object);
    }
    
    /**
     * 
     * @date 2017年7月7日 下午6:29:11
     * @author Kevin
     * @Description: 判断对象是否为空
     * @param object
     * @return
     *
     */
    public static boolean isEmptyOrNull(Object object) {
        
        if("".equals(StringUtils.valueOf(object))) {
            return true;
        }else if ("null".equals(StringUtils.valueOf(object))) {
        	return true;
		}else if ("[]".equals(StringUtils.valueOf(object))) {
        	return true;
		}else if ("undefined".equals(StringUtils.valueOf(object))) {
        	return true;
		}else if ("{}".equals(StringUtils.valueOf(object))) {
        	return true;
		}
        
        return false;
    }
    
    /**
     * 
     * @date 2017年12月5日 下午4:56:01
     * 
     * @Description: 把原始字符串分割成指定长度的字符串列表
     * @param inputString 原始字符串
     * @param length 长度
     * @return
     *
     */
    public static List<String> splitStrToListForLength(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }
    
    /**
     * 
     * @date 2017年12月5日 下午5:01:41
     * 
     * @Description: 分割字符串，如果开始位置大于字符串长度，返回空
     * @param str 原始字符串
     * @param f 开始位置
     * @param t 结束位置
     * @return
     *
     */
    private static String substring(String str, int f, int t) {
        if (f > str.length()) {
            return null;
        }
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }
    
    /**
     * 
     * @date 2017年12月5日 下午5:03:53
     * 
     * @Description: 测试
     * @param args
     *
     */
	public static void main(String[] args) {
		String str = "请问饿二二二二二二二二二二二二二奥所多多多多多去稳去稳23 啊实打实的去稳请3123qdadarqwdfasdaweqadsdasdaxcqwe12sdaaaaaaaaaaaaqweqwe123大搜索所所所所所所所所所所所所所所所所所所所所所";
		List<String> list = splitStrToListForLength(str, 200);
		System.out.println(JsonUtils.getInstance().objectToJson(list));
	}
	
}
