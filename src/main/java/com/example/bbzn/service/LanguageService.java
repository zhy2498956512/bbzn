package com.example.bbzn.service;

import com.example.bbzn.pojo.Language;

import java.util.List;

public interface LanguageService {

    List<Language> getLanguageList();

    List<Language> getEquipmentLanguageList(List<Integer> idlist);
}
