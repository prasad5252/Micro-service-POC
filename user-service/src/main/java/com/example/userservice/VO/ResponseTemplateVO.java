package com.example.userservice.VO;

import com.example.userservice.entity.MsUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private MsUser msUser;

    private DepartmentVO departmentVO;
}
