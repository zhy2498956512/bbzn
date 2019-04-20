package com.example.bbzn.service.impl;

import com.example.bbzn.dao.ProjectTypeDao;
import com.example.bbzn.pojo.ProjectType;
import com.example.bbzn.service.ProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {

    @Autowired
    private ProjectTypeDao projectTypeDao;

    public List<ProjectType> getProjectTypeList(){
        return projectTypeDao.getProjectTypeList();
    }

}
