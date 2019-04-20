package com.example.bbzn.service;

import com.example.bbzn.pojo.GrantRecord;

import java.util.List;

public interface GrantRecordService {

    List<GrantRecord> getGrantRecordList(int companyId);

    GrantRecord getGrantRecord(int grant_record_id);

    List<GrantRecord> getGrantRecordRuleList(int grantRecordLength,String grantRecordBinary);

    int saveGrantRecord(GrantRecord grantRecord);

    int saveGrantRecordFile(GrantRecord grantRecord);

}
