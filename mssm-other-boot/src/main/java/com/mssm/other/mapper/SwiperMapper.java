package com.mssm.other.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mssm.common.domain.Swiper;

public interface SwiperMapper extends BaseMapper<Swiper> {
    // 1.添加轮播图
    void addSwiper(Swiper swiper);
}
