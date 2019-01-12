/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2016年12月29日 下午1:46:09
 * @author Administrator
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.framework.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @date 2016年12月29日 下午1:46:09
 * @author Fan
 * @Description: 比较
 * 
 */
public class CompareIdList {

	
	/*public static void main(String[] args)  {
	      List<Long> a = new ArrayList<Long>();
	      List<Long> b = new ArrayList<Long>();
	      List<Long> c = new ArrayList<Long>();
	      a.add(1L);
	      a.add(8L);
	      a.add(3L);
	      a.add(5L);
	      b.add(2L);
	      b.add(3L);
	      b.add(5L);
	      System.out.println(compareLists(b,a));
	      //System.out.println(compareIdList(2L,b));
	      //c.remove(b);
	      //a.removeAll(c);
	      //boolean c = a.removeAll(compareLists(a,b));
	      //System.out.println(a);
	   }*/
	
	/**
	 * 
	 * @date 2016年12月29日 下午2:19:44
	 * @author Fan
	 * @Description: 比较两个list里面的id是否相等并返回相等的id
	 * @param a
	 * @param b
	 * @return
	 *
	 */
	public static List<Long> compareLists(List<Long> a, List<Long> b){
		List<Long> list = new ArrayList<Long>();
		if(a != null && b != null ){
			for (Long aLong : a) {
				for (Long bLong : b) {
					if(aLong.equals(bLong)){
						//相等
						list.add(aLong);
						break;
					}
				}
			}
		}else{
			throw new NullPointerException("请传入参数");
		}
		return list;
	}
	
	/**
	 * 
	 * @date 2016年12月29日 下午2:20:04
	 * @author Fan
	 * @Description: 比较一个id是否存在一个list集合里
	 * @param a
	 * @param b
	 * @return
	 * @throws Exception 
	 *
	 */
	public static boolean compareIdList(Long a, List<Long> b) {
		if(a != null && b != null ){
			for (Long bLong : b) {
				if(a.equals(bLong))//相等
                {
                    return true;
                }
			}
			return false;
		}else{
			throw new NullPointerException("请传入参数");
		}
	}
}
