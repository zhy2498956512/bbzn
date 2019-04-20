package com.example.bbzn.pojo;

import java.util.Date;

public class Company {

    /*
     * 公司表
     * */

    private int companyId;          //id
    private String companyUserName;     //用户名
    private String companyUserPass;   //用户密码
    private int companyUserType;      //用户类型（1：管理员   0：代理商）
    private String companyName;          //公司名
    private String companyAddress;          //公司地址
    private String companyPhone;          //公司电话
    private String companyMail;          //公司邮箱
    private Date companyFoundtime;          //创建时间
    private int companyUserId;          //公司用户id
    private int companyState;          //代理商开启状态（0：未开启    1：开启）

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyUserName() {
        return companyUserName;
    }

    public void setCompanyUserName(String companyUserName) {
        this.companyUserName = companyUserName;
    }

    public String getCompanyUserPass() {
        return companyUserPass;
    }

    public void setCompanyUserPass(String companyUserPass) {
        this.companyUserPass = companyUserPass;
    }

    public int getCompanyUserType() {
        return companyUserType;
    }

    public void setCompanyUserType(int companyUserType) {
        this.companyUserType = companyUserType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyMail() {
        return companyMail;
    }

    public void setCompanyMail(String companyMail) {
        this.companyMail = companyMail;
    }

    public Date getCompanyFoundtime() {
        return companyFoundtime;
    }

    public void setCompanyFoundtime(Date companyFoundtime) {
        this.companyFoundtime = companyFoundtime;
    }

    public int getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(int companyUserId) {
        this.companyUserId = companyUserId;
    }

    public int getCompanyState() {
        return companyState;
    }

    public void setCompanyState(int companyState) {
        this.companyState = companyState;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyUserName='" + companyUserName + '\'' +
                ", companyUserPass='" + companyUserPass + '\'' +
                ", companyUserType=" + companyUserType +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", companyMail='" + companyMail + '\'' +
                ", companyFoundtime=" + companyFoundtime +
                ", companyUserId=" + companyUserId +
                '}';
    }
}
