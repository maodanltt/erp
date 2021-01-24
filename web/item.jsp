<%@ page import="com.tywh.erp.bean.Item" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: THINK
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
<form id="itemForm" action="/itemQuery" method="post">
    部门名称：<input type="text" name="xsbmmc" id="xsbmmc"><br>
    <input type="submit" value="查询">
</form>
<table border="1">
    <tr align="center">
        <td width="600px">书名</td>
        <td width="100px">定价</td>
        <td width="200px">图书分类简称</td>
        <td width="200px">期初库存</td>
        <td width="200px">期末库存</td>
        <td width="200px">销售册数</td>
        <td width="200px">商品周转率</td>
    </tr>
    <%
        if (itemList != null) {
            for (Item item : itemList) {
    %>
    <tr align="center">
        <td width="500px"><%=item.getShum()%></td>
        <td width="200px"><%=item.getGjdj()%></td>
        <td width="200px"><%=item.getTsfljc()%></td>
        <td width="200px"><%=item.getQckc()%></td>
        <td width="200px"><%=item.getQmkc()%></td>
        <td width="200px"><%=item.getXscs()%></td>
        <td width="200px"><%=item.getSpzzl()%></td>
    </tr>
    <%
            }
        }
    %>

</table>
</body>
</html>
