package com.example.bbzn.pojo;

public class ProjectLanguage {

    private int projectLanguageId;          //项目语言id
    private int projectId;                  //项目id
    private int languageId;                 //语言id

    //
    private String languageName;                //语言名称

    public int getProjectLanguageId() {
        return projectLanguageId;
    }

    public void setProjectLanguageId(int projectLanguageId) {
        this.projectLanguageId = projectLanguageId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public String toString() {
        return "ProjectLanguage{" +
                "projectLanguageId=" + projectLanguageId +
                ", projectId=" + projectId +
                ", languageId=" + languageId +
                '}';
    }
}
