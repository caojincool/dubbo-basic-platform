package com.basic.framework.common.utils.datatype;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 *
 * @date 2017年6月22日 上午11:32:33
 * 
 * @Description: 测试fastjson
 *
 */
public class JsonUtilsTest2 {

    public static void main(String args[]){
        
        JsonUtilsTest2 test = new JsonUtilsTest2();
        test.testObjectToJson();
    }

    private String testObjectToJson(){
    	TestBean bean1 = new TestBean();
    	bean1.setName("lzj");
    	bean1.setBirthDay(new Date());
    	bean1.setAge(1);
    	bean1.setaLong(10L);

//    	一、序列化
//
//    	fastjson序列化的时候，支持多种方式设置序列化日期：
//
//    	1.序列化日期为ISO-8601日期格式
//
//    	2.序列化日期为指定格式
//
//    	3.用默认的全局日期格式
//
//    	4.使用@JSONField配置
    	
        // 日期不做处理  
        System.out.println(JSON.toJSONString(bean1));  
        // 序列化日期为ISO-8601日期格式  
        System.out.println(JSON.toJSONString(bean1, SerializerFeature.UseISO8601DateFormat));  
        // 序列化日期为指定格式  
        System.out.println(JSON.toJSONStringWithDateFormat(bean1, "yyyy-MM-dd HH:mm:ss.SSS"));  
        // 修改全局的全局日期格式  
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";  
        // 使用默认的全局日期格式  
        System.out.println(JSON.toJSONString(bean1, SerializerFeature.WriteDateUseDateFormat));  
        

/*        二、反序列化
        反序列化能够自动识别如下日期格式：

        ISO-8601日期格式
        yyyy-MM-dd
        yyyy-MM-dd HH:mm:ss
        yyyy-MM-dd HH:mm:ss.SSS
	           毫秒数字
	           毫秒数字字符串
        .NET JSON日期格式
        new Date(1982932381111)*/
        
        String str = "{\"name\":\"lzj\",\"birthDay\":\"2017-06-22\",\"age\":1,\"aLong\":10,\"beanAList\":null,\"beanAMap\":null}";
//      Bean1 bean = JsonUtils.getInstance().jsonToObject(str, Bean1.class);
      
//        ParserConfig config = new ParserConfig();
//        config.putDeserializer(Date.class, MyDateFormatDeserializer.INSTANCE); // 我们自己实现的Deserializer
//        TestBean bean23 = JSON.parseObject(str, TestBean.class, config, JSON.DEFAULT_PARSER_FEATURE);
        
      TestBean bean = JSON.parseObject(str, TestBean.class); 
      System.out.println(JSON.toJSONString(bean));
      
        return null;
    }


}
