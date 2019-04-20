package com.example.bbzn.dao;

import com.example.bbzn.pojo.UserFeedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeedbackDao {

    @Insert("insert into b_user_feedback(user_id,feedback_phone,feedback_content,feedback_date) " +
            "value(#{uf.userId},#{uf.feedbackPhone},#{uf.feedbackContent},#{uf.feedbackDate})")
    int saveUserFeedback(@Param("uf") UserFeedback userFeedback);

}
