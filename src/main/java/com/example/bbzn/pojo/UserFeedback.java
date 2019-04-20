package com.example.bbzn.pojo;

import java.util.Date;

public class UserFeedback {

    /*
     * 用户反馈表
     * */

    private int feedbackId;                 //反馈id
    private int userId;                     //反馈用户
    private String feedbackPhone;           //反馈手机
    private String feedbackContent;         //反馈内容
    private Date feedbackDate;              //反馈时间

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFeedbackPhone() {
        return feedbackPhone;
    }

    public void setFeedbackPhone(String feedbackPhone) {
        this.feedbackPhone = feedbackPhone;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    @Override
    public String toString() {
        return "UserFeedback{" +
                "feedbackId=" + feedbackId +
                ", userId=" + userId +
                ", feedbackPhone='" + feedbackPhone + '\'' +
                ", feedbackContent='" + feedbackContent + '\'' +
                ", feedbackDate=" + feedbackDate +
                '}';
    }
}
