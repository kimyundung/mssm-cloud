package com.mssm.other.controller;

import com.mssm.common.domain.Category;
import com.mssm.common.domain.Meta;
import com.mssm.common.domain.ResponseResult;
import com.mssm.other.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cate")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 1.获取所有
    @GetMapping("/query")
    public ResponseResult queryAll(){
        ResponseResult result = new ResponseResult();
        try {
            // 查询
            List<Category> categoryList = categoryService.queryAll();
            result.setData(categoryList);
            result.setMeta(new Meta(200,"成功"));
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败"));
        }
        return result;
    }

    // 2.添加
    @PostMapping("/add/{name}")
    public ResponseResult addCate(@PathVariable String name){
    ResponseResult result = new ResponseResult();
        try {
            // 1:success 8:重复 0:fail
            int i = categoryService.addCate(name);
            if(i==1){
                result.setData(categoryService.queryAll());
                result.setMeta(new Meta(200,"成功添加分类"));
            } else if(i==8){
                result.setData(categoryService.queryAll());
                result.setMeta(new Meta(800,"分类<"+name+">以存在"));
            } else {
                result.setMeta(new Meta(500,"失败"));
            }
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败"));
        }
        return result;
    }

    // 3.批量修改
    @PutMapping("/updateBatch")
    public ResponseResult updateBatch(@RequestBody List<Category> categoryList){
        ResponseResult result = new ResponseResult();
        try {

            // 更新
            categoryService.updateBatch(categoryList);
            result.setMeta(new Meta(200,"成功批量更改分类"));
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败批量更改分类"));
        }
        return result;
    }

    // 4.删除根据ID
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteById(@PathVariable Integer id){
        ResponseResult result = new ResponseResult();
        try {

            // 删除
            int i = categoryService.deleteById(id);
            if(i==1){
                List<Category> categoryList = categoryService.queryAll();
                result.setData(categoryList);
                result.setMeta(new Meta(200,"成功删除"));
            }else if(i==8){
                result.setMeta(new Meta(800,"使用中分类无法删除"));
            } else {
                result.setMeta(new Meta(500,"失败"));
            }
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败"));
        }
        return result;
    }

//        ResponseResult result = new ResponseResult();
//        try {
//            System.out.println(">>>>>>>>>>>>>> cate : ");
//            result.setMeta(new Meta(200,"成功"));
//        } catch (Exception e){
//            e.printStackTrace();
//            result.setMeta(new Meta(500,"失败"));
//        }
//        return result;
}
