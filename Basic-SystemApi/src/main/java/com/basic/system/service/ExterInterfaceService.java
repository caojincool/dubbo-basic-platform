package com.basic.system.service;

import com.basic.system.model.BaseSysInterface;
import com.alibaba.fastjson.JSONObject;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.db.serverinterface.BaseServer;
import com.basic.framework.common.model.DefaultQueryFilter;
import java.util.List;
/**
 * 
 * <pre> 
 * 描述：外围系统接口 处理接口
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 14:12:03
 * 版权：companyName
 * </pre>
 */
public interface ExterInterfaceService{

	/**
	 * 调用外部系统接口
	 * @param type 接口类型
	 * @param jsonObject 入参
	 * @param systemCode 系统编码
	 * @return
	 */
	List<JSONObject> exterInterface(String type, JSONObject jsonObject, String systemCode);
}
