package com.example.bbzn.service;

import com.example.bbzn.pojo.APK;

import java.util.List;

public interface APKService {

    APK getAPK(int userId);

    APK getApkEdition(int equipmentId);

    List<APK> getAPKList();

    int getPageAPKCount(int projectId);

    List<APK> getPageAPKList(int pageNum,int pageSize,int projectId);

    int saveAPK(APK apk);

}
