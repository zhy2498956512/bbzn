package com.example.bbzn.dao;

import com.example.bbzn.pojo.CompanyLanguage;
import com.example.bbzn.pojo.Language;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyLanguageDao {

    @Select("select c.*,l.language_name from b_company_language as c,b_language as l where c.language_id = l.language_id and company_id = #{company_id}")
    List<CompanyLanguage> getLanguageList(int company_id);

    @Delete("Delete from b_company_language where company_id = #{company_id}")
    int deleteLanguage(int company_id);

    @Insert("insert into b_company_language(company_id,language_id) value(#{c.companyId},#{c.languageId})")
    int saveLanguage(@Param("c") CompanyLanguage companyLanguage);

}
