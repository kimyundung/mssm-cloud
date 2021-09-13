package com.mssm.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Swiper {
    private Long id;
    private Long pid;
    private String fileId;
    private String url;
}
