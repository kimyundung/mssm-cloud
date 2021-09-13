package com.mssm.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Target;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goodscolor {
    private Long id;
    private Integer goodsId;
    private Integer colorId;
    private String colorName;
}
