package com.example.bbzn.dao;

import com.example.bbzn.pojo.Photograph;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographDao {

    @Insert("insert into b_photograph(user_id,photograph_content,photograph_date) " +
            "value(#{p.userId},#{p.photographContent},#{p.photographDate})")
    int savePhotograph(@Param("p") Photograph photograph);

}
