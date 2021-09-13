package com.mssm.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryVO {
    private String query;
    private Integer status;
    private Integer pagenum;
    private Integer pagesize;
}
