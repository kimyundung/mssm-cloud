package com.mssm.photo.service;

import com.mssm.common.domain.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {

    // 1.添加图片
    Photo add(Photo photo, MultipartFile file) throws Exception;

    // 2.查询所有
    List<Photo> queryAll();

    // 3.删除当个图片
    int deletePhoto(Photo photo) throws Exception;

    // 4.删除批量图片
    int deletePhotoList(List<Photo> photoList) throws Exception;

    // 5.修改图片信息
    void updatePhoto(Photo photo);

    // 6.获取指定资源图片列表
    List<Photo> queryBySource(String source);
}
