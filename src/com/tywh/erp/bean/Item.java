package com.tywh.erp.bean;

public class Item {
    /*
    shum, tsfljc, gjdj, bmmc, xscs, zxscs, qckc, qmkc, xscs / ((qckc + qmkc) / 2)) AS spzzl,
                xscs / zxscs) AS dpdxl
     */

    //书名
    private String shum;
    //图书分类简称
    private String tsfljc;
    //图书定价
    private double gjdj;
    //期初库存
    private Integer qckc;
    //期末库存
    private Integer qmkc;
    //单品销售册数
    private Integer xscs;
    //商品周转率
    private double spzzl;
    //动销率
    private double kcdxl;

    //书名、定价、图书分类简称组合，作为主键
    private String key;
    public String getKey() {
        return this.shum + "-" + this.gjdj + "-" + this.tsfljc;
    }

    public String getShum() {
        return shum;
    }

    public void setShum(String shum) {
        this.shum = shum;
    }

    public String getTsfljc() {
        return tsfljc;
    }

    public void setTsfljc(String tsfljc) {
        this.tsfljc = tsfljc;
    }

    public double getGjdj() {
        return gjdj;
    }

    public void setGjdj(double gjdj) {
        this.gjdj = gjdj;
    }

    public Integer getQckc() {
        return qckc;
    }

    public void setQckc(Integer qckc) {
        this.qckc = qckc;
    }

    public Integer getQmkc() {
        return qmkc;
    }

    public void setQmkc(Integer qmkc) {
        this.qmkc = qmkc;
    }

    public Integer getXscs() {
        return xscs;
    }

    public void setXscs(Integer xscs) {
        this.xscs = xscs;
    }

    public double getKcdxl() {
        return kcdxl;
    }

    public void setKcdxl(double kcdxl) {
        this.kcdxl = kcdxl;
    }

    public double getSpzzl() {
        return spzzl;
    }

    public void setSpzzl(double spzzl) {
        this.spzzl = spzzl;
    }
}
