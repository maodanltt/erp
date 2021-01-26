<%@ page import="com.tywh.erp.bean.Item" %>
<%@ page import="java.util.List" %><%--
  Created by dingrm.
  User: everyone
  Date: 2021/1/24
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Item> itemList = (List<Item>) request.getAttribute("itemList");
%>
<html>
<head>
    <title>库存动销率</title>
</head>
<body>
<br>
<form id="itemForm" action="/itemQuery" method="post">
    部门名称：<input type="text" name="xsbmmc" id="xsbmmc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    图书分类简称：<input type="text" name="tsfljc" id="tsfljc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    开始日期：<input type="date" name="startdate" id="startdate"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    结束日期：<input type="date" name="enddate" id="enddate">
    <input type="submit" value="查询">
</form>
<hr color="black">
<table border="1" cellspacing="0" cellpadding="0" width="100%">
    <tr align="center">
        <td width="3%">序号</td>
        <td width="40%">书名</td>
        <td width="7%">定价</td>
        <td width="10%">图书分类简称</td>
        <td width="10%">期初库存</td>
        <td width="10%">期末库存</td>
        <td width="10%">销售册数</td>
        <td width="10%">库存周转率</td>
    </tr>
    <%
        if (itemList != null) {
            int i = 0;
            for (Item item : itemList) {
    %>
    <tr align="center">
        <td width="3%"><%=++i%></td>
        <td width="40%"><%=item.getShum()%></td>
        <td width="7%"><%=item.getGjdj()%></td>
        <td width="10%"><%=item.getTsfljc()%></td>
        <td width="10%"><%=item.getQckc()%></td>
        <td width="10%"><%=item.getQmkc()%></td>
        <td width="10%"><%=item.getXscs()%></td>
        <td width="10%"><%=item.getKczzl()%></td>
    </tr>
    <%
            }
        }
    %>

</table>
</body>
</html>
