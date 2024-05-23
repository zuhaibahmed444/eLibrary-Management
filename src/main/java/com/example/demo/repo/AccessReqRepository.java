package com.example.demo.repo;

import com.example.demo.model.AccessReq;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessReqRepository extends MongoRepository<AccessReq,String> {
    List<AccessReq> findByActive(Boolean active);
    AccessReq findByBookIdAndUserEmail(String bookId,String userEmail);
    List<AccessReq> findByUserEmail(String userEmail);
}
