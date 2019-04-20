package com.example.bbzn.service.impl;

import com.example.bbzn.dao.APKDao;
import com.example.bbzn.pojo.APK;
import com.example.bbzn.service.APKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APKServiceImpl implements APKService {

    @Autowired
    private APKDao apkDao;

    public APK getAPK(int userId){
        return apkDao.getAPK(userId);
    }

    public APK getApkEdition(int equipmentId){
        return apkDao.getApkEdition(equipmentId);
    }

    public List<APK> getAPKList(){
        return apkDao.getAPKList();
    }

    public int getPageAPKCount(int projectId){
        return apkDao.getPageAPKCount(projectId);
    }

    public List<APK> getPageAPKList(int pageNum,int pageSize,int projectId){
        return apkDao.getPageAPKList((pageNum-1)*pageSize,pageSize,projectId);
    }

    public int saveAPK(APK apk){
        return apkDao.saveAPK(apk);
    }

}
