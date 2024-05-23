package com.example.demo.contoller;

import com.example.demo.model.AccessReq;
import com.example.demo.service.AccessReqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accessreq")
@CrossOrigin("*")
public class AccessReqController {

    @Autowired
    private AccessReqService accessReqService;

    @PostMapping("/")
    public AccessReq accessReq(@RequestBody AccessReq accessReq) throws Exception {
        System.out.println(accessReq.getBookId());
        return accessReqService.saveAccessReq(accessReq);
    }

    @GetMapping("/")
    public List<AccessReq> getAllAccessReq() {
        return accessReqService.getAllAccessReq();
    }

    @PostMapping("/update")
    public AccessReq updateAccessReq(@RequestBody AccessReq accessReq) {
        return accessReqService.updateAccessReq(accessReq);
    }

    @GetMapping("/active")
    public List<AccessReq> getActiveAccessReq() {
        return accessReqService.getActiveAccessReq();
    }

    @GetMapping("/{id}")
    public AccessReq getAccessReqById(@PathVariable String id) {
        return accessReqService.getAccessReq(id);
    }

    @PostMapping("/user")
    public List<AccessReq> getAccessReqByUser(@RequestBody String email) {
        return accessReqService.getAllByUser(email);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getReqCount() {
        Integer count = accessReqService.getActiveAccessReq().size();
        return ResponseEntity.ok(count);
    }
}
