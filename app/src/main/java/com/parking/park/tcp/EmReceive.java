package com.parking.park.tcp;

public enum EmReceive {
    HEART_BEAT("900"),
    ENTRANCE("1001"),
    EXIT("1003");


    private String cmdType;

    EmReceive(String cmdType) {
        this.cmdType = cmdType;
    }

    public String getCmdType() {
        return cmdType;
    }


    public static String getReCmd(String code) {
        for (EmReceive re : EmReceive.values()) {
            if (code.equals(re.cmdType)) {
                return re.name();
            }
        }
        return "";
    }
}
