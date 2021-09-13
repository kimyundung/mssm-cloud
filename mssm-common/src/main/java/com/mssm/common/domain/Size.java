package com.mssm.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Size {
    private Long id;
    private String name;
    private Integer order;
}
