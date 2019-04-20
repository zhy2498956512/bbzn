package com.example.bbzn.service.impl;

import com.example.bbzn.dao.CompanyLanguageDao;
import com.example.bbzn.pojo.CompanyLanguage;
import com.example.bbzn.service.CompanyLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyLanguageServiceImpl implements CompanyLanguageService {

    @Autowired
    private CompanyLanguageDao companyLanguageDao;

    public int deleteLanguage(int company_id){
        return companyLanguageDao.deleteLanguage(company_id);
    }

    public List<CompanyLanguage> getLanguageList(int company_id){
        return companyLanguageDao.getLanguageList(company_id);
    }

    public int saveLanguage(CompanyLanguage companyLanguage){
        return companyLanguageDao.saveLanguage(companyLanguage);
    }
}
