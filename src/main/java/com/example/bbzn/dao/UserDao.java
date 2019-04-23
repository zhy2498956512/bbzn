package com.example.bbzn.dao;

import com.example.bbzn.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserDao {

    @Select("select * from b_user where user_mail=#{mail} and user_pass=#{pass}")
    User getUser(@Param("mail") String mail,@Param("pass") String pass);

    @Select("select * from b_user where user_id=#{user_id}")
    User getUserId(int user_id);

    @Select("select count(1) from b_user where user_mail=#{mail}")
    int getMail(String mail);

    @Select("select count(1) from b_user where grant_id=#{grantId}")
    int getGrant(int grantId);

    @Select("select count(1) from b_user where token = #{token}")
    int getUserToken(String token);

    @Update("update b_user set token=#{token} where user_id = #{userId}")
    int updateUserToken(@Param("userId") int userId,@Param("token") String token);

    @Update("update b_user set user_logintime=now() where user_id=#{userId}")
    int updateUserLoginTime(int userId);

    @Update("update b_user set grant_id=#{grant_id} where user_id=#{user_id}")
    int updateUserGrantId(@Param("user_id") int user_id,@Param("grant_id") int grant_id);

    @Update("update b_user set user_name=#{user.userName},user_mobile_code=#{user.userMobileCode}" +
            ",user_mail=#{user.userMail},user_sex=#{user.userSex},user_address=#{user.userAddress}" +
            " where user_id=#{user.userId}")
    int updateUserInformation(@Param("user") User user);

    @Update("UPDATE b_user set grant_id = 0 where grant_id = #{grantId}")
    int updateUserGrant(int grantId);

    @Update("update b_user set user_location=#{userLocation} where user_id=#{userId}")
    int updateUserlocation(@Param("userId") int userId,@Param("userLocation") String userLocation);

    @Update("update b_user set user_pass=#{userPass} where user_mail=#{userMail}")
    int updateUserPass(@Param("userMail") String userMail,@Param("userPass") String userPass);

    @Insert("insert into b_user(user_mail,user_pass) value(#{user.userMail},#{user.userPass})")
    int saveUser(@Param("user") User user);

}
