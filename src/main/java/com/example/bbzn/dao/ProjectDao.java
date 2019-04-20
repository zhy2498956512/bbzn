package com.example.bbzn.dao;

import com.example.bbzn.pojo.Project;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectDao {

    @Select("<script>" +
            "select p.*,t.project_type_name from b_project as p,b_project_type as t " +
            "where p.project_type_id = t.project_type_id and company_id in " +
            "<foreach collection='list' item='id' index='index' open='(' close=')' separator=','>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<Project> getProjectList(List<Integer> companyId);

    @Select("select * from b_project where project_id = #{project_id}")
    Project getProject(int project_id);

    @Update("update b_project set project_name = #{projectName},project_updatedate = now() where project_id = #{projectId}")
    int updateProject(@Param("projectName") String projectName,@Param("projectId") int projectId);

    @Insert("insert into b_project(project_name,project_newdate,project_type_id,company_id) value(#{p.projectName},#{p.projectNewdate},#{p.projectTypeId},#{p.companyId})")
    int saveProject(@Param("p") Project project);

}
