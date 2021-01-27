package com.tywh.erp.inventory.dao;

import com.tywh.erp.bean.Condition;
import com.tywh.erp.bean.Item;

import java.util.List;
import java.util.Map;

public interface ItemDao {

    //查询图书列表
    List<Item> queryItemList(Condition condition);

//    //按部门查询单品销售数据
//    Map<String, Integer> queryXscs(Condition condition);

    //按部门查询销售总数据
    Integer queryZxscs(Condition condition);

    //按书名 图书分类简称 定价 期间 汇总各个期间期初、期末库存
    Map<String, Integer> queryKucun();


}
