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
    private String languageSpeakTextCode;   //nuance语言代码
    private String languageMotherTongueCode;   //母语语言代码

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

    public Date getTranslateDate() {
        return translateDate;
    }

    public void setTranslateDate(Date translateDate) {
        this.translateDate = translateDate;
    }

    public String getLanguageSpeakTextCode() {
        return languageSpeakTextCode;
    }

    public void setLanguageSpeakTextCode(String languageSpeakTextCode) {
        this.languageSpeakTextCode = languageSpeakTextCode;
    }

    public String getLanguageMotherTongueCode() {
        return languageMotherTongueCode;
    }

    public void setLanguageMotherTongueCode(String languageMotherTongueCode) {
        this.languageMotherTongueCode = languageMotherTongueCode;
    }

    @Override
    public String toString() {
        return "Translate{" +
                "translateId=" + translateId +
                ", userId=" + userId +
                ", translateContent='" + translateContent + '\'' +
                ", translateResult='" + translateResult + '\'' +
                '}';
    }
}
