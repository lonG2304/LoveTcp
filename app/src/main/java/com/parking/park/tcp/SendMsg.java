package com.parking.park.tcp;

public class SendMsg {
    /**
     * cmdType : 100
     * servSeq : 111111
     * data : {"code":0,"msg":""}
     */

    private String cmdType;
    private String servSeq;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        public DataBean(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        /**
         * code : 0
         * msg :
         */

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }


    public static SendMsg creat(EmSend emCommand) {
        SendMsg sendMsg = new SendMsg(emCommand);
        return sendMsg;
    }

    public SendMsg(EmSend emCommand) {
        this.cmdType = emCommand.getCmdType();
        this.servSeq = emCommand.getServSeq();
    }

    public static SendMsg creat(EmSend emCommand, int code, String msg) {
        SendMsg sendMsg = new SendMsg(emCommand, code, msg);
        return sendMsg;
    }


    public SendMsg(EmSend emCommand, int code, String msg) {
        this.cmdType = emCommand.getCmdType();
        this.servSeq = emCommand.getServSeq();
        this.data = creatDataBean(code, msg);
    }


    public static DataBean creatDataBean(int code, String msg) {
        DataBean dataBean = new DataBean(code, msg);
        return dataBean;
    }


    public SendMsg(RspModel rspModel, int code, String msg) {
        this.cmdType = rspModel.getCmdType();
        this.servSeq = rspModel.getServSeq();
        this.data = creatDataBean(code, msg);
    }


    public static SendMsg creat(RspModel rspModel, int code, String msg) {
        SendMsg sendMsg = new SendMsg(rspModel, code, msg);
        return sendMsg;
    }


}
