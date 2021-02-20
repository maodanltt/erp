package com.tywh.erp.inventory.controler;

import com.tywh.erp.bean.Condition;
import com.tywh.erp.bean.Item;
import com.tywh.erp.inventory.service.ItemService;
import com.tywh.erp.inventory.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ItemController extends HttpServlet {
    private ItemService itemService = new ItemServiceImpl();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        String bjbmmc = request.getParameter("bjbmmc");
        String xsbmmc = request.getParameter("xsbmmc");
        String tsfljc = request.getParameter("tsfljc");
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");

        Condition condition = new Condition();
        condition.setBjbmmc(bjbmmc);
        condition.setXsbmmc(xsbmmc);
        condition.setTsfljc(tsfljc);
        condition.setStartdate(startdate);
        condition.setEnddate(enddate);
        request.setAttribute("condition",condition);

        Map<String,Object> retMap = itemService.queryItem(condition);
        List<Item> itemList = (List<Item>) retMap.get("itemList");
        Integer zxscs = (Integer) retMap.get("zxscs");
        String zkczzl = (String) retMap.get("zkczzl");
        Integer zqckc = (Integer) retMap.get("zqckc");
        Integer zqmkc = (Integer) retMap.get("zqmkc");

        request.setAttribute("itemList", itemList);
        request.setAttribute("zxscs", zxscs);
        request.setAttribute("zkczzl", zkczzl);
        request.setAttribute("zqckc", zqckc);
        request.setAttribute("zqmkc", zqmkc);
        request.getRequestDispatcher("/item.jsp").forward(request, response);
    }
}
