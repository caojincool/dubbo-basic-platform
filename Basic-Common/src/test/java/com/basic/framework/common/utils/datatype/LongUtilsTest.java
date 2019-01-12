package com.basic.framework.common.utils.datatype;

/**
 *
 * @date 2017年7月7日 下午4:22:48
 * 
 * @Description: test
 * 
 */
public class LongUtilsTest {
	
	public static void main(String[] args){
		double value = 2;
		long v = (long) value;
		int v2 = (int) value;
		LongUtils.valueOf(value);
		LongUtils.valueOf(v);
		IntegerUtils.valueOf(value);
		IntegerUtils.valueOf(v2);
		
		DoubleUtils.valueOf(v);
		DoubleUtils.valueOf(v2);
	}
	
}
