/*
 * Copyright (c) 2012-2013, Poplar Yfyang 杨友峰 (poplar1123@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.basic.framework.common.mybatis.support;


import com.basic.framework.common.mybatis.Dialect;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 统计总数，分页插件中使用。
 * @author ray
 */
public class SQLHelp {
    private static Logger logger = LoggerFactory.getLogger(SQLHelp.class);

    /**
     * 查询总纪录数
     *
     * @param sql             SQL语句
     * @param mappedStatement mapped
     * @param parameterObject 参数
     * @param boundSql        boundSql
     * @param dialect         database dialect
     * @return 总记录数
     * @throws SQLException sql查询错误
     */
    public static int getCount(final String sql,
                               final MappedStatement mappedStatement, final Object parameterObject,
                               final BoundSql boundSql, Dialect dialect) throws SQLException {
    	String countSql = "";
    	if(mappedStatement.getConfiguration().hasStatement(mappedStatement.getId()+"Count")){
    		MappedStatement countMs = mappedStatement.getConfiguration().getMappedStatement(mappedStatement.getId()+"Count");
    		final BoundSql boundSql2 =  countMs.getBoundSql(parameterObject);
    		countSql = boundSql2.getSql();
    		countSql = dialect.getCountString(countSql);
    	}else{
    		countSql = dialect.getCountString(sql);
    	}
    	
        final String countSql1 =countSql;
        
        logger.debug("Total count SQL [{}] ", countSql1);
        logger.debug("Total count Parameters: {} ", parameterObject);
       
        DataSource dataSource=mappedStatement.getConfiguration().getEnvironment().getDataSource();
        Connection connection=  DataSourceUtils.getConnection(dataSource);
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            countStmt = connection.prepareStatement(countSql1);
            //Page SQL和Count SQL的参数是一样的，在绑定参数时可以使用一样的boundSql
            DefaultParameterHandler handler = new DefaultParameterHandler(mappedStatement,parameterObject,boundSql);
            handler.setParameters(countStmt);

            rs = countStmt.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            logger.debug("Total count: {}", count);
            return count;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } finally {
                try {
                    if (countStmt != null) {
                        countStmt.close();
                    }
                } finally {
                	DataSourceUtils.releaseConnection(connection,dataSource);
                }
            }
        }
    }

}