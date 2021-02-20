package com.tywh.erp.inventory.dao;

import com.tywh.erp.bean.Condition;
import com.tywh.erp.bean.Item;

import java.util.List;
import java.util.Map;

public interface ItemDao {


    List<Item> queryItemList(Condition condition) throws Exception;


//    Map<String, Integer> queryXscs(Condition condition);


    Integer queryZxscs(Condition condition) throws Exception;


    Map<String, Integer> queryKucun() throws Exception;


}
