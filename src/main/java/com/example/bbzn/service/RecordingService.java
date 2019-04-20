package com.example.bbzn.service;

import com.example.bbzn.pojo.Recording;

import java.util.List;

public interface RecordingService {

    List<Recording> getRecordingList(int userId);

    int saveRecording(Recording recording);

    int deleteRecording(int recordingId);

    int updateRecording(String recordingName,int recordingId);

}
