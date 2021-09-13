package com.mssm.photo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mssm.common.domain.Photo;
import com.mssm.photo.mapper.PhotoMapper;
import com.mssm.photo.service.PhotoService;
import com.mssm.photo.util.FastDFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    // 1.添加图片(服务器+数据库)
    @Override
    public Photo add(Photo photo, MultipartFile file) throws Exception {

        String fileID = FastDFS.uploadPhoto(file);
        photo.setFileId(fileID);
        photo.setUrl("http://106.75.253.40/"+fileID);
        photoMapper.add(photo);
        return photo;
    }

    // 2.查询所有
    @Override
    public List<Photo> queryAll() {
        QueryWrapper<Photo> wrapper = new QueryWrapper<>();
        return photoMapper.selectList(wrapper);
    }

    // 3.删除单个图片(服务器+数据库)
    @Override
    public int deletePhoto(Photo photo) throws Exception {

        int i2 = 0;
        int i1 = -1;


        // 从数据库删除 1:成功
        i2 = photoMapper.deleteById(photo.getId());
        // 从服务器中删除 0:成功
        i1 = FastDFS.deletePhoto(photo.getFileId());

        return i2==1&&i1==0 ? 1:0;
    }

    // 4.删除批量图片(服务器+数据库)
    @Override
    public int deletePhotoList(List<Photo> photoList) throws Exception {

        int i2 = -1;
        for(Photo photo : photoList){
            i2 = photoMapper.deleteById(photo.getId());
            if(i2!=1){
                return 0;
            }
        }

        int i1 = -1;
        for(Photo photo : photoList){
            i1 = FastDFS.deletePhoto(photo.getFileId());
            if(i1!=0){
                return 0;
            }
        }

        return i2==1&&i1==0 ? 1:0;
    }

    // 5.修改图片信息
    @Override
    public void updatePhoto(Photo photo) {
        photoMapper.updatePhoto(photo);
    }

    // 6.获取指定资源图片列表
    @Override
    public List<Photo> queryBySource(String source) {
        return photoMapper.queryBySource(source);
    }
}
