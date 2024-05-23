package com.example.demo.service;

import com.example.demo.model.AccessReq;

import java.util.List;

public interface AccessReqService {
    AccessReq saveAccessReq(AccessReq accessReq) throws Exception;
    AccessReq getAccessReq(String id);
    List<AccessReq> getActiveAccessReq();
    AccessReq updateAccessReq(AccessReq accessReq);
    List<AccessReq> getAllAccessReq();
    List<AccessReq> getAllByUser(String email);
}

