package com.tywh.erp.inventory.service;

import com.tywh.erp.bean.Item;

import java.util.List;

public interface ItemService {

    List<Item> queryItemList(String xsbmmc);
}
