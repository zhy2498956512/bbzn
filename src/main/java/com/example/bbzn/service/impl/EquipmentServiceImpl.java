package com.example.bbzn.service.impl;

import com.example.bbzn.dao.EquipmentDao;
import com.example.bbzn.pojo.Equipment;
import com.example.bbzn.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentDao equipmentDao;

    public int getEquipmentCount(List<Integer> idlist,
                                 String date1,
                                 String date2,
                                 int equipmentType,
                                 String searchEquipment){
        return equipmentDao.getEquipmentCount(idlist,date1,date2,equipmentType,searchEquipment);
    }

    public List<Equipment> getEquipmentList(List<Integer> idlist,
                                            String date1,
                                            String date2,
                                            int equipmentType,
                                            String searchEquipment,
                                            int pageNum,
                                            int pageSize){
        return equipmentDao.getEquipmentList(idlist,date1,date2,equipmentType,searchEquipment,(pageNum-1)*pageSize,pageSize);
    }

    public Equipment getEquipment(int equipmentId){
        return equipmentDao.getEquipment(equipmentId);
    }

    public List<Equipment> getProjectIdLookEquipmentList(int projectId){
        return equipmentDao.getProjectIdLookEquipmentList(projectId);
    }

    public int getGrantId(int grantId){
        return equipmentDao.getGrantId(grantId);
    }

    public Equipment getGrant(int grantId){
        return equipmentDao.getGrant(grantId);
    }

    public Equipment getGrantCode(String grantCode){
        return equipmentDao.getGrantCode(grantCode);
    }

    public int isItActivated(String grantCode){
        return equipmentDao.isItActivated(grantCode);
    }

    public int deleteEquipment(int equipmentId){
        return equipmentDao.deleteEquipment(equipmentId);
    }

    public int updateEquipmentAppId(int equipmentId,int apkId){
        return equipmentDao.updateEquipmentAppId(equipmentId,apkId);
    }

    public int updateEquipmentType(int grantId){
        return equipmentDao.updateEquipmentType(grantId);
    }

    public int equipmentSwitchProject(int projectId,int equipmentId){
        return equipmentDao.equipmentSwitchProject(projectId,equipmentId);
    }

    public int updateEquipmentLogintime(int userId){
        return equipmentDao.updateEquipmentLogintime(userId);
    }

    public int saveEquipment(Equipment equipment){
        return equipmentDao.saveEquipment(equipment);
    }

}
