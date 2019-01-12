package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.TacheIbean;
import com.basic.order.model.Tache;

public interface TacheMapper {
    int deleteByPrimaryKey(Long tacheId);

    int insertSelective(Tache record);

    Tache selectByPrimaryKey(Long tacheId);

    int updateByPrimaryKeySelective(Tache record);
    
    /**
     * 
     * @date 2017年8月21日 下午5:18:28
     * 
     * @Description: 根据环节编码查询有效的一条数据
     * @param tacheCode
     * @return
     *
     */
    public Tache selectByTacheCode(String tacheCode);
    
    /**
     * 
     * @date 2017年9月4日 下午3:59:35
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<Tache> selectTacheList(TacheIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:06
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectTacheListCount(TacheIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:20
     * 
     * @Description: 批量修改状态
     * @param ibean
     * @return
     *
     */
    public int updateBatchStateByTacheIds(Tache ibean);
}