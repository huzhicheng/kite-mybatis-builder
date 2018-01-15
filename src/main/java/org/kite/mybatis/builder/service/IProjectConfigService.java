package org.kite.mybatis.builder.service;

import org.kite.mybatis.builder.model.ProjectConfig;
import org.kite.mybatis.builder.model.TableInfo;

import java.util.List;

/**
 * IProjectConfigService
 *
 * @author fengzheng
 * @date 2018/1/7
 */
public interface IProjectConfigService {

    List<ProjectConfig> getAll();

    ProjectConfig get(Integer id);

    ProjectConfig insert(ProjectConfig projectConfig);

    ProjectConfig update(ProjectConfig projectConfig);

    List<TableInfo> getTables(Integer id);

    List<String> build(String tableList,String entityList,Integer id);

}
