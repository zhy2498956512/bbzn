package com.example.bbzn.dao;

import com.example.bbzn.pojo.Company;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDao {

    @Select("select * from b_company")
    List<Company> getCompanyList();

    @Select("select * from b_company where company_user_name = #{companyUserName} and company_user_pass = #{companyUserPass}")
    Company getCompany(@Param("companyUserName") String companyUserName,@Param("companyUserPass") String companyUserPass);

    @Select("select * from b_company where company_id = #{company_id}")
    Company getCompanyId(int company_id);

    @Insert("insert into b_company(company_name,company_address,company_phone,company_mail,company_foundtime,company_user_id) "+
            "value(#{c.companyName},#{c.companyAddress},#{c.companyPhone},#{c.companyMail},#{c.companyFoundtime},#{c.companyUserId})")
    int saveCompany(@Param("c") Company company);


    @Update("UPDATE b_company set company_name=#{c.companyName},company_address=#{c.companyAddress},company_phone=#{c.companyPhone}"+
            " where company_id=#{c.companyId}")
    int updateCompany(@Param("c") Company company);

    @Update("UPDATE b_company set company_user_pass=#{c.companyUserPass} where company_id=#{c.companyId}")
    int updatePass(@Param("c") Company company);

    @Update("UPDATE b_company set company_state=#{companyState} where company_id=#{companyId}")
    int updateCompanyState(@Param("companyState") int companyState,@Param("companyId") int companyId);


}
