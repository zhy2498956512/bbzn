package com.example.bbzn.pojo;

import java.util.Date;

public class Project {

    /*
     * 项目表
     * */

    private int projectId;             //项目id
    private String projectName;        //项目名
    private Date projectNewdate;        //创建时间
    private Date projectUpdatedate;     //修改时间
    private int projectTypeId;        //项目类型id
    private int companyId;             //企业id

    //
    private String projectTypeName;     //项目类型名称

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(int projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Date getProjectNewdate() {
        return projectNewdate;
    }

    public void setProjectNewdate(Date projectNewdate) {
        this.projectNewdate = projectNewdate;
    }

    public Date getProjectUpdatedate() {
        return projectUpdatedate;
    }

    public void setProjectUpdatedate(Date projectUpdatedate) {
        this.projectUpdatedate = projectUpdatedate;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectTypeId=" + projectTypeId +
                ", companyId=" + companyId +
                '}';
    }
}
