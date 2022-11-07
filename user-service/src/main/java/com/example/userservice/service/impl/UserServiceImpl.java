package com.example.userservice.service.impl;

import com.example.userservice.VO.DepartmentVO;
import com.example.userservice.VO.ResponseTemplateVO;
import com.example.userservice.entity.Department;
import com.example.userservice.entity.MsUser;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebClient.Builder webclient;

    @Override
    public MsUser saveUser(MsUser msUser) {
        return userRepository.save(msUser);
    }

    @Override
    @CircuitBreaker(name = "user", fallbackMethod = "fallbackGetUser")
    public ResponseTemplateVO findUserById(Long msUserId) {
        MsUser msUser = userRepository.findById(msUserId).orElse(null);
        if(msUser != null) {
            ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
            responseTemplateVO.setMsUser(msUser);
            Department department = webclient
                    .build()
                    .get()
                    .uri("http://DEPARTMENT-SERVICE/department/v1/" + msUser.getDepartmentId())
                    .retrieve()
                    .bodyToMono(Department.class)
                    .block();
            DepartmentVO departmentVO = new DepartmentVO();
            departmentVO.setDepartmentId(departmentVO.getDepartmentId());
            departmentVO.setDepartmentCode(department.getDepartmentCode());
            departmentVO.setDepartmentAddress(department.getDepartmentAddress());
            departmentVO.setDepartmentName(department.getDepartmentName());
            responseTemplateVO.setDepartmentVO(departmentVO);
            return responseTemplateVO;
        }
        return null;
    }

    private ResponseTemplateVO fallbackGetUser(Long msUserId, Exception runtimeException) {
        System.out.println("inside fallback method");
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        MsUser msUser = new MsUser();
        msUser.setUserId(0L);
        msUser.setDepartmentId(0L);
        msUser.setFirstName("this is fallback: service is down");
        msUser.setLastName("this is fallback: service is down");
        responseTemplateVO.setMsUser(msUser);
        DepartmentVO departmentVO = new DepartmentVO();
        departmentVO.setDepartmentCode("0");
        departmentVO.setDepartmentName("this is fallback: service is down");
        responseTemplateVO.setDepartmentVO(departmentVO);
        return responseTemplateVO;
    }


}
