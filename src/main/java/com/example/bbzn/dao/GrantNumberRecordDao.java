package com.example.bbzn.dao;

import com.example.bbzn.pojo.GrantNumberRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GrantNumberRecordDao {

    @Select("select count(1) from b_grant_number_record where company_id = #{companyId} and grant_number_record_type = #{recordType} ")
    int getGrantNumberRecordCount(@Param("companyId") int companyId,@Param("recordType") int recordType);

    @Select("<script>" +
            "select g.*,c.company_name from b_grant_number_record as g,b_company as c where g.company_id=c.company_id and grant_number_record_type = #{recordType} " +
            "<if test='ci!=\"\"'> and g.company_id = #{ci} </if>" +
            "  order by  grant_number_record_feedbacktime desc limit #{pn},#{ps}" +
            "</script>")
    List<GrantNumberRecord> getGrantNumberRecordList(@Param("pn") int pageNum,
                                                     @Param("ps") int pageSize,
                                                     @Param("ci") int companyId,
                                                     @Param("recordType") int recordType);

    @Select("<script>" +
            "select count(1) from b_grant_number_record where company_id = #{companyId} and grant_number_record_type = 1 " +
            "<if test='see!=-1'> and grant_number_record_see = 0 </if>" +
            "</script>")
    int getSeeCount(@Param("companyId") int companyId,@Param("see") int see);

    @Select("select * from b_grant_number_record where company_id = #{ci} and grant_number_record_type = 1 " +
            "order by grant_number_record_see,grant_number_record_feedbacktime desc limit #{pn},#{ps}")
    List<GrantNumberRecord> getSeeList(@Param("pn") int pageNum,
                                                     @Param("ps") int pageSize,
                                                     @Param("ci") int companyId);

    @Select("select * from b_grant_number_record where grant_number_record_id = #{grantNumberRecordId}")
    GrantNumberRecord getGrantNumberRecord(int grantNumberRecordId);

    @Update("Update b_grant_number_record set grant_number_record_see = 1 where grant_number_record_id = #{grantNumberRecordId}")
    int updateSee(int grantNumberRecordId);

    @Insert("insert into b_grant_number_record(grant_number_record_amount,grant_number_record_remark,grant_number_record_applytime,grant_number_record_feedbacktime,grant_number_record_type,company_id) "+
            " value(#{g.grantNumberRecordAmount},#{g.grantNumberRecordRemark},#{g.grantNumberRecordApplytime},#{g.grantNumberRecordFeedbacktime},#{g.grantNumberRecordType},#{g.companyId})")
    int saveGrantRecord(@Param("g") GrantNumberRecord grantNumberRecord);

    @Update("Update b_grant_number_record set grant_number_record_feedbacktime=#{date},grant_number_record_type=1 where grant_number_record_id = #{grantNumberRecordId}")
    int updateGrantRecord(@Param("date") Date date, @Param("grantNumberRecordId") int grantNumberRecordId);

}
