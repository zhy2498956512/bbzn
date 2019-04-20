package com.example.bbzn.pojo;

public class SMSReturn {

    private int status;
    private String error;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "SMSReturn{" +
                "status=" + status +
                ", error='" + error + '\'' +
                '}';
    }
}
