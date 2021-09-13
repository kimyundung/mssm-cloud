package com.mssm.photo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mssm.common.domain.Photo;

import java.util.List;

public interface PhotoMapper extends BaseMapper<Photo> {

    // 1.添加图片
    void add(Photo photo);

    // 2.修改图片
    void updatePhoto(Photo photo);

    // 3.获取指定资源图片列表
    List<Photo> queryBySource(String source);
}
