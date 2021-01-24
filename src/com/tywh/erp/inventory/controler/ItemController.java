package com.tywh.erp.inventory.controler;

import com.tywh.erp.bean.Item;
import com.tywh.erp.inventory.service.ItemService;
import com.tywh.erp.inventory.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ItemController extends HttpServlet {
    private ItemService itemService = new ItemServiceImpl();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String xsbmmc = request.getParameter("xsbmmc");
        List<Item> itemList = itemService.queryItemList(xsbmmc);
        request.setAttribute("itemList", itemList);
        request.getRequestDispatcher("/item.jsp").forward(request, response);
    }
}
