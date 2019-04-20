package com.example.bbzn.dao;

import com.example.bbzn.pojo.Grant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrantDao {

    @Select("select * from b_grant where grant_code=#{grantCode}")
    Grant getGrant(String grantCode);

    @Select("<script>"+
            "select count(1) from b_grant where grant_code=#{grantCode}" +
            "<if test='idlist.size()>0'>"+
            " and grant_record_id in " +
            "<foreach collection='idlist' item='id' index='index' open='(' close=')' separator=','>" +
            "#{id}" +
            "</foreach>" +
            "</if>"+
            "</script>")
    int getGrantCode(@Param("grantCode") String grantCode,@Param("idlist") List<Integer> idlist);

    @Select("select count(1) from b_grant as g,b_equipment as e where g.grant_id=e.grant_id and g.grant_code = #{grantCode} AND e.project_id = #{projectId}")
    int getAvailability(@Param("grantCode") String grantCode,@Param("projectId") int projectId);

    @Insert("insert into b_grant(grant_code,grant_record_id) value(#{g.grantCode},#{g.grantRecordId})")
    int saveGrant(@Param("g") Grant grant);

}
