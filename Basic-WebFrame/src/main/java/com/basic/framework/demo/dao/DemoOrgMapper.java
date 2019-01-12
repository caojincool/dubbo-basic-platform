package com.basic.framework.demo.dao;

import java.util.List;

import com.basic.framework.demo.model.DemoOrg;

/**
 * 
 * 
 * @date 2016年8月4日 下午5:41:01
 * @author lzj
 * @Description: 组件部门数据库操作定义
 *
 */
public interface DemoOrgMapper {

	/**
	 * 查询,为了拼成树型分阶查询
	 * @date 2016年8月4日 下午5:41:32
	 * @author lzj
	 * @Description: 分阶查询
	 * @param nodeId
	 * @return
	 *
	 */
	public List<DemoOrg> selectOrgsTreeStep(Long nodeId);
	
	/**
	 * 查询全部
	 * @date 2016年8月4日 下午5:42:21
	 * @author lzj
	 * @Description: 查询全部
	 * @return
	 *
	 */
	public List<DemoOrg> selectAllOrgs();
	
	
	/**
	 * 
	 * @date 2016年8月24日 上午9:10:25
	 * @author 杰
	 * @Description: 分步查询
	 * @return
	 *
	 */
	public List<DemoOrg> selectOaasOrgTreeStep(Long id);
	
	/**
	 * 
	 * @date 2016年8月24日 上午9:10:25
	 * @author 杰
	 * @Description: 查询全部
	 * @return
	 *
	 */
	public List<DemoOrg> selectOaasOrgTree();
}
