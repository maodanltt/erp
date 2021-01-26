package com.tywh.erp.inventory.service.impl;

import com.tywh.erp.bean.Condition;
import com.tywh.erp.bean.Item;
import com.tywh.erp.inventory.dao.ItemDao;
import com.tywh.erp.inventory.dao.impl.ItemDaoImpl;
import com.tywh.erp.inventory.service.ItemService;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

public class ItemServiceImpl implements ItemService {
    private ItemDao itemDao = new ItemDaoImpl();
    @Override
    public List<Item> queryItemList(Condition condition) {
        List<Item> itemList = itemDao.queryItemList(condition);
        Map<String, Integer> qckcMap = itemDao.queryQckc(condition);
        Map<String, Integer> qmkcMap = itemDao.queryQmkc(condition);
        Map<String, Integer> xscsMap = itemDao.queryXscs(condition);
        for (Item item : itemList) {
            Integer qckc = qckcMap.get(item.getKey()) == null ? 0 : qckcMap.get(item.getKey());
            Integer qmkc = qmkcMap.get(item.getKey()) == null ? 0 : qmkcMap.get(item.getKey());
            Integer xscs = xscsMap.get(item.getKey()) == null ? 0 : xscsMap.get(item.getKey());
            item.setQckc(qckc);
            item.setQmkc(qmkc);
            item.setXscs(xscs);
            if (xscs <= 0) {
                item.setKczzl("0");
            } else if (xscs > 0 && ((qckc + qmkc) == 0)){
                item.setKczzl("0");
            } else if (xscs > 0 && ((qckc + qmkc) > 0)) {
                double d = (xscs.doubleValue() / (qckc.doubleValue() + qmkc.doubleValue() + xscs.doubleValue()));
                NumberFormat nf = NumberFormat.getPercentInstance();
                nf.setMinimumFractionDigits(6);
                item.setKczzl(nf.format(d));
            } else {
                item.setKczzl("0");
            }
        }
        return itemList;
    }
}
