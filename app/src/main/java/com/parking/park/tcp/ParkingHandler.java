package com.parking.park.tcp;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.parking.park.BaseApplication;
import com.parking.park.bean.EntranceBean;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

class ParkingHandler extends SimpleChannelInboundHandler<String> {
    private static final String TAG = "ParkingHandler";
    private ParkingInfoListener listener;

    ParkingHandler(ParkingInfoListener listener) {
        this.listener = listener;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ParkingHelper.channelCtx = ctx;
        if (listener != null) {
            listener.onChannelActive(ctx);
        }
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String rcvMsg) throws Exception {

        if (listener != null) {
            if (!TextUtils.isEmpty(rcvMsg)) {
                Log.v("gl", "rcvMsg==" + rcvMsg);
                Gson gson = new Gson();
                RspModel model = gson.fromJson(rcvMsg, RspModel.class);


                if (model != null) {
                    String name = EmReceive.getReCmd(model.getCmdType());
                    if (!TextUtils.isEmpty(name)) {
                        EmSend emCommand = EmSend.getCmd(name);
                        listener.onReceiveInfo(ctx, emCommand, gson.toJson(model.getData()));
                    } else
                        Log.v("gl", "非法指令");
                } else {
                    Log.v("gl", "指令格式错误");
                }
            }
        }
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        ParkingHelper.channelCtx = null;
        if (listener != null) {
            listener.onChannelInactive(ctx);
        }
    }

}
