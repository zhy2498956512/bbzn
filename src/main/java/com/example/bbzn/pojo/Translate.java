package com.example.bbzn.pojo;

import java.util.Date;

public class Translate {

    /*
     * 翻译记录表
     * */

    private int translateId;                //翻译记录id
    private int userId;                     //用户id
    private Date translateDate;             //翻译时间
    private String translateContent;        //翻译内容
    private String translateResult;         //翻译结果
    private String foreignPlayCode;   //nuance语言代码
    private String motherPlayCode;   //母语语言代码
    private String player;
    private int isLeft;//是否母语 1.母语，2.外语
    private int gender;//性别 1.男的，2.女的

    public int getTranslateId() {
        return translateId;
    }

    public void setTranslateId(int translateId) {
        this.translateId = translateId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getTranslateDate() {
        return translateDate;
    }

    public void setTranslateDate(Date translateDate) {
        this.translateDate = translateDate;
    }

    public String getTranslateContent() {
        return translateContent;
    }

    public void setTranslateContent(String translateContent) {
        this.translateContent = translateContent;
    }

    public String getTranslateResult() {
        return translateResult;
    }

    public void setTranslateResult(String translateResult) {
        this.translateResult = translateResult;
    }

    public String getForeignPlayCode() {
        return foreignPlayCode;
    }

    public void setForeignPlayCode(String foreignPlayCode) {
        this.foreignPlayCode = foreignPlayCode;
    }

    public String getMotherPlayCode() {
        return motherPlayCode;
    }

    public void setMotherPlayCode(String motherPlayCode) {
        this.motherPlayCode = motherPlayCode;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getIsLeft() {
        return isLeft;
    }

    public void setIsLeft(int isLeft) {
        this.isLeft = isLeft;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Translate{" +
                "translateId=" + translateId +
                ", userId=" + userId +
                ", translateDate=" + translateDate +
                ", translateContent='" + translateContent + '\'' +
                ", translateResult='" + translateResult + '\'' +
                ", foreignPlayCode='" + foreignPlayCode + '\'' +
                ", motherPlayCode='" + motherPlayCode + '\'' +
                ", player='" + player + '\'' +
                ", isLeft=" + isLeft +
                ", gender=" + gender +
                '}';
    }
}
