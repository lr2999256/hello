package com.test.netty.server;

import com.test.netty.protobuf.SubscribeReq;
import com.test.netty.protobuf.SubscribeResp;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SubReqServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq.SubScribeReq req = (SubscribeReq.SubScribeReq)msg;
        System.out.println("server accept client subscribe req:["+req.toString()+"]");
        SubscribeResp.SubScribeResp.Builder builder = SubscribeResp.SubScribeResp.newBuilder();
        builder.setSubReqId(req.getSubReqId());
        builder.setRespCode("0");
        builder.setDesc("please wait 3 day");
        ctx.writeAndFlush(builder.build());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
