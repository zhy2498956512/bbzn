package com.example.bbzn.service.impl;

import com.example.bbzn.dao.TranslateDao;
import com.example.bbzn.pojo.Translate;
import com.example.bbzn.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslateServiceImpl implements TranslateService {

    @Autowired
    private TranslateDao translateDao;

    public List<Translate> getTranslateList(int userId){
        return translateDao.getTranslateList(userId);
    }

    public int saveTranslate(Translate translate){
        return translateDao.saveTranslate(translate);
    }

    public int deleteTranslate(int translateId){
        return translateDao.deleteTranslate(translateId);
    }
}
