package com.example.bbzn.service.impl;

import com.example.bbzn.dao.GrantRecordDao;
import com.example.bbzn.pojo.GrantRecord;
import com.example.bbzn.service.GrantRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrantRecordServiceImpl implements GrantRecordService {

    @Autowired
    private GrantRecordDao grantRecordDao;

    public List<GrantRecord> getGrantRecordList(int companyId){
        return grantRecordDao.getGrantRecordList(companyId);
    }

    public GrantRecord getGrantRecord(int grant_record_id){
        return grantRecordDao.getGrantRecord(grant_record_id);
    }

    public List<GrantRecord> getGrantRecordRuleList(int grantRecordLength,String grantRecordBinary){
        return grantRecordDao.getGrantRecordRuleList(grantRecordLength,grantRecordBinary);
    }

    public int saveGrantRecord(GrantRecord grantRecord){
        return grantRecordDao.saveGrantRecord(grantRecord);
    }

    public int saveGrantRecordFile(GrantRecord grantRecord){
        return grantRecordDao.saveGrantRecordFile(grantRecord);
    }

}
