package com.tywh.erp.inventory.service;

import com.tywh.erp.bean.Condition;
import com.tywh.erp.bean.Item;

import java.util.List;
import java.util.Map;

public interface ItemService {

    Map<String,Object> queryItem(Condition condition);
}
