package com.example.bbzn.dao;

import com.example.bbzn.pojo.Language;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageDao {

    @Select("select * from b_language")
    List<Language> getLanguageList();

    @Select("<script>" +
            "select * from b_language where language_id in "+
            "<foreach collection='list' item='id' index='index' open='(' close=')' separator=','>" +
                "#{id}" +
            "</foreach>" +
            "</script>")
    List<Language> getEquipmentLanguageList(List<Integer> idlist);

}
