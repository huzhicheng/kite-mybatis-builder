package org.kite.mybatis.builder.model;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;

/**
 * ProjectConfig
 *
 * @author fengzheng
 * @date 2018/1/7
 */
@Entity
public class ProjectConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PROJECTNAME")
    private String projectName;

    @Column(name = "TARGETRUNTIME")
    private String targetRuntime;

    @Column(name = "SUPPRESSALLCOMMENTS")
    private Boolean suppressAllComments;

    @Column(name = "DRIVERCLASS")
    private String driverClass;

    @Column(name = "CONNECTIONURL")
    private String connectionURL;

    @Column(name = "USERID")
    private String userId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TARGETPROJECT")
    private String targetProject;

    @Column(name = "TARGETMODELPACKAGE")
    private String targetModelPackage;

    @Column(name = "TARGETMAPPERPACKAGE")
    private String targetMapperPackage;

    @Column(name = "TARGETMAPPERINTERFACEPACKAGE")
    private String targetMapperInterfacePackage;

    @Column(name = "ISOVERWRITE")
    private Boolean isOverwrite;

    @Column(name = "REMOVEPREFIX")
    private String removePrefix;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTargetRuntime() {
        return targetRuntime;
    }

    public void setTargetRuntime(String targetRuntime) {
        this.targetRuntime = targetRuntime;
    }

    public Boolean getSuppressAllComments() {
        return suppressAllComments;
    }

    public void setSuppressAllComments(Boolean suppressAllComments) {
        this.suppressAllComments = suppressAllComments;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public String getTargetModelPackage() {
        return targetModelPackage;
    }

    public void setTargetModelPackage(String targetModelPackage) {
        this.targetModelPackage = targetModelPackage;
    }

    public String getTargetMapperPackage() {
        return targetMapperPackage;
    }

    public void setTargetMapperPackage(String targetMapperPackage) {
        this.targetMapperPackage = targetMapperPackage;
    }

    public String getTargetMapperInterfacePackage() {
        return targetMapperInterfacePackage;
    }

    public void setTargetMapperInterfacePackage(String targetMapperInterfacePackage) {
        this.targetMapperInterfacePackage = targetMapperInterfacePackage;
    }

    public Boolean getOverwrite() {
        return isOverwrite;
    }

    public void setOverwrite(Boolean overwrite) {
        isOverwrite = overwrite;
    }

    public String getRemovePrefix() {
        return removePrefix;
    }

    public void setRemovePrefix(String removePrefix) {
        this.removePrefix = removePrefix;
    }
}
