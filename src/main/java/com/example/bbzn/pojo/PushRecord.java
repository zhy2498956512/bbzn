package com.example.bbzn.pojo;

import java.util.Date;

public class PushRecord {

    /*
    * 推送记录表
    * */

    private int pushId;
    private int apkId;
    private int projectId;
    private int equipmentId;
    private Date pushTime;

    //
    private String grantCode;
    private String apkEdition;
    private String apkRoute;
    private String timeString;

    public int getPushId() {
        return pushId;
    }

    public void setPushId(int pushId) {
        this.pushId = pushId;
    }

    public int getApkId() {
        return apkId;
    }

    public void setApkId(int apkId) {
        this.apkId = apkId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getGrantCode() {
        return grantCode;
    }

    public void setGrantCode(String grantCode) {
        this.grantCode = grantCode;
    }

    public String getApkEdition() {
        return apkEdition;
    }

    public void setApkEdition(String apkEdition) {
        this.apkEdition = apkEdition;
    }

    public String getApkRoute() {
        return apkRoute;
    }

    public void setApkRoute(String apkRoute) {
        this.apkRoute = apkRoute;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    @Override
    public String toString() {
        return "PushRecord{" +
                "pushId=" + pushId +
                ", apkId=" + apkId +
                ", projectId=" + projectId +
                ", equipmentId=" + equipmentId +
                ", pushTime=" + pushTime +
                ", grantCode='" + grantCode + '\'' +
                ", apkEdition='" + apkEdition + '\'' +
                ", apkRoute='" + apkRoute + '\'' +
                '}';
    }
}
