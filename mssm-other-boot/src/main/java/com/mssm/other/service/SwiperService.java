package com.mssm.other.service;

import com.mssm.common.domain.Photo;
import com.mssm.common.domain.Swiper;

import java.util.List;

public interface SwiperService {
    // 1.查询所有
    List<Swiper> queryAll();

    // 2.根据ID删除轮播图(数据库)
    int deleteSwiper(Swiper swiper);

    // 3.添加轮播图
    void addSwiper(Photo photo);

    // 4.删除批量轮播图
    int deleteSwiperList(List<Swiper> swiperList);

    // 5.添加批量轮播图
    void addSwiperList(List<Photo> photoList);
}
