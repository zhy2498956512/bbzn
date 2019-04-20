package com.example.bbzn.pojo;

public class EquipmentLanguage {

    private int equipmentLanguageId;          //设备语言id
    private int equipmentId;                   //设备id
    private int languageId;                    //语言id

    private String languageName;                //语言名称

    public int getEquipmentLanguageId() {
        return equipmentLanguageId;
    }

    public void setEquipmentLanguageId(int equipmentLanguageId) {
        this.equipmentLanguageId = equipmentLanguageId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public String toString() {
        return "EquipmentLanguage{" +
                "equipmentLanguageId=" + equipmentLanguageId +
                ", equipmentId=" + equipmentId +
                ", languageId=" + languageId +
                ", languageName='" + languageName + '\'' +
                '}';
    }
}
