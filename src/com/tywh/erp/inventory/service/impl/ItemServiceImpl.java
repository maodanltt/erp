package com.tywh.erp.inventory.service.impl;

import com.tywh.erp.bean.Item;
import com.tywh.erp.inventory.dao.ItemDao;
import com.tywh.erp.inventory.dao.impl.ItemDaoImpl;
import com.tywh.erp.inventory.service.ItemService;

import java.util.List;
import java.util.Map;

public class ItemServiceImpl implements ItemService {
    private ItemDao itemDao = new ItemDaoImpl();
    @Override
    public List<Item> queryItemList(String xsbmmc) {
        List<Item> itemList = itemDao.queryItemList(xsbmmc);
        Map<String, Integer> qckcMap = itemDao.queryQckc();
        Map<String, Integer> qmkcMap = itemDao.queryQmkc();
        Map<String, Integer> xscsMap = itemDao.queryXscs();
        for (Item item : itemList) {
            Integer qckc = qckcMap.get(item.getKey()) == null ? 0 : qckcMap.get(item.getKey());
            Integer qmkc = qmkcMap.get(item.getKey()) == null ? 0 : qmkcMap.get(item.getKey());
            Integer xscs = xscsMap.get(item.getKey()) == null ? 0 : xscsMap.get(item.getKey());
            item.setQckc(qckc);
            item.setQmkc(qmkc);
            item.setXscs(xscs);
            if ((qckc + qmkc) != 0) {
                item.setSpzzl(xscs / (qckc + qmkc) );
            } else {
                item.setSpzzl(0);
            }
        }
        return itemList;
    }
}
