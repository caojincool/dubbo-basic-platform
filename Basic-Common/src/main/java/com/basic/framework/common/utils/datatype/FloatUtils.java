package com.basic.framework.common.utils.datatype;

import java.math.BigDecimal;

/**
 * 
 *
 * @date 2017年12月22日 上午10:52:10
 * 
 * @Description: Float工具类
 *
 */
public class FloatUtils {
	
	private FloatUtils() {
    }

	/**
	 * 
	 * @date 2017年12月22日 上午10:52:49
	 * 
	 * @Description: Object转为Double
	 * @param value
	 * @return
	 *
	 */
    public static Float valueOf(Object value) {
    	Float res = null;
        if (value != null && value.toString().trim().length() > 0) {
            res = Float.valueOf(value.toString().trim());
        }
        return res;
    }
    
    /**
     * 
     * @date 2017年12月22日 上午10:53:57
     * 
     * @Description: 加
     * @param a
     * @param b
     * @return
     *
     */
	public static float add(float a, float b) {

		BigDecimal b1 = new BigDecimal(a + "");
		BigDecimal b2 = new BigDecimal(b + "");
		float f = b1.add(b2).floatValue();

		return f;
	}

	/**
	 * 
	 * @date 2017年12月22日 上午10:54:27
	 * 
	 * @Description: 减
	 * @param a
	 * @param b
	 * @return
	 *
	 */
	public static float sub(float a, float b) {

		BigDecimal b1 = new BigDecimal(a + "");
		BigDecimal b2 = new BigDecimal(b + "");
		float f = b1.subtract(b2).floatValue();

		return f;
	}

	/**
	 * 
	 * @date 2017年12月22日 上午10:54:50
	 * 
	 * @Description: 乘
	 * @param a
	 * @param b
	 * @return
	 *
	 */
	public static float mul(float a, float b) {

		BigDecimal b1 = new BigDecimal(a + "");
		BigDecimal b2 = new BigDecimal(b + "");
		float f = b1.multiply(b2).floatValue();

		return f;
	}

	/**
	 * 
	 * @date 2017年12月22日 上午10:55:14
	 * 
	 * @Description: 除
	 * 当不整除，出现无限循环小数时，向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向上舍入, 1.55保留一位小数结果为1.6
	 * @param a
	 * @param b
	 * @return
	 *
	 */
	public static float div(float a, float b) {

		return div(a, b, 2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 
	 * @date 2017年12月22日 上午10:55:41
	 * 
	 * @Description: 除
	 * @param a
	 * @param b
	 * @param scale
	 * @param roundingMode
	 * @return
	 *
	 */
	public static float div(float a, float b, int scale, int roundingMode) {

		/*
		 * 通过BigDecimal的divide方法进行除法时就会抛异常的，异常如下： java.lang.ArithmeticException:
		 * Non-terminating decimal expansion; no exact representable decimal
		 * result. at java.math.BigDecimal.divide(Unknown Source)
		 * 解决之道：就是给divide设置精确的小数点divide(xxxxx,2, BigDecimal.ROUND_HALF_EVEN)
		 * BigDecimal.ROUND_HALF_UP : 向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向上舍入,
		 * 1.55保留一位小数结果为1.6
		 */

		BigDecimal b1 = new BigDecimal(a + "");
		BigDecimal b2 = new BigDecimal(b + "");
		float f = b1.divide(b2, scale, roundingMode).floatValue();

		return f;
	}
	
	
	/**
	 * 
	 * @date 2017年12月22日 上午11:01:14
	 * 
	 * @Description: 测试方法
	 * @param args
	 *
	 */
	public static void main(String[] args) {
		   Float a = 2.0f;
		   Float b = 1.8f;
		   System.out.println("加----------" + FloatUtils.add(a, b));
		   System.out.println("减----------" + FloatUtils.sub(a, b));
		   System.out.println("乘----------" + FloatUtils.mul(a, b));
		   System.out.println("除----------" + FloatUtils.div(a, b));
		   System.out.println("----------" + (a - b));
		}
	
}
