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

public class ItemController extends HttpServlet {
    private ItemService itemService = new ItemServiceImpl();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求字符集
        request.setCharacterEncoding("utf-8");
        //获取表单参数
        String xsbmmc = request.getParameter("xsbmmc");
        String tsfljc = request.getParameter("tsfljc");
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");
        //封装条件对象
        Condition condition = new Condition();
        condition.setBmmc(xsbmmc);
        condition.setTsfljc(tsfljc);
        condition.setStartdate(startdate);
        condition.setEnddate(enddate);
        //调用service查询
        List<Item> itemList = itemService.queryItemList(condition);
        //转向页面
        request.setAttribute("itemList", itemList);
        request.getRequestDispatcher("/item.jsp").forward(request, response);
    }
}
