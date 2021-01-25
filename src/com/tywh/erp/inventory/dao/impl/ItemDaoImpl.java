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
            StringBuilder sql = new StringBuilder("select a.shum,a.gjdj,a.tsfljc,b.xscs from FxsgkView a left join ");
            /*
            select a.shum,a.gjdj,a.tsfljc,b.xscs from FxsgkView a
left join (select shum,dj,tsfljc,sum(cs) as xscs from xsmxview where fhdbh in (select fhdbh from xsdview where fhzt in('待发','已发') and (khbh <> '2000000747') ) group by shum,dj,tsfljc) b
on a.shum = b.shum and a.gjdj = b.dj and a.tsfljc = b.tsfljc
where a.tsfljc not like '%联考%' and sxh not like '%W%' group by a.shum,gjdj, a.tsfljc,b.xscs order by b.xscs desc
             */
            sql.append("(select shum,dj,tsfljc,sum(cs) as xscs from xsmxview where fhdbh in ");
            sql.append("(select fhdbh from xsdview where fhzt in(?, ?) and (khbh <> ?) ) group by shum,dj,tsfljc) b ");
            sql.append("on a.shum = b.shum and a.gjdj = b.dj and a.tsfljc = b.tsfljc ");
            sql.append("where a.tsfljc not like ? and a.sxh not like ? ");
            if (bmmc != "") {
                sql.append("and bmmc like ?");
            }
            sql.append(" group by a.shum,a.gjdj, a.tsfljc,b.xscs order by b.xscs desc ");
//            String sql = "select a.shum,a.gjdj,a.tsfljc,b.xscs from FxsgkView a left join (select shum,dj,tsfljc,sum(cs) as xscs from xsmxview where fhdbh in (select fhdbh from xsdview where fhzt in('待发','已发') and (khbh <> '2000000747') ) group by shum,dj,tsfljc) b on a.shum = b.shum and a.gjdj = b.dj and a.tsfljc = b.tsfljc where a.tsfljc not like '%联考%' and a.sxh not like '%W%'  group by a.shum,a.gjdj, a.tsfljc,b.xscs order by b.xscs desc";
            String sqlString = sql.toString();
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sqlString);
            ps.setString(1,"待发");
            ps.setString(2,"已发");
            ps.setString(3,"2000000747");
            ps.setString(4,"%联考%");
            ps.setString(5,"%W%");
            if (bmmc != "") {
                ps.setString(6, "%" + bmmc + "%");
            }
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
