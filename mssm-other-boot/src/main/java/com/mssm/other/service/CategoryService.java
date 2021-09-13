package com.mssm.other.service;

import com.mssm.common.domain.Category;

import java.util.List;

public interface CategoryService {
    // 1.查询所有
    List<Category> queryAll();

    // 2.添加
    int addCate(String name);

    // 3.更新
    void updateBatch(List<Category> categoryList);

    // 4.删除根据ID
    int deleteById(Integer id);
}
