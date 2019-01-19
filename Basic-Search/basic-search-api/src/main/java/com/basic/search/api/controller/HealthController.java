package com.basic.search.api.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



//import redis.clients.jedis.JedisPool;
/**
 * 心跳检测服务：
 * 1、检测程序是否启动成功，启动成功，日志打印info级的提示信息"Server programmer b2c-search-api bootstrap is OK!"
 * 2、提供/health.do服务，可以给监控程序定期检测，包括tomcat服务状况、redis连通性检测；
 *
 */
@Order
@Controller
public class HealthController {
    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

   // @Resource(name = "jedisPool")
    //private JedisPool jedisPool;
    
   /* @RequestMapping(value = "/health.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result health() {
    	Result reslut = new Result();
    	reslut.setCode(ResultCode.SUCCESS);
    	JedisStat jedisStatVO = MonitorUtil.createJedisMonitorVo(jedisPool);
    	reslut.setObject(jedisStatVO);
    	reslut.setMessage("It's b2c-search-api running...");
        return reslut;
    }*/

    @PostConstruct
    public void postConstruct() {
        logger.info("Server programmer b2c-search-api bootstrap is OK!");
    }
    
    public static void main(String[] args) {
        String url = "http://127.0.0.1:8180";
        String server = "goods1";
        SolrClient solrClient = new HttpSolrClient.Builder().withBaseSolrUrl(url+"/"+server).build();
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", 10000);
        doc.addField("title", "沙发椅");
        doc.addField("price", 100000);
        try {
            solrClient.add(doc);
            solrClient.commit();
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        SolrQuery query = new SolrQuery();
        query.setQuery("title:沙发");
        QueryResponse response = null;
        try {
            response = solrClient.query(query);
            System.out.println(response.toString());
            System.out.println();
            SolrDocumentList docs = response.getResults();
            System.out.println("文档个数：" + docs.getNumFound());
            System.out.println("查询时间：" + response.getQTime());
            for (SolrDocument doc2 : docs) {
                System.out.println("id: " + doc2.getFieldValue("id") + "      title: " + doc2.getFieldValue("title"));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
//    @Autowired
//    private MessageProducer producer;
//    
//    @RequestMapping(value = "/testmq.do", produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public Result testmq(String topic, String tag, String content) {
//    	Message msg = new Message(topic, tag, "1", content.getBytes());
//    	Result reslut = new Result();
//		try {
//			SendResult sendResult = producer.send(msg);
//			reslut.setObject(sendResult);
//		} catch (MQClientException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RemotingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MQBrokerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	reslut.setCode(ResultCode.SUCCESS);
//    	reslut.setMessage("testmq finish");
//        return reslut;
//    }
}
