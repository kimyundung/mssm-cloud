package com.mssm.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goodscate {
    private Long id;
    private Integer goodsId;
    private Integer cateId;
    private String cateName;
}
