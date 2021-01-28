package com.tywh.erp.inventory.service.impl;

import com.tywh.erp.bean.Condition;
import com.tywh.erp.bean.Item;
import com.tywh.erp.inventory.dao.ItemDao;
import com.tywh.erp.inventory.dao.impl.ItemDaoImpl;
import com.tywh.erp.inventory.service.ItemService;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemServiceImpl implements ItemService {

    private ItemDao itemDao = new ItemDaoImpl();

    @Override
    public Map<String,Object> queryItem(Condition condition) {
        String ksqj = condition.getStartdate().substring(0,7);
        String jsqj = condition.getEnddate().substring(0,7);
        Map<String, Object> retMap = new HashMap<>();
        List<Item> itemList = null;
        Integer zxscs = null;
        try {
            itemList = itemDao.queryItemList(condition);
            Map<String, Integer> kucunMap = itemDao.queryKucun();
            zxscs = itemDao.queryZxscs(condition);
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
                    item.setKcdxl("0");
                } else if (xscs > 0 && ((qckc + qmkc) == 0)){
                    double d = xscs.doubleValue() / zxscs.doubleValue() ;
                    NumberFormat nf = NumberFormat.getPercentInstance();
                    nf.setMinimumFractionDigits(2);
                    item.setKczzl("100%");
                    item.setKcdxl(nf.format(d));
                } else if (xscs > 0 && ((qckc + qmkc) > 0)) {
                    double d1 = (xscs.doubleValue() / (qckc.doubleValue() + qmkc.doubleValue() + xscs.doubleValue()));
                    double d2 = xscs.doubleValue() / zxscs.doubleValue() ;
                    NumberFormat nf = NumberFormat.getPercentInstance();
                    nf.setMinimumFractionDigits(2);
                    item.setKczzl(nf.format(d1));
                    item.setKcdxl(nf.format(d2));
                } else {
                    item.setKczzl("0");
                    item.setKcdxl("0");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        retMap.put("itemList",itemList);
        retMap.put("zxscs",zxscs);
        return retMap;
    }

}
