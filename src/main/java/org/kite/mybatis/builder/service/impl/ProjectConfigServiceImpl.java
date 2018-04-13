package org.kite.mybatis.builder.service.impl;

import org.kite.mybatis.builder.dao.ProjectConfigJpaRepository;
import org.kite.mybatis.builder.model.ProjectConfig;
import org.kite.mybatis.builder.model.TableInfo;
import org.kite.mybatis.builder.service.IProjectConfigService;
import org.kite.mybatis.builder.util.DataBaseOperatorFactory;
import org.kite.mybatis.builder.util.IDataBaseOperator;
import org.kite.mybatis.builder.util.ServiceException;
import org.kite.mybatis.extend.MysqlJavaType2ResolverImpl;
import org.kite.mybatis.extend.MysqlJavaTypeResolverImpl;
import org.kite.mybatis.extend.Plugins.MySqlLimitPlugin;
import org.kite.mybatis.extend.Plugins.SerializablePlugin;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ProjectServiceImpl
 *
 * @author fengzheng
 * @date 2018/1/7
 */
@Service
@Transactional
public class ProjectConfigServiceImpl implements IProjectConfigService{

    @Autowired
    private ProjectConfigJpaRepository projectConfigJpaRepository;

    @Override
    public List<ProjectConfig> getAll(){
        return projectConfigJpaRepository.findAll();
    }

    @Override
    public ProjectConfig get(Integer id){
        return projectConfigJpaRepository.findById(id);
    }

    @Override
    public ProjectConfig insert(ProjectConfig projectConfig){
        ProjectConfig afterProjectConfig = projectConfigJpaRepository.save(projectConfig);
        return afterProjectConfig;
    }

    @Override
    public ProjectConfig update(ProjectConfig projectConfig){
        ProjectConfig afterProjectConfig =  projectConfigJpaRepository.save(projectConfig);
        return afterProjectConfig;
    }

    @Override
    public List<TableInfo> getTables(Integer id,String removePrefix){
        List<TableInfo> tableInfos = new ArrayList<>();
        ProjectConfig projectConfig = get(id);
        String driverClass = projectConfig.getDriverClass();
        IDataBaseOperator dataBaseOperator = DataBaseOperatorFactory.getDataBaseOperator(driverClass);
        List<String> tables = dataBaseOperator.getAllTables(projectConfig.getConnectionURL(),projectConfig.getUserId(),projectConfig.getPassword());
        for(String table: tables){
            TableInfo tableInfo = new TableInfo();
            tableInfo.setTableName(table);
            tableInfo.setEntityName(toUpperCase(table,removePrefix));
            tableInfos.add(tableInfo);
        }
        return tableInfos;
    }

    @Override
    public List<String> build(String tableList,String entityList,Integer id){
        ProjectConfig projectConfig = get(id);
        List<String> warnings = new ArrayList<String>();
        Configuration config = new Configuration();

        // create a Context object
        Context context = new Context(ModelType.FLAT);
        context.setId("mysql_table");
        context.setTargetRuntime(projectConfig.getTargetRuntime());

        /**
         * 分页 limit 方式
         */
        PluginConfiguration limitPlugin = new PluginConfiguration();
        limitPlugin.setConfigurationType(MySqlLimitPlugin.class.getName());
        context.addPluginConfiguration(limitPlugin);

        /**
         * 序列化
         */
        PluginConfiguration serializablePlugin = new PluginConfiguration();
        serializablePlugin.setConfigurationType(SerializablePlugin.class.getName());
        context.addPluginConfiguration(serializablePlugin);

        /**
         * 代码注释控制
         */
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        commentGeneratorConfiguration.addProperty("suppressDate","true");
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

        /**
         *  数据库连接配置
         */
        String connectionUrl = projectConfig.getConnectionURL();
        String driverClass = projectConfig.getDriverClass();
        String username = projectConfig.getUserId();
        String password = projectConfig.getPassword();
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass(driverClass);
        jdbcConnectionConfiguration.setConnectionURL(connectionUrl);
        jdbcConnectionConfiguration.setUserId(username);
        jdbcConnectionConfiguration.setPassword(password);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        /**
         * 设置 JavaTypeResolver
         */
        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();

        String javaTypeResolverType = "";
        if(projectConfig.getTimestamp2Date()) {
            MysqlJavaType2ResolverImpl javaType2Resolver = new MysqlJavaType2ResolverImpl();
            javaTypeResolverType = javaType2Resolver.getClass().getName();
        }else{
            MysqlJavaTypeResolverImpl javaTypeResolverDefault = new MysqlJavaTypeResolverImpl();
            javaTypeResolverType = javaTypeResolverDefault.getClass().getName();
        }

        System.out.println(javaTypeResolverType);
        javaTypeResolverConfiguration.setConfigurationType(javaTypeResolverType);
        javaTypeResolverConfiguration.addProperty("forceBigDecimals","false");
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);

        /**
         * model 映射
         */
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        String targetModelPackage = projectConfig.getTargetModelPackage();
        javaModelGeneratorConfiguration.setTargetPackage(targetModelPackage);
        javaModelGeneratorConfiguration.setTargetProject(projectConfig.getTargetProject());
        javaModelGeneratorConfiguration.addProperty("enableSubPackages","true");
        javaModelGeneratorConfiguration.addProperty("trimStrings","true");
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        /**
         * mapper.xml 映射
         */
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        String targetMapperPackage = projectConfig.getTargetMapperPackage();
        sqlMapGeneratorConfiguration.setTargetPackage(targetMapperPackage);
        sqlMapGeneratorConfiguration.setTargetProject(projectConfig.getTargetProject());
        sqlMapGeneratorConfiguration.addProperty("enableSubPackages","true");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        /**
         * mapper 接口文件映射
         */
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        String targetMapperInterfacePackage = projectConfig.getTargetMapperInterfacePackage();
        javaClientGeneratorConfiguration.setTargetPackage(targetMapperInterfacePackage);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        javaClientGeneratorConfiguration.setTargetProject(projectConfig.getTargetProject());
        javaClientGeneratorConfiguration.addProperty("enableSubPackages","true");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);


        importTableList(tableList,entityList,context);


        config.addContext(context);

        DefaultShellCallback callback = new DefaultShellCallback(projectConfig.getOverwrite());
        try {
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage(),e.getCause());
        }
        return warnings;
    }

    private void importTableList(String tableList,String entityList,Context context){
        String[] tableArray = tableList.split(",");
        String[] entityArray = entityList.split(",");
        for(int i = 0;i<tableArray.length;i++){
            TableConfiguration t = new TableConfiguration(context);
            t.setSchema("root");
            t.setTableName(tableArray[i]);
            t.setDomainObjectName(entityArray[i]);
            t.addProperty("useActualColumnNames","false");
            t.setGeneratedKey(new GeneratedKey("id","MySql",true,"post"));
            context.addTableConfiguration(t);
        }
    }

    private static  Pattern linePattern = Pattern.compile("_(\\w)");

    private String toUpperCase(String tableName,String removePrefix){
        if(tableName.startsWith(removePrefix)){
            tableName = tableName.replace(removePrefix,"");
        }
        tableName = tableName.toLowerCase();
        Matcher matcher = linePattern.matcher(tableName);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        String s = sb.toString();
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public static void main(String[] args){
        String tableName = "advisory_picture_text";
        System.out.println(MySqlLimitPlugin.class.getName());
        //System.out.println( toUpperCase(tableName));
    }
}
