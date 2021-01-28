<%@ page import="com.tywh.erp.bean.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tywh.erp.bean.Condition" %><%--
  Created by dingrm.
  User: everyone
  Date: 2021/1/24
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Item> itemList = (List<Item>) request.getAttribute("itemList");
    Integer zxscs = (Integer) request.getAttribute("zxscs");
    String bjbmmc = request.getParameter("bjbmmc");
    String xsbmmc = request.getParameter("xsbmmc");
    String tsfljc = request.getParameter("tsfljc");
    String startdate = request.getParameter("startdate");
    String enddate = request.getParameter("enddate");
%>
<html>
<head>
    <title>库存动销率</title>
</head>
<script type="text/javascript">

    function search() {
        var flag = check();
        if (flag) {
            document.getElementById("itemForm").submit();
        }
    }

    function check() {
        var startdate = document.getElementById("startdate");
        var enddate = document.getElementById("enddate");
        var xsbmmc = document.getElementById("xsbmmc");

        if (startdate.value == "") {
            alert("开始月份必填！");
            startdate.focus();
            return false;
        }
        if (enddate.value == "") {
            alert("结束月份必填！");
            enddate.focus();
            return false;
        }
        return true;
    }

    function init() {
        <%
            if (zxscs != null) {
         %>
        document.getElementById("zxscsspan").innerHTML = <%=zxscs%>;
        <%
            }
        %>

    }
</script>
<body onload="init()">
<br>
<form id="itemForm" action="/itemQuery" method="post">
    编辑部门名称：<input type="text" name="bjbmmc" id="bjbmmc" value="<%=bjbmmc == null ? "" : bjbmmc %>">&nbsp;&nbsp;&nbsp;
    图书分类简称：<input type="text" name="tsfljc" id="tsfljc" value="<%=tsfljc == null ? "" : tsfljc %>">&nbsp;&nbsp;&nbsp;
    销售部门名称：<input type="text" name="xsbmmc" id="xsbmmc" value="<%=xsbmmc == null ? "" : xsbmmc %>">&nbsp;&nbsp;&nbsp;
       开始月份：<input type="month" name="startdate" id="startdate" value="<%=startdate == null ? "" : startdate %>">&nbsp;&nbsp;&nbsp;
       结束月份：<input type="month" name="enddate" id="enddate" value="<%=enddate == null ? "" : enddate %>"> &nbsp;&nbsp;&nbsp;
    <input type="button" value="查询" onclick="search()">
</form>
<hr color="black">

    总销售册数：<span id="zxscsspan"></span>
    <br><br>
<table border="1" cellspacing="0" cellpadding="0" width="98%">
    <tr align="center">
        <td width="3%">序号</td>
        <td width="40%">书名</td>
        <td width="5%">定价</td>
        <td width="15%">图书分类简称</td>
        <td width="7%">期初库存</td>
        <td width="7%">期末库存</td>
        <td width="7%">销售册数</td>
        <td width="8%">库存周转率</td>
        <td width="8%">动销率</td>
    </tr>
    <%
        if (itemList != null) {
            int i = 0;
            for (Item item : itemList) {
    %>
    <tr align="center">
        <td width="3%"><%=++i%></td>
        <td width="40%"><%=item.getShum()%></td>
        <td width="5%"><%=item.getGjdj()%></td>
        <td width="15%"><%=item.getTsfljc()%></td>
        <td width="7%"><%=item.getQckc()%></td>
        <td width="7%"><%=item.getQmkc()%></td>
        <td width="7%"><%=item.getXscs()%></td>
        <td width="8%"><%=item.getKczzl()%></td>
        <td width="8%"><%=item.getKcdxl()%></td>
    </tr>
    <%
            }
        }
    %>

</table>
</body>
</html>
