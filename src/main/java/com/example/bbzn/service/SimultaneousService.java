package com.example.bbzn.service;

import com.example.bbzn.pojo.Simultaneous;

import java.util.List;

public interface SimultaneousService {

    List<Simultaneous> getSimultaneousList(int userId);

    int saveSimultaneous(Simultaneous simultaneous);

    int deleteSimultaneous(int simultaneousId);

}
