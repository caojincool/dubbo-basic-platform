package com.basic.framework.web.api;

import com.basic.framework.web.model.VerifyUniqueCode;

/**
 * 
 *
 * @date 2017年8月22日 下午5:42:19
 * 
 * @Description: 用于验证
 *
 */
public interface VerifyService {

	/**
	 * 
	 * @date 2017年8月22日 下午5:44:39
	 * 
	 * @Description: 验证某个表字段的值是否表内唯一
	 * @param ibean
	 * @return
	 *
	 */
	public int verifyUniqueCode(VerifyUniqueCode ibean);

}
