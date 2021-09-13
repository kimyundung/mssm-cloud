package com.mssm.other.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mssm.common.domain.Category;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {

    // 1.查询所有
    List<Category> queryAll();

    // 2.添加
    void addCate(String name);

    // 3.检查是否有相同
    int checkName(String name);

    // 4.更新
    void update(Category category);
}
