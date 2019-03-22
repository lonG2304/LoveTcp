package com.parking.park.tcp;


import android.util.Log;

import com.google.gson.Gson;
import com.parking.park.Constant;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ParkingHelper {

    private static ParkingHelper instance;
    static ChannelHandlerContext channelCtx;


    private ParkingHelper() {

    }

    public static ParkingHelper getInstance() {
        if (instance == null) {
            synchronized (ParkingHelper.class) {
                if (instance == null) {
                    instance = new ParkingHelper();
                }
            }
        }
        return instance;
    }


    public void start(ParkingInfoListener listener) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup wokerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, wokerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ParkingChannel(listener));

            ChannelFuture channelFuture = serverBootstrap.bind(Constant.PORT).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }
    }


    public void sendMessage(SendMsg sender) {
        if (channelCtx == null) {
            Log.e("gl", "sendMessage but ctx is null");
            return;
        }

        String msg = new Gson().toJson(sender);
        channelCtx.writeAndFlush(msg);
    }


    public void sendHeart() {
        SendMsg sendMsg = SendMsg.creat(EmCommand.HEART_BEAT);
        sendMessage(sendMsg);
    }


    public void sendMsg(boolean result, EmCommand emCommand) {
        SendMsg sendMsg;
        if (result) {
            sendMsg = SendMsg.creat(emCommand, 0, "yes");
        } else
            sendMsg = SendMsg.creat(emCommand, 1, "no");
        sendMessage(sendMsg);
    }


}
