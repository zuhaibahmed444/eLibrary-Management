package com.example.demo.service.Impl;

import com.example.demo.model.AccessReq;
import com.example.demo.repo.AccessReqRepository;
import com.example.demo.service.AccessReqService;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.helper.UserException;

import java.util.List;

@Service
public class AccessReqServiceImpl implements AccessReqService {

    @Autowired
    private AccessReqRepository accessReqRepository;

    @Override
    public AccessReq saveAccessReq(AccessReq accessReq) throws Exception {
        String bookId = accessReq.getBookId();
        String userEmail = accessReq.getUserEmail();
        if (accessReqRepository.findByBookIdAndUserEmail(bookId, userEmail) != null) {
            throw new UserException("Access Request Already Exists");
        } else {
            accessReq.setReqID(Generators.timeBasedGenerator().generate().toString());
            accessReq.setActive(true);
            return accessReqRepository.save(accessReq);
        }
    }

    @Override
    public AccessReq updateAccessReq(AccessReq accessReq) {
        return accessReqRepository.save(accessReq);
    }

    @Override
    public List<AccessReq> getAllAccessReq() {
        return accessReqRepository.findAll();
    }

    @Override
    public List<AccessReq> getAllByUser(String email) {
        return accessReqRepository.findByUserEmail(email);
    }

    @Override
    public AccessReq getAccessReq(String id) {
        return accessReqRepository.findById(id).orElse(null);
    }

    @Override
    public List<AccessReq> getActiveAccessReq() {
        return accessReqRepository.findByActive(true);
    }
}

