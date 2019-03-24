package com.parking.park.tcp;

public class RspModel {

    private String cmdType;
    private String servSeq;
    private String data;

    public String getCmdType() {
        return cmdType;
    }

    public void setCmdType(String cmdType) {
        this.cmdType = cmdType;
    }

    public String getServSeq() {
        return servSeq;
    }

    public void setServSeq(String servSeq) {
        this.servSeq = servSeq;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
