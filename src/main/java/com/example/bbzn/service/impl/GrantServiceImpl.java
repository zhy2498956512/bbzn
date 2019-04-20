package com.example.bbzn.service.impl;

import com.example.bbzn.dao.GrantDao;
import com.example.bbzn.pojo.Grant;
import com.example.bbzn.service.GrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrantServiceImpl implements GrantService {

    @Autowired
    private GrantDao grantDao;

    public Grant getGrant(String grantCode){
        return grantDao.getGrant(grantCode);
    }

    public int getGrantCode(String grantCode, List<Integer> idlist){
        return grantDao.getGrantCode(grantCode,idlist);
    }

    public int getAvailability(String grantCode,int projectId){
        return grantDao.getAvailability(grantCode,projectId);
    }

    public int saveGrant(Grant grant){
        return grantDao.saveGrant(grant);
    }
}
