package com.parking.park.tcp;

import io.netty.channel.ChannelHandlerContext;

public interface ParkingInfoListener {
    void onChannelActive(ChannelHandlerContext ctx);

    void onReceiveInfo(ChannelHandlerContext ctx, MessageReceiver info);

    void onChannelInactive(ChannelHandlerContext ctx);
}
