package com.example.bbzn.dao;

import com.example.bbzn.pojo.EquipmentLanguage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentLanguageDao {

    @Select("SELECT el.*,l.language_name FROM b_equipment_language as el,b_language as l" +
            " WHERE l.language_id=el.language_id and equipment_id = #{equipmentId}")
    List<EquipmentLanguage> getEquipmentLanguageList(int equipmentId);

    @Insert("insert into b_equipment_language(equipment_id,language_id) value(#{e.equipmentId},#{e.languageId})")
    int saveEquipmentLanguage(@Param("e") EquipmentLanguage equipmentLanguage);

    @Delete("delete from b_equipment_language where equipment_id=#{e.equipmentId} and language_id=#{e.languageId}")
    int deleteEquipmentLanguage(@Param("e") EquipmentLanguage equipmentLanguage);

    @Delete("delete from b_equipment_language where equipment_id=#{equipmentId}")
    int deleteSingleEquipmentLanguage(int equipmentId);

}
