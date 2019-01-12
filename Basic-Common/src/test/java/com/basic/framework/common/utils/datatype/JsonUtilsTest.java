package com.basic.framework.common.utils.datatype;

import java.util.*;

/**
 * Created by lzj on 2017/6/6.
 */
public class JsonUtilsTest {


    private String testObjectToJson(){
        Bean bean = new Bean();
        bean.setName("lzj");
        bean.setBirthDay(new Date());
        bean.setAge(1);
        bean.setaLong(10L);

        String json = JsonUtils.getInstance().objectToJson(bean);
        System.out.println(json);

        return json;
    }

    private Object testJsonToObject(String json){
        Bean bean = JsonUtils.getInstance().jsonToObject(json,Bean.class);
        System.out.println("name:"+bean.getName());
        System.out.println("birthDay:"+bean.getBirthDay());
        System.out.println("age:"+bean.getAge());
        System.out.println("aLong:"+bean.getaLong());
        return null;
    }

    private String testObjectToJsonWithListMap(){
        String json = null;
        Bean bean = new Bean();
        bean.setName("lzj");
        bean.setBirthDay(new Date());
        bean.setAge(1);
        bean.setaLong(10L);

        List<BeanA> beanAList = new ArrayList<BeanA>();
        BeanA beanA1 = new BeanA();
        beanA1.setName("lzj1");
        beanA1.setBirthDay(new Date());
        beanA1.setAge(11);
        beanA1.setaLong(101L);
        beanAList.add(beanA1);

        BeanA beanA2 = new BeanA();
        beanA2.setName("lzj2");
        beanA2.setBirthDay(new Date());
        beanA2.setAge(12);
        beanA2.setaLong(102L);
        beanAList.add(beanA2);
        bean.setBeanAList(beanAList);


        Map<String,BeanA> beanAMap = new HashMap<String,BeanA>();
        BeanA beanAMap1 = new BeanA();
        beanAMap1.setName("lzj11");
        beanAMap1.setBirthDay(new Date());
        beanAMap1.setAge(111);
        beanAMap1.setaLong(1011L);
        beanAMap.put("1",beanAMap1);

        BeanA beanAMap2 = new BeanA();
        beanAMap2.setName("lzj12");
        beanAMap2.setBirthDay(new Date());
        beanAMap2.setAge(112);
        beanAMap2.setaLong(1012L);
        beanAMap.put("2",beanAMap2);
        bean.setBeanAMap(beanAMap);

        json = JsonUtils.getInstance().objectToJson(bean);
        System.out.println(json);
        return json;
    }

    private Object testJsonToObjectWithListMap(String json){
        Bean bean = JsonUtils.getInstance().jsonToObject(json,Bean.class);
        System.out.println("name:"+bean.getName());
        System.out.println("birthDay:"+bean.getBirthDay());
        System.out.println("age:"+bean.getAge());
        System.out.println("aLong:"+bean.getaLong());
        List<BeanA> list = bean.getBeanAList();

        for(BeanA beanA:list){
            System.out.println("name:"+beanA.getName());
            System.out.println("birthDay:"+beanA.getBirthDay());
            System.out.println("age:"+beanA.getAge());
            System.out.println("aLong:"+beanA.getaLong());
        }


        return bean;
    }


    public static void main(String args[]){
        JsonUtilsTest test = new JsonUtilsTest();
        String json = test.testObjectToJson();
        test.testJsonToObject(json);

        String json1 = test.testObjectToJsonWithListMap();
        test.testJsonToObjectWithListMap(json1);
        
        String str = "{\"name\":\"lzj\",\"birthDay\":\"2017-06-22\",\"age\":1,\"aLong\":10,\"beanAList\":null,\"beanAMap\":null}";
        Bean bean = JsonUtils.getInstance().jsonToObject(str, Bean.class);
        System.out.println(JsonUtils.getInstance().objectToJson(bean));
        
    }

}

class Bean{
    String name;
    Date birthDay;
    Integer age;
    Long aLong;
    List<BeanA> beanAList;
    Map<String,BeanA> beanAMap;

    public List<BeanA> getBeanAList() {
        return beanAList;
    }

    public void setBeanAList(List<BeanA> beanAList) {
        this.beanAList = beanAList;
    }

    public Map<String, BeanA> getBeanAMap() {
        return beanAMap;
    }

    public void setBeanAMap(Map<String, BeanA> beanAMap) {
        this.beanAMap = beanAMap;
    }

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


class BeanA{
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
