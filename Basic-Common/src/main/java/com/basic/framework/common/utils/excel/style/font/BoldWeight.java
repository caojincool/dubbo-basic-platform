package com.basic.framework.common.utils.excel.style.font;

import org.apache.poi.hssf.usermodel.HSSFFont;

/**
 * 字体样式属性，字体加粗
 * @author zxh
 */
public enum BoldWeight {
	/**
	 * 正常，不加粗
	 */
	NORMAL((short)400), 
	
	/**
	 * 加粗，weight等于700
	 */
	BOLD((short)400);

	private short weight;

	private BoldWeight(short weight) {
		this.weight = weight;
	}
	
	/**
	 * 返回加粗的等级
	 * @return
	 */
	public short getWeight() {
		return weight;
	}

	/**
	 * 根据值返回对应的枚举值
	 * @param weight
	 * @return
	 */
	public static BoldWeight instance(short weight){
		for(BoldWeight e : BoldWeight.values()){
			if(e.getWeight() == weight){
				return e;
			}
		}
		return BoldWeight.NORMAL;
	}
}
