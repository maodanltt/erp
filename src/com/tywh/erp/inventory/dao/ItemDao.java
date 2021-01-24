package com.tywh.erp.inventory.dao;

import com.tywh.erp.bean.Item;

import java.util.List;
import java.util.Map;

public interface ItemDao {

    //查询图书列表
    List<Item> queryItemList(String bmmc);

    //查询期初库存
    Map<String, Integer> queryQckc();

    //查询期末库存
    Map<String, Integer> queryQmkc();

    //按部门查询单品销售数据
    Map<String, Integer> queryXscs();

    //按部门查询销售总数据
    Integer queryZxscs();

}
