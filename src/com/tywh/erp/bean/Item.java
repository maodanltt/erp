package com.tywh.erp.bean;

public class Item {
    /*
    shum, tsfljc, gjdj, bmmc, xscs, zxscs, qckc, qmkc, xscs / ((qckc + qmkc) / 2)) AS spzzl,
                xscs / zxscs) AS dpdxl
     */

    //����
    private String shum;
    //ͼ�������
    private String tsfljc;
    //ͼ�鶨��
    private double gjdj;
    //�ڳ����
    private Integer qckc;
    //��ĩ���
    private Integer qmkc;
    //��Ʒ���۲���
    private Integer xscs;
    //��Ʒ��ת��
    private double spzzl;
    //������
    private double kcdxl;

    //���������ۡ�ͼ���������ϣ���Ϊ����
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
