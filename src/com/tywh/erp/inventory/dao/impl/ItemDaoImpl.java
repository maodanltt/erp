package com.tywh.erp.inventory.dao.impl;

import com.tywh.erp.bean.Item;
import com.tywh.erp.inventory.dao.ItemDao;
import com.tywh.erp.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDaoImpl implements ItemDao {
    @Override
    public List<Item> queryItemList(String bmmc) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Item> itemList = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select shum,gjdj,tsfljc from FxsgkView where tsfljc not like '%联考%' and sxh not like '%W' ");
            if (bmmc != "") {
                sql.append("and bmmc like '%" + bmmc + "%'");
            }
            sql.append(" group by shum,gjdj, tsfljc");
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                String shum = rs.getString("shum");
                double gjdj = rs.getDouble("gjdj");
                String tsfljc = rs.getString("tsfljc");
                item.setShum(shum);
                item.setGjdj(gjdj);
                item.setTsfljc(tsfljc);
                itemList.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }

        return itemList;
    }

    @Override
    public Map<String, Integer> queryQckc() {
        String sql = "select shum,dj,tsfljc,SUM(qckc) AS qckc from kucunView where kwbh = ? group by shum,dj,tsfljc";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, Integer> qckcMap = new HashMap<>();
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,"00HG");
            rs = ps.executeQuery();
            while (rs.next()) {
                String shum = rs.getString("shum");
                double dj = rs.getDouble("dj");
                String tsfljc = rs.getString("tsfljc");
                Integer qckc = rs.getInt("qckc");
                String qckcItemName = shum + "-" + dj + "-" + tsfljc;
                qckcMap.put(qckcItemName, qckc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }
        return qckcMap;
    }

    @Override
    public Map<String, Integer> queryQmkc() {
        String sql = "select shum,dj,tsfljc,SUM(qmkc) AS qmkc from kucunView where kwbh = '00HG' group by shum,dj,tsfljc";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, Integer> qmkcMap = new HashMap<>();
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String shum = rs.getString("shum");
                double dj = rs.getDouble("dj");
                String tsfljc = rs.getString("tsfljc");
                Integer qmkc = rs.getInt("qmkc");
                String qmkcItemName = shum + "-" + dj + "-" + tsfljc;
                qmkcMap.put(qmkcItemName, qmkc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }
        return qmkcMap;
    }

    @Override
    public Map<String, Integer> queryXscs() {
//        String sql = "select shum,dj,tsfljc,sum(cs) as xscs from xsmxview where fhdbh in (select fhdbh from xsdview where fhzt in('待发','已发') and (khbh <> '2000000747')) group by shum,dj,tsfljc";
        StringBuilder sql = new StringBuilder();
        sql.append("select shum,dj,tsfljc,sum(cs) as xscs from xsmxview ");
        sql.append("where fhdbh in (select fhdbh from xsdview where fhzt in('待发','已发') and (khbh <> '2000000747') ");
        sql.append(") group by shum,dj,tsfljc");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, Integer> xscsMap = new HashMap<>();
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                String shum = rs.getString("shum");
                double dj = rs.getDouble("dj");
                String tsfljc = rs.getString("tsfljc");
                Integer xscs = rs.getInt("xscs");
                String xscsItemName = shum + "-" + dj + "-" + tsfljc;
                xscsMap.put(xscsItemName, xscs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }
        return xscsMap;
    }

    @Override
    public Integer queryZxscs() {
        return null;
    }
}
