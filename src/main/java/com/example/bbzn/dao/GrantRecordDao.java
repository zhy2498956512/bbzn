package com.example.bbzn.dao;

import com.example.bbzn.pojo.GrantRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrantRecordDao {

    @Select("select * from b_grant_record where company_id = #{companyId}")
    List<GrantRecord> getGrantRecordList(int companyId);

    @Select("select * from b_grant_record where grant_record_id = #{grant_record_id}")
    GrantRecord getGrantRecord(int grant_record_id);

    @Select("select * from b_grant_record where grant_record_length=#{grantRecordLength} and grant_record_binary=#{grantRecordBinary}")
    List<GrantRecord> getGrantRecordRuleList(@Param("grantRecordLength") int grantRecordLength,@Param("grantRecordBinary") String grantRecordBinary);

    //主键回填
    @Options(keyProperty = "g.grantRecordId", keyColumn ="grant_record_id",useGeneratedKeys = true )
    @Insert("insert into b_grant_record(grant_record_amount,grant_record_prefix,grant_record_length,grant_record_binary,grant_record_submittime,company_id,project_id,grant_record_file) "+
            "value(#{g.grantRecordAmount},#{g.grantRecordPrefix},#{g.grantRecordLength},#{g.grantRecordBinary},#{g.grantRecordSubmittime},#{g.companyId},#{g.projectId},#{g.grantRecordFile})")
    int saveGrantRecord(@Param("g") GrantRecord grantRecord);

    //主键回填
    @Options(keyProperty = "g.grantRecordId", keyColumn ="grant_record_id",useGeneratedKeys = true )
    @Insert("insert into b_grant_record(grant_record_amount,grant_record_submittime,company_id,project_id,grant_record_file) "+
            "value(#{g.grantRecordAmount},#{g.grantRecordSubmittime},#{g.companyId},#{g.projectId},#{g.grantRecordFile})")
    int saveGrantRecordFile(@Param("g") GrantRecord grantRecord);

}
