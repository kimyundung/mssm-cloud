package com.mssm.other.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mssm.common.domain.Size;

import java.util.List;

public interface SizeMapper extends BaseMapper<Size> {
    // 1.查询所有
    List<Size> queryAll();
    // 2.添加
    void addSize(String name);
    // 3.修改
    void updateSize(Size size);
}
