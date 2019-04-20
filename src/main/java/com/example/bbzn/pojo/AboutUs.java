package com.example.bbzn.pojo;

public class AboutUs {

    /*
     * 关于我们表
     * */

    private int aboutUsId;                  //id
    private String aboutUsContent;          //内容
    private String aboutUsImg;              //图片

    public int getAboutUsId() {
        return aboutUsId;
    }

    public void setAboutUsId(int aboutUsId) {
        this.aboutUsId = aboutUsId;
    }

    public String getAboutUsContent() {
        return aboutUsContent;
    }

    public void setAboutUsContent(String aboutUsContent) {
        this.aboutUsContent = aboutUsContent;
    }

    public String getAboutUsImg() {
        return aboutUsImg;
    }

    public void setAboutUsImg(String aboutUsImg) {
        this.aboutUsImg = aboutUsImg;
    }

    @Override
    public String toString() {
        return "AboutUs{" +
                "aboutUsId=" + aboutUsId +
                ", aboutUsContent='" + aboutUsContent + '\'' +
                ", aboutUsImg='" + aboutUsImg + '\'' +
                '}';
    }
}
