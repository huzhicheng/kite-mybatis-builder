package org.kite.mybatis.builder.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * MySqlOperator
 *
 * @author fengzheng
 * @date 2018/1/7
 */
public class MySqlOperator implements IDataBaseOperator {

    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";


    @Override
    public Connection getConnection(String connectionUrl,String userId,String password) throws Exception{
        try {
            Class.forName(DRIVER_CLASS);
            Connection conn = DriverManager.getConnection(connectionUrl,userId,password);
            return conn;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<String> getAllTables(String connectionUrl,String userId,String password){
        try {
            List<String> tables = new ArrayList<>();
            Connection conn = getConnection(connectionUrl, userId, password);
            Statement statement = conn.createStatement();
            String databaseName = getDatabaseName(connectionUrl);
            String sql = String.format("select table_name from information_schema.tables where table_schema='%s' and table_type='base table';",databaseName);
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                tables.add(rs.getString(1));
            }
            rs.close();
            conn.close();
            return tables;
        }catch (Exception e){
            throw new ServiceException(e.getMessage(),e.getCause());
        }
    }

    private String getDatabaseName(String connectionUrl){
        String effective = connectionUrl.substring(13);
        String nameWithParam = effective.split("/")[1];
        String[] arry = nameWithParam.split("\\?");
        String name = arry[0];
        return name;
    }

    public static void main(String[] args){
        String connUrl = "jdbc:mysql://123.56.207.213:3306/relieved_app_test?characterEncoding=utf-8";
        String effective = connUrl.substring(13);
        String nameWithParam = effective.split("/")[1];
        String[] arry = nameWithParam.split("\\?");
        String name = arry[0];
        System.out.println(name);
    }

}
