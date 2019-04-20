package com.example.bbzn.dao;

import com.example.bbzn.pojo.PushRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PushRecordDao {

    @Select("<script>" +
            "select count(1) from b_push_record where 1=1 " +
            "<if test='projectId!=0'> and project_id = #{projectId} </if>" +
            "<if test='equipmentId!=0'> and equipment_id = #{equipmentId} </if>" +
            "</script>")
    int getPushRecordCount(@Param("projectId") int projectId,@Param("equipmentId") int equipmentId);

    @Select("<script>" +
            "SELECT g.grant_code,a.apk_edition,a.apk_route,r.* from b_push_record as r,b_equipment as e,b_grant as g,b_apk as a " +
            "where e.equipment_id=r.equipment_id and g.grant_id=e.grant_id and a.apk_id=r.apk_id " +
            "<if test='projectId!=0'> and r.project_id = #{projectId} </if>" +
            "<if test='equipmentId!=0'> and r.equipment_id = #{equipmentId} </if>" +
            " limit #{pn},#{ps}" +
            "</script>")
    List<PushRecord> getPushRecordList(@Param("pn") int pageNum,
                                       @Param("ps") int pageSize,
                                       @Param("projectId") int projectId,
                                       @Param("equipmentId") int equipmentId);

    @Insert("insert into b_push_record(apk_id,project_id,equipment_id,push_time) value(#{p.apkId},#{p.projectId},#{p.equipmentId},now())")
    int savePushRecord(@Param("p")PushRecord pushRecord);

}
