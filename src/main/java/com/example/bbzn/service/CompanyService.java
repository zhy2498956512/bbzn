package com.example.bbzn.service;

import com.example.bbzn.pojo.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanyList();

    Company getCompany(String companyUserName,String companyUserPass);

    Company getCompanyId(int company_id);

    int saveCompany(Company company);

    int updateCompany(Company company);

    int updatePass(Company company);

    int updateCompanyState(int companyState,int companyId);
}
