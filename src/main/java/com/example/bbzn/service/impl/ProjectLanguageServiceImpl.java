package com.example.bbzn.service.impl;

import com.example.bbzn.dao.ProjectLanguageDao;
import com.example.bbzn.pojo.ProjectLanguage;
import com.example.bbzn.service.ProjectLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectLanguageServiceImpl implements ProjectLanguageService {

    @Autowired
    private ProjectLanguageDao projectLanguageDao;

    public List<ProjectLanguage> getLanguageList(int project_id){
        return projectLanguageDao.getLanguageList(project_id);
    }

    public int saveProjectLanguage(ProjectLanguage projectLanguage){
        return projectLanguageDao.saveProjectLanguage(projectLanguage);
    }

    public int deleteProjectLanguage(ProjectLanguage projectLanguage){
        return projectLanguageDao.deleteProjectLanguage(projectLanguage);
    }

}
