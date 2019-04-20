package com.example.bbzn.util;

public enum ResponseErrorCode {
    NET_ERROR("系统异常", 0),
    SUCCESS("SUCCESS",1),
    NET_ERROR_SMSCODE("验证码错误",2),
    NO_DATA("暂无数据",3),
    NO_TOKEN("用户验证失败",4),
    NO_REGISTER("用户注册失败",5),
    UPDATE_FAIL("修改失败",6),
    UPDATE_CODE("授权码不存在",7),
    UPDATE_PHONE("该手机已使用",8),
    UNKOWN("未知错误",9999);


    private String name;
    private int index;

    ResponseErrorCode(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static String getStringByIndex(int index){
        if(index==SUCCESS.getIndex()){
            return SUCCESS.getName();
        }else if(index==NET_ERROR.getIndex()){
            return NET_ERROR.getName();
        }else if(index==NET_ERROR_SMSCODE.getIndex()){
            return NET_ERROR_SMSCODE.getName();
        }else if(index==NO_DATA.getIndex()){
            return NO_DATA.getName();
        }else if(index==NO_TOKEN.getIndex()){
            return NO_TOKEN.getName();
        }else if(index==UPDATE_FAIL.getIndex()){
            return UPDATE_FAIL.getName();
        }else if(index==UNKOWN.getIndex()){
            return UNKOWN.getName();
        }
        return "";
    }
}
