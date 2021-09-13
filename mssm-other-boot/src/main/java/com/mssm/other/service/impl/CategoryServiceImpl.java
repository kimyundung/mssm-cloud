package com.mssm.other.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mssm.common.domain.Category;
import com.mssm.other.mapper.CategoryMapper;
import com.mssm.other.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    // 1.查询所有
    @Override
    public List<Category> queryAll() {
        List<Category> categoryList = categoryMapper.queryAll();
        return categoryList;
    }

    // 2.添加
    @Override
    public int addCate(String name) {
        int result = 0;

        // 添加
        try {
            categoryMapper.addCate(name);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
            result = 8;
        }
        return  result;
    }

    // 3.更新
    @Override
    public void updateBatch(List<Category> categoryList) {
        for(Category category: categoryList){
            categoryMapper.update(category);
        }
    }

    // 4.删除根据ID
    @Override
    public int deleteById(Integer id) {
        // 1:success 8:外键
        int i = 0;
        try {
            categoryMapper.deleteById(id);
            i=1;
        } catch (Exception e) {
            e.printStackTrace();
            i=8;
        }
        return i;

    }
}
