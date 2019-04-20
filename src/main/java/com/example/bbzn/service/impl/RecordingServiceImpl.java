package com.example.bbzn.service.impl;

import com.example.bbzn.dao.RecordingDao;
import com.example.bbzn.pojo.Recording;
import com.example.bbzn.service.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordingServiceImpl implements RecordingService {

    @Autowired
    private RecordingDao recordingDao;

    public List<Recording> getRecordingList(int userId){
        return recordingDao.getRecordingList(userId);
    }
    public int saveRecording(Recording recording){
        return recordingDao.saveRecording(recording);
    }

    public int deleteRecording(int recordingId){
        return recordingDao.deleteRecording(recordingId);
    }

    public int updateRecording(String recordingName,int recordingId){
        return recordingDao.updateRecording(recordingName,recordingId);
    }

}
