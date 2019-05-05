package com.parking.park.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ParkingChannel extends ChannelInitializer<SocketChannel> {

    private ParkingInfoListener listener;

    ParkingChannel(ParkingInfoListener listener) {
        this.listener = listener;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        ByteBuf delimiter=Unpooled.copiedBuffer("\n".getBytes());
        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(new ParkingHandler(listener));
    }
}