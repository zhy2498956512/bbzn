package com.example.bbzn.service.impl;

import com.example.bbzn.dao.CompanyDao;
import com.example.bbzn.pojo.Company;
import com.example.bbzn.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    public List<Company> getCompanyList(){
        return companyDao.getCompanyList();
    }

    public Company getCompany(String companyUserName,String companyUserPass){
        return companyDao.getCompany(companyUserName,companyUserPass);
    }

    public Company getCompanyId(int company_id){
        return companyDao.getCompanyId(company_id);
    }

    public int saveCompany(Company company){
        return companyDao.saveCompany(company);
    }

    public int updateCompany(Company company){
        return companyDao.updateCompany(company);
    }

    public int updatePass(Company company){ return companyDao.updatePass(company); }

    public int updateCompanyState(int companyState,int companyId){
        return companyDao.updateCompanyState(companyState,companyId);
    }
}
