package org.kite.mybatis.builder.model;

/**
 * TableInfo
 *
 * @author fengzheng
 * @date 2018/1/8
 */
public class TableInfo {

    private String tableName;

    private String entityName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
