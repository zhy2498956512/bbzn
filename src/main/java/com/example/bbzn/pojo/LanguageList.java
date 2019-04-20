package com.example.bbzn.pojo;

import com.example.bbzn.pojo.CompanyLanguage;
import com.example.bbzn.pojo.Language;

import java.util.List;

public class LanguageList {

    private List<?> languageList;
    private List<?> companyLanguageList;

    public List<?> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<?> languageList) {
        this.languageList = languageList;
    }

    public List<?> getCompanyLanguageList() {
        return companyLanguageList;
    }

    public void setCompanyLanguageList(List<?> companyLanguageList) {
        this.companyLanguageList = companyLanguageList;
    }

    @Override
    public String toString() {
        return "LanguageList{" +
                "languageList=" + languageList +
                ", companyLanguageList=" + companyLanguageList +
                '}';
    }
}
