package com.example.bbzn.service;

import com.example.bbzn.pojo.ProjectLanguage;

import java.util.List;

public interface ProjectLanguageService {

    List<ProjectLanguage> getLanguageList(int project_id);

    int saveProjectLanguage(ProjectLanguage projectLanguage);

    int deleteProjectLanguage(ProjectLanguage projectLanguage);
}
