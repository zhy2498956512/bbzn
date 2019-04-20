package com.example.bbzn.service.impl;

import com.example.bbzn.dao.LanguageDao;
import com.example.bbzn.pojo.Language;
import com.example.bbzn.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageDao languageDao;

    public List<Language> getLanguageList(){
        return languageDao.getLanguageList();
    }

    public List<Language> getEquipmentLanguageList(List<Integer> idlist){
        return languageDao.getEquipmentLanguageList(idlist);
    }
}
