package com.basic.framework.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @date 2017年3月21日 上午11:15:13
 * @author 杰
 * @Description: oracle sql处理
 * 
 */
public class OracleSQLUtils {

	/**
	 * 
	 * @date 2017年3月21日 上午11:02:09
	 * @author 杰
	 * @Description: 把所有的ids分批
	 * @param ids（传进去的所有ids）
	 * @param count（每次in括号里面的总条数，不能超过1000）
	 * @param field（传进去的字段，例如哪个字段需要使用in）
	 * @return
	 *
	 */
	public static String getOracleSQLIn(List<?> ids, int count, String field) {  
	    count = Math.min(count, 1000);  
	    int len = ids.size();  
	    int size = len % count;  
	    if (size == 0) {  
	        size = len / count;  
	    } else {  
	        size = (len / count) + 1;  
	    }  
	    StringBuilder builder = new StringBuilder();  
	    for (int i = 0; i < size; i++) {  
	        int fromIndex = i * count;  
	        int toIndex = Math.min(fromIndex + count, len);  
	        //System.out.println(ids.subList(fromIndex, toIndex));  
	        String productId = StringUtils.defaultIfEmpty(StringUtils.join(ids.subList(fromIndex, toIndex), "','"), "");  
	        if (i != 0) {  
	            builder.append(" OR ");  
	        }  
	        builder.append(field).append(" IN ('").append(productId).append("')");  
	    }  
	      
	    return StringUtils.defaultIfEmpty(builder.toString(), field + " IN ('')");  
	}  
	
	/**
	 * 
	 * @date 2017年5月9日 上午11:00:32
	 * 
	 * @Description: 传进来一个list，拆解成多个
	 * 比如：超过一千的id传进来，后面的个数是1000，就可以拆成多个1000大小的id list传回去
	 * @param targetList
	 * @param splitSize
	 * @return
	 *
	 */
	public static <E> List<List<E>> splitList (List<E> targetList, Integer splitSize){  
        if(targetList == null) {
            return null;
        }
        Integer size = targetList.size();  
        List<List<E>> resultList = new ArrayList<List<E>>();  
        if(size <= splitSize) {  
            resultList.add(targetList);  
        } else {  
            for (int i = 0; i < size; i += splitSize) {  
                //用于限制最后一部分size小于splitSize的list  
                Integer limit = i+splitSize;  
                if(limit > size){  
                    limit = size;  
                }  
                resultList.add(targetList.subList(i, limit));  
            }  
        }  
        return resultList;  
    }  
	
	/**
	 * @date 2017年3月21日 上午11:15:13
	 * @author 杰
	 * @Description: 测试
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		String ids = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32";
		String[] id = ids.split(",");
		List<String> list = new ArrayList<>();
		for(String item : id){
			list.add(item);
		}
		String str = getOracleSQLIn(list, 10, "字段");
		System.out.println(str);

	}

}
