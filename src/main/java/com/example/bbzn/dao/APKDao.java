package com.example.bbzn.dao;

import com.example.bbzn.pojo.APK;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface APKDao {

    @Select("select a.* from b_apk as a,b_equipment as e,b_grant as g,b_user as u where a.apk_id=e.apk_id and g.grant_id=e.grant_id and u.grant_id=g.grant_id and u.user_id=#{userId}")
    APK getAPK(int userId);

    @Select("SELECT apk_edition from b_apk where apk_id = (select apk_id from b_equipment where equipment_id = #{equipmentId})")
    APK getApkEdition(int equipmentId);

    @Select("select * from b_apk")
    List<APK> getAPKList();

    @Select("select count(1) from b_apk where project_id = #{projectId}")
    int getPageAPKCount(int projectId);

    @Select("select * from b_apk where project_id = #{projectId} limit #{pn},#{ps}")
    List<APK> getPageAPKList(@Param("pn") int pageNum,
                                       @Param("ps") int pageSize,
                                       @Param("projectId") int projectId);

    //主键回填
    @Options(keyProperty = "apk.apkId", keyColumn ="apk_id",useGeneratedKeys = true )
    @Insert("insert into b_apk(apk_edition,apk_route,upload_date,project_id) value(#{apk.apkEdition},#{apk.apkRoute},#{apk.uploadDate},#{apk.projectId})")
    int saveAPK(@Param("apk") APK apk);

}
