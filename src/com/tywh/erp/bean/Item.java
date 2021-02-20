package com.tywh.erp.bean;

public class Item {

    private String shum;

    private String tsfljc;

    private double gjdj;

    private Integer qckc;

    private Integer qmkc;

    private Integer xscs;

    private String kczzl;

    private String kcdxl;


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

    public String getKcdxl() {
        return kcdxl;
    }

    public void setKcdxl(String kcdxl) {
        this.kcdxl = kcdxl;
    }

    public String getKczzl() {
        return kczzl;
    }

    public void setKczzl(String kczzl) {
        this.kczzl = kczzl;
    }
}
