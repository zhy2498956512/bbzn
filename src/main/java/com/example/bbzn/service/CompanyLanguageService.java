package com.example.bbzn.service;

import com.example.bbzn.pojo.CompanyLanguage;

import java.util.List;

public interface CompanyLanguageService {

    List<CompanyLanguage> getLanguageList(int company_id);

    int deleteLanguage(int company_id);

    int saveLanguage(CompanyLanguage companyLanguage);

}
