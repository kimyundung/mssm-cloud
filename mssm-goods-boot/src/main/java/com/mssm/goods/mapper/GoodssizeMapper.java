package com.mssm.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mssm.common.domain.Goodssize;

public interface GoodssizeMapper extends BaseMapper<Goodssize> {
    // 添加
    void add(Goodssize goodssize);
}
