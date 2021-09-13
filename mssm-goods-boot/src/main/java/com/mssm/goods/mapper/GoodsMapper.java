package com.mssm.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mssm.common.domain.Goods;
import com.mssm.common.domain.QueryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods>{
    // 1.条件查询
    List<Goods> queryByCondition(@Param("name") String name, @Param("status") Integer status);
    // 2.更新状态
    void updateStatus(@Param("id") Integer id, @Param("status") Integer status);
}
