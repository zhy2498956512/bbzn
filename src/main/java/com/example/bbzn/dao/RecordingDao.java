package com.example.bbzn.dao;

import com.example.bbzn.pojo.Recording;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordingDao {

    @Select("select * from b_recording where user_id = #{userId}")
    List<Recording> getRecordingList(int userId);

    @Insert("insert into b_recording(user_id,recording_name,recording_duration,recording_date,recording_content,file_path) " +
            "value(#{r.userId},#{r.recordingName},#{r.recordingDuration},#{r.recordingDate},#{r.recordingContent},#{r.filePath})")
    int saveRecording(@Param("r") Recording recording);

    @Delete("delete from b_recording where recording_id = #{recordingId}")
    int deleteRecording(int recordingId);

    @Update("update b_recording set recording_name = #{recordingName} where recording_id = #{recordingId}")
    int updateRecording(@Param("recordingName") String recordingName,@Param("recordingId") int recordingId);

}
