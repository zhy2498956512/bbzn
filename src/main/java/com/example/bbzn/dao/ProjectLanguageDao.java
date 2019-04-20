package com.example.bbzn.dao;

import com.example.bbzn.pojo.ProjectLanguage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectLanguageDao {

    @Select("select pl.*,l.language_name from b_project_language as pl,b_project as p,b_language as l\n" +
            " where pl.project_id=p.project_id and pl.project_id = #{project_id} " +
            "and l.language_id=pl.language_id")
    List<ProjectLanguage> getLanguageList(int project_id);

    @Insert("insert into b_project_language(project_id,language_id) value(#{p.projectId},#{p.languageId})")
    int saveProjectLanguage(@Param("p") ProjectLanguage projectLanguage);

    @Delete("DELETE FROM b_project_language where project_id = #{p.projectId} and language_id = #{p.languageId}")
    int deleteProjectLanguage(@Param("p") ProjectLanguage projectLanguage);

}
