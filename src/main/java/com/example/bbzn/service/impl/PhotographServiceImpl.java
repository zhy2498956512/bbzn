package com.example.bbzn.service.impl;

import com.example.bbzn.dao.PhotographDao;
import com.example.bbzn.pojo.Photograph;
import com.example.bbzn.service.PhotographService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotographServiceImpl implements PhotographService {

    @Autowired
    private PhotographDao photographDao;

    public int savePhotograph(Photograph photograph){
        return photographDao.savePhotograph(photograph);
    }

}
