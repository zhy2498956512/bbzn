package com.example.bbzn.service.impl;

import com.example.bbzn.dao.ProjectDao;
import com.example.bbzn.pojo.Project;
import com.example.bbzn.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public List<Project> getProjectList(List<Integer> companyId){
        return projectDao.getProjectList(companyId);
    }

    public Project getProject(int project_id){
        return projectDao.getProject(project_id);
    }

    public int updateProject(String projectName,int projectId){
        return projectDao.updateProject(projectName,projectId);
    }

    public int saveProject(Project project){
        return projectDao.saveProject(project);
    }
}
