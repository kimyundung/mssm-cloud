package com.mssm.other.controller;

import com.mssm.common.domain.Color;
import com.mssm.common.domain.Meta;
import com.mssm.common.domain.ResponseResult;
import com.mssm.common.domain.Size;
import com.mssm.other.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/size")
public class SizeController {

    @Autowired
    private SizeService sizeService;


    // 1.查询所有
    @GetMapping("/query")
    public ResponseResult queryAll(){
        ResponseResult result = new ResponseResult();
        try {

            List<Size> sizeList = sizeService.queryAll();
            result.setData(sizeList);
            result.setMeta(new Meta(200,"成功获取尺码列表"));
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败获取尺码列表"));
        }
        return result;
    }

    // 2.删除根据ID
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteById(@PathVariable Integer id){
        ResponseResult result = new ResponseResult();
        try {

            // 删除
            // 1:success 8:外键
            int i = sizeService.deleteById(id);
            if(i==1){
                List<Size> sizeList = sizeService.queryAll();
                result.setData(sizeList);
                result.setMeta(new Meta(200,"成功删除尺码"));
            } else if(i==8){
                result.setMeta(new Meta(800,"使用中尺码, 无法删除"));
            }
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败删除尺码"));
        }
        return result;
    }

    // 3.添加
    @PostMapping("/add/{name}")
    public ResponseResult addSize(@PathVariable String name){
        ResponseResult result = new ResponseResult();
        try {
            // 1:success 0:fail
            int i = sizeService.addSize(name);
            if(i==1){
                result.setData(sizeService.queryAll());
                result.setMeta(new Meta(200,"成功添加尺码"));
            } else if (i==8){
                result.setData(sizeService.queryAll());
                result.setMeta(new Meta(800,"尺码<"+name+">以存在"));
            } else {
                result.setMeta(new Meta(500,"失败添加尺码"));
            }
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败添加尺码 "));
        }
        return result;
    }

    // 4.修改批量颜色
    @PutMapping("/updateBatch")
    public ResponseResult updateBatch(@RequestBody List<Size> sizeList){
        ResponseResult result = new ResponseResult();
        try {

            // 更新
            sizeService.updateBatch(sizeList);
            result.setMeta(new Meta(200,"成功批量更改尺码"));
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败批量更改尺码"));
        }
        return result;
    }
}
