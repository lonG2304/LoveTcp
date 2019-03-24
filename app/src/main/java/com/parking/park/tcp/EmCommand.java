package com.parking.park.tcp;

public enum EmCommand {
    HEART_BEAT("901", "111111"),
    ENTRANCE("1002", "111111"),
    EXIT("1004", "111111");





    private String cmdType;
    private String servSeq;

    EmCommand(String cmdType, String servSeq) {
        this.cmdType = cmdType;
        this.servSeq = servSeq;
    }


    public String getCmdType() {
        return cmdType;
    }

    public String getServSeq() {
        return servSeq;
    }
}
