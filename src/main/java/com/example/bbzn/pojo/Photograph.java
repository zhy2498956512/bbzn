package com.example.bbzn.pojo;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

public class Photograph {

    /*
     * 拍照翻译记录表
     * */

    private int photographId;              //拍照翻译记录id
    private int userId;                    //用户id
    /*private String photographImg;          //图片路径*/
    private String photographContent;      //翻译内容
    private String photographResult;       //拍照结果
    private Date photographDate;           //翻译时间
    /*private byte[] imgBinary ;             //图片二进制*/

    public int getPhotographId() {
        return photographId;
    }

    public void setPhotographId(int photographId) {
        this.photographId = photographId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /*public String getPhotographImg() {
        return photographImg;
    }

    public void setPhotographImg(String photographImg) {
        this.photographImg = photographImg;
    }*/

    public String getPhotographContent() {
        return photographContent;
    }

    public void setPhotographContent(String photographContent) {
        this.photographContent = photographContent;
    }

    public Date getPhotographDate() {
        return photographDate;
    }

    public void setPhotographDate(Date photographDate) {
        this.photographDate = photographDate;
    }

/*    public byte[] getImgBinary() {
        return imgBinary;
    }

    public void setImgBinary(byte[] imgBinary) {
        this.imgBinary = imgBinary;
    }*/

    public String getPhotographResult() {
        return photographResult;
    }

    public void setPhotographResult(String photographResult) {
        this.photographResult = photographResult;
    }

    @Override
    public String toString() {
        return "Photograph{" +
                "photographId=" + photographId +
                ", userId=" + userId +
                ", photographContent='" + photographContent + '\'' +
                '}';
    }
}
