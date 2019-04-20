package com.example.bbzn.service;

import com.example.bbzn.pojo.User;

import java.util.Date;

public interface UserService {

    User getUser(String phone,String pass);

    int getPhone(String phone);

    int getGrant(int grantId);

    User getUserId(int user_id);

    int getUserToken(String token);

    int updateUserToken(int userId,String token);

    int updateUserLoginTime(int userId);

    int updateUserGrantId(int user_id,int grant_id);

    int updateUserInformation(User user);

    int updateUserPass(String userPhone,String userPass);

    int updateUserGrant(int grantId);

    int updateUserlocation(int userId,String userLocation);

    int saveUser(User user);

}
