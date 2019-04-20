package com.example.bbzn.service;

import com.example.bbzn.pojo.EquipmentLanguage;

import java.util.List;

public interface EquipmentLanguageService {

    List<EquipmentLanguage> getEquipmentLanguageList(int equipmentId);

    int saveEquipmentLanguage(EquipmentLanguage equipmentLanguage);

    int deleteEquipmentLanguage(EquipmentLanguage equipmentLanguage);

    int deleteSingleEquipmentLanguage(int equipmentId);

}
