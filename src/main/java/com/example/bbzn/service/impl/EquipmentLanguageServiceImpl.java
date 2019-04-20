package com.example.bbzn.service.impl;

import com.example.bbzn.dao.EquipmentLanguageDao;
import com.example.bbzn.pojo.EquipmentLanguage;
import com.example.bbzn.service.EquipmentLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentLanguageServiceImpl implements EquipmentLanguageService {

    @Autowired
    private EquipmentLanguageDao equipmentLanguageDao;

    public List<EquipmentLanguage> getEquipmentLanguageList(int equipmentId){
        return equipmentLanguageDao.getEquipmentLanguageList(equipmentId);
    }

    public int saveEquipmentLanguage(EquipmentLanguage equipmentLanguage){
        return equipmentLanguageDao.saveEquipmentLanguage(equipmentLanguage);
    }

    public int deleteEquipmentLanguage(EquipmentLanguage equipmentLanguage){
        return equipmentLanguageDao.deleteEquipmentLanguage(equipmentLanguage);
    }

    public int deleteSingleEquipmentLanguage(int equipmentId){
        return equipmentLanguageDao.deleteSingleEquipmentLanguage(equipmentId);
    }
}
