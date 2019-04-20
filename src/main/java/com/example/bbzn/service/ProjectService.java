package com.example.bbzn.service;

import com.example.bbzn.pojo.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getProjectList(List<Integer> companyId);

    Project getProject(int project_id);

    int updateProject(String projectName,int projectId);

    int saveProject(Project project);

}
