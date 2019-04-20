package com.example.bbzn.pojo;

import java.util.Date;

public class GrantNumberRecord {

    /*
     * 授权码数量记录表
     * */

    private int grantNumberRecordId;                  //授权数量记录id
    private int grantNumberRecordAmount;              //授权数量
    private String grantNumberRecordRemark;           //授权备注
    private String grantNumberRecordFeedback;         //授权反馈
    private Date grantNumberRecordApplytime;          //申请时间
    private Date grantNumberRecordFeedbacktime;       //反馈时间
    private int grantNumberRecordType;                //授权状态（1：待审核   2：审核通过   3：审核不通过）
    private int companyId;                            //企业id
    private int grantNumberRecordSee;                 //是否被查看（1：已查看  0：未查看）

    //
    private String applytime;          //申请时间String
    private String feedbacktime;          //反馈时间String
    private String companyName;            //企业名称

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getGrantNumberRecordId() {
        return grantNumberRecordId;
    }

    public void setGrantNumberRecordId(int grantNumberRecordId) {
        this.grantNumberRecordId = grantNumberRecordId;
    }

    public int getGrantNumberRecordAmount() {
        return grantNumberRecordAmount;
    }

    public void setGrantNumberRecordAmount(int grantNumberRecordAmount) {
        this.grantNumberRecordAmount = grantNumberRecordAmount;
    }

    public String getGrantNumberRecordRemark() {
        return grantNumberRecordRemark;
    }

    public void setGrantNumberRecordRemark(String grantNumberRecordRemark) {
        this.grantNumberRecordRemark = grantNumberRecordRemark;
    }

    public String getGrantNumberRecordFeedback() {
        return grantNumberRecordFeedback;
    }

    public void setGrantNumberRecordFeedback(String grantNumberRecordFeedback) {
        this.grantNumberRecordFeedback = grantNumberRecordFeedback;
    }

    public Date getGrantNumberRecordApplytime() {
        return grantNumberRecordApplytime;
    }

    public void setGrantNumberRecordApplytime(Date grantNumberRecordApplytime) {
        this.grantNumberRecordApplytime = grantNumberRecordApplytime;
    }

    public Date getGrantNumberRecordFeedbacktime() {
        return grantNumberRecordFeedbacktime;
    }

    public void setGrantNumberRecordFeedbacktime(Date grantNumberRecordFeedbacktime) {
        this.grantNumberRecordFeedbacktime = grantNumberRecordFeedbacktime;
    }

    public int getGrantNumberRecordType() {
        return grantNumberRecordType;
    }

    public void setGrantNumberRecordType(int grantNumberRecordType) {
        this.grantNumberRecordType = grantNumberRecordType;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }

    public String getFeedbacktime() {
        return feedbacktime;
    }

    public void setFeedbacktime(String feedbacktime) {
        this.feedbacktime = feedbacktime;
    }

    public int getGrantNumberRecordSee() {
        return grantNumberRecordSee;
    }

    public void setGrantNumberRecordSee(int grantNumberRecordSee) {
        this.grantNumberRecordSee = grantNumberRecordSee;
    }

    @Override
    public String toString() {
        return "GrantNumberRecord{" +
                "grantNumberRecordId=" + grantNumberRecordId +
                ", grantNumberRecordAmount=" + grantNumberRecordAmount +
                ", grantNumberRecordRemark='" + grantNumberRecordRemark + '\'' +
                ", grantNumberRecordFeedback='" + grantNumberRecordFeedback + '\'' +
                ", grantNumberRecordApplytime=" + grantNumberRecordApplytime +
                ", grantRecordNumberFeedbacktime=" + grantNumberRecordFeedbacktime +
                ", grantNumberRecordType=" + grantNumberRecordType +
                ", companyId=" + companyId +
                '}';
    }
}
