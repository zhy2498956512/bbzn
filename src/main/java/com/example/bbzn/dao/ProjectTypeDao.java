package com.example.bbzn.dao;

import com.example.bbzn.pojo.ProjectType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTypeDao {

    @Select("select * from b_project_type")
    List<ProjectType> getProjectTypeList();

}
