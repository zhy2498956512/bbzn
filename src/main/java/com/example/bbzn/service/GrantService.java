package com.example.bbzn.service;

import com.example.bbzn.pojo.Grant;

import java.util.List;

public interface GrantService {

    Grant getGrant(String grantCode);

    int getGrantCode(String grantCode, List<Integer> idlist);

    int getAvailability(String grantCode,int projectId);

    int saveGrant(Grant grant);

}
