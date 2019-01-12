package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.ButtonIbean;
import com.basic.order.model.Button;

public interface ButtonMapper {
    int deleteByPrimaryKey(Long buttonId);

    int insertSelective(Button record);

    Button selectByPrimaryKey(Long buttonId);

    int updateByPrimaryKeySelective(Button record);
	
    /**
     * 
     * @date 2017年9月8日 下午2:24:32
     * 
     * @Description: 根据参数查询单据按钮
     * @param ibean
     * @return
     *
     */
    public List<Button> selectOrderButtonList(ButtonIbean ibean);
    
    /**
     * 
     * @date 2017年9月8日 下午2:24:32
     * 
     * @Description: 根据参数查询任务按钮
     * @param ibean
     * @return
     *
     */
    public List<Button> selectWoButtonList(ButtonIbean ibean);
    
}