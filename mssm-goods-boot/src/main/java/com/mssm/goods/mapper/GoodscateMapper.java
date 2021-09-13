package com.mssm.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mssm.common.domain.Goodscate;

public interface GoodscateMapper extends BaseMapper<Goodscate> {
    // 添加
    void add(Goodscate goodscate);
}