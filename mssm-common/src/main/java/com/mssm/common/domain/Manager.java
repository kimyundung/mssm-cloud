package com.mssm.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    private Integer id;
    private String name;
    private String phone;
    private String portrait;
    private String password;
    private Integer status;
    private Integer locked;
    private String createtime;
    private String updatetime;
}
