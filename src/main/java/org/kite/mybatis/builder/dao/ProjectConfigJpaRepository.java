package org.kite.mybatis.builder.dao;

import org.kite.mybatis.builder.model.ProjectConfig;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * ProjectConfigJpaRepository
 *
 * @author fengzheng
 * @date 2018/1/7
 */
public interface ProjectConfigJpaRepository extends Repository<ProjectConfig, Long> {

    List<ProjectConfig> findAll();

    ProjectConfig findById(Integer id);

    ProjectConfig save(ProjectConfig projectConfig);

}
