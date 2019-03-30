package com.parking.park.tcp;

public enum EmSend {
    HEART_BEAT("901", "111111"),
    ENTRANCE("1002", "111111"),
    EXIT("1004", "111111"),
    OVER("1006","111111");

    private String cmdType;
    private String servSeq;

    EmSend(String cmdType, String servSeq) {
        this.cmdType = cmdType;
        this.servSeq = servSeq;
    }


    public String getCmdType() {
        return cmdType;
    }

    public String getServSeq() {
        return servSeq;
    }


    public static EmSend getCmd(String name) {
        EmSend cmd = null;
        for (EmSend re : EmSend.values()) {
            if (name.equals(re.name())) {
                return re;
            }
        }
        return cmd;
    }
}
