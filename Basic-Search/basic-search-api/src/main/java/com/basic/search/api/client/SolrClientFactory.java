/**
 * Copyright: Copyright 2007-2016 LSMY All Rights Reserved.
 */

package com.basic.search.api.client;

import java.io.IOException;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: TODO
 * @author TanJY
 * @date 2016年7月15日 上午11:55:53
 */
public class SolrClientFactory {

    private String zookeeperHost;

    private CloudSolrClient cloudSolrClient;

    private static Logger log = LoggerFactory.getLogger(SolrClientFactory.class);

    public String getZookeeperHost() {
        return zookeeperHost;
    }

    public void setZookeeperHost(String zookeeperHost) {
        this.zookeeperHost = zookeeperHost;
    }
    
    public CloudSolrClient getCloudSolrClient() {
        return cloudSolrClient;
    }

    public void init() throws Exception {
        log.info("SolrServerFactory init ...");
        long startTime = System.currentTimeMillis();
//        SolrZkClient solrZkClient = new SolrZkClient("", 1000);
        cloudSolrClient = new CloudSolrClient.Builder().withZkHost(zookeeperHost).build();
        log.info("SolrServerFactory init finish, use time {}ms", (System.currentTimeMillis() - startTime));
    }

    public void destory() {
        if (cloudSolrClient != null) {
            try {
                log.info("cloudSolrClient close ...");
                cloudSolrClient.close();
            } catch (IOException e) {
                log.error("cloudSolrClient close error ", e);
            }
        }
    }
}
