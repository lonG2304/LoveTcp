package com.parking.park.tcp;

import io.netty.channel.ChannelHandlerContext;

public interface ParkingInfoListener {
    void onChannelActive(ChannelHandlerContext ctx);


    void onReceiveInfo(ChannelHandlerContext ctx, EmSend emCommand, String rcvData);


    void onChannelInactive(ChannelHandlerContext ctx);
}
