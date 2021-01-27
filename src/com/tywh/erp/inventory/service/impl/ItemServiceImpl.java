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
        String ksqj = condition.getStartdate().substring(0,7);
        String jsqj = condition.getStartdate().substring(0,7);

        List<Item> itemList = itemDao.queryItemList(condition);
        Map<String, Integer> kucunMap = itemDao.queryKucun();
        for (Item item : itemList) {
            String qckcKey = item.getKey() + "-" + ksqj + "-qckc";
            String qmkcKey = item.getKey() + "-" + jsqj + "-qmkc";
            Integer qckc = kucunMap.get(qckcKey) == null ? 0 : kucunMap.get(qckcKey);
            Integer qmkc = kucunMap.get(qmkcKey) == null ? 0 : kucunMap.get(qmkcKey);
            Integer xscs = item.getXscs();
            item.setQckc(qckc);
            item.setQmkc(qmkc);
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
