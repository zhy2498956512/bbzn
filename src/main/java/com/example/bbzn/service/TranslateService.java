package com.example.bbzn.service;

import com.example.bbzn.pojo.Translate;
import java.util.List;

public interface TranslateService {

    List<Translate> getTranslateList(int userId);

    int saveTranslate(Translate translate);

    int deleteTranslate(int translateId);

}
