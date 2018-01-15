package org.kite.mybatis.builder.util;

import java.sql.Connection;
import java.util.List;

/**
 * IDataBaseOperator
 *
 * @author fengzheng
 * @date 2018/1/7
 */
public interface IDataBaseOperator {

    Connection getConnection(String connectionUrl,String userId,String password) throws Exception;

    List<String> getAllTables(String connectionUrl,String userId,String password);
}
