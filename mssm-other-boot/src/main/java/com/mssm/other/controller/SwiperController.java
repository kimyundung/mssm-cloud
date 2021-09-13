package com.mssm.other.controller;

import com.mssm.common.domain.Meta;
import com.mssm.common.domain.Photo;
import com.mssm.common.domain.ResponseResult;
import com.mssm.common.domain.Swiper;
import com.mssm.other.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/swiper")
public class SwiperController {

    @Autowired
    private SwiperService swiperService;

    // 1.查询所有轮播图
    @GetMapping("/query")
    public ResponseResult queryAll(){
        ResponseResult result = new ResponseResult();

        try {
            List<Swiper> swiperList = swiperService.queryAll();
            result.setData(swiperList);
            result.setMeta(new Meta(200,"成功"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败"));
        }

        return result;
    }

    // 2.根据ID删除轮播图(数据库)
    @DeleteMapping("/delete")
    public ResponseResult deleteSwiper(Swiper swiper){
        ResponseResult result = new ResponseResult();

        try {
            int i = swiperService.deleteSwiper(swiper);
            if(i==1){
                result.setMeta(new Meta(200,"成功删除轮播图"));
            } else{
                result.setMeta(new Meta(500,"失败删除轮播图"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败删除轮播图"));
        }

        return result;
    }

    // 3.添加轮播图
    @PostMapping("/add")
    public ResponseResult addSwiper(@RequestBody Photo photo){
        ResponseResult result = new ResponseResult();

        try {
            swiperService.addSwiper(photo);

            result.setMeta(new Meta(200,"成功添加轮播图"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败添加轮播图"));
        }

        return result;
    }

    // 4.删除批量轮播图
    @PostMapping("/deleteSwiperList")
    public ResponseResult deleteSwiperList(@RequestBody List<Swiper> swiperList){
        ResponseResult result = new ResponseResult();
        try {
            int i = swiperService.deleteSwiperList(swiperList);
            if(i==1){
                result.setMeta(new Meta(200,"成功删除批量轮播图"));
            } else {
                result.setMeta(new Meta(500,"失败删除批量轮播图"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败删除批量轮播图"));
        }

        return result;
    }

    // 5.添加批量轮播图
    @PostMapping("/addSwiperList")
    public ResponseResult addSwiperList(@RequestBody List<Photo> photoList){
        //System.out.println(">>>>>>>>>>>>>>>>>>> "+ photoList);
        ResponseResult result = new ResponseResult();

        try {
            swiperService.addSwiperList(photoList);
            result.setMeta(new Meta(200,"成功"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败"));
        }

        return result;
    }

    ///////////////////////////////////////////////////////////////////////
    public ResponseResult test(){

        ResponseResult result = new ResponseResult();

        try {

            result.setMeta(new Meta(200,"成功"));
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败"));
        }

        return result;
    }
}
