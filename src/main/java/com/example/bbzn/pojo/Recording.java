package com.example.bbzn.pojo;


import java.util.Date;

public class Recording {

    /*
     * 录音记录表
     * */

    private int recordingId;               //录音记录id
    private int userId;                    //用户id
    private String recordingName;          //录音名
    private Long recordingDuration;        //录音时长
    private Date recordingDate;            //录音时间
    private String recordingContent;       //录音内容
    private String filePath;                 //路径

    public int getRecordingId() {
        return recordingId;
    }

    public void setRecordingId(int recordingId) {
        this.recordingId = recordingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRecordingName() {
        return recordingName;
    }

    public void setRecordingName(String recordingName) {
        this.recordingName = recordingName;
    }

    public Long getRecordingDuration() {
        return recordingDuration;
    }

    public void setRecordingDuration(Long recordingDuration) {
        this.recordingDuration = recordingDuration;
    }

    public Date getRecordingDate() {
        return recordingDate;
    }

    public void setRecordingDate(Date recordingDate) {
        this.recordingDate = recordingDate;
    }

    public String getRecordingContent() {
        return recordingContent;
    }

    public void setRecordingContent(String recordingContent) {
        this.recordingContent = recordingContent;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Recording{" +
                "recordingId=" + recordingId +
                ", userId=" + userId +
                ", recordingName='" + recordingName + '\'' +
                ", recordingDuration=" + recordingDuration +
                ", recordingDate=" + recordingDate +
                ", recordingContent='" + recordingContent + '\'' +
                '}';
    }
}
