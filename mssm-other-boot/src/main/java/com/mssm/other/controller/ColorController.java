package com.mssm.other.controller;

import com.mssm.common.domain.Category;
import com.mssm.common.domain.Color;
import com.mssm.common.domain.Meta;
import com.mssm.common.domain.ResponseResult;
import com.mssm.other.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    // 1.查询所有
    @GetMapping("/query")
    public ResponseResult queryAll(){
        ResponseResult result = new ResponseResult();
        try {

            List<Color> colorList = colorService.queryAll();
            result.setData(colorList);
            result.setMeta(new Meta(200,"成功获取颜色列表"));
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败获取颜色列表"));
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
            int i = colorService.deleteById(id);
            if(i==1){
                List<Color> colorList = colorService.queryAll();
                result.setData(colorList);
                result.setMeta(new Meta(200,"成功删除颜色"));
            } else if(i==8){
                result.setMeta(new Meta(800,"使用中颜色无法删除"));
            }
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败删除颜色"));
        }
        return result;
    }

    // 3.添加
    @PostMapping("/add/{name}")
    public ResponseResult addColor(@PathVariable String name){
        ResponseResult result = new ResponseResult();
        try {
            // 1:success 0:fail
            int i = colorService.addColor(name);
            if(i==1){
                result.setData(colorService.queryAll());
                result.setMeta(new Meta(200,"成功添加颜色"));
            } else if (i==8){
                result.setData(colorService.queryAll());
                result.setMeta(new Meta(800,"颜色<"+name+">以存在"));
            } else {
                result.setMeta(new Meta(500,"失败添加颜色"));
            }
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败添加颜色"));
        }
        return result;
    }

    // 4.修改批量颜色
    @PutMapping("/updateBatch")
    public ResponseResult updateBatch(@RequestBody List<Color> colorList){
        ResponseResult result = new ResponseResult();
        try {

            // 更新
            colorService.updateBatch(colorList);
            result.setMeta(new Meta(200,"成功批量更改颜色"));
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败批量更改颜色"));
        }
        return result;
    }
}
