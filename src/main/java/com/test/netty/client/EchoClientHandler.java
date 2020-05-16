package com.test.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Active");
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8)); //#2 通道连接上后写入消息 记得flush() 很重要
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        System.out.println("Read");

        System.out.println("Client received: " + ByteBufUtil.hexDump(in.readBytes(in.readableBytes())));  //#4
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,              //#5
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
