package com.example.bbzn.service;

import com.example.bbzn.pojo.Equipment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EquipmentService {

    int getEquipmentCount(List<Integer> idlist,
                          String date1,
                          String date2,
                          int equipmentType,
                          String searchEquipment);

    List<Equipment> getEquipmentList(List<Integer> idlist,
                                     String date1,
                                     String date2,
                                     int equipmentType,
                                     String searchEquipment,
                                     int pageNum,
                                     int pageSize);

    Equipment getEquipment(int equipmentId);

    List<Equipment> getProjectIdLookEquipmentList(int projectId);

    int getGrantId(int grantId);

    Equipment getGrant(int grantId);

    Equipment getGrantCode(String grantCode);

    int isItActivated(String grantCode);

    int deleteEquipment(int equipmentId);

    int updateEquipmentAppId(int equipmentId,int apkId);

    int updateEquipmentType(int grantId);

    int equipmentSwitchProject(int projectId,int equipmentId);

    int updateEquipmentLogintime(int userId);

    int saveEquipment(Equipment equipment);

}
