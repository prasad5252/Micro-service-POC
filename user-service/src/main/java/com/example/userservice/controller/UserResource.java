package com.example.userservice.controller;

import com.example.userservice.VO.ResponseTemplateVO;
import com.example.userservice.entity.MsUser;
import com.example.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/v1")
@Slf4j
@RefreshScope
public class UserResource {

    @Autowired
    private UserService userService;

    @Value("${test: not fetching}")
    private String test;

    @PostMapping("/")
    public MsUser saveUser(@RequestBody MsUser msUser) {
        log.info("inside saveUser");
        return userService.saveUser(msUser);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserById(@PathVariable("id") Long msUserId) {
        log.info("inside getUserById");
        return userService.findUserById(msUserId);
    }

    @GetMapping("/")
    public String getRefresh() {
        return test;
    }



}
