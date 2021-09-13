package com.mssm.other.service;

import com.mssm.common.domain.Color;
import com.mssm.common.domain.Size;

import java.util.List;

public interface SizeService {
    // 1.查询所有
    List<Size> queryAll();
    // 2.删除根据ID
    int deleteById(Integer id);
    // 3.添加
    int addSize(String name);
    // 4.修改批量颜色
    void updateBatch(List<Size> sizeList);
}
