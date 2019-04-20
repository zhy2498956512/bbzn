package com.example.bbzn.service.impl;

import com.example.bbzn.dao.UserFeedbackDao;
import com.example.bbzn.pojo.UserFeedback;
import com.example.bbzn.service.UserFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFeedbackServiceImpl implements UserFeedbackService {

    @Autowired
    private UserFeedbackDao userFeedbackDao;

    public int saveUserFeedback(UserFeedback userFeedback){
        return userFeedbackDao.saveUserFeedback(userFeedback);
    }

}
