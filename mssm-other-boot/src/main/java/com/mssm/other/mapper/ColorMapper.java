package com.mssm.other.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mssm.common.domain.Color;

import java.util.List;

public interface ColorMapper extends BaseMapper<Color> {
    // 1.查询所有
    List<Color> queryAll();
    // 2.添加
    void addColor(String name);
    // 3.修改
    void updateColor(Color color);
}
