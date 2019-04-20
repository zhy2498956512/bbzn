package com.example.bbzn.pojo;

public class Grant {

    /*
     * 授权码表
     * */

    private int grantId;          //授权码id
    private String grantCode;          //授权码号
    private int grantRecordId;          //授权记录id

    public int getGrantId() {
        return grantId;
    }

    public void setGrantId(int grantId) {
        this.grantId = grantId;
    }

    public String getGrantCode() {
        return grantCode;
    }

    public void setGrantCode(String grantCode) {
        this.grantCode = grantCode;
    }

    public int getGrantRecordId() {
        return grantRecordId;
    }

    public void setGrantRecordId(int grantRecordId) {
        this.grantRecordId = grantRecordId;
    }

    @Override
    public String toString() {
        return "Grant{" +
                "grantId=" + grantId +
                ", grantCode='" + grantCode + '\'' +
                ", grantRecordId=" + grantRecordId +
                '}';
    }
}
