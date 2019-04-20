package com.example.bbzn.service.impl;

import com.example.bbzn.dao.GrantNumberRecordDao;
import com.example.bbzn.pojo.GrantNumberRecord;
import com.example.bbzn.service.GrantNumberRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GrantNumberRecordServiceImpl implements GrantNumberRecordService {

    @Autowired
    private GrantNumberRecordDao grantNumberRecordDao;


    public int getGrantNumberRecordCount(int companyId,int recordType){
        return grantNumberRecordDao.getGrantNumberRecordCount(companyId,recordType);
    }

    public List<GrantNumberRecord> getGrantNumberRecordList(int pageNum,int pageSize,int companyId,int recordType){
        return grantNumberRecordDao.getGrantNumberRecordList((pageNum-1)*pageSize,pageSize,companyId,recordType);
    }

    public int getSeeCount(int companyId,int see){
        return grantNumberRecordDao.getSeeCount(companyId,see);
    }

    public List<GrantNumberRecord> getSeeList(int pageNum,int pageSize,int companyId){
        return grantNumberRecordDao.getSeeList((pageNum-1)*pageSize,pageSize,companyId);
    }

    public int saveGrantRecord(GrantNumberRecord grantNumberRecord){
        return grantNumberRecordDao.saveGrantRecord(grantNumberRecord);
    }

    public int updateGrantRecord(Date date, int grantNumberRecordId){
        return grantNumberRecordDao.updateGrantRecord(date,grantNumberRecordId);
    }

}
