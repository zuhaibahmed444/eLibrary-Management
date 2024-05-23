package com.example.demo.service.Impl;

import com.example.demo.helper.CSVUtils;
import com.example.demo.model.helper.RevokeRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVServiceImpl {

    @Autowired
    private CSVUtils csvUtils;

    public List<RevokeRequestModel> uploadMultipart(MultipartFile file) throws IOException {
        List<RevokeRequestModel> ot = csvUtils.read(RevokeRequestModel.class, file.getInputStream());
        System.out.println(ot);
        return ot;
    }
}

