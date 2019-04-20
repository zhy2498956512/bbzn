package com.example.bbzn.dao;

import com.example.bbzn.pojo.AboutUs;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutUsDao {

    @Select("select * from b_aboutUs")
    AboutUs getAboutUs();

}
