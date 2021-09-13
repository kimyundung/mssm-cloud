package com.mssm.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goodssize {
    private Long id;
    private Integer goodsId;
    private Integer sizeId;
    private String sizeName;
}
