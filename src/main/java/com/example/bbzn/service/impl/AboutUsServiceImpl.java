package com.example.bbzn.service.impl;

import com.example.bbzn.dao.AboutUsDao;
import com.example.bbzn.pojo.AboutUs;
import com.example.bbzn.service.AboutUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutUsServiceImpl implements AboutUsService {

    @Autowired
    private AboutUsDao aboutUsDao;

    public AboutUs getAboutUs(){
        return aboutUsDao.getAboutUs();
    }

}
