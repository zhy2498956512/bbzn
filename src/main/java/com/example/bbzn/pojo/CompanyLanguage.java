package com.example.bbzn.pojo;

public class CompanyLanguage {

    private int companyLanguageId;              //企业语言id
    private int companyId;                      //企业id
    private int languageId;                     //语言id

    //
    private String languageName;                //语言名称

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public int getCompanyLanguageId() {
        return companyLanguageId;
    }

    public void setCompanyLanguageId(int companyLanguageId) {
        this.companyLanguageId = companyLanguageId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Override
    public String toString() {
        return "CompanyLanguage{" +
                "companyLanguageId=" + companyLanguageId +
                ", companyId=" + companyId +
                ", languageId=" + languageId +
                '}';
    }
}
