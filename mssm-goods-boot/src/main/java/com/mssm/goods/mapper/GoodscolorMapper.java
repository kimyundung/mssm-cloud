package com.mssm.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mssm.common.domain.Goodscolor;

public interface GoodscolorMapper extends BaseMapper<Goodscolor> {
    // 添加
    void add(Goodscolor goodscolor);
}
