package com.example.bbzn.pojo;

public class Language {

    private int languageId;            //语言id
    private String languageName;            //语言id
    private String languageSpeakTextCode;
    private String languageTranslateCode;
    private String languageTextSpeakCode;
    private String countryFlag;             //国旗


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

    public String getLanguageSpeakTextCode() {
        return languageSpeakTextCode;
    }

    public void setLanguageSpeakTextCode(String languageSpeakTextCode) {
        this.languageSpeakTextCode = languageSpeakTextCode;
    }

    public String getLanguageTranslateCode() {
        return languageTranslateCode;
    }

    public void setLanguageTranslateCode(String languageTranslateCode) {
        this.languageTranslateCode = languageTranslateCode;
    }

    public String getLanguageTextSpeakCode() {
        return languageTextSpeakCode;
    }

    public void setLanguageTextSpeakCode(String languageTextSpeakCode) {
        this.languageTextSpeakCode = languageTextSpeakCode;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", languageName='" + languageName + '\'' +
                '}';
    }

}
