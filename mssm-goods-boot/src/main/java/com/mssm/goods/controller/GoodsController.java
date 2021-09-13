package com.mssm.goods.controller;

import com.github.pagehelper.PageInfo;
import com.mssm.common.domain.Goods;
import com.mssm.common.domain.Meta;
import com.mssm.common.domain.QueryVO;
import com.mssm.common.domain.ResponseResult;
import com.mssm.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    // 1.分页条件查询
    @GetMapping("/query")
    public ResponseResult queryByPageAndCondition(QueryVO queryVO){
        ResponseResult result = new ResponseResult();

        try {

            PageInfo<Goods> goodsPageInfo = goodsService.queryByPageAndCondition(queryVO);
            result.setData(goodsPageInfo);
            result.setMeta(new Meta(200,"成功获取商品列表"));
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败获取商品列表"));
        }
        return result;
    }

    // 2.根据商品ID查询关联属性<颜色,尺码,分类>
    @GetMapping("/queryAttr/{gid}")
    public ResponseResult queryAttrByGid(@PathVariable Integer gid){
        ResponseResult result = new ResponseResult();

        try {
            // 获取
            Goods goods = goodsService.queryAttrByGid(gid);
            result.setData(goods);
            result.setMeta(new Meta(200,"成功获取属性信息"));
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败获取属性信息"));
        }
        return result;
    }

    // 3.保存指定商品关联属性<颜色,尺码,分类>
    @PostMapping("/saveAttr")
    public ResponseResult addAttr(@RequestBody Goods goods){
        ResponseResult result = new ResponseResult();

        try {
            //System.out.println(">>>>>>>>>>>>>>> " + goods);
            // 保存
            goodsService.saveAttr(goods);
            result.setMeta(new Meta(200,"成功"));
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败"));
        }
        return result;
    }

    // 4.修改状态
    @PutMapping("/status/{gid}/{status}")
    public ResponseResult updateStatus(@PathVariable Integer gid, @PathVariable Integer status){
        ResponseResult result = new ResponseResult();

        try {

            // 1:成功 800:属性<分类,颜色,尺码>未设置
            Integer i = goodsService.updateStatus(gid, status);
            if(i==1){
                result.setMeta(new Meta(200,"成功修改商品状态"));
            } else {
                result.setMeta(new Meta(800,"失败修改商品状态, 请先通过<属性管理>设置商品属性"));
            }
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败修改商品状态"));
        }
        return result;
    }

    // 5.根据ID删除
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteById(@PathVariable Integer id){
        ResponseResult result = new ResponseResult();

        try {
            System.out.println(">>>>>>>>>>>>>>>>>> id : " + id);
            // 删除
            goodsService.deleteById(id);
            result.setMeta(new Meta(200,"成功"));
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败"));
        }
        return result;
    }
}

//        ResponseResult result = new ResponseResult();
//
//        try {
//
//            result.setMeta(new Meta(200,"成功"));
//        } catch (Exception e){
//            e.printStackTrace();
//            result.setMeta(new Meta(500,"失败"));
//        }
//        return result;
