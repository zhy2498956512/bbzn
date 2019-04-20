package com.example.bbzn.service;

import com.example.bbzn.pojo.PushRecord;

import java.util.List;

public interface PushRecordService {

    int getPushRecordCount(int projectId,int equipmentId);

    List<PushRecord> getPushRecordList(int pageNum, int pageSize, int projectId, int equipmentId);

    int savePushRecord(PushRecord pushRecord);

}
