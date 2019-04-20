package com.example.bbzn.service.impl;

import com.example.bbzn.dao.UserDao;
import com.example.bbzn.pojo.User;
import com.example.bbzn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User getUser(String phone, String pass){
        return userDao.getUser(phone,pass);
    }

    public int getPhone(String phone){
        return userDao.getPhone(phone);
    }

    public User getUserId(int user_id){
        return userDao.getUserId(user_id);
    }

    public int getUserToken(String token){ return userDao.getUserToken(token); }

    public int updateUserToken(int userId,String token){ return userDao.updateUserToken(userId,token); }

    public int getGrant(int grantId){
        return userDao.getGrant(grantId);
    }

    public int updateUserLoginTime(int userId){ return userDao.updateUserLoginTime(userId); }

    public int updateUserGrantId(int user_id,int grant_id){
        return userDao.updateUserGrantId(user_id,grant_id);
    }

    public int updateUserInformation(User user){
        return userDao.updateUserInformation(user);
    }

    public int updateUserPass(String userPhone,String userPass){
        return userDao.updateUserPass(userPhone,userPass);
    }

    public int updateUserGrant(int grantId){
        return userDao.updateUserGrant(grantId);
    }

    public int updateUserlocation(int userId,String userLocation){
        return userDao.updateUserlocation(userId,userLocation);
    }

    public int saveUser(User user){
        return userDao.saveUser(user);
    }

}
