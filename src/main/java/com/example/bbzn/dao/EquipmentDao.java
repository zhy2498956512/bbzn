package com.example.bbzn.dao;

import com.example.bbzn.pojo.Equipment;
import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EquipmentDao {

    @Select("<script>"+
            "SELECT count(1) from b_equipment as e,b_project as p,b_grant as g " +
            "WHERE e.project_id=p.project_id and g.grant_id=e.grant_id " +
            " and g.grant_code like CONCAT('%',#{searchEquipment},'%')"+
            "and p.project_id in " +
            "<foreach collection='idlist' item='id' index='index' open='(' close=')' separator=','>" +
            "#{id}" +
            "</foreach>" +
            "<if test='date1!=\"\"'> and e.equipment_foundtime &gt; #{date1} </if>" +
            "<if test='date2!=\"\"'>and e.equipment_foundtime &lt; #{date2} </if>" +
            "<if test='equipmentType!=0'>and e.equipment_type = #{equipmentType} </if>" +
            "</script>")
    int getEquipmentCount(@Param("idlist") List<Integer> idlist,
                          @Param("date1") String date1,
                          @Param("date2") String date2,
                          @Param("equipmentType") int equipmentType,
                          @Param("searchEquipment") String searchEquipment);

    @Select("<script>"+
            "SELECT g.grant_code,c.company_name,p.project_name,e.* from b_equipment as e,b_project as p,b_company as c,b_grant as g " +
            "WHERE e.project_id=p.project_id and p.company_id=c.company_id and g.grant_id=e.grant_id " +
            " and g.grant_code like CONCAT('%',#{searchEquipment},'%') "+
            "and p.project_id in " +
            "<foreach collection='idlist' item='id' index='index' open='(' close=')' separator=','>" +
            "#{id}" +
            "</foreach>" +
            "<if test='date1!=\"\"'> and e.equipment_foundtime &gt; #{date1} </if>" +
            "<if test='date2!=\"\"'>and e.equipment_foundtime &lt; #{date2} </if>" +
            "<if test='equipmentType!=0'>and e.equipment_type = #{equipmentType} </if>" +
            " limit #{pn},#{ps}" +
            "</script>")
    List<Equipment> getEquipmentList(@Param("idlist") List<Integer> idlist,
                                     @Param("date1") String date1,
                                     @Param("date2") String date2,
                                     @Param("equipmentType") int equipmentType,
                                     @Param("searchEquipment") String searchEquipment,
                                     @Param("pn") int pageNum,
                                     @Param("ps") int pageSize);

    @Select("SELECT * from b_equipment where project_id = #{projectId}")
    List<Equipment> getProjectIdLookEquipmentList(int projectId);

    @Select("SELECT g.grant_code,c.company_name,p.project_name,e.* from b_equipment as e,b_project as p,b_company as c,b_grant as g " +
            "  WHERE e.project_id=p.project_id and p.company_id=c.company_id and g.grant_id=e.grant_id and e.equipment_id = #{equipmentId}")
    Equipment getEquipment(int equipmentId);

    @Select("select equipment_id from b_equipment where grant_id = #{grantId}")
    int getGrantId(int grantId);

    @Select("select * from b_equipment where grant_id = #{grantId}")
    Equipment getGrant(int grantId);

    @Select("select e.* FROM b_equipment as e,b_grant as g where e.grant_id=g.grant_id AND g.grant_code = #{grantCode}")
    Equipment getGrantCode(String grantCode);

    @Select("select count(1) from b_equipment where grant_id = (select grant_id from b_grant where grant_code = #{grantCode})")
    int isItActivated(String grantCode);

    @Delete("delete from b_equipment where equipment_id = #{equipmentId}")
    int deleteEquipment(int equipmentId);

    @Update("Update b_equipment set apk_id=#{apkId} where equipment_id=#{equipmentId}")
    int updateEquipmentAppId(@Param("equipmentId") int equipmentId,@Param("apkId") int apkId);


    @Update("Update b_equipment set equipment_type=1 where grant_id = #{grantId}")
    int updateEquipmentType(int grantId);

    @Update("Update b_equipment set project_id=#{projectId} where equipment_id = #{equipmentId}")
    int equipmentSwitchProject(@Param("projectId") int projectId,@Param("equipmentId") int equipmentId);

    @Update("UPDATE b_equipment set equipment_logintime = now() where grant_id = (select grant_id from b_user where user_id = #{userId})")
    int updateEquipmentLogintime(int userId);

    //主键回填
    @Options(keyProperty = "e.equipmentId", keyColumn ="equipment_id",useGeneratedKeys = true )
    @Insert("insert into b_equipment(equipment_foundtime,equipment_validity,project_id,company_id,grant_id,equipment_type) " +
            "value(#{e.equipmentFoundtime},365,#{e.projectId},#{e.companyId},#{e.grantId},2)")
    int saveEquipment(@Param("e") Equipment equipment);

}
