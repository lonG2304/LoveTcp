package com.parking.park.tcp;

public class RspModel<T> {

    private String cmdType;
    private String servSeq;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
