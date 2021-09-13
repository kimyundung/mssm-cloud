package com.mssm.other.service.impl;

import com.mssm.common.domain.Color;
import com.mssm.common.domain.Size;
import com.mssm.other.mapper.SizeMapper;
import com.mssm.other.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeMapper sizeMapper;

    @Override
    public List<Size> queryAll() {
        List<Size> sizeList = sizeMapper.queryAll();
        return sizeList;
    }

    @Override
    public int deleteById(Integer id) {
        // 1:success 8:外键
        int i = 0;
        try {
            sizeMapper.deleteById(id);
            i=1;
        } catch (Exception e) {
            e.printStackTrace();
            i=8;
        }
        return i;
    }

    @Override
    public int addSize(String name) {

        int result = 0;

        // 添加
        try {
            sizeMapper.addSize(name);
            result = 1;
        } catch (Exception e) {
            result = 8;
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public void updateBatch(List<Size> sizeList) {
        for(Size size: sizeList){
            sizeMapper.updateSize(size);
        }
    }
}
