package com.example.bbzn.pojo;

public class Language {

    private int languageId;            //语言id
    private String languageName;            //语言id
    private String asrCode;
    private String translateCode;
    private String ttsCode;
    private String countryFlag;             //国旗
    private String player;             //国旗
    private int gender;             //国旗


    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getAsrCode() {
        return asrCode;
    }

    public void setAsrCode(String asrCode) {
        this.asrCode = asrCode;
    }

    public String getTranslateCode() {
        return translateCode;
    }

    public void setTranslateCode(String translateCode) {
        this.translateCode = translateCode;
    }

    public String getTtsCode() {
        return ttsCode;
    }

    public void setTtsCode(String ttsCode) {
        this.ttsCode = ttsCode;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", languageName='" + languageName + '\'' +
                ", asrCode='" + asrCode + '\'' +
                ", translateCode='" + translateCode + '\'' +
                ", ttsCode='" + ttsCode + '\'' +
                ", countryFlag='" + countryFlag + '\'' +
                ", player='" + player + '\'' +
                ", gender=" + gender +
                '}';
    }
}
