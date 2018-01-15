package org.kite.mybatis.builder.controller;

import org.kite.mybatis.builder.model.ProjectConfig;
import org.kite.mybatis.builder.model.Result;
import org.kite.mybatis.builder.model.TableInfo;
import org.kite.mybatis.builder.service.IProjectConfigService;
import org.kite.mybatis.builder.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * HomeController
 *
 * @author fengzheng
 * @date 2018/1/6
 */
@Controller
public class HomeController {

    @Autowired
    private IProjectConfigService projectConfigService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(ModelMap model) {
        List<ProjectConfig> allProjects = projectConfigService.getAll();

        return "index";
    }


    @RequestMapping(value = "insert",method = RequestMethod.GET)
    @ResponseBody
    public Object insert(){
        ProjectConfig projectConfig = new ProjectConfig();
        //projectConfig.setId(1);
        projectConfig.setTargetProject("h");
        projectConfig.setConnectionURL("dd");
        projectConfig.setDriverClass("jdcb");
        ProjectConfig afterProjectConfig = projectConfigService.insert(projectConfig);
        return afterProjectConfig;
    }

    @RequestMapping(value = "createConfig",method = RequestMethod.GET)
    @ResponseBody
    public Object createConfig(ProjectConfig projectConfig){
        ProjectConfig afterProjectConfig = projectConfigService.insert(projectConfig);
        return afterProjectConfig;
    }


    @RequestMapping(value = "updateProjectConfg",method = RequestMethod.GET)
    @ResponseBody
    public Object updateProjectConfg(ProjectConfig projectConfig){
        ProjectConfig afterProjectConfig = projectConfigService.update(projectConfig);
        return afterProjectConfig;
    }


    @RequestMapping(value = "getProjectConfig",method = RequestMethod.GET)
    @ResponseBody
    public Object getProjectConfig(Integer id){
        ProjectConfig projectConfig = projectConfigService.get(id);
        return projectConfig;
    }


    @RequestMapping(value = "getTables",method = RequestMethod.GET)
    @ResponseBody
    public Object getTables(Integer id){
        List<TableInfo> tables = projectConfigService.getTables(id);
        return tables;
    }

    @RequestMapping(value = "getAllConfig",method = RequestMethod.GET)
    @ResponseBody
    public Object getAllConfig(){
        List<ProjectConfig> projectConfigList = projectConfigService.getAll();
        return projectConfigList;
    }


    @RequestMapping(value = "build",method = {RequestMethod.POST})
    @ResponseBody
    public Result build(String tableList, String entityList, Integer id) {
        try{
            List<String> warings = projectConfigService.build(tableList,entityList,id);
            return new Result().setData(warings);
        }catch (ServiceException e){
            return new Result().setCode(10001).setMessage(e.getMessage());
        }
    }

}
