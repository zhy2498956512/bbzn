package com.example.bbzn.dao;

import com.example.bbzn.pojo.Translate;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslateDao {

    @Select("select * from b_translate where user_id = #{userId}")
    List<Translate> getTranslateList(int userId);

    @Insert("insert into b_translate(user_id,translate_date,translate_content,translate_result,foreign_play_code,mother_play_code,player,is_left,gender) " +
            "value(#{t.userId},#{t.translateDate},#{t.translateContent},#{t.translateResult},#{t.foreignPlayCode},#{t.motherPlayCode},#{t.player},#{t.isLeft},#{t.gender})")
    int saveTranslate(@Param("t") Translate translate);

    @Delete("delete from b_translate where translate_id = #{translateId}")
    int deleteTranslate(int translateId);

}
