package com.mssm.photo.controller;

import com.mssm.common.domain.Meta;
import com.mssm.common.domain.Photo;
import com.mssm.common.domain.ResponseResult;
import com.mssm.photo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    // 1.图片上传到FastDFS服务器, 图片信息保存到数据库
    @PostMapping("/upload")
    public ResponseResult upload(Photo photo, @RequestParam("file") MultipartFile file){
        ResponseResult result = new ResponseResult();
        try {

            photo = photoService.add(photo,file);
            result.setData(photo);
            result.setMeta(new Meta(200, "成功上传图片"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500, "失败上传图片"));
        }

        return result;
    }

    // 2.查询所有图片
    @GetMapping("/query")
    public ResponseResult queryAll(){
        ResponseResult result = new ResponseResult();

        try {
            List<Photo> photoList = photoService.queryAll();
            result.setData(photoList);
            result.setMeta(new Meta(200,"成功查询所有图片"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败查询所有图片"));
        }
        return result;
    }

    // 3.删除单个图片(FastDFS+数据库)
    @DeleteMapping("/delete")
    public ResponseResult deletePhoto(Photo photo){
        ResponseResult result = new ResponseResult();

        try {

            int i = photoService.deletePhoto(photo);

            if(i==1){
                result.setMeta(new Meta(200,"成功删除图片"));
            } else if(i==2) {
                result.setMeta(new Meta(500,"失败删除图片, "+photo.getSource()+"在使用该图片"));
            } else {
                result.setMeta(new Meta(500,"失败删除图片"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败删除图片"));
        }

        return result;
    }

    // 4.删除批量图片(FastDFS+数据库)
    @PostMapping("/deletePhotoList")
    public ResponseResult deletePhotoList(@RequestBody List<Photo> photoList){
        ResponseResult result = new ResponseResult();

        try {

            int i = photoService.deletePhotoList(photoList);
            if(i==1){
                result.setMeta(new Meta(200,"成功删除批量图片"));
            } else {
                result.setMeta(new Meta(500,"失败删除批量图片"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败删除批量图片"));
        }

        return result;
    }

    // 5.更新图片信息
    @PutMapping("/update")
    public ResponseResult updatePhoto(@RequestBody Photo photo){
        ResponseResult result = new ResponseResult();

        try {
            photoService.updatePhoto(photo);
            result.setMeta(new Meta(200,"成功修改图片信息"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败修改图片信息"));
        }

        return result;
    }

    // 6.获取指定资源图片列表
    @GetMapping("/query/{source}")
    public ResponseResult queryBySource(@PathVariable String source){
        ResponseResult result = new ResponseResult();

        try {
            List<Photo> photoList = photoService.queryBySource(source);
            result.setData(photoList);
            result.setMeta(new Meta(200,"成功获取图片信息"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败获取图片信息"));
        }

        return result;
    }

}
