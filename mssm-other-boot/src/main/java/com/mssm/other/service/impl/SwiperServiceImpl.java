package com.mssm.other.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mssm.common.domain.Photo;
import com.mssm.common.domain.Swiper;
import com.mssm.other.feign.PhotoFeign;
import com.mssm.other.mapper.SwiperMapper;
import com.mssm.other.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwiperServiceImpl implements SwiperService {

    @Autowired
    private SwiperMapper swiperMapper;
    @Autowired
    private PhotoFeign photoFeign;

    // 1.查询所有
    @Override
    public List<Swiper> queryAll() {
        QueryWrapper<Swiper> wrapper = new QueryWrapper<>();
        return swiperMapper.selectList(wrapper);
    }

    // 2.根据ID删除轮播图(数据库)
    @Override
    public int deleteSwiper(Swiper swiper) {
        int i = swiperMapper.deleteById(swiper.getId());
        if(i==1){
            // 修改图片信息
            Photo photo = new Photo();
            photo.setId(swiper.getPid());
            photo.setFid(null);
            photoFeign.updatePhoto(photo);
        }
        return i;
    }

    // 3.添加轮播图
    @Override
    public void addSwiper(Photo photo) {
        Swiper swiper = new Swiper(null,photo.getId(),photo.getFileId(),photo.getUrl());
        swiperMapper.addSwiper(swiper);
        photo.setFid(swiper.getId());
        photoFeign.updatePhoto(photo);
    }

    // 4.删除批量轮播图
    @Override
    public int deleteSwiperList(List<Swiper> swiperList) {
        // 删除(数据库)
        for(Swiper swiper: swiperList){
            int i = swiperMapper.deleteById(swiper.getId());
            if(i!=1){
                return 0;
            }
        }
        // 修改图片信息
        for(Swiper swiper: swiperList){
            // 修改图片信息
            Photo photo = new Photo();
            photo.setId(swiper.getPid());
            photo.setFid(null);
            photoFeign.updatePhoto(photo);
        }
        return 1;
    }

    // 5.添加批量轮播图
    @Override
    public void addSwiperList(List<Photo> photoList) {
        for(Photo photo : photoList){
            Swiper swiper = new Swiper(null,photo.getId(),photo.getFileId(),photo.getUrl());
            swiperMapper.addSwiper(swiper);
            photo.setFid(swiper.getId());
            photoFeign.updatePhoto(photo);
        }
    }
}
