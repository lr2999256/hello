package com.test.netty.client;

import com.test.netty.protobuf.SubscribeReq;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.stream.IntStream;

public class SubReqClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        IntStream.range(0,100).forEach((i)->{
            SubscribeReq.SubScribeReq.Builder builder = SubscribeReq.SubScribeReq.newBuilder();
            builder.setSubReqId(i);
            builder.setUserName("Nick");
            builder.setProductName("Netty");
            builder.setAddress("ChangSha");
            ctx.writeAndFlush(builder.build());
        });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("Receive server message : ["+body + "]");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
