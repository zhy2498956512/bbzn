package com.example.bbzn.service.impl;

import com.example.bbzn.dao.PushRecordDao;
import com.example.bbzn.pojo.PushRecord;
import com.example.bbzn.service.PushRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PushRecordServiceImpl implements PushRecordService {

    @Autowired
    private PushRecordDao pushRecordDao;

    public int getPushRecordCount(int projectId,int equipmentId){
        return pushRecordDao.getPushRecordCount(projectId,equipmentId);
    }

    public List<PushRecord> getPushRecordList(int pageNum, int pageSize, int projectId, int equipmentId){
        return pushRecordDao.getPushRecordList((pageNum-1)*pageSize,pageSize,projectId,equipmentId);
    }

    public int savePushRecord(PushRecord pushRecord){
        return pushRecordDao.savePushRecord(pushRecord);
    }

}
