package com.tywh.erp.inventory.dao.impl;

import com.tywh.erp.bean.Condition;
import com.tywh.erp.bean.Item;
import com.tywh.erp.inventory.dao.ItemDao;
import com.tywh.erp.util.DbUtil;
import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDaoImpl implements ItemDao {
    @Override
    public List<Item> queryItemList(Condition condition) {
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
            if (condition.getBmmc() != "") {
                sql.append("and bmmc like ?");
            }
            if (condition.getTsfljc() != "") {
                sql.append("and tsfljc like ?");
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
            if (condition.getBmmc() != "") {
                ps.setString(6, "%" + condition.getBmmc() + "%");
            }
            if (condition.getTsfljc() != "") {
                ps.setString(7, "%" + condition.getTsfljc() + "%");
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
            DbUtil.close(null, ps, rs);
        }

        return itemList;
    }

    @Override
    public Map<String, Integer> queryQckc(Condition condition) {
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
            DbUtil.close(null, ps, rs);
        }
        return qckcMap;
    }

    @Override
    public Map<String, Integer> queryQmkc(Condition condition) {
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
            DbUtil.close(null, ps, rs);
        }
        return qmkcMap;
    }

    @Override
    public Map<String, Integer> queryXscs(Condition condition) {
        /*
        select shum,tsfljc, dj,sum(cs) as xscs,xsbmmc from (select fhdbh,sxh,shum,tsfljc, dj,kwbh,cs,xsbmmc from xsmxview union select fhdbh,sxh,shum,tsfljc,dj,kwbh,cs,xsbmmc from XsmxnView) t2
where fhdbh in (select fhdbh from (select fhdbh,shrq,khbh,fhzt from xsdview union select fhdbh,shrq,khbh,fhzt from xsdnview) t1
where fhzt in('待发','已发') and  shrq >= '2020-02-01' and shrq <= '2020-05-31' and khbh <> '2000000747')
and tsfljc not like '%联考%' and sxh not like '%W%' group by shum,tsfljc,dj,xsbmmc
         */
        StringBuilder sql = new StringBuilder();
        sql.append("select shum,tsfljc, dj,sum(cs) as xscs from (select fhdbh,sxh,shum,tsfljc, dj,kwbh,cs from xsmxview ");
        sql.append("union select fhdbh,sxh,shum,tsfljc,dj,kwbh,cs from XsmxnView) t2 where fhdbh in (select fhdbh from ");
        sql.append("(select fhdbh,shrq,khbh,fhzt,xsbmmc from xsdview union select fhdbh,shrq,khbh,fhzt,xsbmmc from xsdnview) t1 where ");
        sql.append("fhzt in(?,?) and  shrq >= ? and shrq <= ? ");
        if (condition.getXsbmmc() != "") {
            sql.append("and xsbmmc like ? ");
        }
        sql.append("and khbh <> ?) and tsfljc not like ? and sxh not like ? group by shum,tsfljc,dj");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, Integer> xscsMap = new HashMap<>();
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,"待发");
            ps.setString(2,"已发");
            ps.setString(3,condition.getStartdate());
            ps.setString(4,condition.getEnddate());
            if (condition.getXsbmmc() != "") {
                ps.setString(5,"%" + condition.getXsbmmc() + "%");
            }
            ps.setString(6,"2000000747");
            ps.setString(7,"%联考%");
            ps.setString(8,"%W%");

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
            DbUtil.close(null, ps, rs);
        }
        return xscsMap;
    }

    @Override
    public Integer queryZxscs(Condition condition) {
        StringBuilder sql = new StringBuilder();
        sql.append("select sum(cs) as zxscs from (select fhdbh,sxh,shum,tsfljc, dj,kwbh,cs from xsmxview ");
        sql.append("union select fhdbh,sxh,shum,tsfljc,dj,kwbh,cs from XsmxnView) t2 where fhdbh in (select fhdbh from ");
        sql.append("(select fhdbh,shrq,khbh,fhzt,xsbmmc from xsdview union select fhdbh,shrq,khbh,fhzt,xsbmmc from xsdnview) t1 where ");
        sql.append("fhzt in(?,?) and  shrq >= ? and shrq <= ? ");
        if (condition.getXsbmmc() != "") {
            sql.append("and xsbmmc like ? ");
        }
        sql.append("and khbh <> ?) and tsfljc not like ? and sxh not like ? ");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, Integer> xscsMap = new HashMap<>();
        Integer zxscs = null;
        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,"待发");
            ps.setString(2,"已发");
            ps.setString(3,condition.getStartdate());
            ps.setString(4,condition.getEnddate());
            if (condition.getXsbmmc() != "") {
                ps.setString(5,"%" + condition.getXsbmmc() + "%");
            }
            ps.setString(6,"2000000747");
            ps.setString(7,"%联考%");
            ps.setString(8,"%W%");

            rs = ps.executeQuery();
            while (rs.next()) {
                zxscs = rs.getInt("zxscs");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(null, ps, rs);
        }
        return zxscs;
    }

    public static void main(String[] args) {
        Condition condition = new Condition();
        condition.setStartdate("2020-02-01");
        condition.setEnddate("2020-05-31");
        condition.setXsbmmc("文化");
        ItemDaoImpl itemDao = new ItemDaoImpl();
//        itemDao.queryXscs(condition);
        itemDao.queryZxscs(condition);
    }
}
