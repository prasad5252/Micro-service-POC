package com.example.userservice.service;

import com.example.userservice.VO.ResponseTemplateVO;
import com.example.userservice.entity.MsUser;

public interface UserService {
    MsUser saveUser(MsUser msUser);

    ResponseTemplateVO findUserById(Long msUserId);
}
