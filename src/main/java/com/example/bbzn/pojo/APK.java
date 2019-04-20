package com.example.bbzn.pojo;

import java.util.Date;

public class APK {

    private int apkId;                     //apkID
    private int apkEdition;             //apk版本号
    private String apkRoute;               //apk路径
    private Date uploadDate;               //上传时间
    private int projectId;                //项目ID

    //
    private String timeString;
    public int getApkId() {
        return apkId;
    }

    public void setApkId(int apkId) {
        this.apkId = apkId;
    }

    public int getApkEdition() {
        return apkEdition;
    }

    public void setApkEdition(int apkEdition) {
        this.apkEdition = apkEdition;
    }

    public String getApkRoute() {
        return apkRoute;
    }

    public void setApkRoute(String apkRoute) {
        this.apkRoute = apkRoute;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    @Override
    public String toString() {
        return "APK{" +
                "apkId=" + apkId +
                ", apkEdition='" + apkEdition + '\'' +
                ", apkRoute='" + apkRoute + '\'' +
                '}';
    }
}
