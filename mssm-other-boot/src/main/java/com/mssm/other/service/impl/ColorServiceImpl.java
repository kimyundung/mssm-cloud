package com.mssm.other.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mssm.common.domain.Color;
import com.mssm.other.mapper.ColorMapper;
import com.mssm.other.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorMapper colorMapper;

    // 1.查询所有
    @Override
    public List<Color> queryAll() {
        List<Color> colorList = colorMapper.queryAll();
        return colorList;
    }

    // 2.删除根据ID
    @Override
    public int deleteById(Integer id) {
        // 1:success 8:外键
        int i = 0;
        try {
            colorMapper.deleteById(id);
            i=1;
        } catch (Exception e) {
            e.printStackTrace();
            i=8;
        }
        return i;
    }

    // 3.添加
    @Override
    public int addColor(String name) {

        int result = 0;

        // 添加
        try {
            colorMapper.addColor(name);
            result = 1;
        } catch (Exception e) {
            result = 8;
            e.printStackTrace();
        }
        return  result;
    }

    // 4.修改批量颜色
    @Override
    public void updateBatch(List<Color> colorList) {
        for(Color color: colorList){
            colorMapper.updateColor(color);
        }
    }
}
