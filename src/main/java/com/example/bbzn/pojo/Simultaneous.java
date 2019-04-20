package com.example.bbzn.pojo;

import java.util.Date;

public class Simultaneous {

    /*
     * 同声传译记录表
     * */

    private int simultaneousId;              //同声传译记录id
    private int userId;                      //用户id
    private Date simultaneousDate;           //时间
    private String simultaneousContent;      //内容
    private String simultaneousResult;       //结果

    public int getSimultaneousId() {
        return simultaneousId;
    }

    public void setSimultaneousId(int simultaneousId) {
        this.simultaneousId = simultaneousId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getSimultaneousDate() {
        return simultaneousDate;
    }

    public void setSimultaneousDate(Date simultaneousDate) {
        this.simultaneousDate = simultaneousDate;
    }

    public String getSimultaneousContent() {
        return simultaneousContent;
    }

    public void setSimultaneousContent(String simultaneousContent) {
        this.simultaneousContent = simultaneousContent;
    }

    public String getSimultaneousResult() {
        return simultaneousResult;
    }

    public void setSimultaneousResult(String simultaneousResult) {
        this.simultaneousResult = simultaneousResult;
    }

    @Override
    public String toString() {
        return "Simultaneous{" +
                "simultaneousId=" + simultaneousId +
                ", userId=" + userId +
                ", simultaneousDate=" + simultaneousDate +
                ", simultaneousContent='" + simultaneousContent + '\'' +
                ", simultaneousResult='" + simultaneousResult + '\'' +
                '}';
    }
}
