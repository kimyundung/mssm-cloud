package com.mssm.other.service;

import com.mssm.common.domain.Color;

import java.util.List;

public interface ColorService {
    // 1.查询所有
    List<Color> queryAll();
    // 2.删除根据ID
    int deleteById(Integer id);
    // 3.添加
    int addColor(String name);
    // 4.修改批量颜色
    void updateBatch(List<Color> colorList);
}
