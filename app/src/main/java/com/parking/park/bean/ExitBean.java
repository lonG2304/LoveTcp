package com.parking.park.bean;

/**
 * Created by Administrator on 2019/3/23 0023.
 */


//"data":{"sc":"2小时4分","cp":"苏B12345","je":"6.50元"，"clxz":"包月车
//","fjxx1"："一路顺风","fkewm"："http://abc.com"}
//        }

public class ExitBean extends BaseBean {
    private String sc;
    private String cp;
    private String je;
    private String clxz;
    private String fjxx1;
    private String fkewm;

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getJe() {
        return je;
    }

    public void setJe(String je) {
        this.je = je;
    }

    public String getClxz() {
        return clxz;
    }

    public void setClxz(String clxz) {
        this.clxz = clxz;
    }

    public String getFjxx1() {
        return fjxx1;
    }

    public void setFjxx1(String fjxx1) {
        this.fjxx1 = fjxx1;
    }

    public String getFkewm() {
        return fkewm;
    }

    public void setFkewm(String fkewm) {
        this.fkewm = fkewm;
    }
}
