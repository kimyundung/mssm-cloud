package com.mssm.goods.service;

import com.github.pagehelper.PageInfo;
import com.mssm.common.domain.Goods;
import com.mssm.common.domain.QueryVO;

import java.util.List;

public interface GoodsService {

    // 1.分页条件查询
    PageInfo<Goods> queryByPageAndCondition(QueryVO queryVO);

    // 2.根据商品ID查询关联<颜色,尺码,分类>
    Goods queryAttrByGid(Integer gid);

    // 3.保存指定商品关联属性<颜色,尺码,分类>
    void saveAttr(Goods goods);

    // 4.修改状态
    Integer updateStatus(Integer gid, Integer status);

    // 5.根据ID删除
    void deleteById(Integer id);
}
