package com.mssm.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mssm.common.domain.*;
import com.mssm.goods.mapper.GoodscateMapper;
import com.mssm.goods.mapper.GoodscolorMapper;
import com.mssm.goods.mapper.GoodsMapper;
import com.mssm.goods.mapper.GoodssizeMapper;
import com.mssm.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodscolorMapper goodscolorMapper;
    @Autowired
    private GoodssizeMapper goodssizeMapper;
    @Autowired
    private GoodscateMapper goodscateMapper;

    // 1.分页条件查询
    @Override
    public PageInfo<Goods> queryByPageAndCondition(QueryVO queryVO) {
        // 分页
        PageHelper.startPage(queryVO.getPagenum(),queryVO.getPagesize());
        List<Goods> goodsList = goodsMapper.queryByCondition(queryVO.getQuery(), queryVO.getStatus());
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
        return goodsPageInfo;
    }

    // 2.根据商品ID查询关联<颜色,尺码,分类>
    @Override
    public Goods queryAttrByGid(Integer gid) {
        QueryWrapper<Goodscolor> wrapperColor = new QueryWrapper<>();
        QueryWrapper<Goodssize> wrapperSize = new QueryWrapper<>();
        QueryWrapper<Goodscate> wrapperCate = new QueryWrapper<>();
        wrapperColor.eq("goods_id", gid);
        wrapperSize.eq("goods_id", gid);
        wrapperCate.eq("goods_id", gid);
        List<Goodscolor> goodscolorList = goodscolorMapper.selectList(wrapperColor);
        List<Goodssize> goodssizeList = goodssizeMapper.selectList(wrapperSize);
        List<Goodscate> goodscateList = goodscateMapper.selectList(wrapperCate);
        Goods goods = new Goods();
        goods.setId((long) gid);
        goods.setGoodscolorList(goodscolorList);
        goods.setGoodssizeList(goodssizeList);
        goods.setGoodscateList(goodscateList);
        return goods;
    }

    // 3.保存指定商品关联属性<颜色,尺码,分类>
    @Override
    public void saveAttr(Goods goods) {
        // 1.判断是否为空
        // 2.大于0时进行保存操作
        // 3.保存操作: 先删除再插入

        // cate
        if(goods.getGoodscateList().size()>0){
            // delete
            QueryWrapper<Goodscate> wrapperCate = new QueryWrapper();
            wrapperCate.eq("goods_id", goods.getId());
            goodscateMapper.delete(wrapperCate);
            // insert
            for(Goodscate goodscate: goods.getGoodscateList()){
                goodscateMapper.add(goodscate);
            }
        }

        // color
        if(goods.getGoodscolorList().size()>0){
            // delete
            QueryWrapper<Goodscolor> wrapperColor = new QueryWrapper();
            wrapperColor.eq("goods_id", goods.getId());
            goodscolorMapper.delete(wrapperColor);
            for(Goodscolor goodscolor: goods.getGoodscolorList()){

                goodscolorMapper.add(goodscolor);
            }
        }

        // size
        if(goods.getGoodssizeList().size()>0){
            // delete
            QueryWrapper<Goodssize> wrapperSize = new QueryWrapper();
            wrapperSize.eq("goods_id", goods.getId());
            goodssizeMapper.delete(wrapperSize);
            for(Goodssize goodssize: goods.getGoodssizeList()){
                goodssizeMapper.add(goodssize);
            }
        }
    }

    // 4.修改状态
    @Override
    public Integer updateStatus(Integer gid, Integer status) {
        // 上架: 没有属性,不能上架
        // 下架: 无要求

        Integer result = 0;

        // 下架
        if(status == 0){
            goodsMapper.updateStatus(gid, status);
            result = 1;
        }

        // 上架
        else {
            QueryWrapper<Goodscate> wrapperCate = new QueryWrapper<>();
            wrapperCate.eq("goods_id",gid);
            Integer countCate = goodscateMapper.selectCount(wrapperCate);
            System.out.println("----------------- countCate: " + countCate);
            if(countCate<=0) {
                result=800;
                return result;
            }

            QueryWrapper<Goodscolor> wrapperColor = new QueryWrapper<>();
            wrapperColor.eq("goods_id",gid);
            Integer countColor = goodscolorMapper.selectCount(wrapperColor);
            System.out.println("----------------- countColor: " + countColor);
            if(countColor<=0) {
                result=800;
                return result;
            }

            QueryWrapper<Goodssize> wrapperSize = new QueryWrapper<>();
            wrapperSize.eq("goods_id",gid);
            Integer countSize = goodssizeMapper.selectCount(wrapperSize);
            System.out.println("----------------- countSize: " + countSize);
            if(countSize<=0) {
                result=800;
                return result;
            }

            // 执行到这里, 表示属性健全, 上架
            goodsMapper.updateStatus(gid, status);
            result = 1;
        }
        return result;
    }

    // 5.根据ID删除
    @Override
    public void deleteById(Integer id) {
        // 关联的也要删除: 属性, 库存
        // 逻辑删除商品

        // 物理删除属性

        // 物理删除库存

    }
}
