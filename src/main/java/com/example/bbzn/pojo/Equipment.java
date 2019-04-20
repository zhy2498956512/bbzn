package com.example.bbzn.pojo;

import java.util.Date;

public class Equipment {

    private int equipmentId;                       //设备id
    private String appId;                          //appID
    private int apkId;                             //apkID
    private String systemEdition;                  //系统版本号
    private String systemType;                     //系统类型（安卓，ios）
    private Date equipmentFoundtime;               //创建时间
    private Date equipmentLogintime;               //登录时间
    private int equipmentValidity;                 //有效期（已天为单位）
    private int projectId;                         //项目id
    private int companyId;                         //代理商id
    private int grantId;                           //授权码id
    private int equipmentType;                     //设备状态（1：已激活设备     2：入库但未激活      3：未入库被拦截）


    //
    private String foundtime;                      //创建时间String
    private String logintime;                      //登录时间String
    private String companyName;                    //代理商名
    private String projectName;                    //项目名
    private int apkEdition;                     //APK版本名
    private String grantCode;                           //授权码


    public int getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getGrantCode() {
        return grantCode;
    }

    public void setGrantCode(String grantCode) {
        this.grantCode = grantCode;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getApkId() {
        return apkId;
    }

    public void setApkId(int apkId) {
        this.apkId = apkId;
    }

    public String getSystemEdition() {
        return systemEdition;
    }

    public void setSystemEdition(String systemEdition) {
        this.systemEdition = systemEdition;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public Date getEquipmentFoundtime() {
        return equipmentFoundtime;
    }

    public void setEquipmentFoundtime(Date equipmentFoundtime) {
        this.equipmentFoundtime = equipmentFoundtime;
    }

    public Date getEquipmentLogintime() {
        return equipmentLogintime;
    }

    public void setEquipmentLogintime(Date equipmentLogintime) {
        this.equipmentLogintime = equipmentLogintime;
    }

    public int getEquipmentValidity() {
        return equipmentValidity;
    }

    public void setEquipmentValidity(int equipmentValidity) {
        this.equipmentValidity = equipmentValidity;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getGrantId() {
        return grantId;
    }

    public void setGrantId(int grantId) {
        this.grantId = grantId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getApkEdition() {
        return apkEdition;
    }

    public void setApkEdition(int apkEdition) {
        this.apkEdition = apkEdition;
    }

    public String getFoundtime() {
        return foundtime;
    }

    public void setFoundtime(String foundtime) {
        this.foundtime = foundtime;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", appId='" + appId + '\'' +
                ", apkId='" + apkId + '\'' +
                ", systemEdition='" + systemEdition + '\'' +
                ", systemType='" + systemType + '\'' +
                ", equipmentFoundtime=" + equipmentFoundtime +
                ", equipmentLogintime=" + equipmentLogintime +
                ", equipmentValidity=" + equipmentValidity +
                ", projectId=" + projectId +
                ", companyId=" + companyId +
                '}';
    }
}
