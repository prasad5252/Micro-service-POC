package com.example.userservice.VO;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentVO {

    private Long departmentId;

    private String departmentName;

    private String departmentAddress;

    private String departmentCode;
}
