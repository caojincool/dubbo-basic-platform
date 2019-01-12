/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年6月22日 上午10:50:56
 * 
 * @Description: 测试bean
 * 
 */
package com.basic.framework.common.utils.datatype;

import java.util.Date;

/**
 *
 * @date 2017年6月22日 上午10:50:56
 * 
 * @Description: 测试bean
 * 
 */
public class TestBean {
    String name;
    Date birthDay;
    Integer age;
    Long aLong;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getaLong() {
        return aLong;
    }

    public void setaLong(Long aLong) {
        this.aLong = aLong;
    }
}
