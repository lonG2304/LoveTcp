package com.parking.park.tcp;

import android.util.Log;

import com.google.gson.Gson;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

class ParkingHandler extends SimpleChannelInboundHandler<String> {
    private static final String TAG = "MusicWiseHandler";
    private ParkingInfoListener listener;

    ParkingHandler(ParkingInfoListener listener) {
        this.listener = listener;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        Log.e(TAG, "channelActive");
        ParkingHelper.channelCtx = ctx;
        if (listener != null) {
            listener.onChannelActive(ctx);
        }

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String rcvMsg) throws Exception {
        if (listener != null) {

            Gson gson = new Gson();
            RspModel model = gson.fromJson(rcvMsg, RspModel.class);
            String name = EmReceive.getReCmd(model.getCmdType());
            EmSend emCommand = EmSend.getCmd(name);

            listener.onReceiveInfo(ctx, emCommand, model.getData());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        Log.e(TAG, "channelInactive");
        ParkingHelper.channelCtx = null;
        if (listener != null) {
            listener.onChannelInactive(ctx);
        }
    }
}
