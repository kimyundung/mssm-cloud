package com.mssm.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private Long id;
    private String name;
    private Integer status;
    private Double price;
    private Integer deleted;
    private String introduce;
    private Date updatetime;
    private Date createtime;
    private Date deletetime;

    private List<Goodscolor> goodscolorList;
    private List<Goodssize> goodssizeList;
    private List<Goodscate> goodscateList;
}
