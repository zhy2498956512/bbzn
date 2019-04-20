package com.example.bbzn.service;

import com.example.bbzn.pojo.GrantNumberRecord;

import java.util.Date;
import java.util.List;

public interface GrantNumberRecordService {

    int getGrantNumberRecordCount(int companyId,int recordType);

    List<GrantNumberRecord> getGrantNumberRecordList(int pageNum,int pageSize,int companyId,int recordType);

    int getSeeCount(int companyId,int see);

    List<GrantNumberRecord> getSeeList(int pageNum,int pageSize,int companyId);

    int saveGrantRecord(GrantNumberRecord grantNumberRecord);

    int updateGrantRecord(Date date, int grantNumberRecordId);

}
