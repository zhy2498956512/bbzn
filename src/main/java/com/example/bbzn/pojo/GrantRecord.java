package com.example.bbzn.pojo;

import java.util.Date;

public class GrantRecord {

    /*
     * 授权码记录表
     * */

    private int grantRecordId;                  //授权码记录id
    private int grantRecordAmount;              //授权数量
    private String grantRecordPrefix;         //授权码前缀
    private int grantRecordLength;            //授权码长度
    private String grantRecordBinary;         //授权码进制
    private Date grantRecordSubmittime;         //提交时间
    private int companyId;                      //企业id
    private int projectId;                      //项目id
    private String grantRecordFile;             //授权码文件

    public int getGrantRecordId() {
        return grantRecordId;
    }

    public void setGrantRecordId(int grantRecordId) {
        this.grantRecordId = grantRecordId;
    }

    public int getGrantRecordAmount() {
        return grantRecordAmount;
    }

    public void setGrantRecordAmount(int grantRecordAmount) {
        this.grantRecordAmount = grantRecordAmount;
    }

    public String getGrantRecordPrefix() {
        return grantRecordPrefix;
    }

    public void setGrantRecordPrefix(String grantRecordPrefix) {
        this.grantRecordPrefix = grantRecordPrefix;
    }

    public int getGrantRecordLength() {
        return grantRecordLength;
    }

    public void setGrantRecordLength(int grantRecordLength) {
        this.grantRecordLength = grantRecordLength;
    }

    public String getGrantRecordBinary() {
        return grantRecordBinary;
    }

    public void setGrantRecordBinary(String grantRecordBinary) {
        this.grantRecordBinary = grantRecordBinary;
    }

    public Date getGrantRecordSubmittime() {
        return grantRecordSubmittime;
    }

    public void setGrantRecordSubmittime(Date grantRecordSubmittime) {
        this.grantRecordSubmittime = grantRecordSubmittime;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getGrantRecordFile() {
        return grantRecordFile;
    }

    public void setGrantRecordFile(String grantRecordFile) {
        this.grantRecordFile = grantRecordFile;
    }

    @Override
    public String toString() {
        return "GrantRecord{" +
                "grantRecordId=" + grantRecordId +
                ", grantRecordAmount=" + grantRecordAmount +
                ", grantRecordPrefix='" + grantRecordPrefix + '\'' +
                ", grantRecordLength=" + grantRecordLength +
                ", grantRecordBinary='" + grantRecordBinary + '\'' +
                ", grantRecordSubmittime=" + grantRecordSubmittime +
                ", companyId=" + companyId +
                ", projectId=" + projectId +
                '}';
    }
}
