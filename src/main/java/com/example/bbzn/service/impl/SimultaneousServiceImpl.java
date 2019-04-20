package com.example.bbzn.service.impl;

import com.example.bbzn.dao.SimultaneousDao;
import com.example.bbzn.pojo.Simultaneous;
import com.example.bbzn.service.SimultaneousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimultaneousServiceImpl implements SimultaneousService {

    @Autowired
    private SimultaneousDao simultaneousDao;

    public List<Simultaneous> getSimultaneousList(int userId){
        return simultaneousDao.getSimultaneousList(userId);
    }

    public int saveSimultaneous(Simultaneous simultaneous){
        return simultaneousDao.saveSimultaneous(simultaneous);
    }

    public int deleteSimultaneous(int simultaneousId){
        return simultaneousDao.deleteSimultaneous(simultaneousId);
    }

}
