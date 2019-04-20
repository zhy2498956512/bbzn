package com.example.bbzn.dao;

import com.example.bbzn.pojo.Simultaneous;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimultaneousDao {

    @Select("select * from b_simultaneous where user_id = #{userId}")
    List<Simultaneous> getSimultaneousList(int userId);

    @Insert("insert into b_simultaneous(user_id,simultaneous_date,simultaneous_content,simultaneous_result) " +
            "value(#{s.userId},#{s.simultaneousDate},#{s.simultaneousContent},#{s.simultaneousResult})")
    int saveSimultaneous(@Param("s") Simultaneous simultaneous);

    @Delete("delete from b_simultaneous where simultaneous_id = #{simultaneousId}")
    int deleteSimultaneous(int simultaneousId);


}
