package org.kite.mybatis.builder.util;


/**
 * DataBaseOperatorFactory
 *
 * @author fengzheng
 * @date 2018/1/7
 */
public class DataBaseOperatorFactory {

    public static IDataBaseOperator getDataBaseOperator(String driverClass){
        if(driverClass.equals("com.mysql.jdbc.Driver")){
            return new MySqlOperator();
        }
        return null;
        //throw new Exception("实例化操作类失败，找不到对应的 driverClass");
    }
}
